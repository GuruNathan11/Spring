package com.MHC.Project.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="PSConfiguration")
public class PSConfiguration {
	
	@Id
	private String id;
	private String date;
	private List<Shift> shift; 
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Shift> getShift() {
		return shift;
	}
	public void setShift(List<Shift> shift) {
		this.shift = shift;
	}

	public static class Shift{
		
		private String shiftName;
		private String rnIncharge;
		private String startTime;
		private String endTime;
		private List<Schedule> schedule;

		public String getShiftName() {
			return shiftName;
		}
		public void setShiftName(String shiftName) {
			this.shiftName = shiftName;
		}
		public String getRnIncharge() {
			return rnIncharge;
		}
		public void setRnIncharge(String rnIncharge) {
			this.rnIncharge = rnIncharge;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		
		public List<Schedule> getSchedule() {
			return schedule;
		}
		public void setSchedule(List<Schedule> schedule) {
			this.schedule = schedule;
		}
		
		public static class Schedule{
			
			private String time;
			private List<bedStaff> bedStaff;
			
			public String getTime() {
				return time;
			}
			public void setTime(String time) {
				this.time = time;
			}
			public List<bedStaff> getBedStaff() {
				return bedStaff;
			}
			public void setBedStaff(List<bedStaff> bedStaff) {
				this.bedStaff = bedStaff;
			}
		}		
		
		public static class bedStaff {
			
			private String[] roomRange;
			private String startRoomNo;
			private String endRoomNo;
			private String staff;
			private String deviceId;
			
			public String[] getRoomRange() {
				return roomRange;
			}
			public void setRoomRange(String[] roomRange) {
				this.roomRange = roomRange;
			}
			public String getStartRoomNo() {
				return startRoomNo;
			}
			public void setStartRoomNo(String startRoomNo) {
				this.startRoomNo = startRoomNo;
			}
			public String getEndRoomNo() {
				return endRoomNo;
			}
			public void setEndRoomNo(String endRoomNo) { 
				this.endRoomNo = endRoomNo;
			}
			public String getStaff() {
				return staff;
			}
			public void setStaff(String staff) {
				this.staff = staff;
			}
			public String getDeviceId() {
				return deviceId;
			}
			public void setDeviceId(String deviceId) {
				this.deviceId = deviceId;
			}
		}
	}

	public PSConfiguration(String id, String date, List<Shift> shift, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.date = date;
		this.shift = shift;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public static PSConfiguration build(String id, String date, List<Shift> shift, String createdAt, String updatedAt) {
		return new PSConfiguration(id,date,shift, createdAt, updatedAt);
	}
	
	public void  update(PSConfiguration psConfig) {
		this.setDate(psConfig.getDate());
		this.setShift(psConfig.getShift());
	}	
}