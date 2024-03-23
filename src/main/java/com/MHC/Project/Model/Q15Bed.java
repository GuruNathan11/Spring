package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "Q15Bed")
public class Q15Bed {

	@Id
	private String id;
	private String roomNo;
//	private String odd;
//	private String even;
	private String bedNo;
	private String pid;
	private String AdmitDate;
	private boolean occupied;
	private String bedAssignDateTime;
	private String assignedBy;
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
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
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
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public String getBedAssignDateTime() {
		return bedAssignDateTime;
	}
	public void setBedAssignDateTime(String bedAssignDateTime) {
		this.bedAssignDateTime = bedAssignDateTime;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
//	public String getOdd() {
//		return odd;
//	}
//	public void setOdd(String odd) {
//		this.odd = odd;
//	}
//	public String getEven() {
//		return even;
//	}
//	public void setEven(String even) {
//		this.even = even;
//	}
}
