//package com.MHC.Project.Controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.MHC.Project.Model.MasterBeaconTable;
//import com.MHC.Project.Repository.MasterBeaconTableRepository;
//import com.MHC.Project.Response.DataResponse;
//import com.MHC.Project.Response.VerifyResponseFactory;
//import com.MHC.Project.Service.PatientService;
//
//@RestController
//@RequestMapping("/api/masterBeaconTable")
//public class MasterBeaconTableController {
//	
//	@Autowired
//	MasterBeaconTableRepository repository;
//	
//	@PostMapping("/register")
//	public ResponseEntity<?> saveMasterBeaconTable(@RequestBody MasterBeaconTable masterBeacon) {
//		
//		String uid = PatientService.generateUID();
//		
//		Optional<MasterBeaconTable> masterBeaconDeviceId = repository.findByDeviceId(masterBeacon.getDeviceId());
//		if (masterBeaconDeviceId.isPresent()) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0192"));
//		}
//		Optional<MasterBeaconTable> masterBeaconDeviceName = repository.findByDeviceName(masterBeacon.getDeviceName());
//		if (masterBeaconDeviceName.isPresent()) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0193"));
//		}
//		Optional<MasterBeaconTable> masterBeaconUUID = repository.findByUuid(masterBeacon.getUuid());
//		if (masterBeaconUUID.isPresent()) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0189"));
//		}
//		
//		masterBeacon.setId(uid);
//		repository.save(masterBeacon);
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, masterBeacon);
//		return ResponseEntity.ok().body(dataResponse);
//		
//	} 
//	
//	@GetMapping("/getAll")
//	public ResponseEntity<?> getAllMasterBeacon() {
//		
//		List<MasterBeaconTable> masterBeaconList = repository.findAll();
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, masterBeaconList);
//		return ResponseEntity.ok().body(dataResponse);
//		
//	}
//
//}
