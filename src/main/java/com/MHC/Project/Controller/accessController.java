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
import org.springframework.web.bind.annotation.RestController;

import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.accessControl;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.accessRepo;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/access")
@SecurityRequirement(name = "mettlerHealth")
public class accessController {

	@Autowired
	private accessRepo repo;
	
	@Autowired
	private OrganizationRepository orgRepo;
	
	@PostMapping("register")
	public ResponseEntity<?> saveAccess(@RequestBody accessControl access){
		
		Optional<accessControl> accessId = repo.findById(access.getId());
		if (accessId.isPresent()) {
			accessControl accessClass = accessId.get();
			accessClass.update(access);
			repo.save(accessClass);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, accessClass);
			return ResponseEntity.ok().body(dataResponse); 
		}
		
		String uid = PatientService.generateUID(); 
		String org = access.getOrgName();
		accessControl accesses = new accessControl(uid, access.getOrgName(), access.getQ15(), access.getPv(),
				access.getGf(), access.getAllAccess());
		Organization orgName1 = orgRepo.findById(org).orElse(null);
		Optional<accessControl> organization = repo.findByOrgName(org);

		if (orgName1 == null) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0052"));
		} 
		if (organization.isPresent()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0053"));
		}
		
		repo.save(accesses);

		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 309");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, accesses);
		return ResponseEntity.ok().body(dataResponse);
	}
	
//	@GetMapping("getAll")
//	public ResponseEntity<?> getAllAcces() {
//		List<accessControl> get = repo.findAll();
//		 DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 306");
//		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
//	        return ResponseEntity.ok(dataResponse);
//		}
//	
//	@GetMapping("getByOrg/{orgName}")
//	public ResponseEntity<?> getAccess(@PathVariable String orgName){
//		Optional<accessControl> access = repo.findByOrgName(orgName);
//		if(access.isPresent()) {
//			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0225");
//		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, access);
//            return ResponseEntity.ok(dataResponse);
//        } else {
////        	logger.error("Organization Id not found...");
//        	return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
//        }
//    } 
		
//			accessControl access1 = access.get();
//			return ResponseEntity.ok().body(access1);
//		}
//		return ResponseEntity.ok().body("Given Org name does not find...");
//	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAllAcces() {
		
		List<accessControl> get = repo.findAll();
		
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 306");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, get);
		return ResponseEntity.ok(dataResponse);
	}

	@GetMapping("/ById/{id}")
	public ResponseEntity<?> getaccessControlById(@PathVariable String id) {
		
		Optional<accessControl> accessOptional = repo.findById(id);
		if (accessOptional.isPresent()) {
			accessControl access = accessOptional.get();
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 307");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, access);
			return ResponseEntity.ok().body(dataResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}
	
	@GetMapping("getByOrg/{orgName}")
	public ResponseEntity<?> getAccess(@PathVariable String orgName) {
		Optional<accessControl> access = repo.findByOrgName(orgName);
		if (access.isPresent()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 308");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, access);
			return ResponseEntity.ok().body(dataResponse);
		} else {
//        	logger.error("Organization Id not found...");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0061"));
		}
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteAccessById(@PathVariable String id) {
		
		Optional<accessControl> accessOptional = repo.findById(id);
		if (accessOptional.isPresent()) {
			accessControl accessClass = accessOptional.get();
			
			repo.delete(accessClass);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0078"));
	}

}
