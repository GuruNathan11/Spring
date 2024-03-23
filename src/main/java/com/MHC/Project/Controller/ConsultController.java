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

import com.MHC.Project.Model.OrderConsult;
import com.MHC.Project.Repository.ConsultRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/labConsult")
@RestController
@SecurityRequirement(name = "mettlerHealth")
public class ConsultController {

	@Autowired
	ConsultRepository consultRepository;

	@PostMapping("/register")
	public ResponseEntity<?> addOrderConsult(@RequestBody OrderConsult consult) {

		String uid = PatientService.generateUID();
		LocalDateTime createdDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createdDate.format(formatter);

		consult.setId(uid);
		consult.setCreatedAt(createdAt);
		
		consultRepository.save(consult);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, consult);
		return ResponseEntity.ok().body(dataResponse);

	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllOrderConsult() {

		List<OrderConsult> consultList = consultRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, consultList);
		return ResponseEntity.ok().body(dataResponse);

	}

	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getOrderConsultById(@PathVariable String id) {

		Optional<OrderConsult> consultOptional = consultRepository.findById(id);
		if (consultOptional.isPresent()) {
			OrderConsult orderConsult = consultOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, orderConsult);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateOrderConsult(@PathVariable String id, @RequestBody OrderConsult orderConsult) {

		Optional<OrderConsult> consultOptional = consultRepository.findById(id);
		
		LocalDateTime updatedDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updatedDate.format(formatter);
		
		if (consultOptional.isPresent()) {
			OrderConsult consult = consultOptional.get();
			consult.setUpdatedAt(updatedAt);
			consult.update(orderConsult);
			consultRepository.save(consult);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, consult);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

	@GetMapping("/getByPid/{pid}")
	public ResponseEntity<?> getOrderConsultByPID(@PathVariable String pid) {

		List<OrderConsult> consultList = consultRepository.findByPid(pid);
		if (consultList.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, consultList);
		return ResponseEntity.ok().body(dataResponse);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteOrderConsult(@PathVariable String id) {

		Optional<OrderConsult> consultOptional = consultRepository.findById(id);
		if (consultOptional.isPresent()) {
			OrderConsult consult = consultOptional.get();
			consultRepository.delete(consult);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
