package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Q15-Form-Config")
public class Config {

	@Id
	private String id;
	private String PID;
	private String Location;
	private String Activity;
	private String q15Date;
	private String q15Slot;
	private String q15Time;
	private String enteredBy;
	private String timeStamp;
	private String timestampCreatedAt;
	private String timestampUpdatedAt;
	private boolean breathing;
	private String remarks;
	private String reason;
	private String shift;
	private String shiftIncharge;
	private String deviceId;
	private String deviceName;
	private String rssi;
	private double distance;
	private boolean skippedScanning;
	private String message;
	private String locationName;
	private String activityName;
	
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
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceTimeStamp) {
		this.deviceName = deviceTimeStamp;
	}
	public String getRssi() {
		return rssi;
	}
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}
	public boolean isBreathing() {
		return breathing;
	}
	public void setBreathing(boolean breathing) {
		this.breathing = breathing;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTimestampCreatedAt() {
		return timestampCreatedAt;
	}
	public void setTimestampCreatedAt(String timestampCreatedAt) {
		this.timestampCreatedAt = timestampCreatedAt;
	}
	public String getTimestampUpdatedAt() {
		return timestampUpdatedAt;
	}
	public void setTimestampUpdatedAt(String timestampUpdatedAt) {
		this.timestampUpdatedAt = timestampUpdatedAt;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		this.PID = pID;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		this.Location = location;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		this.Activity = activity;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getQ15Date() {
		return q15Date;
	}
	public void setQ15Date(String q15Date) {
		this.q15Date = q15Date;
	}
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

	// ************ Constructor Method ************\\

	public Config(String id, String PID, String Location, String Activity, String q15Date, String q15Slot, String q15Time,
			String enteredBy, String timeStamp,String shift,String shiftIncharge,String rssi, String deviceId, String deviceName, String locationName, String activityName) {
		this.id = id;
		this.PID = PID;
		this.Location = Location;
		this.Activity = Activity;
		this.q15Date = q15Date;
		this.q15Slot=q15Slot;
		this.q15Time = q15Time;
		this.enteredBy = enteredBy;
		this.timeStamp = timeStamp;
		this.shift = shift;
		this.shiftIncharge = shiftIncharge;
		this.rssi = rssi;
		this.deviceId= deviceId;
		this.deviceName = deviceName;
		this.locationName = locationName;
		this.activityName = activityName;
	}

	public static Config build(String id, String PID, String Location, String Activity, String q15Date, String q15Slot, String q15Time,
			String enteredBy, String TimeStamp, String shift, String shiftIncharge,String rssi,String deviceId, String deviceName, String locationName, String activityName) {
		return new Config(id, PID, Location, Activity, q15Date, q15Slot, q15Time, enteredBy, TimeStamp, shift, shiftIncharge,rssi,deviceId,deviceName, locationName, activityName);
	}

	public void update(Config config) {
		this.setActivity(config.getActivity());
		this.setPID(config.getPID());
		this.setLocation(config.getLocation());
		this.setQ15Date(config.getQ15Date());
		this.setQ15Slot(config.getQ15Slot());
		this.setQ15Time(config.getQ15Time());
		this.setEnteredBy(config.getEnteredBy());
		this.setTimeStamp(config.getTimeStamp());
		this.setShift(config.getShift());
		this.setShiftIncharge(config.getShiftIncharge());
		this.setRssi(config.getRssi());
		this.setDeviceId(config.getDeviceId());
		this.setDeviceName(config.getDeviceName());
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getShiftIncharge() {
		return shiftIncharge;
	}
	public void setShiftIncharge(String shiftIncharge) {
		this.shiftIncharge = shiftIncharge;
	}
	public boolean isSkippedScanning() {
		return skippedScanning;
	}
	public void setSkippedScanning(boolean skippedScanning) {
		this.skippedScanning = skippedScanning;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
