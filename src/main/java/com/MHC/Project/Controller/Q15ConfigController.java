package com.MHC.Project.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
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

import com.MHC.Project.Error.VerifyResponse;
import com.MHC.Project.Model.Config;
import com.MHC.Project.Model.Model;
import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.Patient.basicDetails.Name;
import com.MHC.Project.Model.Q15Report;
import com.MHC.Project.Model.Q15Report.DataEntry;
import com.MHC.Project.Model.Q15Report.EnteredBy;
import com.MHC.Project.Model.Q15Report.ShiftIncharge;
import com.MHC.Project.Model.Staff;
import com.MHC.Project.Repository.ConfigRepository;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.Repo;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.Q15ReportResponse;
import com.MHC.Project.Response.Q15ReportResponse.Q15ReportResponse1;
import com.MHC.Project.Response.Q15Response;
import com.MHC.Project.Response.Q15Response.slot;
import com.MHC.Project.Response.SignInResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.ConfigService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/config")
@SecurityRequirement(name = "mettlerHealth")
public class Q15ConfigController {

	private static final Logger logger = LoggerFactory.getLogger(Q15ConfigController.class);

	@Autowired
	private ConfigService service;

	@Autowired
	PatientRepository pRepository;

	@Autowired
	OrganizationRepository oRepo;

	@Autowired
	StaffRepository sRepo;

