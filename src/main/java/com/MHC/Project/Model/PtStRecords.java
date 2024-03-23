package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "Pt-St-Record")
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PtStRecords {

	@Id
	private String id;
	private String PID[];
	private String psychiatrists[];
	private String medicalDoctor[];
	private String nursePractitioner[];
	private String physicianAssistant[];
	private String psychologist[];
	private String registeredNurses[];
	private String socialWorkers[];
	private String activityTherapist[];
	private String yogaTherapist[];
	private String mentalHealthWorkers[];
	private String rROfficer[];
	private String nurseManagers[];
	private String dirOfNursing[];
	private String executives[];
	private String HR[];
	private String QRDirector[];
	private String dirOfHIM[];
	private String regDietitian[];
	private String createdAt;
	private String updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getPsychiatrists() {
		return psychiatrists;
	}

	public void setPsychiatrists(String[] psychiatrists) {
		this.psychiatrists = psychiatrists;
	}

	public String[] getMedicalDoctor() {
		return medicalDoctor;
	}

	public void setMedicalDoctor(String[] medicalDoctor) {
		this.medicalDoctor = medicalDoctor;
	}

	public String[] getNursePractitioner() {
		return nursePractitioner;
	}

	public void setNursePractitioner(String[] nursePractitioner) {
		this.nursePractitioner = nursePractitioner;
	}

	public String[] getPhysicianAssistant() {
		return physicianAssistant;
	}

	public void setPhysicianAssistant(String[] physicianAssistant) {
		this.physicianAssistant = physicianAssistant;
	}

	public String[] getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(String[] psychologist) {
		this.psychologist = psychologist;
	}

	public String[] getRegisteredNurses() {
		return registeredNurses;
	}

	public void setRegisteredNurses(String[] registeredNurses) {
		this.registeredNurses = registeredNurses;
	}

	public String[] getSocialWorkers() {
		return socialWorkers;
	}

	public void setSocialWorkers(String[] socialWorkers) {
		this.socialWorkers = socialWorkers;
	}

	public String[] getActivityTherapist() {
		return activityTherapist;
	}

	public void setActivityTherapist(String[] activityTherapist) {
		this.activityTherapist = activityTherapist;
	}

	public String[] getYogaTherapist() {
		return yogaTherapist;
	}

	public void setYogaTherapist(String[] yogaTherapist) {
		this.yogaTherapist = yogaTherapist;
	}

	public String[] getMentalHealthWorkers() {
		return mentalHealthWorkers;
	}

	public void setMentalHealthWorkers(String[] mentalHealthWorkers) {
		this.mentalHealthWorkers = mentalHealthWorkers;
	}

	public String[] getrROfficer() {
		return rROfficer;
	}

	public void setrROfficer(String[] rROfficer) {
		this.rROfficer = rROfficer;
	}

	public String[] getNurseManagers() {
		return nurseManagers;
	}

	public void setNurseManagers(String[] nurseManagers) {
		this.nurseManagers = nurseManagers;
	}

	public String[] getDirOfNursing() {
		return dirOfNursing;
	}

	public void setDirOfNursing(String[] dirOfNursing) {
		this.dirOfNursing = dirOfNursing;
	}

	public String[] getExecutives() {
		return executives;
	}

	public void setExecutives(String[] executives) {
		this.executives = executives;
	}

	public String[] getHR() {
		return HR;
	}

	public void setHR(String[] hR) {
		this.HR = hR;
	}

	public String[] getQRDirector() {
		return QRDirector;
	}

	public void setQRDirector(String[] qRDirector) {
		this.QRDirector = qRDirector;
	}

	public String[] getDirOfHIM() {
		return dirOfHIM;
	}

	public void setDirOfHIM(String[] dirOfHIM) {
		this.dirOfHIM = dirOfHIM;
	}

	public String[] getRegDietitian() {
		return regDietitian;
	}

	public void setRegDietitian(String[] regDietitian) {
		this.regDietitian = regDietitian;
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

	public String[] getPID() {
		return PID;
	}

	public void setPID(String[] pID) {
		this.PID = pID;
	}
	
	
	
}