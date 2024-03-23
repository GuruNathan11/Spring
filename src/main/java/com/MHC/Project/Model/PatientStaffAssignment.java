//package com.MHC.Project.Model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//@Document(collection = "Patient-Staff-Management")
////@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
//public class PatientStaffAssignment {
//
//	@Id
//	private String id;
//	private String[] PID;
//	private String psychiatrists1;
//	private String psychiatrists2;
//	private String psychiatrists3;
//	private String psychiatrists4;
//	private String psychiatrists5;
//	private String medicalDoctor1;
//	private String medicalDoctor2;
//	private String medicalDoctor3;
//	private String medicalDoctor4;
//	private String medicalDoctor5;
//	private String nursePractitioner1;
//	private String nursePractitioner2;
//	private String nursePractitioner3;
//	private String nursePractitioner4;
//	private String nursePractitioner5;
//	private String physicianAssistant1;
//	private String physicianAssistant2;
//	private String physicianAssistant3;
//	private String physicianAssistant4;
//	private String physicianAssistant5;
//	private String psychologist1;
//	private String psychologist2;
//	private String psychologist3;
//	private String psychologist4;
//	private String psychologist5;
//	private String registeredNurses1;
//	private String registeredNurses2;
//	private String registeredNurses3;
//	private String registeredNurses4;
//	private String registeredNurses5;
//	private String socialWorkers1;
//	private String socialWorkers2;
//	private String socialWorkers3;
//	private String socialWorkers4;
//	private String socialWorkers5;
//	private String activityTherapist1;
//	private String activityTherapist2;
//	private String activityTherapist3;
//	private String activityTherapist4;
//	private String activityTherapist5;
//	private String yogaTherapist1;
//	private String yogaTherapist2;
//	private String yogaTherapist3;
//	private String yogaTherapist4;
//	private String yogaTherapist5;
//	private String mentalHealthWorkers1;
//	private String mentalHealthWorkers2;
//	private String mentalHealthWorkers3;
//	private String mentalHealthWorkers4;
//	private String mentalHealthWorkers5;
//	private String rROfficer1;
//	private String rROfficer2;
//	private String rROfficer3;
//	private String rROfficer4;
//	private String rROfficer5;
//	private String nurseManagers1;
//	private String nurseManagers2;
//	private String nurseManagers3;
//	private String nurseManagers4;
//	private String nurseManagers5;
//	private String dirOfNursing1;
//	private String dirOfNursing2;
//	private String dirOfNursing3;
//	private String dirOfNursing4;
//	private String dirOfNursing5;
//	private String executives1;
//	private String executives2;
//	private String executives3;
//	private String executives4;
//	private String executives5;
//	private String HR1;
//	private String HR2;
//	private String HR3;
//	private String HR4;
//	private String HR5;
//	private String QRDirector1;
//	private String QRDirector2;
//	private String QRDirector3;
//	private String QRDirector4;
//	private String QRDirector5;
//	private String dirOfHIM1;
//	private String dirOfHIM2;
//	private String dirOfHIM3;
//	private String dirOfHIM4;
//	private String dirOfHIM5;
//	private String regDietitian1;
//	private String regDietitian2;
//	private String regDietitian3;
//	private String regDietitian4;
//	private String regDietitian5;
//	private String createdAt;
//	private String updatedAt;
//	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String[] getPID() {
//		return PID;
//	}
//	public void setPID(String[] pID) {
//		this.PID = pID;
//	}
//	// ****** Psychiatrists ********
//	public String getPsychiatrists1() {
//		return psychiatrists1;
//	}
//	public void setPsychiatrists1(String psychiatrists1) {
//		this.psychiatrists1 = psychiatrists1;
//	}
//	public String getPsychiatrists2() {
//		return psychiatrists2;
//	}
//	public void setPsychiatrists2(String psychiatrists2) {
//		this.psychiatrists2 = psychiatrists2;
//	}
//	public String getPsychiatrists3() {
//		return psychiatrists3;
//	}
//	public void setPsychiatrists3(String psychiatrists3) {
//		this.psychiatrists3 = psychiatrists3;
//	}
//	public String getPsychiatrists4() {
//		return psychiatrists4;
//	}
//	public void setPsychiatrists4(String psychiatrists4) {
//		this.psychiatrists4 = psychiatrists4;
//	}
//	public String getPsychiatrists5() {
//		return psychiatrists5;
//	}
//	public void setPsychiatrists5(String psychiatrists5) {
//		this.psychiatrists5 = psychiatrists5;
//	}
//	// ****** Medical Doctor ********
//	public String getMedicalDoctor1() {
//		return medicalDoctor1;
//	}
//	public void setMedicalDoctor1(String medicalDoctor1) {
//		this.medicalDoctor1 = medicalDoctor1;
//	}
//	public String getMedicalDoctor2() {
//		return medicalDoctor2;
//	}
//	public void setMedicalDoctor2(String medicalDoctor2) {
//		this.medicalDoctor2 = medicalDoctor2;
//	}
//	public String getMedicalDoctor3() {
//		return medicalDoctor3;
//	}
//	public void setMedicalDoctor3(String medicalDoctor3) {
//		this.medicalDoctor3 = medicalDoctor3;
//	}
//	public String getMedicalDoctor4() {
//		return medicalDoctor4;
//	}
//	public void setMedicalDoctor4(String medicalDoctor4) {
//		this.medicalDoctor4 = medicalDoctor4;
//	}
//	public String getMedicalDoctor5() {
//		return medicalDoctor5;
//	}
//	public void setMedicalDoctor5(String medicalDoctor5) {
//		this.medicalDoctor5 = medicalDoctor5;
//	}
//	// ****** Nurse Practitioner ********
//	public String getNursePractitioner1() {
//		return nursePractitioner1;
//	}
//	public void setNursePractitioner1(String nursePractitioner1) {
//		this.nursePractitioner1 = nursePractitioner1;
//	}
//	public String getNursePractitioner2() {
//		return nursePractitioner2;
//	}
//	public void setNursePractitioner2(String nursePractitioner2) {
//		this.nursePractitioner2 = nursePractitioner2;
//	}
//	public String getNursePractitioner3() {
//		return nursePractitioner3;
//	}
//	public void setNursePractitioner3(String nursePractitioner3) {
//		this.nursePractitioner3 = nursePractitioner3;
//	}
//	public String getNursePractitioner4() {
//		return nursePractitioner4;
//	}
//	public void setNursePractitioner4(String nursePractitioner4) {
//		this.nursePractitioner4 = nursePractitioner4;
//	}
//	public String getNursePractitioner5() {
//		return nursePractitioner5;
//	}
//	public void setNursePractitioner5(String nursePractitioner5) {
//		this.nursePractitioner5 = nursePractitioner5;
//	}
//	// ****** Physician Assistant ********
//	public String getPhysicianAssistant1() {
//		return physicianAssistant1;
//	}
//	public void setPhysicianAssistant1(String physicianAssistant1) {
//		this.physicianAssistant1 = physicianAssistant1;
//	}
//	public String getPhysicianAssistant2() {
//		return physicianAssistant2;
//	}
//	public void setPhysicianAssistant2(String physicianAssistant2) {
//		this.physicianAssistant2 = physicianAssistant2;
//	}
//	public String getPhysicianAssistant3() {
//		return physicianAssistant3;
//	}
//	public void setPhysicianAssistant3(String physicianAssistant3) {
//		this.physicianAssistant3 = physicianAssistant3;
//	}
//	public String getPhysicianAssistant4() {
//		return physicianAssistant4;
//	}
//	public void setPhysicianAssistant4(String physicianAssistant4) {
//		this.physicianAssistant4 = physicianAssistant4;
//	}
//	public String getPhysicianAssistant5() {
//		return physicianAssistant5;
//	}
//	public void setPhysicianAssistant5(String physicianAssistant5) {
//		this.physicianAssistant5 = physicianAssistant5;
//	}
//	// ****** Psychologist ********
//	public String getPsychologist1() {
//		return psychologist1;
//	}
//	public void setPsychologist1(String psychologist1) {
//		this.psychologist1 = psychologist1;
//	}
//	public String getPsychologist2() {
//		return psychologist2;
//	}
//	public void setPsychologist2(String psychologist2) {
//		this.psychologist2 = psychologist2;
//	}
//	public String getPsychologist3() {
//		return psychologist3;
//	}
//	public void setPsychologist3(String psychologist3) {
//		this.psychologist3 = psychologist3;
//	}
//	public String getPsychologist4() {
//		return psychologist4;
//	}
//	public void setPsychologist4(String psychologist4) {
//		this.psychologist4 = psychologist4;
//	}
//	public String getPsychologist5() {
//		return psychologist5;
//	}
//	public void setPsychologist5(String psychologist5) {
//		this.psychologist5 = psychologist5;
//	}
//	// ****** Registered Nurses ********
//	public String getRegisteredNurses1() {
//		return registeredNurses1;
//	}
//	public void setRegisteredNurses1(String registeredNurses1) {
//		this.registeredNurses1 = registeredNurses1;
//	}
//	public String getRegisteredNurses2() {
//		return registeredNurses2;
//	}
//	public void setRegisteredNurses2(String registeredNurses2) {
//		this.registeredNurses2 = registeredNurses2;
//	}
//	public String getRegisteredNurses3() {
//		return registeredNurses3;
//	}
//	public void setRegisteredNurses3(String registeredNurses3) {
//		this.registeredNurses3 = registeredNurses3;
//	}
//	public String getRegisteredNurses4() {
//		return registeredNurses4;
//	}
//	public void setRegisteredNurses4(String registeredNurses4) {
//		this.registeredNurses4 = registeredNurses4;
//	}
//	public String getRegisteredNurses5() {
//		return registeredNurses5;
//	}
//	public void setRegisteredNurses5(String registeredNurses5) {
//		this.registeredNurses5 = registeredNurses5;
//	}
//	// ****** Social Workers ********
//	public String getSocialWorkers1() {
//		return socialWorkers1;
//	}
//	public void setSocialWorkers1(String socialWorkers1) {
//		this.socialWorkers1 = socialWorkers1;
//	}
//	public String getSocialWorkers2() {
//		return socialWorkers2;
//	}
//	public void setSocialWorkers2(String socialWorkers2) {
//		this.socialWorkers2 = socialWorkers2;
//	}
//	public String getSocialWorkers3() {
//		return socialWorkers3;
//	}
//	public void setSocialWorkers3(String socialWorkers3) {
//		this.socialWorkers3 = socialWorkers3;
//	}
//	public String getSocialWorkers4() {
//		return socialWorkers4;
//	}
//	public void setSocialWorkers4(String socialWorkers4) {
//		this.socialWorkers4 = socialWorkers4;
//	}
//	public String getSocialWorkers5() {
//		return socialWorkers5;
//	}
//	public void setSocialWorkers5(String socialWorkers5) {
//		this.socialWorkers5 = socialWorkers5;
//	}
//	// ****** Activity Therapist ********
//	public String getActivityTherapist1() {
//		return activityTherapist1;
//	}
//	public void setActivityTherapist1(String activityTherapist1) {
//		this.activityTherapist1 = activityTherapist1;
//	}
//	public String getActivityTherapist2() {
//		return activityTherapist2;
//	}
//	public void setActivityTherapist2(String activityTherapist2) {
//		this.activityTherapist2 = activityTherapist2;
//	}
//	public String getActivityTherapist3() {
//		return activityTherapist3;
//	}
//	public void setActivityTherapist3(String activityTherapist3) {
//		this.activityTherapist3 = activityTherapist3;
//	}
//	public String getActivityTherapist4() {
//		return activityTherapist4;
//	}
//	public void setActivityTherapist4(String activityTherapist4) {
//		this.activityTherapist4 = activityTherapist4;
//	}
//	public String getActivityTherapist5() {
//		return activityTherapist5;
//	}
//	public void setActivityTherapist5(String activityTherapist5) {
//		this.activityTherapist5 = activityTherapist5;
//	}
//	// ****** Yoga Therapist ********
//	public String getYogaTherapist1() {
//		return yogaTherapist1;
//	}
//	public void setYogaTherapist1(String yogaTherapist1) {
//		this.yogaTherapist1 = yogaTherapist1;
//	}
//	public String getYogaTherapist2() {
//		return yogaTherapist2;
//	}
//	public void setYogaTherapist2(String yogaTherapist2) {
//		this.yogaTherapist2 = yogaTherapist2;
//	}
//	public String getYogaTherapist3() {
//		return yogaTherapist3;
//	}
//	public void setYogaTherapist3(String yogaTherapist3) {
//		this.yogaTherapist3 = yogaTherapist3;
//	}
//	public String getYogaTherapist4() {
//		return yogaTherapist4;
//	}
//	public void setYogaTherapist4(String yogaTherapist4) {
//		this.yogaTherapist4 = yogaTherapist4;
//	}
//	public String getYogaTherapist5() {
//		return yogaTherapist5;
//	}
//	public void setYogaTherapist5(String yogaTherapist5) {
//		this.yogaTherapist5 = yogaTherapist5;
//	}
//	
//	public String getMentalHealthWorkers1() {
//		return mentalHealthWorkers1;
//	}
//	public void setMentalHealthWorkers1(String mentalHealthWorkers1) {
//		this.mentalHealthWorkers1 = mentalHealthWorkers1;
//	}
//	public String getMentalHealthWorkers2() {
//		return mentalHealthWorkers2;
//	}
//	public void setMentalHealthWorkers2(String mentalHealthWorkers2) {
//		this.mentalHealthWorkers2 = mentalHealthWorkers2;
//	}
//	public String getMentalHealthWorkers3() {
//		return mentalHealthWorkers3;
//	}
//	public void setMentalHealthWorkers3(String mentalHealthWorkers3) {
//		this.mentalHealthWorkers3 = mentalHealthWorkers3;
//	}
//	public String getMentalHealthWorkers4() {
//		return mentalHealthWorkers4;
//	}
//	public void setMentalHealthWorkers4(String mentalHealthWorkers4) {
//		this.mentalHealthWorkers4 = mentalHealthWorkers4;
//	}
//	public String getMentalHealthWorkers5() {
//		return mentalHealthWorkers5;
//	}
//	public void setMentalHealthWorkers5(String mentalHealthWorkers5) {
//		this.mentalHealthWorkers5 = mentalHealthWorkers5;
//	}
//	// *********** Registerd Dietitian *************
//	public String getrROfficer1() {
//		return rROfficer1;
//	}
//	public void setrROfficer1(String rROfficer1) {
//		this.rROfficer1 = rROfficer1;
//	}
//	public String getrROfficer2() {
//		return rROfficer2;
//	}
//	public void setrROfficer2(String rROfficer2) {
//		this.rROfficer2 = rROfficer2;
//	}
//	public String getrROfficer3() {
//		return rROfficer3;
//	}
//	public void setrROfficer3(String rROfficer3) {
//		this.rROfficer3 = rROfficer3;
//	}
//	public String getrROfficer4() {
//		return rROfficer4;
//	}
//	public void setrROfficer4(String rROfficer4) {
//		this.rROfficer4 = rROfficer4;
//	}
//	public String getrROfficer5() {
//		return rROfficer5;
//	}
//	public void setrROfficer5(String rROfficer5) {
//		this.rROfficer5 = rROfficer5;
//	}
//	// *********** Nursing Managers *************
//	public String getNurseManagers1() {
//		return nurseManagers1;
//	}
//	public void setNurseManagers1(String nurseManagers1) {
//		this.nurseManagers1 = nurseManagers1;
//	}
//	public String getNurseManagers2() {
//		return nurseManagers2;
//	}
//	public void setNurseManagers2(String nurseManagers2) {
//		this.nurseManagers2 = nurseManagers2;
//	}
//	public String getNurseManagers3() {
//		return nurseManagers3;
//	}
//	public void setNurseManagers3(String nurseManagers3) {
//		this.nurseManagers3 = nurseManagers3;
//	}
//	public String getNurseManagers4() {
//		return nurseManagers4;
//	}
//	public void setNurseManagers4(String nurseManagers4) {
//		this.nurseManagers4 = nurseManagers4;
//	}
//	public String getNurseManagers5() {
//		return nurseManagers5;
//	}
//	public void setNurseManagers5(String nurseManagers5) {
//		this.nurseManagers5 = nurseManagers5;
//	}
//	// *********** Director Of Nursing *************
//	public String getDirOfNursing1() {
//		return dirOfNursing1;
//	}
//	public void setDirOfNursing1(String dirOfNursing1) {
//		this.dirOfNursing1 = dirOfNursing1;
//	}
//	public String getDirOfNursing2() {
//		return dirOfNursing2;
//	}
//	public void setDirOfNursing2(String dirOfNursing2) {
//		this.dirOfNursing2 = dirOfNursing2;
//	}
//	public String getDirOfNursing3() {
//		return dirOfNursing3;
//	}
//	public void setDirOfNursing3(String dirOfNursing3) {
//		this.dirOfNursing3 = dirOfNursing3;
//	}
//	public String getDirOfNursing4() {
//		return dirOfNursing4;
//	}
//	public void setDirOfNursing4(String dirOfNursing4) {
//		this.dirOfNursing4 = dirOfNursing4;
//	}
//	public String getDirOfNursing5() {
//		return dirOfNursing5;
//	}
//	public void setDirOfNursing5(String dirOfNursing5) {
//		this.dirOfNursing5 = dirOfNursing5;
//	}
//	// *********** Executives- CEO *************
//	public String getExecutives1() {
//		return executives1;
//	}
//	public void setExecutives1(String executives1) {
//		this.executives1 = executives1;
//	}
//	public String getExecutives2() {
//		return executives2;
//	}
//	public void setExecutives2(String executives2) {
//		this.executives2 = executives2;
//	}
//	public String getExecutives3() {
//		return executives3;
//	}
//	public void setExecutives3(String executives3) {
//		this.executives3 = executives3;
//	}
//	public String getExecutives4() {
//		return executives4;
//	}
//	public void setExecutives4(String executives4) {
//		this.executives4 = executives4;
//	}
//	public String getExecutives5() {
//		return executives5;
//	}
//	public void setExecutives5(String executives5) {
//		this.executives5 = executives5;
//	}
//	// ****** Human  Resources********
//	public String getHR1() {
//		return HR1;
//	}
//	public void setHR1(String HR1) {
//		this.HR1 = HR1;
//	}
//	public String getHR2() {
//		return HR2;
//	}
//	public void setHR2(String HR2) {
//		this.HR2 = HR2;
//	}
//	public String getHR3() {
//		return HR3;
//	}
//	public void setHR3(String HR3) {
//		this.HR3 = HR3;
//	}
//	public String getHR4() {
//		return HR4;
//	}
//	public void setHR4(String HR4) {
//		this.HR4 = HR4;
//	}
//	public String getHR5() {
//		return HR5;
//	}
//	public void setHR5(String HR5) {
//		this.HR5 = HR5;
//	}
//	// *********** Qaulity And Risk Director *************
//	public String getQRDirector1() {
//		return QRDirector1;
//	}
//	public void setQRDirector1(String qRDirector1) {
//		QRDirector1 = qRDirector1;
//	}
//	public String getQRDirector2() {
//		return QRDirector2;
//	}
//	public void setQRDirector2(String qRDirector2) {
//		QRDirector2 = qRDirector2;
//	}
//	public String getQRDirector3() {
//		return QRDirector3;
//	}
//	public void setQRDirector3(String qRDirector3) {
//		QRDirector3 = qRDirector3;
//	}
//	public String getQRDirector4() {
//		return QRDirector4;
//	}
//	public void setQRDirector4(String qRDirector4) {
//		QRDirector4 = qRDirector4;
//	}
//	public String getQRDirector5() {
//		return QRDirector5;
//	}
//	public void setQRDirector5(String qRDirector5) {
//		QRDirector5 = qRDirector5;
//	}
//	// *********** Human Information Management *************
//	public String getDirOfHIM1() {
//		return dirOfHIM1;
//	}
//	public void setDirOfHIM1(String dirOfHIM1) {
//		this.dirOfHIM1 = dirOfHIM1;
//	}
//	public String getDirOfHIM2() {
//		return dirOfHIM2;
//	}
//	public void setDirOfHIM2(String dirOfHIM2) {
//		this.dirOfHIM2 = dirOfHIM2;
//	}
//	public String getDirOfHIM3() {
//		return dirOfHIM3;
//	}
//	public void setDirOfHIM3(String dirOfHIM3) {
//		this.dirOfHIM3 = dirOfHIM3;
//	}
//	public String getDirOfHIM4() {
//		return dirOfHIM4;
//	}
//	public void setDirOfHIM4(String dirOfHIM4) {
//		this.dirOfHIM4 = dirOfHIM4;
//	}
//	public String getDirOfHIM5() {
//		return dirOfHIM5;
//	}
//	public void setDirOfHIM5(String dirOfHIM5) {
//		this.dirOfHIM5 = dirOfHIM5;
//	}
//	// *********** Registerd Dietitian *************
//	public String getRegDietitian1() {
//		return regDietitian1;
//	}
//	public void setRegDietitian1(String regDietitian1) {
//		this.regDietitian1 = regDietitian1;
//	}
//	public String getRegDietitian2() {
//		return regDietitian2;
//	}
//	public void setRegDietitian2(String regDietitian2) {
//		this.regDietitian2 = regDietitian2;
//	}
//	public String getRegDietitian3() {
//		return regDietitian3;
//	}
//	public void setRegDietitian3(String regDietitian3) {
//		this.regDietitian3 = regDietitian3;
//	}
//	public String getRegDietitian4() {
//		return regDietitian4;
//	}
//	public void setRegDietitian4(String regDietitian4) {
//		this.regDietitian4 = regDietitian4;
//	}
//	public String getRegDietitian5() {
//		return regDietitian5;
//	}
//	public void setRegDietitian5(String regDietitian5) {
//		this.regDietitian5 = regDietitian5;
//	}
//	public String getCreatedAt() {
//		return createdAt;
//	}
//	public void setCreatedAt(String createdAt) {
//		this.createdAt = createdAt;
//	}
//	public String getUpdatedAt() {
//		return updatedAt;
//	}
//	public void setUpdatedAt(String updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//	// *********** Constructor *************
//	public PatientStaffAssignment(String id,String[] PID, String psychiatrists1, String psychiatrists2, 
//			String psychiatrists3, String psychiatrists4, String psychiatrists5, String medicalDoctor1,
//			String medicalDoctor2, String medicalDoctor3, String medicalDoctor4, String medicalDoctor5,
//			String nursePractitioner1, String nursePractitioner2, String nursePractitioner3, String nursePractitioner4,
//			String nursePractitioner5, String physicianAssistant1, String physicianAssistant2, String physicianAssistant3,
//			String physicianAssistant4,	String physicianAssistant5, String psychologist1, String psychologist2,
//			String psychologist3, String psychologist4, String psychologist5, String registeredNurses1,String registeredNurses2,
//			String registeredNurses3,String registeredNurses4, String registeredNurses5, String socialWorkers1, String socialWorkers2,
//			String socialWorkers3, String socialWorkers4, String socialWorkers5, String activityTherapist1, String activityTherapist2,
//			String activityTherapist3, String activityTherapist4, String activityTherapist5, String yogaTherapist1, String yogaTherapist2,
//			String yogaTherapist3, String yogaTherapist4, String yogaTherapist5, String mentalHealthWorkers1,String mentalHealthWorkers2,
//			String mentalHealthWorkers3, String mentalHealthWorkers4, String mentalHealthWorkers5, String rROfficer1, String rROfficer2, 
//			String rROfficer3, String rROfficer4, String rROfficer5, String nurseManagers1, String nurseManagers2,
//			String nurseManagers3, String nurseManagers4, String nurseManagers5, String dirOfNursing1, String dirOfNursing2, 
//			String dirOfNursing3, String dirOfNursing4, String dirOfNursing5, String executives1, String executives2, String executives3,
//			String executives4, String executives5, String HR1, String HR2, String HR3, String HR4, String HR5, String QRDirector1,
//			String QRDirector2, String QRDirector3, String QRDirector4, String QRDirector5, String dirOfHIM1, String dirOfHIM2, 
//			String dirOfHIM3, String dirOfHIM4, String dirOfHIM5, String regDietitian1, String regDietitian2, String regDietitian3, 
//			String regDietitian4, String regDietitian5) {
//		super();
//		this.id = id;
//		this.PID = PID;
//		this.psychiatrists1 = psychiatrists1;
//		this.psychiatrists2 = psychiatrists2;
//		this.psychiatrists3 = psychiatrists3;
//		this.psychiatrists4 = psychiatrists4;
//		this.psychiatrists5 = psychiatrists5;
//		this.medicalDoctor1 = medicalDoctor1;
//		this.medicalDoctor2 = medicalDoctor2;
//		this.medicalDoctor3 = medicalDoctor3;
//		this.medicalDoctor4 = medicalDoctor4;
//		this.medicalDoctor5 = medicalDoctor5;
//		this.nursePractitioner1 = nursePractitioner1;
//		this.nursePractitioner2 = nursePractitioner2;
//		this.nursePractitioner3 = nursePractitioner3;
//		this.nursePractitioner4 = nursePractitioner4;
//		this.nursePractitioner5 = nursePractitioner5;
//		this.physicianAssistant1 = physicianAssistant1;
//		this.physicianAssistant2 = physicianAssistant2;
//		this.physicianAssistant3 = physicianAssistant3;
//		this.physicianAssistant4 = physicianAssistant4;
//		this.physicianAssistant5 = physicianAssistant5;
//		this.psychologist1 = psychologist1;
//		this.psychologist2 = psychologist2;
//		this.psychologist3 = psychologist3;
//		this.psychologist4 = psychologist4;
//		this.psychologist5 = psychologist5;
//		this.registeredNurses1 = registeredNurses1;
//		this.registeredNurses2 = registeredNurses2;
//		this.registeredNurses3 = registeredNurses3;
//		this.registeredNurses4 = registeredNurses4;
//		this.registeredNurses5 = registeredNurses5;
//		this.socialWorkers1 = socialWorkers1;
//		this.socialWorkers2 = socialWorkers2;
//		this.socialWorkers3 = socialWorkers3;
//		this.socialWorkers4 = socialWorkers4;
//		this.socialWorkers5 = socialWorkers5;
//		this.activityTherapist1 = activityTherapist1;
//		this.activityTherapist2 = activityTherapist2;
//		this.activityTherapist3 = activityTherapist3;
//		this.activityTherapist4 = activityTherapist4;
//		this.activityTherapist5 = activityTherapist5;
//		this.yogaTherapist1 = yogaTherapist1;
//		this.yogaTherapist2 = yogaTherapist2;
//		this.yogaTherapist3 = yogaTherapist3;
//		this.yogaTherapist4 = yogaTherapist4;
//		this.yogaTherapist5 = yogaTherapist5;
//		this.mentalHealthWorkers1 = mentalHealthWorkers1;
//		this.mentalHealthWorkers2 = mentalHealthWorkers2;
//		this.mentalHealthWorkers3 = mentalHealthWorkers3;
//		this.mentalHealthWorkers4 = mentalHealthWorkers4;
//		this.mentalHealthWorkers5 = mentalHealthWorkers5;
//		this.rROfficer1 = rROfficer1;
//		this.rROfficer2 = rROfficer2;
//		this.rROfficer3 = rROfficer3;
//		this.rROfficer4 = rROfficer4;
//		this.rROfficer5 = rROfficer5;
//		this.nurseManagers1 = nurseManagers1;
//		this.nurseManagers2 = nurseManagers2;
//		this.nurseManagers3 = nurseManagers3;
//		this.nurseManagers4 = nurseManagers4;
//		this.nurseManagers5 = nurseManagers5;
//		this.dirOfNursing1 = dirOfNursing1;
//		this.dirOfNursing2 = dirOfNursing2;
//		this.dirOfNursing3 = dirOfNursing3;
//		this.dirOfNursing4 = dirOfNursing4;
//		this.dirOfNursing5 = dirOfNursing5;
//		this.executives1 = executives1;
//		this.executives2 = executives2;
//		this.executives3 = executives3;
//		this.executives4 = executives4;
//		this.executives5 = executives5;
//		this.HR1 = HR1;
//		this.HR2 = HR2;
//		this.HR3 = HR3;
//		this.HR4 = HR4;
//		this.HR5 = HR5;
//		this.QRDirector1 = QRDirector1;
//		this.QRDirector2 = QRDirector2;
//		this.QRDirector3 = QRDirector3;
//		this.QRDirector4 = QRDirector4;
//		this.QRDirector5 = QRDirector5;
//		this.dirOfHIM1 = dirOfHIM1;
//		this.dirOfHIM2 = dirOfHIM2;
//		this.dirOfHIM3 = dirOfHIM3;
//		this.dirOfHIM4 = dirOfHIM4;
//		this.dirOfHIM5 = dirOfHIM5;
//		this.regDietitian1 = regDietitian1;
//		this.regDietitian2 = regDietitian2;
//		this.regDietitian3 = regDietitian3;
//		this.regDietitian4 = regDietitian4;
//		this.regDietitian5 = regDietitian5;
//	}
//	
//	public static PatientStaffAssignment build(String id,String[] PID, String psychiatrists1, String psychiatrists2, 
//			String psychiatrists3, String psychiatrists4, String psychiatrists5, String medicalDoctor1,
//			String medicalDoctor2, String medicalDoctor3, String medicalDoctor4, String medicalDoctor5,
//			String nursePractitioner1, String nursePractitioner2, String nursePractitioner3, String nursePractitioner4,
//			String nursePractitioner5, String physicianAssistant1, String physicianAssistant2, String physicianAssistant3,
//			String physicianAssistant4,	String physicianAssistant5, String psychologist1, String psychologist2,
//			String psychologist3, String psychologist4, String psychologist5, String registeredNurses1,String registeredNurses2,
//			String registeredNurses3,String registeredNurses4, String registeredNurses5, String socialWorkers1, String socialWorkers2,
//			String socialWorkers3, String socialWorkers4, String socialWorkers5, String activityTherapist1, String activityTherapist2,
//			String activityTherapist3, String activityTherapist4, String activityTherapist5, String yogaTherapist1, String yogaTherapist2,
//			String yogaTherapist3, String yogaTherapist4, String yogaTherapist5, String mentalHealthWorkers1,String mentalHealthWorkers2,
//			String mentalHealthWorkers3, String mentalHealthWorkers4, String mentalHealthWorkers5, String rROfficer1, String rROfficer2, 
//			String rROfficer3, String rROfficer4, String rROfficer5, String nurseManagers1, String nurseManagers2,
//			String nurseManagers3, String nurseManagers4, String nurseManagers5, String dirOfNursing1, String dirOfNursing2, 
//			String dirOfNursing3, String dirOfNursing4, String dirOfNursing5, String executives1, String executives2, String executives3,
//			String executives4, String executives5, String HR1, String HR2, String HR3, String HR4, String HR5, String QRDirector1,
//			String QRDirector2, String QRDirector3, String QRDirector4, String QRDirector5, String dirOfHIM1, String dirOfHIM2, 
//			String dirOfHIM3, String dirOfHIM4, String dirOfHIM5, String regDietitian1, String regDietitian2, String regDietitian3, 
//			String regDietitian4, String regDietitian5) {
//		
//		return new PatientStaffAssignment (id,PID,psychiatrists1,psychiatrists2,psychiatrists3,psychiatrists4,psychiatrists5,
//				medicalDoctor1,medicalDoctor2,medicalDoctor3,medicalDoctor4,medicalDoctor5,nursePractitioner1,nursePractitioner2,
//				nursePractitioner3,nursePractitioner4,nursePractitioner5,physicianAssistant1,physicianAssistant2,physicianAssistant1,
//				physicianAssistant4,physicianAssistant5,psychologist1,psychologist2,psychologist3,psychologist4,psychologist5,
//				registeredNurses1,registeredNurses2,registeredNurses3,registeredNurses4,registeredNurses5,socialWorkers1,socialWorkers2,
//				socialWorkers3,socialWorkers4,socialWorkers5,activityTherapist1,activityTherapist2,activityTherapist3,activityTherapist4,
//				activityTherapist5,yogaTherapist1,yogaTherapist2,yogaTherapist3,yogaTherapist4,yogaTherapist5,mentalHealthWorkers1,
//				mentalHealthWorkers2,mentalHealthWorkers3,mentalHealthWorkers4,mentalHealthWorkers5,rROfficer1,rROfficer2,rROfficer3,
//				rROfficer4,rROfficer5,nurseManagers1,nurseManagers2,nurseManagers3,nurseManagers4,nurseManagers5,dirOfNursing1,
//				dirOfNursing2,dirOfNursing3,dirOfNursing4,dirOfNursing5,executives1,executives2,executives3,executives4,executives5,
//				HR1,HR2,HR3,HR4,HR5,QRDirector1,QRDirector2,QRDirector3,QRDirector4,QRDirector5,dirOfHIM1,dirOfHIM2,dirOfHIM3,dirOfHIM4,
//				dirOfHIM5,regDietitian1,regDietitian2,regDietitian3,regDietitian4,regDietitian5);
//	}
//
//	public void update (PatientStaffAssignment psAssign) {
//		this.setPID(psAssign.getPID());
//		this.setPsychiatrists1(psAssign.getPsychiatrists1());
//		this.setPsychiatrists2(psAssign.getPsychiatrists2());
//		this.setPsychiatrists3(psAssign.getPsychiatrists3());
//		this.setPsychiatrists4(psAssign.getPsychiatrists4());
//		this.setPsychiatrists5(psAssign.getPsychiatrists5());	
//		this.setMedicalDoctor1(psAssign.getMedicalDoctor1());
//		this.setMedicalDoctor2(psAssign.getMedicalDoctor2());
//		this.setMedicalDoctor3(psAssign.getMedicalDoctor3());
//		this.setMedicalDoctor4(psAssign.getMedicalDoctor4());
//		this.setMedicalDoctor5(psAssign.getMedicalDoctor5());		
//		this.setNursePractitioner1(psAssign.getNursePractitioner1());
//		this.setNursePractitioner2(psAssign.getNursePractitioner2());
//		this.setNursePractitioner3(psAssign.getNursePractitioner3());
//		this.setNursePractitioner4(psAssign.getNursePractitioner4());
//		this.setNursePractitioner5(psAssign.getNursePractitioner5());		
//		this.setPhysicianAssistant1(psAssign.getPhysicianAssistant1());
//		this.setPhysicianAssistant2(psAssign.getPhysicianAssistant2());
//		this.setPhysicianAssistant3(psAssign.getPhysicianAssistant3());
//		this.setPhysicianAssistant4(psAssign.getPhysicianAssistant4());
//		this.setPhysicianAssistant5(psAssign.getPhysicianAssistant5());		
//		this.setPsychologist1(psAssign.getPsychologist1());
//		this.setPsychologist2(psAssign.getPsychologist2());
//		this.setPsychologist3(psAssign.getPsychologist3());
//		this.setPsychologist4(psAssign.getPsychologist4());
//		this.setPsychologist5(psAssign.getPsychologist5());
//		this.setRegisteredNurses1(psAssign.getRegisteredNurses1());
//		this.setRegisteredNurses2(psAssign.getRegisteredNurses2());
//		this.setRegisteredNurses3(psAssign.getRegisteredNurses3());
//		this.setRegisteredNurses4(psAssign.getRegisteredNurses4());
//		this.setRegisteredNurses5(psAssign.getRegisteredNurses5());		
//		this.setSocialWorkers1(psAssign.getSocialWorkers1());
//		this.setSocialWorkers2(psAssign.getSocialWorkers2());
//		this.setSocialWorkers3(psAssign.getSocialWorkers3());
//		this.setSocialWorkers4(psAssign.getSocialWorkers4());
//		this.setSocialWorkers5(psAssign.getSocialWorkers5());		
//		this.setActivityTherapist1(psAssign.getActivityTherapist1());
//		this.setActivityTherapist2(psAssign.getActivityTherapist2());
//		this.setActivityTherapist3(psAssign.getActivityTherapist3());
//		this.setActivityTherapist4(psAssign.getActivityTherapist4());
//		this.setActivityTherapist5(psAssign.getActivityTherapist5());		
//		this.setYogaTherapist1(psAssign.getYogaTherapist1());
//		this.setYogaTherapist2(psAssign.getYogaTherapist2());
//		this.setYogaTherapist3(psAssign.getYogaTherapist3());
//		this.setYogaTherapist4(psAssign.getYogaTherapist4());
//		this.setYogaTherapist5(psAssign.getYogaTherapist5());
//		this.setMentalHealthWorkers1(psAssign.getMentalHealthWorkers1());
//		this.setMentalHealthWorkers2(psAssign.getMentalHealthWorkers2());
//		this.setMentalHealthWorkers3(psAssign.getMentalHealthWorkers3());
//		this.setMentalHealthWorkers4(psAssign.getMentalHealthWorkers4());
//		this.setMentalHealthWorkers5(psAssign.getMentalHealthWorkers5());
//		this.setrROfficer1(psAssign.getrROfficer1());
//		this.setrROfficer2(psAssign.getrROfficer2());
//		this.setrROfficer3(psAssign.getrROfficer3());
//		this.setrROfficer4(psAssign.getrROfficer4());
//		this.setrROfficer5(psAssign.getrROfficer5());
//		this.setNurseManagers1(psAssign.getNurseManagers1());
//		this.setNurseManagers2(psAssign.getNurseManagers2());
//		this.setNurseManagers3(psAssign.getNurseManagers3());
//		this.setNurseManagers4(psAssign.getNurseManagers4());
//		this.setNurseManagers5(psAssign.getNurseManagers5());
//		this.setDirOfNursing1(psAssign.getDirOfNursing1());
//		this.setDirOfNursing2(psAssign.getDirOfNursing2());
//		this.setDirOfNursing3(psAssign.getDirOfNursing3());
//		this.setDirOfNursing4(psAssign.getDirOfNursing4());
//		this.setDirOfNursing5(psAssign.getDirOfNursing5());
//		this.setExecutives1(psAssign.getExecutives1());
//		this.setExecutives2(psAssign.getExecutives2());
//		this.setExecutives3(psAssign.getExecutives3());
//		this.setExecutives4(psAssign.getExecutives4());
//		this.setExecutives5(psAssign.getExecutives5());
//		this.setHR1(psAssign.getHR1());
//		this.setHR2(psAssign.getHR2());
//		this.setHR3(psAssign.getHR3());
//		this.setHR4(psAssign.getHR4());
//		this.setHR5(psAssign.getHR5());
//		this.setQRDirector1(psAssign.getQRDirector1());
//		this.setQRDirector2(psAssign.getQRDirector2());
//		this.setQRDirector3(psAssign.getQRDirector3());
//		this.setQRDirector4(psAssign.getQRDirector4());
//		this.setQRDirector5(psAssign.getQRDirector5());
//		this.setDirOfHIM1(psAssign.getDirOfHIM1());
//		this.setDirOfHIM2(psAssign.getDirOfHIM2());
//		this.setDirOfHIM3(psAssign.getDirOfHIM3());
//		this.setDirOfHIM4(psAssign.getDirOfHIM4());
//		this.setDirOfHIM5(psAssign.getDirOfHIM5());
//		this.setRegDietitian1(psAssign.getRegDietitian1());
//		this.setRegDietitian2(psAssign.getRegDietitian2());
//		this.setRegDietitian3(psAssign.getRegDietitian3());
//		this.setRegDietitian4(psAssign.getRegDietitian4());
//		this.setRegDietitian5(psAssign.getRegDietitian5());
//		
//	}
//}
