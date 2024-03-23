package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Immunization")
public class Immunization {

	@Id
	private String id;
	private String patientId;
	private String immunization;
	private String lotNo;
	private String dueDate;
	private String doneDate;
	private String administrationDate;
	private String administeredBy;
	private String orderedBy;
	private String route;
	private String anatomicLocation;
	private String series;
	private String dosage;
	private boolean administeringByPolicy;
	private boolean includeNonVAProviders;
	private String comments;
	private String lastVisit;
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
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getImmunization() {
		return immunization;
	}
	public void setImmunization(String immunization) {
		this.immunization = immunization;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDoneDate() {
		return doneDate;
	}
	public void setDoneDate(String doneDate) {
		this.doneDate = doneDate;
	}
	public String getAdministrationDate() {
		return administrationDate;
	}
	public void setAdministrationDate(String administrationDate) {
		this.administrationDate = administrationDate;
	}
	public String getAdministeredBy() {
		return administeredBy;
	}
	public void setAdministeredBy(String administeredBy) {
		this.administeredBy = administeredBy;
	}
	public String getOrderedBy() {
		return orderedBy;
	}
	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getAnatomicLocation() {
		return anatomicLocation;
	}
	public void setAnatomicLocation(String anatomicLocation) {
		this.anatomicLocation = anatomicLocation;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public boolean isAdministeringByPolicy() {
		return administeringByPolicy;
	}
	public void setAdministeringByPolicy(boolean administeringByPolicy) {
		this.administeringByPolicy = administeringByPolicy;
	}
	public boolean isIncludeNonVAProviders() {
		return includeNonVAProviders;
	}
	public void setIncludeNonVAProviders(boolean includeNonVAProviders) {
		this.includeNonVAProviders = includeNonVAProviders;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Immunization(String id, String patientId,String immunization, String lotNo, String dueDate, String doneDate,
			String administrationDate, String administeredBy, String orderedBy, String route, String anatomicLocation,
			String series, String dosage, boolean administeringByPolicy, boolean includeNonVAProviders,
			String comments,String lastVisit, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.patientId=patientId;
		this.immunization = immunization;
		this.lotNo = lotNo;
		this.dueDate = dueDate;
		this.doneDate = doneDate;
		this.administrationDate = administrationDate;
		this.administeredBy = administeredBy;
		this.orderedBy = orderedBy;
		this.route = route;
		this.anatomicLocation = anatomicLocation;
		this.series = series;
		this.dosage = dosage;
		this.administeringByPolicy = administeringByPolicy;
		this.includeNonVAProviders = includeNonVAProviders;
		this.comments = comments;
		this.lastVisit=lastVisit;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
	}
	
	
	public Immunization build(String id,String patientId, String immunization, String lotNo, String dueDate, String doneDate,
			String administrationDate, String administeredBy, String orderedBy, String route, String anatomicLocation,
			String series, String dosage, boolean administeringByPolicy, boolean includeNonVAProviders,
			String comments,String lastVisit, String createdAt, String updatedAt) {
		
		return new Immunization(id,patientId,immunization,lotNo,dueDate,doneDate,administrationDate,administeredBy,orderedBy,route,
				anatomicLocation,series,dosage,administeringByPolicy,includeNonVAProviders,comments,lastVisit, createdAt, updatedAt);
	}
	
	public void update(Immunization immunization) {
		
		this.setPatientId(immunization.getPatientId());
		this.setImmunization(immunization.getImmunization());
		this.setLotNo(immunization.getLotNo());
		this.setDueDate(immunization.getDueDate());
		this.setDoneDate(immunization.getDoneDate());
		this.setAdministrationDate(immunization.getAdministrationDate());
		this.setAdministeredBy(immunization.getAdministeredBy());
		this.setOrderedBy(immunization.getOrderedBy());
		this.setRoute(immunization.getRoute());
		this.setAnatomicLocation(immunization.getAnatomicLocation());
		this.setSeries(immunization.getSeries());
		this.setDosage(immunization.getDosage());
		this.setAdministeringByPolicy(immunization.isAdministeringByPolicy());
		this.setIncludeNonVAProviders(immunization.isIncludeNonVAProviders());
		this.setComments(immunization.getComments());
		this.setLastVisit(immunization.getLastVisit());
	}
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	
}