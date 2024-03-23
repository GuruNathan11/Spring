package com.MHC.Project.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Model.Config;
import com.MHC.Project.Model.Q15Form;
import com.MHC.Project.Model.Sensor;
import com.MHC.Project.Model.SensorServer;
import com.MHC.Project.Repository.ConfigRepository;
import com.MHC.Project.Repository.Q15FormRepository;
import com.MHC.Project.Repository.SensorRepository;
import com.MHC.Project.Repository.SensorServerRepository;

@Service
public class ConfigService {

	@Autowired
	ConfigRepository repository;
	
	@Autowired
	SensorRepository sRepository;
	
	@Autowired
	SensorServerRepository ssRepository;
	
	@Autowired
	Q15FormRepository q15FormRepository;

	   public Config saveConfig(Config config) {
	        // Check if a configuration with the given PID, Q15Date, and Q15Time already exists
	        Optional<Config> existingConfig = repository.findByPIDAndQ15DateAndQ15Time(config.getPID(),
	                config.getQ15Date(), config.getQ15Time());
	        
	        Optional<Sensor> sensor = sRepository.findByDeviceId(config.getDeviceId());
	        Optional<SensorServer> sensors = ssRepository.findByDeviceId(config.getDeviceId());
	        if (existingConfig.isPresent()) {
	            // Configuration with the same PID, Q15Date, and Q15Time already exists, update the other fields
	            Config existingConfigData = existingConfig.get();
	            
	            List<Q15Form> formList = q15FormRepository.findAll();
	    		if (formList != null) {
	    			for (Q15Form form : formList) {
	    				
	    				Map<String, Object> location = form.getLocation();
	    				Map<String, Object> activity = form.getActivity();
	    				
	    				if (location != null) {
	    					String locationName = (String) location.get(config.getLocation());
	    					existingConfigData.setLocationName(locationName);
	    					break;
	    				}
	    				if (activity != null) {
	    					String activityName = (String) activity.get(config.getActivity());
	    					existingConfigData.setActivityName(activityName);
	    					break;
	    				}
	    			}
	    		}
	            
	            existingConfigData.setBreathing(config.isBreathing());
	            existingConfigData.setRemarks(config.getRemarks());
	            existingConfigData.setActivity(config.getActivity());
	            existingConfigData.setLocation(config.getLocation());
	            existingConfigData.setEnteredBy(config.getEnteredBy());
	            existingConfigData.setShift(config.getShift());
	            existingConfigData.setTimestampUpdatedAt(getTimeStamp());
	            existingConfigData.setDeviceId(config.getDeviceId());
	            existingConfigData.setReason(config.getReason());
	            existingConfigData.setRssi(config.getRssi());
	            // Save the updated configuration
	            if (sensor.isPresent()) {
		            existingConfigData.setDeviceName(sensor.get().getDeviceName());
				}
	            return repository.save(existingConfigData);
	        } else {
	            // Configuration with the same PID, Q15Date, and Q15Time does not exist, create a new record
	            String uid = PatientService.generateUID();

	            Config newConfig = Config.build(uid, config.getPID(), config.getLocation(), config.getActivity(),
	                    config.getQ15Date(), config.getQ15Slot(), config.getQ15Time(), config.getEnteredBy(),
	                    getTimeStamp(),config.getShift(),config.getShiftIncharge(),config.getRssi(),config.getDeviceId(),config.getDeviceName(), config.getLocationName(), config.getActivityName());
	            
	         // set the location and activity name
				String locationKey = config.getLocation();
				String activityKey = config.getActivity();
				
				List<Q15Form> formList = q15FormRepository.findAll(); 
				if (formList != null) {
					for (Q15Form form : formList) {
						Map<String, Object> activity = form.getActivity();
						
						if (activity != null) {
							String activityName = (String) activity.get(activityKey);

							newConfig.setActivityName(activityName);
							break;
						}

					}
				}
				if (formList != null) {
					for (Q15Form form : formList) {
						Map<String, Object> location = form.getLocation();
						if (location != null) {
							String locationName = (String) location.get(locationKey);
							newConfig.setLocationName(locationName);
							break;
						}
					}
				}
	            
	            newConfig.setBreathing(config.isBreathing()); 
	            newConfig.setRemarks(config.getRemarks());
//	            newConfig.setRssi(sensor.get().get());
	            newConfig.setReason(config.getReason());
	            newConfig.setId(uid);
	            newConfig.setSkippedScanning(config.isSkippedScanning());
	            newConfig.setMessage(config.getMessage());
	            newConfig.setTimestampCreatedAt(getTimeStamp()); // Set created timestamp
	            if (sensor.isPresent()) {
		            newConfig.setDeviceName(sensor.get().getDeviceName());
				}
	            if (sensors.isPresent()) {
		            newConfig.setDistance(sensors.get().getDistance());
				}
	            return repository.save(newConfig);
	        }
	    }
	// ***************** Get All Config ****************\\
	public List<Config> getAllConfig() {
		return repository.findAll();
	}

	public String getTimeStamp() {
		LocalDateTime now = LocalDateTime.now();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timestamp = now.format(formatter);
		return timestamp;
	}
}
