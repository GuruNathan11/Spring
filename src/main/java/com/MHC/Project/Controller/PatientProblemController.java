package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Error.VerifyResponse;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.PatientProblem;
import com.MHC.Project.Model.Visit;
import com.MHC.Project.Repository.PatientProblemRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.VisitRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientProblemService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/Problem")
@SecurityRequirement(name = "mettlerHealth")
public class PatientProblemController {

	private static final Logger logger = LoggerFactory.getLogger(Q15FormController.class);

	@Autowired
	PatientProblemService service;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	PatientProblemRepository repository;

	@Autowired
	VisitRepository patientVisitRepository;

	@PostMapping("/register")
	public ResponseEntity<?> saveProblem(@RequestBody @Validated PatientProblem patientProblem) {
		String patientId = patientProblem.getPatientId();
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			logger.error("Patient Not Found for given Id " + patientId);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}
		Optional<Visit> patientVisitAdmitOptional = patientVisitRepository
				.findById(patientProblem.getLastVisit());
		if (patientVisitAdmitOptional.isPresent()) {
//			String LastVisit = patientVisitAdmitOptional.get().getVisitStartDate();
			patientProblem.setLastVisit(patientProblem.getLastVisit());
		} else {
			logger.error("Patient visit with given patientVisitId not found");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0045"));
		}

		PatientProblem problem = service.saveProblem(patientProblem);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<PatientProblem> signInResponse = new DataResponse<>(verifyResponse, problem);
		return ResponseEntity.ok().body(signInResponse);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProblem(@PathVariable String id,
			@RequestBody @Validated PatientProblem patientProblem) {

		try {
			patientProblem.setId(id);
			PatientProblem problemUpdate = service.updateProblem(id, patientProblem);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<PatientProblem> signInResponse = new DataResponse<>(verifyResponse, problemUpdate);
			return ResponseEntity.ok().body(signInResponse);
		} catch (PageNotFound e) {
			logger.error("Problem Id not found");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0096"));
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllProblems() {
		List<PatientProblem> problem = service.getAllProblem();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<List<PatientProblem>> signInResponse = new DataResponse<>(verifyResponse, problem);
		return ResponseEntity.ok().body(signInResponse);
	}

	@GetMapping("getByPatientId/{patientId}")
	public ResponseEntity<?> getById(@PathVariable String patientId) {
		List<PatientProblem> problems = repository.findByPatientId(patientId);
		if (!problems.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<List<PatientProblem>> signInResponse = new DataResponse<>(verifyResponse, problems);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("No records found for the given Patient");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
		}
	}

	@GetMapping("getByPatientIdAndId/{patientId}/{id}")
	public ResponseEntity<?> getByIdApid(@PathVariable String patientId, @PathVariable String id) {
		Optional<PatientProblem> ProblemOptional = repository.findByPatientIdAndId(patientId, id);
		if (ProblemOptional.isPresent()) {
			PatientProblem problem = ProblemOptional.get();
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<PatientProblem> signInResponse = new DataResponse<>(verifyResponse, problem);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("No records found for given Patient And Problem Id");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0080"));
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProblemById(@PathVariable String id) {
		service.deleteById(id);
		VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0209");
		return ResponseEntity.ok().body(verifyResponse);
	}

}
