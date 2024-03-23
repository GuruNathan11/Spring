package com.MHC.Project.Request;

public class AdmitRequest {

    private String wardName;
    private String roomNo;
    private String bedNo;
    private String Pid;
    private String assignedby;
    private String gender;
    private String age;
    private String expectedLengthOfStay;
    
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}
	public String getPid() {
		return Pid;
	}
	public void setPid(String Pid) {
		this.Pid = Pid;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getAssignedby() {
		return assignedby;
	}
	public void setAssignedby(String assignedby) {
		this.assignedby = assignedby;
	}
	public String getGender() {
		return gender;
	}
	public String setGender(String gender) {
		return gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getExpectedLengthOfStay() {
		return expectedLengthOfStay;
	}
	public void setExpectedLengthOfStay(String expectedLengthOfStay) {
		this.expectedLengthOfStay = expectedLengthOfStay;
	}
	
}
