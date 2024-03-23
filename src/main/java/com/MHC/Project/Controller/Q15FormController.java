package com.MHC.Project.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Q15Form;
import com.MHC.Project.Repository.Q15FormRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.Q15FormService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/q15form")
@SecurityRequirement(name = "mettlerHealth")
public class Q15FormController {

	@Autowired
	private Q15FormService service;

	@Autowired
	private Q15FormRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(Q15FormController.class);

	// ****** To Add the Location *****\\
	@PostMapping("/location/register")
	public ResponseEntity<?> savePatient(@RequestBody @Validated Q15Form formRequest) {
		try {
			Q15Form q15Form = service.saveQ15Form(formRequest);
			
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0250");
			DataResponse<Q15Form> signInResponse = new DataResponse<>(dataResponse, q15Form);
			return ResponseEntity.ok().body(signInResponse);
		} catch (NoSuchElementException e) {
			logger.error("Q15 Id already Exisits....");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0090"));
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getQ15Form(@PathVariable String id) {
		Optional<Q15Form> Q15Form = repository.findById(id);
		if (Q15Form.isPresent()) {
			Q15Form q15Form = Q15Form.get();
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0251");
			DataResponse<Q15Form> signInResponse = new DataResponse<>(dataResponse, q15Form);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("No records found for the given id: " + id);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0089"));
		}
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllform() {
		List<Q15Form> Q15list = service.getAllForm();
		DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0252");
		DataResponse<List<Q15Form>> signInResponse = new DataResponse<>(dataResponse, Q15list);
		return ResponseEntity.ok().body(signInResponse);
	}

	@PutMapping("/location/update/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable String id, @RequestBody @Validated Q15Form formRequest) {
		try {
			Q15Form updatePatient = service.updateQ15Form(id, formRequest);
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0253");
			DataResponse<Q15Form> signInResponse = new DataResponse<>(dataResponse, updatePatient);
			return ResponseEntity.ok().body(signInResponse);
		} catch (PageNotFound e) {
			logger.error("Q15 Location Id not found...");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0090"));																										
		}
	}

	@PostMapping("/Q15/register")
	public ResponseEntity<?> saveQ15Min(@RequestBody @Validated Q15Form q15Request) {
		try {
			Q15Form q15Form = service.saveQ15Form(q15Request);
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0254");
			DataResponse<Q15Form> signInResponse = new DataResponse<>(dataResponse, q15Form);
			return ResponseEntity.ok().body(signInResponse);
		} catch (NoSuchElementException e) {
			logger.error("Q15 Id already Exisits....");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0090"));
		}
	}

	@PutMapping("/Q15/update/{id}")
	public ResponseEntity<?> updateQ15Min(@PathVariable String id, @RequestBody @Validated Q15Form q15Request) {
		try {
			Q15Form updateQ15Min = service.updateQ15Form(id, q15Request);
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0255");
			DataResponse<Q15Form> signInResponse = new DataResponse<>(dataResponse, updateQ15Min);
			return ResponseEntity.ok().body(signInResponse);
		} catch (PageNotFound e) {
			logger.error("Q15Form Id not found...");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0090"));
		}
	}

	@PostMapping("/activity/register")
	public ResponseEntity<?> savePrecaution(@RequestBody @Validated Q15Form precautionRequest) {
		try {
			Q15Form q15Form = service.saveQ15Form(precautionRequest);
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0256");
			DataResponse<Q15Form> signInResponse = new DataResponse<>(dataResponse, q15Form);
			return ResponseEntity.ok().body(signInResponse);
		} catch (NoSuchElementException e) {
			logger.error("Q15 Id already Exisits....");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0090"));
		}
	}

	@PutMapping("/activity/update/{id}")
	public ResponseEntity<?> updatePrecaution(@PathVariable String id,
			@RequestBody @Validated Q15Form precautionRequest) {
		try {
			Q15Form updatePrecaution = service.updateQ15Form(id, precautionRequest);
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0257");
			DataResponse<Q15Form> signInResponse = new DataResponse<>(dataResponse, updatePrecaution);
			return ResponseEntity.ok().body(signInResponse);
		} catch (PageNotFound e) {
			logger.error("Q15 Id Not found");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0090"));
		}
	}
}
