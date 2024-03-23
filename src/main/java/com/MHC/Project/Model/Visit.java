package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "visit")
public class Visit {
	
	@Id
	private String id;
	private String visitStartDate;
	private String visitEndDate;
	private String visitReason;
	private String visitType;
	private String status;
	private String location;
	private String admitDate;
	private String dischargeDate;
	private String speciality;
	private String refferingPhysycian;
	private String refferingHospital;
	private boolean schedule;
	private String lastVisit;
	private String pid;
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
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisitStartDate() {
		return visitStartDate;
	}
	public void setVisitStartDate(String visitStartDate) {
		this.visitStartDate = visitStartDate;
	}
	public String getVisitEndDate() {
		return visitEndDate;
	}
	public void setVisitEndDate(String visitEndDate) {
		this.visitEndDate = visitEndDate;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getRefferingPhysycian() {
		return refferingPhysycian;
	}
	public void setRefferingPhysycian(String refferingPhysycian) {
		this.refferingPhysycian = refferingPhysycian;
	}
	public boolean isSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
	
	public void update(Visit visit) {
		
		this.setVisitStartDate(visit.getVisitStartDate());
		this.setVisitEndDate(visit.getVisitEndDate());
		this.setVisitReason(visit.getVisitReason());
		this.setVisitType(visit.getVisitType());
		this.setStatus(visit.getStatus());
		this.setLocation(visit.getLocation());
		this.setAdmitDate(visit.getAdmitDate());
		this.setDischargeDate(visit.getDischargeDate());
		this.setSpeciality(visit.getSpeciality());
		this.setRefferingPhysycian(visit.getRefferingPhysycian());
		this.setSchedule(visit.isSchedule());
		this.setLastVisit(visit.getLastVisit());
		this.setPid(visit.getPid());
		this.setOrganization(visit.getOrganization());
		
	}
	public String getRefferingHospital() {
		return refferingHospital;
	}
	public void setRefferingHospital(String refferingHospital) {
		this.refferingHospital = refferingHospital;
	}

}