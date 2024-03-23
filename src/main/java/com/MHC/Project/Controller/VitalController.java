package com.MHC.Project.Controller;

import java.util.Comparator;
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
import com.MHC.Project.Model.Visit;
import com.MHC.Project.Model.Vital;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.VisitRepository;
import com.MHC.Project.Repository.VitalRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.VitalService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("api/vital")
@SecurityRequirement(name = "mettlerHealth")
public class VitalController {

	private static final Logger logger = LoggerFactory.getLogger(Q15FormController.class);

	@Autowired
	VitalService service;

	@Autowired
	VitalRepository repository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	VisitRepository patientVisitAdmitRepository;

	@PostMapping("/register")
	public ResponseEntity<?> saveDetails(@RequestBody @Validated Vital vital) {
		String patientId = vital.getPatientId();
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			logger.error("Patient Not Found for given Id " + patientId);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}

		Optional<Visit> patientVisit = patientVisitAdmitRepository.findById(vital.getLastVisit());
		if (patientVisit.isPresent()) {
			if(patientId.equals(patientVisit.get().getPid())) {
				String dateOfLastVisit=patientVisit.get().getVisitStartDate();
				vital.setLastVisit(dateOfLastVisit);
			}else {
				logger.error("Patient visit with given patientId not found");
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0055"));
			}
			
		}else {
			logger.error("Patient visit with given patientVisitId not found");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0045"));
		}
		
		String lastVisit= patientVisit.get().getVisitStartDate();
		List<Vital> vitalList = repository.findByLastVisit(lastVisit);
		if(!vitalList.isEmpty()) {
			vitalList.sort(Comparator.comparing(Vital::getLastVisit).reversed());
			Vital existingVital = vitalList.get(0);
			
			if(vital.getBodyTemperature().getUnit().isEmpty()) {
				vital.setBodyTemperature(existingVital.getBodyTemperature());
			}
			if(vital.getPulseRate().getUnit().isEmpty()) {
				vital.setPulseRate(existingVital.getPulseRate());
			}
			if(vital.getHeartRate().getUnit().isEmpty()) {
				vital.setHeartRate(existingVital.getHeartRate());
			}
			if(vital.getRespirationRate().getUnit().isEmpty()) {
				vital.setRespirationRate(existingVital.getRespirationRate());
			}
			if(vital.getBloodPressure().getUnit().isEmpty()) {
				vital.setBloodPressure(existingVital.getBloodPressure());
			}
			if(vital.getBloodOxygen().getUnit().isEmpty()) {
				vital.setBloodOxygen(existingVital.getBloodOxygen());
			}
			if(vital.getWeight().getUnit().isEmpty()) {
				vital.setWeight(existingVital.getWeight());
			}
			if(vital.getHeight().getUnit().isEmpty()) {
				vital.setHeight(existingVital.getHeight());
			}
			if(vital.getBloodGlucoseLevel().getUnit().isEmpty()) {
				vital.setBloodGlucoseLevel(existingVital.getBloodGlucoseLevel());
			}
			if(vital.getCircumferenceOrGirth().getUnit().isEmpty()) {
				vital.setCircumferenceOrGirth(existingVital.getCircumferenceOrGirth());
			}
			if(vital.getPain().getUnit().isEmpty()) {
				vital.setPain(existingVital.getPain());
			}
			if(vital.getPulseOximetry().getUnit().isEmpty()) {
				vital.setPulseOximetry(existingVital.getPulseOximetry());
			}
			
			Vital Vital = service.saveDetails(vital);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<Vital> signInResponse = new DataResponse<>(verifyResponse, Vital);
			return ResponseEntity.ok().body(signInResponse);
			
		}
		else {
			Vital Vital = service.saveDetails(vital);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<Vital> signInResponse = new DataResponse<>(verifyResponse, Vital);
			return ResponseEntity.ok().body(signInResponse);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDetails(@PathVariable String id, @RequestBody @Validated Vital vital) {
		String patientId = vital.getPatientId();
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			logger.error("Patient Not Found for given Id " + patientId);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}
		try {
			vital.setId(id);
			Vital vitalUpdate = service.updateDetail(id, vital);
			vitalUpdate.setEnteredDate(vital.getEnteredDate());
			repository.save(vitalUpdate);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<Vital> signInResponse = new DataResponse<>(verifyResponse, vitalUpdate);
			return ResponseEntity.ok().body(signInResponse);
		} catch (PageNotFound e) {
			logger.error("Vital Id not found");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0094"));
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllVital() {
		try {
			List<Vital> vital = service.getAllVital();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<List<Vital>> signInResponse = new DataResponse<>(verifyResponse, vital);
			return ResponseEntity.ok().body(signInResponse);
		} catch (Exception e) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0038"));
		}
	}

	@GetMapping("getByPatientId/{patientId}")
	public ResponseEntity<?> getById(@PathVariable String patientId) {
		List<Vital> vitalOptional = repository.findByPatientId(patientId);
		if (!vitalOptional.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<List<Vital>> signInResponse = new DataResponse<>(verifyResponse, vitalOptional);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("No records found for given Patient");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {
		boolean delete = service.deleteById(id);
		if (delete) {
			VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0209");
			return ResponseEntity.ok().body(verifyResponse);
		} else {
			VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0094");
			return ResponseEntity.ok().body(verifyResponse);
		}
	}

	@GetMapping("getByPatientIdAndId/{patientId}/{id}")
	public ResponseEntity<?> getByIdAndId(@PathVariable String patientId,@PathVariable String id)
	{
		Optional<Vital> vitalOptional = repository.findByPatientIdAndId(patientId,id);
		if(vitalOptional.isPresent())
		{
			Vital vital= vitalOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<Vital> signInResponse = new DataResponse<>(verifyResponse,vital);
			return ResponseEntity.ok().body(signInResponse);
		}
		else {
			logger.error("No records found for given Patient And vital id.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
		}
	}
	
	@GetMapping("/getLatestVital/{patientId}")
	public ResponseEntity<?> getVital(@PathVariable String patientId) {

		List<Vital> vital = repository.findByPatientId(patientId);

		if (!vital.isEmpty()) {
			vital.sort(Comparator.comparing(Vital::getLastVisit));   //use .reversed() to sort descending
	
			int size = vital.size();
			Vital sortedVital = vital.get(size-1);
	
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<Vital> signInResponse = new DataResponse<>(verifyResponse, sortedVital);
			return ResponseEntity.ok().body(signInResponse);
		}else {
			logger.error("No records found for given Patient");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
		}

	}
}