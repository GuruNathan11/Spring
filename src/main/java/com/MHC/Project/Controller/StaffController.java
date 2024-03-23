package com.MHC.Project.Controller;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.MHC.Project.Model.Staff;
import com.MHC.Project.Model.Staff.Address;
import com.MHC.Project.Model.Staff.Emergency;
import com.MHC.Project.Model.Staff.EmployeeDetails;
import com.MHC.Project.Model.Staff.Name;
import com.MHC.Project.Model.Staff.resource;
import com.MHC.Project.Model.User;
import com.MHC.Project.Model.down;
import com.MHC.Project.Repository.DropdownRepository;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.SessionRepository;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Repository.UserRepository;
import com.MHC.Project.Request.StaffRequest;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.StaffService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.internet.MimeMessage;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/staff")
@SecurityRequirement(name = "mettlerHealth")
public class StaffController {

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	DropdownRepository dropdownRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	// -------------------------------------SIGN-UP--------------------------------------------------------------------------------------------------------

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody StaffRequest staffRequest) {

		if (staffRepository.existsByUsername(staffRequest.getUsername())) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0023"));
		}

		if (staffRepository.existsByEmail(staffRequest.getEmail())) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0024"));
		}
		User userEmail = userRepository.findByEmail(staffRequest.getEmail());
		if (userEmail != null) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0024"));
		}

		// To validate ssn
		String ssn = staffRequest.getSsn();
		if (ssn.length() != 9) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0022"));
		}
		if (staffRepository.existsBySsn(staffRequest.getSsn())) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0008"));
		}

		// Create new user's account
		String uid = StaffService.generateUID(); // Generate UID

		resource resource = new resource();
		resource.setFullUrl("urn:uuid:" + uid);
		resource.setResourceType("STAFF");
		staffRequest.setActive("1"); // Set active status to true
		
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);

		Staff staff = new Staff(uid, List.of(resource), staffRequest.getName(), staffRequest.getGender(),
				staffRequest.getEmail(), staffRequest.getRole(), staffRequest.getUsername(),
				encoder.encode(staffRequest.getPassword()), staffRequest.getOrganization(),staffRequest.getStartDate(), staffRequest.getUserType(),
				staffRequest.getSpeciality(), staffRequest.getDateofBirth(), staffRequest.get_birthdate(),
				staffRequest.getSsn(), staffRequest.getNpi(), staffRequest.getContact(),
				staffRequest.getEmployeeDetails(), staffRequest.getEmergency(), staffRequest.getBackgroundCheck(),
				staffRequest.getImmunizationStatus(), staffRequest.getHipaaTraining(),
				staffRequest.getPrivacyAcknowledgement(), staffRequest.getSignature(), staffRequest.getActive(),
				staffRequest.getTerminationDate(), staffRequest.getTerminationReason());

		Optional<Organization> organizationOptional = organizationRepository.findById(staffRequest.getOrganization());
		if (organizationOptional.isEmpty()) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0025"));
		}	
