package com.MHC.Project.Model;

import java.util.List;

public class Q15Report {

	private String id;
	private String q15Date;
	private List<DataEntry> data;
	private String pid;
	private ShiftIncharge shiftIncharge;
	private EnteredBy enteredBy;
	
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	
	public List<DataEntry> getData() {
		return data;
	}

	public void setData(List<DataEntry> data) {
		this.data = data;
	}


	public ShiftIncharge getShiftIncharge() {
		return shiftIncharge;
	}

	public void setShiftIncharge(ShiftIncharge shiftIncharge) {
		this.shiftIncharge = shiftIncharge;
	}


	public EnteredBy getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(EnteredBy enteredBy) {
		this.enteredBy = enteredBy;
	}


	public static class DataEntry{
		private String remarks;
		private String q15Slot;
		private String q15Time;
		private String location;
		private String activity;
		private String locationName;
		private String activityName;
		
		public String getQ15Slot() {
			return q15Slot;
		}
		public void setQ15Slot(String q15Slot) {
			this.q15Slot = q15Slot;
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
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getQ15Time() {
			return q15Time;
		}
		public void setQ15Time(String q15Time) {
			this.q15Time = q15Time;
		}
		public String getLocationName() {
			return locationName;
		}
		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}
		public String getActivityName() {
			return activityName;
		}
		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}	
	}
	
	public static class EnteredBy {

		private shift shiftA;
		private shift shiftB;
		private shift shiftC;
		public shift getShiftA() {
			return shiftA;
		}
		public void setShiftA(shift shiftA) {
			this.shiftA = shiftA;
		}
		public shift getShiftB() {
			return shiftB;
		}
		public void setShiftB(shift shiftB) {
			this.shiftB = shiftB;
		}
		public shift getShiftC() {
			return shiftC;
		}
		public void setShiftC(shift shiftC) {
			this.shiftC = shiftC;
		}
	}
	

	public static class shift {
		private String slot1;
		private String slot2;
		private String slot3;
		private String slot4;
		public String getSlot1() {
			return slot1;
		}
		public void setSlot1(String slot1) {
			this.slot1 = slot1;
		}
		public String getSlot2() {
			return slot2;
		}
		public void setSlot2(String slot2) {
			this.slot2 = slot2;
		}
		public String getSlot3() {
			return slot3;
		}
		public void setSlot3(String slot3) {
			this.slot3 = slot3;
		}
		public String getSlot4() {
			return slot4;
		}
		public void setSlot4(String slot4) {
			this.slot4 = slot4;
		}
	}
	
	public static class ShiftIncharge{
		private String shiftInchargeA;
		private String shiftInchargeB;
		private String shiftInchargeC;
		
		public String getShiftInchargeA() {
			return shiftInchargeA;
		}
		public void setShiftInchargeA(String shiftInchargeA) {
			this.shiftInchargeA = shiftInchargeA;
		}
		public String getShiftInchargeB() {
			return shiftInchargeB;
		}
		public void setShiftInchargeB(String shiftInchargeB) {
			this.shiftInchargeB = shiftInchargeB;
		}
		public String getShiftInchargeC() {
			return shiftInchargeC;
		}
		public void setShiftInchargeC(String shiftInchargeC) {
			this.shiftInchargeC = shiftInchargeC;
		}
		
		
	}
}
