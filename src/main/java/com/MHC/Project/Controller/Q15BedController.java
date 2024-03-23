//package com.MHC.Project.Controller;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.MHC.Project.Model.Organization;
//import com.MHC.Project.Model.Patient;
//import com.MHC.Project.Model.Patient.basicDetails.Name;
//import com.MHC.Project.Model.Q15BedAssign;
//import com.MHC.Project.Model.Q15BedCreate;
//import com.MHC.Project.Repository.OrganizationRepository;
//import com.MHC.Project.Repository.PatientRepository;
//import com.MHC.Project.Repository.Q15BedAssignRepository;
//import com.MHC.Project.Repository.Q15BedCreateRepository;
//import com.MHC.Project.Response.DataResponse;
//import com.MHC.Project.Response.VerifyResponseFactory;
//import com.MHC.Project.Service.PatientService;
//
//@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
//		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
//@RestController
//@RequestMapping("/api/Q15Bed")
//public class Q15BedCreateAssignController {
//
//	@Autowired
//	Q15BedCreateRepository createRepository;
//	
//	@Autowired
//	Q15BedAssignRepository assignRepository;
//	
//	@Autowired
//	OrganizationRepository organizationRepository;
//	
//	@Autowired
//	PatientRepository patientRepository;
//	
//	@PostMapping("/create")
//	public ResponseEntity<?> createQ15Bed(@RequestBody Q15BedCreate create) {
//		
//		String uid = PatientService.generateUID();
//		LocalDateTime createdDate = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//		String createdAt = createdDate.format(formatter);
//		
//		Optional<Organization> organization = organizationRepository.findById(create.getOrgId());
//		if (organization.isEmpty()) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
//		}
//		
//		String orgId = create.getOrgId();
//		String roomNo = create.getRoomNo();
//		String bedNo = create.getBedNo();
//		
//		Optional<Q15BedCreate> createOptional = createRepository.findByOrgIdAndRoomNoAndBedNo(orgId, roomNo, bedNo);
//		if (createOptional.isPresent()) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0196"));
//		}
//		create.setId(uid);
//		create.setAvailable(true);
//		create.setCreatedAt(createdAt);
//		createRepository.save(create);
//		
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, create);
//		return ResponseEntity.ok().body(dataResponse);
//	}
//	
//	@GetMapping("/create/getAll/{orgId}")
//	public ResponseEntity<?> getAllQ15BedCreate(@RequestParam String orgId) {
//		
//		List<Q15BedCreate> createdQ15Bed = createRepository.findAllByOrgId(orgId);
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, createdQ15Bed);
//		return ResponseEntity.ok().body(dataResponse);
//		
//	}
//	
//	@GetMapping("/ByAvailableBedByOrgID/{orgId}")
//	public ResponseEntity<?> getAvailableBedByOrgID(@PathVariable String orgId) {
//		
//		List<Q15BedCreate> createList = createRepository.findByAvailableAndOrgId(true, orgId);
//		if (!createList.isEmpty()) {
//			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, createList);
//			return ResponseEntity.ok().body(dataResponse);
//		}
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, createList);
//		return ResponseEntity.ok().body(dataResponse); 
//	}
//	
//	@PostMapping("/Assign")
//	public ResponseEntity<?> assignQ15Bed(@RequestBody Q15BedAssign assign) {
//		
//		String uid = PatientService.generateUID();
//		LocalDateTime createdDate = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//		String createdAt = createdDate.format(formatter);
//		
//		Optional<Patient> patientOptional = patientRepository.findById(assign.getPid());
//		if (patientOptional.isEmpty()) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
//		}
//		
//	
//		Patient patient = patientOptional.get();
//		 patient.setRoomNo(assign.getRoomNo());
//	        patient.setBedNo(assign.getBedNo());
//	        patientRepository.save(patient);
//		String patientOrg = patient.getOrganization();
//		if (!patientOrg.equals(assign.getOrgId())) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0188"));
//		}
//		
//		Optional<Q15BedAssign> assignBed = assignRepository.findByPid(assign.getPid());
//		if (assignBed.isPresent()) {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0197"));
//		}
//		
//		Optional<Q15BedCreate> q15BedCreate = createRepository.findById(assign.getBedId());
//		if (q15BedCreate.isPresent()) {
//			Q15BedCreate createBed = q15BedCreate.get();
//			
//			assign.setRoomNo(createBed.getRoomNo());
//			assign.setBedNo(createBed.getBedNo());
//			
//			if (createBed.isAvailable()) {
//				
//				createBed.setAvailable(false);
//				createRepository.save(createBed);
//			} else {
//				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0071"));
//			}
//		} else {
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0051"));
//		}
//		
//		Name name = patient.getBasicDetails().get(0).getName().get(0);
//		String givenName = name.getGiven();
//		String familyName = name.getFamily(); 
//		
//		assign.setId(uid);
//		assign.setCreatedAt(createdAt);
//		assign.setPatientName(givenName + familyName);
//		
//		assignRepository.save(assign);
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, assign);
//		return ResponseEntity.ok().body(dataResponse);
//	}
//	
//	@GetMapping("/assign/getAll/{orgId}")
//	public ResponseEntity<?> getAllAssignedBed(@RequestParam String orgId) {
//		
//		List<Q15BedAssign> assignedQ15Bed = assignRepository.findAllByOrgId(orgId);
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, assignedQ15Bed);
//		return ResponseEntity.ok().body(dataResponse);
//	}
//	
//	@GetMapping("/AssignedBedById/{id}")
//	public ResponseEntity<?> getAssignedBedById(@PathVariable String id) {
//		
//		Optional<Q15BedAssign> assignedBedOptional = assignRepository.findById(id);
//		if (assignedBedOptional.isPresent()) {
//			Q15BedAssign assignedBed = assignedBedOptional.get();
//			
//			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, assignedBed);
//			return ResponseEntity.ok().body(dataResponse);
//		}
//		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
//	}
//	
//	@DeleteMapping("/assignedQ15BedById/{id}")
//	public ResponseEntity<?> deleteAssignedQ15BedById(@PathVariable String id) {
//		
//		Optional<Q15BedAssign> q15BedAssignOptional = assignRepository.findById(id);
//		if (q15BedAssignOptional.isPresent()) {
//			Q15BedAssign assign = q15BedAssignOptional.get();
//			
//			assignRepository.delete(assign);
//			
//			Optional<Q15BedCreate> q15BedCreateOptional = createRepository.findById(assign.getBedId());
//			if (q15BedCreateOptional.isPresent()) {
//				Q15BedCreate create = q15BedCreateOptional.get();
//				
//				create.setAvailable(true);
//				createRepository.save(create);
//			}
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
//		}
//		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
//	}
//	
//	@DeleteMapping("/createdQ15BedById/{createID}")
//	public ResponseEntity<?> deleteCreatedQ15BedById(@PathVariable String createID) {
//		
//		Optional<Q15BedCreate> q15BedCreateOptional = createRepository.findById(createID);
//		if (q15BedCreateOptional.isPresent()) {
//			Q15BedCreate create = q15BedCreateOptional.get();
//			
//			createRepository.delete(create);
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
//		}
//		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
//	}
//}

