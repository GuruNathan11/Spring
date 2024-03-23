package com.MHC.Project.Request;

public class DischargeRequest {
	
	private String pid;
	private String admitId;
	private String lastVisit;
	private String dischargeDate;
	private String dischargeType;
	private String dischargeBy;
	private String summaryOfCare;
	
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
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
	public String getSummaryOfCare() {
		return summaryOfCare;
	}
	public void setSummaryOfCare(String summaryOfCare) {
		this.summaryOfCare = summaryOfCare;
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
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

}