package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.MHC.Project.Model.LabOrder;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.Staff;
import com.MHC.Project.Repository.LabOrderRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Request.LabOrderRequest;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE  }, maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/lab")
@RestController
@SecurityRequirement(name = "mettlerHealth")
public class LabOrderController {

	private static final Logger logger = LoggerFactory.getLogger(LabOrderController.class);


	@Autowired
	LabOrderRepository repository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	StaffRepository staffRepository;

	//To add lab order//
	@PostMapping("/register")
	public ResponseEntity<?> savelaborder(@Valid @RequestBody LabOrder laborder) {
		String patientId = laborder.getPatientId();
		String staffId = laborder.getEnteredBy();
        Patient patient = patientRepository.findById(patientId).orElse(null);
        Staff staff = staffRepository.findById(staffId).orElse(null);
        // Check if the patient exists
     		if (patient == null) {
     			logger.error("Error:Laborder given PatientId Could Not found");
     			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0097"));
     		}
     		if (staff == null) {
     			logger.error("Error:Laborder given StaffId Could Not Found");
     			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0098"));
     		}
		// Create new user's account
	    String uid = PatientService.generateUID(); // Generate UID
	    LabOrder builtlaborder=new LabOrder(uid,laborder.getPatientId(),laborder.getLabTestName(),laborder.getEnteredDate(),
				laborder.getEnteredType(),laborder.getEnteredBy(),laborder.getOrderedBy(),laborder.getCollectionSample(),
				laborder.getSpecimen(),laborder.getCollectionType(),laborder.getCollectionDateTime(),laborder.getStartDate(),
				laborder.getStopDate(),laborder.getUrgency(),laborder.getHowoften(),laborder.getHowlong(),laborder.getHospitalLocation(),
				laborder.getRespProvider(),laborder.getLastVisit(),laborder.getComments());
	    builtlaborder.setId(uid);
	    builtlaborder.setEnteredDate( builtlaborder.getEnteredDate());
	    builtlaborder.setStartDate( builtlaborder.getStartDate());
	    repository.save(builtlaborder);
	    DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 300");
	    DataResponse<LabOrder> signInResponse = new DataResponse<>(verifyResponse1, builtlaborder);
	    return ResponseEntity.ok().body(signInResponse);
	}


	// ****** To Get All Laborder details *****//
	@GetMapping("/get_all")
	public ResponseEntity<?> getAllLabOrder(){
	    List<LabOrder> builtlaborder = repository.findAll();
	    DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 301");
	    DataResponse<List<LabOrder>> signInResponse = new DataResponse<>(verifyResponse1, builtlaborder);
	    return ResponseEntity.ok().body(signInResponse);
	}

	// ****** To Get All Laborder Particular Details*****//
	@GetMapping("/getlab/{id}")
	public ResponseEntity<?> getLabOrder(@PathVariable String id) {
	        Optional<LabOrder> laborderOptional = repository.findById(id);
	        if (laborderOptional.isPresent()) {
	            LabOrder builtlaborder = laborderOptional.get();
	            repository.save(builtlaborder);
	            DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 302");
	    	    DataResponse<LabOrder> signInResponse = new DataResponse<>(verifyResponse1, builtlaborder);
	    	    return ResponseEntity.ok().body(signInResponse);
			}else {
				logger.error("Error: Laborder Id could not found.");
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0066"));
			}
	}
	
	@GetMapping("/getlabPidAndId/{id}/{patientId}")
	public ResponseEntity<?> getLabOrderPidAndId(@PathVariable String id, @PathVariable String patientId) {
	        Optional<LabOrder> laborder = repository.findByIdAndPatientId(id,patientId);
	        if (!laborder.isEmpty()) {
	        	LabOrder laborder1 = laborder.get();
	            DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 302");
	    	    DataResponse<LabOrder> signInResponse = new DataResponse<>(verifyResponse1, laborder1);
	    	    return ResponseEntity.ok().body(signInResponse);
			}else {
				logger.error("Error: Laborder Id could not found.");
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0066"));
			}
	}

	//****** To update the laborder *****//
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateLabOrder(@PathVariable String id,
	        @RequestBody @Validated LabOrder labOrder) {
	    Optional<LabOrder> laborderOptional = repository.findById(id);
	    if (laborderOptional.isPresent()) {
	        LabOrder updatedlabOrder = laborderOptional.get();
	        updatedlabOrder.update(labOrder);
	        repository.save(updatedlabOrder);
	        DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 303");
		    DataResponse<LabOrder> signInResponse = new DataResponse<>(verifyResponse1, updatedlabOrder);
		    return ResponseEntity.ok().body(signInResponse);
	    } else {
	    	logger.error("Error: Laborder Id could not found.");
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0066"));
	    }
	}

	//****** To update the StopDate laborder *****//
	@PutMapping("/update/stopDate/{id}")
	public ResponseEntity<?> updateStopDate(@PathVariable String id,@RequestBody @Validated LabOrderRequest laborderRequest) {
		 Optional<LabOrder> laborderOptional = repository.findById(id);
		    if (laborderOptional.isPresent()) {
		        LabOrder laborder = laborderOptional.get();
		        laborder.setStopDate(laborderRequest.getStopdate());           //Update StopDate
		        laborder.update(laborder);
		        repository.save(laborder);
		        DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 304");
			    DataResponse<LabOrder> signInResponse = new DataResponse<>(verifyResponse1, laborder);
			    return ResponseEntity.ok().body(signInResponse);
		    } else {
		    	logger.error("Error: Laborder Id could not found.");
		        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0066"));
		    }
	 }

	//****** To update the StopDate laborder *****//
	@PutMapping("/update/collectionDate/{id}")
	public ResponseEntity<?> updateCollectionDate(@PathVariable String id,@RequestBody @Validated LabOrderRequest laborderRequest) {
		  Optional<LabOrder> laborderOptional = repository.findById(id);
		    if (laborderOptional.isPresent()) {
		        LabOrder laborder = laborderOptional.get();
		        laborder.setCollectionDateTime(laborderRequest.getCollectionDateTime());  // Update CollectionDateTime
		        laborder.update(laborder);
		        repository.save(laborder);
		        DataResponse.Response verifyResponse1 = DataResponse.createVerifyResponse("MHC - 305");
			    DataResponse<LabOrder> signInResponse = new DataResponse<>(verifyResponse1, laborder);
			    return ResponseEntity.ok().body(signInResponse);
		    } else {
		    	logger.error("Error: Laborder Id could not found.");
		        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0066"));
		    }
	 }

	//****** To delete a lab order by ID *****//
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteLabOrder(@PathVariable String id) {
		  if (repository.existsByid(id)) {
		        repository.deleteById(id);
		        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 272"));
		    } else {
		    	logger.error("Error: Laborder Id could not found.");
		        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0066"));
		    }
	}
}