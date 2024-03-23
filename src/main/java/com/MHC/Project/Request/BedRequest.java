package com.MHC.Project.Request;

import jakarta.persistence.Id;

public class BedRequest {
	
	@Id
	private String id;
	private String wardName;
	private String bedName;
	
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}	

}
