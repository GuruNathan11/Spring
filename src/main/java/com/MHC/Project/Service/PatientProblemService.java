//package com.MHC.Project.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.MHC.Project.Error.PageNotFound;
//import com.MHC.Project.Model.PatientProblem;
//import com.MHC.Project.Repository.PatientProblemRepository;
//
//@Service
//public class PatientProblemService {
//	
//	@Autowired
//	PatientProblemRepository repository;
//
//	public PatientProblem saveProblem(PatientProblem patientProblem){
//		String uid=generateUID();
//		PatientProblem problems = patientProblem.build(uid, patientProblem.getPatientId(), patientProblem.getProblemDescription(),patientProblem.getDateOfOnset(), patientProblem.getProblemStatus(),
//				patientProblem.getRelevantMedicalHistory(),patientProblem.getMedicationHistory(),patientProblem.getFamilyHistoryOfReleatedProblem(),
//				patientProblem.getSeverityOfProblem(), patientProblem.getLocationOfProblem(), patientProblem.getAssociatedSymptoms(), patientProblem.getTreatmentPlan(),
//				patientProblem.getPhysicianName(), patientProblem.getDateOfLastVisit(), patientProblem.getAllergyInformation(), patientProblem.getOtherRelevantMedicalCondition(), patientProblem.getDiagnosticTestResults(), patientProblem.getReferralInformation()); 
//		
//		problems.setId(uid);
//		return repository.save(problems);
//	}
//	public PatientProblem updateProblem(String id,PatientProblem patientProblem) throws PageNotFound{
//		
//		PatientProblem problems = patientProblem.build(patientProblem.getId(), patientProblem.getPatientId(), patientProblem.getProblemDescription(),patientProblem.getDateOfOnset(), patientProblem.getProblemStatus(),
//				patientProblem.getRelevantMedicalHistory(), patientProblem.getMedicationHistory(),patientProblem.getFamilyHistoryOfReleatedProblem(),
//				patientProblem.getSeverityOfProblem(), patientProblem.getLocationOfProblem(), patientProblem.getAssociatedSymptoms(), patientProblem.getTreatmentPlan(),
//				patientProblem.getPhysicianName(), patientProblem.getDateOfLastVisit(), patientProblem.getAllergyInformation(), patientProblem.getOtherRelevantMedicalCondition(), patientProblem.getDiagnosticTestResults(), patientProblem.getReferralInformation()); 
//		
//		Optional<PatientProblem> problemOptional= repository.findById(id);
//		if(problemOptional.isPresent())
//		{
//			PatientProblem problemUpdate=problemOptional.get();
//			problemUpdate.update(problems);
//			return repository.save(problemUpdate);
//		}
//		else
//            throw new PageNotFound("Problem not Found ");
//	}
//	public List<PatientProblem> getAllProblem() {
//		return repository.findAll();
//	}
//
//	public PatientProblem getProblemById(String id) {
//		
//		return repository.findById(id).get();
//	}
//
//	public String deleteById(String id) {
//		boolean delete=false;
//		if(repository.existsById(id))
//		{
//			repository.deleteById(id);
//			delete=true;
//		}
//		return "Problems of id: "+id+" Delete Status: "+delete;
//	}
//	public static String getDateTime(){
//		 LocalDateTime now= LocalDateTime.now();
//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//		 String dateTime = now.format(formatter);
//		 return dateTime;
//	}
//	public static String generateUID() {
//		// Generate a random alphanumeric ID with 10 digits
//		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < 10; i++) {
//			int index = (int) (Math.random() * characters.length());
//			sb.append(characters.charAt(index));
//		}
//		return sb.toString();
//	}
//
//}




package com.MHC.Project.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.PatientProblem;
import com.MHC.Project.Repository.PatientProblemRepository;

@Service
public class PatientProblemService {
	
	@Autowired
	PatientProblemRepository repository;

	public PatientProblem saveProblem(PatientProblem patientProblem){
		String uid=generateUID();
		PatientProblem problems = patientProblem.build(uid, patientProblem.getPatientId(),patientProblem.getProblemCategory(), patientProblem.getProblemDescription(),patientProblem.getDateOfOnset(),patientProblem.getLastUpdate(),patientProblem.getRespProvider(), patientProblem.getProblemStatus(),
				patientProblem.getInactive(),patientProblem.getService(),patientProblem.getImmediacy(),patientProblem.getClinic(),  patientProblem.getRelevantMedicalHistory(),patientProblem.getMedicationHistory(),patientProblem.getFamilyHistoryOfReleatedProblem(),
				patientProblem.getSeverityOfProblem(), patientProblem.getLocationOfProblem(), patientProblem.getAssociatedSymptoms(), patientProblem.getTreatmentFactors(),
				patientProblem.getPhysicianName(), patientProblem.getLastVisit(), patientProblem.getAllergyInformation(), patientProblem.getOtherRelevantMedicalCondition(), 
				patientProblem.getDiagnosticTestResults(), patientProblem.getReferralInformation(),patientProblem.getComments()); 
		
		problems.setId(uid);
		return repository.save(problems);
	}
	public PatientProblem updateProblem(String id,PatientProblem patientProblem) throws PageNotFound{
		
		PatientProblem problems = patientProblem.build(patientProblem.getId(), patientProblem.getPatientId(),patientProblem.getProblemCategory(), patientProblem.getProblemDescription(),patientProblem.getDateOfOnset(),patientProblem.getLastUpdate(),patientProblem.getRespProvider(), patientProblem.getProblemStatus(),
				patientProblem.getInactive(),patientProblem.getService(),patientProblem.getImmediacy(),patientProblem.getClinic(), patientProblem.getRelevantMedicalHistory(), patientProblem.getMedicationHistory(),patientProblem.getFamilyHistoryOfReleatedProblem(),
				patientProblem.getSeverityOfProblem(), patientProblem.getLocationOfProblem(), patientProblem.getAssociatedSymptoms(), patientProblem.getTreatmentFactors(),
				patientProblem.getPhysicianName(), patientProblem.getLastVisit(), patientProblem.getAllergyInformation(), patientProblem.getOtherRelevantMedicalCondition(), 
				patientProblem.getDiagnosticTestResults(), patientProblem.getReferralInformation(),patientProblem.getComments()); 
		
		Optional<PatientProblem> problemOptional= repository.findById(id);
		if(problemOptional.isPresent())
		{
			PatientProblem problemUpdate=problemOptional.get();
			problemUpdate.update(problems);
			problemUpdate.setUpdatedDate(getDateTime());
			return repository.save(problemUpdate);
		}
		else
            throw new PageNotFound("Problem not Found ");
	}
	public List<PatientProblem> getAllProblem() {
		return repository.findAll();
	}

	public PatientProblem getProblemById(String id) {
		
		return repository.findById(id).get();
	}

	public String deleteById(String id) {
		boolean delete=false;
		if(repository.existsById(id))
		{
			repository.deleteById(id);
			delete=true;
		}
		return "Problems of id: "+id+" Delete Status: "+delete;
	}
	public static String getDateTime(){
		 LocalDateTime now= LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		 String dateTime = now.format(formatter);
		 return dateTime;
	}
	public static String generateUID() {
		// Generate a random alphanumeric ID with 10 digits
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

}

