package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "consult")
public class OrderConsult {
	
	@Id
	private String id;
	private String pid;
	private String lastVisit;
	private String speciality;
	private String urgency;
	private String serviceProblem;
	private String appropriateDate;
	private boolean observed;
	private String placeOfConsultation;
	private String provisionalDiagnosis;
	private String orderedBy;
	private String enteredBy;
	private String comments;
	private String organization;
	private String createdAt;
	private String updatedAt;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getServiceProblem() {
		return serviceProblem;
	}
	public void setServiceProblem(String serviceProblem) {
		this.serviceProblem = serviceProblem;
	}
	public String getAppropriateDate() {
		return appropriateDate;
	}
	public void setAppropriateDate(String appropriateDate) {
		this.appropriateDate = appropriateDate;
	}
	public boolean isObserved() {
		return observed;
	}
	public void setObserved(boolean observed) {
		this.observed = observed;
	}
	public String getPlaceOfConsultation() {
		return placeOfConsultation;
	}
	public void setPlaceOfConsultation(String placeOfConsultation) {
		this.placeOfConsultation = placeOfConsultation;
	}
	public String getProvisionalDiagnosis() {
		return provisionalDiagnosis;
	}
	public void setProvisionalDiagnosis(String provisionalDiagnosis) {
		this.provisionalDiagnosis = provisionalDiagnosis;
	}
	public String getOrderedBy() {
		return orderedBy;
	}
	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void update(OrderConsult consult) {
		
		this.setSpeciality(consult.getSpeciality());
		this.setPid(consult.getPid());
		this.setLastVisit(consult.getLastVisit());
		this.setUrgency(consult.getUrgency());
		this.setServiceProblem(consult.getServiceProblem());
		this.setAppropriateDate(consult.getAppropriateDate());
		this.setObserved(consult.isObserved());
		this.setPlaceOfConsultation(consult.getPlaceOfConsultation());
		this.setProvisionalDiagnosis(consult.getProvisionalDiagnosis());
		this.setOrderedBy(consult.getOrderedBy());
		this.setEnteredBy(consult.getEnteredBy());
		this.setComments(consult.getComments());
		
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	

}
