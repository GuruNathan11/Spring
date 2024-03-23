package com.MHC.Project.Request;

import javax.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    
    @NotBlank
    private String username;

    @NotBlank
    private String password;
    
//    @NotNull
//    private String organization;
    
   public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//	public String getOrganization() {
//		return organization;
//	}
//
//	public void setOrganization(String organization) {
//		this.organization = organization;
//	}

}