package com.MHC.Project.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.Q15Bed;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.Q15BedRepository;
import com.MHC.Project.Request.Q15BedRequest;
import com.MHC.Project.Request.Q15PatientAssign;
import com.MHC.Project.Request.UpdateQ15BedRequest;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/Q15Bed")
@SecurityRequirement(name = "mettlerHealth")
public class Q15BedController {
	
	@Autowired
	Q15BedRepository repository;
	
	@Autowired
	PatientRepository pRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;

//	@PostMapping("/create")
//	public ResponseEntity<?> createRecords(@RequestBody Q15BedRequest bedRequest) {
//	    String roomNoStart = bedRequest.getRoomNoStart();
//	    String roomNoEnd = bedRequest.getRoomNoEnd();
//	    String oddOrEven = bedRequest.getOddOrEven();
//
//	    // Validate input
//	    if (roomNoStart == null) {
//	        // Handle validation error, throw exception, or return an appropriate response
//	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0106"));
//	    }
//
//	    // Parse room numbers to integers
//	    int start = Integer.parseInt(roomNoStart);
//
//	    // Check if roomNoEnd is provided
//	    List<Q15Bed> savedBeds = new ArrayList<>(); // List to store saved records
//
//	    if (roomNoEnd != null) {
//	        int end = Integer.parseInt(roomNoEnd);
//
//	        // Iterate through room numbers and create records accordingly
//	        for (int i = start; i <= end; i++) {
//	            if ((i % 2 == 0 && oddOrEven.equalsIgnoreCase("even")) ||
//	                    (i % 2 != 0 && oddOrEven.equalsIgnoreCase("odd"))) {
//	                // Check if the room number already exists in the repository
//	                if (repository.existsByRoomNo(String.valueOf(i))) {
//	                    // Handle case where room number already exists (e.g., return an error response)
//	                    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0107"));
//	                }
//
//	                // Create a new Q15Bed instance for each record
//	                Q15Bed newBedRequest = new Q15Bed();
//	                newBedRequest.setId(PatientService.generateUID());
//	                newBedRequest.setRoomNo(Integer.toString(i));
//	                newBedRequest.setBedNo(bedRequest.getBedNo());
//	                newBedRequest.setOrganization(bedRequest.getOrganization());
//	                // Set createdAt with the current date and time in the format "yyyyMMddHHmmssSSS"
//	                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//	                String formattedDate = sdf.format(new Date());
//	                newBedRequest.setCreatedAt(formattedDate);
//	                // Save or process the new record using your service or repository
//	                savedBeds.add(repository.save(newBedRequest));
//	            }
//	        }
//	    } else {
//	        // If roomNoEnd is not provided, create a single record
//	        if (repository.existsByRoomNo(roomNoStart)) {
//	            return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0107"));
//	        }
//
//	        Q15Bed newBedRequest = new Q15Bed();
//	        newBedRequest.setId(PatientService.generateUID());
//	        newBedRequest.setRoomNo(roomNoStart);
//	        newBedRequest.setBedNo(bedRequest.getBedNo());
//	        newBedRequest.setOrganization(bedRequest.getOrganization());
//	        // Set createdAt with the current date and time in the format "yyyyMMddHHmmssSSS"
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//	        String formattedDate = sdf.format(new Date());
//	        newBedRequest.setCreatedAt(formattedDate);
//	        // Save or process the new record using your service or repository
//	        savedBeds.add(repository.save(newBedRequest));
//	    }
//
//	    DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
//	    DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, savedBeds);
//	    return ResponseEntity.ok(dataResponse);
//	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createBedRecordsWithBEdNo(@RequestBody Q15BedRequest bedRequest) {
	    String roomNoStart = bedRequest.getRoomNoStart();
	    String roomNoEnd = bedRequest.getRoomNoEnd();
	    String oddOrEven = bedRequest.getOddOrEven();
	    
