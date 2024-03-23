package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Id;

@Document(collection = "Staff-Patient-Management")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffPatientAssign {

	@Id
	private String id;
	private String sid[];
	private String[] pid;
	private String createdAt;
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String[] getSid() {
		return sid;
	}
	public void setSid(String[] sid) {
		this.sid = sid;
	}
	public String[] getPid() {
		return pid;
	}
	public void setPid(String[] pid) {
		this.pid = pid;
	}
	public StaffPatientAssign(String id, String[] sid, String[] pid, String createdAt) {
		super();
		this.id = id;
		this.sid = sid;
		this.pid = pid;
		this.createdAt = createdAt;
	}
	public StaffPatientAssign build(String id,String[] sid, String[] pid, String createdAt) {
		return new StaffPatientAssign(id, sid, pid, createdAt);
	}

}
