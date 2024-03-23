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

import com.MHC.Project.Model.OrderImagingProcedure;
import com.MHC.Project.Repository.ImagingProcedureRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/labimgpro")
@RestController
@SecurityRequirement(name = "mettlerHealth")
public class ImagingProcedureController {

	@Autowired
	ImagingProcedureRepository imagingProcedureRepository;

	@PostMapping("/register")
	public ResponseEntity<?> addImagingProcedure(@RequestBody OrderImagingProcedure imagingProcedure) {

		String uid = PatientService.generateUID();
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);

		imagingProcedure.setId(uid);
		imagingProcedure.setCreatedAt(createdAt);
		
		imagingProcedureRepository.save(imagingProcedure);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, imagingProcedure);
		return ResponseEntity.ok().body(dataResponse);

	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllImagingProcedure() {

		List<OrderImagingProcedure> imagingProcedureList = imagingProcedureRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, imagingProcedureList);
		return ResponseEntity.ok().body(dataResponse);
	}

	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getImagingProcedureById(@PathVariable String id) {

		Optional<OrderImagingProcedure> imagingProcedureOptional = imagingProcedureRepository.findById(id);
		if (imagingProcedureOptional.isPresent()) {
			OrderImagingProcedure imagingProcedure = imagingProcedureOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, imagingProcedure);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateImagingProcedure(@PathVariable String id,
			@RequestBody OrderImagingProcedure imagingProcedure) {

		Optional<OrderImagingProcedure> imagingProcedureOptional = imagingProcedureRepository.findById(id);
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (imagingProcedureOptional.isPresent()) {
			OrderImagingProcedure imagingProcedureClass = imagingProcedureOptional.get();
			imagingProcedureClass.setUpdatedAt(updatedAt);
			imagingProcedureClass.update(imagingProcedure);
			imagingProcedureRepository.save(imagingProcedureClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, imagingProcedureClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

	@GetMapping("/getByPid/{pid}")
	public ResponseEntity<?> getOrderImagingProcedureByPID(@PathVariable String pid) {

		List<OrderImagingProcedure> imagingProcedureList = imagingProcedureRepository.findByPid(pid);
		if (imagingProcedureList.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, imagingProcedureList);
		return ResponseEntity.ok().body(dataResponse);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteImagingProcedure(@PathVariable String id) {

		Optional<OrderImagingProcedure> imagingProcedure = imagingProcedureRepository.findById(id);
		if (imagingProcedure.isPresent()) {
			OrderImagingProcedure imagingProcedureClass = imagingProcedure.get();
			imagingProcedureRepository.delete(imagingProcedureClass);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
