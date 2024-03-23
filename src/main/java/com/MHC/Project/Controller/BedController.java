package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Bed;
import com.MHC.Project.Model.BedMasterConfig;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Repository.BedMasterRepository;
import com.MHC.Project.Repository.BedRepository;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.BedService;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "mettlerHealth")
public class BedController {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	BedMasterRepository bedMasterRepository;
	
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	private final BedService bedService;

	public BedController(BedService bedService) {
		this.bedService = bedService;
	}
	@PostMapping("/bedConfig/add")
	public ResponseEntity<?> createBedMasterConfig(@RequestBody BedMasterConfig bedMasterConfig) {
	    String org = bedMasterConfig.getOrganization();
	    if (!organizationRepository.existsById(org)) {
	        return ResponseEntity.ok().body("Error: Organization not found");
	    }

	    // Check if bedType is "Semi Private"
	    if ("2".equals(bedMasterConfig.getBedType())) {
	        if ("1".equals(bedMasterConfig.getSide())  || "2".equals(bedMasterConfig.getSide())) {
	            bedMasterConfig.setBedNo("01");
	            bedMasterConfig.setSide("1");
	            BedMasterConfig createdConfig1 = bedService.createBedMasterConfig(bedMasterConfig);

	            bedMasterConfig.setBedNo("02");
	            bedMasterConfig.setSide("2");
	            BedMasterConfig createdConfig2 = bedService.createBedMasterConfig(bedMasterConfig);
	            
	            DataResponse<BedMasterConfig> response1 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig1);
	            DataResponse<BedMasterConfig> response2 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig2);

	            List<DataResponse<BedMasterConfig>> responses = Arrays.asList(response1, response2);
	            return new ResponseEntity<>(responses, HttpStatus.CREATED);
	        } else if ("3".equals(bedMasterConfig.getSide())  || "4".equals(bedMasterConfig.getSide())) {
	            bedMasterConfig.setBedNo("01");
	            bedMasterConfig.setSide("3");
	            BedMasterConfig createdConfig1 = bedService.createBedMasterConfig(bedMasterConfig);

	            bedMasterConfig.setBedNo("02");
	            bedMasterConfig.setSide("4"); // Set the position as "2"
	            BedMasterConfig createdConfig2 = bedService.createBedMasterConfig(bedMasterConfig);
	            
	            DataResponse<BedMasterConfig> response1 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig1);
	            DataResponse<BedMasterConfig> response2 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig2);

	            // Return a list of the two created bed configurations
	            List<DataResponse<BedMasterConfig>> responses = Arrays.asList(response1, response2);
	            return new ResponseEntity<>(responses, HttpStatus.CREATED);
	        } else if ("5".equals(bedMasterConfig.getSide())  || "6".equals(bedMasterConfig.getSide())) {
	            bedMasterConfig.setBedNo("01");
	            bedMasterConfig.setSide("5");
	            BedMasterConfig createdConfig1 = bedService.createBedMasterConfig(bedMasterConfig);

	            bedMasterConfig.setBedNo("02");
	            bedMasterConfig.setSide("6"); // Set the position as "2"
	            BedMasterConfig createdConfig2 = bedService.createBedMasterConfig(bedMasterConfig);
	            
	            DataResponse<BedMasterConfig> response1 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig1);
	            DataResponse<BedMasterConfig> response2 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig2);

	            // Return a list of the two created bed configurations
	            List<DataResponse<BedMasterConfig>> responses = Arrays.asList(response1, response2);
	            return new ResponseEntity<>(responses, HttpStatus.CREATED);
	        } else if ("7".equals(bedMasterConfig.getSide())  || "8".equals(bedMasterConfig.getSide())) {
	            bedMasterConfig.setBedNo("01");
	            bedMasterConfig.setSide("7");
	            bedMasterConfig.getId();
	            BedMasterConfig createdConfig1 = bedService.createBedMasterConfig(bedMasterConfig);

	            bedMasterConfig.setBedNo("02");
	            bedMasterConfig.setSide("8"); // Set the position as "2"
	            BedMasterConfig createdConfig2 = bedService.createBedMasterConfig(bedMasterConfig);
	            
	            DataResponse<BedMasterConfig> response1 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig1);
	            DataResponse<BedMasterConfig> response2 = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                    createdConfig2);

	            // Return a list of the two created bed configurations
	            List<DataResponse<BedMasterConfig>> responses = Arrays.asList(response1, response2);
	            return new ResponseEntity<>(responses, HttpStatus.CREATED);
	        }  
	    } 
	    else {
	        // For other positions, create a single record
	        BedMasterConfig createdConfig = bedService.createBedMasterConfig(bedMasterConfig);
	        DataResponse<BedMasterConfig> response = new DataResponse<>(DataResponse.createVerifyResponse("MHC - 0271"),
	                createdConfig);
	        return ResponseEntity.ok().body(response);
	    }
	    return ResponseEntity.ok().body("Invalid Parameters");
	}

	@GetMapping("/bedConfig/getAll")
	public ResponseEntity<DataResponse<List<BedMasterConfig>>> getAllBedMasterConfigs() {
		List<BedMasterConfig> bedConfigs = bedService.getAllBedMasterConfigs();
		DataResponse<List<BedMasterConfig>> response = new DataResponse<>(
				DataResponse.createVerifyResponse("MHC - 0272"), bedConfigs);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/bedConfig/delete/{id}")
	public ResponseEntity<?> deleteBedMasterConfig(@PathVariable String id) {
	    // Check if the bed configuration with the given ID exists
	    Optional<BedMasterConfig> existingConfig = bedMasterRepository.findById(id);

	    if (!existingConfig.isPresent()) {
	        // Return a response indicating that the bed configuration was not found
	    	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0181"));
	    }
	    // Delete the bed configuration
	    bedMasterRepository.deleteById(id);
	    // Return a response indicating successful deletion
	    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0273"));
	}

	@PostMapping("/assign")
	public ResponseEntity<?> bedAssign(@RequestBody Bed bed) {
		Optional<Patient> patientOptional = patientRepository.findById(bed.getPid());

		if (patientOptional.isPresent()) {
			String assignedPatient = patientOptional.get().getAssignedBed();
			Optional<BedMasterConfig> bedMasterOptional = bedMasterRepository.findById(bed.getBedId());

			if (bedMasterOptional.isPresent()) {
				BedMasterConfig bedMaster = bedMasterOptional.get();
				String bedNo = bedMasterOptional.get().getBedNo(); 
				if (bedNo == null || bedMaster.isOccupied()) {
					return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0071"));  
				}
//				if (assignedPatient != null && !assignedPatient.isEmpty()) {
//					return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0072"));
//				}

				 if (patientOptional.get().getBasicDetails().get(0).getGender().equalsIgnoreCase("male")) {
						bed.setGenderRestriction(false);  
						bedMaster.setGenderRestriction(false);
					} else if (patientOptional.get().getBasicDetails().get(0).getGender().equalsIgnoreCase("female")) {
						bed.setGenderRestriction(true);
						bedMaster.setGenderRestriction(true);
						
					}
				 
				String wardName = bedMasterOptional.get().getWardName();
				String roomNo = bedMasterOptional.get().getRoomNo();
				
				LocalDateTime createdDate = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
				String createdAt = createdDate.format(formatter);

				Patient assign = patientOptional.get();
				assign.setAssignedBed(wardName + "-" + roomNo + "-" + bedNo);
				patientRepository.save(assign);
				
				bedMaster.setOccupied(true);
				bedMasterRepository.save(bedMaster);
				
				bed.setId(PatientService.generateUID());
				bed.setWardBedInfo(wardName + "-" + roomNo + "-" + bedNo);
				bed.setOccupied(true);
				bed.setBedAssignDateTime(createdAt);
				bedRepository.save(bed);
				
				DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
			    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, bed); 
				return ResponseEntity.ok(dataResponse);
			}
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0073")); 

}
	
	@PostMapping("/dischrage/{id}")
	public ResponseEntity<?> dischargeBed(@PathVariable String id) {
				
		Optional<Bed> bed = bedRepository.findById(id);
		if (bed.isPresent()) {
			Bed bedList = bed.get();
			boolean bedOccupied = bed.get().isOccupied();
			
			Optional<BedMasterConfig> bedMasterOptional = bedMasterRepository.findById(bedList.getBedId());
			Optional<Patient> patientOptional =  patientRepository.findById(bedList.getPid());
			
			LocalDateTime updatedDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String updatedAt = updatedDate.format(formatter);

			if (bedOccupied) {
				bedList.setOccupied(false);
				bedList.setDischargeDateTime(updatedAt);
				bedRepository.save(bedList);
				
				if (bedMasterOptional.isPresent()) {
					BedMasterConfig bedMasterConfig = bedMasterOptional.get();
					bedMasterConfig.setOccupied(false);
					bedMasterConfig.setGenderRestriction(false);
					bedMasterRepository.save(bedMasterConfig); 
				}
				if (patientOptional.isPresent()) {
					Patient patient = patientOptional.get();
					patient.setAssignedBed("");
					patientRepository.save(patient);
				}

				DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, bedList); 
				return ResponseEntity.ok(dataResponse);
			}
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0076"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0077"));
	}
	
	@GetMapping("/getBedMasterConfig/{org}")
	public ResponseEntity<?> getBedMasterConfigByOrg(@PathVariable String org) {
		
		List<BedMasterConfig> bedMasterList = bedMasterRepository.findByOrganization(org);
		if (!bedMasterList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, bedMasterList); 
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0075")); 
	}
	
	@GetMapping("/ByOccupiedBed")
	public ResponseEntity<?> getOccupiedBeds() {
		
		List<Bed> bedOcuupied = bedRepository.findByOccupied(true); 
		if (!bedOcuupied.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, bedOcuupied); 
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078")); 
	}
	
	@GetMapping("/ByAvailableBed")
	public ResponseEntity<?> getAvailableBeds() {
		
		List<Bed> bedOcuupied = bedRepository.findByOccupied(false); 
		if (!bedOcuupied.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, bedOcuupied); 
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078")); 
	}
	
	@GetMapping("/getAllBed")
	public ResponseEntity<?> getAllBed() {
		List<Bed> bedList = bedRepository.findAll();
		if (!bedList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, bedList); 
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0075")); 
	}
	@DeleteMapping("bed/deleteById/{id}")
	public ResponseEntity<?> deleteBedById(@PathVariable String id) {

		Optional<Bed> bedOptional = bedRepository.findById(id);
		if (bedOptional.isPresent()) {
			Bed bed = bedOptional.get();

			Optional<Patient> patientOptional = patientRepository.findById(bed.getPid());
			Patient patientClass = patientOptional.get();
			patientClass.setAssignedBed("");
			patientRepository.save(patientClass);

			Optional<BedMasterConfig> bedConfigOptional = bedMasterRepository.findById(bed.getBedId());
			BedMasterConfig bedConfig = bedConfigOptional.get();
			bedConfig.setOccupied(false);
			bedMasterRepository.save(bedConfig);

			bedRepository.delete(bed);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0274"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0275"));
   	}
	@PutMapping("/bedUnavailable/{bedConfigId}")
	public ResponseEntity<?> unavailableBed(@PathVariable String bedConfigId) {
		
		Optional<BedMasterConfig> bedConfig = bedMasterRepository.findById(bedConfigId);
		if (bedConfig.isPresent()) {
			BedMasterConfig bedMasterClass = bedConfig.get();
			bedMasterClass.setUnavailable(true);
			bedMasterRepository.save(bedMasterClass);
			
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0276"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0051"));
	}
	
	@PutMapping("/bedAvailable/{bedConfigId}")
	public ResponseEntity<?> availableBed(@PathVariable String bedConfigId) {
		
		Optional<BedMasterConfig> bedConfig = bedMasterRepository.findById(bedConfigId);
		if (bedConfig.isPresent()) {
			BedMasterConfig bedMasterClass = bedConfig.get();
			bedMasterClass.setUnavailable(false);
			bedMasterRepository.save(bedMasterClass);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0277"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0051"));
	}
}
