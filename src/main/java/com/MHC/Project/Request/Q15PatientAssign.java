package com.MHC.Project.Request;

public class Q15PatientAssign {
	
	private String bedId;
	private String pid;
	private String AdmitDate;
	private String assignedBy;
	
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
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	public String getBedId() {
		return bedId;
	}
	public void setBedId(String bedId) {
		this.bedId = bedId;
	}

}
