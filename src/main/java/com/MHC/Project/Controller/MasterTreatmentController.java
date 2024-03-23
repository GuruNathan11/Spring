package com.MHC.Project.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

import com.MHC.Project.Model.MasterTreatment;
import com.MHC.Project.Model.MasterTreatment.MedicalPractitioner;
import com.MHC.Project.Model.MasterTreatment.MedicalProblem;
import com.MHC.Project.Model.MasterTreatment.Nursing;
import com.MHC.Project.Model.MasterTreatment.PatientStrengthAssets;
import com.MHC.Project.Model.MasterTreatment.RecreationalTherapist;
import com.MHC.Project.Model.MasterTreatment.SocialWorkRecorder;
import com.MHC.Project.Model.MasterTreatment.TreatmentTeamMemberSignature;
import com.MHC.Project.Repository.MasterTreatmentRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/masterTreatment")
@SecurityRequirement(name = "mettlerHealth")
public class MasterTreatmentController {
	
	@Autowired
	MasterTreatmentRepository repository;

//	@PostMapping
//	public ResponseEntity<?> postMasterTreatment(@RequestBody MasterTreatment masterTreatment) {
//	    String id = masterTreatment.getId();
//	    Optional<MasterTreatment> existingTreatment = repository.findById(id);
//
//	    LocalDateTime currentDateTime = LocalDateTime.now();
//	    String formattedDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(currentDateTime);
//
//	    if (existingTreatment.isPresent()) {
//	        // Update the existing record
//	        MasterTreatment existingRecord = existingTreatment.get();
//	        // Update the fields as needed
//	        existingRecord.setLastVisit(masterTreatment.getLastVisit());
//	        Optional<MasterTreatment> isrecreation = repository.findByRecreationalTherapist_Id(masterTreatment.getRecreationalTherapist().getId());
//	        if(isrecreation.isPresent()) {
//	        existingRecord.setRecreationalTherapist(masterTreatment.getRecreationalTherapist());
//	        String idt = masterTreatment.getRecreationalTherapist().getId();
//        	existingRecord.getRecreationalTherapist().setId(idt);
//        	existingRecord.getRecreationalTherapist().setUpdatedAt(formattedDateTime);
//	        }
//	        existingRecord.setSocialWorkRecorder(masterTreatment.getSocialWorkRecorder());
//	        existingRecord.setNursing(masterTreatment.getNursing());
//	        existingRecord.setMedicalPractitioner(masterTreatment.getMedicalPractitioner());
//	        existingRecord.setParticipatingTreatmentTeamMemberSignatures(masterTreatment.getParticipatingTreatmentTeamMemberSignatures());
//
//	        MasterTreatment master = repository.save(existingRecord);
//	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
//	        DataResponse<?> dataResponse = new DataResponse<>(response, master);
//	        return ResponseEntity.ok(dataResponse);
//
//	    } else {
//	        // Save the new record
//	        masterTreatment.setId(PatientService.generateUID());
//	        // Set createdAt field
//	        Optional<RecreationalTherapist> isrecreation = Optional.ofNullable(masterTreatment.getRecreationalTherapist());
//	        if(isrecreation.isPresent()) {
//	        	isrecreation.get().setId(PatientService.generateUID());
//	        	isrecreation.get().setCreatedAt(formattedDateTime);
//	        }
//	        Optional<SocialWorkRecorder> social = Optional.ofNullable(masterTreatment.getSocialWorkRecorder());
//	        if(social.isPresent()) {
//	        	social.get().setId(PatientService.generateUID());
//	        	social.get().setCreatedAt(formattedDateTime);
//	        }
//	        Optional<Nursing> nursing = Optional.ofNullable(masterTreatment.getNursing());
//	        if(nursing.isPresent()) {
//	        	nursing.get().setId(PatientService.generateUID());
//	        	nursing.get().setCreatedAt(formattedDateTime);
//	        }
//	        Optional<MedicalPractitioner> medicalPrac = Optional.ofNullable(masterTreatment.getMedicalPractitioner());
//	        if(medicalPrac.isPresent()) {
//	        	medicalPrac.get().setId(PatientService.generateUID());
//	        	medicalPrac.get().setCreatedAt(formattedDateTime);
//	        }
//	        Optional<List<TreatmentTeamMemberSignature>> treatmentteam = Optional.ofNullable(masterTreatment.getParticipatingTreatmentTeamMemberSignatures());
//	        if (treatmentteam.isPresent()) {
//	            List<TreatmentTeamMemberSignature> signatures = treatmentteam.get();
//	            for (TreatmentTeamMemberSignature signature : signatures) {
//	                signature.setId(PatientService.generateUID());
//	                signature.setCreatedAt(formattedDateTime);
//	            }
//	        }
//
//	        
//	        MasterTreatment master = repository.save(masterTreatment);
//	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
//	        DataResponse<?> dataResponse = new DataResponse<>(response, master);
//	        return ResponseEntity.ok(dataResponse);
//	    }
//	}

