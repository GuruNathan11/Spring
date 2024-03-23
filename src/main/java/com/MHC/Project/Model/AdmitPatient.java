package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Admit-List")
public class AdmitPatient {
	
	private String id;
	private String pid;
	private String admitId;
	private String admissionDate;
	private String dischargeDate;
	private String transferedDate;
	private String admissionType; 
	private String typeOfTransfer;
	private String transferedBy;
	private String dischargeType;
	private String dischargeBy;
	private String attendingPhysician;
	private String primaryPhysician;
	private String facilityTreatingSpeciality;
	private String attendingPhysician1;
	private String primaryPhysician1;
	private String facilityTreatingSpeciality1;
	private String sourceOfAdmission;
	private String wardLocation;
	private String bedId;
	private String briefDescription;
	private String briefDescriptionTransfer;
	private String briefDescriptionDischarge;
	private String status;
	private boolean trackingDevice;
	private String deviceId;
	private String lastVisit;
	private String referringHospital;
	
	public String getReferringHospital() {
		return referringHospital;
	}
	public void setReferringHospital(String referringHospital) {
		this.referringHospital = referringHospital;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getAdmissionType() {
		return admissionType;
	}
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}
	public String getAttendingPhysician() {
		return attendingPhysician;
	}
	public void setAttendingPhysician(String attendingPhysician) {
		this.attendingPhysician = attendingPhysician;
	}
	public String getPrimaryPhysician() {
		return primaryPhysician;
	}
	public void setPrimaryPhysician(String primaryPhysician) {
		this.primaryPhysician = primaryPhysician;
	}
	public String getFacilityTreatingSpeciality() {
		return facilityTreatingSpeciality;
	}
	public void setFacilityTreatingSpeciality(String facilityTreatingSpeciality) {
		this.facilityTreatingSpeciality = facilityTreatingSpeciality;
	}
	public String getSourceOfAdmission() {
		return sourceOfAdmission;
	}
	public void setSourceOfAdmission(String sourceOfAdmission) {
		this.sourceOfAdmission = sourceOfAdmission;
	}
	public String getWardLocation() {
		return wardLocation;
	}
	public void setWardLocation(String wardLocation) {
		this.wardLocation = wardLocation;
	}
	public String getBedId() {
		return bedId;
	}
	public void setBedId(String bedId) {
		this.bedId = bedId;
	}
	public String getBriefDescription() {
		return briefDescription;
	}
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getTransferedDate() {
		return transferedDate;
	}
	public void setTransferedDate(String transferedDate) {
		this.transferedDate = transferedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isTrackingDevice() {
		return trackingDevice;
	}
	public void setTrackingDevice(boolean trackingDevice) {
		this.trackingDevice = trackingDevice;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getBriefDescriptionTransfer() {
		return briefDescriptionTransfer;
	}
	public void setBriefDescriptionTransfer(String briefDescriptionTransfer) {
		this.briefDescriptionTransfer = briefDescriptionTransfer;
	}
	public String getBriefDescriptionDischarge() {
		return briefDescriptionDischarge;
	}
	public void setBriefDescriptionDischarge(String briefDescriptionDischarge) {
		this.briefDescriptionDischarge = briefDescriptionDischarge;
	}
	public String getTypeOfTransfer() {
		return typeOfTransfer;
	}
	public void setTypeOfTransfer(String typeOfTransfer) {
		this.typeOfTransfer = typeOfTransfer;
	}
	public String getDischargeType() {
		return dischargeType;
	}
	public void setDischargeType(String dischargeType) {
		this.dischargeType = dischargeType;
	}
	public String getDischargeBy() {
		return dischargeBy;
	}
	public void setDischargeBy(String dischargeBy) {
		this.dischargeBy = dischargeBy;
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
	public String getTransferedBy() {
		return transferedBy;
	}
	public void setTransferedBy(String transferedBy) {
		this.transferedBy = transferedBy;
	}
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getAdmitId() {
		return admitId;
	}
	public void setAdmitId(String admitId) {
		this.admitId = admitId;
	}
	
	
}