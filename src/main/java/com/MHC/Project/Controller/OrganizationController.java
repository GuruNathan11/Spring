//
//
//package com.MHC.Project.Controller;
//
//
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.MHC.Project.Model.Organization;
//import com.MHC.Project.Model.Organization.HIPAAPrivacyOfficer;
//import com.MHC.Project.Model.Organization.HIPAASecurityOfficer;
//import com.MHC.Project.Model.Organization.PointOfContact;
//import com.MHC.Project.Repository.OrganizationRepository;
//import com.MHC.Project.Response.OrganizationResponse;
//import com.MHC.Project.Response.VerifyResponseFactory;
//import com.MHC.Project.Service.OrganizationService;
//
//@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST }, maxAge = 3600, allowedHeaders = "*")
//@RestController
//@RequestMapping("/api/org")
//public class OrganizationController {
//
//	@Autowired
//	private OrganizationService organizationService;
//	
//	@Autowired
//    private OrganizationRepository repository;
//
//	@GetMapping
//	public String Welcome() {
//	return ("Welcome to Organization Registration...");	
//	}
//	
//	@PostMapping("/register")
//	public ResponseEntity<?> saveOrganization(@RequestBody @Validated Organization organizationRequest) {
//	    try {
//	        Organization savedOrganization = organizationService.saveOrganization(organizationRequest);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganization);
//	    } catch (IllegalArgumentException e) {
//	    	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0059"));
//	    }
//	}
//
//	//***************Get All organization***********************//
//	@GetMapping("/getAll")
//	public ResponseEntity<List<Organization>> getAllOrganization() {
//	    List<Organization> organizationList = repository.findAll();
//	    return ResponseEntity.ok(organizationList);
//	}
//
//
//	//*************** Get the Specific Organization By Id*************//
//	@GetMapping("/getById/{id}")
//	public ResponseEntity<?> getOrganization(@PathVariable String id) {
//	   Optional<Organization> organization = repository.findById(id);
//	        if (organization.isPresent()) {
//	            return ResponseEntity.ok(organization.get());
//	        } else {
//	        	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0060"));
//	        }
//	    } 
//	
//     //**************To update Organization**************************//
//    @PutMapping("/update/{id}")
//	public ResponseEntity<?> updateOrganization(@PathVariable String id, @RequestBody @Validated Organization organizationRequest) {
//	   Optional<Organization> organizationOptional = repository.findById(id);
//	        if (organizationOptional.isPresent()) {
//	            Organization organization = organizationOptional.get();
//	            List<Organization.OrganizationDetails> organizationDetailsList = organizationRequest.getOrganizationdetails();
//	            List<Organization.contact> contact = organizationRequest.getContact();
//	            String email = organizationRequest.getEmail();
//	            String webUrl = organizationRequest.getWebsiteUrl();
//	            List<PointOfContact> pContact = organizationRequest.getPointofcontact();
//	            List<HIPAAPrivacyOfficer> HPO = organizationRequest.getHippaprivacyofficer();
//	            List<HIPAASecurityOfficer> HSO = organizationRequest.getHippassecurityofficer();
//
//	            organization.setOrganizationdetails(organizationDetailsList);
//	            organization.setContact(contact);
//	            organization.setEmail(email);
//	            organization.setWebsiteUrl(webUrl);
//	            organization.setPointofcontact(pContact);
//	            organization.setHippaprivacyofficer(HPO);
//	            organization.setHippassecurityofficer(HSO);
//
//	            Organization updatedOrganization = repository.save(organization);
//	            return ResponseEntity.ok(updatedOrganization);
//	        } else {
//	        	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
//	        }
//	    
//	}
//
//	//*************** Delete the Specific Organization By Id*************\\
//    @DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteOrganization(@PathVariable String id) {
//	        if (!repository.existsById(id)) {
//	        	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0062"));
//	        }
//	        repository.deleteById(id);
//	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0213"));
//	  } 
    package com.MHC.Project.Controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Organization.HIPAAPrivacyOfficer;
