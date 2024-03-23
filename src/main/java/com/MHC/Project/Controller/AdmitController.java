package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.MHC.Project.Model.AdmitPatient;
import com.MHC.Project.Model.AdmitRecords;
import com.MHC.Project.Model.Beacon;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Repository.AdmitRecordRepository;
import com.MHC.Project.Repository.AdmitRepository;
import com.MHC.Project.Repository.BeaconRepository;
import com.MHC.Project.Repository.BedMasterRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Request.DischargeRequest;
import com.MHC.Project.Request.TransferRequest;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/admit")
@SecurityRequirement(name = "mettlerHealth")
public class AdmitController {
	
	@Autowired
	AdmitRepository repository;
	
	@Autowired
	AdmitRecordRepository arRepository;
	
	@Autowired
	BedMasterRepository configRepository;
	
	@Autowired
	BeaconRepository beaconRepo;
	
	@Autowired
	PatientRepository patientRepository;
	
	@PostMapping("/add")
	public ResponseEntity<?> AdmitPatient(@RequestBody AdmitPatient admit){
		admit.setId(PatientService.generateUID());
		admit.setStatus("Admitted");
//		Optional<BedMasterConfig> bedConfig = configRepository.findById(admit.getBedId());
		
		boolean trackingDevice = admit.isTrackingDevice();
		if(trackingDevice) {
			String deviceId = admit.getDeviceId();
			Optional<Beacon> device = beaconRepo.findByDeviceId(deviceId);
			if(!device.isPresent()) {
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0041"));
			}
			
			admit.setDeviceId(deviceId);
//			Patient patient = new Patient();
			Optional<Patient> patientop = patientRepository.findById(admit.getPid());
			patientop.ifPresent(p -> {
			    // Set the beaconDevice for the patient
				p.setActive("1");
			    p.setBeaconDevice(deviceId);

			    // Save the updated patient back to the repository
			    patientRepository.save(p);
			});
		}
		
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String admissionDate = createDate.format(formatter);
		
		admit.setAdmissionDate(admissionDate);
		
		AdmitRecords record = new AdmitRecords(PatientService.generateUID(),admit.getId(), admit.getPid(),admit.getAdmissionDate(),
				admit.getAdmissionType(),admit.getAttendingPhysician(),admit.getPrimaryPhysician(),
				admit.getFacilityTreatingSpeciality(),admit.getSourceOfAdmission(),admit.getWardLocation(),
				admit.getBedId(),admit.getBriefDescription(),admit.getStatus(),admit.isTrackingDevice(),
				admit.getDeviceId(),admit.getLastVisit());
		
		AdmitPatient savedAdmit = repository.save(admit);
		arRepository.save(record);
		DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
        DataResponse<?> dataResponse = new DataResponse<>(response, savedAdmit);
        return ResponseEntity.ok(dataResponse);
	}

	@PostMapping("/transferPatient/{admitId}")
	public ResponseEntity<?> transferPatient(
	    @PathVariable String admitId,
	    @RequestBody TransferRequest transferRequest) {
	    // Fetch the current admission record from the database
	    Optional<AdmitPatient> optionalAdmit = repository.findById(admitId);
	    
	    LocalDateTime updateDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String transferedDate = updateDate.format(formatter);

	    if (optionalAdmit.isPresent()) {
	        AdmitPatient admit = optionalAdmit.get();
	        
	        // Update the ward location and room/bed
	        admit.setPid(transferRequest.getPid());
	        admit.setAdmitId(transferRequest.getAdmitId());
	        admit.setWardLocation(transferRequest.getWardLocation());
	        admit.setBedId(transferRequest.getBedId());
	        admit.setTransferedDate(transferedDate);
	        admit.setTransferedBy(transferRequest.getTransferedBy());
	        admit.setTypeOfTransfer(transferRequest.getTypeOfTransfer());
	        admit.setBriefDescriptionTransfer(transferRequest.getBriefDescriptionTransfer());
	        admit.setAttendingPhysician1(transferRequest.getAttendingPhysician1());
	        admit.setPrimaryPhysician1(transferRequest.getPrimaryPhysician1());
	        admit.setFacilityTreatingSpeciality1(transferRequest.getFacilityTreatingSpeciality1());
	        admit.setLastVisit(transferRequest.getLastVisit());
	        
	        admit.setStatus("Transfered");
	        AdmitRecords newRecord = new AdmitRecords();
	        newRecord.setId(PatientService.generateUID());
	        newRecord.setPid(transferRequest.getPid());
	        newRecord.setAdmitId(transferRequest.getAdmitId());
	        newRecord.setWardLocation(transferRequest.getWardLocation());
	        newRecord.setBedId(transferRequest.getBedId());
	        newRecord.setTransferedDate(transferedDate);
	        newRecord.setTransferedBy(transferRequest.getTransferedBy());
	        newRecord.setTypeOfTransfer(transferRequest.getTypeOfTransfer());
	        newRecord.setAttendingPhysician1(transferRequest.getAttendingPhysician1());
	        newRecord.setPrimaryPhysician1(transferRequest.getPrimaryPhysician1());
	        newRecord.setFacilityTreatingSpeciality1(transferRequest.getFacilityTreatingSpeciality1());
	        newRecord.setBriefDescriptionTransfer(transferRequest.getBriefDescriptionTransfer());
	        newRecord.setLastVisit(transferRequest.getLastVisit());
	        
	        arRepository.save(newRecord);
	        // Save the updated admission record 
	        AdmitPatient updatedAdmit = repository.save(admit);
	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
	        DataResponse<?> dataResponse = new DataResponse<>(response, updatedAdmit);
	        return ResponseEntity.ok(dataResponse);
	    } else {
	        return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0088"));
	    }
	}
	
