package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Visit-Admit")
public class PatientVisitAdmit {
	@Id
	private String id;
	private String activeFlag;
	private List<resource> resource;
	private List<Coding> coding;
	private List<Visit> visit;
	private String patientid;
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


	private String code;

	public String getId() {
		return id;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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
	
	public List<Coding> getCoding() {
		return coding;
	}
	public void setCoding(List<Coding> coding) {
		this.coding = coding;
	}
	public List<Visit> getVisit() {
		return visit;
	}
	public void setVisit(List<Visit> visit) {
		this.visit = visit;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public PatientVisitAdmit(String id, String activeFlag, List<resource> resource, List<Coding> coding,
			List<Visit> visit, String patientid,String code) {
		super();
		this.id = id;
		this.activeFlag = activeFlag;
		this.resource = resource;
		this.coding = coding;
		this.visit = visit;
		this.patientid = patientid;
		this.setCode(code);
	}

	public static PatientVisitAdmit build(String id, String activeFlag, List<resource> resource,
			List<Coding> coding, List<Visit> visit, String patientid,String code) {
		return new PatientVisitAdmit(id, activeFlag, resource, coding, visit, patientid,code);
	}

	public void update(PatientVisitAdmit patientvisitadmitRequest) {
		this.setActiveFlag(patientvisitadmitRequest.getActiveFlag());
		this.setResource(patientvisitadmitRequest.getResource());
		this.setCoding(patientvisitadmitRequest.getCoding());
		this.setVisit(patientvisitadmitRequest.getVisit());
		this.setPatientid(patientvisitadmitRequest.getPatientid());
		this.setCode(patientvisitadmitRequest.getCode());
	}

	// ------Resource ---------//
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

	// -----BasicDetails ----- //
	public static class Coding {
	        private String system;
			private String code;
			private String display;

			public String getSystem() {
				return system;
			}
			public void setSystem(String system) {
				this.system = "http://terminology.hl7.org/CodeSystem/v3-ActCode";
			}
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = "IP";
			}
			public String getDisplay() {
				return display;
			}
			public void setDisplay(String display) {
				this.display = "Inpatient encounter";
			}
		}
	

	// ------Visit and admit ---//
	public static class Visit {
		private String[] visitoradmitType;
		private String activityStartDate;
		private String visitStartDate;
		private String visitEndDate;
		private String activityEndDate;
		private List<Preadmit> preadmit;
		private List<Admit> admit;

	    public String[] getVisitoradmitType() {
			return visitoradmitType;
		}
		public void setVisitoradmitType(String[] visitoradmitType) {
			this.visitoradmitType = visitoradmitType;
		}
		public String getActivityStartDate() {
			return activityStartDate;
		}
		public void setActivityStartDate(String activityStartDate) {
			this.activityStartDate = activityStartDate;
		}
		public String getVisitStartDate() {
			return visitStartDate;
		}
		public void setVisitStartDate(String visitStartDate) {
			this.visitStartDate = visitStartDate;
		}
		public String getVisitEndDate() {
			return visitEndDate;
		}
		public void setVisitEndDate(String visitEndDate) {
			this.visitEndDate = visitEndDate;
		}
		public String getActivityEndDate() {
			return activityEndDate;
		}
		public void setActivityEndDate(String activityEndDate) {
			this.activityEndDate = activityEndDate;
		}
		public List<Admit> getAdmit() {
			return admit;
		}
		public void setAdmit(List<Admit> admit) {
			this.admit = admit;
		}
		
		public List<Preadmit> getPreadmit() {
			return preadmit;
		}
		public void setPreadmit(List<Preadmit> preadmit) {
			this.preadmit = preadmit;
		}
		public static class Preadmit{
	    	private String expectedarrivalDateTime;
	    	private String expecteddischDateTime;
	    	private String pStatus;
	    	
	    	public String getExpectedarrivalDateTime() {
				return expectedarrivalDateTime;
			}
			public void setExpectedarrivalDateTime(String expectedarrivalDateTime) {
				this.expectedarrivalDateTime = expectedarrivalDateTime;
			}
			public String getExpecteddischDateTime() {
				return expecteddischDateTime;
			}
			public void setExpecteddischDateTime(String expecteddischDateTime) {
				this.expecteddischDateTime = expecteddischDateTime;
			}
			public String getpStatus() {
				return pStatus;
			}
			public void setpStatus(String pStatus) {
				this.pStatus = pStatus;
			}
			
		
		 }

		public static class Admit {
	        private String admitDate;
			private String dischDate;
			private String aStatus;
			private String[] patientLocation;
			private String patientRoom;
			private String ptAccount;
			private String altPatientID;
			private String rapidOrderTypeStatus;
			private String rapidOrderOrigTechCode;
			private String rapidOrderUpdateTechCode;
			private List<AdmitTech> admittech;

            public String getDischDate() {
				return dischDate;
			}
			public void setDischDate(String dischDate) {
				this.dischDate = dischDate;
			}
			public void setAdmitDate(String admitDate) {
				this.admitDate = admitDate;
			}
			public String getAdmitDate() {
				return admitDate;
			}
			
			
			public String getaStatus() {
				return aStatus;
			}
			public void setaStatus(String aStatus) {
				this.aStatus = aStatus;
			}
			public String[] getPatientLocation() {
				return patientLocation;
			}
			public void setPatientLocation(String[] patientLocation) {
				this.patientLocation = patientLocation;
			}
			public String getPatientRoom() {
				return patientRoom;
			}
			public void setPatientRoom(String patientRoom) {
				this.patientRoom = patientRoom;
			}
			public String getPtAccount() {
				return ptAccount;
			}
			public void setPtAccount(String ptAccount) {
				this.ptAccount = ptAccount;
			}
			public String getAltPatientID() {
				return altPatientID;
			}
			public void setAltPatientID(String altPatientID) {
				this.altPatientID = altPatientID;
			}
			public String getRapidOrderTypeStatus() {
				return rapidOrderTypeStatus;
			}
			public void setRapidOrderTypeStatus(String rapidOrderTypeStatus) {
				this.rapidOrderTypeStatus = rapidOrderTypeStatus;
			}
			public String getRapidOrderOrigTechCode() {
				return rapidOrderOrigTechCode;
			}
			public void setRapidOrderOrigTechCode(String rapidOrderOrigTechCode) {
				this.rapidOrderOrigTechCode = rapidOrderOrigTechCode;
			}
			public String getRapidOrderUpdateTechCode() {
				return rapidOrderUpdateTechCode;
			}
			public void setRapidOrderUpdateTechCode(String rapidOrderUpdateTechCode) {
				this.rapidOrderUpdateTechCode = rapidOrderUpdateTechCode;
			}
			public List<AdmitTech> getAdmittech() {
				return admittech;
			}
			public void setAdmittech(List<AdmitTech> admittech) {
				this.admittech = admittech;
			}
		}
		
		private static class AdmitTech {
			private String admittechCode;
			private String[] admittingPhysician;
			private String[] referringPhysician;
			private String[] admittingNurse;
			private String[] speciality;
			private String diagnosis;
			private String AdmitComment;
			private String[] admissionType;
			private String[] admissionSource;
			private String[] admissionReason;
			
			private String DischDx;

			public String getAdmittechCode() {
				return admittechCode;
			}
			public void setAdmittechCode(String admittechCode) {
				this.admittechCode = admittechCode;
			}
			
			
			public String[] getAdmittingPhysician() {
				return admittingPhysician;
			}
			public void setAdmittingPhysician(String[] admittingPhysician) {
				this.admittingPhysician = admittingPhysician;
			}
			public String[] getAdmissionReason() {
				return admissionReason;
			}
			public void setAdmissionReason(String[] admissionReason) {
				this.admissionReason = admissionReason;
			}
			public String[] getReferringPhysician() {
				return referringPhysician;
			}
			public void setReferringPhysician(String[] referringPhysician) {
				this.referringPhysician = referringPhysician;
			}
			public String getDiagnosis() {
				return diagnosis;
			}
			public void setDiagnosis(String diagnosis) {
				this.diagnosis = diagnosis;
			}
			public String getAdmitComment() {
				return AdmitComment;
			}
			public void setAdmitComment(String admitComment) {
				AdmitComment = admitComment;
			}
			
			public String[] getAdmittingNurse() {
				return admittingNurse;
			}
			public void setAdmittingNurse(String[] admittingNurse) {
				this.admittingNurse = admittingNurse;
			}
			public String[] getSpeciality() {
				return speciality;
			}
			public void setSpeciality(String[] speciality) {
				this.speciality = speciality;
			}
			public String[] getAdmissionType() {
				return admissionType;
			}
			public void setAdmissionType(String[] admissionType) {
				this.admissionType = admissionType;
			}
			
			public String[] getAdmissionSource() {
				return admissionSource;
			}
			public void setAdmissionSource(String[] admissionSource) {
				this.admissionSource = admissionSource;
			}
			
			public String getDischDx() {
				return DischDx;
			}
			public void setDischDx(String dischDx) {
				DischDx = dischDx;
			}
		}
	}
}
