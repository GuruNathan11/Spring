package com.MHC.Project.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Sensor;
import com.MHC.Project.Model.SensorServer;
import com.MHC.Project.Repository.SensorRepository;
import com.MHC.Project.Repository.SensorServerRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/sensor_server")
@SecurityRequirement(name = "mettlerHealth")
public class SensorServerController {
	
	@Autowired
	SensorServerRepository repository;
	
	@Autowired
	SensorRepository sensorRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveSensorServerData(@RequestBody SensorServer server) {

		String uid = PatientService.generateUID();

		Optional<SensorServer> serverOptional = repository.findByUuid(server.getUuid());
		if (serverOptional.isPresent()) {
			SensorServer serverClass = serverOptional.get();

			serverClass.setTimeStamp(server.getTimeStamp());
//			serverClass.setDistance(server.getDistance() * 3.28084);
			serverClass.setDistance(server.getDistance());
			repository.save(serverClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, serverClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		Optional<Sensor> sensorOptional = sensorRepository.findByUuid(server.getUuid());
		if (sensorOptional.isPresent()) {
			Sensor sensor = sensorOptional.get();

			server.setDeviceId(sensor.getDeviceId());
			server.setDeviceName(sensor.getDeviceName());
			server.setTimeStamp(server.getTimeStamp());
			server.setDistance(server.getDistance() * 3.28084);
			server.setId(uid);
			repository.save(server);

			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, server);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0190"));
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllSensorServer() {
		
		List<SensorServer> serverList = repository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, serverList);
		return ResponseEntity.ok().body(dataResponse);
		
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAllSensorServer() {

		List<SensorServer> serverList = repository.findAll();
		repository.deleteAll(serverList); 
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));

	}

}
