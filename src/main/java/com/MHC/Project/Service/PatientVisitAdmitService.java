package com.MHC.Project.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.PatientVisitAdmit;
import com.MHC.Project.Model.PatientVisitAdmit.Visit.Admit;
import com.MHC.Project.Model.PatientVisitAdmit.Visit.Preadmit;
import com.MHC.Project.Model.PatientVisitAdmit.resource;
import com.MHC.Project.Repository.PatientVisitAdmitRepository;

@Service
public class PatientVisitAdmitService {

	@Autowired
	private PatientVisitAdmitRepository repository;

	public PatientVisitAdmit savePatientVisit(PatientVisitAdmit patientvisitadmitRequest) {

		String uid =  PatientService.generateUID(); // Generate UID
		resource resource = new resource();
		resource.setFullUrl("urn:uuid:" + uid);
		resource.setResourceType("Encounter");
		
		PatientVisitAdmit patientvisit = PatientVisitAdmit.build(uid, patientvisitadmitRequest.getActiveFlag(),
				List.of(resource), patientvisitadmitRequest.getCoding(), patientvisitadmitRequest.getVisit(),
				patientvisitadmitRequest.getPatientid(),patientvisitadmitRequest.getCode());
		patientvisit.setId(uid); // Set the _id value as UID
		return repository.save(patientvisit);
	}

	// To get the PatientVisit All Details //
	public List<PatientVisitAdmit> getAllPatientVisit() {
		return repository.findAll();
	}

	// **********To get the PatientVisit Details Based on ID **********
	public PatientVisitAdmit getPatientVisit(String id) throws PageNotFound {
		Optional<PatientVisitAdmit> patientvisitOptional = repository.findById(id);
		if (patientvisitOptional.isPresent()) {
			return patientvisitOptional.get();
		} else {
			throw new PageNotFound("Patientvisit Not Found With the Given Id: " + id);
		}
	}

	// **********To updatePatientvisit Service **********
	public PatientVisitAdmit updatePatientVisit(String id, PatientVisitAdmit patientvisitRequest)
			throws PageNotFound {
		Optional<PatientVisitAdmit> patientvisitOptional = Optional.ofNullable(repository.findById(id).get());
		if (patientvisitOptional.isPresent()) {
			PatientVisitAdmit patientvisit = patientvisitOptional.get();
			patientvisit.update(patientvisitRequest);
			return repository.save(patientvisit);
		} else {
			throw new PageNotFound("PatientVisit not found with id: " + id);
		}
	}

	// **********To updatePatientVisitEndDate Service**********
	public PatientVisitAdmit updatePatientVisitEnddate(String id) throws PageNotFound {
		Optional<PatientVisitAdmit> patientvisitOptional = Optional.ofNullable(repository.findById(id).get());
		if (patientvisitOptional.isPresent()) {
			PatientVisitAdmit patientvisit = patientvisitOptional.get();
			List<PatientVisitAdmit.Visit> visits = patientvisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				if (visit != null) {
					visit.setVisitEndDate(localDateTime()); // setVisitEndDate
				}
			}
			repository.save(patientvisit);
			return patientvisit;
		} else {
			throw new PageNotFound("PatientVisit not found with id: " + id);
		}
	}

	//********** To updatePatientactivityEndDate Service**********
	public PatientVisitAdmit updatePatientactivityEnddate(String id) throws PageNotFound {
		Optional<PatientVisitAdmit> patientvisitOptional = Optional.ofNullable(repository.findById(id).get());
		if (patientvisitOptional.isPresent()) {
			PatientVisitAdmit patientvisit = patientvisitOptional.get();
			List<PatientVisitAdmit.Visit> visits = patientvisit.getVisit();
			if (visits != null && !visits.isEmpty()) {
				PatientVisitAdmit.Visit visit = visits.get(0);
				if (visit != null) {
					visit.setActivityEndDate(localDateTime()); // setActivityEndDate
				}
			}
			repository.save(patientvisit);
			return patientvisit;
		} else {
			throw new PageNotFound("PatientVisit not found with id: " + id);
		}
	}
	
