package com.MHC.Project.Model;
public class Content {

		private String id;
		private String heading;
		private String institutionName;
		private String policyTitle;
		private String policyNumber;
		private String effectiveDate;
		private String department;
		private SubHeading[] subHeading;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getHeading() {
			return heading;
		}
		public void setHeading(String heading) {
			this.heading = heading;
		}
		public String getInstitutionName() {
			return institutionName;
		}
		public void setInstitutionName(String institutionName) {
			this.institutionName = institutionName;
		}
		public String getPolicyTitle() {
			return policyTitle;
		}
		public void setPolicyTitle(String policyTitle) {
			this.policyTitle = policyTitle;
		}
		public String getPolicyNumber() {
			return policyNumber;
		}
		public void setPolicyNumber(String policyNumber) {
			this.policyNumber = policyNumber;
		}
		public String getEffectiveDate() {
			return effectiveDate;
		}
		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = effectiveDate;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public SubHeading[] getSubHeading() {
			return subHeading;
		}
		public void setSubHeading(SubHeading[] subHeading) {
			this.subHeading = subHeading;
		}
				
	}

	