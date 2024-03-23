package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "TreatmentPlanProblem")
public class TreatmentPlanProblem {

	@Id
	private String id;
	private String problemName;
	private String problemNumber;
	private String dateDefined;
	private String[] evidencedBy;
	private boolean longTermGoalOnOff;
	private boolean shortTermGoalOnOff;
	private boolean nursingIntervention;
	private boolean psychiatristIntervention;
	private boolean socialWorkIntervention;
	private boolean recreationalTherapyIntervention;
	private longTermGoal1 longTermGoal;
	private longTermGoal shortTermGoal;
	private intervention intervention;
	

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

	public boolean isNursingIntervention() {
		return nursingIntervention;
	}

	public void setNursingIntervention(boolean nursingIntervention) {
		this.nursingIntervention = nursingIntervention;
	}

	public boolean isPsychiatristIntervention() {
		return psychiatristIntervention;
	}

	public void setPsychiatristIntervention(boolean psychiatristIntervention) {
		this.psychiatristIntervention = psychiatristIntervention;
	}

	public boolean isSocialWorkIntervention() {
		return socialWorkIntervention;
	}

	public void setSocialWorkIntervention(boolean socialWorkIntervention) {
		this.socialWorkIntervention = socialWorkIntervention;
	}

	public boolean isRecreationalTherapyIntervention() {
		return recreationalTherapyIntervention;
	}

	public void setRecreationalTherapyIntervention(boolean recreationalTherapyIntervention) {
		this.recreationalTherapyIntervention = recreationalTherapyIntervention;
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

		private String[] headers;
		private List<goalLabelName1> goalLabelName;

		public String[] getHeaders() {
			return headers;
		}

		public void setHeaders(String[] headers) {
			this.headers = headers;
		}

		public List<goalLabelName1> getGoalLabelName() {
			return goalLabelName;
		}

		public void setGoalLabelName(List<goalLabelName1> goalLabelName) {
			this.goalLabelName = goalLabelName;
		}

	}

	public static class longTermGoal {

		private String[] headers;
		private List<goalLabelName> goalLabelName;

		public String[] getHeaders() {
			return headers;
		}
		public void setHeaders(String[] headers) {
			this.headers = headers;
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
	
	public static class intervention {
		
		private longTermGoal nursingIntervention;
		private longTermGoal psychiatristIntervention;
		private longTermGoal socialWorkIntervention;
		private longTermGoal recreationalTherapyIntervention;
		public longTermGoal getNursingIntervention() {
			return nursingIntervention;
		}
		public void setNursingIntervention(longTermGoal nursingIntervention) {
			this.nursingIntervention = nursingIntervention;
		}
		public longTermGoal getPsychiatristIntervention() {
			return psychiatristIntervention;
		}
		public void setPsychiatristIntervention(longTermGoal psychiatristIntervention) {
			this.psychiatristIntervention = psychiatristIntervention;
		}
		public longTermGoal getSocialWorkIntervention() {
			return socialWorkIntervention;
		}
		public void setSocialWorkIntervention(longTermGoal socialWorkIntervention) {
			this.socialWorkIntervention = socialWorkIntervention;
		}
		public longTermGoal getRecreationalTherapyIntervention() {
			return recreationalTherapyIntervention;
		}
		public void setRecreationalTherapyIntervention(longTermGoal recreationalTherapyIntervention) {
			this.recreationalTherapyIntervention = recreationalTherapyIntervention;
		}
	}
	
	public void update(TreatmentPlanProblem problem) {
		
		this.setProblemName(problem.getProblemName());
		this.setProblemNumber(problem.getProblemNumber());
		this.setDateDefined(problem.getDateDefined());
		this.setEvidencedBy(problem.getEvidencedBy());
		this.setLongTermGoalOnOff(problem.isLongTermGoalOnOff());
		this.setShortTermGoalOnOff(problem.isShortTermGoalOnOff());
		this.setNursingIntervention(problem.isNursingIntervention());
		this.setPsychiatristIntervention(problem.isPsychiatristIntervention());
		this.setSocialWorkIntervention(problem.isSocialWorkIntervention());
		this.setRecreationalTherapyIntervention(problem.isRecreationalTherapyIntervention());
		this.setLongTermGoal(problem.getLongTermGoal());
		this.setShortTermGoal(problem.getShortTermGoal());
		this.setIntervention(problem.getIntervention());
	}
}
