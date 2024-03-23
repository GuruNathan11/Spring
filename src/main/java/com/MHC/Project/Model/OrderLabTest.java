package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "labTest")
public class OrderLabTest {
	
	@Id
	private String id;
	private String pid;
	private String lastVisit;
	private String[] labTest;
	private String collectionType;
	private String collectionDateTime;
	private String collectionSample;
	private String specimen;
	private String urgency;
	private String howOften;
	private String howLong;
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
	public String[] getLabTest() {
		return labTest;
	}
	public void setLabTest(String[] labTest) {
		this.labTest = labTest;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public String getCollectionDateTime() {
		return collectionDateTime;
	}
	public void setCollectionDateTime(String collectionDateTime) {
		this.collectionDateTime = collectionDateTime;
	}
	public String getCollectionSample() {
		return collectionSample;
	}
	public void setCollectionSample(String collectionSample) {
		this.collectionSample = collectionSample;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getHowOften() {
		return howOften;
	}
	public void setHowOften(String howOften) {
		this.howOften = howOften;
	}
	public String getHowLong() {
		return howLong;
	}
	public void setHowLong(String howLong) {
		this.howLong = howLong;
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
	
	public void update(OrderLabTest labTest) {
		
		this.setPid(labTest.getPid());
		this.setLastVisit(labTest.getLastVisit());
		this.setLabTest(labTest.getLabTest());
		this.setCollectionType(labTest.getCollectionType());
		this.setCollectionDateTime(labTest.getCollectionDateTime());
		this.setCollectionSample(labTest.getCollectionSample());
		this.setSpecimen(labTest.getSpecimen());
		this.setUrgency(labTest.getUrgency());
		this.setHowOften(labTest.getHowOften());
		this.setHowLong(labTest.getHowLong());
		this.setOrderedBy(labTest.getOrderedBy());
		this.setEnteredBy(labTest.getEnteredBy());
		this.setComments(labTest.getComments()); 
		
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
