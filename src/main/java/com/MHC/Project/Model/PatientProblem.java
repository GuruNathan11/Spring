//package com.MHC.Project.Model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document(collection="Problem")
//public class PatientProblem {
//	
//	@Id
//	private String id;
//	private String patientId;
//	private String problemDescription;
//	private String dateOfOnset;
//	private String problemStatus;
//	private String relevantMedicalHistory;
//	private String medicationHistory;
//	private String familyHistoryOfReleatedProblem;
//	private String severityOfProblem;
//	private String locationOfProblem;
//	private String associatedSymptoms;
//	private String treatmentPlan;
//	private String physicianName;
//	private String dateOfLastVisit;
//	private String allergyInformation;
//	private String otherRelevantMedicalCondition;
//	private String diagnosticTestResults;
//	private String referralInformation;
//	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getPatientId() {
//		return patientId;
//	}
//	public void setPatientId(String patientId) {
//		this.patientId = patientId;
//	}
//	public String getProblemDescription() {
//		return problemDescription;
//	}
//	public void setProblemDescription(String problemDescription) {
//		this.problemDescription = problemDescription;
//	}
//	public String getDateOfOnset() {
//		return dateOfOnset;
//	}
//	public void setDateOfOnset(String dateOfOnset) {
//		this.dateOfOnset = dateOfOnset;
//	}
//	public String getProblemStatus() {
//		return problemStatus;
//	}
//	public void setProblemStatus(String problemStatus) {
//		this.problemStatus = problemStatus;
//	}
//	public String getRelevantMedicalHistory() {
//	    return relevantMedicalHistory;
//	}
//	public void setRelevantMedicalHistory(String relevantMedicalHistory) {
//	    this.relevantMedicalHistory = relevantMedicalHistory;
//	}
//	public String getMedicationHistory() {
//		return medicationHistory;
//	}
//	public void setMedicationHistory(String medicationHistory) {
//		this.medicationHistory = medicationHistory;
//	}
//	public String getFamilyHistoryOfReleatedProblem() {
//		return familyHistoryOfReleatedProblem;
//	}
//	public void setFamilyHistoryOfReleatedProblem(String familyHistoryOfReleatedProblem) {
//		this.familyHistoryOfReleatedProblem = familyHistoryOfReleatedProblem;
//	}
//	public String getSeverityOfProblem() {
//		return severityOfProblem;
//	}
//	public void setSeverityOfProblem(String severityOfProblem) {
//		this.severityOfProblem = severityOfProblem;
//	}
//	public String getLocationOfProblem() {
//		return locationOfProblem;
//	}
//	public void setLocationOfProblem(String locationOfProblem) {
//		this.locationOfProblem = locationOfProblem;
//	}
//	public String getAssociatedSymptoms() {
//		return associatedSymptoms;
//	}
//	public void setAssociatedSymptoms(String associatedSymptoms) {
//		this.associatedSymptoms = associatedSymptoms;
//	}
//	public String getTreatmentPlan() {
//		return treatmentPlan;
//	}
//	public void setTreatmentPlan(String treatmentPlan) {
//		this.treatmentPlan = treatmentPlan;
//	}
//	public String getPhysicianName() {
//		return physicianName;
//	}
//	public void setPhysicianName(String physicianName) {
//		this.physicianName = physicianName;
//	}
//	public String getDateOfLastVisit() {
//		return dateOfLastVisit;
//	}
//	public void setDateOfLastVisit(String dateOfLastVisit) {
//		this.dateOfLastVisit = dateOfLastVisit;
//	}
//	public String getAllergyInformation() {
//		return allergyInformation;
//	}
//	public void setAllergyInformation(String allergyInformation) {
//		this.allergyInformation = allergyInformation;
//	}
//	public String getOtherRelevantMedicalCondition() {
//		return otherRelevantMedicalCondition;
//	}
//	public void setOtherRelevantMedicalCondition(String otherRelevantMedicalCondition) {
//		this.otherRelevantMedicalCondition = otherRelevantMedicalCondition;
//	}
//	public String getDiagnosticTestResults() {
//		return diagnosticTestResults;
//	}
//	public void setDiagnosticTestResults(String diagnosticTestResults) {
//		this.diagnosticTestResults = diagnosticTestResults;
//	}
//	public String getReferralInformation() {
//		return referralInformation;
//	}
//	public void setReferralInformation(String referralInformation) {
//		this.referralInformation = referralInformation;
//	}
//	public PatientProblem(String id, String patientId, String problemDescription,String dateOfOnset, String problemStatus,
//			String relevantMedicalHistory,String medicationHistory, String familyHistoryOfReleatedProblem, String severityOfProblem,
//			String locationOfProblem, String associatedSymptoms, String treatmentPlan, String physicianName,String dateOfLastVisit,
//			String allergyInformation, String otherRelevantMedicalCondition, String diagnosticTestResults,
//			String referralInformation) {
//		super();
//		this.id = id;
//		this.patientId = patientId;
//		this.problemDescription = problemDescription;
//		this.dateOfOnset=dateOfOnset;
//		this.problemStatus = problemStatus;
//		this.relevantMedicalHistory = relevantMedicalHistory;
//		this.medicationHistory = medicationHistory;
//		this.familyHistoryOfReleatedProblem = familyHistoryOfReleatedProblem;
//		this.severityOfProblem = severityOfProblem;
//		this.locationOfProblem = locationOfProblem;
//		this.associatedSymptoms = associatedSymptoms;
//		this.treatmentPlan = treatmentPlan;
//		this.physicianName = physicianName;
//		this.dateOfLastVisit = dateOfLastVisit;
//		this.allergyInformation = allergyInformation;
//		this.otherRelevantMedicalCondition = otherRelevantMedicalCondition;
//		this.diagnosticTestResults = diagnosticTestResults;
//		this.referralInformation = referralInformation;
//	}
//	public PatientProblem build(String id, String patientId, String problemDescription,String dateOfOnset, String problemStatus,
//			 String relevantMedicalHistory,String medicationHistory, String familyHistoryOfReleatedProblem,
//			String severityOfProblem, String locationOfProblem, String associatedSymptoms, String treatmentPlan,
//			String physicianName, String dateOfLastVisit, String allergyInformation,String otherRelevantMedicalCondition, String diagnosticTestResults, String referralInformation) {
//		
//		return new PatientProblem(id,patientId,problemDescription,dateOfOnset,problemStatus,relevantMedicalHistory, medicationHistory,familyHistoryOfReleatedProblem,severityOfProblem,
//				locationOfProblem,associatedSymptoms,treatmentPlan,physicianName,dateOfLastVisit,allergyInformation,otherRelevantMedicalCondition,diagnosticTestResults,referralInformation);
//	}
//	public void update(PatientProblem problem) {
//		this.setPatientId(problem.getPatientId());
//		this.setProblemDescription(problem.getProblemDescription());
//		this.setDateOfOnset(problem.getDateOfOnset());
//		this.setProblemStatus(problem.getProblemStatus());
//		this.setRelevantMedicalHistory(problem.getRelevantMedicalHistory());
//		this.setMedicationHistory(problem.getMedicationHistory());
//		this.setFamilyHistoryOfReleatedProblem(problem.getFamilyHistoryOfReleatedProblem());
//		this.setSeverityOfProblem(problem.getSeverityOfProblem());
//		this.setLocationOfProblem(problem.getLocationOfProblem());
//		this.setAssociatedSymptoms(problem.getAssociatedSymptoms());
//		this.setTreatmentPlan(problem.getTreatmentPlan());
//		this.setPhysicianName(problem.getPhysicianName());
//		this.setDateOfLastVisit(problem.getDateOfLastVisit());
//		this.setAllergyInformation(problem.getAllergyInformation());
//		this.setOtherRelevantMedicalCondition(problem.getOtherRelevantMedicalCondition());
//		this.setDiagnosticTestResults(problem.getDiagnosticTestResults());
//		this.setReferralInformation(problem.getReferralInformation());
//	}
//
//
//}



