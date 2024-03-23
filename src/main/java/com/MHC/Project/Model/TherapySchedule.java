package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "therapy-schedule")
public class TherapySchedule {
	
	@Id
	private String id;
	private String[] scheduleParticipants;
	private String[] scheduleStaff;
	private String scheduleDate;
	private String scheduleTime;
	private String groupType;
	private String groupTopic;
	private String[] groupGoals;
	private String[] interventions;
	private String duration;
	private String actualTimeAttended;
	private String comments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getScheduleParticipants() {
		return scheduleParticipants;
	}
	public void setScheduleParticipants(String[] scheduleParticipants) {
		this.scheduleParticipants = scheduleParticipants;
	}
	public String[] getScheduleStaff() {
		return scheduleStaff;
	}
	public void setScheduleStaff(String[] scheduleStaff) {
		this.scheduleStaff = scheduleStaff;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGroupTopic() {
		return groupTopic;
	}
	public void setGroupTopic(String groupTopic) {
		this.groupTopic = groupTopic;
	}
	public String[] getGroupGoals() {
		return groupGoals;
	}
	public void setGroupGoals(String[] groupGoals) {
		this.groupGoals = groupGoals;
	}
	public String[] getInterventions() {
		return interventions;
	}
	public void setInterventions(String[] interventions) {
		this.interventions = interventions;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getActualTimeAttended() {
		return actualTimeAttended;
	}
	public void setActualTimeAttended(String actualTimeAttended) {
		this.actualTimeAttended = actualTimeAttended;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void update(TherapySchedule therapy) {
		
		this.setScheduleParticipants(therapy.getScheduleParticipants());
		this.setScheduleStaff(therapy.getScheduleStaff());
		this.setScheduleDate(therapy.getScheduleDate());
		this.setScheduleTime(therapy.getScheduleTime());
		this.setGroupType(therapy.getGroupType());
		this.setGroupTopic(therapy.getGroupTopic());
		this.setGroupGoals(therapy.getGroupGoals());
		this.setInterventions(therapy.getInterventions());
		this.setDuration(therapy.getDuration());
		this.setActualTimeAttended(therapy.getActualTimeAttended());
		this.setComments(therapy.getComments());
		
	}
}
