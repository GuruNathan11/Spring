package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "NurseToNurse") 
public class NurseToNurse {
	
	@Id
	private String id;
	private String pid;
	private additionalRisks additionalRisks;
	private String admittingNurse;
	private String age;
	private String appetite;
	private String birthDate;
	private String currentResidence;
	private String date;
	private String debriefingWithCareTeam;
	private String diagnosis;
	private String firstName;
	private String gender;
	private String[] hallucinations;
	private String hospitalPhoneNumber;
	private impairments impairments;
	private inPatientAdmission inPatientAdmission;
	private String infections;
	private String lastName;
	private String medicalHistory;
	private String medicationGiven;
	private String negativeCovid19Test;
	private String other;
	private String patientReturn;
	private String patientUsesCPAP;
	private String physician;
	private String presentingProblem;
	private String inPatientAdmissionDate;
	private String inPatientAdmissionHospitalNames;
	private String psychiatricMedications;
	private String race;
	private String restraints;
	private String room;
	private String sendingNurseAndHospital;
	private String sleepIssue;
	private String[] substanceAbuse;
	private String time;
	private String transport;
	private String type;
	private vitalSigns vitalSigns;
	private String createdAt;
	private String updatedAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public additionalRisks getAdditionalRisks() {
		return additionalRisks;
	}
	public void setAdditionalRisks(additionalRisks additionalRisks) {
		this.additionalRisks = additionalRisks;
	}
	public String getAdmittingNurse() {
		return admittingNurse;
	}
	public void setAdmittingNurse(String admittingNurse) {
		this.admittingNurse = admittingNurse;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAppetite() {
		return appetite;
	}
	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getHospitalPhoneNumber() {
		return hospitalPhoneNumber;
	}
	public void setHospitalPhoneNumber(String hospitalPhoneNumber) {
		this.hospitalPhoneNumber = hospitalPhoneNumber;
	}
	public impairments getImpairments() {
		return impairments;
	}
	public void setImpairments(impairments impairments) {
		this.impairments = impairments;
	}
	public inPatientAdmission getInPatientAdmission() {
		return inPatientAdmission;
	}
	public void setInPatientAdmission(inPatientAdmission inPatientAdmission) {
		this.inPatientAdmission = inPatientAdmission;
	}
	public String getInfections() {
		return infections;
	}
	public void setInfections(String infections) {
		this.infections = infections;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	public String getMedicationGiven() {
		return medicationGiven;
	}
	public void setMedicationGiven(String medicationGiven) {
		this.medicationGiven = medicationGiven;
	}
	
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	public String getPhysician() {
		return physician;
	}
	public void setPhysician(String physician) {
		this.physician = physician;
	}
	public String getPresentingProblem() {
		return presentingProblem;
	}
	public void setPresentingProblem(String presentingProblem) {
		this.presentingProblem = presentingProblem;
	}
	public String getInPatientAdmissionDate() {
		return inPatientAdmissionDate;
	}
	public void setInPatientAdmissionDate(String inPatientAdmissionDate) {
		this.inPatientAdmissionDate = inPatientAdmissionDate;
	}
	public String getInPatientAdmissionHospitalNames() {
		return inPatientAdmissionHospitalNames;
	}
	public void setInPatientAdmissionHospitalNames(String inPatientAdmissionHospitalNames) {
		this.inPatientAdmissionHospitalNames = inPatientAdmissionHospitalNames;
	}
	public String getPsychiatricMedications() {
		return psychiatricMedications;
	}
	public void setPsychiatricMedications(String psychiatricMedications) {
		this.psychiatricMedications = psychiatricMedications;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getRestraints() {
		return restraints;
	}
	public void setRestraints(String restraints) {
		this.restraints = restraints;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSendingNurseAndHospital() {
		return sendingNurseAndHospital;
	}
	public void setSendingNurseAndHospital(String sendingNurseAndHospital) {
		this.sendingNurseAndHospital = sendingNurseAndHospital;
	}
	
	public String getCurrentResidence() {
		return currentResidence;
	}
	public void setCurrentResidence(String currentResidence) {
		this.currentResidence = currentResidence;
	}
	public String getDebriefingWithCareTeam() {
		return debriefingWithCareTeam;
	}
	public void setDebriefingWithCareTeam(String debriefingWithCareTeam) {
		this.debriefingWithCareTeam = debriefingWithCareTeam;
	}
	public String getNegativeCovid19Test() {
		return negativeCovid19Test;
	}
	public void setNegativeCovid19Test(String negativeCovid19Test) {
		this.negativeCovid19Test = negativeCovid19Test;
	}
	public String getPatientReturn() {
		return patientReturn;
	}
	public void setPatientReturn(String patientReturn) {
		this.patientReturn = patientReturn;
	}
	public String getPatientUsesCPAP() {
		return patientUsesCPAP;
	}
	public void setPatientUsesCPAP(String patientUsesCPAP) {
		this.patientUsesCPAP = patientUsesCPAP;
	}
	public String getSleepIssue() {
		return sleepIssue;
	}
	public void setSleepIssue(String sleepIssue) {
		this.sleepIssue = sleepIssue;
	}
	
	public String[] getHallucinations() {
		return hallucinations;
	}
	public void setHallucinations(String[] hallucinations) {
		this.hallucinations = hallucinations;
	}
	public String[] getSubstanceAbuse() {
		return substanceAbuse;
	}
	public void setSubstanceAbuse(String[] substanceAbuse) {
		this.substanceAbuse = substanceAbuse;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public vitalSigns getVitalSigns() {
		return vitalSigns;
	}
	public void setVitalSigns(vitalSigns vitalSigns) {
		this.vitalSigns = vitalSigns;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static class additionalRisks {
	
		private String[] additionalRisks;
		private String otherRisk;
		
		public String[] getAdditionalRisks() {
			return additionalRisks;
		}
		public void setAdditionalRisks(String[] additionalRisks) {
			this.additionalRisks = additionalRisks;
		}
		public String getOtherRisk() {
			return otherRisk;
		}
		public void setOtherRisk(String otherRisk) {
			this.otherRisk = otherRisk;
		}
	}
	
	
	
	public static class impairments {
		
		private String allergies;
		private String allergiesText;
		private String ambulatory; 
		private String assistiveDevice;
		private String assistiveDeviceText;
		private String diet;
		private String documents;
		private String guardian;
		private String name;
		private String phone;
		private String status;
		public String getAllergies() {
			return allergies;
		}
		public void setAllergies(String allergies) {
			this.allergies = allergies;
		}
		public String getAllergiesText() {
			return allergiesText;
		}
		public void setAllergiesText(String allergiesText) {
			this.allergiesText = allergiesText;
		}
		public String getAmbulatory() {
			return ambulatory;
		}
		public void setAmbulatory(String ambulatory) {
			this.ambulatory = ambulatory;
		}
		public String getAssistiveDevice() {
			return assistiveDevice;
		}
		public void setAssistiveDevice(String assistiveDevice) {
			this.assistiveDevice = assistiveDevice;
		}
		public String getAssistiveDeviceText() {
			return assistiveDeviceText;
		}
		public void setAssistiveDeviceText(String assistiveDeviceText) {
			this.assistiveDeviceText = assistiveDeviceText;
		}
		public String getDiet() {
			return diet;
		}
		public void setDiet(String diet) {
			this.diet = diet;
		}
		public String getDocuments() {
			return documents;
		}
		public void setDocuments(String documents) {
			this.documents = documents;
		}
		public String getGuardian() {
			return guardian;
		}
		public void setGuardian(String guardian) {
			this.guardian = guardian;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	}
	
	public static class inPatientAdmission {
		
		private aggressionRisk aggressionRisk;
		private homicidalRisk homicidalRisk;
		private suicidalRisk suicidalRisk;
		
		public aggressionRisk getAggressionRisk() {
			return aggressionRisk;
		}
		public void setAggressionRisk(aggressionRisk aggressionRisk) {
			this.aggressionRisk = aggressionRisk;
		}
		public homicidalRisk getHomicidalRisk() {
			return homicidalRisk;
		}
		public void setHomicidalRisk(homicidalRisk homicidalRisk) {
			this.homicidalRisk = homicidalRisk;
		}
		public suicidalRisk getSuicidalRisk() {
			return suicidalRisk;
		}
		public void setSuicidalRisk(suicidalRisk suicidalRisk) {
			this.suicidalRisk = suicidalRisk;
		}
	}
	
	public static class aggressionRisk {
		
		private String aggressionRisk;
		private String aggressionRiskText;
		
		
		public String getAggressionRisk() {
			return aggressionRisk;
		}
		public void setAggressionRisk(String aggressionRisk) {
			this.aggressionRisk = aggressionRisk;
		}
		public String getAggressionRiskText() {
			return aggressionRiskText;
		}
		public void setAggressionRiskText(String aggressionRiskText) {
			this.aggressionRiskText = aggressionRiskText;
		}
	}
	
	public static class homicidalRisk {
		
		private String homicidalRisk;
		private String intent;
		private String intentText;
		private String means;
		private String meansText;
		private String plan;
		private String planText;
		
		
		public String getHomicidalRisk() {
			return homicidalRisk;
		}
		public void setHomicidalRisk(String homicidalRisk) {
			this.homicidalRisk = homicidalRisk;
		}
		public String getIntent() {
			return intent;
		}
		public void setIntent(String intent) {
			this.intent = intent;
		}
		public String getIntentText() {
			return intentText;
		}
		public void setIntentText(String intentText) {
			this.intentText = intentText;
		}
		public String getMeans() {
			return means;
		}
		public void setMeans(String means) {
			this.means = means;
		}
		public String getMeansText() {
			return meansText;
		}
		public void setMeansText(String meansText) {
			this.meansText = meansText;
		}
		public String getPlan() {
			return plan;
		}
		public void setPlan(String plan) {
			this.plan = plan;
		}
		public String getPlanText() {
			return planText;
		}
		public void setPlanText(String planText) {
			this.planText = planText;
		}
	}
	
	public static class suicidalRisk {
		
		private String intent;
		private String intentText;
		private String means;
		private String meansText;
		private String plan;
		private String planText;
		private String suicidalRisk;
		
		public String getIntent() {
			return intent;
		}
		public void setIntent(String intent) {
			this.intent = intent;
		}
		public String getIntentText() {
			return intentText;
		}
		public void setIntentText(String intentText) {
			this.intentText = intentText;
		}
		public String getMeans() {
			return means;
		}
		public void setMeans(String means) {
			this.means = means;
		}
		public String getMeansText() {
			return meansText;
		}
		public void setMeansText(String meansText) {
			this.meansText = meansText;
		}
		public String getPlan() {
			return plan;
		}
		public void setPlan(String plan) {
			this.plan = plan;
		}
		public String getPlanText() {
			return planText;
		}
		public void setPlanText(String planText) {
			this.planText = planText;
		}
		public String getSuicidalRisk() {
			return suicidalRisk;
		}
		public void setSuicidalRisk(String suicidalRisk) {
			this.suicidalRisk = suicidalRisk;
		}
		
	}
	
	public static class vitalSigns {
		
		private String bloodPressure;
		private String pulse;
		private String pulseOx;
		private String respiration;
		private String temp;
		
		public String getBloodPressure() {
			return bloodPressure;
		}
		public void setBloodPressure(String bloodPressure) {
			this.bloodPressure = bloodPressure;
		}
		public String getPulse() {
			return pulse;
		}
		public void setPulse(String pulse) {
			this.pulse = pulse;
		}
		public String getPulseOx() {
			return pulseOx;
		}
		public void setPulseOx(String pulseOx) {
			this.pulseOx = pulseOx;
		}
		public String getRespiration() {
			return respiration;
		}
		public void setRespiration(String respiration) {
			this.respiration = respiration;
		}
		public String getTemp() {
			return temp;
		}
		public void setTemp(String temp) {
			this.temp = temp;
		}
	}
	
	public void update(NurseToNurse nurse) {
		
		this.setAdditionalRisks(nurse.getAdditionalRisks());
		this.setAdmittingNurse(nurse.getAdmittingNurse());
		this.setAge(nurse.getAge());
		this.setAppetite(nurse.getAppetite());
		this.setBirthDate(nurse.getBirthDate());
		this.setCurrentResidence(nurse.getCurrentResidence());
		this.setDate(nurse.getDate());
		this.setDebriefingWithCareTeam(nurse.getDebriefingWithCareTeam());
		this.setDiagnosis(nurse.getDiagnosis());
		this.setFirstName(nurse.getFirstName());
		this.setGender(nurse.getGender());
		this.setHallucinations(nurse.getHallucinations());
		this.setHospitalPhoneNumber(nurse.getHospitalPhoneNumber());
		this.setImpairments(nurse.getImpairments());
		this.setInPatientAdmission(nurse.getInPatientAdmission());
		this.setInfections(nurse.getInfections());
		this.setLastName(nurse.getLastName());
		this.setMedicalHistory(nurse.getMedicalHistory());
		this.setMedicationGiven(nurse.getMedicationGiven());
		this.setNegativeCovid19Test(nurse.getNegativeCovid19Test());
		this.setOther(nurse.getOther());
		this.setPatientReturn(nurse.getPatientReturn());
		this.setPatientUsesCPAP(nurse.getPatientUsesCPAP());
		this.setPhysician(nurse.getPhysician());
		this.setPresentingProblem(nurse.getPresentingProblem());
		this.setInPatientAdmissionDate(nurse.getInPatientAdmissionDate());
		this.setInPatientAdmissionHospitalNames(nurse.getInPatientAdmissionHospitalNames());
		this.setPsychiatricMedications(nurse.getPsychiatricMedications());
		this.setRace(nurse.getRace());
		this.setRestraints(nurse.getRestraints());
		this.setRoom(nurse.getRoom());
		this.setSendingNurseAndHospital(nurse.getSendingNurseAndHospital());
		this.setSleepIssue(nurse.getSleepIssue());
		this.setSubstanceAbuse(nurse.getSubstanceAbuse());
		this.setTime(nurse.getTime());
		this.setTransport(nurse.getTransport());
		this.setType(nurse.getType());
		this.setVitalSigns(nurse.getVitalSigns());
	}
}
