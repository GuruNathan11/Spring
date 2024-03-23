package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Beacon;
import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Sensor;
import com.MHC.Project.Repository.BeaconRepository;
import com.MHC.Project.Repository.MasterBeaconTableRepository;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.SensorRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "mettlerHealth")
public class BeaconController {

	@Autowired
	private BeaconRepository repository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	MasterBeaconTableRepository masterBeaconRepository;
	
	@Autowired
	SensorRepository sensorRepository;
	
	@PostMapping("/add")
	public ResponseEntity<?> saveBeacon(@RequestBody Beacon beacon) {
		
		String uid = generateUID();
		
		Optional<Organization> organizationOptional = organizationRepository.findById(beacon.getOrganization());
		if (organizationOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0025"));
		}
		
		Optional<Beacon> beaconOptional = repository.findByDeviceId(beacon.getDeviceId());
		if (beaconOptional.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0192"));
		}
		
		Beacon beacons = new Beacon(uid, beacon.getDeviceName(), beacon.getDeviceId(), beacon.getOrganization(), beacon.getUuid());
		
		Optional<Sensor> sensorOptional = sensorRepository.findByDeviceId(beacon.getDeviceId());
		if (sensorOptional.isPresent()) {
			Sensor sensor = sensorOptional.get(); 
			
			beacons.setDeviceName(sensor.getDeviceName());
			beacons.setUuid(sensor.getUuid());
			repository.save(beacons);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0185");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, beacons);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0191"));
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBeacon() {
		List<Beacon> get = repository.findAll(); 
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@DeleteMapping("/macDetails/{id}")
	public ResponseEntity<?> deleteMacDetils(@PathVariable String id) {
		
		Optional<Beacon> beaconOptional = repository.findById(id);
		if (beaconOptional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));		
	}
	
	@GetMapping("/getDevice/{deviceId}")
	public ResponseEntity<?> getDeviceName(@PathVariable String deviceId) {
	    Optional<Beacon> beaconOptional = repository.findByDeviceId(deviceId);
	    if (beaconOptional.isPresent()) {
	        Beacon beacon = beaconOptional.get();
	        String deviceName = beacon.getDeviceName();
//	        return ResponseEntity.ok().body("Device Name: " + deviceName);
	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, beacon);
			return ResponseEntity.ok().body(dataResponse);	   
			}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("/ByOrgId/{orgId}")
	public ResponseEntity<?> getBeaconByOrgId(@PathVariable String orgId) {
		
		List<Beacon> beaconList = repository.findByOrganization(orgId);
		if (!beaconList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, beaconList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

	
	/********** Generate UID ***********/
	public static String generateUID() {
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
