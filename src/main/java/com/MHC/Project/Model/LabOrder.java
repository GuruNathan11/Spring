package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection="Lab")
public class LabOrder {
	
	@Id
	private String id;  
	private String patientId;
	private String[] labTestName;
	private String enteredDate;
	private String enteredType;
	private String enteredBy;
	private String orderedBy;
	private String[] collectionSample;
	private String[] specimen;
	private String[] collectionType;
	private String collectionDateTime;
	private String startDate;
	private String stopDate;
	private String[] urgency;
	private String howoften;
	private String howlong;
	private String hospitalLocation;
	private String respProvider;
	private String lastVisit;
	private String comments;
	
	//Getters and setters //
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
	public String[] getLabTestName() {
		return labTestName;
	}
	public void setLabTestName(String[] labTestName) {
		this.labTestName = labTestName;
	}
	public String getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(String enteredDate) {
		this.enteredDate = enteredDate;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public String getEnteredType() {
		return enteredType;
	}
	public void setEnteredType(String enteredType) {
		this.enteredType = enteredType;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	public String getOrderedBy() {
		return orderedBy;
	}
	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}
	public String[] getCollectionSample() {
		return collectionSample;
	}
	public void setCollectionSample(String[] collectionSample) {
		this.collectionSample = collectionSample;
	}
	public String[] getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String[] specimen) {
		this.specimen = specimen;
	}
	public String[] getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String[] collectionType) {
		this.collectionType = collectionType;
	}
	public String getCollectionDateTime() {
		return collectionDateTime;
	}
	public void setCollectionDateTime(String collectionDateTime) {
		this.collectionDateTime = collectionDateTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStopDate() {
		return stopDate;
	}
	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}
	public String[] getUrgency() {
		return urgency;
	}
	public void setUrgency(String[] urgency) {
		this.urgency = urgency;
	}
	public String getHowoften() {
		return howoften;
	}
	public void setHowoften(String howoften) {
		this.howoften = howoften;
	}
	public String getHowlong() {
		return howlong;
	}
	public void setHowlong(String howlong) {
		this.howlong = howlong;
	}
	public String getHospitalLocation() {
		return hospitalLocation;
	}
	public void setHospitalLocation(String hospitalLocation) {
		this.hospitalLocation = hospitalLocation;
	}
	public String getRespProvider() {
		return respProvider;
	}
	public void setRespProvider(String respProvider) {
		this.respProvider = respProvider;
	}
	
	// Constructor Method//
	public LabOrder(String id,String patientId,String[] labTestName,String enteredDate,String enteredType,
			String enteredBy,String orderedBy,String[] collectionSample,String[] specimen,String[] collectionType,String collectionDateTime,
			String startDate,String stopDate,String[] urgency,String howoften,String howlong,String hospitalLocation,
			String respProvider,String lastVisit,String comments) {
	    this.id=id;
	    this.patientId=patientId;
	    this.labTestName=labTestName;
	    this.enteredDate=enteredDate;
	    this.enteredType=enteredType;
	    this.enteredBy=enteredBy;
	    this.orderedBy=orderedBy;
	    this.collectionSample=collectionSample;
	    this.specimen=specimen;
	    this.collectionType=collectionType;
	    this.collectionDateTime=collectionDateTime;
	    this.startDate=startDate;
	    this.stopDate=stopDate;
	    this.urgency=urgency;
	    this.howoften=howoften;
	    this.howlong=howlong;
	    this.hospitalLocation=hospitalLocation;
	    this.respProvider=respProvider;
	    this.lastVisit=lastVisit;
	    this.comments=comments;
	}
	public static LabOrder build(String id,String patientId,String[] labTestName,String enteredDate,String enteredType,
			String enteredBy,String orderedBy,String[] collectionSample,String[] specimen,String[] collectionType,String collectionDateTime,
			String startDate,String stopDate,String[] urgency,String howoften,String howlong,String hospitalLocation,
			String respProvider,String lastVisit,String comments) {
	    return new LabOrder(id,patientId,labTestName,enteredDate,enteredBy,enteredType,orderedBy,collectionSample,specimen,
	    		collectionType,collectionDateTime,startDate,stopDate,urgency,howoften,howlong,hospitalLocation,
	    		respProvider,lastVisit,comments);
	}
	public void update(LabOrder laborder) {
		this.setPatientId(laborder.getPatientId());
		this.setLabTestName(laborder.getLabTestName());
		this.setEnteredDate(laborder.getEnteredDate());
		this.setEnteredType(laborder.getEnteredType());
		this.setEnteredBy(laborder.getEnteredBy());
		this.setOrderedBy(laborder.getOrderedBy());
		this.setCollectionSample(laborder.getCollectionSample());
		this.setSpecimen(laborder.getSpecimen());
		this.setCollectionType(laborder.getCollectionType());
		this.setCollectionDateTime(laborder.getCollectionDateTime());
		this.setStartDate(laborder.getStartDate());
		this.setStopDate(laborder.getStopDate());
		this.setUrgency(laborder.getUrgency());
		this.setHowoften(laborder.getHowoften());
		this.setHowlong(laborder.getHowlong());
		this.setHospitalLocation(laborder.getHospitalLocation());
		this.setRespProvider(laborder.getRespProvider());
		this.setLastVisit(laborder.getLastVisit());
		this.setComments(laborder.getComments());
	}
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}