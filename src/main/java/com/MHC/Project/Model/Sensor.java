package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "sensor")
public class Sensor {
	
	@Id
	private String id;
	private String uuid;
	private String deviceName;
	private String deviceId;
	private String deviceType;
	private String modelNumber;
	private String orgId;
	
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public void update(Sensor sensor) {

		this.setUuid(sensor.getUuid());
		this.setDeviceId(sensor.getDeviceId());
		this.setDeviceName(sensor.getDeviceName());
		this.setDeviceType(sensor.getDeviceType());
		this.setModelNumber(sensor.getModelNumber());
		this.setOrgId(sensor.getOrgId());

	}
	
}
