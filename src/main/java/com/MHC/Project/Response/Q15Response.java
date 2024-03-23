package com.MHC.Project.Response;

import java.util.List;

public class Q15Response {
	
	private String id;
	private String q15Date;
	private String patient;
	private String bed;
	private List<slot> slotData;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQ15Date() {
		return q15Date;
	}
	public void setQ15Date(String q15Date) {
		this.q15Date = q15Date;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public List<slot> getSlotData() {
		return slotData;
	}
	public void setSlotData(List<slot> slotData) {
		this.slotData = slotData;
	}
	
	public static class slot {
		
		private String q15Slot;
		private String q15Time;
		private String incharge;
		private String enteredBy;
		private String location;
		private String activity;
		private boolean proximityStatus;
		private boolean slotMissed;
		private boolean slotLateEntry;
		
		public String getQ15Slot() {
			return q15Slot;
		}
		public void setQ15Slot(String q15Slot) {
			this.q15Slot = q15Slot;
		}
		public String getQ15Time() {
			return q15Time;
		}
		public void setQ15Time(String q15Time) {
			this.q15Time = q15Time;
		}
		public String getIncharge() {
			return incharge;
		}
		public void setIncharge(String incharge) {
			this.incharge = incharge;
		}
		public String getEnteredBy() {
			return enteredBy;
		}
		public void setEnteredBy(String enteredBy) {
			this.enteredBy = enteredBy;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getActivity() {
			return activity;
		}
		public void setActivity(String activity) {
			this.activity = activity;
		}
		public boolean isProximityStatus() {
			return proximityStatus;
		}
		public void setProximityStatus(boolean proximityStatus) {
			this.proximityStatus = proximityStatus;
		}
		public boolean isSlotMissed() {
			return slotMissed;
		}
		public void setSlotMissed(boolean slotMissed) {
			this.slotMissed = slotMissed;
		}
		public boolean isSlotLateEntry() {
			return slotLateEntry;
		}
		public void setSlotLateEntry(boolean slotLateEntry) {
			this.slotLateEntry = slotLateEntry;
		}
	}
	
}
