package com.MHC.Project.Model;


import java.util.List;
import jakarta.persistence.Id;
public class Organization {

	@Id
	private String id;
	private List<OrganizationDetails> organizationdetails;
	private List<contact> contact;
	private String email;
	private String mobileNumber;
	private String websiteUrl;
	private SHIFT shift;
	private String proximityVerification;
	private String geofencing;
	private String q15Access;
	private List<PointOfContact> pointofcontact;
	private List<HIPAAPrivacyOfficer> hippaprivacyofficer;
	private List<HIPAASecurityOfficer> hippassecurityofficer;
	private String userType;
	private String profile;
	private String createdAt;
	private String updatedAt;

	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getProximityVerification() {
		return proximityVerification;
	}
	public void setProximityVerification(String proximityVerification) {
		this.proximityVerification = proximityVerification;
	}
	public String getGeofencing() {
		return geofencing;
	}
	public void setGeofencing(String geofencing) {
		this.geofencing = geofencing;
	}
	public String getQ15Access() {
		return q15Access;
	}
	public void setQ15Access(String q15Access) {
		this.q15Access = q15Access;
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
	public List<OrganizationDetails> getOrganizationdetails() {
		return organizationdetails;
	}
	public void setOrganizationdetails(List<OrganizationDetails> organizationdetails) {
		this.organizationdetails = organizationdetails;
	}
	public List<contact> getContact() {
		return contact;
	}
	public void setContact(List<contact> contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public List<PointOfContact> getPointofcontact() {
		return pointofcontact;
	}
	public void setPointofcontact(List<PointOfContact> pointofcontact) {
		this.pointofcontact = pointofcontact;
	}
	public List<HIPAAPrivacyOfficer> getHippaprivacyofficer() {
		return hippaprivacyofficer;
	}
	public void setHippaprivacyofficer(List<HIPAAPrivacyOfficer> hippaprivacyofficer) {
		this.hippaprivacyofficer = hippaprivacyofficer;
	}
	public List<HIPAASecurityOfficer> getHippassecurityofficer() {
		return hippassecurityofficer;
	}
	public void setHippassecurityofficer(List<HIPAASecurityOfficer> hippassecurityofficer) {
		this.hippassecurityofficer = hippassecurityofficer;
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
	public void setUserType(String userType) {
		this.userType = userType;
	}

	//*********** Organization Details ****************\\
	public static class OrganizationDetails {
		private String name;
		private String type;
		private String tin;
		private String npi;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getTin() {
			return tin;
		}
		public void setTin(String tin) {
			this.tin = tin;
		}
		public String getNpi() {
			return npi;
		}
		public void setNpi(String npi) {
			this.npi = npi;
		}	
	}

	//*********** Contact Details ****************\\
	public static class contact {	
		private String addressLine1;
		private String addressLine2;
		private String city;
	    private String state;
	    private int Zip;
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
		public int getZip() {
			return Zip;
		}
		public void setZip(int zip) {
			Zip = zip;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
	}

	//*********** Point Of Contact Details ****************\\
	public static class PointOfContact {
		public String name;
		public String email;
		public String phoneNumber;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
	}
	
	//*********** HIPAA Privacy Officer Details ****************\\
	public static class HIPAAPrivacyOfficer {
		private String name;
		private String email;
		private String mobile;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}	
	}
		
	//*********** HIPAA Security Officer Details ****************\\
	public static class HIPAASecurityOfficer {
		private String name;
		private String email;
		private String mobile;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
	}
	
	public Organization(String id, List<OrganizationDetails> organizationdetails, List<contact> contact, String email,
			String mobileNumber, String websiteUrl,SHIFT shift, String proximityVerification, String geofencing, String q15Access, List<PointOfContact> pointofcontact, List<HIPAAPrivacyOfficer> hippaprivacyofficer,
			List<HIPAASecurityOfficer> hippassecurityofficer,String userType, String profile, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.organizationdetails = organizationdetails;
		this.contact = contact;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.websiteUrl = websiteUrl;
		this.shift = shift;
		this.proximityVerification = proximityVerification;
		this.geofencing = geofencing;
		this.q15Access = q15Access;
		this.pointofcontact = pointofcontact;
		this.hippaprivacyofficer = hippaprivacyofficer;
		this.hippassecurityofficer = hippassecurityofficer;
		this.userType = userType;
		this.profile = profile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
	}

	public static Organization build(String id, List<OrganizationDetails> organizationdetails, List<contact> contact,
			String email, String mobileNumber, String websiteUrl,SHIFT shift, String proximityVerification, String geofencing, String q15Access, List<PointOfContact> pointofcontact, 
			List<HIPAAPrivacyOfficer> hippaprivacyofficer, List<HIPAASecurityOfficer> hippassecurityofficer,String userType, String profile, String createdAt, String updatedAt) {
		return new Organization(id, organizationdetails, contact, email, mobileNumber, websiteUrl,shift, proximityVerification, geofencing, q15Access, pointofcontact,
				hippaprivacyofficer, hippassecurityofficer,userType, profile, createdAt, updatedAt);
	}

	public void update(Organization organizationRequest) {
		
		this.setId(organizationRequest.getId());
		this.setOrganizationdetails(organizationRequest.getOrganizationdetails());
		this.setContact(organizationRequest.getContact());
		this.setEmail(organizationRequest.getEmail());
		this.setMobileNumber(organizationRequest.getMobileNumber());
		this.setWebsiteUrl(organizationRequest.getWebsiteUrl());
		this.setShift(organizationRequest.getShift());
		this.setProximityVerification(organizationRequest.getProximityVerification());
		this.setGeofencing(organizationRequest.getGeofencing());
		this.setQ15Access(organizationRequest.getQ15Access());
		this.setPointofcontact(organizationRequest.getPointofcontact());
		this.setHippaprivacyofficer(organizationRequest.getHippaprivacyofficer());
		this.setHippassecurityofficer(organizationRequest.getHippassecurityofficer());
		this.setUserType(organizationRequest.getUserType());
		this.setProfile(organizationRequest.getProfile()); 
		
	}
	public SHIFT getShift() {
		return shift;
	}
	public void setShift(SHIFT shift) {
		this.shift = shift;
	}	
	
	public static class SHIFT{
		private String duration;
		private String startTime;
		
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}	
		
	}
	
}

