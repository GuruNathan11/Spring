package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "forms")
public class form {
   
	@Id
    private String id;
    private String name;
    private String organization;
    private String download;
    private String print;
    private List fields;
    private String createdAt;
    private String updatedAt;
    
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	public List getFields() {
		return fields;
	}
	public void setFields(List fields) {
		this.fields = fields;
	}
public void update(form form) {
		
		this.setName(form.getName());
		this.setOrganization(form.getOrganization());
		this.setDownload(form.getDownload());
		this.setPrint(form.getPrint()); 
		this.setFields(form.getFields());
		
	}
    
}