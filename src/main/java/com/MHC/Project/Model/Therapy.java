package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "therapy")
public class Therapy {
	
	@Id
	private String id;
	private String scheduleId;
	private therapy therapy;
	private String[] participation;
	private String[] affect;
	private String[] behavior;
	private String[] interaction;
	private String[] thoughtProcess;
	private String shortTermGoal;
	private String progressTowardsShortTermGoal;
	private List<oneOnOneCounselling> oneOnOneCounselling;
	private String signature;
	private String date;
	
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public therapy getTherapy() {
		return therapy;
	}
	public void setTherapy(therapy therapy) {
		this.therapy = therapy;
	}
	public String[] getParticipation() {
		return participation;
	}
	public void setParticipation(String[] participation) {
		this.participation = participation;
	}
	public String[] getAffect() {
		return affect;
	}
	public void setAffect(String[] affect) {
		this.affect = affect;
	}
	public String[] getBehavior() {
		return behavior;
	}
	public void setBehavior(String[] behavior) {
		this.behavior = behavior;
	}
	public String[] getInteraction() {
		return interaction;
	}
	public void setInteraction(String[] interaction) {
		this.interaction = interaction;
	}
	public String[] getThoughtProcess() {
		return thoughtProcess;
	}
	public void setThoughtProcess(String[] thoughtProcess) {
		this.thoughtProcess = thoughtProcess;
	}
	public String getShortTermGoal() {
		return shortTermGoal;
	}
	public void setShortTermGoal(String shortTermGoal) {
		this.shortTermGoal = shortTermGoal;
	}
	public String getProgressTowardsShortTermGoal() {
		return progressTowardsShortTermGoal;
	}
	public void setProgressTowardsShortTermGoal(String progressTowardsShortTermGoal) {
		this.progressTowardsShortTermGoal = progressTowardsShortTermGoal;
	}
	public List<oneOnOneCounselling> getOneOnOneCounselling() {
		return oneOnOneCounselling;
	}
	public void setOneOnOneCounselling(List<oneOnOneCounselling> oneOnOneCounselling) {
		this.oneOnOneCounselling = oneOnOneCounselling;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public static class therapy {
		
		private String[] attendants;
		private String addressographStamp;
		private String date;
		private String startTime;
		private String ofParticipants;
		private String groupFocus;
		
		public String[] getAttendants() {
			return attendants;
		}
		public void setAttendants(String[] attendants) {
			this.attendants = attendants;
		}
		public String getAddressographStamp() {
			return addressographStamp;
		}
		public void setAddressographStamp(String addressographStamp) {
			this.addressographStamp = addressographStamp;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getOfParticipants() {
			return ofParticipants;
		}
		public void setOfParticipants(String ofParticipants) {
			this.ofParticipants = ofParticipants;
		}
		public String getGroupFocus() {
			return groupFocus;
		}
		public void setGroupFocus(String groupFocus) {
			this.groupFocus = groupFocus;
		}
	}
	
	public static class oneOnOneCounselling {
		
		private String patientName;
		private String[] staffAssign;
		private String duration;
		private String focus;
		private String patientResponse;
		
		public String getPatientName() {
			return patientName;
		}
		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}
		public String[] getStaffAssign() {
			return staffAssign;
		}
		public void setStaffAssign(String[] staffAssign) {
			this.staffAssign = staffAssign;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getFocus() {
			return focus;
		}
		public void setFocus(String focus) {
			this.focus = focus;
		}
		public String getPatientResponse() {
			return patientResponse;
		}
		public void setPatientResponse(String patientResponse) {
			this.patientResponse = patientResponse;
		}
	}
	
	public void update(Therapy therapy) {
		
		this.setTherapy(therapy.getTherapy());
		this.setParticipation(therapy.getParticipation());
		this.setAffect(therapy.getAffect());
		this.setBehavior(therapy.getBehavior());
		this.setInteraction(therapy.getInteraction());
		this.setThoughtProcess(therapy.getThoughtProcess());
		this.setShortTermGoal(therapy.getShortTermGoal());
		this.setProgressTowardsShortTermGoal(therapy.getProgressTowardsShortTermGoal());
		this.setOneOnOneCounselling(therapy.getOneOnOneCounselling());
		this.setSignature(therapy.getSignature());
		this.setDate(therapy.getDate());
		
	}
}
