package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.PatientVisitAdmit;
import com.MHC.Project.Model.PatientVisitAdmit.Visit.Admit;
import com.MHC.Project.Model.PatientVisitAdmit.Visit.Preadmit;
import com.MHC.Project.Model.PatientVisitAdmit.resource;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.PatientVisitAdmitRepository;
import com.MHC.Project.Request.PatientVisitRequest;
import com.MHC.Project.Request.PreadmitRequest;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/ptVisit")
@SecurityRequirement(name = "mettlerHealth")
public class PatientVisitAdmitController {

	private static final Logger logger = LoggerFactory.getLogger(PatientVisitAdmitController.class);

	@Autowired
	private PatientVisitAdmitRepository repository;

	@Autowired
	PatientRepository patientRepository;

	// ********** To Register the PatientVisit **********
	@PostMapping("/register")
	public ResponseEntity<?> savePatientVisit(@RequestBody @Validated PatientVisitAdmit patientvisitadmitRequest) {

		String patientId = patientvisitadmitRequest.getPatientid();
		Patient patient = patientRepository.findById(patientId).orElse(null);
		
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);
		
		// Check if the patient exists
		if (patient == null) {
			logger.error("Error: Patient with the given patientId Could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}
		// check visit start date Exists
		List<PatientVisitAdmit> patientvisits = repository.findByPatientid(patientId);
		String newVisitStartDate = patientvisitadmitRequest.getVisit().get(0).getVisitStartDate();

		for (PatientVisitAdmit existingVisit : patientvisits) {
			if ("1".equals(existingVisit.getActiveFlag())) {
				String existingVisitStartDate = existingVisit.getVisit().get(0).getVisitStartDate();
				if (newVisitStartDate.equals(existingVisitStartDate)) {
					logger.error("Error: PatientVisit Date already exists");
					return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0065"));
				}
			}
		}
		String uid = PatientService.generateUID(); // Generate UID
		resource resource = new resource();
		resource.setFullUrl("urn:uuid:" + uid);
		resource.setResourceType("Encounter");
		patientvisitadmitRequest.setActiveFlag("1"); // Set active status to true
		patientvisitadmitRequest.setCode("0");
		patientvisitadmitRequest.setCreatedAt(createdAt);

		PatientVisitAdmit patientvisit1 = new PatientVisitAdmit(uid, patientvisitadmitRequest.getActiveFlag(),
				List.of(resource), patientvisitadmitRequest.getCoding(), patientvisitadmitRequest.getVisit(),
				patientvisitadmitRequest.getPatientid(), patientvisitadmitRequest.getCode());
		repository.save(patientvisit1);
		DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 280");
		DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientvisit1);
		return ResponseEntity.ok().body(signInResponse); // Return the response with the SignInResponse object
	}

	// ********** To get all the PatientVisit **********
	@GetMapping("/get_all")
	public ResponseEntity<?> getAllPatientVisit() {
		List<PatientVisitAdmit> patientVisits = repository.findAll();
		DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 281");
		DataResponse<List<PatientVisitAdmit>> signInResponse = new DataResponse<>(verifyResponse1, patientVisits);
		return ResponseEntity.ok().body(signInResponse);
	}