//		String organizationName = organizationOptional.get().getOrganizationdetails().get(0).getName(); 
																										
		staff.setOrganization(organizationOptional.get().getId());
		staff.setCreatedAt(createdAt);

		Staff staff1=staffRepository.save(staff);

		User user = new User();
		user.setUsername(staffRequest.getUsername());
		user.setEmail(staffRequest.getEmail());
		user.setPassword(encoder.encode(staffRequest.getPassword()));
		user.setOrganization(organizationOptional.get().getId());
		user.setUserType(staffRequest.getUserType());
		user.setRole(staffRequest.getRole());

		userRepository.save(user);

		// Generate OTP
		String secretKey = Login.generateSecretKey();
		user.setSecretKey(secretKey);
		user.setSecretKeyExpiration(LocalDateTime.now().plusDays(60)); // Set OTP expiration to 60 days from now
		userRepository.save(user);

		// Send OTP to the user's email
		sendVerificationEmail(staffRequest.getEmail(), secretKey, staffRequest.getUsername(),
				staffRequest.getPassword(), staff);
		DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0271");
		DataResponse<Staff> signInResponse = new DataResponse<>(dataResponse, staff1);
		return ResponseEntity.ok().body(signInResponse);
	}

	private String sendVerificationEmail(String email, String secretKey, String username, String password,
			Staff staff) {
		try {
			// Initialize the Apache velocity engine
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init();

			String given = "";
			String family = "";
			String firstname = "";
			if (staff.getName() != null && !staff.getName().isEmpty()) {
				Name name = staff.getName().get(0); // Assuming only one name
				given = name.getGiven();
				family = name.getFamily();
				firstname = given + " " + family;
			}

			// Create the Velocity context and set the dynamic content
			VelocityContext context = new VelocityContext();
			context.put("FirstName", firstname);
			context.put("UserName", username);
			context.put("TempPass", password);
			context.put("SKey", secretKey);
			context.put("EmailSubject", "Access to Mettler Healthcare Software Application");

			// Load the Velocity template using an absolute path
			String templatePath = "src/main/resources/welcome_mail_template.vm";
			Template template = velocityEngine.getTemplate(templatePath);

			// Process the template with the dynamic content
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			String emailContent = writer.toString();

			// Send the email using JavaMailSender
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(email);
			helper.setSubject(context.get("EmailSubject").toString()); // Set the email subject
			helper.setText(emailContent, true);
			mailSender.send(message);

			// Return a success message or any other status indicator
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	@GetMapping("/staffName/{id}")
	public ResponseEntity<?> getStaffNameById(@PathVariable String id) {
		
		Optional<Staff> staffOptional = staffRepository.findById(id);
		if (staffOptional.isPresent()) {
			Staff staff = staffOptional.get();
			
			Name name = staff.getName().get(0);
			
			String givenName = name.getGiven();
			String familyName = name.getFamily();
			
			String names = givenName + " " + familyName; 
		
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 293");
			DataResponse<?> signInResponse = new DataResponse<>(dataResponse, names);
			return ResponseEntity.ok().body(signInResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0039"));
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<?> getAllStaff() {
		try {
			List<Staff> staffList = staffRepository.findAll();

			for (Staff staff : staffList) {

				if (staff != null) {

					Address address = staff.getContact() != null && staff.getContact().size() > 0
							&& staff.getContact().get(0).getAddress() != null
							&& staff.getContact().get(0).getAddress().size() > 0
									? staff.getContact().get(0).getAddress().get(0)
									: null;
					EmployeeDetails employee = staff.getEmployeeDetails() != null
							&& staff.getEmployeeDetails().size() > 0 ? staff.getEmployeeDetails().get(0) : null;
					Emergency emergency = staff.getEmergency() != null && staff.getEmergency().size() > 0
							? staff.getEmergency().get(0)
							: null;

					String genderId = staff.getGender();
					String roleId = staff.getRole();
					String hipaaTrainingId = staff.getHipaaTraining();
					String userTypeId = staff.getUserType();
					String[] specialityId = staff.getSpeciality();
					String[] backgroundCheckId = staff.getBackgroundCheck();

					List<down> downList = dropdownRepository.findAll();
					for (down d : downList) {
						com.MHC.Project.Model.down.List[] list = d.getList();
						for (com.MHC.Project.Model.down.List item : list) {

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									staff.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("roles")) {
								if (item.getId().equals(roleId)) {
									staff.setRole(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("hipaaTraining")) {
								if (item.getId().equals(hipaaTrainingId)) {
									staff.setHipaaTraining(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("user type")) {
								if (item.getId().equals(userTypeId)) {
									staff.setUserType(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("speciality")) {
								if (item.getId().equals(specialityId)) {
									String[] speciality = item.getValue().split("");
									staff.setSpeciality(speciality);
								}
							}

							if (d.getDropdown().equalsIgnoreCase("backgroundCheckInformation")) {
								if (item.getId().equals(backgroundCheckId)) {
									String[] backgroundcheckinformation = item.getValue().split("");
									staff.setBackgroundCheck(backgroundcheckinformation);
								}
							}

							if (address != null) {
								String stateId = address.getState();
								String countryId = address.getCountry();

								if (d.getDropdown().equalsIgnoreCase("state")) {
									if (item.getId().equals(stateId)) {
										address.setState(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("country")) {
									if (item.getId().equals(countryId)) {
										address.setCountry(item.getValue());
									}
								}
							}

							if (employee != null) {
								String qualificationId = employee.getQualification();
								String[] skillsId = employee.getSkills();

								if (d.getDropdown().equalsIgnoreCase("skillsandqualification")) {
									if (item.getId().equals(qualificationId)) {
										employee.setQualification(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("skillsAndqualification")) {
									if (item.getId().equals(skillsId)) {
										String[] skills = item.getValue().split("");
										employee.setSkills(skills);
									}
								}
							}

							if (emergency != null) {
								String relationshipId = emergency.getRelationShip();

								if (d.getDropdown().equalsIgnoreCase("relationship")) {
									if (item.getId().equals(relationshipId)) {
										emergency.setRelationShip(item.getValue());
									}
								}
							}

						}
					}
				}
			}

			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0259");
			DataResponse<List<Staff>> signInResponse = new DataResponse<>(dataResponse, staffList);
			return ResponseEntity.ok().body(signInResponse);
		} catch (Exception e) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0038"));
		}
	}

	@GetMapping("/get/ActiveStaff")
	public ResponseEntity<?> getActiveStaff() {
		String active = "1";
		List<Staff> staffList = staffRepository.findAllByActive(active);

		for (Staff staff : staffList) {

			if (staff != null) {

				Address address = staff.getContact() != null && staff.getContact().size() > 0
						&& staff.getContact().get(0).getAddress() != null
						&& staff.getContact().get(0).getAddress().size() > 0
								? staff.getContact().get(0).getAddress().get(0)
								: null;
				EmployeeDetails employee = staff.getEmployeeDetails() != null && staff.getEmployeeDetails().size() > 0
						? staff.getEmployeeDetails().get(0)
						: null;
				Emergency emergency = staff.getEmergency() != null && staff.getEmergency().size() > 0
						? staff.getEmergency().get(0)
						: null;

				String genderId = staff.getGender();
				String roleId = staff.getRole();
				String hipaaTrainingId = staff.getHipaaTraining();
				String userTypeId = staff.getUserType();
				String[] specialityId = staff.getSpeciality();
				String[] backgroundCheckId = staff.getBackgroundCheck();

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (d.getDropdown().equalsIgnoreCase("gender")) {
							if (item.getId().equals(genderId)) {
								staff.setGender(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("roles")) {
							if (item.getId().equals(roleId)) {
								staff.setRole(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("hipaaTraining")) {
							if (item.getId().equals(hipaaTrainingId)) {
								staff.setHipaaTraining(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("user type")) {
							if (item.getId().equals(userTypeId)) {
								staff.setUserType(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("speciality")) {
							if (item.getId().equals(specialityId)) {
								String[] speciality = item.getValue().split("");
								staff.setSpeciality(speciality);
							}
						}

						if (d.getDropdown().equalsIgnoreCase("backgroundCheckInformation")) {
							if (item.getId().equals(backgroundCheckId)) {
								String[] backgroundcheckinformation = item.getValue().split("");
								staff.setBackgroundCheck(backgroundcheckinformation);
							}
						}

						if (address != null) {
							String stateId = address.getState();
							String countryId = address.getCountry();

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									address.setState(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									address.setCountry(item.getValue());
								}
							}
						}

						if (employee != null) {
							String qualificationId = employee.getQualification();
							String[] skillsId = employee.getSkills();

							if (d.getDropdown().equalsIgnoreCase("skillsandqualification")) {
								if (item.getId().equals(qualificationId)) {
									employee.setQualification(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("skillsAndqualification")) {
								if (item.getId().equals(skillsId)) {
									String[] skills = item.getValue().split("");
									employee.setSkills(skills);
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationShip();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationShip(item.getValue());
								}
							}
						}

					}
				}
			}

		}

		if (!staffList.isEmpty()) {
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0260");
			DataResponse<List<Staff>> signInResponse = new DataResponse<>(dataResponse, staffList);
			return ResponseEntity.ok().body(signInResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0067"));
	}

	// ****** To find the Specific Staff Based on id *****\\
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getStaff(@PathVariable String id) {
		Optional<Staff> staffOptional = staffRepository.findById(id);
		if (staffOptional.isPresent()) {
			Staff staff = staffOptional.get();

			if (staff != null) {

				Address address = staff.getContact() != null && staff.getContact().size() > 0
						&& staff.getContact().get(0).getAddress() != null
						&& staff.getContact().get(0).getAddress().size() > 0
								? staff.getContact().get(0).getAddress().get(0)
								: null;
				EmployeeDetails employee = staff.getEmployeeDetails() != null && staff.getEmployeeDetails().size() > 0
						? staff.getEmployeeDetails().get(0)
						: null;
				Emergency emergency = staff.getEmergency() != null && staff.getEmergency().size() > 0
						? staff.getEmergency().get(0)
						: null;

				String genderId = staff.getGender();
				String roleId = staff.getRole();
				String hipaaTrainingId = staff.getHipaaTraining();
				String userTypeId = staff.getUserType();
				String[] specialityId = staff.getSpeciality();
				String[] backgroundCheckId = staff.getBackgroundCheck();

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (d.getDropdown().equalsIgnoreCase("gender")) {
							if (item.getId().equals(genderId)) {
								staff.setGender(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("roles")) {
							if (item.getId().equals(roleId)) {
								staff.setRole(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("hipaaTraining")) {
							if (item.getId().equals(hipaaTrainingId)) {
								staff.setHipaaTraining(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("user type")) {
							if (item.getId().equals(userTypeId)) {
								staff.setUserType(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("speciality")) {
							if (item.getId().equals(specialityId)) {
								String[] speciality = item.getValue().split("");
								staff.setSpeciality(speciality);
							}
						}

						if (d.getDropdown().equalsIgnoreCase("backgroundCheckInformation")) {
							if (item.getId().equals(backgroundCheckId)) {
								String[] backgroundcheckinformation = item.getValue().split("");
								staff.setBackgroundCheck(backgroundcheckinformation);
							}
						}

						if (address != null) {
							String stateId = address.getState();
							String countryId = address.getCountry();

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									address.setState(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									address.setCountry(item.getValue());
								}
							}
						}

						if (employee != null) {
							String qualificationId = employee.getQualification();
							String[] skillsId = employee.getSkills();

							if (d.getDropdown().equalsIgnoreCase("skillsandqualification")) {
								if (item.getId().equals(qualificationId)) {
									employee.setQualification(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("skillsAndqualification")) {
								if (item.getId().equals(skillsId)) {
									String[] skills = item.getValue().split("");
									employee.setSkills(skills);
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationShip();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationShip(item.getValue());
								}
							}
						}

					}
				}
			}

			if (staff != null) {
				DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0261");
				DataResponse<Staff> signInResponse = new DataResponse<>(dataResponse, staff);
				return ResponseEntity.ok().body(signInResponse);
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0039"));
		}
	}

	// ****** To Update Specific Staff details Based on Id *****\\
	   @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateStaff(@PathVariable String id,  @RequestBody @Validated StaffRequest staffRequest ) {
	        Optional<Staff> staffOptional = staffRepository.findById(id);
	        
	        LocalDateTime updateDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String updatedAt = updateDate.format(formatter);
	        
	        if (staffOptional.isPresent()) {
	            Staff staff = staffOptional.get();
	            String username = staffOptional.get().getUsername();
//	            System.out.println(username);
	            staff.setUsername(username);
	            staff.setUpdatedAt(updatedAt);
	            staff.update(staffRequest);
	            Staff updatedStaff=staffRepository.save(staff);
	            
	            User user = userRepository.findByEmail(staff.getEmail());

		        if (user != null) {
		            user.setOrganization(staffRequest.getOrganization());
		            user.setEmail(staffRequest.getEmail());
		            userRepository.save(user);
		        }
	            DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0262");
				DataResponse<Staff> signInResponse = new DataResponse<>(dataResponse, updatedStaff);
				return ResponseEntity.ok().body(signInResponse);
	        } else {
	            return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0039"));
	        }
	    }

	//******** To Staff Active ***********\\
	@PutMapping("/in/{id}")
	public ResponseEntity<?> staffIn(@RequestBody @PathVariable String id) {
	    Optional<Staff> staffOptional = staffRepository.findById(id);
	    if (staffOptional.isPresent()) {
	        Staff staff = staffOptional.get();
	        staff.setActive("1"); // Set active
	        staffRepository.save(staff);
	        DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0263");
			DataResponse<Staff> signInResponse = new DataResponse<>(dataResponse, staff);
			return ResponseEntity.ok().body(signInResponse);
	    } else {
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0039"));
	    }
	}

	//******To Staff InActive *****\\
    @PutMapping("/Out/{id}")
    public ResponseEntity<?> staffOut(@RequestBody @PathVariable String id) {
		 Optional<Staff> staffOptional = staffRepository.findById(id);
		    if (staffOptional.isPresent()) {
		        Staff staff = staffOptional.get();
		        staff.setActive("0"); // Set active
		        staffRepository.save(staff);
		        DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0264");
				DataResponse<Staff> signInResponse = new DataResponse<>(dataResponse, staff);
				return ResponseEntity.ok().body(signInResponse);
		    } else {
		        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0039"));
		    }
	}

    // ****** To Delete Specific Staff details Based on User Name *****\\
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteStaff(@PathVariable String username) {
        if (staffRepository.existsByUsername(username)) {
            staffRepository.deleteByUsername(username);   // Staff data exists, so delete it
            userRepository.deleteByUsername(username);    // Delete associated user data
            return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0212"));
        }
        logger.error("Staff Username does not Exists.");
        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0040"));
    }
	//*******To get Particular Role **********\\
    @GetMapping("/role/{role}")
	public ResponseEntity<?> getStaffByRole(@PathVariable String role) {
		List<Staff> staffList = staffRepository.findAllByRole(role);

		for (Staff staff : staffList) {

			if (staff != null) {

				Address address = staff.getContact() != null && staff.getContact().size() > 0
						&& staff.getContact().get(0).getAddress() != null
						&& staff.getContact().get(0).getAddress().size() > 0
								? staff.getContact().get(0).getAddress().get(0)
								: null;
				EmployeeDetails employee = staff.getEmployeeDetails() != null && staff.getEmployeeDetails().size() > 0
						? staff.getEmployeeDetails().get(0)
						: null;
				Emergency emergency = staff.getEmergency() != null && staff.getEmergency().size() > 0
						? staff.getEmergency().get(0)
						: null;

				String genderId = staff.getGender();
				String roleId = staff.getRole();
				String hipaaTrainingId = staff.getHipaaTraining();
				String userTypeId = staff.getUserType();
				String[] specialityId = staff.getSpeciality();
				String[] backgroundCheckId = staff.getBackgroundCheck();

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (d.getDropdown().equalsIgnoreCase("gender")) {
							if (item.getId().equals(genderId)) {
								staff.setGender(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("roles")) {
							if (item.getId().equals(roleId)) {
								staff.setRole(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("hipaaTraining")) {
							if (item.getId().equals(hipaaTrainingId)) {
								staff.setHipaaTraining(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("user type")) {
							if (item.getId().equals(userTypeId)) {
								staff.setUserType(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("speciality")) {
							if (item.getId().equals(specialityId)) {
								String[] speciality = item.getValue().split("");
								staff.setSpeciality(speciality);
							}
						}

						if (d.getDropdown().equalsIgnoreCase("backgroundCheckInformation")) {
							if (item.getId().equals(backgroundCheckId)) {
								String[] backgroundcheckinformation = item.getValue().split("");
								staff.setBackgroundCheck(backgroundcheckinformation);
							}
						}

						if (address != null) {
							String stateId = address.getState();
							String countryId = address.getCountry();

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									address.setState(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									address.setCountry(item.getValue());
								}
							}
						}

						if (employee != null) {
							String qualificationId = employee.getQualification();
							String[] skillsId = employee.getSkills();

							if (d.getDropdown().equalsIgnoreCase("skillsandqualification")) {
								if (item.getId().equals(qualificationId)) {
									employee.setQualification(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("skillsAndqualification")) {
								if (item.getId().equals(skillsId)) {
									String[] skills = item.getValue().split("");
									employee.setSkills(skills);
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationShip();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationShip(item.getValue());
								}
							}
						}

					}
				}
			}

		}

		if (!staffList.isEmpty()) {
			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0265");
			DataResponse<List<Staff>> signInResponse = new DataResponse<>(dataResponse, staffList);
			return ResponseEntity.ok().body(signInResponse);
		}
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0044"));
	}

	// *******To get Particular Role **********\\
	@GetMapping("/role/{role}/{Orgid}")
	public ResponseEntity<?> getStaffByRole(@PathVariable String role, @PathVariable String Orgid) {
//	Optional<Organization> organization = organizationRepository.findById(Orgid);
//	String name = organization.get().getOrganizationdetails().get(0).getName(); 

		List<Staff> staffList = staffRepository.findAllByRoleAndOrganization(role, Orgid);

		for (Staff staff : staffList) {

			if (staff != null) {

				Address address = staff.getContact() != null && staff.getContact().size() > 0
						&& staff.getContact().get(0).getAddress() != null
						&& staff.getContact().get(0).getAddress().size() > 0
								? staff.getContact().get(0).getAddress().get(0)
								: null;
				EmployeeDetails employee = staff.getEmployeeDetails() != null && staff.getEmployeeDetails().size() > 0
						? staff.getEmployeeDetails().get(0)
						: null;
				Emergency emergency = staff.getEmergency() != null && staff.getEmergency().size() > 0
						? staff.getEmergency().get(0)
						: null;

				String genderId = staff.getGender();
				String roleId = staff.getRole();
				String hipaaTrainingId = staff.getHipaaTraining();
				String userTypeId = staff.getUserType();
				String[] specialityId = staff.getSpeciality();
				String[] backgroundCheckId = staff.getBackgroundCheck();

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (d.getDropdown().equalsIgnoreCase("gender")) {
							if (item.getId().equals(genderId)) {
								staff.setGender(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("roles")) {
							if (item.getId().equals(roleId)) {
								staff.setRole(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("hipaaTraining")) {
							if (item.getId().equals(hipaaTrainingId)) {
								staff.setHipaaTraining(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("user type")) {
							if (item.getId().equals(userTypeId)) {
								staff.setUserType(item.getValue());
							}
						}

						if (d.getDropdown().equalsIgnoreCase("speciality")) {
							if (item.getId().equals(specialityId)) {
								String[] speciality = item.getValue().split("");
								staff.setSpeciality(speciality);
							}
						}

						if (d.getDropdown().equalsIgnoreCase("backgroundCheckInformation")) {
							if (item.getId().equals(backgroundCheckId)) {
								String[] backgroundcheckinformation = item.getValue().split("");
								staff.setBackgroundCheck(backgroundcheckinformation);
							}
						}

						if (address != null) {
							String stateId = address.getState();
							String countryId = address.getCountry();

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									address.setState(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									address.setCountry(item.getValue());
								}
							}
						}

						if (employee != null) {
							String qualificationId = employee.getQualification();
							String[] skillsId = employee.getSkills();

							if (d.getDropdown().equalsIgnoreCase("skillsandqualification")) {
								if (item.getId().equals(qualificationId)) {
									employee.setQualification(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("skillsAndqualification")) {
								if (item.getId().equals(skillsId)) {
									String[] skills = item.getValue().split("");
									employee.setSkills(skills);
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationShip();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationShip(item.getValue());
								}
							}
						}

					}
				}
			}

		}

		if (!staffList.isEmpty()) {
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<List<Staff>> dataResponse = new DataResponse<>(verifyResponse, staffList);
			return ResponseEntity.ok(dataResponse);
		} else {
			logger.error("Staff not found with given role.");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0044"));
		}
	}
	
	@GetMapping("/get/ActiveStaff/{organization}")
	public ResponseEntity<?> getActiveStaff(@PathVariable String organization,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize)
			 {

		String active = "1";
		if (page == -1) {
			List<Staff> staffList = staffRepository.findAll();

			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0260");
			DataResponse<?> signInResponse = new DataResponse<>(dataResponse, staffList);
			return ResponseEntity.ok().body(signInResponse);
		} else {

			Pageable pageable = PageRequest.of(page, pageSize);
			Page<Staff> staffList = staffRepository.findAllByActiveAndOrganization(active, organization, pageable);

			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0260");
			DataResponse<?> signInResponse = new DataResponse<>(dataResponse, staffList);
			return ResponseEntity.ok().body(signInResponse);
		}

	}

	// *******To get Active Staffs Based on Organization **********\\
//	@GetMapping("/get/ActiveStaff/{organization}")
//	public ResponseEntity<?> getActiveStaff(@PathVariable String organization,
//											@RequestParam(defaultValue = "0") int page,
//											@RequestParam(defaultValue = "10") int pageSize) {
//		String active = "1";
//	        Optional<Organization> organizationOptional = organizationRepository.findById(organization);
//	        String organizationName = organizationOptional.get().getOrganizationdetails().get(0).getName();
//		Pageable pageable = PageRequest.of(page, pageSize);
//		Page<Staff> staffList = staffRepository.findAllByActiveAndOrganization(active, organization, pageable);

//		for (Staff staff : staffList) {
//
//			if (staff != null) {
//
//				Address address = staff.getContact() != null && staff.getContact().size() > 0
//						&& staff.getContact().get(0).getAddress() != null
//						&& staff.getContact().get(0).getAddress().size() > 0
//								? staff.getContact().get(0).getAddress().get(0)
//								: null;
//				EmployeeDetails employee = staff.getEmployeeDetails() != null && staff.getEmployeeDetails().size() > 0
//						? staff.getEmployeeDetails().get(0)
//						: null;
//				Emergency emergency = staff.getEmergency() != null && staff.getEmergency().size() > 0
//						? staff.getEmergency().get(0)
//						: null;
//
//				String genderId = staff.getGender();
//				String roleId = staff.getRole();
//				String hipaaTrainingId = staff.getHipaaTraining();
//				String userTypeId = staff.getUserType();
//				String[] specialityId = staff.getSpeciality();
//				String[] backgroundCheckId = staff.getBackgroundCheck();
//
//				List<down> downList = dropdownRepository.findAll();
//				for (down d : downList) {
//					com.MHC.Project.Model.down.List[] list = d.getList();
//					for (com.MHC.Project.Model.down.List item : list) {
//
//						if (d.getDropdown().equalsIgnoreCase("gender")) {
//							if (item.getId().equals(genderId)) {
//								staff.setGender(item.getValue());
//							}
//						}
//
//						if (d.getDropdown().equalsIgnoreCase("roles")) {
//							if (item.getId().equals(roleId)) {
//								staff.setRole(item.getValue());
//							}
//						}
//
//						if (d.getDropdown().equalsIgnoreCase("hipaaTraining")) {
//							if (item.getId().equals(hipaaTrainingId)) {
//								staff.setHipaaTraining(item.getValue());
//							}
//						}
//
//						if (d.getDropdown().equalsIgnoreCase("user type")) {
//							if (item.getId().equals(userTypeId)) {
//								staff.setUserType(item.getValue());
//							}
//						}
//
//						if (d.getDropdown().equalsIgnoreCase("speciality")) {
//							if (item.getId().equals(specialityId)) {
//								String[] speciality = item.getValue().split("");
//								staff.setSpeciality(speciality);
//							}
//						}
//
//						if (d.getDropdown().equalsIgnoreCase("backgroundCheckInformation")) {
//							if (item.getId().equals(backgroundCheckId)) {
//								String[] backgroundcheckinformation = item.getValue().split("");
//								staff.setBackgroundCheck(backgroundcheckinformation);
//							}
//						}
//
//						if (address != null) {
//							String stateId = address.getState();
//							String countryId = address.getCountry();
//
//							if (d.getDropdown().equalsIgnoreCase("state")) {
//								if (item.getId().equals(stateId)) {
//									address.setState(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("country")) {
//								if (item.getId().equals(countryId)) {
//									address.setCountry(item.getValue());
//								}
//							}
//						}
//
//						if (employee != null) {
//							String qualificationId = employee.getQualification();
//							String[] skillsId = employee.getSkills();
//
//							if (d.getDropdown().equalsIgnoreCase("skillsandqualification")) {
//								if (item.getId().equals(qualificationId)) {
//									employee.setQualification(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("skillsAndqualification")) {
//								if (item.getId().equals(skillsId)) {
//									String[] skills = item.getValue().split("");
//									employee.setSkills(skills);
//								}
//							}
//						}
//
//						if (emergency != null) {
//							String relationshipId = emergency.getRelationShip();
//
//							if (d.getDropdown().equalsIgnoreCase("relationship")) {
//								if (item.getId().equals(relationshipId)) {
//									emergency.setRelationShip(item.getValue());
//								}
//							}
//						}
//
//					}
//				}
//			}
//
//		}
//
//		if (!staffList.isEmpty()) {
//			DataResponse.Response dataResponse = DataResponse.createVerifyResponse("MHC - 0260");
//			DataResponse<?> signInResponse = new DataResponse<>(dataResponse, staffList);
//			return ResponseEntity.ok().body(signInResponse);
//		}
//		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0067"));
//
//	}
}