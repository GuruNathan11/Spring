package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "BedMaster")
public class BedMasterConfig {
    @Id
    private String id;
    private String floorNo;
    private String wing;
    private String wardName;
    private String side;
    private String roomNo;
    private String bedType;
    private String bedNo;
    private String bedFeatures;
    private String supervisionLevel;
    private String securityMeasures;
    private String position;
    private boolean occupied;
    private String organization;
    private boolean genderRestriction;
    private boolean unavailable = false;

	public boolean getGenderRestriction() {
		return genderRestriction;
	}
	public void setGenderRestriction(boolean genderRestriction) {
		this.genderRestriction = genderRestriction;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getWing() {
		return wing;
	}
	public void setWing(String wing) {
		this.wing = wing;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}
	public String getBedFeatures() {
		return bedFeatures;
	}
	public void setBedFeatures(String bedFeatures) {
		this.bedFeatures = bedFeatures;
	}
	public String getSupervisionLevel() {
		return supervisionLevel;
	}
	public void setSupervisionLevel(String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}
	public String getSecurityMeasures() {
		return securityMeasures;
	}
	public void setSecurityMeasures(String securityMeasures) {
		this.securityMeasures = securityMeasures;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public boolean isUnavailable() {
		return unavailable;
	}
	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
	}
}
