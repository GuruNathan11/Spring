package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "Beds")
public class Bed {

	@Id
	private String id;
	private String bedId;
	private String pid;
	private String AdmitDate;
	private String wardBedInfo;
	private boolean occupied;
	private boolean genderRestriction;
	private String bedAssignDateTime;
	private String dischargeDateTime;
	private String assignedBy;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBedId() {
		return bedId;
	}
	public void setBedId(String bedId) {
		this.bedId = bedId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAdmitDate() {
		return AdmitDate;
	}
	public void setAdmitDate(String admitDate) {
		AdmitDate = admitDate;
	}
	public String getWardBedInfo() {
		return wardBedInfo;
	}
	public void setWardBedInfo(String wardBedInfo) {
		this.wardBedInfo = wardBedInfo;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public boolean isGenderRestriction() {
		return genderRestriction;
	}
	public void setGenderRestriction(boolean genderRestriction) {
		this.genderRestriction = genderRestriction;
	}
	public String getBedAssignDateTime() {
		return bedAssignDateTime;
	}
	public void setBedAssignDateTime(String bedAssignDateTime) {
		this.bedAssignDateTime = bedAssignDateTime;
	}
	public String getDischargeDateTime() {
		return dischargeDateTime;
	}
	public void setDischargeDateTime(String dischargeDateTime) {
		this.dischargeDateTime = dischargeDateTime;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	
}