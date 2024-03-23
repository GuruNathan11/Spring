package spring.beacon.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Emergency Data")
public class Emergency {
	
	
	@Id
	private String id;
	private String deviceName;
	private String deviceId;
	
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

	public Emergency(String id, String deviceName, String deviceId) {
		super();
		this.id = id;
		this.setDeviceName(deviceName);
		this.setDeviceId(deviceId);
	}
}
