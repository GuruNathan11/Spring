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

import com.MHC.Project.Model.Visit;
import com.MHC.Project.Repository.VisitRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/visit")
@SecurityRequirement(name = "mettlerHealth")
public class VisitController {
	
	@Autowired
	VisitRepository visitRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> addVisit(@RequestBody Visit visit) {
		
		String uid = PatientService.generateUID();
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);
		
		visit.setId(uid);
		visit.setCreatedAt(createdAt);
		
		visitRepository.save(visit);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, visit);
		return ResponseEntity.ok(dataResponse);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllVisit() {
		
		List<Visit> visitList = visitRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, visitList);
		return ResponseEntity.ok(dataResponse);
	}
 	
	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getVisitById(@PathVariable String id) {
		
		Optional<Visit> visitOptional = visitRepository.findById(id);
		if (visitOptional.isPresent()) {
			Visit visitClass = visitOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, visitClass);
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));	
	}
	
	@GetMapping("/ByPid/{pid}")
	public ResponseEntity<?> getVisitByPid(@PathVariable String pid) {
		
		List<Visit> visitOptional = visitRepository.findByPid(pid);
		if (!visitOptional.isEmpty()) {
//			Visit visitClass = visitOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, visitOptional);
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));	
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateVisit(@PathVariable String id, @RequestBody Visit visit) {
		
		Optional<Visit> visitOptional = visitRepository.findById(id);
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (visitOptional.isPresent()) {
			Visit visitClass = visitOptional.get();
			visitClass.setUpdatedAt(updatedAt);
			visitClass.setRefferingHospital(visit.getRefferingHospital());
			visitClass.update(visit);
			visitRepository.save(visitClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, visitClass);
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteVisit(@PathVariable String id) {

		Optional<Visit> visitOptional = visitRepository.findById(id);
		if (visitOptional.isPresent()) {
			Visit visitClass = visitOptional.get();
			visitRepository.delete(visitClass);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}