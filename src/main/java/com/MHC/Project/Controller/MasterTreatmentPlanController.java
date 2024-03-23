package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.MasterTreatmentPlan;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Repository.MasterTreatmentPlanRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/MasterTreatmentPlan")
@SecurityRequirement(name = "mettlerHealth")
public class MasterTreatmentPlanController {
	
	@Autowired
	MasterTreatmentPlanRepository repository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveMasterTreatmentPlan(@RequestBody MasterTreatmentPlan masterProblem) {
		
		String uid = PatientService.generateUID();
		LocalDateTime createdDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createdDate.format(formatter);
		
		masterProblem.setId(uid);
//		problem.setCreatedAt(createdAt);
		Optional<Patient> patientOptional = patientRepository.findById(masterProblem.getPid());
		if (patientOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}
		
		List<MasterTreatmentPlan> problemList = repository.findByPid(masterProblem.getPid());
		if (problemList.isEmpty()) {
			masterProblem.setProblemNumber("1");
		}
		
		repository.save(masterProblem);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, masterProblem);
		return ResponseEntity.ok().body(dataResponse);	
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllMasterTreatmentPlan() {
		
		List<MasterTreatmentPlan> problemList = repository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problemList);
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getMasterTreatmentPlanById(@PathVariable String id) {
		
		Optional<MasterTreatmentPlan> problemOptional = repository.findById(id);
		if (problemOptional.isPresent()) {
			MasterTreatmentPlan problem = problemOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problem);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByProblemName/{problemName}")
	public ResponseEntity<?> getMasterTreatmentPlanByProblemName(@PathVariable String problemName) {
		
		List<MasterTreatmentPlan> problemList = repository.findByProblemName(problemName);
		if (!problemList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problemList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<?> updateMasterTreatmentPlanById(@PathVariable String id, 
															@RequestBody MasterTreatmentPlan masterProblem) {
		
		Optional<MasterTreatmentPlan> problemOptional = repository.findById(id);
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (problemOptional.isPresent()) {
			MasterTreatmentPlan problemClass = problemOptional.get();
			
//			problemClass.setUpdatedAt(updatedAt);
			problemClass.update(masterProblem);
			repository.save(problemClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, problemClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
