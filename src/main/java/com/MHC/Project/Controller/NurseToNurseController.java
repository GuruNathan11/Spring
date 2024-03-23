package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.MHC.Project.Model.NurseToNurse;
import com.MHC.Project.Repository.NurseToNurseRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")

@RestController
@RequestMapping("/api/NurseToNurse")
@SecurityRequirement(name = "mettlerHealth")
public class NurseToNurseController {
	
	@Autowired
	NurseToNurseRepository repository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveNurseToNurse(@RequestBody NurseToNurse nurse) {
		
		String uid = PatientService.generateUID();
		LocalDateTime createdDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createdDate.format(formatter);
		
		nurse.setId(uid);
		nurse.setCreatedAt(createdAt);
		
		repository.save(nurse);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, nurse);
		return ResponseEntity.ok().body(dataResponse);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllNurseToNurse() {
		
		List<NurseToNurse> nurseList = repository.findAll();
		
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, nurseList);
		return ResponseEntity.ok().body(dataResponse);
		
	}
	
	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getNurseToNurseById(@PathVariable String id) {
		
		Optional<NurseToNurse> nurseOptional = repository.findById(id);
		if (nurseOptional.isPresent()) {
			NurseToNurse nurse = nurseOptional.get();
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, nurse);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByAdmittingNurse/{admittingNurse}")
	public ResponseEntity<?> getNurseToNurseByAdmittingNurse(@PathVariable String admittingNurse) {
		
		List<NurseToNurse> nurseList = repository.findByAdmittingNurse(admittingNurse);
		if (!nurseList.isEmpty()) {
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, nurseList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<?> updateNurseToNurse(@PathVariable String id, @RequestBody NurseToNurse nurse) {
		
		Optional<NurseToNurse> nurseOptional = repository.findById(id);
		
		LocalDateTime updatedDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updatedDate.format(formatter);
		if (nurseOptional.isPresent()) {
			NurseToNurse nurseClass = nurseOptional.get();
			nurseClass.setUpdatedAt(updatedAt);
			nurseClass.update(nurse);
			repository.save(nurseClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, nurseClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@DeleteMapping("/ById/{id}")
	public ResponseEntity<?> deleteNurseToNurseById(@PathVariable String id) {
		
		Optional<NurseToNurse> nurseOptional = repository.findById(id);
		if (nurseOptional.isPresent()) {
			NurseToNurse nurse = nurseOptional.get();
			repository.delete(nurse);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
