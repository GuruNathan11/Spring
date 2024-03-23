package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import com.MHC.Project.Model.form;
import com.MHC.Project.Repository.formRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/forms")
@SecurityRequirement(name = "mettlerHealth")
public class formController {

	@Autowired
	private final formRepository formRepository;

	public formController(formRepository formRepository) {
		this.formRepository = formRepository;
	}

	@PostMapping("add")
	public ResponseEntity<?> createForm(@RequestBody form form) {
		// Check if the form already exists
		boolean formExists = formRepository.existsByName(form.getName());
		
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);

		if (formExists) {
			// Return an error response indicating that the form already exists
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0087"));
		}
		form.setId(generateId());
		form.setCreatedAt(createdAt);
		form createdForm = formRepository.save(form);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, createdForm);
		return ResponseEntity.ok(dataResponse);
	}

	@GetMapping("get")
	public ResponseEntity<?> getAllForms() {
		List<form> forms = formRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, forms);
		return ResponseEntity.ok(dataResponse);
	}

	@GetMapping("getByName/{name}")
	public ResponseEntity<?> getByName(@PathVariable String name) {
		form form = formRepository.findByName(name);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, form);
		return ResponseEntity.ok(dataResponse);
	}

	public static String generateId() {
		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(10);
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}

		return sb.toString();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateForm(@PathVariable String id, @RequestBody form form) {
		
		Optional<form> formOptional = formRepository.findById(id);
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (formOptional.isPresent()) {
			form formClass = formOptional.get();
			formClass.setUpdatedAt(updatedAt);
			formClass.update(form);
			formRepository.save(formClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, formClass);
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

//	@DeleteMapping("/ById/{id}")
//	public ResponseEntity<?> deleteById(@PathVariable String id) {
//
//		Optional<form> fields = formRepository.findById(id);
//		if (fields.isPresent()) {
//			formRepository.deleteById(id);
//			VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0209");
//			return ResponseEntity.ok().body(verifyResponse);
//		}
//		VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0078");
//		return ResponseEntity.ok().body(verifyResponse);
//	}
}