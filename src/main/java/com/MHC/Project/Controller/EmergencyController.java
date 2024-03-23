package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Emergency1;
import com.MHC.Project.Model.Organization;
import com.MHC.Project.Repository.EmergencyRepo;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/emergency")
@SecurityRequirement(name = "mettlerHealth")
public class EmergencyController {

	@Autowired
	private EmergencyRepo repository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@PostMapping("/add")
	public ResponseEntity<?> saveBeacon(@RequestBody Emergency1 beacon) {
		
		String uid = generateUID();
		
		Optional<Emergency1> emerUuid = repository.findByUuid(beacon.getUuid());
		if (emerUuid.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0189"));
		}
		
		Optional<Emergency1> emerDeviceId = repository.findByDeviceId(beacon.getDeviceId());
		if (emerDeviceId.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0192"));
		}
		
		Optional<Emergency1> emerDeviceName = repository.findByDeviceName(beacon.getDeviceName());
		if (emerDeviceName.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0047"));
		}
		
		Optional<Organization> orgOptional = organizationRepository.findById(beacon.getOrgId());
		if (orgOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
		}
		
		Emergency1 beacons = new Emergency1(uid, beacon.getUuid(), beacon.getDeviceName(), beacon.getDeviceId(), 
											beacon.getDeviceType(), beacon.getModelNumber(), beacon.getOrgId());
		
		repository.save(beacons);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0185");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, beacons);
		return ResponseEntity.ok().body(dataResponse); 
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBeacon() {
		List<Emergency1> get = repository.findAll(); 
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
		return ResponseEntity.ok().body(dataResponse); 
	}
	
	@DeleteMapping("/macDetails/{id}")
	public ResponseEntity<?> deleteMacDetils(@PathVariable String id) {
		
		Optional<Emergency1> beaconOptional = repository.findById(id);
		if (beaconOptional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
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
