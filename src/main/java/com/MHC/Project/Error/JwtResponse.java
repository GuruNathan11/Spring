package com.MHC.Project.Error;

public class JwtResponse {

    private String JwtToken;

    public JwtResponse(String JwtToken) {
        this.JwtToken = JwtToken;
    }

    public String getJwtToken() {
        return JwtToken;
    }
 
    public void setJwtToken(String JwtToken) {
        this.JwtToken = JwtToken;
    }
}