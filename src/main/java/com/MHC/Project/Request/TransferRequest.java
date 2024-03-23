package com.MHC.Project.Request;

public class TransferRequest {
	
	private String pid;
	private String admitId;
	private String wardLocation;
	private String bedId;
	private String typeOfTransfer;
	private String transferedDate;
	private String transferedBy;
	private String briefDescriptionTransfer;
	private String attendingPhysician1;
	private String primaryPhysician1;
	private String facilityTreatingSpeciality1;
	private String lastVisit;
	
	public String getWardLocation() {
		return wardLocation;
	}
	public void setWardLocation(String wardLocation) {
		this.wardLocation = wardLocation;
	}
	public String getTypeOfTransfer() {
		return typeOfTransfer;
	}
	public void setTypeOfTransfer(String typeOfTransfer) {
		this.typeOfTransfer = typeOfTransfer;
	}
	public String getTransferedDate() {
		return transferedDate;
	}
	public void setTransferedDate(String transferedDate) {
		this.transferedDate = transferedDate;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAdmitId() {
		return admitId;
	}
	public void setAdmitId(String admitId) {
		this.admitId = admitId;
	}
	public String getTransferedBy() {
		return transferedBy;
	}
	public void setTransferedBy(String transferedBy) {
		this.transferedBy = transferedBy;
	}
	public String getAttendingPhysician1() {
		return attendingPhysician1;
	}
	public void setAttendingPhysician1(String attendingPhysician1) {
		this.attendingPhysician1 = attendingPhysician1;
	}
	public String getPrimaryPhysician1() {
		return primaryPhysician1;
	}
	public void setPrimaryPhysician1(String primaryPhysician1) {
		this.primaryPhysician1 = primaryPhysician1;
	}
	public String getFacilityTreatingSpeciality1() {
		return facilityTreatingSpeciality1;
	}
	public void setFacilityTreatingSpeciality1(String facilityTreatingSpeciality1) {
		this.facilityTreatingSpeciality1 = facilityTreatingSpeciality1;
	}
	public String getBriefDescriptionTransfer() {
		return briefDescriptionTransfer;
	}
	public void setBriefDescriptionTransfer(String briefDescriptionTransfer) {
		this.briefDescriptionTransfer = briefDescriptionTransfer;
	}
	public String getBedId() {
		return bedId;
	}
	public void setBedId(String bedId) {
		this.bedId = bedId;
	}
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}	

}
