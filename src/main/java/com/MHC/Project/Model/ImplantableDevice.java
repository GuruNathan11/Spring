package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.persistence.Id;

@Document(collection = "ImplantableDevice")
public class ImplantableDevice {

	@Id
	private String id;
	private Object data;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
