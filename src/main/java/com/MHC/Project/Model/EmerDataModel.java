package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Document(collection = "EmergencyScannedData")
public class EmerDataModel {
    @Id
    private String id;
    private String uuid;
	private String deviceName;
    private String deviceId;
    private String deviceType;
    private String modelNumber;
    private double distance;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date timestamp;
    
    public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

//	public EmerDataModel(String id, String uuid, String deviceName, String deviceId, 
//			String deviceType, String modelNumber, double distance, Date timestamp) {
//		super();
//		this.id = id;
//		this.uuid = uuid;
//		this.deviceName = deviceName;
//		this.deviceId = deviceId;
//		this.deviceType = deviceType;
//		this.modelNumber = modelNumber;
//		this.distance = distance;
//		this.timestamp = timestamp;
//	}
//	public static EmerDataModel build(String id, String uuid, String deviceName, String deviceId, 
//									  String deviceType, String modelNumber, double distance, Date timestamp) {
//		return new EmerDataModel(id, uuid, deviceName, deviceId, deviceType, modelNumber, distance, timestamp);
//}
    

}