	@PostMapping("/add")
	public ResponseEntity<?> postMasterTreatment1(@RequestBody MasterTreatment masterTreatment) {
	    String id = masterTreatment.getId();
	    Optional<MasterTreatment> existingTreatment = repository.findById(id);
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    String formattedDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(currentDateTime);
	    if(existingTreatment.isEmpty()) {
	        // Save the new record
	        masterTreatment.setId(PatientService.generateUID());
	        // Set createdAt field
	        Optional<RecreationalTherapist> isrecreation = Optional.ofNullable(masterTreatment.getRecreationalTherapist());
	        if(isrecreation.isPresent()) {
	        	isrecreation.get().setId(PatientService.generateUID());
	        	isrecreation.get().setCreatedAt(formattedDateTime);
	        }
	        Optional<SocialWorkRecorder> social = Optional.ofNullable(masterTreatment.getSocialWorkRecorder());
	        if(social.isPresent()) {
	        	social.get().setId(PatientService.generateUID());
	        	social.get().setCreatedAt(formattedDateTime);
	        	
	        	List<PatientStrengthAssets> patientStrength = social.get().getPatientStrengthAssets();
	        	for (PatientStrengthAssets p : patientStrength) {
	        		p.setId(PatientService.generateUID());
	        	}
	        }
	        Optional<Nursing> nursing = Optional.ofNullable(masterTreatment.getNursing());
	        if(nursing.isPresent()) {
	        	nursing.get().setId(PatientService.generateUID());
	        	nursing.get().setCreatedAt(formattedDateTime);
	        }
	        Optional<MedicalPractitioner> medicalPrac = Optional.ofNullable(masterTreatment.getMedicalPractitioner());
	        if(medicalPrac.isPresent()) {
	        	medicalPrac.get().setId(PatientService.generateUID());
	        	medicalPrac.get().setCreatedAt(formattedDateTime);
	        	
	        	List<MedicalProblem> medical = medicalPrac.get().getMedicalProblems();
	        	for (MedicalProblem m : medical) {
	        		m.setId(PatientService.generateUID());
	        	}
	        }
	        Optional<List<TreatmentTeamMemberSignature>> treatmentteam = Optional.ofNullable(masterTreatment.getParticipatingTreatmentTeamMemberSignatures());
	        if (treatmentteam.isPresent()) {
	            List<TreatmentTeamMemberSignature> signatures = treatmentteam.get();
	            for (TreatmentTeamMemberSignature signature : signatures) {
	                signature.setId(PatientService.generateUID());
	                signature.setCreatedAt(formattedDateTime);
	            }
	        }

	        
	        MasterTreatment master = repository.save(masterTreatment);
	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
	        DataResponse<?> dataResponse = new DataResponse<>(response, master);
	        return ResponseEntity.ok(dataResponse);
	    }else if(existingTreatment.isPresent()) {
	    	
	    	masterTreatment.setId(id);
	        // Set createdAt field
	        Optional<MasterTreatment> isrecreation = repository.findByRecreationalTherapist_Id(masterTreatment.getRecreationalTherapist().getId());
	        if(isrecreation.isPresent()) {
	        	isrecreation.get().getRecreationalTherapist().setUpdatedAt(formattedDateTime);
	        }
	        Optional<SocialWorkRecorder> social = Optional.ofNullable(masterTreatment.getSocialWorkRecorder());
	        if(social.isPresent()) {
	        	social.get().setUpdatedAt(formattedDateTime);
	        	
	        	List<PatientStrengthAssets> patientStrength = social.get().getPatientStrengthAssets();
	        	for (PatientStrengthAssets p : patientStrength) {
	        		p.setId(PatientService.generateUID());
	        	}
	        }
	        Optional<Nursing> nursing = Optional.ofNullable(masterTreatment.getNursing());
	        if(nursing.isPresent()) {
	        	nursing.get().setUpdatedAt(formattedDateTime);
	        }
	        Optional<MedicalPractitioner> medicalPrac = Optional.ofNullable(masterTreatment.getMedicalPractitioner());
	        if(medicalPrac.isPresent()) {
	        	medicalPrac.get().setUpdatedAt(formattedDateTime);
	        	
	        	List<MedicalProblem> medical = medicalPrac.get().getMedicalProblems();
	        	for (MedicalProblem m : medical) {
	        		m.setId(PatientService.generateUID());
	        	}
	        }
	        Optional<List<TreatmentTeamMemberSignature>> treatmentteam = Optional.ofNullable(masterTreatment.getParticipatingTreatmentTeamMemberSignatures());
	        if (treatmentteam.isPresent()) {
	            List<TreatmentTeamMemberSignature> signatures = treatmentteam.get();
	            for (TreatmentTeamMemberSignature signature : signatures) {
	                signature.setUpdatedAt(formattedDateTime);
	            }
	        }

	        
	        MasterTreatment master = repository.save(masterTreatment);
	        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0270");
	        DataResponse<?> dataResponse = new DataResponse<>(response, master);
	        return ResponseEntity.ok(dataResponse);
	    }
		return ResponseEntity.ok("Error: something went wrong..");
	}

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllMasterTreatments() {
    	
        List<MasterTreatment> master = repository.findAll();
        
        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(response, master);
        return ResponseEntity.ok(dataResponse);
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
    	
        Optional<MasterTreatment> master = repository.findById(id);
        
        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(response, master);
        return ResponseEntity.ok(dataResponse);
    }
    
    @GetMapping("/getByPid/{pid}")
    public ResponseEntity<?> getBypid(@PathVariable String pid) {
    	
        List<MasterTreatment> master = repository.findByPid(pid);
        
        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(response, master);
        return ResponseEntity.ok(dataResponse);
    }
    
    @GetMapping("/getByPidAndVisitId/{pid}/{visitId}")
    public ResponseEntity<?> getBypidAndVisitId(@PathVariable String pid,@PathVariable String visitId) {
    	
        List<MasterTreatment> master = repository.findByPidAndLastVisit(pid,visitId);
        
        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(response, master);
        return ResponseEntity.ok(dataResponse);
    }
}
