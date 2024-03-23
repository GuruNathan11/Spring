package com.MHC.Project.Controller;

import java.util.List;
import java.util.Map;

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

import com.MHC.Project.Model.KBeacon;
import com.MHC.Project.Repository.KBeaconRepo;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/beacon")
@SecurityRequirement(name = "mettlerHealth")
public class KBeaconController {
	private final KBeaconRepo beaconRepository;

	@Autowired
	public KBeaconController(KBeaconRepo beaconRepository) {
		this.beaconRepository = beaconRepository;
	}

	@PostMapping
	public ResponseEntity<?> createBeacon(@RequestBody Map<String, Object> beaconData) {
		String uid = generateUID();

		KBeacon kBeacon = new KBeacon(uid, beaconData);
		kBeacon = beaconRepository.save(kBeacon);

		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0185");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, kBeacon);
		return ResponseEntity.ok().body(dataResponse);
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllBeacon() {
		List<KBeacon> get = beaconRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
		return ResponseEntity.ok().body(dataResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBeaconById(@PathVariable String id) {
		KBeacon kBeacon = beaconRepository.findById(id).orElse(null);

		if (kBeacon != null) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, kBeacon);
			return ResponseEntity.ok().body(dataResponse);
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));

		}
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