package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Problem")
public class PatientProblem {
	
	@Id
	private String id;
	private String patientId;
	private String problemCategory;
	private String problemDescription;
	private String dateOfOnset;
	private String updatedDate;
	private String lastUpdate;
	private String respProvider;
	private String problemStatus;
	private boolean inactive;
	private String service;
	private String immediacy;
	private String clinic;
	private String relevantMedicalHistory;
	private String medicationHistory;
	private String familyHistoryOfReleatedProblem;
	private String severityOfProblem;
	private String locationOfProblem;
	private String associatedSymptoms;
	private String[] treatmentFactors;
	private String physicianName;
	private String LastVisit;
	private String allergyInformation;
	private String otherRelevantMedicalCondition;
	private String diagnosticTestResults;
	private String referralInformation;
	private String comments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public String getDateOfOnset() {
		return dateOfOnset;
	}
	public void setDateOfOnset(String dateOfOnset) {
		this.dateOfOnset = dateOfOnset;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getRespProvider() {
		return respProvider;
	}
	public void setRespProvider(String respProvider) {
		this.respProvider = respProvider;
	}
	public String getProblemStatus() {
		return problemStatus;
	}
	public void setProblemStatus(String problemStatus) {
		this.problemStatus = problemStatus;
	}
	public boolean getInactive() {
		return inactive;
	}
	public void setInactive(boolean status) {
		this.inactive = status;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getImmediacy() {
		return immediacy;
	}
	public void setImmediacy(String immediacy) {
		this.immediacy = immediacy;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRelevantMedicalHistory() {
	    return relevantMedicalHistory;
	}
	public void setRelevantMedicalHistory(String relevantMedicalHistory) {
	    this.relevantMedicalHistory = relevantMedicalHistory;
	}
	public String getMedicationHistory() {
		return medicationHistory;
	}
	public void setMedicationHistory(String medicationHistory) {
		this.medicationHistory = medicationHistory;
	}
	public String getFamilyHistoryOfReleatedProblem() {
		return familyHistoryOfReleatedProblem;
	}
	public void setFamilyHistoryOfReleatedProblem(String familyHistoryOfReleatedProblem) {
		this.familyHistoryOfReleatedProblem = familyHistoryOfReleatedProblem;
	}
	public String getSeverityOfProblem() {
		return severityOfProblem;
	}
	public void setSeverityOfProblem(String severityOfProblem) {
		this.severityOfProblem = severityOfProblem;
	}
	public String getLocationOfProblem() {
		return locationOfProblem;
	}
	public void setLocationOfProblem(String locationOfProblem) {
		this.locationOfProblem = locationOfProblem;
	}
	public String getAssociatedSymptoms() {
		return associatedSymptoms;
	}
	public void setAssociatedSymptoms(String associatedSymptoms) {
		this.associatedSymptoms = associatedSymptoms;
	}
	public String[] getTreatmentFactors() {
		return treatmentFactors;
	}
	public void setTreatmentFactors(String[] treatmentFactors) {
		this.treatmentFactors = treatmentFactors;
	}
	public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	public String getLastVisit() {
		return LastVisit;
	}
	public void setLastVisit(String LastVisit) {
		this.LastVisit = LastVisit;
	}
	public String getAllergyInformation() {
		return allergyInformation;
	}
	public void setAllergyInformation(String allergyInformation) {
		this.allergyInformation = allergyInformation;
	}
	public String getOtherRelevantMedicalCondition() {
		return otherRelevantMedicalCondition;
	}
	public void setOtherRelevantMedicalCondition(String otherRelevantMedicalCondition) {
		this.otherRelevantMedicalCondition = otherRelevantMedicalCondition;
	}
	public String getDiagnosticTestResults() {
		return diagnosticTestResults;
	}
	public void setDiagnosticTestResults(String diagnosticTestResults) {
		this.diagnosticTestResults = diagnosticTestResults;
	}
	public String getReferralInformation() {
		return referralInformation;
	}
	public void setReferralInformation(String referralInformation) {
		this.referralInformation = referralInformation;
	}
	public PatientProblem(String id, String patientId,String problemCategory, String problemDescription,String dateOfOnset,String lastUpdate,String respProvider, String problemStatus,
			boolean inactive, String service, String immediacy, String clinic, String relevantMedicalHistory,String medicationHistory, String familyHistoryOfReleatedProblem, String severityOfProblem,
			String locationOfProblem, String associatedSymptoms, String[] treatmentFactors, String physicianName,String LastVisit,
			String allergyInformation, String otherRelevantMedicalCondition, String diagnosticTestResults,
			String referralInformation,String comments) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.problemCategory = problemCategory;
		this.problemDescription = problemDescription;
		this.dateOfOnset=dateOfOnset;
		this.lastUpdate=lastUpdate;
		this.respProvider=respProvider;
		this.problemStatus = problemStatus;
		this.inactive=inactive;
		this.service=service;
		this.immediacy=immediacy;
		this.clinic=clinic;
		this.relevantMedicalHistory = relevantMedicalHistory;
		this.medicationHistory = medicationHistory;
		this.familyHistoryOfReleatedProblem = familyHistoryOfReleatedProblem;
		this.severityOfProblem = severityOfProblem;
		this.locationOfProblem = locationOfProblem;
		this.associatedSymptoms = associatedSymptoms;
		this.treatmentFactors = treatmentFactors;
		this.physicianName = physicianName;
		this.LastVisit = LastVisit;
		this.allergyInformation = allergyInformation;
		this.otherRelevantMedicalCondition = otherRelevantMedicalCondition;
		this.diagnosticTestResults = diagnosticTestResults;
		this.referralInformation = referralInformation;
		this.comments=comments;
	}
	public PatientProblem build(String id, String patientId,String problemCategory, String problemDescription,String dateOfOnset,String lastUpdate,String respProvider, String problemStatus,
			boolean inactive, String service, String immediacy, String clinic, String relevantMedicalHistory,String medicationHistory, String familyHistoryOfReleatedProblem,
			String severityOfProblem, String locationOfProblem, String associatedSymptoms, String[] treatmentFactors,
			String physicianName, String LastVisit, String allergyInformation,String otherRelevantMedicalCondition, String diagnosticTestResults, String referralInformation,String comments) {
		
		return new PatientProblem(id,patientId,problemCategory,problemDescription,dateOfOnset,lastUpdate,respProvider, problemStatus,inactive,service,immediacy,clinic, relevantMedicalHistory, medicationHistory,familyHistoryOfReleatedProblem,severityOfProblem,
				locationOfProblem,associatedSymptoms,treatmentFactors,physicianName,LastVisit,allergyInformation,otherRelevantMedicalCondition,diagnosticTestResults,referralInformation,comments);
	}
	public void update(PatientProblem problem) {
		this.setPatientId(problem.getPatientId());
		this.setProblemCategory(problem.getProblemCategory());
		this.setProblemDescription(problem.getProblemDescription());
		this.setDateOfOnset(problem.getDateOfOnset());
		this.setUpdatedDate(problem.getUpdatedDate());
		this.setLastUpdate(problem.getLastUpdate());
		this.setRespProvider(problem.getRespProvider());
		this.setProblemStatus(problem.getProblemStatus());
		this.setInactive(problem.getInactive());
		this.setService(problem.getService());
		this.setImmediacy(problem.getImmediacy());
		this.setClinic(problem.getClinic());
		this.setRelevantMedicalHistory(problem.getRelevantMedicalHistory());
		this.setMedicationHistory(problem.getMedicationHistory());
		this.setFamilyHistoryOfReleatedProblem(problem.getFamilyHistoryOfReleatedProblem());
		this.setSeverityOfProblem(problem.getSeverityOfProblem());
		this.setLocationOfProblem(problem.getLocationOfProblem());
		this.setAssociatedSymptoms(problem.getAssociatedSymptoms());
		this.setTreatmentFactors(problem.getTreatmentFactors());
		this.setPhysicianName(problem.getPhysicianName());
		this.setLastVisit(problem.getLastVisit());
		this.setAllergyInformation(problem.getAllergyInformation());
		this.setOtherRelevantMedicalCondition(problem.getOtherRelevantMedicalCondition());
		this.setDiagnosticTestResults(problem.getDiagnosticTestResults());
		this.setReferralInformation(problem.getReferralInformation());
		this.setComments(problem.getComments());
	}
	public String getProblemCategory() {
		return problemCategory;
	}
	public void setProblemCategory(String problemCategory) {
		this.problemCategory = problemCategory;
	}
}

