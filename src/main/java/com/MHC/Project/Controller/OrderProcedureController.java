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

import com.MHC.Project.Model.OrderProcedure;
import com.MHC.Project.Repository.OrderProcedureRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/labpro")
@RestController
@SecurityRequirement(name = "mettlerHealth")
public class OrderProcedureController {
	
	@Autowired
	private OrderProcedureRepository procedureRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> addOrderprocedure(@RequestBody OrderProcedure orderProcedure) {
		
		String uid = PatientService.generateUID();
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);
		
		orderProcedure.setId(uid);
		orderProcedure.setCreatedAt(createdAt);
		
		procedureRepository.save(orderProcedure);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
	    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, orderProcedure); 
		return ResponseEntity.ok().body(dataResponse);		 
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllOrderProcedure() {
		
		List<OrderProcedure> procedureList = procedureRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
	    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, procedureList); 
		return ResponseEntity.ok().body(dataResponse);
		
	}
	
	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getOrderProcedureById(@PathVariable String id) {
		
		Optional<OrderProcedure> procedureOptional = procedureRepository.findById(id);
		if (procedureOptional.isPresent()) {
			OrderProcedure orderProcedure = procedureOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, orderProcedure); 
			return ResponseEntity.ok().body(dataResponse); 
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateOrderProcedure(@PathVariable String id, @RequestBody OrderProcedure orderProcedure) {
		
		Optional<OrderProcedure> procedureOptional = procedureRepository.findById(id);
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (procedureOptional.isPresent()) {
			OrderProcedure procedure = procedureOptional.get();
			procedure.setUpdatedAt(updatedAt);
			procedure.update(orderProcedure);
			procedureRepository.save(procedure); 
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, procedure); 
			return ResponseEntity.ok().body(dataResponse);
		} 
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/getByPid/{pid}")
	public ResponseEntity<?> getOrderProcedureByPID(@PathVariable String pid) {
		
		List<OrderProcedure> procedureList = procedureRepository.findByPid(pid);
		if (procedureList.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
	    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, procedureList); 
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteOrderProcedure(@PathVariable String id) {
		
		Optional<OrderProcedure> procedureOptional = procedureRepository.findById(id);
		if (procedureOptional.isPresent()) {
			OrderProcedure procedure = procedureOptional.get();
			procedureRepository.delete(procedure);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