import com.MHC.Project.Model.Organization.HIPAASecurityOfficer;
import com.MHC.Project.Model.Organization.OrganizationDetails;
import com.MHC.Project.Model.Organization.PointOfContact;
import com.MHC.Project.Model.Organization.SHIFT;
import com.MHC.Project.Model.Organization.contact;
import com.MHC.Project.Model.User;
import com.MHC.Project.Model.down;
import com.MHC.Project.Repository.DropdownRepository;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.UserRepository;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.OrganizationResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.OrganizationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/org")
@SecurityRequirement(name = "mettlerHealth")
public class OrganizationController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
    private OrganizationRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DropdownRepository dropdownRepository;
	
	public OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }
	
	@PostMapping("/register")
	public ResponseEntity<?> saveOrganization(@RequestBody @Validated Organization organizationRequest) {
		 String email = organizationRequest.getEmail();
		 User userEmail = userRepository.findByEmail(email);
		    if (repository.existsByEmail(email)) {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0024"));
	    }
		    if (userEmail != null) {
		        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0024"));
		}
	    try {
	        Organization savedOrganization = organizationService.saveOrganization(organizationRequest);
		    DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0223");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, savedOrganization);
	        return ResponseEntity.ok()
		              .header(HttpHeaders.SET_COOKIE.toString())
		              .body(dataResponse);
	    } catch (IllegalArgumentException e) {
	    	logger.error("Organization Name Already Exists....");
	    	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0060"));
	    }
	}

	//***************Get All organization***********************//
	