	    Optional<Organization> organizationOptional = organizationRepository.findById(bedRequest.getOrganization());
	    if (organizationOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
		}
	    // Validate input
	    if (roomNoStart == null) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0106"));
	    }

	    // Parse room numbers to integers
	    int start = Integer.parseInt(roomNoStart);

	    // Check if roomNoEnd is provided
	    List<Q15Bed> savedBeds = new ArrayList<>(); // List to store saved records

	    if (roomNoEnd != null) {
	        int end = Integer.parseInt(roomNoEnd);

	        // Iterate through room numbers
	        for (int i = start; i <= end; i++) {
	            int bedCountForCurrentRoom = 1;  // Default value if not found
	            for (Q15BedRequest.RoomBedQuantity roomBedQuantity : bedRequest.getBedNoList()) {
	                if (roomBedQuantity.getRoomNo().equals(Integer.toString(i))) {
	                    bedCountForCurrentRoom = roomBedQuantity.getBedQuantity();
	                    break;
	                }
	            }

	            // Iterate through bed numbers for each room
	            for (int j = 1; j <= bedCountForCurrentRoom; j++) {
	                // Check if the oddOrEven field allows the creation of beds for the current room
	                if ((oddOrEven.equalsIgnoreCase("both")) ||
	                        (i % 2 == 0 && oddOrEven.equalsIgnoreCase("even")) ||
	                        (i % 2 != 0 && oddOrEven.equalsIgnoreCase("odd"))) {
	                    // Check if the room number and bed number combination already exists in the repository
	                    if (repository.existsByOrganizationAndRoomNoAndBedNo(bedRequest.getOrganization(), Integer.toString(i), String.format("%02d", j))) {
	                        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0107"));
	                    }

	                    // Create a new Q15Bed instance for each record
	                    Q15Bed newBedRequest = new Q15Bed();
	                    newBedRequest.setId(PatientService.generateUID());
	                    newBedRequest.setRoomNo(Integer.toString(i));
	                    newBedRequest.setBedNo(String.format("%02d", j));
	                    newBedRequest.setOrganization(bedRequest.getOrganization());
	                    // Set createdAt with the current date and time in the format "yyyyMMddHHmmssSSS"
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	                    String formattedDate = sdf.format(new Date());
	                    newBedRequest.setCreatedAt(formattedDate);
	                    // Save or process the new record using your service or repository
	                    savedBeds.add(repository.save(newBedRequest));
	                }
	            }
	        }
	    } else {
	        // If roomNoEnd is not provided, create a single record
	        int bedCountForCurrentRoom = 1;  // Default value if not found
	        for (Q15BedRequest.RoomBedQuantity roomBedQuantity : bedRequest.getBedNoList()) {
	            if (roomBedQuantity.getRoomNo().equals(Integer.toString(start))) {
	                bedCountForCurrentRoom = roomBedQuantity.getBedQuantity();
	                break;
	            }
	        }

	        for (int j = 1; j <= bedCountForCurrentRoom; j++) {
	            // Check if the oddOrEven field allows the creation of beds for the current room
	            if ((oddOrEven.equalsIgnoreCase("both")) ||
	                    (start % 2 == 0 && oddOrEven.equalsIgnoreCase("even")) ||
	                    (start % 2 != 0 && oddOrEven.equalsIgnoreCase("odd"))) {
	                // Check if the room number and bed number combination already exists in the repository
	                if (repository.existsByOrganizationAndRoomNoAndBedNo(bedRequest.getOrganization(), Integer.toString(start), String.format("%02d", j))) {
	                    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0107"));
	                }

	                Q15Bed newBedRequest = new Q15Bed();
	                newBedRequest.setId(PatientService.generateUID());
	                newBedRequest.setRoomNo(Integer.toString(start));
	                newBedRequest.setBedNo(String.format("%02d", j));
	                newBedRequest.setOrganization(bedRequest.getOrganization());
	                // Set createdAt with the current date and time in the format "yyyyMMddHHmmssSSS"
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	                String formattedDate = sdf.format(new Date());
	                newBedRequest.setCreatedAt(formattedDate);
	                // Save or process the new record using your service or repository
	                savedBeds.add(repository.save(newBedRequest));
	            }
	        }
	    }

	    DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
	    DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, savedBeds);
	    return ResponseEntity.ok(dataResponse);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCreateBedByRoomNos(@RequestBody UpdateQ15BedRequest quantities) {

	    List<Q15Bed> savedBeds = new ArrayList<>();
	    Optional<Organization> organizationOptional = organizationRepository.findById(quantities.getOrganization());
	    if (organizationOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
		}

	    for (UpdateQ15BedRequest.RoomBedQuantity quantity : quantities.getBedNoList()) {
	        String roomNo = quantity.getRoomNo();
	        List<Q15Bed> bedList = repository.findByOrganizationAndRoomNo(quantities.getOrganization(), roomNo);

	        int existingBedCount = bedList.size();
	        int requestedBedQuantity = quantity.getBedQuantity();

	        // Check if there are extra beds that need to be deleted
	        if (existingBedCount > requestedBedQuantity) {
	            List<Q15Bed> bedsToDelete = bedList.subList(requestedBedQuantity, existingBedCount);
	            repository.deleteAll(bedsToDelete);
	        }

	        int additionalBedCount = requestedBedQuantity - existingBedCount;

	        if (additionalBedCount > 0) {
	            for (int i = 0; i < additionalBedCount; i++) {
	                Q15Bed newBed = new Q15Bed();
	                newBed.setId(PatientService.generateUID());
	                newBed.setRoomNo(roomNo);
	                newBed.setBedNo(String.format("%02d", existingBedCount + i + 1));
	                newBed.setOccupied(false);
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	                String formattedDate = sdf.format(new Date());
	                newBed.setUpdatedAt(formattedDate);

	                // Check if the roomNo is not present, create a new room
	                if (bedList.isEmpty()) {
	                    // Assuming 'organization' is taken from quantities.getOrganization()
	                    newBed.setOrganization(quantities.getOrganization());
	                    // Set other properties as needed
	                } else {
	                    // Copy organization and createdAt from an existing bed
	                	for (Q15Bed bedRoom : bedList) {
	                        if (bedRoom.getRoomNo().equals(roomNo)) {
	                            String org = bedRoom.getOrganization();
	                            String createdAt = bedRoom.getCreatedAt();
	                            newBed.setOrganization(org);
	                            newBed.setCreatedAt(createdAt);
	                        }
	                    }
	                }

	                savedBeds.add(repository.save(newBed));
	            }
	        }
	    }

	    DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
	    DataResponse<?> dataResponse = new DataResponse<>(response, savedBeds);
	    return ResponseEntity.ok(dataResponse);
	}
	
	@PostMapping("/assign")
	public ResponseEntity<?> Q15PatientAssign(@RequestBody Q15PatientAssign assign) {
		String pid = assign.getPid();
		 List<Q15Bed> savedBeds = new ArrayList<>();
		 
		Optional<Q15Bed> newBedRequest = repository.findById(assign.getBedId());
		
//			System.out.println(pid);
		Optional<Q15Bed> pidA = repository.findByPid(pid);
		
		if(pidA.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0108"));
		}
		boolean occu = newBedRequest.get().isOccupied();
		if(occu) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0072"));
		}
		
		
		
