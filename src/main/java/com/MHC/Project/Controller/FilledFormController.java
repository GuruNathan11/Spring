package com.MHC.Project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.MHC.Project.Model.filledForm;
import com.MHC.Project.Repository.FilledFormRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/filledForm")
@SecurityRequirement(name = "mettlerHealth")
public class FilledFormController {
	
	@Autowired
	FilledFormRepository repository;

	@PostMapping("/add")
	public ResponseEntity<?> addFilledForm(@RequestBody filledForm filledForm){
		String id = filledForm.getId();
		Optional<filledForm> filledForm1 = repository.findById(id);
		if(filledForm1.isPresent()) {
			filledForm existingForm = filledForm1.get();
			existingForm.setId(filledForm.getId());
			existingForm.setPid(filledForm.getPid());
			existingForm.setForm(filledForm.getForm());
			repository.save(existingForm);
			DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0270");
	        DataResponse<?> dataResponse = new DataResponse<>(response, existingForm);
	        return ResponseEntity.ok(dataResponse);
		}
		filledForm.setId(PatientService.generateUID());
		repository.save(filledForm);
		DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
        DataResponse<?> dataResponse = new DataResponse<>(response, filledForm);
        return ResponseEntity.ok(dataResponse);
	}
	
	@GetMapping("/getByPid/{pid}")
	public ResponseEntity<?> getFormByPid(@RequestParam String pid){
		List<filledForm> forms = repository.findByPid(pid);
		
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, forms);
		return ResponseEntity.ok(dataResponse);
	}
	
	@DeleteMapping("/delete/{Pid}/{form}")
	public ResponseEntity<?> deleteFilledForm(@PathVariable("Pid") String pid, @PathVariable("form") String form) {
		Optional<filledForm> pidForm = repository.findByPidAndForm_Name(pid, form);
		if(pidForm.isPresent()) {
		String pidFormId = pidForm.get().getId();
 		repository.deleteById(pidFormId);
 		return ResponseEntity.ok(DataResponse.createVerifyResponse("MHC - 292"));
		}else {
			return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0048"));
		}
	}
}
