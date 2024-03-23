package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.EmerDataModel;
import com.MHC.Project.Model.Emergency1;
import com.MHC.Project.Repository.EmerDataRepo;
import com.MHC.Project.Repository.EmergencyRepo;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;



@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/emergency/beacon")
@SecurityRequirement(name = "mettlerHealth")
public class EmerDataController {

	@Autowired
    private EmerDataRepo repository;
	
	@Autowired
	EmergencyRepo emergencyRepository;
	
	@GetMapping
	public String Welcome() {
	return ("Welcome to Beacon Data...");	
	}
	
	@PostMapping("/data")
	public ResponseEntity<?> saveOrganization(@RequestBody @Validated EmerDataModel request) {
//		System.out.println("FailedController");

		String uid = PatientService.generateUID();
//
		EmerDataModel lastRecord = repository.findTopByUuidOrderByTimestampDesc(request.getUuid());
		// System.out.println("Failed");
//		if (lastRecord != null) {
//			// Calculate the time difference in milliseconds
//			long timeDifference = System.currentTimeMillis() - lastRecord.getTimestamp().getTime();
//
//			// Allow saving only if the time difference is greater than one minute (60000
//			// milliseconds)
//			if (timeDifference < 60000) {
//				throw new IllegalArgumentException("You can add data with the same device ID after one minute.");
//			}
//		}
		
		Optional<EmerDataModel> emerDataOptional = repository.findByUuid(request.getUuid());
		if (emerDataOptional.isPresent()) {
			EmerDataModel emerData = emerDataOptional.get();
			
			emerData.setDistance(request.getDistance());
			emerData.setTimestamp(request.getTimestamp());
			repository.save(emerData);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, emerData);
			return ResponseEntity.ok().body(dataResponse);
		}
		
		Optional<Emergency1> emergencyOptional = emergencyRepository.findByUuid(request.getUuid());
		if (emergencyOptional.isPresent()) {
			Emergency1 emergency1 = emergencyOptional.get();
			
			request.setId(uid);
			request.setDeviceId(emergency1.getDeviceId());
			request.setDeviceName(emergency1.getDeviceName());
			request.setDeviceType(emergency1.getDeviceType());
			request.setModelNumber(emergency1.getModelNumber());
			repository.save(request);
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, request);
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0190"));
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBeacon() {
		List<EmerDataModel> get = repository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
        return ResponseEntity.ok(dataResponse); 
	}
	
	@DeleteMapping("deleteById{id}")
	public ResponseEntity<?> deleteEmerDataById(@PathVariable String id) {
		
		Optional<EmerDataModel> emerDataOptional = repository.findById(id);
		if (emerDataOptional.isPresent()) {
			EmerDataModel emerData = emerDataOptional.get();
			
			repository.delete(emerData);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
}
