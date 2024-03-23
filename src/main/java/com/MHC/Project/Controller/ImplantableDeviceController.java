package com.MHC.Project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.MHC.Project.Model.ImplantableDevice;
import com.MHC.Project.Repository.ImplantableDeviceCustomRepository;
import com.MHC.Project.Repository.ImplantableDeviceRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/ImplantableDevice")
@SecurityRequirement(name = "mettlerHealth")
public class ImplantableDeviceController {
	
	@Autowired
	ImplantableDeviceRepository repository;
	
	@Autowired
	ImplantableDeviceCustomRepository customRepository;
	
	
	@PostMapping("/storing")
	public ResponseEntity<?> storeDeviceData(@RequestParam String deviceId) {

			String uid = PatientService.generateUID();

			RestTemplate restTemplate = new RestTemplate();

			String apiURL = "https://accessgudid.nlm.nih.gov/api/v2/devices/lookup.json?di=" + deviceId;
			Object incomingJson = restTemplate.getForObject(apiURL, Object.class);

			ImplantableDevice data = new ImplantableDevice();
			data.setData(incomingJson);
			data.setId(uid);

			repository.save(data);

			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, data);
			return ResponseEntity.ok().body(dataResponse);

		
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllDeviceIncomingData() {

		List<ImplantableDevice> dataList = repository.findAll();

		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, dataList);
		return ResponseEntity.ok().body(dataResponse);

	}

	@GetMapping("/ByDeviceId/{deviceId}")
	public ResponseEntity<?> findDataByDeviceId(String deviceId) {
		List<ImplantableDevice> data = customRepository.findByDeviceId(deviceId);
		if (!data.isEmpty()) {
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, data);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
