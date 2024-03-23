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

import com.MHC.Project.Model.TherapySchedule;
import com.MHC.Project.Repository.TherapyScheduleRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE}, maxAge = 3600, allowedHeaders = "*" )
@RestController
@RequestMapping("/api/TherapySchedule")
@SecurityRequirement(name = "mettlerHealth")
public class TherapyScheduleController {
	
	@Autowired
	TherapyScheduleRepository repository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveTherapySchedule(@RequestBody TherapySchedule schedule) {
		
		String uid = PatientService.generateUID();
		
		schedule.setId(uid);
		repository.save(schedule);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, schedule);
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTherapySchedule() {
		
		List<TherapySchedule> scheduleList = repository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, scheduleList);
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getTherapyScheduleById(@PathVariable String id) {
		
		Optional<TherapySchedule> scheduleOptional = repository.findById(id);
		if (scheduleOptional.isPresent()) {
			TherapySchedule schedule = scheduleOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, schedule);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByScheduleDate/{scheduleDate}")
	public ResponseEntity<?> getTherapyScheduleByScheduleDate(@PathVariable String scheduleDate) {
		
		List<TherapySchedule> scheduleList = repository.findByScheduleDate(scheduleDate);
		if (!scheduleList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, scheduleList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByScheduleTime/{scheduleTime}")
	public ResponseEntity<?> getTherapyScheduleByScheduleTime(@PathVariable String scheduleTime) {
		
		List<TherapySchedule> therapyList = repository.findByScheduleTime(scheduleTime);
		if (!therapyList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapyList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByScheduleDateAndTime/{scheduleDate}/{scheduleTime}")
	public ResponseEntity<?> getTherapyScheduleByScheduleDateAndTime(@PathVariable String scheduleDate,
																	 @PathVariable String scheduleTime) {
		
		List<TherapySchedule> scheduleList = repository.findByScheduleDateAndScheduleTime(scheduleDate, scheduleTime);
		if (!scheduleList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, scheduleList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@PutMapping("/updateTherapyScheduleById/{id}")
	public ResponseEntity<?> updateTherapyScheduleById(@PathVariable String id,
													   @RequestBody TherapySchedule schedule) {
		
		Optional<TherapySchedule> scheduleOptional = repository.findById(id);
		if (scheduleOptional.isPresent()) {
			TherapySchedule therapySchedule = scheduleOptional.get();
			
			therapySchedule.update(schedule);
			repository.save(therapySchedule);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, therapySchedule);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@DeleteMapping("/deleteTherapyById/{id}")
	public ResponseEntity<?> deleteTherapyScheduleById(@PathVariable String id) {
		
		Optional<TherapySchedule> scheduleOptional = repository.findById(id);
		if (scheduleOptional.isPresent()) {
			TherapySchedule schedule = scheduleOptional.get();
			
			repository.delete(schedule);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}	