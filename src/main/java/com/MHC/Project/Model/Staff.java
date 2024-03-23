package com.MHC.Project.Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.MHC.Project.Request.StaffRequest;

import jakarta.persistence.Id;

@Document(collection = "Staff")
public class Staff {

	@Id
	private String id;
	private String active;
	private List<resource> resource;
	private List<Name> name;
	private String gender;
	private String email;
	private String role;
	private String username;
	private String password;
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
	private String terminationDate;
	private String terminationReason;
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
	public List<resource> getResource() {
		return resource;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	public String getPassword() {
//		return password;
//	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	
	// Constructor Details
	public Staff(String id, List<resource> resource, List<Name> name, String gender, String email, String role,
			String username, String password, String organization,String startDate, String userType, String[] speciality,
			String dateofBirth, List<_Birthdate> _birthdate, String ssn, String npi,
			List<Contact> contact, List<EmployeeDetails> employeeDetails, List<Emergency> emergency,
			String[] backgroundCheck, String immunizationStatus, String hipaaTraining,
			String privacyAcknowledgement, String signature, String active, String terminationDate, String terminationReason) {
		
		super();
		this.id = id;
		this.resource = resource;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.role = role;
		this.username = username;
		this.password = password;
		this.organization = organization;
		this.startDate = startDate;
		this.userType = userType;
		this.speciality = speciality;
		this.dateofBirth = dateofBirth;
		this._birthdate = _birthdate;
		this.ssn = ssn;
		this.npi = npi;
		this.contact = contact;
		this.employeeDetails = employeeDetails;
		this.emergency = emergency;
		this.backgroundCheck = backgroundCheck;
		this.immunizationStatus = immunizationStatus;
		this.hipaaTraining = hipaaTraining;
		this.privacyAcknowledgement = privacyAcknowledgement;
		this.signature = signature;
		this.active = active;
		this.terminationDate = terminationDate;
		this.terminationReason = terminationReason;
	}

	public static Staff build(String id, List<resource> resource, List<Name> name, String gender, String email,
			String role,String username, String password, String organization,String startDate,String userType, String[] speciality,
			String dateofBirth, List<_Birthdate> _birthdate, String ssn, String npi, List<Contact> contact,
			List<EmployeeDetails> employeeDetails, List<Emergency> emergency,String[] backgroundCheck, 
			String immunizationStatus, String hipaaTraining,String privacyAcknowledgement, String signature,
			String active, String terminationDate, String terminationReason) {
		
		return new Staff(id, resource, name, gender, email, role, username, password, organization,startDate, userType, speciality, dateofBirth, 
				_birthdate,	ssn, npi, contact, employeeDetails, emergency, backgroundCheck, immunizationStatus, hipaaTraining,
				privacyAcknowledgement, signature, active, terminationDate, terminationReason);
	}

	public void update(StaffRequest staffRequest) {

		this.setResource(staffRequest.getResource());
		this.setName(staffRequest.getName());
		this.setGender(staffRequest.getGender());
		this.setEmail(staffRequest.getEmail());
		this.setRole(staffRequest.getRole());
//		this.setUsername(staffRequest.getUsername());
//		this.setPassword(staffRequest.getPassword());
		this.setOrganization(staffRequest.getOrganization());
		this.setStartDate(staffRequest.getStartDate());
		this.setUserType(staffRequest.getUserType());
		this.setSpeciality(staffRequest.getSpeciality());
		this.setDateofBirth(staffRequest.getDateofBirth());
		this.set_birthdate(staffRequest.get_birthdate());
		this.setSsn(staffRequest.getSsn());
		this.setNpi(staffRequest.getNpi());
		this.setContact(staffRequest.getContact());
		this.setEmployeeDetails(staffRequest.getEmployeeDetails());
		this.setEmergency(staffRequest.getEmergency());
		this.setBackgroundCheck(staffRequest.getBackgroundCheck());
		this.setImmunizationStatus(staffRequest.getImmunizationStatus());
		this.setHipaaTraining(staffRequest.getHipaaTraining());
		this.setPrivacyAcknowledgement(staffRequest.getPrivacyAcknowledgement());
		this.setSignature(staffRequest.getSignature());
		this.setActive(staffRequest.getActive());
		this.setTerminationDate(staffRequest.getTerminationDate());
		this.setTerminationReason(staffRequest.getTerminationReason());
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	// Resource class
	public static class resource {
		private String fullUrl;
		private String resourceType;

		public String getResourceType() {
			return resourceType;
		}
		public void setResourceType(String resourceType) {
			this.resourceType = resourceType;
		}
		public String getFullUrl() {
			return fullUrl;
		}
		public void setFullUrl(String fullUrl) {
			this.fullUrl = fullUrl;
		}
	}

	// Name class
	public static class Name {
		private String use;
		public String given;
		public String family;

		public String getUse() {
			return use;
		}
		public void setUse(String use) {
			this.use = use;
		}
		public String getGiven() {
			return given;
		}
		public void setGiven(String given) {
			this.given = given;
		}
		public String getFamily() {
			return family;
		}
		public void setFamily(String family) {
			this.family = family;
		}
	}

	// birthdate class
	public static class _Birthdate {
		public List<Extension> getExtension() {
			return extension;
		}
		public void setExtension(List<Extension> extension) {
			this.extension = extension;
		}
		private List<Extension> extension;

	}

	public static class Extension {
		private String url;

		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = "http://hl7.org/fhir/StructureDefinition/staff-birthTime";
		}
	}

	// Contact class
	public static class Contact {
		private List<Address> address;
		private String homePhone;
		private String workPhone;
		private String mobilePhone;
		private String contactEmail;
		
		public List<Address> getAddress() {
			return address;
		}
		public void setAddress(List<Address> address) {
			this.address = address;
		}
		public String getHomePhone() {
			return homePhone;
		}
		public void setHomePhone(String homePhone) {
			this.homePhone = homePhone;
		}
		public String getWorkPhone() {
			return workPhone;
		}
		public void setWorkPhone(String workPhone) {
			this.workPhone = workPhone;
		}
		public String getMobilePhone() {
			return mobilePhone;
		}
		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}
		public String getContactEmail() {
			return contactEmail;
		}
		public void setContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
		}
	}

