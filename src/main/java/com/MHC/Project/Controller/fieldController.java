package com.MHC.Project.Controller;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

import com.MHC.Project.Error.VerifyResponse;
import com.MHC.Project.Model.field;
import com.MHC.Project.Model.form;
import com.MHC.Project.Repository.fieldRepository;
import com.MHC.Project.Repository.formRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
 
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/field")
@SecurityRequirement(name = "mettlerHealth")
public class fieldController {
 
	@Autowired
	private final fieldRepository fieldRepository;
 
	
	public fieldController(fieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}
 
	@Autowired
	formRepository formRepository;

	@PostMapping("add")
	public ResponseEntity<?> createField(@RequestBody field field) {
		// Check if the field already exists
//		boolean fieldExists = fieldRepository.existsByFieldLabelAndFormName(field.getFieldLabel(), field.getFormName());

//		if (fieldExists) {
//			// Return an error response indicating that the field already exists
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0086"));
//		}

		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);
		
		field.setId(formController.generateId());
		field.setCreatedAt(createdAt);

		// Check if the form with the specified name exists
		form form = formRepository.findByName(field.getFormName());

		if (form != null) {
			// Add the field data to the form's fields
			form.getFields().add(field);

			// Save the updated form
			formRepository.save(form);
		}

		field createdField = fieldRepository.save(field);

		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, createdField);
		return ResponseEntity.ok(dataResponse);
	}
 
	@GetMapping("get")
	public ResponseEntity<?> getAllfields() {
		List<field> fields = fieldRepository.findAll();
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, fields);
		return ResponseEntity.ok(dataResponse);
	}
	
	@PutMapping("/updateField/{id}")
	public ResponseEntity<?> updateFieldById(@PathVariable String id,
											 @RequestBody field field) {
		
		Optional<field> fieldOptional = fieldRepository.findById(id);
		if (fieldOptional.isPresent()) {
			field fieldClass = fieldOptional.get();
			
			form form = formRepository.findByName(fieldClass.getFormName());
			if (form != null) {
				List fieldList  = form.getFields();
				
				for (Object object : fieldList) {
					
					if (object instanceof field) {
						field fieldFormUpdate = (com.MHC.Project.Model.field) object;
						
						if (fieldFormUpdate.getId().equals(id)) {
							
							fieldFormUpdate.update(field);
							break;
						}
					}
				}
				formRepository.save(form); 
			}
			
			fieldClass.update(field); 
			fieldRepository.save(fieldClass);   
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, fieldClass);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
 
	@DeleteMapping("/ById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {
	    Optional<field> fieldOptional = fieldRepository.findById(id);

	    if (fieldOptional.isPresent()) {
	        field field = fieldOptional.get();
	        String formName = field.getFormName();

	        fieldRepository.deleteById(id);
	        form form = formRepository.findByName(formName);

	        if (form != null) {
	            form.getFields().removeIf(f -> {
	                if (f instanceof com.MHC.Project.Model.field) {
	                    return ((com.MHC.Project.Model.field) f).getId().equals(id);
	                }
	                return false;
	            });
	            formRepository.save(form);
	            VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0209");
	            return ResponseEntity.ok().body(verifyResponse);
	        }
	    }

	    VerifyResponse verifyResponse = VerifyResponseFactory.createVerifyResponse("MHC - 0078");
	    return ResponseEntity.ok().body(verifyResponse);
	}
 
}