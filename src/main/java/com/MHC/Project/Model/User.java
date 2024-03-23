package com.MHC.Project.Model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection="user")
public class User {

	@Id
	private String id;
	private String userType;
	private String username;
	private String email;
	private String role;
	private String password;
	private String jwt;
	private String organization;
	private String resetToken;
	private LocalDateTime resetTokenExpiry;
	private String resetOtp;
	private String secretKey;
	private LocalDateTime resetOtpExpiration;
	private int resetCount;
	private LocalDateTime secretKeyExpiration;
	private String verificationKey;
	private boolean verified;
	private Date verificationDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public LocalDateTime getResetTokenExpiry() {
		return resetTokenExpiry;
	}
	public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
		this.resetTokenExpiry = resetTokenExpiry;
	}
	public String getResetOtp() {
		return resetOtp;
	}
	public void setResetOtp(String resetOtp) {
		this.resetOtp = resetOtp;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public LocalDateTime getResetOtpExpiration() {
		return resetOtpExpiration;
	}
	public void setResetOtpExpiration(LocalDateTime resetOtpExpiration) {
		this.resetOtpExpiration = resetOtpExpiration;
	}
	public LocalDateTime getSecretKeyExpiration() {
		return secretKeyExpiration;
	}
	public void setSecretKeyExpiration(LocalDateTime secretKeyExpiration) {
		this.secretKeyExpiration = secretKeyExpiration;
	}
	public String getVerificationKey() {
		return verificationKey;
	}
	public void setVerificationKey(String verificationKey) {
		this.verificationKey = verificationKey;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public Date getVerificationDate() {
		return verificationDate;
	}
	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public int getResetCount() {
		return resetCount;
	}
	public void setResetCount(int resetCount) {
		this.resetCount = resetCount;
	}
	public void incrementResetCount() {
        this.resetCount++;
    }
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
		
}
