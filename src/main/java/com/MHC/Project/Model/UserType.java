package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "roles")
public class UserType {
	@Id
	private String id;

	private String userType;

	public UserType() {
	}

	public UserType(String name) {
		this.userType = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String name) {
		this.userType = name;
	}

}
