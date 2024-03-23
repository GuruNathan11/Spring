package spring.beacon.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Document(collection = "EmergencyScannedData")
public class EmerDataModel {
    @Id
    private String id;
	private String deviceName;
    private String deviceId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mma", timezone = "UTC")
    private Date timestamp;
    
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

	public EmerDataModel(String id, String deviceName, String deviceId, Date timestamp) {
		super();
		this.id = id;
		this.deviceName = deviceName;
		this.deviceId = deviceId;
		this.timestamp = timestamp;
	}
	public static EmerDataModel build(String id, String deviceName, String deviceId, Date timestamp) {
		return new EmerDataModel(id, deviceName, deviceId, timestamp);
}
    

}
