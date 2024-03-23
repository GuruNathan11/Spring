package spring.beacon.model;


//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document(collection = "beaconData")
//public class KBeacon {
//    @Id
//    private String id;
//    private String data; // Store the data as a JSON string
//
//    
//
//    public KBeacon(String id, String data) {
//		super();
//		this.id = id;
//		this.data = data;
//	}
//
//	public KBeacon(String data) {
//        this.data = data;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//}

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "beaconData")
public class KBeacon {
    @Id
    private String id;
    private Map<String, Object> data; // Store the data as a Map

   public KBeacon() {
	   
   }

    public KBeacon(String id, Map<String, Object> data) {
		super();
		this.id = id;
		this.data = data;
	}

	public KBeacon(Map<String, Object> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
