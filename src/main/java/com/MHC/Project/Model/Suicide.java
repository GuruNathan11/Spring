package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "suicide")
public class Suicide {

	@Id
	private String id;
	private String pid;
	private String lastVisit;
	private String patientName;
	private String ssNumber;
	private String mrNumber;
	private String dateAssessed;
	private String registeredNurse;
	private String staffName;
	private suicide suicide;
	private scoring scoring;
	
	
	public String getDateAssessed() {
		return dateAssessed;
	}
	public void setDateAssessed(String dateAssessed) {
		this.dateAssessed = dateAssessed;
	}
	public String getRegisteredNurse() {
		return registeredNurse;
	}
	public void setRegisteredNurse(String registeredNurse) {
		this.registeredNurse = registeredNurse;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public suicide getSuicide() {
		return suicide;
	}
	public void setSuicide(suicide suicide) {
		this.suicide = suicide;
	}
	public scoring getScoring() {
		return scoring;
	}
	public void setScoring(scoring scoring) {
		this.scoring = scoring;
	}
	
	
	public static class suicide {
		
		private String haveyouwishedyouwereDeadOryoucouldgotoSleepAndNotWakeUp;
		private String haveyouActuallyHadAnyThoughtsOfKillingYourself;
		private String haveyoubeenthinkingaboutHowyouMightKillYourself;
		private String haveyouhadtheseThoughtsAndhadsomeIntentionofActingonThem;
		private String haveyoustartedtoWorkoutOrWorkedOutTheDetailsofHowToKillYourself;
		private String haveyoueverDoneAnythingOrStartedtoDoAnything;
		private String howlongagoDidyouDoanyofThese;
		private String answer;
		
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public String getHaveyouwishedyouwereDeadOryoucouldgotoSleepAndNotWakeUp() {
			return haveyouwishedyouwereDeadOryoucouldgotoSleepAndNotWakeUp;
		}
		public void setHaveyouwishedyouwereDeadOryoucouldgotoSleepAndNotWakeUp(
				String haveyouwishedyouwereDeadOryoucouldgotoSleepAndNotWakeUp) {
			this.haveyouwishedyouwereDeadOryoucouldgotoSleepAndNotWakeUp = haveyouwishedyouwereDeadOryoucouldgotoSleepAndNotWakeUp;
		}
		public String getHaveyouActuallyHadAnyThoughtsOfKillingYourself() {
			return haveyouActuallyHadAnyThoughtsOfKillingYourself;
		}
		public void setHaveyouActuallyHadAnyThoughtsOfKillingYourself(String haveyouActuallyHadAnyThoughtsOfKillingYourself) {
			this.haveyouActuallyHadAnyThoughtsOfKillingYourself = haveyouActuallyHadAnyThoughtsOfKillingYourself;
		}
		public String getHaveyoubeenthinkingaboutHowyouMightKillYourself() {
			return haveyoubeenthinkingaboutHowyouMightKillYourself;
		}
		public void setHaveyoubeenthinkingaboutHowyouMightKillYourself(String haveyoubeenthinkingaboutHowyouMightKillYourself) {
			this.haveyoubeenthinkingaboutHowyouMightKillYourself = haveyoubeenthinkingaboutHowyouMightKillYourself;
		}
		public String getHaveyouhadtheseThoughtsAndhadsomeIntentionofActingonThem() {
			return haveyouhadtheseThoughtsAndhadsomeIntentionofActingonThem;
		}
		public void setHaveyouhadtheseThoughtsAndhadsomeIntentionofActingonThem(
				String haveyouhadtheseThoughtsAndhadsomeIntentionofActingonThem) {
			this.haveyouhadtheseThoughtsAndhadsomeIntentionofActingonThem = haveyouhadtheseThoughtsAndhadsomeIntentionofActingonThem;
		}
		public String getHaveyoustartedtoWorkoutOrWorkedOutTheDetailsofHowToKillYourself() {
			return haveyoustartedtoWorkoutOrWorkedOutTheDetailsofHowToKillYourself;
		}
		public void setHaveyoustartedtoWorkoutOrWorkedOutTheDetailsofHowToKillYourself(
				String haveyoustartedtoWorkoutOrWorkedOutTheDetailsofHowToKillYourself) {
			this.haveyoustartedtoWorkoutOrWorkedOutTheDetailsofHowToKillYourself = haveyoustartedtoWorkoutOrWorkedOutTheDetailsofHowToKillYourself;
		}
		public String getHaveyoueverDoneAnythingOrStartedtoDoAnything() {
			return haveyoueverDoneAnythingOrStartedtoDoAnything;
		}
		public void setHaveyoueverDoneAnythingOrStartedtoDoAnything(String haveyoueverDoneAnythingOrStartedtoDoAnything) {
			this.haveyoueverDoneAnythingOrStartedtoDoAnything = haveyoueverDoneAnythingOrStartedtoDoAnything;
		}
		public String getHowlongagoDidyouDoanyofThese() {
			return howlongagoDidyouDoanyofThese;
		}
		public void setHowlongagoDidyouDoanyofThese(String howlongagoDidyouDoanyofThese) {
			this.howlongagoDidyouDoanyofThese = howlongagoDidyouDoanyofThese;
		}
		
	}
	
	public static class scoring {
		
		private String suicideRisk;
		private String signature;
		private String date;
		private String time;
		
		public String getSuicideRisk() {
			return suicideRisk;
		}
		public void setSuicideRisk(String suicideRisk) {
			this.suicideRisk = suicideRisk;
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
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
	}
	
}
