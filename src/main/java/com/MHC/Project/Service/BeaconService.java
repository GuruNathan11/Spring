package com.MHC.Project.Service;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Model.Model;
import com.MHC.Project.Repository.Repo;

@Service
public class BeaconService {

	@Autowired
	private Repo repository;

	public Model saveOrganization(Model request) {
	    // Find the most recent record with the same device ID
	    Model lastRecord = repository.findTopByDeviceIdOrderByTimestampDesc(request.getDeviceId());
	    if (lastRecord != null) {
	        // Calculate the time difference in milliseconds
	        long currentTimeMillis = System.currentTimeMillis();
	        long timeDifference = currentTimeMillis - lastRecord.getTimestamp().getTime();

	        // Convert timestamps to UTC
	        long currentUTCTime = toUTC(currentTimeMillis);
	        long lastRecordUTCTime = toUTC(lastRecord.getTimestamp().getTime());
System.out.println("currentUTCTime "+currentUTCTime);
System.out.print(" lastRecordUTCTime  "+lastRecordUTCTime);
	        // Allow saving only if the time difference is greater than one minute (60000 milliseconds)
	        if (timeDifference < 120000) {
	            System.out.print("passed");
	            throw new IllegalArgumentException("You can add data with the same device ID after one minute.");
	        }
	    }

	    String uid = generateUID();
	    Model organization = Model.build(uid, request.getDeviceName(), request.getDeviceId(), request.getRssi(), request.getTimestamp(), request.getDistance());
	    return repository.save(organization);
	}

	// Helper method to convert timestamp to UTC
	private long toUTC(long timestamp) {
	    return timestamp - TimeZone.getDefault().getOffset(timestamp);
	}


	private String generateUID() {
		// Generate a random alphanumeric ID with 10 digits
		String characters = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
}