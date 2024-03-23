package com.MHC.Project.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Allergy")
public class PatientAllergy {

	@Id
	private String id;
	private String patientId;
	private String causativeAgentName;
	private String enteredDate;
	private String updatedDate;
	private String dateOfOnset;
	private String physicianName;
	private String LastVisit;
	private String allergyType;
	private String specificAllergen;
	private String treatmentPlan;
	private String otherReleventMedicalCondition;
	private String medicationHistory;
	private String[] symptoms;
	private String allergySeverity;
	private String familyHistoryOfAllergies;
	private String natureOfReaction;
	private String comments;
	public String observed;
	private boolean inactive;
	public  ObservedDetails observedDetails;

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
	public String getCausativeAgentName() {
		return causativeAgentName;
	}
	public void setCausativeAgentName(String causativeAgentName) {
		this.causativeAgentName = causativeAgentName;
	}
	public String getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(String enteredDate) {
		this.enteredDate = enteredDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getDateOfOnset() {
		return dateOfOnset;
	}
	public void setDateOfOnset(String dateOfOnset) {
		this.dateOfOnset = dateOfOnset;
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
	public String getAllergyType() {
		return allergyType;
	}
	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}
	public String getSpecificAllergen() {
		return specificAllergen;
	}
	public void setSpecificAllergen(String specificAllergen) {
		this.specificAllergen = specificAllergen;
	}
	public String getTreatmentPlan() {
		return treatmentPlan;
	}
	public void setTreatmentPlan(String treatmentPlan) {
		this.treatmentPlan = treatmentPlan;
	}
	public String[] getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
	}
	public String getOtherReleventMedicalCondition() {
		return otherReleventMedicalCondition;
	}
	public void setOtherReleventMedicalCondition(String otherReleventMedicalCondition) {
		this.otherReleventMedicalCondition = otherReleventMedicalCondition;
	}
	public String getMedicationHistory() {
		return medicationHistory;
	}
	public void setMedicationHistory(String medicationHistory) {
		this.medicationHistory = medicationHistory;
	}
	public String getAllergySeverity() {
		return allergySeverity;
	}
	public void setAllergySeverity(String allergySeverity) {
		this.allergySeverity = allergySeverity;
	}
	public String getFamilyHistoryOfAllergies() {
		
		return familyHistoryOfAllergies;
	}
	public void setFamilyHistoryOfAllergies(String familyHistoryOfAllergies) {
		this.familyHistoryOfAllergies = familyHistoryOfAllergies;
	}
	public String getNatureOfReaction() {
		return natureOfReaction;
	}
	public void setNatureOfReaction(String natureOfReaction) {
		this.natureOfReaction = natureOfReaction;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getObserved() {
		return observed;
	}
	public void setObserved(String observed) {
		this.observed = observed;
	}
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	public  ObservedDetails getObservedDetails() {
		return observedDetails;
	}
	public void setObservedDetails(ObservedDetails observedDetails) {
		this.observedDetails = observedDetails;
	}
	public PatientAllergy(String id,String patientId, String causativeAgentName,String dateOfOnset,String physicianName,String LastVisit, String allergyType, String specificAllergen,
			String treatmentPlan, String otherReleventMedicalCondition,String medicationHistory, String[] symptoms, String allergySeverity,
			String familyHistoryOfAllergies,String natureOfReaction,String comments, String observed,boolean inactive,ObservedDetails observedDetails) {
		
		super();
		this.id = id;
		this.patientId=patientId;
		this.dateOfOnset=dateOfOnset;
		this.causativeAgentName = causativeAgentName;
		this.physicianName = physicianName;
		this.LastVisit=LastVisit;
		this.allergyType = allergyType;
		this.specificAllergen=specificAllergen;
		this.treatmentPlan=treatmentPlan;
		this.otherReleventMedicalCondition=otherReleventMedicalCondition;
		this.medicationHistory=medicationHistory;
		this.symptoms=symptoms;
		this.allergySeverity=allergySeverity;
		this.familyHistoryOfAllergies=familyHistoryOfAllergies;
		this.natureOfReaction=natureOfReaction;
		this.comments=comments;
		this.observed=observed;
		this.inactive=inactive;
		this.observedDetails=observedDetails;
	}
	public PatientAllergy build(String id, String patientId, String causativeAgentName,String dateOfOnset, String physicianName,String LastVisit,
			String allergyType, String specificAllergen, String treatmentPlan, String otherReleventMedicalCondition, String medicationHistory, String[] symptoms,
			String allergySeverity,String familyHistoryOfAllergies,String natureOfReaction,String comments, String observed,boolean inactive,ObservedDetails observedDetails) {
		return new PatientAllergy (id,patientId,causativeAgentName,dateOfOnset,physicianName,LastVisit, allergyType,specificAllergen,treatmentPlan,otherReleventMedicalCondition,medicationHistory,symptoms, 
				allergySeverity,familyHistoryOfAllergies,natureOfReaction,comments, observed,inactive,observedDetails);
	}
	public void update(PatientAllergy patientAllergy)
	{
		this.setPatientId(patientAllergy.getPatientId());
		this.setCausativeAgentName(patientAllergy.getCausativeAgentName());
		this.setDateOfOnset(patientAllergy.getDateOfOnset());
		this.setUpdatedDate(patientAllergy.getUpdatedDate());
		this.setPhysicianName(patientAllergy.getPhysicianName());
		this.setLastVisit(patientAllergy.getLastVisit());
		this.setAllergyType(patientAllergy.getAllergyType());
		this.setSpecificAllergen(patientAllergy.getSpecificAllergen());
		this.setTreatmentPlan(patientAllergy.getTreatmentPlan());
		this.setOtherReleventMedicalCondition(patientAllergy.getOtherReleventMedicalCondition());
		this.setMedicationHistory(patientAllergy.getMedicationHistory());
		this.setSymptoms(patientAllergy.getSymptoms());
		this.setAllergySeverity(patientAllergy.getAllergySeverity());
		this.setFamilyHistoryOfAllergies(patientAllergy.getFamilyHistoryOfAllergies());
		this.setNatureOfReaction(patientAllergy.getNatureOfReaction());
		this.setComments(patientAllergy.getComments());
		this.setObserved(patientAllergy.getObserved());
		this.setInactive(patientAllergy.isInactive());
		this.setObservedDetails(patientAllergy.getObservedDetails());
	}
	
	public static class ObservedDetails {
		private String reactionDateTime;
		private  String dateOfLastReaction;
		
		
		public String getReactionDateTime() {
			return reactionDateTime;
		}
		public void setReactionDateTime(String reactionDateTime) {
			this.reactionDateTime = reactionDateTime;
		}
		public  String getDateOfLastReaction() {
			return dateOfLastReaction;
		}
		public  void setDateOfLastReaction(String dateOfLastReaction) {
			this.dateOfLastReaction = dateOfLastReaction;
		}
	}
}
