package com.MHC.Project.Controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.Sensor;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.SensorRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/sensor")
@SecurityRequirement(name = "mettlerHealth")
public class SensorController {
	
	@Autowired
	SensorRepository repository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveSensor(@RequestBody Sensor sensor) {
		
		String uid = PatientService.generateUID();
		
		Optional<Sensor> sensorList = repository.findByUuid(sensor.getUuid());
		if (sensorList.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0189"));
		}
		Optional<Sensor> sensorDeviceId = repository.findByDeviceId(sensor.getDeviceId());
		if (sensorDeviceId.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0192"));
		}
		Optional<Sensor> sensorDeviceName = repository.findByDeviceName(sensor.getDeviceName());
		if (sensorDeviceName.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0047"));
		}
		Optional<Organization> orgOptional = organizationRepository.findById(sensor.getOrgId());
		if (orgOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
		}
		
		sensor.setId(uid);
		repository.save(sensor);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, sensor);
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@GetMapping("/getAllByorgId/{orgId}")
	public ResponseEntity<?> getAllSensorDetailsByOrgID(@PathVariable String orgId,
	                                                    @RequestParam(defaultValue = "0") int page,
	                                                    @RequestParam(defaultValue = "10") int pageSize) {
	    if (page == -1) {
	        List<Sensor> sensorList = repository.findAllByOrgId(orgId);
	        
	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, sensorList);
	        return ResponseEntity.ok().body(dataResponse); 
	    } else {
	        Pageable pageable = PageRequest.of(page, pageSize);
	        Page<Sensor> sensorList = repository.findAllByOrgId(orgId, pageable);
	        
	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, sensorList);
	        return ResponseEntity.ok().body(dataResponse);
	    }
	}
	
	@GetMapping("/getAllSensorByOrgId/{orgId}")
	public ResponseEntity<?> getAllSensorDetailsByOrgId(@PathVariable String orgId) {
		
		List<Sensor> sensorList = repository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, sensorList);
		return ResponseEntity.ok().body(dataResponse);
	}
	
	@GetMapping("/getUnassigned/{orgId}")
	public ResponseEntity<?> getUnassignedDeviceByOrgId(@PathVariable String orgId) {
		
		List<Sensor> unassignedSensorList = new ArrayList<>();
		
		List<Sensor> sensorList = repository.findAllByOrgId(orgId);
		if (sensorList != null) {
			for (Sensor sensor : sensorList) {
			 	String deviceId = sensor.getDeviceId();
			 	
			 	Optional<Patient> patientOptional = patientRepository.findByBeaconDeviceAndOrganization(deviceId,orgId);
			 	Optional<Patient> patientOptional1 = patientRepository.findByBeaconDevice1AndOrganization(deviceId,orgId);

			 	if (patientOptional.isEmpty() && patientOptional1.isEmpty() && patientOptional != null && patientOptional1 != null) {
					
			 		unassignedSensorList.add(sensor);
				}
			}
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, unassignedSensorList);
		return ResponseEntity.ok().body(dataResponse);		 
	}
	
	@GetMapping("/Byuuid/{uuid}")
	public ResponseEntity<?> getSensorByUuid(@PathVariable String uuid) {
		
		Optional<Sensor> sensorList = repository.findByUuid(uuid);
		if (!sensorList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, sensorList);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@PutMapping("updateSensorTableByDeviceName/{id}")
	public ResponseEntity<?> updateSensorByDeviceName(@PathVariable String id,
													  @RequestBody Sensor sensor) {
		
		Optional<Sensor> sensorOptional = repository.findById(id);
		if (sensorOptional.isPresent()) {
			Sensor sensorClass = sensorOptional.get();
			
			sensorClass.update(sensor);
			repository.save(sensorClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, sensorClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@DeleteMapping("/deleteByDeviceName/{deviceName}")
	public ResponseEntity<?> deleteSensorByDeviceName(@PathVariable String deviceName) {
		
		Optional<Sensor> sensorList = repository.findByDeviceName(deviceName);
		if (!sensorList.isEmpty()) {
			Sensor sensor = sensorList.get();
			repository.delete(sensor);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
