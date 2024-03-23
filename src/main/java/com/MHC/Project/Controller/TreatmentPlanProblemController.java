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

import com.MHC.Project.Model.TreatmentPlanProblem;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.TreatmentPlanProblemRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/TreatmentPlanProblem")
@SecurityRequirement(name = "mettlerHealth")
public class TreatmentPlanProblemController {
	
	@Autowired
	TreatmentPlanProblemRepository repository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveTreatmentPlanProblem(@RequestBody TreatmentPlanProblem problem) {
		
		String uid = PatientService.generateUID();
		LocalDateTime createdDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createdDate.format(formatter);
		
		problem.setId(uid);
//		problem.setCreatedAt(createdAt);
		
		repository.save(problem);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problem);
		return ResponseEntity.ok().body(dataResponse);	
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTreatmentPlanProblem() {
		
		List<TreatmentPlanProblem> problemList = repository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problemList);
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getTreatmentPlanProblemById(@PathVariable String id) {
		
		Optional<TreatmentPlanProblem> problemOptional = repository.findById(id);
		if (problemOptional.isPresent()) {
			TreatmentPlanProblem problem = problemOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problem);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByProblemName/{problemName}")
	public ResponseEntity<?> getTreatmentPlanProblemByProblemName(@PathVariable String problemName) {
		
		List<TreatmentPlanProblem> problemList = repository.findByProblemName(problemName);
		if (!problemList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problemList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<?> updateTreatmentPlanProblemById(@PathVariable String id, 
															@RequestBody TreatmentPlanProblem problem) {
		
		Optional<TreatmentPlanProblem> problemOptional = repository.findById(id);
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (problemOptional.isPresent()) {
			TreatmentPlanProblem problemClass = problemOptional.get();
			
//			problemClass.setUpdatedAt(updatedAt);
			problemClass.update(problem);
			repository.save(problemClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problemClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteTreatmentPlanProblemById(@PathVariable String id) {
		
		Optional<TreatmentPlanProblem> problemOptional = repository.findById(id);
		if (problemOptional.isPresent()) {
			TreatmentPlanProblem problem = problemOptional.get();
			repository.delete(problem);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	} 
}
 