//	@GetMapping("/getAll")
//	public ResponseEntity<?> getAllOrganization(@RequestParam(defaultValue = "0") int page,
//												@RequestParam(defaultValue = "10") int pageSize) {
//		
//		Pageable pageable = PageRequest.of(page, pageSize);
//		Page<Organization> organizationList = repository.findAll(pageable);
//	
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0224");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, organizationList);
//		return ResponseEntity.ok(dataResponse);
//	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllOrganization(@RequestParam(defaultValue = "0") int page,
	                                            @RequestParam(defaultValue = "10") int pageSize) {
	    if (page == -1) {
	        // Retrieve all organizations without pagination
	        List<Organization> organizations = repository.findAll(Sort.by("createdAt").descending());

	        // Additional logic to handle updates and remove duplicates
	        Map<String, Organization> uniqueOrganizations = new LinkedHashMap<>();

	        for (Organization organization : organizations) {
	            if (uniqueOrganizations.containsKey(organization.getId())) {
	                // If the organization is already present, update its fields
	                Organization existingOrganization = uniqueOrganizations.get(organization.getId());

	                LocalDateTime existingCreatedAt = LocalDateTime.parse(existingOrganization.getCreatedAt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	                LocalDateTime currentCreatedAt = LocalDateTime.parse(organization.getCreatedAt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	                if (currentCreatedAt.isAfter(existingCreatedAt)) {
	                    // Update if the createdAt field indicates a more recent change
	                    uniqueOrganizations.put(organization.getId(), organization);
	                }
	            } else {
	                // If not present, add to the map
	                uniqueOrganizations.put(organization.getId(), organization);
	            }
	        }

	        // Convert the map back to a list
	        List<Organization> sortedAndUniqueOrganizations = new ArrayList<>(uniqueOrganizations.values());

	        Map<String, Object> responseData = new LinkedHashMap<>();
	        responseData.put("content", sortedAndUniqueOrganizations);
	        responseData.put("totalElements", sortedAndUniqueOrganizations.size());

	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0224");
	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, responseData);
	        return ResponseEntity.ok(dataResponse);
	    } else {
	        // Pagination logic
	        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
	        Page<Organization> organizationPage = repository.findAll(pageable);

	        // Additional logic to handle updates and remove duplicates
	        List<Organization> organizations = organizationPage.getContent();
	        Map<String, Organization> uniqueOrganizations = new LinkedHashMap<>();

	        for (Organization organization : organizations) {
	            if (uniqueOrganizations.containsKey(organization.getId())) {
	                // If the organization is already present, update its fields
	                Organization existingOrganization = uniqueOrganizations.get(organization.getId());

	                LocalDateTime existingCreatedAt = LocalDateTime.parse(existingOrganization.getCreatedAt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	                LocalDateTime currentCreatedAt = LocalDateTime.parse(organization.getCreatedAt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	                if (currentCreatedAt.isAfter(existingCreatedAt)) {
	                    // Update if the createdAt field indicates a more recent change
	                    uniqueOrganizations.put(organization.getId(), organization);
	                }
	            } else {
	                // If not present, add to the map
	                uniqueOrganizations.put(organization.getId(), organization);
	            }
	        }

	        // Convert the map back to a list
	        List<Organization> sortedAndUniqueOrganizations = new ArrayList<>(uniqueOrganizations.values());

	        Map<String, Object> responseData = new LinkedHashMap<>();
	        responseData.put("content", sortedAndUniqueOrganizations);
	        responseData.put("totalElements", organizationPage.getTotalElements());

	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0224");
	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, responseData);
	        return ResponseEntity.ok(dataResponse);
	    }
	}
	
//	@GetMapping("/getAll")
//	public ResponseEntity<?> getAllOrganization(@RequestParam(defaultValue = "0") int page,
//												@RequestParam(defaultValue = "10") int pageSize) {
//
//		Pageable pageable = PageRequest.of(page, pageSize);
//		Page<Organization> organizationList = repository.findAll(pageable);
//
//		for (Organization org : organizationList) {
//
//			List<contact> contactList = org.getContact();
//			List<OrganizationDetails> orgDetailsList = org.getOrganizationdetails();
//
//			if (contactList != null) {
//
//				contact contact = contactList.get(0);
//
//				String stateId = contact.getState();
//				String countryId = contact.getCountry();
//
//				List<down> downList = dropdownRepository.findAll();
//				for (down d : downList) {
//					com.MHC.Project.Model.down.List[] list = d.getList();
//					for (com.MHC.Project.Model.down.List item : list) {
//
//						if (d.getDropdown().equalsIgnoreCase("state")) {
//							if (item.getId().equals(stateId)) {
//								contact.setState(item.getValue());
//							}
//						}
//
//						if (d.getDropdown().equalsIgnoreCase("country")) {
//							if (item.getId().equals(countryId)) {
//								contact.setCountry(item.getValue());
//							}
//						}
//
//					if (orgDetailsList != null) {
//						OrganizationDetails orgDetails = orgDetailsList.get(0);
//						String organizationTypeId = orgDetails.getType();
//
//						if (d.getDropdown().equalsIgnoreCase("OrganizationType")) {
//							if (item.getId().equals(organizationTypeId)) {
//								orgDetails.setType(item.getValue());
//							}
//						}
//					}
//
//					}
//				}
//			}
//		}
//
//		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0224");
//		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, organizationList);
//		return ResponseEntity.ok(dataResponse);
//	}

	//*************** Get the Specific Organization By Id*************//
//	@GetMapping("/getById/{id}")
//	public ResponseEntity<?> getOrganization(@PathVariable String id) {
//	   Optional<Organization> organization = repository.findById(id);
//	        if (organization.isPresent()) {
//	        	DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0225");
//			    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, organization);
//	            return ResponseEntity.ok(dataResponse);
//	        } else {
//	        	logger.error("Organization Id not found...");
//	        	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
//	        }
//	    } 
	
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getOrganization(@PathVariable String id) {
		Optional<Organization> organizationOptional = repository.findById(id);
		if (organizationOptional.isPresent()) {
			Organization organization = organizationOptional.get();

			List<contact> contactList = organization.getContact();
			List<OrganizationDetails> orgDetailsList = organization.getOrganizationdetails();

			if (contactList != null) {

				contact contact = contactList.get(0);

				String stateId = contact.getState();
				String countryId = contact.getCountry();

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {
						
						if (d.getDropdown().equalsIgnoreCase("state")) {
							if (item.getId().equals(stateId)) {
								contact.setState(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("country")) {
							if (item.getId().equals(countryId)) {
								contact.setCountry(item.getValue());
							}
						}
						
					if (orgDetailsList != null) {
						OrganizationDetails orgDetails = orgDetailsList.get(0);
						String organizationTypeId = orgDetails.getType();
						
						if (d.getDropdown().equalsIgnoreCase("OrganizationType")) {
							if (item.getId().equals(organizationTypeId)) {
								orgDetails.setType(item.getValue());
							}
						}
					}	
						

					}
				}
			}

			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0225");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, organization);
			return ResponseEntity.ok(dataResponse);
		} else {
			logger.error("Organization Id not found...");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
		}
	}
	
     //**************To update Organization**************************//
    @PutMapping("/update/{id}")
	public ResponseEntity<?> updateOrganization(@PathVariable String id, @RequestBody @Validated Organization organizationRequest) {
	   Optional<Organization> organizationOptional = repository.findById(id);
	        if (organizationOptional.isPresent()) {
	            Organization organization = organizationOptional.get();
	            List<Organization.OrganizationDetails> organizationDetailsList = organizationRequest.getOrganizationdetails();
	            List<Organization.contact> contact = organizationRequest.getContact();
	            String email = organizationRequest.getEmail();
	            String webUrl = organizationRequest.getWebsiteUrl();
	            List<PointOfContact> pContact = organizationRequest.getPointofcontact();
	            List<HIPAAPrivacyOfficer> HPO = organizationRequest.getHippaprivacyofficer();
	            List<HIPAASecurityOfficer> HSO = organizationRequest.getHippassecurityofficer();
	            SHIFT shift1 = organizationRequest.getShift();
	            
	            LocalDateTime updateDate = LocalDateTime.now();
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    		String updatedAt = updateDate.format(formatter);

	            organization.setOrganizationdetails(organizationDetailsList);
	            organization.setContact(contact);
	            organization.setEmail(email);
	            organization.setMobileNumber(organizationRequest.getMobileNumber());
	            organization.setWebsiteUrl(webUrl);
	            organization.setPointofcontact(pContact);
	            organization.setHippaprivacyofficer(HPO);
	            organization.setHippassecurityofficer(HSO);
	            organization.setCreatedAt(organization.getCreatedAt());
	            organization.setUpdatedAt(updatedAt);
	            organization.setShift(shift1);
	            organization.setUserType(organizationRequest.getUserType());
	            organization.setProximityVerification(organizationRequest.getProximityVerification());
	            organization.setGeofencing(organizationRequest.getGeofencing());
	            organization.setQ15Access(organizationRequest.getQ15Access());
	            organization.setProfile(organizationRequest.getProfile());

	            Organization updatedOrganization = repository.save(organization);
	            DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0226");
			    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, updatedOrganization);
	            return ResponseEntity.ok(dataResponse);
                } else {
	        	logger.error("Organization Id not found...");
	        	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
	        }	    
	}

	//*************** Delete the Specific Organization By Id*************\\
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteOrganization(@PathVariable String id) {
	        if (!repository.existsById(id)) {
	          	logger.error("The given Organization Id " + id +" does not Exists.");
	        	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
	        }
	        repository.deleteById(id);
	        Optional<Organization> org = repository.findById(id);
	        String email = org.get().getEmail();
        	userRepository.deleteByEmail(email);
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0213"));
	  } 

    @GetMapping("/name")
    public ResponseEntity<?> getAllOrganizationNames() {
        List<OrganizationResponse> response = organizationService.getAllOrganizations();
        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0227");
	    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, response);
        return ResponseEntity.ok(dataResponse);

    }
}