	// Address class
	public static class Address {
		private String addressLine1;
		private String addressLine2;
		private String city;
		private String state;
		private String Zip;
		private String country;
		
		public String getAddressLine1() {
			return addressLine1;
		}
		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}
		public String getAddressLine2() {
			return addressLine2;
		}
		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZip() {
			return Zip;
		}
		public void setZip(String zip) {
			this.Zip = zip;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
	}

	// Emergency class
	public static class Emergency {
		private String relationShip;
		private String emergencyContact;
		private String emergencyPhone;
		
		public String getRelationShip() {
			return relationShip;
		}
		public void setRelationShip(String relationShip) {
			this.relationShip = relationShip;
		}
		public String getEmergencyContact() {
			return emergencyContact;
		}
		public void setEmergencyContact(String emergencyContact) {
			this.emergencyContact = emergencyContact;
		}
		public String getEmergencyPhone() {
			return emergencyPhone;
		}
		public void setEmergencyPhone(String emergencyPhone) {
			this.emergencyPhone = emergencyPhone;
		}
	}

	// Employee details class
	public static class EmployeeDetails {
		private String employeeId;
		private String jobTitle;
		private String department;
		private String workSchedule;
		private String salary;
		private String startDate;
		private String endDate;
		private String credentials;
		private String[] Skills;
		private String qualification;

		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getJobTitle() {
			return jobTitle;
		}
		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getWorkSchedule() {
			return workSchedule;
		}
		public void setWorkSchedule(String workSchedule) {
			this.workSchedule = workSchedule;
		}
		public String getSalary() {
			return salary;
		}
		public void setSalary(String salary) {
			this.salary = salary;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public String getCredentials() {
			return credentials;
		}
		public void setCredentials(String credentials) {
			this.credentials = credentials;
		}
		public String[] getSkills() {
			return Skills;
		}
		public void setSkills(String[] skills) {
			Skills = skills;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
	}
}