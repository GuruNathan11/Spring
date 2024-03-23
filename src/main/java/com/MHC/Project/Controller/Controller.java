package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Model;
import com.MHC.Project.Repository.Repo;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/beacon")
@SecurityRequirement(name = "mettlerHealth")
public class Controller {

	@Autowired
    private Repo repository;
	
//	@PostMapping("/data")
//	public ResponseEntity<?> saveScannedData(@RequestBody Model request) {
//    Optional<Model> existingRecord = repository.findByDeviceId(request.getDeviceId());
//
//    if (existingRecord.isPresent()) {
//        Model model = existingRecord.get();
////        long currentTimeMillis = System.currentTimeMillis();
////        long timeDifference = currentTimeMillis - model.getTimestamp().getTime();
////        System.out.println(timeDifference);
//        // Allow saving only if the time difference is greater than one minute (60000 milliseconds)
////        if (timeDifference < 60000) {
//////            System.out.print("passed");
////            throw new IllegalArgumentException("You can add data with the same device ID after one minute.");
////        }
//        // Update the existing record with new values
//        model.setDeviceName(request.getDeviceName());
//        model.setRssi(request.getRssi());
//        model.setTimestamp(request.getTimestamp());
//
//        // Save the updated record
//        Model saved = repository.save(model);
//        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
//        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, saved);
//        return ResponseEntity.ok(dataResponse);
//    } else {
//        // No existing record found, create a new one
//        String uid = PatientService.generateUID();
//        Model model = Model.build(uid, request.getDeviceName(), request.getDeviceId(), request.getRssi(), request.getTimestamp(), request.getDistance());
//        Model saved = repository.save(model);
//        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
//        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, saved);
//        return ResponseEntity.ok(dataResponse);
//    }
//}

	@PostMapping("/data")
	public ResponseEntity<?> saveScannedData(@RequestBody Model request) {
	    Optional<Model> existingRecord = repository.findByDeviceId(request.getDeviceId());

	    if (existingRecord.isPresent()) {
	        Model model = existingRecord.get();

	        // Update the existing record with new values
	        model.setDeviceName(request.getDeviceName());
	        model.setRssi(request.getRssi());
	        model.setTimestamp(request.getTimestamp());

	        // Calculate and set the distance
	        double lowerThreshold = -70;
	        double upperThreshold = -55;

	        double adjustedRssi = model.getRssi();

	        // Limit the RSSI value to the specified range
	        if (adjustedRssi <= lowerThreshold) {
	            adjustedRssi = lowerThreshold;
	        } else if (adjustedRssi >= upperThreshold) {
	            adjustedRssi = upperThreshold;
	        }

	        if (!Double.isNaN(adjustedRssi)) {
	            double txPower = -55; // Replace with the actual transmit power of your beacon
	            double pathLossExponent = 3; // Adjust as needed based on your environment

	            double calculatedDistance = Math.pow(10, (txPower - adjustedRssi) / (10 * pathLossExponent));

	            // Format the calculated distance to two decimal places
	            String formattedDistance = String.format("%.2f", calculatedDistance);

	            model.setDistance(Double.parseDouble(formattedDistance));
	        } else {
	            // Handle invalid RSSI value
	            return ResponseEntity.badRequest().body("Invalid RSSI value");
	        }

	        // Save the updated record
	        Model saved = repository.save(model);
	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, saved);
	        return ResponseEntity.ok(dataResponse);
	    } else {
	        // No existing record found, create a new one
	        String uid = PatientService.generateUID();
	        Model model = Model.build(uid, request.getDeviceName(), request.getDeviceId(), request.getRssi(), request.getTimestamp(), request.getDistance());
	        
	        // Calculate and set the distance for the new record
	        double lowerThreshold = -70;
	        double upperThreshold = -55;

	        double adjustedRssi = model.getRssi();

	        // Limit the RSSI value to the specified range
	        if (adjustedRssi <= lowerThreshold) {
	            adjustedRssi = lowerThreshold;
	        } else if (adjustedRssi >= upperThreshold) {
	            adjustedRssi = upperThreshold;
	        }

	        if (!Double.isNaN(adjustedRssi)) {
	            double txPower = -55; // Replace with the actual transmit power of your beacon
	            double pathLossExponent = 3; // Adjust as needed based on your environment

	            double calculatedDistance = Math.pow(10, (txPower - adjustedRssi) / (10 * pathLossExponent));

	            // Format the calculated distance to two decimal places
	            String formattedDistance = String.format("%.2f", calculatedDistance);

	            model.setDistance(Double.parseDouble(formattedDistance));
	        } else {
	            // Handle invalid RSSI value
	            return ResponseEntity.badRequest().body("Invalid RSSI value");
	        }

	        Model saved = repository.save(model);
	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, saved);
	        return ResponseEntity.ok(dataResponse);
	    }
	}


	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBeacon() {
		List<Model> get = repository.findAll(); 
		
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
        return ResponseEntity.ok(dataResponse);
	}
	
	@GetMapping("/getById/{deviceId}")
	public ResponseEntity<?> getById(@PathVariable String deviceId) {
		Optional<Model> get = repository.findByDeviceId(deviceId); 
		if(get.isPresent()) {
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
        return ResponseEntity.ok(dataResponse);
	}
	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0048"));	
}
}



//package com.MHC.Project.Controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.MHC.Project.Model.Model;
//import com.MHC.Project.Repository.Repo;
//import com.MHC.Project.Service.BeaconService;
//
//
//@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
//@RestController
//@RequestMapping("/api/beacon")
//public class Controller {
//
//	@Autowired
//	private BeaconService Service;
//
//	@Autowired
//    private Repo repository;
//	
//	@GetMapping
//	public String Welcome() {
//	return ("Welcome to Beacon Data...");	
//	}
//	
//	@PostMapping("/data")
//	public ResponseEntity<?> saveOrganization(@RequestBody @Validated Model Request) {
//	    try {
//	    	Model savedOrganization = Service.saveOrganization(Request);
//	    	return ResponseEntity.ok().body(savedOrganization);   
//	    } catch (IllegalArgumentException e) {
//	    	return ResponseEntity.ok().body("Error");   
//	    }
//	} 
//
//	@GetMapping("/getAll")
//	public ResponseEntity<?> getAllBeacon() {
//		List<Model> get = repository.findAll(); 
//		Map<String, Object> response = new HashMap<>();
//		response.put("message", "get the list of beacon");
//		response.put("data", get);
//		return ResponseEntity.ok().body(response);
//	}
//}

