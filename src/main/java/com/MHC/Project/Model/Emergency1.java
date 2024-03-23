package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Emergency Data")
public class Emergency1 {
	
	
	@Id
	private String id;
	private String uuid;
	private String deviceName;
	private String deviceId;
	private String deviceType;
	private String modelNumber;
	private String orgId;
	
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

	public Emergency1(String id, String uuid, String deviceName, String deviceId,
					  String deviceType, String modelNumber, String orgId) {
		super();
		this.id = id;
		this.setUuid(uuid);
		this.setDeviceName(deviceName);
		this.setDeviceId(deviceId);
		this.setDeviceType(deviceType);
		this.setModelNumber(modelNumber);
		this.setOrgId(orgId);
	}
}