	@PostMapping("/dischargePatient/{admitId}")
    public ResponseEntity<?> dischargePatient(@PathVariable String admitId, 
    										  @RequestBody DischargeRequest dischargeRequest) {
	 
        // Fetch the current admission record from the database
        AdmitPatient currentAdmit = repository.findById(admitId).orElse(null);

        if (currentAdmit == null) {
        	 return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0088"));
        }
        
        LocalDateTime dischargeDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDischargeDate = dischargeDateTime.format(formatter);
        
        // Set the formatted discharge date in the admission record
        currentAdmit.setDischargeDate(formattedDischargeDate);
        currentAdmit.setStatus("Discharged");
        currentAdmit.setDischargeBy(dischargeRequest.getDischargeBy());
        currentAdmit.setAdmitId(dischargeRequest.getAdmitId());
        currentAdmit.setDischargeType(dischargeRequest.getDischargeType());
        currentAdmit.setBriefDescriptionDischarge(dischargeRequest.getSummaryOfCare());
        currentAdmit.setLastVisit(dischargeRequest.getLastVisit());
        
        // Save the updated admission record
        AdmitPatient dischargedAdmit = repository.save(currentAdmit);
        
        AdmitRecords records = new AdmitRecords();
        records.setId(PatientService.generateUID());
        records.setAdmitId(dischargeRequest.getAdmitId());
        records.setPid(dischargeRequest.getPid());
        records.setDischargeBy(dischargeRequest.getDischargeBy());
        records.setDischargeType(dischargeRequest.getDischargeType());
        records.setDischargeDate(formattedDischargeDate);
        records.setSummaryOfCare(dischargeRequest.getSummaryOfCare());
        records.setLastVisit(dischargeRequest.getLastVisit());
        arRepository.save(records);
        
        Optional<Patient> patientop = patientRepository.findById(dischargeRequest.getPid());
		patientop.ifPresent(p -> {
		    // Set the beaconDevice for the patient
			p.setActive("0");
		    p.setBeaconDevice(null);
		    // Save the updated patient back to the repository
		    patientRepository.save(p);
		});
        
        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
        DataResponse<?> dataResponse = new DataResponse<>(response, dischargedAdmit);
        return ResponseEntity.ok(dataResponse);
    }
	 @GetMapping("/getAdmitPatient")
		public ResponseEntity<?> getVisitById() {
			
			List<AdmitPatient> status = repository.findByStatus("Admitted");
			if (!status.isEmpty()) {
				
				DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
				DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, status);
				return ResponseEntity.ok(dataResponse);
			}
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));	
		}
	 @GetMapping("/getTransferedPatient")
		public ResponseEntity<?> getTransferedPatient() {
			
			List<AdmitPatient> status = repository.findByStatus("Transfered");
			if (!status.isEmpty()) {
				
				DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
				DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, status);
				return ResponseEntity.ok(dataResponse);
			}
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));	
		}
	 @GetMapping("/getDischargedPatient")
		public ResponseEntity<?> getDischargedPatient() {
			
			List<AdmitPatient> status = repository.findByStatus("Discharged");
			if (!status.isEmpty()) {
				
				DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
				DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, status);
				return ResponseEntity.ok(dataResponse);
			}
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));	
		}
	 
//	 @GetMapping("/getAll")
//	 public ResponseEntity<?> getAllADT(){
//		 List<AdmitPatient> ADTList = repository.findAll();
//		 DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, ADTList);
//			return ResponseEntity.ok(dataResponse);
//	 }
	 
	 @GetMapping("/getByPid/{pid}")
	 public ResponseEntity<?> getAllADT(@PathVariable String pid){
		 List<AdmitPatient> ADTList = repository.findByPid(pid);
		 DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, ADTList);
			return ResponseEntity.ok(dataResponse);
	 }
	 
	 @GetMapping("/getTodayAdmitted/{admissionDate}")
	 public ResponseEntity<?> getTodayAdmitted(@PathVariable String admissionDate){
		
		 List<AdmitPatient> admitList = repository.findByAdmissionDateAndStatus(admissionDate, "Admitted");
		 // Assuming PID is a field in the AdmitPatient class
		    List<String> pidList = admitList.stream()
		            .map(AdmitPatient::getPid) // Assuming getPid() is the getter for PID
		            .collect(Collectors.toList());
		    List<Patient> patientDataList = new ArrayList<>();
		    for (String pid : pidList) {
		        // Assuming there is a method findByPid in patientRepository
		        Optional<Patient> optionalPatient = patientRepository.findById(pid);
		        optionalPatient.ifPresent(patientDataList::add);
		    }
		 DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientDataList);
			return ResponseEntity.ok(dataResponse);
	 }
	 
	 @GetMapping("/getTodayDischarged/{dischargeDate}")
	 public ResponseEntity<?> getTodayDischarged(@PathVariable String dischargeDate){
		
		 List<AdmitPatient> admitList = repository.findByDischargeDateAndStatus(dischargeDate, "Discharged");
		 // Assuming PID is a field in the AdmitPatient class
		    List<String> pidList = admitList.stream()
		            .map(AdmitPatient::getPid) // Assuming getPid() is the getter for PID
		            .collect(Collectors.toList());
		    List<Patient> patientDataList = new ArrayList<>();
		    for (String pid : pidList) {
		        // Assuming there is a method findByPid in patientRepository
		        Optional<Patient> optionalPatient = patientRepository.findById(pid);
		        optionalPatient.ifPresent(patientDataList::add);
		    }
//		    List<String> patientNames = patientDataList.stream()
//		            .map(Patient::getId) // Assuming getName() is the getter for patient name
//		            .collect(Collectors.toList());
		 DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientDataList);
			return ResponseEntity.ok(dataResponse);
	 }
}
