package com.MHC.Project.Response;

public class OrganizationResponse {
	
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OrganizationResponse(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

}
