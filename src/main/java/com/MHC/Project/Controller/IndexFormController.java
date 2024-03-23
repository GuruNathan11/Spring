package com.MHC.Project.Controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Content;
import com.MHC.Project.Model.IndexForm;
import com.MHC.Project.Model.IntexContent;
import com.MHC.Project.Model.SubHeading;
import com.MHC.Project.Repository.IndexFormRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
		RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/indexForm")
@SecurityRequirement(name = "mettlerHealth")
public class IndexFormController {
	
	@Autowired
	IndexFormRepository repository;

	@PostMapping("/add")
	public ResponseEntity<?> addOrUpdateIndexForm(@RequestBody IndexForm indexForm) {
	    String indexFormId = indexForm.getId();
	    if (indexFormId == null || indexFormId.isEmpty()) {
	        // If the ID is not provided, generate a new one
	        indexForm.setId(PatientService.generateUID());
	    } else {
	        // If the ID is provided, check if an entity with the same ID already exists
	        Optional<IndexForm> existingIndexForm = repository.findById(indexFormId);
	        if (existingIndexForm.isPresent()) {
	            // If an entity with the same ID exists, update its fields
	            IndexForm storedIndexForm = existingIndexForm.get();
	            storedIndexForm.setContent(indexForm.getContent()); // Update other fields as needed
	            // Save the updated IndexForm to the repository
	            repository.save(storedIndexForm);
	            return ResponseEntity.ok(storedIndexForm);
	        } else {
	            // If no entity with the same ID exists, use the provided ID
	            indexForm.setId(indexFormId);
	        }
	    }

	    // Save the new or updated IndexForm to the repository
	    IndexForm savedIndexForm = repository.save(indexForm);
	    DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, savedIndexForm);
		return ResponseEntity.ok(dataResponse);
	}

	@PostMapping("/contents/{indexFormId}")
	public ResponseEntity<?> addOrUpdateContent(
	        @PathVariable String indexFormId,
	        @RequestBody Content content) {
	    Optional<IndexForm> optionalIndexForm = repository.findById(indexFormId);
	    
	    if (optionalIndexForm.isPresent()) {
	        IndexForm indexForm = optionalIndexForm.get();
	        
	        Content[] contents = indexForm.getContent();
	        
	        if (contents != null) {
	            for (Content existingContent : contents) {
	                if (existingContent.getId().equals(content.getId())) {
	                    // Update existing content
	                    existingContent.setHeading(content.getHeading());
	                    existingContent.setInstitutionName(content.getInstitutionName());
	                    existingContent.setPolicyTitle(content.getPolicyTitle());
	                    existingContent.setPolicyNumber(content.getPolicyNumber());
	                    existingContent.setEffectiveDate(content.getEffectiveDate());
	                    existingContent.setDepartment(content.getDepartment());
	                    // Update other fields as needed
	                    repository.save(indexForm); // Save the updated IndexForm to the repository
	                    DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
	        			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, indexForm);
	        			return ResponseEntity.ok(dataResponse);
	                }
	            }

	            // If content with the given ID is not found, add a new one
	            content.setId(PatientService.generateUID());
	            contents = Arrays.copyOf(contents, contents.length + 1);
	            contents[contents.length - 1] = content;
	            
	            indexForm.setContent(contents);
	            repository.save(indexForm); // Save the updated IndexForm to the repository
	            DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
				DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, indexForm);
				return ResponseEntity.ok(dataResponse);
	        } else {
	        	return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0102"));
	        }
	    } else {
	    	return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0105"));
	    }
	}



	    @PostMapping("/subheadings/{indexFormId}/{contentId}")
	    public ResponseEntity<?> addOrUpdateSubHeading(
	            @PathVariable String indexFormId,
	            @PathVariable String contentId,
	            @RequestBody SubHeading subHeading) {
	        Optional<IndexForm> optionalIndexForm = repository.findById(indexFormId);
	        
	        if (optionalIndexForm.isPresent()) {
	            IndexForm indexForm = optionalIndexForm.get();
	            Content[] contents = indexForm.getContent();
	            
	            if (contents != null) {
	                for (Content content : contents) {
	                    if (content.getId().equals(contentId)) {
	                        SubHeading[] subHeadings = content.getSubHeading();
	                        
	                        if (subHeadings == null) {
	                            subHeadings = new SubHeading[]{subHeading};
	                        } else {
	                            // Check if SubHeading with the same ID already exists, update it if found
	                            boolean found = false;
	                            for (SubHeading existingSubHeading : subHeadings) {
	                                if (existingSubHeading.getId().equals(subHeading.getId())) {
	                                    
	                                    existingSubHeading.setContent(subHeading.getContent());
	                                    existingSubHeading.setIndexContent(subHeading.getIndexContent());
//	                                    existingSubHeading.setIndexContent1(subHeading.getIndexContent1());
	                                    found = true;
	                                    break;
	                                }
	                            }
	                            if (!found) {
	                                // Add new SubHeading if not found
	                            	subHeading.setId(PatientService.generateUID());
	                                subHeadings = Arrays.copyOf(subHeadings, subHeadings.length + 1);
	                                subHeadings[subHeadings.length - 1] = subHeading;
	                            }
	                        }
	                        
	                        content.setSubHeading(subHeadings);
	                        repository.save(indexForm); // Save the updated IndexForm to the repository
	                        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
	            			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, indexForm);
	            			return ResponseEntity.ok(dataResponse);
	                    }
	                }
	                return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0104"));
	            }
	            return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0104"));
	        } else {
	        	return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0105"));
	        }
	    }

	    @PostMapping("/index-contents/{indexFormId}/{contentId}/{subHeadingId}")
	    public ResponseEntity<?> addOrUpdateIndexContent(
	            @PathVariable String indexFormId,
	            @PathVariable String contentId,
	            @PathVariable String subHeadingId,
	            @RequestBody IntexContent indexContent) {
	        Optional<IndexForm> optionalIndexForm = repository.findById(indexFormId);
	        
	        if (optionalIndexForm.isPresent()) {
	            IndexForm indexForm = optionalIndexForm.get();
	            Content[] contents = indexForm.getContent();
	            
	            if (contents != null) {
	                for (Content content : contents) {
	                    if (content.getId().equals(contentId)) {
	                        SubHeading[] subHeadings = content.getSubHeading();
	                        
	                        if (subHeadings != null) {
	                            for (SubHeading subHeading : subHeadings) {
	                                if (subHeading.getId().equals(subHeadingId)) {
	                                    IntexContent[] indexContents = subHeading.getIndexContent1();
	                                    
	                                    if (indexContents == null) {
	                                        indexContents = new IntexContent[]{indexContent};
	                                    } else {
	                                        // Check if IndexContent with the same ID already exists, update it if found
	                                        boolean found = false;
	                                        for (IntexContent existingIndexContent : indexContents) {
	                                            if (existingIndexContent.getId().equals(indexContent.getId())) {
	                                            	existingIndexContent.setIndexContent(indexContent.getIndexContent());
	                                                existingIndexContent.setContent(indexContent.getContent());
	                                                found = true;
	                                                break;
	                                            }
	                                        }
	                                        if (!found) {
	                                            // Add new IndexContent if not found
	                                        	indexContent.setId(PatientService.generateUID());
	                                            indexContents = Arrays.copyOf(indexContents, indexContents.length + 1);
	                                            indexContents[indexContents.length - 1] = indexContent;
	                                        }
	                                    }
	                                    
	                                    subHeading.setIndexContent1(indexContents);
	                                    repository.save(indexForm); // Save the updated IndexForm to the repository
	                                    DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
	                        			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, indexForm);
	                        			return ResponseEntity.ok(dataResponse);
	                                }
	                            }
	                            return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0103"));
	                        }
	                        return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0103"));
	                    }
	                }
	                return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0104"));
	            }
	            return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0104"));
	        } else {
//	            return new ResponseEntity<>("IndexForm not found", HttpStatus.NOT_FOUND);
	        	return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0105"));
	        }
	    }

	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllIndexForm(){
		List<IndexForm> indexForms = repository.findAll();
		DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(response, indexForms);
        return ResponseEntity.ok(dataResponse);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable String id){
		
		Optional<IndexForm> indexForm = repository.findById(id);
		if(indexForm.isPresent()) {
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, indexForm);
		return ResponseEntity.ok(dataResponse);
		}else {
			return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0048"));
		}
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id){
		
		Optional<IndexForm> indexForm = repository.findById(id);
		if(!indexForm.isPresent()) {
			return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0048"));
		}
		repository.deleteById(id);
		return ResponseEntity.ok(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
	}
}