	// ********** To get the PatientVisit Based on Id **********
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getPatientVisit(@PathVariable String id) {
		Optional<PatientVisitAdmit> patientVisitOptional = repository.findById(id);
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit patientVisit = patientVisitOptional.get();
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 282");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientVisit);
			repository.save(patientVisit);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error: Patientvisit with given patientvisitId could not be found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// *******To get PatientVisit Based on particular Patient id*******//
	@GetMapping("/get/pid/{patientid}")
	public ResponseEntity<?> getPatientId(@PathVariable String patientid) {
		List<PatientVisitAdmit> patientvisit = repository.findByPatientid(patientid);
		if (patientvisit.isEmpty()) {
			logger.error("Error:No Active Patients found");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0055"));

		} else {
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 283");
			DataResponse<List<PatientVisitAdmit>> signInResponse = new DataResponse<>(verifyResponse1, patientvisit);
			return ResponseEntity.ok().body(signInResponse);
		}
	}

	// ********** To update the Patient visit Based on Id **********
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePatientVisit(@PathVariable String id,
			@RequestBody @Validated PatientVisitAdmit patientvisitRequest) {
		Optional<PatientVisitAdmit> patientVisitOptional = Optional.ofNullable(repository.findById(id).orElse(null));
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit updatedPatientVisit = patientVisitOptional.get();
			updatedPatientVisit.setUpdatedAt(updatedAt);
			updatedPatientVisit.update(patientvisitRequest);
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 284");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, updatedPatientVisit);
			repository.save(updatedPatientVisit);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error:Patientvisit could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// **********To update the Patient Visit End Date **********
	@PutMapping("/endDate/{id}")
	public ResponseEntity<?> updatePatientVisitEnddate(@PathVariable String id,
			@RequestBody @Validated PatientVisitAdmit patientvisitRequest) {
		Optional<PatientVisitAdmit> patientVisitOptional = Optional.ofNullable(repository.findById(id).orElse(null));
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit patientVisit = patientVisitOptional.get();
			patientvisitRequest.setActiveFlag("0");
			List<PatientVisitAdmit.Visit> visits = patientVisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				if (visit != null) {
					visit.setVisitEndDate(visit.getVisitEndDate());
				}
			}
			patientVisit.update(patientvisitRequest);
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 285");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientVisit);
			repository.save(patientvisitRequest);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error:Patientvisit could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// **********To update the Patient Activity End Date **********
	@PutMapping("/activityEndDate/{id}")
	public ResponseEntity<?> updatePatientActivityEnddate(@PathVariable String id,
			@RequestBody @Validated PatientVisitAdmit patientvisitRequest) {
		Optional<PatientVisitAdmit> patientVisitOptional = Optional.ofNullable(repository.findById(id).orElse(null));
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit patientVisit = patientVisitOptional.get();
			List<PatientVisitAdmit.Visit> visits = patientVisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				if (visit != null) {
					visit.getActivityEndDate(); // Set current date and time
				}
			}
			patientVisit.update(patientvisitRequest);
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 286");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientVisit);
			repository.save(patientvisitRequest);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error:Patientvisit could not be found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// **********To update the Patient Expected admit date **********
	@PutMapping("/Expectedarrivaldate/{id}")
	public ResponseEntity<?> expectedArrivalDateTime(@PathVariable String id,
			@RequestBody PreadmitRequest preadmitRequest) {
		Optional<PatientVisitAdmit> patientVisitOptional = repository.findById(id);
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit patientVisit = patientVisitOptional.get();
			List<PatientVisitAdmit.Visit> visits = patientVisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				List<Preadmit> preadmits = visit.getPreadmit();
				if (preadmits != null && !preadmits.isEmpty()) {
					Preadmit preadmit = preadmits.get(0);
					preadmit.setpStatus("1"); // preadmit checkin
					preadmit.setExpectedarrivalDateTime(preadmitRequest.getExpectedArrivalDateTime());
				}
			}
			patientVisit.setCode("1");
			patientVisit.update(patientVisit);
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 287");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientVisit);
			repository.save(patientVisit);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error:Patientvisit with given patientvisitId could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// **********To update the Patient Expected disch date **********
	@PutMapping("/Expecteddischdate/{id}")
	public ResponseEntity<?> Expecteddischdatetime(@PathVariable String id,
			@RequestBody PreadmitRequest preadmitRequest) {
		Optional<PatientVisitAdmit> patientVisitOptional = repository.findById(id);
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit patientVisit = patientVisitOptional.get();
			List<PatientVisitAdmit.Visit> visits = patientVisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				List<Preadmit> preadmits = visit.getPreadmit();
				if (preadmits != null && !preadmits.isEmpty()) {
					Preadmit preadmit = preadmits.get(0);
					preadmit.setpStatus("0"); // preadmitcheckout
					preadmit.setExpecteddischDateTime(preadmitRequest.getExpectedDischDateTime());
				}
			}
			patientVisit.setCode("1");
			patientVisit.update(patientVisit);
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 288");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientVisit);
			repository.save(patientVisit);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error:Patientvisit with given patientvisitId could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// ********** To Admit the Patient **********
	@PutMapping("/admit/{id}")
	public ResponseEntity<?> checkIn(@PathVariable String id,
			@RequestBody @Validated PatientVisitRequest patientvisitRequest) {
		Optional<PatientVisitAdmit> patientVisitOptional = repository.findById(id);
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit patientVisit = patientVisitOptional.get();
			List<PatientVisitAdmit.Visit> visits = patientVisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				List<Admit> admits = visit.getAdmit();
				if (admits != null && !admits.isEmpty()) {
					Admit admit = admits.get(0);
					admit.setaStatus("1"); // checkout
					admit.setAdmitDate(patientvisitRequest.getAdmitDate()); // Set current date and time
				}
			}
			patientVisit.setCode("2");
			patientVisit.update(patientVisit);
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 289");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientVisit);
			repository.save(patientVisit);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error:Patientvisit with given patientvisitId could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// ********** To Discharge the Patient **********
	@PutMapping("/discharge/{id}")
	public ResponseEntity<?> checkOut(@PathVariable String id,
			@RequestBody @Validated PatientVisitRequest patientvisitRequest) {
		Optional<PatientVisitAdmit> patientVisitOptional = repository.findById(id);
		if (patientVisitOptional.isPresent()) {
			PatientVisitAdmit patientVisit = patientVisitOptional.get();
			List<PatientVisitAdmit.Visit> visits = patientVisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				List<Admit> admits = visit.getAdmit();
				if (admits != null && !admits.isEmpty()) {
					Admit admit = admits.get(0);
					admit.setDischDate(patientvisitRequest.getDischDate()); // Set current date and time
					admit.setaStatus("0"); // set Inactive
				}
			}
			patientVisit.setCode("2"); // admit
			patientVisit.update(patientVisit);
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 290");
			DataResponse<PatientVisitAdmit> signInResponse = new DataResponse<>(verifyResponse1, patientVisit);
			repository.save(patientVisit);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			logger.error("Error:Patientvisit with given patientvisitId could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}

	// ***************To activeVisit patient******//
	@GetMapping("/get/ActiveVisit")
	public ResponseEntity<?> getActiveStaff() {
		String activeFlag = "1";
		List<PatientVisitAdmit> patientvisit = repository.findAllByActiveFlag(activeFlag);
		if (!patientvisit.isEmpty()) {
			DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 291");
			DataResponse<List<PatientVisitAdmit>> signInResponse = new DataResponse<>(verifyResponse1, patientvisit);
			return ResponseEntity.ok().body(signInResponse);
		}
		logger.error("Error: No Activevisit Could Not be found .");
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0064"));
	}

	// ****** To delete a Patient visit by ID *****//
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletepatientvisit(@PathVariable String id) {
		if (repository.existsByid(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 292"));
		} else {
			logger.error("Error:Patientvisit with given patientvisitId could not found.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0046"));
		}
	}
}