	@Autowired
	ConfigRepository repository;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	Repo repo;
	
//	@PostMapping("/register")
//	public ResponseEntity<?> saveConfig(@RequestBody @Validated Config config) {
//		String patientId = config.getPID();
//		String staffUsername = config.getEnteredBy(); // Assuming this is the staff username
//		Patient patient = pRepository.findById(patientId).orElse(null);
//		// Check if the patient exists
//		VerifyResponse verifyResponse;
//		if (patient == null) {
//			return ResponseEntity.ok().body(verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0084"));
//		}
//		// Retrieve staff using the staff username
//		Optional<Staff> staff = sRepo.findByUsername(staffUsername);
//		if (staff.isEmpty()) {
//			return ResponseEntity.ok().body(verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0085"));
//		}
//		if (config.isSkippedScanning()) {
//            sendVerificationMail(config);
//        }
//		// Set the staff id to the enteredBy field
//		config.setEnteredBy(staff.get().getId());
//		Config savedConfig = service.saveConfig(config);
//		Map<String, Object> responseBody = new HashMap<>();
//		responseBody.put("savedConfig", savedConfig);
//		
//		String deviceId = config.getDeviceId();
//	    Optional<Model> modelOptional = repo.findByDeviceId(deviceId);
//	    if (modelOptional.isPresent()) {
//	        Model model = modelOptional.get();
//	        String modelId = model.getId();
//	        repo.deleteById(modelId);
//	    } 
//		SignInResponse.Response verifyResponse1 = SignInResponse.createVerifyResponse("MHC - 0242");
//		SignInResponse signInResponse = new SignInResponse(verifyResponse1, responseBody);
//		return ResponseEntity.ok().body(signInResponse);
//
//	}
//	
//	private void sendVerificationMail(Config config) {
//		
//		String pid = config.getPID();
//		Optional<Patient> oPatient = pRepository.findById(pid);
//		String org = oPatient.get().getOrganization();
//		Optional<Organization> orgEmail = oRepo.findById(org);
//		String OEmail = orgEmail.get().getEmail();
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(OEmail);
//        mail.setSubject("Skipped beacon Device detection.");
//        
//        String message = String.format("This is the message to inform that %s was skipped the beacon detection while entered the q15 data for the patient %s. date: %s, slot: %s.",
//                config.getEnteredBy(), config.getPID(), config.getQ15Date(), config.getQ15Slot());
//
//        mail.setText(message);
//
//        try {
//            javaMailSender.send(mail);
//        } catch (MailException e) {
//            // Handle exception appropriately, log or throw
//            e.printStackTrace();
//        }
//    }
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> saveConfig(@RequestBody @Validated Config config) {
		String patientId = config.getPID();
		String staffUsername = config.getEnteredBy(); // Assuming this is the staff username
		Patient patient = pRepository.findById(patientId).orElse(null);
		// Check if the patient exists
		VerifyResponse verifyResponse;
		if (patient == null) {
			return ResponseEntity.ok().body(verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0084"));
		}
		// Retrieve staff using the staff username
		Optional<Staff> staff = sRepo.findByUsername(staffUsername);

		if (staff.isEmpty()) {
			return ResponseEntity.ok().body(verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0085"));
		}
		if (config.isSkippedScanning()) {
            sendVerificationMail(config);
        }
		
		// Set the staff id to the enteredBy field
		config.setEnteredBy(staff.get().getId());
		Config savedConfig = service.saveConfig(config);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("savedConfig", savedConfig);
		
		String deviceId = config.getDeviceId();
	    Optional<Model> modelOptional = repo.findByDeviceId(deviceId);

	    if (modelOptional.isPresent()) {
	        Model model = modelOptional.get();
	        String modelId = model.getId();
	        repo.deleteById(modelId);
	    } 
		SignInResponse.Response verifyResponse1 = SignInResponse.createVerifyResponse("MHC - 0242");
		SignInResponse signInResponse = new SignInResponse(verifyResponse1, responseBody);
		return ResponseEntity.ok().body(signInResponse);

	}

	
	private void sendVerificationMail(Config config) {

		String pid = config.getPID();
		Optional<Patient> oPatient = pRepository.findById(pid);
		String org = oPatient.get().getOrganization();
		Optional<Organization> orgEmail = oRepo.findById(org);
		String OEmail = orgEmail.get().getEmail();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(OEmail);
		mail.setSubject("Skipped beacon Device detection.");

		String message = String.format(
				"This is the message to inform that %s was skipped the beacon detection while entered the q15 data for the patient %s. date: %s, slot: %s.",
				config.getEnteredBy(), config.getPID(), config.getQ15Date(), config.getQ15Slot());

		mail.setText(message);

		try {
			javaMailSender.send(mail);
		} catch (MailException e) {
			// Handle exception appropriately, log or throw
			e.printStackTrace();
		}
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllConfig() {
		List<Config> configList = service.getAllConfig();
		VerifyResponse verifyResponse;

		if (!configList.isEmpty()) {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 241");
			verifyResponse.setData(configList); // Set the data as a list
		} else {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0083");
		}
		return ResponseEntity.ok(verifyResponse);
	}

	// ***************** Get the Config Details Based on PID ****************\\
	@GetMapping("getById/{PID}")
	public ResponseEntity<?> getConfig(@PathVariable String PID) {
		List<Config> configs = repository.findByPID(PID);
		VerifyResponse verifyResponse;

		if (!configs.isEmpty()) {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0215");
			verifyResponse.setData(configs); // Set the data as a list
		} else {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0080");
		}
		return ResponseEntity.ok(verifyResponse);
	}
	
	@GetMapping("getById/{PID}/date/{date}")
	public ResponseEntity<?> getByPidAndDate(@PathVariable String PID, @PathVariable String date) {
		List<Config> configs = repository.findByPIDAndQ15Date(PID,date);
		VerifyResponse verifyResponse;

		if (!configs.isEmpty()) {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0215");
			verifyResponse.setData(configs); // Set the data as a list
		} else {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0080");
		}
		return ResponseEntity.ok(verifyResponse);
	}

	@GetMapping("getBySlot/{q15Slot}")
	public ResponseEntity<?> getQ15(@PathVariable String q15Slot) {
		List<Config> configs = repository.findByQ15Slot(q15Slot);
		VerifyResponse verifyResponse;

		if (!configs.isEmpty()) {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 214");
			verifyResponse.setData(configs); // Set the data as a list
		} else {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0081");
		}
		return ResponseEntity.ok(verifyResponse);
	}

	@GetMapping("get/{q15Slot}/{q15Date}")
	public ResponseEntity<?> getQ15(@PathVariable String q15Slot, @PathVariable String q15Date) {
		List<Config> configs = repository.findByQ15SlotAndQ15Date(q15Slot, q15Date);
		VerifyResponse verifyResponse;

		if (!configs.isEmpty()) {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 271");
			verifyResponse.setData(configs); // Set the data as a list
		} else {
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0091");
		}
		return ResponseEntity.ok(verifyResponse);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePID(@PathVariable String id) {
		VerifyResponse verifyResponse;

		if (repository.existsById(id)) {
			repository.deleteById(id);
			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 217");
			return ResponseEntity.ok(verifyResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0082"));
	}

	@GetMapping("/get/completed/{q15Slot}/{q15Date}/{org}")
	public ResponseEntity<?> getActivePatientsWithTimestamp(@PathVariable String q15Slot, @PathVariable String q15Date,
			@PathVariable String org) {
//		Optional<Organization> organizationOptional = oRepo.findById(org);
//		String organization = organizationOptional.get().getOrganizationdetails().get(0).getName();
		List<Patient> patients = pRepository.findAllByActiveAndOrganization("1", org);
		List<Config> configList = repository.findByQ15SlotAndQ15DateAndTimestampCreatedAtNotNull(q15Slot, q15Date);

		if (!patients.isEmpty()) {
			List<Patient> filteredPatients = new ArrayList<>();

			for (Patient patient : patients) {
				String patientId = patient.getId();
				if (configList.stream().anyMatch(config -> config.getPID().equals(patientId))) {
					filteredPatients.add(patient);
				}
			}
			
//	        filteredPatients.sort(Comparator.comparing(Patient::getAssignedBed, Comparator.nullsLast(String::compareTo)));
			
			filteredPatients.sort(Comparator.comparing(patient -> {
			    String assignedBed = patient.getAssignedBed();
			    if (assignedBed != null) {
			        String[] parts = assignedBed.split("-");
			        if (parts.length == 2) {
			            try {
			                return Integer.parseInt(parts[0]);
			            } catch (NumberFormatException e) {
			                // Handle parsing exception if necessary
			                e.printStackTrace();
			            }
			        }
			    }
			    // Return null if parsing fails or if assignedBed is null
			    return null;
			}, Comparator.nullsLast(Comparator.naturalOrder())));

			
			if (!filteredPatients.isEmpty()) {
				DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
				DataResponse<List<Patient>> dataResponse = new DataResponse<>(response, filteredPatients);
				return ResponseEntity.ok(dataResponse);
			} else {
				logger.error("No Active Patients Found with the specified criteria...");
				DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0175");
				DataResponse<List<Patient>> dataResponse = new DataResponse<>(response, filteredPatients);
				return ResponseEntity.ok(dataResponse);
			}
		} else {
			logger.error("No Active Patients Found...");
			DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0176");
			DataResponse<List<Patient>> dataResponse = new DataResponse<>(response, patients);
			return ResponseEntity.ok(dataResponse);
		}
	}

	@GetMapping("/get/notCompleted/{q15Slot}/{q15Date}/{org}")
	public ResponseEntity<?> getActivePatientsWithNoConfigData(@PathVariable String q15Slot,
			@PathVariable String q15Date, @PathVariable String org) {
//		Optional<Organization> organizationOptional = oRepo.findById(org);
//		String organization = organizationOptional.get().getOrganizationdetails().get(0).getName();
		List<Patient> patients = pRepository.findAllByActiveAndOrganization("1", org);
		List<Config> configList = repository.findByQ15SlotAndQ15DateAndTimestampCreatedAtNotNull(q15Slot, q15Date);

		List<Patient> patientsWithNoConfigData = new ArrayList<>();

		for (Patient patient : patients) {
			
			String patientId = patient.getId();
			boolean hasConfigData = configList.stream().anyMatch(config -> config.getPID().equals(patientId));
			if (!hasConfigData) {
				patientsWithNoConfigData.add(patient);
			}
		}

//        patientsWithNoConfigData.sort(Comparator.comparing(Patient::getAssignedBed, Comparator.nullsLast(String::compareTo)));

		patientsWithNoConfigData.sort(Comparator.comparing(patient -> {
		    String assignedBed = patient.getAssignedBed();
		    if (assignedBed != null) {
		        String[] parts = assignedBed.split("-");
		        if (parts.length == 2) {
		            try {
		                return Integer.parseInt(parts[0]);
		            } catch (NumberFormatException e) {
		                // Handle parsing exception if necessary
		                e.printStackTrace();
		            }
		        }
		    }
		    // Return null if parsing fails or if assignedBed is null
		    return null;
		}, Comparator.nullsLast(Comparator.naturalOrder())));

		
		if (!patientsWithNoConfigData.isEmpty()) {
	        for (Patient patient : patientsWithNoConfigData) {
	            String beaconDevice = patient.getBeaconDevice();
	            repo.deleteByDeviceId(beaconDevice);
	        }
			DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<List<Patient>> dataResponse = new DataResponse<>(response, patientsWithNoConfigData);
			return ResponseEntity.ok(dataResponse);
		} else {
			logger.error("No Active Patients Found with no config data for the specified criteria...");
			DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0177");
			DataResponse<List<Patient>> dataResponse = new DataResponse<>(response, patientsWithNoConfigData);
			return ResponseEntity.ok(dataResponse);
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getConfigsByFilters(@RequestParam(required = false) String pid,
	                                             @RequestParam(required = false) String enteredBy,
	                                             @RequestParam(required = false) String slot,
	                                             @RequestParam(required = false) String shift,
	                                             @RequestParam(required = false) String shiftIncharge,
	                                             @RequestParam(required = false) String startDate,
	                                             @RequestParam(required = false) String endDate) {
		
	    LocalDate parsedStartDate = startDate != null ? LocalDate.parse(startDate, DateTimeFormatter.BASIC_ISO_DATE).minusDays(1) : null;
	    LocalDate parsedEndDate = endDate != null ? LocalDate.parse(endDate, DateTimeFormatter.BASIC_ISO_DATE).plusDays(1) : null;
 

	    List<Config> configs = new ArrayList<>();

	    if (pid != null && enteredBy != null && slot != null && shift != null && shiftIncharge != null && parsedStartDate != null && parsedEndDate != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15SlotAndShiftAndShiftInchargeAndQ15DateBetween(pid, enteredBy, slot, shift, shiftIncharge, parsedStartDate.format(DateTimeFormatter.BASIC_ISO_DATE), parsedEndDate.format(DateTimeFormatter.BASIC_ISO_DATE));
	    }  else if (pid != null && parsedStartDate != null && parsedEndDate != null) {
	        // Filter by Pid and date range
	        configs = repository.findByPIDAndQ15DateBetween(pid, parsedStartDate.format(DateTimeFormatter.BASIC_ISO_DATE), parsedEndDate.format(DateTimeFormatter.BASIC_ISO_DATE));
	    } else if (enteredBy != null && parsedStartDate != null && parsedEndDate != null) {
	        // Filter by enteredBy and date range
	        configs = repository.findByEnteredByAndQ15DateBetween(enteredBy, parsedStartDate.format(DateTimeFormatter.BASIC_ISO_DATE), parsedEndDate.format(DateTimeFormatter.BASIC_ISO_DATE));
	    } else if (slot != null && parsedStartDate != null && parsedEndDate != null) {
	        // Filter by Pid and date range
	        configs = repository.findByQ15SlotAndQ15DateBetween(slot, parsedStartDate.format(DateTimeFormatter.BASIC_ISO_DATE), parsedEndDate.format(DateTimeFormatter.BASIC_ISO_DATE));
	    } else if (shift != null && parsedStartDate != null && parsedEndDate != null) {
	        // Filter by Pid and date range
	        configs = repository.findByShiftAndQ15DateBetween(shift, parsedStartDate.format(DateTimeFormatter.BASIC_ISO_DATE), parsedEndDate.format(DateTimeFormatter.BASIC_ISO_DATE));
	    } else if (shiftIncharge != null && parsedStartDate != null && parsedEndDate != null) {
	        // Filter by Pid and date range
	        configs = repository.findByShiftInchargeAndQ15DateBetween(shiftIncharge, parsedStartDate.format(DateTimeFormatter.BASIC_ISO_DATE), parsedEndDate.format(DateTimeFormatter.BASIC_ISO_DATE));
	    } else if (startDate != null && endDate != null) {
	        configs = repository.findByQ15DateBetween(parsedStartDate.format(DateTimeFormatter.BASIC_ISO_DATE), parsedEndDate.format(DateTimeFormatter.BASIC_ISO_DATE));
	    } else if (pid != null && enteredBy != null && slot != null && shift != null && shiftIncharge != null && startDate != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15SlotAndShiftAndShiftInchargeAndQ15Date(pid, enteredBy, slot, shift, shiftIncharge, startDate);
	    } else if (pid != null && enteredBy != null && slot != null && shift != null && shiftIncharge != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15SlotAndShiftAndShiftIncharge(pid, enteredBy, slot, shift, shiftIncharge);
	    }  else if (enteredBy != null && slot != null && shift != null && shiftIncharge != null && startDate != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByEnteredByAndQ15SlotAndShiftAndShiftInchargeAndQ15Date(enteredBy, slot, shift, shiftIncharge, startDate);
	    } else if (pid != null && enteredBy != null && slot != null && shift != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15SlotAndShift(pid, enteredBy, slot, shift);
	    }  else if (pid != null && enteredBy != null && slot != null && shiftIncharge != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15SlotAndShiftIncharge(pid, enteredBy, slot, shiftIncharge);
	    }  else if (pid != null && enteredBy != null && slot != null && startDate != null) {
	        // Filter by PID, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15SlotAndQ15Date(pid, enteredBy, slot, startDate);
	    }  else if (enteredBy != null && slot != null && shift != null && shiftIncharge != null) {
	        // Filter by PID, enteredBy, and date range
	        configs = repository.findByEnteredByAndQ15SlotAndShiftAndShiftIncharge(enteredBy, slot, shift, shiftIncharge);
	    } else if (slot != null && shift != null && shiftIncharge != null && startDate != null) {
	        // Filter by PID, enteredBy, and date range
	        configs = repository.findByQ15SlotAndShiftAndShiftInchargeAndQ15Date(slot, shift, shiftIncharge, startDate);
	    } else if (pid != null && enteredBy != null && slot != null) { 
	        // Filter by PID, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15Slot(pid, enteredBy, slot);
	    } else if (pid != null && enteredBy != null && shift != null) {
	        // Filter by PID, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndShift(pid, enteredBy, shift);
	    } else if (pid != null && enteredBy != null && shiftIncharge != null) {
	        // Filter by PID, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndShiftIncharge(pid, enteredBy, shiftIncharge);
	    } else if (pid != null && enteredBy != null && startDate != null) {
	        // Filter by PID, enteredBy, and date range
	        configs = repository.findByPIDAndEnteredByAndQ15Date(pid, enteredBy, startDate);
	    } else if (pid != null && slot != null && shift != null) {
	        // Filter by findByPID and enteredBy
	        configs = repository.findByPIDAndQ15SlotAndShift(pid, slot, shift);  
	    } else if (pid != null && slot != null && shiftIncharge != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndQ15SlotAndShiftIncharge(pid, slot, shiftIncharge); 
	    } else if (pid != null && slot != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndQ15SlotAndQ15Date(pid, slot, startDate); 
	    } else if (pid != null && shift != null && shiftIncharge != null) { 
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndShiftAndShiftIncharge(pid, shift, shiftIncharge); 
	    } else if (pid != null && shift != null && startDate != null) { 
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndShiftAndQ15Date(pid, shift, startDate); 
	    } else if (pid != null && shiftIncharge != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndShiftInchargeAndQ15Date(pid, shiftIncharge, startDate);
	    } else if (enteredBy != null && slot != null && shift != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByEnteredByAndQ15SlotAndShift(enteredBy, slot, shift);
	    } else if (enteredBy != null && slot != null && shiftIncharge != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByEnteredByAndQ15SlotAndShiftIncharge(enteredBy, slot, shiftIncharge);
	    } else if (enteredBy != null && slot != null && startDate != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByEnteredByAndQ15SlotAndQ15Date(enteredBy, slot, startDate);
	    } else if (enteredBy != null && shift != null && shiftIncharge != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByEnteredByAndShiftAndShiftIncharge(enteredBy, shift, shiftIncharge); 
	    } else if (enteredBy != null && shift != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByEnteredByAndShiftAndQ15Date(enteredBy, shift, startDate); 
	    } else if (enteredBy != null && shiftIncharge != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByEnteredByAndShiftInchargeAndQ15Date(enteredBy, shiftIncharge, startDate); 
	    } else if (slot != null && shift != null && shiftIncharge != null) {
	        // Filter by Pid, enteredBy, and date range
	        configs = repository.findByQ15SlotAndShiftAndShiftIncharge(slot, shift, shiftIncharge);
	    } else if (slot != null && shiftIncharge != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByQ15SlotAndShiftInchargeAndQ15Date(slot, shiftIncharge, startDate); 
	    } else if (shift != null && shiftIncharge != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByShiftAndShiftInchargeAndQ15Date(shift, shiftIncharge, startDate); 
	    } else if (pid != null && enteredBy != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndEnteredBy(pid, enteredBy);
	    } else if (pid != null && slot != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndQ15Slot(pid, slot); 
	    } else if (pid != null && shift != null) { 
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndShift(pid, shift); 
	    } else if (pid != null && shiftIncharge != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndShiftIncharge(pid, shiftIncharge);
	    } else if (pid != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByPIDAndQ15Date(pid, startDate); 
	    }   else if (enteredBy != null && slot != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByEnteredByAndQ15Slot(enteredBy, slot);
	    } else if (enteredBy != null && shift != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByEnteredByAndShift(enteredBy, shift); 
	    } else if (enteredBy != null && shiftIncharge != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByEnteredByAndShiftIncharge(enteredBy, shiftIncharge); 
	    } else if (enteredBy != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByEnteredByAndQ15Date(enteredBy, startDate); 
	    }   else if (slot != null && shift != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByQ15SlotAndShift(slot, shift); 
	    } else if (slot != null && shiftIncharge != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByQ15SlotAndShiftIncharge(slot, shiftIncharge); 
	    } else if (slot != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByQ15SlotAndQ15Date(slot, startDate);  
	    }  else if (shift != null && shiftIncharge != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByShiftAndShiftIncharge(shift, shiftIncharge); 
	    } else if (shift != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByShiftAndQ15Date(shift, startDate); 
	    } else if (shiftIncharge != null && startDate != null) {
	        // Filter by Pid and enteredBy
	        configs = repository.findByShiftInchargeAndQ15Date(shiftIncharge, startDate); 
	    } else if (pid != null) {
	        // Filter by enteredBy
	        configs = repository.findByPID(pid); 
	    } else if (enteredBy != null) {
	        // Filter by enteredBy
	        configs = repository.findByEnteredBy(enteredBy);
	    } else if (slot != null) {
	        // Filter by enteredBy
	        configs = repository.findByQ15Slot(slot); 
	    } else if (shift != null) {
	        // Filter by enteredBy
	        configs = repository.findByShift(shift); 
	    } else if (shiftIncharge != null) {
	        // Filter by enteredBy
	        configs = repository.findByShiftIncharge(shiftIncharge);
	    } else if (startDate != null) {
	        // Filter by enteredBy
	        configs = repository.findByQ15Date(startDate);
	    } else {
	        // No filter criteria provided
	        return ResponseEntity.badRequest().body("No filter criteria provided"); 
	    }
 

	    if (!configs.isEmpty()) {
	        Map<String, List<Config>> configsByPid = configs.stream()
	                .collect(Collectors.groupingBy(Config::getPID));

	        List<List<Config>> result = new ArrayList<>(configsByPid.values());
	        VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0215");
	        verifyResponse.setData2(result);
	        return ResponseEntity.ok(verifyResponse);
	    } else {
	        VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0083");
	        return ResponseEntity.ok(verifyResponse);
	    }
	}
	
	@GetMapping("/getByDateShow/{q15date}")
	public ResponseEntity<?> getQ15ResponseToShow(@PathVariable String q15date) {

	    List<Config> configList = repository.findByQ15Date(q15date);

	    if (!configList.isEmpty()) {
	        Map<String, Q15Response> q15ResponseMap = new HashMap<>(); // Changed type to Q15Response
	        for (Config config : configList) {
	            String pid = config.getPID();
	            
	            //get the patient name and bed number
	            Optional<Patient> patientOptional = pRepository.findById(pid);
	            if (patientOptional.isPresent()) {
	                Patient patient = patientOptional.get();

	                Name pName = patient.getBasicDetails().get(0).getName().get(0);
	                String PgivenName = pName.getGiven();
	                String PfamilyName = pName.getFamily();

	                String patientName = PgivenName + " " + PfamilyName;
	                String bedNo = patient.getAssignedBed();
	                
	          Q15Response q15Response = q15ResponseMap.get(pid); // Initialize within if condition
	             if (q15Response == null) {
	                 q15Response = new Q15Response();
	                 q15Response.setId(config.getId());
	                 q15Response.setPatient(patientName);
	                 q15Response.setBed(bedNo);
	                 q15Response.setQ15Date(q15date);
	                 q15Response.setSlotData(new ArrayList<>()); // Initialize slotData list
	                 q15ResponseMap.put(pid, q15Response);
	             }

	              //get the staff name (enteredBy)
	              Optional<Staff> staffOptional = sRepo.findById(config.getEnteredBy());
	              if (staffOptional.isPresent()) {
	                  Staff staff = staffOptional.get();

	                  com.MHC.Project.Model.Staff.Name sName = staff.getName().get(0);
	                  String SgivenName = sName.getGiven();
	                  String SfamilyName = sName.getFamily();

	                  String staffName = SgivenName + " " + SfamilyName;
	                    
	               //get the staff name (shiftIncharge) 
	               Optional<Staff> staffInchargeOptional = sRepo.findById(config.getShiftIncharge());
	               if (staffInchargeOptional.isPresent()) {
	                   Staff staffIncharge = staffInchargeOptional.get();

	                   com.MHC.Project.Model.Staff.Name iName = staffIncharge.getName().get(0);
	                   String IgivenName = iName.getGiven();
	                   String IfamilyName = iName.getFamily();

	                   String InchargeName = IgivenName + " " + IfamilyName;

	               
	          slot slot = new slot();
	                        
	             if (slot != null) {
	                slot.setQ15Slot(config.getQ15Slot());
	                slot.setQ15Time(config.getQ15Time());
	                slot.setIncharge(InchargeName);
	                slot.setEnteredBy(staffName);
	                slot.setLocation(config.getLocationName());
	                slot.setActivity(config.getActivityName());
	                slot.setProximityStatus(config.isSkippedScanning());

	                if (config.getQ15Time().isEmpty()) {
  	                    slot.setSlotMissed(true);
	                } else {
	                    slot.setSlotMissed(false);
	                }

	                // Extract HHmm from the timeStamp
                    String timeStamp = config.getTimeStamp().substring(8, 12);
                    // Extract HHmm from q15Time
                    String[] q15TimeParts = config.getQ15Time().split("-");
                    if (q15TimeParts.length == 2) {
                    	String startTime = q15TimeParts[0];
                        String endTime = q15TimeParts[1];

                        // Check if timeStamp is within q15Time range
                        boolean isTimeInRange = timeStamp.compareTo(startTime) >= 0 && timeStamp.compareTo(endTime) <= 0;
                        slot.setSlotLateEntry(!isTimeInRange);
					} else {
						slot.setSlotLateEntry(true);
					}
                    
	                q15Response.getSlotData().add(slot);
	             }
	                    }
	                }
	            }
	        }
	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
	        DataResponse<?> dataResponse = new DataResponse<>(response, new ArrayList<>(q15ResponseMap.values()));
	        return ResponseEntity.ok(dataResponse);
	    }
	    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0083"));
	}
	
	@GetMapping("/getByDate/{q15Date}")
	public ResponseEntity<?> getQ15report(@PathVariable String q15Date) {
	    List<Config> configList = repository.findByQ15Date(q15Date);

	    if (!configList.isEmpty()) {
	        Map<String, Q15Report> pidToQ15ReportMap = new HashMap<>();
	        Q15Report q15Response; 
	        for (Config config : configList) {
	            String pid = config.getPID();

	          q15Response = pidToQ15ReportMap.get(pid);
	            if (q15Response == null) {
	                q15Response = new Q15Report();
	                q15Response.setPid(pid);
	                q15Response.setQ15Date(q15Date);
	                q15Response.setId(config.getId());
	                q15Response.setShiftIncharge(new ShiftIncharge());
	                q15Response.setEnteredBy(new EnteredBy());
	                q15Response.setData(new ArrayList<>());
	                pidToQ15ReportMap.put(pid, q15Response);
	            }

	            DataEntry data = new DataEntry();
	            data.setRemarks(config.getRemarks());
	            data.setQ15Slot(config.getQ15Slot());
	            data.setQ15Time(config.getQ15Time());
	            data.setLocation(config.getLocation());
	            data.setActivity(config.getActivity());
	            data.setLocationName(config.getLocationName());
	            data.setActivityName(config.getActivityName());
	            q15Response.getData().add(data);

	            String shift = config.getShift();
	            ShiftIncharge shiftIncharge = q15Response.getShiftIncharge();
	            EnteredBy enteredBy = q15Response.getEnteredBy();

	            if (shift.equalsIgnoreCase("shift-A")) {
	                shiftIncharge.setShiftInchargeA(config.getShiftIncharge());
	                setEnteredByForSlot(enteredBy.getShiftA(), config.getQ15Slot(), config.getEnteredBy());
	            } else if (shift.equalsIgnoreCase("shift-B")) {
	                shiftIncharge.setShiftInchargeB(config.getShiftIncharge());
	                setEnteredByForSlot(enteredBy.getShiftB(), config.getQ15Slot(), config.getEnteredBy());
	            } else if (shift.equalsIgnoreCase("shift-C")) {
	                shiftIncharge.setShiftInchargeC(config.getShiftIncharge());
	                setEnteredByForSlot(enteredBy.getShiftC(), config.getQ15Slot(), config.getEnteredBy());
	            }
	       
			// Ensure that null properties are set to non-null default values
	        ensureNonNullOrDefaultValues(q15Response);
 }
	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
	        DataResponse<List<Q15Report>> dataResponse = new DataResponse<>(response, new ArrayList<>(pidToQ15ReportMap.values()));

	        return ResponseEntity.ok(dataResponse);
	    }

	    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0083"));
	}

	private void setEnteredByForSlot(Q15Report.shift shift, String q15Slot, String enteredBy) {
	    if (shift == null) {
	        // If 'shift' is null, create a new 'shift' object
	        shift = new Q15Report.shift();
	    }
	    
	    switch (q15Slot) {
        case "A06","B06","C06","D06","A07","B07","C07","D07":
            shift.setSlot1(enteredBy);
            break;
        case "A08","B08","C08","D08","A09","B09","C09","D09":
        	shift.setSlot2(enteredBy); 
        	break;
        case "A10","B10","C10","D10","A11","B11","C11","D11":
            shift.setSlot3(enteredBy);
            break;
        case "A12","B12","C12","D12","A13","B13","C13","D13":
        	shift.setSlot4(enteredBy); 
        	break;
        case "A14","B14","C14","D14","A15","B15","C15","D15":
            shift.setSlot1(enteredBy);
            break; 
        case "A16","B16","C16","D16","A17","B17","C17","D17":
            shift.setSlot2(enteredBy);
            break;
        case "A18","B18","C18","D18","A19","B19","C19","D19":
            shift.setSlot3(enteredBy);
            break;
        case "A20","B20","C20","D20","A21","B21","C21","D21":
            shift.setSlot4(enteredBy);
            break;
        case "A22","B22","C22","D22","A23","B23","C23","D23":
            shift.setSlot1(enteredBy);
            break;
        case "A00","B00","C00","D00","A01","B01","C01","D01":
            shift.setSlot2(enteredBy);
            break;
        case "A02","B02","C02","D02","A03","B03","C03","D03":
            shift.setSlot3(enteredBy);
            break;
        case "A04","B04","C04","D04","A05","B05","C05","D05":
            shift.setSlot4(enteredBy);
            break;
        default:
            // Handle unknown q15Slot values
            break;
    }
	}
	
	private void ensureNonNullOrDefaultValues(Q15Report q15Response) {
	    if (q15Response.getEnteredBy().getShiftA() == null) {
	        q15Response.getEnteredBy().setShiftA(new Q15Report.shift());
	    }
	    if (q15Response.getEnteredBy().getShiftB() == null) {
	        q15Response.getEnteredBy().setShiftB(new Q15Report.shift());
	    }
	    if (q15Response.getEnteredBy().getShiftC() == null) {
	        q15Response.getEnteredBy().setShiftC(new Q15Report.shift());
	    } 
	}
	
	@GetMapping("/getByDateRange")
	public ResponseEntity<?> getQ15reportByDateRange(
	        @RequestParam String startDate,
	        @RequestParam(required = false) String endDate) {
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDate start = LocalDate.parse(startDate, dateFormatter);

	    Map<String, Map<String, Q15Report>> dataByDateAndPid = new HashMap<>();
	    Q15ReportResponse1 q15ReportResponse = new Q15ReportResponse1("MHC - 0272", "Success: Data fetched successfully");

	    while (endDate == null || !start.isAfter(LocalDate.parse(endDate, dateFormatter))) {
	        String q15Date = start.format(dateFormatter);
	        List<Config> configList = repository.findByQ15Date(q15Date);

	        for (Config config : configList) {
	            String pid = config.getPID();

	            dataByDateAndPid.computeIfAbsent(q15Date, k -> new HashMap<>());

	            Map<String, Q15Report> q15ReportsByPid = dataByDateAndPid.get(q15Date);
	            Q15Report q15Report = q15ReportsByPid.get(pid);

	            if (q15Report == null) {
	                q15Report = new Q15Report();
	                q15Report.setPid(pid);
	                q15Report.setQ15Date(q15Date);
	                q15Report.setId(config.getId());
	                q15Report.setShiftIncharge(new ShiftIncharge());
	                q15Report.setEnteredBy(new EnteredBy());
	                q15Report.setData(new ArrayList<>());
	                q15ReportsByPid.put(pid, q15Report);
	            }

	            DataEntry data = new DataEntry();
	            data.setRemarks(config.getRemarks());
	            data.setQ15Slot(config.getQ15Slot());
	            data.setQ15Time(config.getQ15Time());
	            data.setLocation(config.getLocation());
	            data.setActivity(config.getActivity());
	            data.setLocationName(config.getLocationName());
	            data.setActivityName(config.getActivityName());
	            q15Report.getData().add(data);

	            String shift = config.getShift();
	            ShiftIncharge shiftIncharge = q15Report.getShiftIncharge();
	            EnteredBy enteredBy = q15Report.getEnteredBy();

	            if (shift.equalsIgnoreCase("shift-A")) {
	                shiftIncharge.setShiftInchargeA(config.getShiftIncharge());
	                setEnteredByForSlot(enteredBy.getShiftA(), config.getQ15Slot(), config.getEnteredBy());
	            } else if (shift.equalsIgnoreCase("shift-B")) {
	                shiftIncharge.setShiftInchargeB(config.getShiftIncharge());
	                setEnteredByForSlot(enteredBy.getShiftB(), config.getQ15Slot(), config.getEnteredBy());
	            } else if (shift.equalsIgnoreCase("shift-C")) {
	                shiftIncharge.setShiftInchargeC(config.getShiftIncharge());
	                setEnteredByForSlot(enteredBy.getShiftC(), config.getQ15Slot(), config.getEnteredBy());
	            }

	            // Ensure that null properties are set to non-null default values
	            ensureNonNullOrDefaultValues(q15Report);
	        }

	        start = start.plusDays(1);
	    }

	    if (dataByDateAndPid.isEmpty()) {
	        return ResponseEntity.ok(new Q15ReportResponse(q15ReportResponse, null));
	    } else {
	        List<Q15Report> consolidatedData = dataByDateAndPid.values().stream()
	                .flatMap(q15ReportsByPid -> q15ReportsByPid.values().stream())
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(new Q15ReportResponse(q15ReportResponse, consolidatedData));
	    }
	}
//	private void setEnteredByForSlot(Q15Report.shift shift, String q15Slot, String enteredBy) {
//	    if (shift == null) {
//	        // If 'shift' is null, create a new 'shift' object
//	        shift = new Q15Report.shift();
//	    }
//	    
//	    switch (q15Slot) {
//        case "A06","B06","C06","D06","A07","B07","C07","D07":
//            shift.setSlot1(enteredBy);
//            break;
//        case "A08","B08","C08","D08","A09","B09","C09","D09":
//        	shift.setSlot2(enteredBy); 
//        	break;
//        case "A10","B10","C10","D10","A11","B11","C11","D11":
//            shift.setSlot3(enteredBy);
//            break;
//        case "A12","B12","C12","D12","A13","B13","C13","D13":
//        	shift.setSlot4(enteredBy); 
//        	break;
//        case "A14","B14","C14","D14","A15","B15","C15","D15":
//            shift.setSlot1(enteredBy);
//            break; 
//        case "A16","B16","C16","D16","A17","B17","C17","D17":
//            shift.setSlot2(enteredBy);
//            break;
//        case "A18","B18","C18","D18","A19","B19","C19","D19":
//            shift.setSlot3(enteredBy);
//            break;
//        case "A20","B20","C20","D20","A21","B21","C21","D21":
//            shift.setSlot4(enteredBy);
//            break;
//        case "A22","B22","C22","D22","A23","B23","C23","D23":
//            shift.setSlot1(enteredBy);
//            break;
//        case "A00","B00","C00","D00","A01","B01","C01","D01":
//            shift.setSlot2(enteredBy);
//            break;
//        case "A02","B02","C02","D02","A03","B03","C03","D03":
//            shift.setSlot3(enteredBy);
//            break;
//        case "A04","B04","C04","D04","A05","B05","C05","D05":
//            shift.setSlot4(enteredBy);
//            break;
//        default:
//            // Handle unknown q15Slot values
//            break;
//    }
//	}
//	
//	private void ensureNonNullOrDefaultValues1(Q15Report q15Response) {
//	    if (q15Response.getEnteredBy().getShiftA() == null) {
//	        q15Response.getEnteredBy().setShiftA(new Q15Report.shift());
//	    }
//	    if (q15Response.getEnteredBy().getShiftB() == null) {
//	        q15Response.getEnteredBy().setShiftB(new Q15Report.shift());
//	    }
//	    if (q15Response.getEnteredBy().getShiftC() == null) {
//	        q15Response.getEnteredBy().setShiftC(new Q15Report.shift());
//	    } 
//	}
	
//	@DeleteMapping("/deleteDate/{q15Date}")
//	public ResponseEntity<?> deleteDate(@PathVariable String q15Date) {
//		VerifyResponse verifyResponse;
//
//		if (repository.existsByQ15Date(q15Date)) {
//			repository.deleteByQ15Date(q15Date);
//			verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 216");
//			return ResponseEntity.ok(verifyResponse);
//		}
//		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0082"));
//	}
}