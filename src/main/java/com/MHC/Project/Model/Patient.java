package com.MHC.Project.Model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.MHC.Project.Request.PatientRequest;

@Document(collection = "Patientlist")
public class Patient {
	
	@Id
	private String id;
	private String active;
	private List<resource> resource;
	private List<basicDetails> basicDetails;
	private String email;
	private String username;
	private String password;
	private String organization;
	private String userType;
	private List<Contact1> contact;
	private List<Employer> employer;
	private List<Guardian> guardian;
	private List<Misc> misc;
	private List<Stats> stats;
	private List<Insurance> insurance;
	private List<FamilyHealth> familyHealth;
	private List<SocialHistory> socialHistory;
	private List<PrimaryCarePhysician> primaryCarePhysician;
	private List<Devices> devices;
	private String assignedBed;
	private String bedNo;
	private String roomNo;
	private String beaconDevice;
	private String uuid;
	private String createdAt;
	private String updatedAt;
	private List<String> beaconDevice1;
	private Map<String, String> uuid1;
	
	public List<String> getBeaconDevice1() {
		return beaconDevice1;
	}
	public void setBeaconDevice1(List<String> beaconDevice1) {
		this.beaconDevice1 = beaconDevice1;
	}
	public Map<String, String> getUuid1() {
		return uuid1;
	}
	public void setUuid1(Map<String, String> uuid1) {
		this.uuid1 = uuid1;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
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
	public List<SocialHistory> getSocialHistory() {
		return socialHistory;
	}
	public void setSocialHistory(List<SocialHistory> socialHistory) {
		this.socialHistory = socialHistory;
	}
	public List<PrimaryCarePhysician> getPrimaryCarePhysician() {
		return primaryCarePhysician;
	}
	public void setPrimaryCarePhysician(List<PrimaryCarePhysician> primaryCarePhysician) {
		this.primaryCarePhysician = primaryCarePhysician;
	}
	public List<Devices> getDevices() {
		return devices;
	}
	public void setDevices(List<Devices> devices) {
		this.devices = devices;
	}
	public List<FamilyHealth> getFamilyHealth() {
		return familyHealth;
	}
	public void setFamilyHealth(List<FamilyHealth> familyHealth) {
		this.familyHealth = familyHealth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public List<Insurance> getInsurance() {
		return insurance;
	}
	public void setInsurance(List<Insurance> insurance) {
		this.insurance = insurance;
	}
	public List<Stats> getStats() {
		return stats;
	}
	public void setStats(List<Stats> stats) {
		this.stats = stats;
	}
	public List<Misc> getMisc() {
		return misc;
	}
	public void setMisc(List<Misc> misc) {
		this.misc = misc;
	}
	public List<Contact1> getContact() {
		return contact;
	}
	public void setContact(List<Contact1> contact) {
		this.contact = contact;
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
	public List<basicDetails> getBasicDetails() {
		return basicDetails;
	}
	public void setBasicDetails(List<basicDetails> basicDetails) {
		this.basicDetails = basicDetails;
	}
	public List<Employer> getEmployer() {
		return employer;
	}
	public void setEmployer(List<Employer> employer) {
		this.employer = employer;
	}
	public List<Guardian> getGuardian() {
		return guardian;
	}
	public void setGuardian(List<Guardian> guardian) {
		this.guardian = guardian;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getAssignedBed() {
		return assignedBed;
	}
	public void setAssignedBed(String assignedBed) {
		this.assignedBed = assignedBed;
	}
	
	// ************ Constructor Method ************\\
	public Patient() {

	}

	public Patient(String id, String active, List<resource> resource, List<basicDetails> basicDetails, String email,
			 String organization,String userType, List<Contact1> contact, List<Employer> employer,
			List<Guardian> guardian, List<Misc> misc, List<Stats> stats, List<Insurance> insurance,
			List<FamilyHealth> familyHealth, List<SocialHistory> socialHistory, 
			List<PrimaryCarePhysician> primaryCarePhysician, List<Devices> devices, String beaconDevice,
			String uuid, List<String> beaconDevice1, Map<String, String> uuid1) {
		this.id = id;
		this.setActive(active);
		this.resource = resource;
		this.basicDetails = basicDetails;
		this.email = email;
		this.organization = organization;
		this.userType = userType;
		this.contact = contact;
		this.employer = employer;
		this.guardian = guardian;
		this.misc = misc;
		this.stats = stats;
		this.insurance = insurance;
		this.familyHealth = familyHealth;
		this.socialHistory = socialHistory;
		this.primaryCarePhysician = primaryCarePhysician;
		this.devices = devices;
		this.beaconDevice = beaconDevice;
		this.uuid = uuid;
		this.beaconDevice1 = beaconDevice1;
		this.uuid1 = uuid1;
	}

	public static Patient build(String id, String active, List<Patient.resource> resource,
			List<Patient.basicDetails> basicDetails, String email, 
			String organization,String userType, List<Patient.Contact1> contact, List<Patient.Employer> employer,
			List<Patient.Guardian> guardian, List<Patient.Misc> misc, List<Patient.Stats> stats,
			List<Patient.Insurance> insurance, List<FamilyHealth> familyHealth, List<SocialHistory> socialHistory,
			List<PrimaryCarePhysician> primaryCarePhysician, List<Devices> devices, String beaconDevice, String uuid, List<String> beaconDevice1, Map<String, String> uuid1) {
		return new Patient(id, active, resource, basicDetails, email, organization,userType, contact,
				employer, guardian, misc, stats, insurance, familyHealth, socialHistory, primaryCarePhysician, devices, beaconDevice, uuid, beaconDevice1, uuid1);
	}

	public void update(PatientRequest patientRequest) {
		this.setResource(patientRequest.getResource());
		this.setBasicDetails(patientRequest.getBasicDetails());
		this.setEmail(patientRequest.getEmail());
//		this.setUsername(patientRequest.getUsername());
//		this.setPassword(patientRequest.getPassword());
		this.setOrganization(patientRequest.getOrganization());
		this.setUserType(patientRequest.getUserType());
		this.setContact(patientRequest.getContact());
		this.setEmployer(patientRequest.getEmployer());
		this.setGuardian(patientRequest.getGuardian());
		this.setMisc(patientRequest.getMisc());
		this.setStats(patientRequest.getStats());
		this.setInsurance(patientRequest.getInsurance());
		this.setBeaconDevice(patientRequest.getBeaconDevice());
		this.setFamilyHealth(patientRequest.getFamilyHealth());
		this.setSocialHistory(patientRequest.getSocialHistory());
		this.setPrimaryCarePhysician(patientRequest.getPrimaryCarePhysician());
		this.setDevices(patientRequest.getDevices());
		this.setBeaconDevice1(patientRequest.getBeaconDevice1());
		this.setUuid1(patientRequest.getUuid1());
		this.setUuid(patientRequest.getUuid());
	}

	public String getBeaconDevice() {
		return beaconDevice;
	}
	public void setBeaconDevice(String beaconDevice) {
		this.beaconDevice = beaconDevice;
	}

	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	// ******************* Resource **************************\\
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

	// ******************* Basic Details **************************\\
	public static class basicDetails {
		public List<Coding> coding;
		private List<Name> name;
		private String profile;
		private String ssn;
		private String mrNumber;
		private String licenseId;
		private String confirmEmail;
		private String gender;
		private String birthDate;
		private List<BirthDate> _birthDate;
		private String maritalStatus;
		private String sexualOrientation;

		public String getMrNumber() {
			return mrNumber;
		}
		public void setMrNumber(String mrNumber) {
			this.mrNumber = mrNumber;
		}
		public List<Name> getName() {
			return name;
		}
		public void setName(List<Name> name) {
			this.name = name;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}
		public List<BirthDate> get_birthDate() {
			return _birthDate;
		}
		public void set_birthDate(List<BirthDate> _birthDate) {
			this._birthDate = _birthDate;
		}
		public String getMaritalStatus() {
			return maritalStatus;
		}
		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}
		public String getSexualOrientation() {
			return sexualOrientation;
		}
		public void setSexualOrientation(String sexualOrientation) {
			this.sexualOrientation = sexualOrientation;
		}
		public List<Coding> getCoding() {
			return coding;
		}
		public void setCoding(List<Coding> coding) {
			this.coding = coding;
		}
		public String getSsn() {
			return ssn;
		}
		public void setSsn(String ssn) {
			this.ssn = ssn;
		}
		public String getLicenseId() {
			return licenseId;
		}
		public void setLicenseId(String licenseId) {
			this.licenseId = licenseId;
		}
		public String getConfirmEmail() {
			return confirmEmail;
		}
		public void setConfirmEmail(String confirmEmail) {
			this.confirmEmail = confirmEmail;
		}

		public static class Coding {
			private String system;
			private String code;

			public String getSystem() {
				return system;
			}
			public void setSystem(String system) {
				this.system = "http://terminology.hl7.org/CodeSystem/v2-0203";
			}
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
		}

		public static class Name {
			private String use;
			private String given;
			private String family;

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

		public static class BirthDate {
			private List<Extension> extension;

			public List<Extension> getExtension() {
				return extension;
			}
			public void setExtension(List<Extension> extension) {
				this.extension = extension;
			}
			
			public static class Extension {
				private String url;

				public String getUrl() {
					return url;
				}
				public void setUrl(String url) {
					this.url = "http://hl7.org/fhir/StructureDefinition/patient-birthTime";
				}
			}
		}
	}

	// ************** Contact ******************\\
	public static class Contact1 {

		private List<Address2> address;
		private String motherName;
		private String firstName;
		private String lastName;
		private String homePhone;
		private String workPhone;
		private String mobilePhone;
		private String contactEmail;
		private String trustedEmail;
		private List<Emergency> emergency;
		private List<AdditionalAddress1> additionalAddress;

		public List<Address2> getAddress() {
			return address;
		}
		public void setAddress(List<Address2> address) {
			this.address = address;
		}
		
		// Address Class
		public static class Address2 {
			private String addressLine1;
			private String addressLine2;
			private String city;
			private String state;
			private String postalCode;
			private String Country;

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
			public String getPostalCode() {
				return postalCode;
			}
			public void setPostalCode(String postalCode) {
				this.postalCode = postalCode;
			}
			public String getCountry() {
				return Country;
			}
			public void setCountry(String country) {
				Country = country;
			}
		}

		public String getMotherName() {
			return motherName;
		}
		public void setMotherName(String motherName) {
			this.motherName = motherName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
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
		public String getTrustedEmail() {
			return trustedEmail;
		}
		public void setTrustedEmail(String trustedEmail) {
			this.trustedEmail = trustedEmail;
		}
		public List<Emergency> getEmergency() {
			return emergency;
		}
		public void setEmergency(List<Emergency> emergency) {
			this.emergency = emergency;
		}
		public List<AdditionalAddress1> getAdditionalAddress() {
			return additionalAddress;
		}
		public void setAdditionalAddress(List<AdditionalAddress1> additionalAddress) {
			this.additionalAddress = additionalAddress;
		}

		// Emergency Class;
		public static class Emergency {
			private String relationship;
			private String emergencyContact;
			private String emergencyMobile;

			public String getRelationship() {
				return relationship;
			}
			public void setRelationship(String relationship) {
				this.relationship = relationship;
			}
			public String getEmergencyContact() {
				return emergencyContact;
			}
			public void setEmergencyContact(String emergencyContact) {
				this.emergencyContact = emergencyContact;
			}
			public String getEmergencyMobile() {
				return emergencyMobile;
			}
			public void setEmergencyMobile(String emergencyMobile) {
				this.emergencyMobile = emergencyMobile;
			}
		}

		public static class AdditionalAddress1 {
			private String addressUse;
			private String addressType;
			private String startDate;
			private String endDate;
			private String addressLine1;
			private String addressLine2;
			private String city;
			private String district;
			private String state;
			private String postalCodeNumber;
			private String country;

			public String getAddressUse() {
				return addressUse;
			}
			public void setAddressUse(String addressUse) {
				this.addressUse = addressUse;
			}
			public String getAddressType() {
				return addressType;
			}
			public void setAddressType(String addressType) {
				this.addressType = addressType;
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
			public String getDistrict() {
				return district;
			}
			public void setDistrict(String district) {
				this.district = district;
			}
			public String getState() {
				return state;
			}
			public void setState(String state) {
				this.state = state;
			}
			public String getPostalCodeNumber() {
				return postalCodeNumber;
			}
			public void setPostalCodeNumber(String postalCodeNumber) {
				this.postalCodeNumber = postalCodeNumber;
			}
			public String getCountry() {
				return country;
			}
			public void setCountry(String country) {
				this.country = country;
			}
		}
	}

	// ******************* Employer **************************\\
	public static class Employer {

		private String occupation;
		private String AddressLine1;
		private String AddressLine2;
		private String city;
		private String state;
		private String postalCode;
		private String country;
		private String unassignedUSA;
		private String industry;

		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public String getAddressLine1() {
			return AddressLine1;
		}
		public void setAddressLine1(String addressLine1) {
			AddressLine1 = addressLine1;
		}
		public String getAddressLine2() {
			return AddressLine2;
		}
		public void setAddressLine2(String addressLine2) {
			AddressLine2 = addressLine2;
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
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getUnassignedUSA() {
			return unassignedUSA;
		}
		public void setUnassignedUSA(String unassignedUSA) {
			this.unassignedUSA = unassignedUSA;
		}
		public String getIndustry() {
			return industry;
		}
		public void setIndustry(String industry) {
			this.industry = industry;
		}
	}

	// ************** Guardian ******************\\
	public static class Guardian {

		private String name;
		private String relationship;
		private String gender;
		private List<Address1> address;
		private String workPhone;
		private String email;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRelationship() {
			return relationship;
		}
		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public List<Address1> getAddress() {
			return address;
		}
		public void setAddress(List<Address1> address) {
			this.address = address;
		}
		public String getWorkPhone() {
			return workPhone;
		}
		public void setWorkPhone(String workPhone) {
			this.workPhone = workPhone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public static class Address1 {

			private String addressLine1;
			private String addressLine2;
			private String city;
			private String state;
			private String postalCode;
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

			public String getPostalCode() {
				return postalCode;
			}

			public void setPostalCode(String postalCode) {
				this.postalCode = postalCode;
			}

			public String getCountry() {
				return country;
			}

			public void setCountry(String country) {
				this.country = country;
			}
		}
	}

	// ************** Misc ******************\\
	public static class Misc {

		private String dateDeceased;
		private String reason;

		public String getDateDeceased() {
			return dateDeceased;
		}

		public void setDateDeceased(String dateDeceased) {
			this.dateDeceased = dateDeceased;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}
	}

	// ************** Stats ******************\\
	public static class Stats {

		private boolean languageDeclined=false;
		private boolean ethnicityDeclined=false;
		private boolean raceDeclined=false;
		private String language;
		private String ethnicity;
		private String race;
		private Number familySize;
		private String financialReviewDate;
		private String monthlyIncome;
		private String homeless;
		private String interpreter;
		private String migrant;
		private String referralSource;
		private String religion;
		private String vfc;

		public boolean isLanguageDeclined() {
			return languageDeclined;
		}
		public void setLanguageDeclined(boolean languageDeclined) {
			this.languageDeclined = languageDeclined;
		}
		public boolean isEthnicityDeclined() {
			return ethnicityDeclined;
		}
		public void setEthnicityDeclined(boolean ethnicityDeclined) {
			this.ethnicityDeclined = ethnicityDeclined;
		}
		public boolean isRaceDeclined() {
			return raceDeclined;
		}
		public void setRaceDeclined(boolean raceDeclined) {
			this.raceDeclined = raceDeclined;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getEthnicity() {
			return ethnicity;
		}
		public void setEthnicity(String ethnicity) {
			this.ethnicity = ethnicity;
		}
		public String getRace() {
			return race;
		}
		public void setRace(String race) {
			this.race = race;
		}
		public Number getFamilySize() {
			return familySize;
		}
		public void setFamilySize(Number familySize) {
			this.familySize = familySize;
		}
		public String getFinancialReviewDate() {
			return financialReviewDate;
		}
		public void setFinancialReviewDate(String financialReviewDate) {
			this.financialReviewDate = financialReviewDate;
		}
		public String getHomeless() {
			return homeless;
		}
		public void setHomeless(String homeless) {
			this.homeless = homeless;
		}
		public String getInterpreter() {
			return interpreter;
		}
		public void setInterpreter(String interpreter) {
			this.interpreter = interpreter;
		}
		public String getMigrant() {
			return migrant;
		}
		public void setMigrant(String migrant) {
			this.migrant = migrant;
		}
		public String getReferralSource() {
			return referralSource;
		}
		public void setReferralSource(String referralSource) {
			this.referralSource = referralSource;
		}
		public String getReligion() {
			return religion;
		}
		public void setReligion(String religion) {
			this.religion = religion;
		}
		public String getVfc() {
			return vfc;
		}
		public void setVfc(String vfc) {
			this.vfc = vfc;
		}
		public String getMonthlyIncome() {
			return monthlyIncome;
		}
		public void setMonthlyIncome(String monthlyIncome) {
			this.monthlyIncome = monthlyIncome;
		}
	}

	// ************** Insurance ******************\\

	public static class Insurance {

		private List<Primary> primary;
		private List<Secondary> secondary;

		public List<Primary> getPrimary() {
			return primary;
		}
		public void setPrimary(List<Primary> primary) {
			this.primary = primary;
		}
		public List<Secondary> getSecondary() {
			return secondary;
		}
		public void setSecondary(List<Secondary> secondary) {
			this.secondary = secondary;
		}

		// ***** Primary-Insurance class ****//

		public static class Primary {

			private String Title;
			private String planName;
			private String subscriber;
			private String effectivedate;
			private String relationship;
			private String policyNumber;
			private String birthDate;
			private String groupNumber;
			private String ss;
			private String subscriberEmployee;
			private String subscriberPhone;
			private String city;
			private String SEAddress;
			private String state;
			private String country;
			private String zipCode;
			private String gender;
			private List<SubscriberAddress> subscriberAddress;
			private String co_pay;
			private String acceptAssignment;

			public String getTitle() {
				return Title;
			}
			public void setTitle(String title) {
				Title = title;
			}
			public String getPlanName() {
				return planName;
			}
			public void setPlanName(String planName) {
				this.planName = planName;
			}
			public String getSubscriber() {
				return subscriber;
			}
			public void setSubscriber(String subscriber) {
				this.subscriber = subscriber;
			}
			public String getEffectivedate() {
				return effectivedate;
			}
			public void setEffectivedate(String effectivedate) {
				this.effectivedate = effectivedate;
			}
			public String getSubscriberPhone() {
				return subscriberPhone;
			}
			public void setSubscriberPhone(String subscriberPhone) {
				this.subscriberPhone = subscriberPhone;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getSEAddress() {
				return SEAddress;
			}
			public void setSEAddress(String sEAddress) {
				SEAddress = sEAddress;
			}
			public String getState() {
				return state;
			}
			public void setState(String state) {
				this.state = state;
			}
			public String getZipCode() {
				return zipCode;
			}
			public String getCountry() {
				return country;
			}
			public void setCountry(String country) {
				this.country = country;
			}
			public void setZipCode(String zipCode) {
				this.zipCode = zipCode;
			}
			public String getRelationship() {
				return relationship;
			}
			public void setRelationship(String relationship) {
				this.relationship = relationship;
			}
			public String getPolicyNumber() {
				return policyNumber;
			}
			public void setPolicyNumber(String policyNumber) {
				this.policyNumber = policyNumber;
			}
			public String getBirthDate() {
				return birthDate;
			}
			public void setBirthDate(String birthDate) {
				this.birthDate = birthDate;
			}
			public String getGroupNumber() {
				return groupNumber;
			}
			public void setGroupNumber(String groupNumber) {
				this.groupNumber = groupNumber;
			}
			public String getSs() {
				return ss;
			}
			public void setSs(String ss) {
				this.ss = ss;
			}
			public String getSubscriberEmployee() {
				return subscriberEmployee;
			}
			public void setSubscriberEmployee(String subscriberEmployee) {
				this.subscriberEmployee = subscriberEmployee;
			}
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			public List<SubscriberAddress> getSubscriberAddress() {
				return subscriberAddress;
			}
			public void setSubscriberAddress(List<SubscriberAddress> subscriberAddress) {
				this.subscriberAddress = subscriberAddress;
			}
			public String getCo_pay() {
				return co_pay;
			}
			public void setCo_pay(String co_pay) {
				this.co_pay = co_pay;
			}
			public String getAcceptAssignment() {
				return acceptAssignment;
			}
			public void setAcceptAssignment(String acceptAssignment) {
				this.acceptAssignment = acceptAssignment;
			}
			
			// ***** SubscriberAddress **** //
			public static class SubscriberAddress {

				private String addressLine1;
				private String addressLine2;
				private String city;
				private String state;
				private String country;
				private String zipCode;

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
				public String getCountry() {
					return country;
				}
				public void setCountry(String country) {
					this.country = country;
				}
				public String getZipCode() {
					return zipCode;
				}
				public void setZipCode(String zipCode) {
					this.zipCode = zipCode;
				}
			}
		}

		// ***** Secondary-Insurance class ****//
		public static class Secondary {
			private Primary insuranceDetails; // Renamed field
			
			
			public Primary getInsuranceDetails() {
				return insuranceDetails;
			}
			public void setInsuranceDetails(Primary insuranceDetails) {
				this.insuranceDetails = insuranceDetails;
			}
		}
	}

	public static class FamilyHealth {
		
		private String id;
		private String name;
		private String deceadsed;
		private String diabetes;
		private String disease;
		private String stroke;
		private String mentalIllness;
		private String cancer;
		private String unknown;
		private String other;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDeceadsed() {
			return deceadsed;
		}
		public void setDeceadsed(String deceadsed) {
			this.deceadsed = deceadsed;
		}
		public String getDiabetes() {
			return diabetes;
		}
		public void setDiabetes(String diabetes) {
			this.diabetes = diabetes;
		}
		public String getDisease() {
			return disease;
		}
		public void setDisease(String disease) {
			this.disease = disease;
		}
		public String getStroke() {
			return stroke;
		}
		public void setStroke(String stroke) {
			this.stroke = stroke;
		}
		public String getMentalIllness() {
			return mentalIllness;
		}
		public void setMentalIllness(String mentalIllness) {
			this.mentalIllness = mentalIllness;
		}
		public String getCancer() {
			return cancer;
		}
		public void setCancer(String cancer) {
			this.cancer = cancer;
		}
		public String getUnknown() {
			return unknown;
		}
		public void setUnknown(String unknown) {
			this.unknown = unknown;
		}
		public String getOther() {
			return other;
		}
		public void setOther(String other) {
			this.other = other;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}

	public static class SocialHistory {
		private String smoker;
		private int smokePerDay;
		private String everSmoked;
		private int smokeYears;
		private int quitYear;
		private String quitIntrest;
		private String drinkAlcohal;
		private String recreationalDrugs;
		private String pastAlcohal;
		private String tabaccoUse;
		private int usingTime;
		private String SexActive;
		private String partner;
		private String sexInfection;
		private String caffine;
		private String migrantOrSeasonal;
		private int usePerDay;
		private String occupation;
		private String maritalStatus;
		private String child;
		private int noOfChild;
		private String[] childAge;

		public String getSmoker() {
			return smoker;
		}
		public void setSmoker(String smoker) {
			this.smoker = smoker;
		}
		public int getSmokePerDay() {
			return smokePerDay;
		}
		public void setSmokePerDay(int smokePerDay) {
			this.smokePerDay = smokePerDay;
		}
		public String getEverSmoked() {
			return everSmoked;
		}
		public void setEverSmoked(String everSmoked) {
			this.everSmoked = everSmoked;
		}
		public int getSmokeYears() {
			return smokeYears;
		}
		public void setSmokeYears(int smokeYears) {
			this.smokeYears = smokeYears;
		}
		public int getQuitYear() {
			return quitYear;
		}
		public void setQuitYear(int quitYear) {
			this.quitYear = quitYear;
		}
		public String getQuitIntrest() {
			return quitIntrest;
		}
		public void setQuitIntrest(String quitIntrest) {
			this.quitIntrest = quitIntrest;
		}
		public String getPastAlcohal() {
			return pastAlcohal;
		}
		public void setPastAlcohal(String pastAlcohal) {
			this.pastAlcohal = pastAlcohal;
		}
		public String getTabaccoUse() {
			return tabaccoUse;
		}
		public void setTabaccoUse(String tabaccoUse) {
			this.tabaccoUse = tabaccoUse;
		}
		public int getUsingTime() {
			return usingTime;
		}
		public void setUsingTime(int usingTime) {
			this.usingTime = usingTime;
		}
		public String getSexActive() {
			return SexActive;
		}
		public void setSexActive(String sexActive) {
			SexActive = sexActive;
		}
		public String getPartner() {
			return partner;
		}
		public void setPartner(String partner) {
			this.partner = partner;
		}
		public String getSexInfection() {
			return sexInfection;
		}
		public void setSexInfection(String sexInfection) {
			this.sexInfection = sexInfection;
		}
		public String getCaffine() {
			return caffine;
		}
		public void setCaffine(String caffine) {
			this.caffine = caffine;
		}
		public int getUsePerDay() {
			return usePerDay;
		}
		public void setUsePerDay(int usePerDay) {
			this.usePerDay = usePerDay;
		}
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public String getMaritalStatus() {
			return maritalStatus;
		}
		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}
		public String getChild() {
			return child;
		}
		public void setChild(String child) {
			this.child = child;
		}
		public int getNoOfChild() {
			return noOfChild;
		}
		public void setNoOfChild(int noOfChild) {
			this.noOfChild = noOfChild;
		}
		public String[] getChildAge() {
			return childAge;
		}
		public void setChildAge(String[] childAge) {
			this.childAge = childAge;
		}
		public String getMigrantOrSeasonal() {
			return migrantOrSeasonal;
		}
		public void setMigrantOrSeasonal(String migrantOrSeasonal) {
			this.migrantOrSeasonal = migrantOrSeasonal;
		}
		public String getDrinkAlcohal() {
			return drinkAlcohal;
		}
		public void setDrinkAlcohal(String drinkAlcohal) {
			this.drinkAlcohal = drinkAlcohal;
		}
		public String getRecreationalDrugs() {
			return recreationalDrugs;
		}
		public void setRecreationalDrugs(String recreationalDrugs) {
			this.recreationalDrugs = recreationalDrugs;
		}
	}
	
	public static class PrimaryCarePhysician {
		
		private String id;
		private String primaryCarePhysician;
		private String phoneNo;
		private String medicalClinicName;
		private String fax;
		private String clinicAddress;
		private boolean notifyprimaryCarePhysician;
		private String patientSignature;
		private String psDateTime;
		private String guardianSignature;
		private String gsDateTime;
		private String releaseOfInformation;
		private String informationDateTime;
		private String faxed;
		public String getPrimaryCarePhysician() {
			return primaryCarePhysician;
		}
		public void setPrimaryCarePhysician(String primaryCarePhysician) {
			this.primaryCarePhysician = primaryCarePhysician;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getMedicalClinicName() {
			return medicalClinicName;
		}
		public void setMedicalClinicName(String medicalClinicName) {
			this.medicalClinicName = medicalClinicName;
		}
		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}
		public String getClinicAddress() {
			return clinicAddress;
		}
		public void setClinicAddress(String clinicAddress) {
			this.clinicAddress = clinicAddress;
		}
		public boolean isNotifyprimaryCarePhysician() {
			return notifyprimaryCarePhysician;
		}
		public void setNotifyprimaryCarePhysician(boolean notifyprimaryCarePhysician) {
			this.notifyprimaryCarePhysician = notifyprimaryCarePhysician;
		}
		public String getPatientSignature() {
			return patientSignature;
		}
		public void setPatientSignature(String patientSignature) {
			this.patientSignature = patientSignature;
		}
		public String getPsDateTime() {
			return psDateTime;
		}
		public void setPsDateTime(String psDateTime) {
			this.psDateTime = psDateTime;
		}
		public String getGuardianSignature() {
			return guardianSignature;
		}
		public void setGuardianSignature(String guardianSignature) {
			this.guardianSignature = guardianSignature;
		}
		public String getGsDateTime() {
			return gsDateTime;
		}
		public void setGsDateTime(String gsDateTime) {
			this.gsDateTime = gsDateTime;
		}
		public String getReleaseOfInformation() {
			return releaseOfInformation;
		}
		public void setReleaseOfInformation(String releaseOfInformation) {
			this.releaseOfInformation = releaseOfInformation;
		}
		public String getInformationDateTime() {
			return informationDateTime;
		}
		public void setInformationDateTime(String informationDateTime) {
			this.informationDateTime = informationDateTime;
		}
		public String getFaxed() {
			return faxed;
		}
		public void setFaxed(String faxed) {
			this.faxed = faxed;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}
	
	public static class Devices {
	
		private String id;
		private String deviceId;
		private String companyName;
		private String brandName;
		private String gmdnPTName;
		private String snomedCTName;
		private String dateTime;
		private String batch;
		private String serialNumber;
		private boolean identificationCode;
		private String mriSaftyStatus;
		private boolean containsNRL;
		private String status;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDeviceId() {
			return deviceId;
		}
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getGmdnPTName() {
			return gmdnPTName;
		}
		public void setGmdnPTName(String gmdnPTName) {
			this.gmdnPTName = gmdnPTName;
		}
		public String getSnomedCTName() {
			return snomedCTName;
		}
		public void setSnomedCTName(String snomedCTName) {
			this.snomedCTName = snomedCTName;
		}
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		public String getBatch() {
			return batch;
		}
		public void setBatch(String batch) {
			this.batch = batch;
		}
		public String getSerialNumber() {
			return serialNumber;
		}
		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}
		public boolean isIdentificationCode() {
			return identificationCode;
		}
		public void setIdentificationCode(boolean identificationCode) {
			this.identificationCode = identificationCode;
		}
		public String getMriSaftyStatus() {
			return mriSaftyStatus;
		}
		public void setMriSaftyStatus(String mriSaftyStatus) {
			this.mriSaftyStatus = mriSaftyStatus;
		}
		public boolean isContainsNRL() {
			return containsNRL;
		}
		public void setContainsNRL(boolean containsNRL) {
			this.containsNRL = containsNRL;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	}

	
}
