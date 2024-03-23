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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.AdmitPatient;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.Patient.basicDetails.Name;
import com.MHC.Project.Model.Suicide;
import com.MHC.Project.Model.Suicide.scoring;
import com.MHC.Project.Repository.AdmitRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.SuicideRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/suicide")
@SecurityRequirement(name = "mettlerHealth")
public class SuicideController {

	@Autowired
	SuicideRepository repository;

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AdmitRepository admitRepository;

	@PostMapping("/register")
	public ResponseEntity<?> saveSuicideDetails(@RequestBody Suicide suicide) {

		String uid = PatientService.generateUID();
		
		Optional<Patient> patientOptional = patientRepository.findById(suicide.getPid());
		if (patientOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0084"));
		}
			List<AdmitPatient> admitList = admitRepository.findByPid(suicide.getPid());
			if (patientOptional.isPresent()) {
				Patient patient = patientOptional.get();

				String ssn = patient.getBasicDetails().get(0).getSsn();
				String mrn = patient.getBasicDetails().get(0).getMrNumber();

				Name name = patient.getBasicDetails().get(0).getName().get(0);
				String givenName = name.getGiven();
				String familyName = name.getFamily();
				
				 scoring scoring  = suicide.getScoring();
				 String dateAssessed = scoring.getDate();
				
				for (AdmitPatient admit : admitList) {
					String attendingPhysician = admit.getAttendingPhysician();

					suicide.setPatientName(givenName + familyName);
					suicide.setSsNumber(ssn);
					suicide.setMrNumber(mrn);
					suicide.setDateAssessed(dateAssessed);
					suicide.setStaffName(attendingPhysician);
				}

		suicide.setId(uid);
		repository.save(suicide);
			}
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, suicide);
			return ResponseEntity.ok().body(dataResponse);
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllSuicideDetails() {

		List<Suicide> suicideList = repository.findAll();
		
//		for (Suicide list : suicideList) {
//			Optional<Patient> patientOptional = patientRepository.findById(list.getPid());
//			List<AdmitPatient> admitList = admitRepository.findByPid(list.getPid());
//			if (patientOptional.isPresent()) {
//				Patient patient = patientOptional.get();
//
//				String ssn = patient.getBasicDetails().get(0).getSsn();
//				String mrn = patient.getBasicDetails().get(0).getMrNumber();
//
//				Name name = patient.getBasicDetails().get(0).getName().get(0);
//				String givenName = name.getGiven();
//				String familyName = name.getFamily();
//				
//				 scoring scoring  = list.getScoring();
//				 String dateAssessed = scoring.getDate();
//				
//				for (AdmitPatient admit : admitList) {
//					String attendingPhysician = admit.getAttendingPhysician();
//
//					list.setPatientName(givenName + familyName);
//					list.setSsNumber(ssn);
//					list.setMrNumber(mrn);
//					list.setDateAssessed(dateAssessed);
//					list.setStaffName(attendingPhysician);
//				}
//			}
//		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, suicideList);
		return ResponseEntity.ok().body(dataResponse);
	}

	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getSuicideDetailsById(@PathVariable String id) {

		Optional<Suicide> suicideOptional = repository.findById(id);
//		if (suicideOptional.isPresent()) {
//			Suicide suicideClass = suicideOptional.get();
//			Optional<Patient> patientOptional = patientRepository.findById(suicideClass.getPid());
//			List<AdmitPatient> admitList = admitRepository.findByPid(suicideClass.getPid());
//			if (patientOptional.isPresent()) {
//				Patient patient = patientOptional.get();
//
//				String ssn = patient.getBasicDetails().get(0).getSsn();
//				String mrn = patient.getBasicDetails().get(0).getMrNumber();
//
//				Name name = patient.getBasicDetails().get(0).getName().get(0);
//				String givenName = name.getGiven();
//				String familyName = name.getFamily();
//				
//				 scoring scoring  = suicideClass.getScoring();
//				 String dateAssessed = scoring.getDate();
//				
//				for (AdmitPatient admit : admitList) {
//					String attendingPhysician = admit.getAttendingPhysician();
//
//					suicideClass.setPatientName(givenName + familyName);
//					suicideClass.setSsNumber(ssn);
//					suicideClass.setMrNumber(mrn);
//					suicideClass.setDateAssessed(dateAssessed);
//					suicideClass.setStaffName(attendingPhysician);
//				}
//			}

			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, suicideOptional);
			return ResponseEntity.ok().body(dataResponse);
		}
