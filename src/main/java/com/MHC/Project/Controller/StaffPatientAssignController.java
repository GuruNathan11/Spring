package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Staff;
import com.MHC.Project.Model.StaffPatientAssign;
import com.MHC.Project.Repository.PtStRecordsRepo;
import com.MHC.Project.Repository.SPAssignRepo;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.SPTAssignService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/staff_patient")
@SecurityRequirement(name = "mettlerHealth")
public class StaffPatientAssignController {

	@Autowired
	SPAssignRepo repo;
	
	@Autowired
	StaffRepository srepo;
	
	@Autowired
	SPTAssignService service;
	
	@Autowired
	PtStRecordsRepo psrepo;
	
	@PostMapping("/assign")
	public ResponseEntity<?> saveSPAssign(@RequestBody @Validated StaffPatientAssign SptAssign) {
	
		String[] staffId = SptAssign.getSid();
		
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);
		
		List<Staff> staff = srepo.findByIdIn(Arrays.asList(staffId));
		   // Check if the patient exists
	    if (staff == null) {
	    	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0038"));
	    }
	    
	    SptAssign.setCreatedAt(createdAt);
	    StaffPatientAssign StaffPatientAssign = service.saveSpAssign(SptAssign);
	    DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 0178");
		DataResponse<?> signInResponse = new DataResponse<>(verifyResponse1, StaffPatientAssign);
		return ResponseEntity.ok().body(signInResponse);
	}

	@GetMapping("/get_all")
	public ResponseEntity<DataResponse<List<StaffPatientAssign>>> getAllStaffPatientAssign() {
		List<StaffPatientAssign> organizationList = service.getAllList();
		DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<List<StaffPatientAssign>> signInResponse = new DataResponse<>(verifyResponse1, organizationList);
		return ResponseEntity.ok().body(signInResponse);
	}
	
}