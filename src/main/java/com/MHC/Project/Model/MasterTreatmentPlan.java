package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "MasterTreatmentPlan")
public class MasterTreatmentPlan {
	
	@Id
	private String id;
	private String patientName;
	private String pid;
	private String ssNumber;
	private String mrNumber;
	private String problemName;
	private String problemNumber;
	private String dateDefined;
	private String lastVisitId;
	private String byRegisteredNurse;
	private String staff;
	private String[] evidencedBy;
	private boolean longTermGoalOnOff;
	private boolean shortTermGoalOnOff;
	private longTermGoal1 longTermGoal;
	private longTermGoal shortTermGoal;
	private intervention intervention;
	
	public String getLastVisitId() {
		return lastVisitId;
	}

	public void setLastVisitId(String lastVisitId) {
		this.lastVisitId = lastVisitId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSsNumber() {
		return ssNumber;
	}

	public void setSsNumber(String ssNumber) {
		this.ssNumber = ssNumber;
	}

	public String getMrNumber() {
		return mrNumber;
	}

	public void setMrNumber(String mrNumber) {
		this.mrNumber = mrNumber;
	}

	public String getByRegisteredNurse() {
		return byRegisteredNurse;
	}

	public void setByRegisteredNurse(String byRegisteredNurse) {
		this.byRegisteredNurse = byRegisteredNurse;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getProblemNumber() {
		return problemNumber;
	}

	public void setProblemNumber(String problemNumber) {
		this.problemNumber = problemNumber;
	}

	public String getDateDefined() {
		return dateDefined;
	}

	public void setDateDefined(String dateDefined) {
		this.dateDefined = dateDefined;
	}

	public String[] getEvidencedBy() {
		return evidencedBy;
	}

	public void setEvidencedBy(String[] evidencedBy) {
		this.evidencedBy = evidencedBy;
	}

	public boolean isLongTermGoalOnOff() {
		return longTermGoalOnOff;
	}

	public void setLongTermGoalOnOff(boolean longTermGoalOnOff) {
		this.longTermGoalOnOff = longTermGoalOnOff;
	}

	public boolean isShortTermGoalOnOff() {
		return shortTermGoalOnOff;
	}

	public void setShortTermGoalOnOff(boolean shortTermGoalOnOff) {
		this.shortTermGoalOnOff = shortTermGoalOnOff;
	}

	public longTermGoal1 getLongTermGoal() {
		return longTermGoal;
	}

	public void setLongTermGoal(longTermGoal1 longTermGoal) {
		this.longTermGoal = longTermGoal;
	}

	public longTermGoal getShortTermGoal() {
		return shortTermGoal;
	}

	public void setShortTermGoal(longTermGoal shortTermGoal) {
		this.shortTermGoal = shortTermGoal;
	}

	public intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(intervention intervention) {
		this.intervention = intervention;
	}

	public static class longTermGoal1 {

		private String[] header;
		private List<goalLabelName1> goalLabelName;

		
		public String[] getHeader() {
			return header;
		}

		public void setHeader(String[] header) {
			this.header = header;
		}

		public List<goalLabelName1> getGoalLabelName() {
			return goalLabelName;
		}

		public void setGoalLabelName(List<goalLabelName1> goalLabelName) {
			this.goalLabelName = goalLabelName;
		}

	}

	public static class longTermGoal {

		private String[] header;
		private List<goalLabelName> goalLabelName;

		
		public String[] getHeader() {
			return header;
		}
		public void setHeader(String[] header) {
			this.header = header;
		}
		public List<goalLabelName> getGoalLabelName() {
			return goalLabelName;
		}
		public void setGoalLabelName(List<goalLabelName> goalLabelName) {
			this.goalLabelName = goalLabelName;
		}
	}
	
	public static class goalLabelName1 {
		
		private List<column> column1;
		private column column2;
		private column column3;
		
		public List<column> getColumn1() {
			return column1;
		}
		public void setColumn1(List<column> column1) {
			this.column1 = column1;
		}
		public column getColumn2() {
			return column2;
		}
		public void setColumn2(column column2) {
			this.column2 = column2;
		}
		public column getColumn3() {
			return column3;
		}
		public void setColumn3(column column3) {
			this.column3 = column3;
		}
	}
	
	public static class goalLabelName {
		
		private List<column> column1;
		private column column2;
		private column column3;
		private column column4;
		
		public List<column> getColumn1() {
			return column1;
		}
		public void setColumn1(List<column> column1) {
			this.column1 = column1;
		}
		public column getColumn2() {
			return column2;
		}
		public void setColumn2(column column2) {
			this.column2 = column2;
		}
		public column getColumn3() {
			return column3;
		}
		public void setColumn3(column column3) {
			this.column3 = column3;
		}
		public column getColumn4() {
			return column4;
		}
		public void setColumn4(column column4) {
			this.column4 = column4;
		}
	}
	
	public static class column {
		
		private String name;
		private String type;
		
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
	}
	
	
	public static class allIntervention {
		
		private String[] header;
		private List<interventionGoalName> goalLabelName;
		
		public String[] getHeader() {
			return header;
		}
		public void setHeader(String[] header) {
			this.header = header;
		}
		public List<interventionGoalName> getGoalLabelName() {
			return goalLabelName;
		}
		public void setGoalLabelName(List<interventionGoalName> goalLabelName) {
			this.goalLabelName = goalLabelName;
		}
		
	}
	
	public static class interventionGoalName {
		
		private List<column> column1;
		private column column2;
		private column column3;
		private column column4;
		
		public List<column> getColumn1() {
			return column1;
		}
		public void setColumn1(List<column> column1) {
			this.column1 = column1;
		}
		public column getColumn2() {
			return column2;
		}
		public void setColumn2(column column2) {
			this.column2 = column2;
		}
		public column getColumn3() {
			return column3;
		}
		public void setColumn3(column column3) {
			this.column3 = column3;
		}
		public column getColumn4() {
			return column4;
		}
		public void setColumn4(column column4) {
			this.column4 = column4;
		}
		
	}
	
	public static class intervention {
		
		private allIntervention nursingIntervention;
		private allIntervention psychiatristIntervention;
		private allIntervention socialWorkIntervention;
		private allIntervention recreationalTherapyIntervention;
		
		public allIntervention getNursingIntervention() {
			return nursingIntervention;
		}
		public void setNursingIntervention(allIntervention nursingIntervention) {
			this.nursingIntervention = nursingIntervention;
		}
		public allIntervention getPsychiatristIntervention() {
			return psychiatristIntervention;
		}
		public void setPsychiatristIntervention(allIntervention psychiatristIntervention) {
			this.psychiatristIntervention = psychiatristIntervention;
		}
		public allIntervention getSocialWorkIntervention() {
			return socialWorkIntervention;
		}
		public void setSocialWorkIntervention(allIntervention socialWorkIntervention) {
			this.socialWorkIntervention = socialWorkIntervention;
		}
		public allIntervention getRecreationalTherapyIntervention() {
			return recreationalTherapyIntervention;
		}
		public void setRecreationalTherapyIntervention(allIntervention recreationalTherapyIntervention) {
			this.recreationalTherapyIntervention = recreationalTherapyIntervention;
		}
		
		
	}
	
	public void update(MasterTreatmentPlan masterProblem) {
		
		this.setPatientName(masterProblem.getPatientName());
		this.setPid(masterProblem.getPid());
		this.setSsNumber(masterProblem.getSsNumber());
		this.setMrNumber(masterProblem.getMrNumber());
		this.setProblemName(masterProblem.getProblemName());
		this.setProblemNumber(masterProblem.getProblemNumber());
		this.setDateDefined(masterProblem.getDateDefined());
		this.setByRegisteredNurse(masterProblem.getByRegisteredNurse());
		this.setStaff(masterProblem.getStaff());
		this.setEvidencedBy(masterProblem.getEvidencedBy());
		this.setLongTermGoalOnOff(masterProblem.isLongTermGoalOnOff());
		this.setShortTermGoalOnOff(masterProblem.isShortTermGoalOnOff());
		this.setLongTermGoal(masterProblem.getLongTermGoal());
		this.setShortTermGoal(masterProblem.getShortTermGoal());
		this.setIntervention(masterProblem.getIntervention());
	}

}