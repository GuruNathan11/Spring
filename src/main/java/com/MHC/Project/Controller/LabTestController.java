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

import com.MHC.Project.Model.OrderLabTest;
import com.MHC.Project.Repository.LabTestRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/labTest")
@RestController
@SecurityRequirement(name = "mettlerHealth")
public class LabTestController {

	@Autowired
	private LabTestRepository labTestRepository;

	@PostMapping("/register")
	public ResponseEntity<?> addOrderLabTest(@RequestBody OrderLabTest labTest) {

		String uid = PatientService.generateUID();
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);

		labTest.setId(uid);
		labTest.setCreatedAt(createdAt);

		labTestRepository.save(labTest);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, labTest);
		return ResponseEntity.ok().body(dataResponse);

	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllLabTest() {

		List<OrderLabTest> labTestList = labTestRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, labTestList);
		return ResponseEntity.ok().body(dataResponse);
	}

	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getLabTestById(@PathVariable String id) {

		Optional<OrderLabTest> labTestOptional = labTestRepository.findById(id);
		if (labTestOptional.isPresent()) {
			OrderLabTest labTest = labTestOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, labTest);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateLabTest(@PathVariable String id, @RequestBody OrderLabTest labTest) {

		Optional<OrderLabTest> labTestOptional = labTestRepository.findById(id);
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (labTestOptional.isPresent()) {
			OrderLabTest orderLabTest = labTestOptional.get();
			orderLabTest.setUpdatedAt(updatedAt);
			orderLabTest.update(labTest);
			labTestRepository.save(orderLabTest);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, orderLabTest);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

	@GetMapping("/getByPid/{pid}")
	public ResponseEntity<?> getOrderLabTestByPID(@PathVariable String pid) {

		List<OrderLabTest> labTestList = labTestRepository.findByPid(pid);
		if (labTestList.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, labTestList);
		return ResponseEntity.ok().body(dataResponse);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteLabTest(@PathVariable String id) {

		Optional<OrderLabTest> labTestOptional = labTestRepository.findById(id);
		if (labTestOptional.isPresent()) {
			OrderLabTest labTest = labTestOptional.get();
			labTestRepository.delete(labTest);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
