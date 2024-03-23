package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "imaging-procedure")
public class OrderImagingProcedure {
	
	@Id
	private String id;
	private String pid;
	private String lastVisit;
	private String imagingType;
	private String reasonForStudy;
	private String modifiers;
	private String dateDesired;
	private String clinicalHistory;
	private String urgency;
	private String transport;
	private String category;
	private String submitTo;
	private String preOpScheduled;
	private String examsOver;
	private String orderedBy;
	private String enteredBy;
	private boolean isolation;
	private String pragnant;
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
	public String getImagingType() {
		return imagingType;
	}
	public void setImagingType(String imagingType) {
		this.imagingType = imagingType;
	}
	public String getReasonForStudy() {
		return reasonForStudy;
	}
	public void setReasonForStudy(String reasonForStudy) {
		this.reasonForStudy = reasonForStudy;
	}
	public String getModifiers() {
		return modifiers;
	}
	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}
	public String getDateDesired() {
		return dateDesired;
	}
	public void setDateDesired(String dateDesired) {
		this.dateDesired = dateDesired;
	}
	public String getClinicalHistory() {
		return clinicalHistory;
	}
	public void setClinicalHistory(String clinicalHistory) {
		this.clinicalHistory = clinicalHistory;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubmitTo() {
		return submitTo;
	}
	public void setSubmitTo(String submitTo) {
		this.submitTo = submitTo;
	}
	public String getPreOpScheduled() {
		return preOpScheduled;
	}
	public void setPreOpScheduled(String preOpScheduled) {
		this.preOpScheduled = preOpScheduled;
	}
	public String getExamsOver() {
		return examsOver;
	}
	public void setExamsOver(String examsOver) {
		this.examsOver = examsOver;
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
	public boolean isIsolation() {
		return isolation;
	}
	public void setIsolation(boolean isolation) {
		this.isolation = isolation;
	}
	public String getPragnant() {
		return pragnant;
	}
	public void setPragnant(String pragnant) {
		this.pragnant = pragnant;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public void update(OrderImagingProcedure imagingProcedure) {
		
		this.setPid(imagingProcedure.getPid());
		this.setLastVisit(imagingProcedure.getLastVisit());
		this.setImagingType(imagingProcedure.getImagingType());
		this.setReasonForStudy(imagingProcedure.getReasonForStudy());
		this.setModifiers(imagingProcedure.getModifiers());
		this.setDateDesired(imagingProcedure.getDateDesired());
		this.setClinicalHistory(imagingProcedure.getClinicalHistory());
		this.setUrgency(imagingProcedure.getUrgency());
		this.setTransport(imagingProcedure.getTransport());
		this.setCategory(imagingProcedure.getCategory());
		this.setSubmitTo(imagingProcedure.getSubmitTo());
		this.setPreOpScheduled(imagingProcedure.getPreOpScheduled());
		this.setExamsOver(imagingProcedure.getExamsOver());
		this.setOrderedBy(imagingProcedure.getOrderedBy());
		this.setEnteredBy(imagingProcedure.getEnteredBy());
		this.setIsolation(imagingProcedure.isIsolation());
		this.setPragnant(imagingProcedure.getPragnant());
		
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
