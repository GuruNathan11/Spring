package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.MHC.Project.Model.Immunization;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Repository.ImmunizationRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.ImmunizationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/Immunization")
@SecurityRequirement(name = "mettlerHealth")
public class ImmunizationController {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	ImmunizationService service;
	
	@Autowired
	ImmunizationRepository repository;

	@PostMapping("/register")
	public ResponseEntity<?> addImmunization(@RequestBody Immunization immunization) {

		Optional<Patient> patient = patientRepository.findById(immunization.getPatientId());
		if (patient.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}

		Immunization immunization1 = service.saveImmunization(immunization);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<Immunization> signInResponse = new DataResponse<>(verifyResponse, immunization1);
		return ResponseEntity.ok().body(signInResponse);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateImmunization(@PathVariable String id, @RequestBody Immunization immunization)
	{
		try {
			immunization.setId(id);
			Immunization UpdatedImmunization= service.updateDetails(id,immunization);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<Immunization> signInResponse = new DataResponse<>(verifyResponse, UpdatedImmunization);
			return ResponseEntity.ok().body(signInResponse);
		}catch(PageNotFound e){
            return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0097"));
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		try {
			List<Immunization> immunization= service.getAllImmunization();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<List<Immunization>> signInResponse = new DataResponse<>(verifyResponse,immunization);
			return ResponseEntity.ok().body(signInResponse);
		}catch(Exception e){
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0038"));
		}
	}
	
	@GetMapping("/getByPatientId/{patientId}")
	public ResponseEntity<?> getByPatientId(@PathVariable String patientId)
	{
		List<Immunization> immunizationOptional = repository.findByPatientId(patientId);
		if(!immunizationOptional.isEmpty())
		{
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<List<Immunization>> signInResponse = new DataResponse<>(verifyResponse,immunizationOptional);
			return ResponseEntity.ok().body(signInResponse);
		}
		else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
		}
	}
	
	@GetMapping("/getByPatientIdAndId/{patientId}/{id}")
	public ResponseEntity<?> getByPatientIdAndId(@PathVariable String patientId, @PathVariable String id) {
	    Optional<Immunization> immunizationOptional = repository.findByPatientIdAndId(patientId, id);
	    
	    if (immunizationOptional.isPresent()) {
	        Immunization immunization = immunizationOptional.get();
	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
	        DataResponse<Immunization> signInResponse = new DataResponse<>(verifyResponse, immunization);
	        return ResponseEntity.ok().body(signInResponse);
	    } else {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
	    }
	}

	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id)
	{
		boolean delete= service.deleteById(id);
		if(delete) {
			VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0209");
			return ResponseEntity.ok().body(verifyResponse);
		}else {
			VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0097");
			return ResponseEntity.ok().body(verifyResponse);
		}
	}
}
