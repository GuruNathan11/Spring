package com.MHC.Project.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.PtStRecords;
import com.MHC.Project.Model.PtStRequest;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.PtStRecordsRepo;
import com.MHC.Project.Repository.PtStRepo;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PtStService;
import com.MHC.Project.Service.SPTAssignService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/patient_staff")
@SecurityRequirement(name = "mettlerHealth")
public class PtStController {

	@Autowired
	private PtStService service;

	@Autowired
	SPTAssignService service1;
	
	@Autowired
	PtStRecordsRepo ptstRecordsRepo;

	@Autowired
	PatientRepository pRepository;

	@Autowired
	PtStRepo repository;
	
	@Autowired
	PatientRepository patientRepository;

	@Autowired
	SPTAssignService sptAssignService;

	@Autowired
	StaffRepository srepo;

	@PostMapping("/assign")
	public ResponseEntity<?> savePsAssign(@RequestBody @Validated PtStRequest psAssign) {
		String[] patientIds = psAssign.getPID();
		List<Patient> patients = pRepository.findByIdIn(Arrays.asList(patientIds));

		// Check if all patients exist
		if (patients.size() != patientIds.length) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0057"));
		}
		PtStRequest pts = service.savePsAssign(psAssign);
		DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 0178");
		DataResponse<PtStRequest> signInResponse = new DataResponse<>(verifyResponse1, pts);
		return ResponseEntity.ok().body(signInResponse);
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllPatientStaffAssignment() throws PageNotFound {
		List<PtStRecords> pts = service.getAllPatientStaffAssignment();
		DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 0179");
		DataResponse<List<PtStRecords>> signInResponse = new DataResponse<>(verifyResponse1, pts);
		return ResponseEntity.ok().body(signInResponse);
	}

	@GetMapping("/getByStaffId/{staffId}")
	public ResponseEntity<?> getPatientIdsByStaffId(@PathVariable("staffId") String staffId) {
		List<String> patientIds = service.getUniquePatientIdsByStaffId(staffId);
		if (patientIds.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0180"));
		}
		DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 0181");
		DataResponse<List<String>> signInResponse = new DataResponse<>(verifyResponse1, patientIds);
		return ResponseEntity.ok().body(signInResponse);
	}

	@GetMapping("/get/{PID}")
	public ResponseEntity<?> getUniqueStaffDetailsByPid(@PathVariable("PID") String pid) {
		Map<String, List<String>> staffDetails = service.getUniqueStaffDetailsByPid(pid);
		if (staffDetails.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0054"));
		}
		DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 0183");
		DataResponse<Map<String, List<String>>> signInResponse = new DataResponse<>(verifyResponse1, staffDetails);
		return ResponseEntity.ok().body(signInResponse);
	}
	
	@DeleteMapping("/PtStAssign/{pid}")
	public ResponseEntity<?> deletePtStAssignPatient(@PathVariable String pid) {
	    
	    Optional<PtStRequest> ptst = repository.findByPID(pid);
	    List<PtStRecords> ptstRecords = ptstRecordsRepo.findByPID(pid);

	    if (ptst.isPresent()) {
	        PtStRequest ptstClass = ptst.get();
	        repository.delete(ptstClass);
	     
	        
	        if (!ptstRecords.isEmpty()) {
	            for (PtStRecords ptStRecord : ptstRecords) {
	                String[] ptstRecordPID = ptStRecord.getPID(); 
	                // Check if pid exists within ptstRecordPID array
	                if (Arrays.asList(ptstRecordPID).contains(pid)) {
	                    ptstRecordsRepo.delete(ptStRecord);
	                }
	            }
	        }
	        return ResponseEntity.ok().body("ptst deleted successfully based on given pid");
	    }
	    return ResponseEntity.ok().body("no pid found for the given");
	}
	
	@GetMapping("/getMyPatients/{staffId}")
	public ResponseEntity<?> getPatientListByStaffId(@PathVariable("staffId") String staffId) {
		List<String> patientIds = service.getUniquePatientIdsByStaffId(staffId);
		List<Patient> patientDataList = new ArrayList<>();
	    for (String pid : patientIds) {
	        // Assuming there is a method findByPid in patientRepository
	        Optional<Patient> optionalPatient = patientRepository.findById(pid);
	        optionalPatient.ifPresent(patientDataList::add);
	    }
		if (patientIds.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0180"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientDataList);
		return ResponseEntity.ok(dataResponse);
	}
}