//		Optional<Q15Bed> newBedRequest = repository.findById(assign.getBedId());
		Q15Bed bed = newBedRequest.get();
		bed.setAdmitDate(assign.getAdmitDate());
		bed.setOccupied(true);
		bed.setPid(assign.getPid());
		bed.setAssignedBy(assign.getAssignedBy());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formattedDate = sdf.format(new Date());
		bed.setBedAssignDateTime(formattedDate);
		
		Optional<Patient> patient = pRepository.findById(pid);
		Patient assignedBed = patient.get();
		assignedBed.setActive("1");
		assignedBed.setAssignedBed(bed.getRoomNo()+"-"+bed.getBedNo());
		pRepository.save(assignedBed);
		savedBeds.add(repository.save(bed));
		DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
	    DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, savedBeds);
	    return ResponseEntity.ok(dataResponse);
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<?> transferBed(@RequestBody Q15PatientAssign transfer) {

		Optional<Q15Bed> newBedRequest = repository.findById(transfer.getBedId());
		if (newBedRequest.isPresent()) {
			Q15Bed bed = newBedRequest.get();

			boolean occupied = bed.isOccupied();
			if (occupied) {
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0072"));
			}
		}

		Optional<Q15Bed> bedOptional = repository.findByPid(transfer.getPid());
		if (bedOptional.isPresent()) {
			Q15Bed bed = bedOptional.get();

			bed.setAdmitDate(null);
			bed.setOccupied(false);
			bed.setPid(null);
			bed.setAssignedBy(null);
			bed.setBedAssignDateTime(null);
//			bed.setAssignStatus(null);
			repository.save(bed);

		}

		if (newBedRequest.isPresent()) {
			Q15Bed bed = newBedRequest.get();

			bed.setAdmitDate(transfer.getAdmitDate());
			bed.setOccupied(true);
			bed.setPid(transfer.getPid());
			bed.setAssignedBy(transfer.getAssignedBy());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String formattedDate = sdf.format(new Date());
			bed.setBedAssignDateTime(formattedDate);
//			bed.setAssignStatus("Transfered");
			repository.save(bed);

			Optional<Patient> patient = pRepository.findById(transfer.getPid());
			Patient assignedBed = patient.get();

			assignedBed.setAssignedBed(bed.getRoomNo() + "-" + bed.getBedNo());
			pRepository.save(assignedBed);

			DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
			DataResponse<?> dataResponse = new DataResponse<>(response, bed);
			return ResponseEntity.ok(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0075"));
	}
	
	@PostMapping("/dischargePatient/{roomBed}/{orgId}")
	public ResponseEntity<?> dischargePatient(@PathVariable String roomBed,
											  @PathVariable String orgId){
		
		 String[] parts = roomBed.split("-");
		  
	        if (parts.length != 2) {
	            return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0279"));
	        }

	        String roomNo = parts[0];
	        String bedNo = parts[1];
	        
		Optional<Q15Bed> bedOptional = repository.findByRoomNoAndBedNoAndOrganization(roomNo, bedNo, orgId);
				
		List<Q15Bed> savedBeds = new ArrayList<>();
		Q15Bed bed = bedOptional.get();
		String pid = bed.getPid();
		Optional<Patient> patient = pRepository.findById(pid);
		Patient assignedBed = patient.get();
		
		assignedBed.setActive("0");
		assignedBed.setAssignedBed(null);
		assignedBed.setBeaconDevice(null);
		assignedBed.setBeaconDevice1(null);
		pRepository.save(assignedBed);
		
		bed.setAdmitDate(null);
		bed.setOccupied(false);
		bed.setPid(null);
		bed.setAssignedBy(null);
		bed.setBedAssignDateTime(null);
		
		
		savedBeds.add(repository.save(bed));
		
		DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
	    DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, savedBeds);
	    return ResponseEntity.ok(dataResponse);
	}
	
	@GetMapping("/getAll")
    public ResponseEntity<?> getAllQ15Bed() {
        List<Q15Bed> Q15Beds = repository.findAll();

        List<Q15Bed> sortedQ15Beds = Q15Beds.stream()
        	    .sorted(Comparator.comparing(Q15Bed::getRoomNo, Comparator.comparingInt(String::length))
        	        .thenComparing(Q15Bed::getRoomNo)
        	        .thenComparing(Q15Bed::getBedNo, Comparator.comparingInt(String::length))
        	        .thenComparing(Q15Bed::getBedNo))
        	    .collect(Collectors.toList());

        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, sortedQ15Beds);

        return ResponseEntity.ok(dataResponse);
    }
    
	