//		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
//	}

	@GetMapping("/ByPid/{pid}")
	public ResponseEntity<?> getSuicideDetailsByPid(@PathVariable String pid) {

		List<Suicide> suicideList = repository.findByPid(pid);
//		for (Suicide list : suicideList) {
//			Optional<Patient> patientOptional = patientRepository.findById(list.getPid());
//			List<AdmitPatient> admitList = admitRepository.findByPid(list.getPid());
//			if (patientOptional.isPresent()) {
//				Patient patient = patientOptional.get();
//
//				String ssn = patient.getBasicDetails().get(0).getSsn();
//				String mrn = patient.getBasicDetails().get(0).getMrNumber();
//
//				Name name = patient.getBasicDetails().get(0).getName().get(0);
//				String givenName = name.getGiven();
//				String familyName = name.getFamily();
//				
//				 scoring scoring  = list.getScoring();
//				 String dateAssessed = scoring.getDate();
//				
//				for (AdmitPatient admit : admitList) {
//					String attendingPhysician = admit.getAttendingPhysician();
//
//					list.setPatientName(givenName + familyName);
//					list.setSsNumber(ssn);
//					list.setMrNumber(mrn);
//					list.setDateAssessed(dateAssessed);
//					list.setStaffName(attendingPhysician);
//				}
//			}
//		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, suicideList);
		return ResponseEntity.ok().body(dataResponse);
	}

	
	@GetMapping("/BySuicideRisk/{suicideRisk}")
	public ResponseEntity<?> getSuicideDetailsByRisk(@PathVariable String suicideRisk) {

		List<Suicide> suicideList = repository.findByScoringSuicideRisk(suicideRisk);
//		for (Suicide list : suicideList) {
//			Optional<Patient> patientOptional = patientRepository.findById(list.getPid());
//			List<AdmitPatient> admitList = admitRepository.findByPid(list.getPid());
//			if (patientOptional.isPresent()) {
//				Patient patient = patientOptional.get();
//
//				String ssn = patient.getBasicDetails().get(0).getSsn();
//				String mrn = patient.getBasicDetails().get(0).getMrNumber();
//
//				Name name = patient.getBasicDetails().get(0).getName().get(0);
//				String givenName = name.getGiven();
//				String familyName = name.getFamily();
//				
//				 scoring scoring  = list.getScoring();
//				 String dateAssessed = scoring.getDate();
//				
//				for (AdmitPatient admit : admitList) {
//					String attendingPhysician = admit.getAttendingPhysician();
//
//					list.setPatientName(givenName + familyName);
//					list.setSsNumber(ssn);
//					list.setMrNumber(mrn);
//					list.setDateAssessed(dateAssessed);
//					list.setStaffName(attendingPhysician);
//				}
//			}
//		}

		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, suicideList);
		return ResponseEntity.ok().body(dataResponse);

	}
	
	@GetMapping("/ByPidAndLastvisit/{pid}/{visitId}")
	public ResponseEntity<?> getSuicideDetailsByPidAndVisitId(@PathVariable String pid, 
															  @PathVariable String visitId) {
		
		List<Suicide> suicideList = repository.findByPidAndLastVisit(pid,visitId);
//		if (!suicideList.isEmpty()) {
//			for (Suicide list : suicideList) {
//				Optional<Patient> patientOptional = patientRepository.findById(list.getPid());
//				List<AdmitPatient> admitList = admitRepository.findByPid(list.getPid());
//				if (patientOptional.isPresent()) {
//					Patient patient = patientOptional.get();
//
//					String ssn = patient.getBasicDetails().get(0).getSsn();
//					String mrn = patient.getBasicDetails().get(0).getMrNumber();
//
//					Name name = patient.getBasicDetails().get(0).getName().get(0);
//					String givenName = name.getGiven();
//					String familyName = name.getFamily();
//					
//					 scoring scoring  = list.getScoring();
//					 String dateAssessed = scoring.getDate();
//					
//					for (AdmitPatient admit : admitList) {
//						String attendingPhysician = admit.getAttendingPhysician();
//
//						list.setPatientName(givenName + familyName);
//						list.setSsNumber(ssn);
//						list.setMrNumber(mrn);
//						list.setDateAssessed(dateAssessed);
//						list.setStaffName(attendingPhysician);
//					}
//				}
//			}
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, suicideList);
			return ResponseEntity.ok().body(dataResponse);
		}
//		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
//	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteSuicideDetails(@PathVariable String id) {
		
		Optional<Suicide> suicideoptional = repository.findById(id);
		if (suicideoptional.isPresent()) {
			Suicide suicideClass = suicideoptional.get();
			repository.delete(suicideClass);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
