package spring.beacon.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Document(collection = "scannedData")
public class Model {
    @Id
    private String id;
	private String deviceName;
    private String deviceId;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mma", timezone = "UTC")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mma", timezone = "Asia/Kolkata")
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

	public Model(String id, String deviceName, String deviceId, Date timestamp) {
		super();
		this.id = id;
		this.deviceName = deviceName;
		this.deviceId = deviceId;
		this.timestamp = timestamp;
	}
	public static Model build(String id, String deviceName, String deviceId, Date timestamp) {
		return new Model(id, deviceName, deviceId, timestamp);
}
    

}
