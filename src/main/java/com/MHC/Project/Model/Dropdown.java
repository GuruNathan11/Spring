package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "dropdowns")
public class Dropdown {
    @Id
    private String id;
    private List[] gender;
    private List[] language;
    private List[] industry;
    private List[] relationShip;
    private List[] occupation;
    private List[] state;
    private List[] country;
    private List[] ethnicity;
    private List[] insuranceProvider;
    private List[] religion;
    private List[] referralSource;
    private List[] race;
    private List[] roles;
    private List[] floorNo;
    private List[] wing;
    private List[] wardName;
    private List[] side;
    private List[] roomNo;
    private List[] bedType;
    private List[] bedNo;
    private List[] supervisionLevel;
    private List[] position;
    private List[] cuffsize;
	private List[] location;
	private List[] method;
	private List[] patientPosition;
	private List[] quality;
	private List[] site;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List[] getGender() {
		return gender;
	}
	public void setGender(List[] gender) {
		this.gender = gender;
	}
	public List[] getLanguage() {
		return language;
	}
	public void setLanguage(List[] language) {
		this.language = language;
	}
	public List[] getIndustry() {
		return industry;
	}
	public void setIndustry(List[] industry) {
		this.industry = industry;
	}
	public List[] getRelationShip() {
		return relationShip;
	}
	public void setRelationShip(List[] relationShip) {
		this.relationShip = relationShip;
	}
	public List[] getOccupation() {
		return occupation;
	}
	public void setOccupation(List[] occupation) {
		this.occupation = occupation;
	}
	public List[] getState() {
		return state;
	}
	public void setState(List[] state) {
		this.state = state;
	}
	public List[] getCountry() {
		return country;
	}
	public void setCountry(List[] country) {
		this.country = country;
	}
	public List[] getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(List[] ethnicity) {
		this.ethnicity = ethnicity;
	}
	public List[] getInsuranceProvider() {
		return insuranceProvider;
	}
	public void setInsuranceProvider(List[] insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	public List[] getReligion() {
		return religion;
	}
	public void setReligion(List[] religion) {
		this.religion = religion;
	}
	public List[] getReferralSource() {
		return referralSource;
	}
	public void setReferralSource(List[] referralSource) {
		this.referralSource = referralSource;
	}
	public List[] getRace() {
		return race;
	}
	public void setRace(List[] race) {
		this.race = race;
	}
	
	public List[] getRoles() {
		return roles;
	}
	public void setRoles(List[] roles) {
		this.roles = roles;
	}
	public List[] getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(List[] floorNo) {
		this.floorNo = floorNo;
	}
	public List[] getWing() {
		return wing;
	}
	public void setWing(List[] wing) {
		this.wing = wing;
	}
	public List[] getWardName() {
		return wardName;
	}
	public void setWardName(List[] wardName) {
		this.wardName = wardName;
	}
	public List[] getSide() {
		return side;
	}
	public void setSide(List[] side) {
		this.side = side;
	}
	public List[] getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(List[] roomNo) {
		this.roomNo = roomNo;
	}
	public List[] getBedType() {
		return bedType;
	}
	public void setBedType(List[] bedType) {
		this.bedType = bedType;
	}
	public List[] getBedNo() {
		return bedNo;
	}
	public void setBedNo(List[] bedNo) {
		this.bedNo = bedNo;
	}
	public List[] getSupervisionLevel() {
		return supervisionLevel;
	}
	public void setSupervisionLevel(List[] supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}
	public List[] getPosition() {
		return position;
	}
	public void setPosition(List[] position) {
		this.position = position;
	}
	public List[] getCuffsize() {
		return cuffsize;
	}
	public void setCuffsize(List[] cuffsize) {
		this.cuffsize = cuffsize;
	}
	public List[] getLocation() {
		return location;
	}
	public void setLocation(List[] location) {
		this.location = location;
	}
	public List[] getMethod() {
		return method;
	}
	public void setMethod(List[] method) {
		this.method = method;
	}
	public List[] getPatientPosition() {
		return patientPosition;
	}
	public void setPatientPosition(List[] patientPosition) {
		this.patientPosition = patientPosition;
	}
	public List[] getQuality() {
		return quality;
	}
	public void setQuality(List[] quality) {
		this.quality = quality;
	}
	public List[] getSite() {
		return site;
	}
	public void setSite(List[] site) {
		this.site = site;
	}

	public static class List {
	    private String id;
	    private String value;
	    
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
	    public String getValue() {
	        return value;
	    }
	    public void setValue(String value) {
	        this.value = value;
	    }	 
	}
}