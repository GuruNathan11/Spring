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

import com.MHC.Project.Model.Therapy;
import com.MHC.Project.Model.TherapySchedule;
import com.MHC.Project.Repository.TherapyRepository;
import com.MHC.Project.Repository.TherapyScheduleRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE}, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/Therapy")
@SecurityRequirement(name = "mettlerHealth")
public class TherapyController {
	
	@Autowired
	TherapyRepository repository;
	
	@Autowired
	TherapyScheduleRepository scheduleRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveTherapy(@RequestBody Therapy therapy) {
		
		String uid = PatientService.generateUID();
		
		Optional<TherapySchedule> scheduleOptional = scheduleRepository.findById(therapy.getScheduleId());
		if (scheduleOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0194"));
		}
		
		therapy.setId(uid);
		repository.save(therapy);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapy);
		return ResponseEntity.ok().body(dataResponse);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTherapy() {
		
		List<Therapy> therapyList = repository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapyList);
		return ResponseEntity.ok().body(dataResponse);
		
	}
	
	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getTherayById(@PathVariable String id) {
		
		Optional<Therapy> therapyOptional = repository.findById(id);
		if (therapyOptional.isPresent()) {
			Therapy therapy = therapyOptional.get();
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapy);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByDate/{date}")
	public ResponseEntity<?> getTherapyByDate(@PathVariable String date) {
		
		List<Therapy> therapyList = repository.findByDate(date);
		if (!therapyList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapyList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByScheduleId/{scheduleId}")
	public ResponseEntity<?> getTherapyByScheduleId(@PathVariable String scheduleId) {
		
		List<Therapy> therapyList = repository.findByScheduleId(scheduleId);
		if (!therapyList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapyList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
		
	@PutMapping("/updateById/{id}")
	public ResponseEntity<?> updateTherapyById(@PathVariable String id,
											   @RequestBody Therapy therapy) {
		
		Optional<Therapy> therapyOptional = repository.findById(id);
		if (therapyOptional.isPresent()) {
			Therapy therapyClass = therapyOptional.get();
			
			therapyClass.update(therapy);
			repository.save(therapyClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapyClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteTherapyById(@PathVariable String id) {
		
		Optional<Therapy> therapyOptional = repository.findById(id);
		if (therapyOptional.isPresent()) {
			Therapy therapy = therapyOptional.get();
			
			repository.delete(therapy);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

} 
