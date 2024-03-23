package com.MHC.Project.Request;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;

import com.MHC.Project.Model.Patient.Contact1;
import com.MHC.Project.Model.Patient.Devices;
import com.MHC.Project.Model.Patient.Employer;
import com.MHC.Project.Model.Patient.FamilyHealth;
import com.MHC.Project.Model.Patient.Guardian;
import com.MHC.Project.Model.Patient.Insurance;
import com.MHC.Project.Model.Patient.Misc;
import com.MHC.Project.Model.Patient.PrimaryCarePhysician;
import com.MHC.Project.Model.Patient.SocialHistory;
import com.MHC.Project.Model.Patient.Stats;
import com.MHC.Project.Model.Patient.basicDetails;
import com.MHC.Project.Model.Patient.resource;

public class PatientRequest {

	private String id;
	private String active;
	private List<resource> resource;
	private List<basicDetails> basicDetails;
	@Email
	private String email;
	private String organization;
	private List<Contact1> contact;
	private String userType;
	private String beaconDevice;
	private String uuid;
	private List<Employer> employer;
	private List<Guardian> guardian;
	private List<Misc> misc;
	private List<Stats> stats;
	private List<Insurance> insurance;
	private List<FamilyHealth> familyHealth;
	private List<SocialHistory> socialHistory;
	private List<PrimaryCarePhysician> primaryCarePhysician;
	private String deviceId;
	private List<Devices> devices;
	private List<String> deviceId1;
	private List<String> beaconDevice1;
	private Map<String, String> uuid1;
	
	public List<String> getDeviceId1() {
		return deviceId1;
	}
	public void setDeviceId1(List<String> deviceId1) {
		this.deviceId1 = deviceId1;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public List<SocialHistory> getSocialHistory() {
		return socialHistory;
	}
	public void setSocialHistory(List<SocialHistory> socialHistory) {
		this.socialHistory = socialHistory;
	}
	public List<FamilyHealth> getFamilyHealth() {
		return familyHealth;
	}
	public void setFamilyHealth(List<FamilyHealth> familyHealth) {
		this.familyHealth = familyHealth;
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
	public List<resource> getResource() {
		return resource;
	}
	public void setResource(List<resource> resource) {
		this.resource = resource;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getBeaconDevice() {
		return beaconDevice;
	}
	public void setBeaconDevice(String beaconDevice) {
		this.beaconDevice = beaconDevice;
	}
	
	public String getUsername() {
        if (basicDetails != null && !basicDetails.isEmpty()) {
            List<basicDetails.Name> nameList = basicDetails.get(0).getName();
            String birthDate = basicDetails.get(0).getBirthDate();
//            String family = nameList.get(0).getFamily();
            if (nameList != null && !nameList.isEmpty() && birthDate != null) {
                String givenName = nameList.get(0).getGiven();
//                String familyName = nameList.get(0).getFamily();
                String yearOfBirth = birthDate.substring(0, 4);
                String givenNameInitials = givenName.substring(0, Math.min(givenName.length(), 3));
//                String familyNameInitial = familyName.substring(0, 1);
                return givenNameInitials + yearOfBirth;
            }
        }
        return null;
    }
	
	public String getPassword() {
        if (basicDetails != null && !basicDetails.isEmpty()) {
               List<basicDetails.Name> nameList = basicDetails.get(0).getName();
               String ssn = basicDetails.get(0).getSsn();          
               if (nameList != null && !nameList.isEmpty() && ssn != null) {
                   String givenName = nameList.get(0).getGiven();
//                   String email = getEmail().substring(3, 5);
                   String ssnlast = ssn.substring(5, 9);
                   return givenName +"@"+ ssnlast;
               }
          }
           return null;
       }
}