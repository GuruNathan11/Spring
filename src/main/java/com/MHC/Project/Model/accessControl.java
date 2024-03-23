package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "accessControl")
public class accessControl {

	@Id
	private String id;
	private String orgName;
	private String allAccess;
	private String q15;
	private String pv;
	private String gf;

	public String getAllAccess() {
		return allAccess;
	}
	public void setAllAccess(String allAccess) {
		this.allAccess = allAccess;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getQ15() {
		return q15;
	}
	public void setQ15(String q15) {
		this.q15 = q15;
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv;
	}
	public String getGf() {
		return gf;
	}
	public void setGf(String gf) {
		this.gf = gf;
	}
	
	public accessControl(String id, String orgName, String q15, String pv, String gf, String allAccess) {
		super();
		this.id = id;
		this.orgName = orgName;
		this.q15 = q15;
		this.pv = pv;
		this.gf = gf;
		this.allAccess = allAccess;
	}
	
	public static accessControl build (String id, String orgName, String q15, String pv, String gf, String allAccess) {
	 return new accessControl (id, orgName, q15, pv, gf, allAccess);
	}
	
	public void update(accessControl access) {
		
		this.setAllAccess(access.getAllAccess());
		this.setQ15(access.getQ15());
		this.setPv(access.getPv());
		this.setGf(access.getGf());
	}
	
}