//	@GetMapping("/getByOrg/{orgId}")
//	public ResponseEntity<?> getAllQ15BedByOrg(@PathVariable String orgId){
//		
//		List<Q15Bed> Q15Beds = repository.findByOrganization(orgId);
//		
//		
//		List<Q15Bed> sortedQ15Beds = Q15Beds.stream()
//			    .sorted(Comparator.comparing(Q15Bed::getRoomNo, Comparator.comparingInt(String::length))
//			        .thenComparing(Q15Bed::getRoomNo)
//			        .thenComparing(Q15Bed::getBedNo, Comparator.comparingInt(String::length))
//			        .thenComparing(Q15Bed::getBedNo))
//			    .collect(Collectors.toList());
//
//	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
//	        DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, sortedQ15Beds);
//
//	        return ResponseEntity.ok(dataResponse);
//	}
//	
//	@GetMapping("/getByAvailable/{orgId}")
//	public ResponseEntity<?> getAllQ15BedAvailableByOrg(@PathVariable String orgId){
//
//	    List<Q15Bed> Q15Beds = repository.findByOrganizationAndOccupied(orgId,false);
//	    
//	    List<Q15Bed> sortedQ15Beds = Q15Beds.stream()
//	    	    .sorted(Comparator.comparing(Q15Bed::getRoomNo, Comparator.comparingInt(String::length))
//	    	        .thenComparing(Q15Bed::getRoomNo)
//	    	        .thenComparing(Q15Bed::getBedNo, Comparator.comparingInt(String::length))
//	    	        .thenComparing(Q15Bed::getBedNo))
//	    	    .collect(Collectors.toList());
//
//	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
//	        DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, sortedQ15Beds);
//
//	        return ResponseEntity.ok(dataResponse);
//	}
//	
//	@GetMapping("/getByOccupied/{orgId}")
//	public ResponseEntity<?> getAllQ15BedOccupiedByOrg(@PathVariable String orgId){
//		
//		List<Q15Bed> Q15Beds = repository.findByOrganizationAndOccupied(orgId,true);
//		
//		List<Q15Bed> sortedQ15Beds = Q15Beds.stream()
//			    .sorted(Comparator.comparing(Q15Bed::getRoomNo, Comparator.comparingInt(String::length))
//			        .thenComparing(Q15Bed::getRoomNo)
//			        .thenComparing(Q15Bed::getBedNo, Comparator.comparingInt(String::length))
//			        .thenComparing(Q15Bed::getBedNo))
//			    .collect(Collectors.toList());
//
//	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
//	        DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, sortedQ15Beds);
//
//	        return ResponseEntity.ok(dataResponse);
//	}
	
	 @GetMapping("/getByOrg/{orgId}")
	    public ResponseEntity<?> getAllQ15BedByOrg(@PathVariable String orgId){
	        List<Q15Bed> sortedQ15Beds = sortByRoomAndBed(repository.findByOrganization(orgId));
	        return createResponse(sortedQ15Beds);
	    }

	    @GetMapping("/getByAvailable/{orgId}")
	    public ResponseEntity<?> getAllQ15BedAvailableByOrg(@PathVariable String orgId){
	        List<Q15Bed> sortedQ15Beds = sortByRoomAndBed(repository.findByOrganizationAndOccupied(orgId, false));
	        return createResponse(sortedQ15Beds);
	    }

	    @GetMapping("/getByOccupied/{orgId}")
	    public ResponseEntity<?> getAllQ15BedOccupiedByOrg(@PathVariable String orgId){
	        List<Q15Bed> sortedQ15Beds = sortByRoomAndBed(repository.findByOrganizationAndOccupied(orgId, true));
	        return createResponse(sortedQ15Beds);
	    }

	    // Helper method to sort by room number and bed number
	    private List<Q15Bed> sortByRoomAndBed(List<Q15Bed> beds) {
	        return beds.stream()
	                .sorted(Comparator.comparing(Q15Bed::getRoomNo, Comparator.comparingInt(String::length))
	                        .thenComparing(Q15Bed::getRoomNo)
	                        .thenComparing(Q15Bed::getBedNo, Comparator.comparingInt(String::length))
	                        .thenComparing(Q15Bed::getBedNo))
	                .collect(Collectors.toList());
	    }

	    // Helper method to create response
	    private ResponseEntity<DataResponse<List<Q15Bed>>> createResponse(List<Q15Bed> sortedQ15Beds) {
	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
	        DataResponse<List<Q15Bed>> dataResponse = new DataResponse<>(response, sortedQ15Beds);
	        return ResponseEntity.ok(dataResponse);
	    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteQ15BedById(@PathVariable String id) {
	    try {
	        Optional<Q15Bed> q15BedOptional = repository.findById(id);

	        if (q15BedOptional.isPresent()) {
	            repository.deleteById(id);

	            // You can customize the success response accordingly
	            DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0266");
	            DataResponse<String> dataResponse = new DataResponse<>(response, "Deleted");
	            return ResponseEntity.ok(dataResponse);
	        } else {
	        	return ResponseEntity.ok().body("Q15Bed not found with id: " + id);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.ok().body("An error occurred while deleting Q15Bed with id: " + id);
	    }
	}
}
