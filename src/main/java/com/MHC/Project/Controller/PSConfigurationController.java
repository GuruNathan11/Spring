package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.PSConfiguration;
import com.MHC.Project.Repository.PSConfigurationRepository;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Request.PSConfigDevice;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("api/PSConfig")
@SecurityRequirement(name = "mettlerHealth")
public class PSConfigurationController {
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	PSConfigurationRepository repository;
	
	@PostMapping("/register")
	public ResponseEntity<?> saveConfig(@RequestBody PSConfiguration psconfig) {
	    String date = psconfig.getDate();
	    PSConfiguration psConfigOptional = repository.findByDate(date);
	    
	    LocalDateTime createDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    String createdAt = createDate.format(formatter);

	    if (psConfigOptional != null) {
	        PSConfiguration existingConfig = psConfigOptional;
	        existingConfig.setDate(date);
	        
//	         Sort the shifts by shift name in ascending order
	        List<PSConfiguration.Shift> sortedShifts = psconfig.getShift().stream()
	                .sorted(Comparator.comparing(PSConfiguration.Shift::getStartTime))
	                .collect(Collectors.toList());

	        for (PSConfiguration.Shift newShift : sortedShifts) {
	            Optional<PSConfiguration.Shift> existingShiftOptional = existingConfig.getShift().stream()
	                    .filter(shift -> shift.getShiftName().equals(newShift.getShiftName()))
	                    .findFirst();

	            if (existingShiftOptional.isPresent()) {
	                // Update the existing shift with the new data
	                PSConfiguration.Shift existingShift = existingShiftOptional.get();
	                existingShift.setRnIncharge(newShift.getRnIncharge());
	                existingShift.setStartTime(newShift.getStartTime());
	                existingShift.setEndTime(newShift.getEndTime());

	                // Iterate through the provided schedule and update or add entries based on 'schedule.time'
	                for (PSConfiguration.Shift.Schedule newSchedule : newShift.getSchedule()) {
	                    Optional<PSConfiguration.Shift.Schedule> existingScheduleOptional = existingShift.getSchedule()
	                            .stream().filter(schedule -> schedule.getTime().equals(newSchedule.getTime()))
	                            .findFirst();

	                    if (existingScheduleOptional.isPresent()) {
	                        // Update the existing schedule entry if 'schedule.time' matches
	                        PSConfiguration.Shift.Schedule existingSchedule = existingScheduleOptional.get();
	                        existingSchedule.setBedStaff(newSchedule.getBedStaff());
	                    } else {
	                        // Add the new schedule entry if 'schedule.time' doesn't match
	                        existingShift.getSchedule().add(newSchedule);
	                    }
	                }

	            } else {
	                // Add the new shift entry if 'shift.name' doesn't match 
	                existingConfig.getShift().add(newShift);
	            }
	        }

	        // Sort the shifts in the existing configuration by shift name
	        existingConfig.getShift().sort(Comparator.comparing(PSConfiguration.Shift::getStartTime));

	        // Save the updated configuration
	        PSConfiguration updatedConfig = repository.save(existingConfig);
	        DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0269");
	        DataResponse<PSConfiguration> signInResponse = new DataResponse<>(dataResponse, updatedConfig);
	        return ResponseEntity.ok().body(signInResponse);

	    }

	    // If no matching configuration or shift exists, save the provided configuration as a new entry
	    psconfig.setId(PatientService.generateUID());
	    psconfig.setCreatedAt(createdAt);
	    PSConfiguration psConfig = repository.save(psconfig);
	    DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0268");
	    DataResponse<PSConfiguration> signInResponse = new DataResponse<>(dataResponse, psConfig);
	    return ResponseEntity.ok().body(signInResponse);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> UpdateConfig(@PathVariable String id, @RequestBody PSConfiguration psConfig) {

		PSConfiguration psConfigDetails = psConfig.build(psConfig.getId(), psConfig.getDate(), psConfig.getShift(), psConfig.getCreatedAt(), psConfig.getUpdatedAt());

		Optional<PSConfiguration> PsConfig = repository.findById(id);
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		if (PsConfig.isPresent()) {

			PSConfiguration PsConfigOptional = PsConfig.get();
			PsConfigOptional.setId(id);
			PsConfigOptional.setUpdatedAt(updatedAt);
			PsConfigOptional.update(psConfigDetails);
			PSConfiguration updatedConfig = repository.save(PsConfigOptional);

			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0269");
			DataResponse<PSConfiguration> signInResponse = new DataResponse<>(dataResponse, updatedConfig);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0092"));
		}
	}
	
	@PostMapping("registerDeviceId")
	public ResponseEntity<?> registerDeviceIdToPSConfig(@RequestBody PSConfigDevice configDevice) {
	    
	    String shiftName = configDevice.getShiftName();
	    String slotTime = configDevice.getSlotTime();
	    String staffId = configDevice.getStaffId();

	    List<PSConfiguration> psconfigList = repository.findAllByShiftShiftName(shiftName);

	    if (psconfigList != null && !psconfigList.isEmpty()) {
	        Optional<PSConfiguration> matchingConfig = psconfigList.stream()
	                .filter(psConfiguration -> psConfiguration.getShift() != null)
	                .filter(psConfiguration -> psConfiguration.getShift().stream()
	                        .flatMap(psShift -> psShift.getSchedule().stream())
	                        .anyMatch(psSchedule -> {
	                            if (psSchedule.getTime() != null && psSchedule.getBedStaff() != null) {
	                                return psSchedule.getTime().equals(slotTime) &&
	                                       psSchedule.getBedStaff().stream()
	                                                .anyMatch(bedstaff -> {
	                                                    String bedStaffId = bedstaff.getStaff();
	                                                    return bedStaffId != null && bedStaffId.equals(staffId);
	                                                });
	                            } else {
	                                return false;
	                            }
	                        }))
	                .findFirst();

	        if (matchingConfig.isPresent()) {
	            PSConfiguration psConfiguration = matchingConfig.get();
	            psConfiguration.getShift().stream()
	                    .filter(psShift -> psShift.getSchedule() != null)
	                    .flatMap(psShift -> psShift.getSchedule().stream())
	                    .flatMap(psSchedule -> psSchedule.getBedStaff().stream())
	                    .filter(bedstaff -> bedstaff.getStaff().equals(staffId))
	                    .forEach(bedstaff -> bedstaff.setDeviceId(configDevice.getDeviceId()));

	            repository.save(psConfiguration);

	            DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0270");
	            DataResponse<PSConfiguration> signInResponse = new DataResponse<>(dataResponse, psConfiguration);
	            return ResponseEntity.ok().body(signInResponse);
	        }
	    }

	    return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0199"));
	}

	
	@GetMapping("/getByDate/{date}")
	public ResponseEntity<?> getByDate(@PathVariable String date) {
		PSConfiguration configList = repository.findByDate(date);

		if (configList!=null) {
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0273");
			DataResponse<PSConfiguration> signInResponse = new DataResponse<>(dataResponse, configList);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0093"));
		}
	}
	
//	@GetMapping("/getByDate/{date}")
//	public ResponseEntity<?> getByDate(@PathVariable String date) {
//	    PSConfiguration configList = repository.findByDate(date);
//
//	    if (configList != null) {
//	        List<PSConfiguration.Shift> shifts = configList.getShift();
//
//	        for (PSConfiguration.Shift shift : shifts) {
//	            Optional<Staff> rnIncharge = staffRepository.findById(shift.getRnIncharge());
//
//	            if (rnIncharge.isPresent()) {
//	                // Now you can use rnIncharge to get the username or any other details you need
//	                String rnInchargeUsername = rnIncharge.get().getUsername();
//	                shift.setRnIncharge(rnInchargeUsername);
//	            }
//	        }
//
//	        DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0273");
//	        DataResponse<PSConfiguration> signInResponse = new DataResponse<>(dataResponse, configList);
//	        return ResponseEntity.ok().body(signInResponse);
//	    } else {
//	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0093"));
//	    }
//	}
	@GetMapping("/get_All")
	public ResponseEntity<?> getAll() {
	    List<PSConfiguration> psConfigList = repository.findAll();

		if (psConfigList!=null) {
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0273");
			DataResponse<List<PSConfiguration>> signInResponse = new DataResponse<>(dataResponse, psConfigList);
			return ResponseEntity.ok().body(signInResponse);
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0093"));
		}
	}
//	@GetMapping("/get_All")
//	public ResponseEntity<?> getAll() {
//	    List<PSConfiguration> psConfigList = repository.findAll();
//
//	    if (psConfigList != null) {
//	        for (PSConfiguration psConfig : psConfigList) {
//	            List<PSConfiguration.Shift> shifts = psConfig.getShift();
//
//	            for (PSConfiguration.Shift shift : shifts) {
//	                Optional<Staff> rnIncharge = staffRepository.findById(shift.getRnIncharge());
//
//	                if (rnIncharge.isPresent()) {
//	                    // Now you can use rnIncharge to get the username or any other details you need
//	                    String rnInchargeUsername = rnIncharge.get().getUsername();
//	                    shift.setRnIncharge(rnInchargeUsername);
//	                }
//	            }
//	        }
//
//	        DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0274");
//	        DataResponse<List<PSConfiguration>> signInResponse = new DataResponse<>(dataResponse, psConfigList);
//	        return ResponseEntity.ok().body(signInResponse);
//	    } else {
//	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0093"));
//	    }
//	}


}
