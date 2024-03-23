package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BeaconMac")
public class Beacon {
	@Id
	private String id;
	private String deviceName;
	private String deviceId;
	private String organization;
	private String uuid;
		
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
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

	public Beacon(String id, String deviceName, String deviceId, String organization, String uuid) {
		super();
		this.id = id;
		this.setDeviceName(deviceName);
		this.setDeviceId(deviceId);
		this.setOrganization(organization);
		this.setUuid(uuid);
	}
}
