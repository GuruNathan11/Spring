package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "procedure")
public class OrderProcedure {
	
	@Id
	private String id;
	private String pid;
	private String lastVisit;
	private String procedure;
	private String urgency;
	private String serviceProblem;
	private String appropriateDate;
	private boolean observed;
	private String consultation;
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
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
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
	public String getConsultation() {
		return consultation;
	}
	public void setConsultation(String consultation) {
		this.consultation = consultation;
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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public void update(OrderProcedure orderProcedure) {
		
		this.setPid(orderProcedure.getPid());
		this.setLastVisit(orderProcedure.getLastVisit());
		this.setProcedure(orderProcedure.getProcedure());
		this.setUrgency(orderProcedure.getUrgency());
		this.setServiceProblem(orderProcedure.getServiceProblem());
		this.setAppropriateDate(orderProcedure.getAppropriateDate());
		this.setObserved(orderProcedure.isObserved());
		this.setConsultation(orderProcedure.getConsultation());
		this.setProvisionalDiagnosis(orderProcedure.getProvisionalDiagnosis());
		this.setOrderedBy(orderProcedure.getOrderedBy());
		this.setEnteredBy(orderProcedure.getEnteredBy());
		this.setComments(orderProcedure.getComments());
		
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

}
