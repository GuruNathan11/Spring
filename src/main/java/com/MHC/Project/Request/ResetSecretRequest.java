package com.MHC.Project.Request;

public class ResetSecretRequest {

	private String email;
    private String jwt;

    public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
