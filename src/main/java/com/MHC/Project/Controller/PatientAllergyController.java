package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Error.VerifyResponse;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.PatientAllergy;
import com.MHC.Project.Model.Visit;
import com.MHC.Project.Repository.PatientAllergyRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Repository.VisitRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientAllergyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/Allergy")
@SecurityRequirement(name = "mettlerHealth")
public class PatientAllergyController {
	
	private static final Logger logger = LoggerFactory.getLogger(Q15FormController.class);
	
	@Autowired
	PatientAllergyService service;
	
	@Autowired
	PatientAllergyRepository repository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	VisitRepository patientVisitRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveDetails(@RequestBody @Validated PatientAllergy patientAllergy)
	{
		String patientId=patientAllergy.getPatientId();
		Patient patient= patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			logger.error("Patient Not Found for given Id "+patientId);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
	    }
		
		Optional<Visit> patientVisitAdmitOptional= patientVisitRepository.findById(patientAllergy.getLastVisit());
		if (patientVisitAdmitOptional.isPresent()) {
			if(patientId.equals(patientVisitAdmitOptional.get().getPid())) {
//				String dateOfLastVisit=patientVisitAdmitOptional.get().getVisitStartDate();
				patientAllergy.setLastVisit(patientAllergy.getLastVisit());
			}else {
				logger.error("Patient visit with given patientVisitId not found");
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0045"));
			}
		}else {
			logger.error("Patient visit with given patientVisitId not found");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0045"));
		}
		
		PatientAllergy allergy= service.saveDetails(patientAllergy);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<PatientAllergy> signInResponse = new DataResponse<>(verifyResponse,allergy);
		return ResponseEntity.ok().body(signInResponse);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDetails(@PathVariable String id, @RequestBody PatientAllergy patientAllergy)
	{
//		Optional<PatientVisitAdmit> patientVisitAdmitOptional= patientVisitRepository.findById(patientAllergy.getDateOfLastVisit());
//		if (patientVisitAdmitOptional.isPresent()) {
//			String dateOfLastVisit=patientVisitAdmitOptional.get().getVisit().get(0).getVisitStartDate();
//			patientAllergy.setDateOfLastVisit(dateOfLastVisit);
//		}else {
//			logger.error("Patient visit with given patientVisitId not found");
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0045"));
//		}
//	
//		Optional<Staff> staffOptional = staffRepository.findById(patientAllergy.getPhysicianName());
//		if (staffOptional.isPresent()) {
//			String physicianName=staffOptional.get().getRole();
//			patientAllergy.setPhysicianName(physicianName);
//		}
//		else {
//			logger.error("Staff not found for given Staff Id");
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0038"));
//		}
		try {
			patientAllergy.setId(id);
			PatientAllergy allergyUpdate= service.updateDetails(id,patientAllergy);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<PatientAllergy> signInResponse = new DataResponse<>(verifyResponse,allergyUpdate);
			return ResponseEntity.ok().body(signInResponse);
		}catch(PageNotFound e){
			logger.error("Allergy Id not found");
            return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0095"));
		}
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllAllergyDetails()
	{
		List<PatientAllergy> allergy= service.getAllAllergyDetails();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<List<PatientAllergy>> signInResponse = new DataResponse<>(verifyResponse,allergy);
		return ResponseEntity.ok().body(signInResponse);
	}
	@GetMapping("getByPatientId/{patientId}")
	public ResponseEntity<?> getById(@PathVariable String patientId) {
	    List<PatientAllergy> allergyList = repository.findByPatientId(patientId);
	    
	    if (!allergyList.isEmpty()) {
	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
	        DataResponse<List<PatientAllergy>> signInResponse = new DataResponse<>(verifyResponse, allergyList);
	        return ResponseEntity.ok().body(signInResponse);
	    } else {
	        logger.error("No data found for the Patient");
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
	    }
	}

	
	@GetMapping("getByPatientIdAndId/{patientId}/{id}")
	public ResponseEntity<?> getByIdAndPid(@PathVariable String patientId, @PathVariable String id)
	{
		Optional<PatientAllergy> allergyOptional = repository.findByPatientIdAndId(patientId,id);
		if(allergyOptional.isPresent())
		{
			PatientAllergy allergy= allergyOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<PatientAllergy> signInResponse = new DataResponse<>(verifyResponse,allergy);
			return ResponseEntity.ok().body(signInResponse);
		}
		else {
			logger.error("No data found for the Patient With Allergy Id");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
		}
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id)
	{
		service.deleteById(id);
		VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0209");
		return ResponseEntity.ok().body(verifyResponse);
	}
	
	

}
