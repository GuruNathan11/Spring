package com.MHC.Project.Request;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.MHC.Project.Model.Staff.Contact;
import com.MHC.Project.Model.Staff.Emergency;
import com.MHC.Project.Model.Staff.EmployeeDetails;
import com.MHC.Project.Model.Staff.Name;
import com.MHC.Project.Model.Staff._Birthdate;
import com.MHC.Project.Model.Staff.resource;

public class StaffRequest {

	private String Id;
	private List<resource> resource;
	private List<Name> name;
	private String gender;
	private String email;
	private String role;
	private String organization;
	private String startDate;
	private String userType;
	private String[] speciality;
	private String dateofBirth;
	private List<_Birthdate> _birthdate;
	private String ssn;
	private String npi;
	private List<Contact> contact;
	private List<EmployeeDetails> employeeDetails;
	private List<Emergency> emergency;
	private String[] backgroundCheck;
	private String immunizationStatus;
	private String hipaaTraining;
	private String privacyAcknowledgement;
	private String signature;
	private String active;
	private String terminationDate;
	private String terminationReason;

	public List<resource> getResource() {
		return resource;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public void setResource(List<resource> resource) {
		this.resource = resource;
	}
	public List<Name> getName() {
		return name;
	}
	public void setName(List<Name> name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public List<_Birthdate> get_birthdate() {
		return _birthdate;
	}
	public void set_birthdate(List<_Birthdate> _birthdate) {
		this._birthdate = _birthdate;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public List<Contact> getContact() {
		return contact;
	}
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	public List<EmployeeDetails> getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(List<EmployeeDetails> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	public List<Emergency> getEmergency() {
		return emergency;
	}
	public void setEmergency(List<Emergency> emergency) {
		this.emergency = emergency;
	}
	public String[] getBackgroundCheck() {
		return backgroundCheck;
	}
	public void setBackgroundCheck(String[] backgroundCheck) {
		this.backgroundCheck = backgroundCheck;
	}
	public String getImmunizationStatus() {
		return immunizationStatus;
	}
	public void setImmunizationStatus(String immunizationStatus) {
		this.immunizationStatus = immunizationStatus;
	}
	public String getHipaaTraining() {
		return hipaaTraining;
	}
	public void setHipaaTraining(String hipaaTraining) {
		this.hipaaTraining = hipaaTraining;
	}
	public String getPrivacyAcknowledgement() {
		return privacyAcknowledgement;
	}
	public void setPrivacyAcknowledgement(String privacyAcknowledgement) {
		this.privacyAcknowledgement = privacyAcknowledgement;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String[] getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String[] speciality) {
		this.speciality = speciality;
	}
	public String getNpi() {
		return npi;
	}
	public void setNpi(String npi) {
		this.npi = npi;
	}
	public String getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTerminationReason() {
		return terminationReason;
	}
	public void setTerminationReason(String terminationReason) {
		this.terminationReason = terminationReason;
	}
	
	// ******* User Name Creation *******
	public String getUsername() {
		String givenName = null;
		String familyName = null;
		String birthYear = null;

		if (name != null && !name.isEmpty()) {
			Name nameObj = name.get(0);
			if (nameObj != null && nameObj.getGiven() != null && !nameObj.getGiven().isEmpty()) {
				givenName = nameObj.getGiven().substring(0, Math.min(nameObj.getGiven().length(), 3));
			}
			if (nameObj != null && nameObj.getFamily() != null && !nameObj.getFamily().isEmpty()) {
				familyName = String.valueOf(nameObj.getFamily().charAt(0));
			}
		}
		if (dateofBirth != null) {
//			birthYear = String.valueOf(dateofBirth.getYear());
			birthYear = getDateofBirth().substring(0, Math.min(getDateofBirth().length(), 4));
		}

		if (givenName != null && familyName != null && birthYear != null) {
			String username = givenName + familyName + birthYear;
			return username;
		} else {
			return null;
		}
	}
	// ********** Password Creation ***********
	public String getPassword() {
        if (name != null && !name.isEmpty()) {
               String nameList = name.get(0).getGiven();
               String ssn = getSsn();          
               if (nameList != null && !nameList.isEmpty() && ssn != null) {
                   String givenName = name.get(0).getGiven();
//                   String email = getEmail().substring(4,9);
                   String ssnlast = ssn.substring(4, 9);
                   return givenName + "@"+ ssnlast;
               }
          }
           return null;
       }
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}