//	//******** To preadmit the patient Expectedadmitdatetime ************
//    public PatientVisitAdmit Expectedadmitdatetime(String id) throws PageNotFound {
//		Optional<PatientVisitAdmit> patientvisitOptional = repository.findById(id);
//		if (patientvisitOptional.isPresent()) {
//			PatientVisitAdmit patientvisit = patientvisitOptional.get();
//			List<PatientVisitAdmit.Visit> visits = patientvisit.getVisit();
//			if (visits != null && !visits.isEmpty()) {
//				 PatientVisitAdmit.Visit visit = visits.get(0);
//		            List<Preadmit> preadmits = visit.getPreadmit();
//		              if (preadmits!= null && !preadmits.isEmpty()) {
//		            	  Preadmit preadmit = preadmits.get(0);
//		            	  preadmit.setStatus("1");                              //preadmit the status active
//		            	  preadmit.setExpectedadmitDateTime(localDateTime());  // setexpectedadmitDate
//				}
//			}
//			patientvisit.setCode("1");                                        //setPreadmit  
//			repository.save(patientvisit);
//			return patientvisit;
//		} else {
//			throw new PageNotFound("patientvisit not found with id: " + id);
//		}
//	 }
//   
//   //********To preadmit the patient Expecteddischdatetime**************
//   public PatientVisitAdmit Expecteddischdatetime(String id) throws PageNotFound {
//		Optional<PatientVisitAdmit> patientvisitOptional = repository.findById(id);
//		if (patientvisitOptional.isPresent()) {
//			PatientVisitAdmit patientvisit = patientvisitOptional.get();                                     
//			List<PatientVisitAdmit.Visit> visits = patientvisit.getVisit();
//			if (visits != null && !visits.isEmpty()) {
//				 PatientVisitAdmit.Visit visit = visits.get(0);
//		            List<Preadmit> preadmits = visit.getPreadmit();
//		              if (preadmits!= null && !preadmits.isEmpty()) {
//		            	  Preadmit preadmit = preadmits.get(0);
//		            	  preadmit.setStatus("0");                              //preadmit the status
//		            	  preadmit.setExpecteddischDateTime(localDateTime());  // setexpecteddischDate
//				}
//			}
//			patientvisit.setCode("1");                                        //setPreadmit  
//			repository.save(patientvisit);
//			return patientvisit;
//		} else {
//			throw new PageNotFound("patientvisit not found with id: " + id);
//		}
//	 }
//
//	// **********To set the patient checkin  **********
//	public PatientVisitAdmit checkIn(String id) throws PageNotFound {
//		Optional<PatientVisitAdmit> patientvisitOptional = repository.findById(id);
//		if (patientvisitOptional.isPresent()) {
//			PatientVisitAdmit patientvisit = patientvisitOptional.get();
//			patientvisit.setActiveFlag("1"); // Set active
//		    List<PatientVisitAdmit.Visit> visits = patientvisit.getVisit();
//			if (visits != null && !visits.isEmpty()) {
//				PatientVisitAdmit.Visit visit = visits.get(0);
//			    List<Admit> admits = visit.getAdmit();
//				if (admits != null && !admits.isEmpty()) {
//					Admit admit = admits.get(0);
//					admit.setAdmitDate(localDateTime()); // setadmitDate
//				}
//			}
//			patientvisit.setCode("2");                    //admit
//			repository.save(patientvisit);
//			return patientvisit;
//		} else {
//			throw new PageNotFound("patientvisit not found with id: " + id);
//		}
//	}
//
//	// **********To set the  patient checkout  **********
//	public PatientVisitAdmit checkOut(String id) throws PageNotFound {
//		Optional<PatientVisitAdmit> patientvisitOptional = repository.findById(id);
//		if (patientvisitOptional.isPresent()) {
//			PatientVisitAdmit patientvisit = patientvisitOptional.get();
//			patientvisit.setActiveFlag("0"); // Set Inactive
//			List<PatientVisitAdmit.Visit> visits = patientvisit.getVisit();
//			if (visits != null && !visits.isEmpty()) {
//				PatientVisitAdmit.Visit visit = visits.get(0);
//				List<Admit> admits = visit.getAdmit();
//				if (admits != null && !admits.isEmpty()) {
//					Admit admit = admits.get(0);
//					admit.setDischDate(localDateTime()); // SetdischDate
//				}
//			}
//			patientvisit.setCode("2");       //setAdmit
//			repository.save(patientvisit);
//			return patientvisit;
//		} else {
//			throw new PageNotFound("patientvisit not found with id: " + id);
//		}
//	}

	public String localDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String visitEndDate = now.format(formatter);
		return visitEndDate;
	}
}