package com.MHC.Project.Request;

public class BedAssignmentRequest {

    private String pid;
    private String wardBedInfo;
    private String admitDate;
    private String height;
    private String weight;
    private String assignedBy;
    
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getWardBedInfo() {
		return wardBedInfo;
	}
	public void setWardBedInfo(String wardBedInfo) {
		this.wardBedInfo = wardBedInfo;
	}
	
	public String getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
    
    
}
