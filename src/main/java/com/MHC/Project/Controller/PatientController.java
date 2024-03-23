package com.MHC.Project.Controller;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
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

import com.MHC.Project.Error.MessageResponse;
import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Model.Patient.Contact1.Emergency;
import com.MHC.Project.Model.Patient.Employer;
import com.MHC.Project.Model.Patient.FamilyHealth;
import com.MHC.Project.Model.Patient.Insurance.Primary;
import com.MHC.Project.Model.Patient.PrimaryCarePhysician;
import com.MHC.Project.Model.Patient.Stats;
import com.MHC.Project.Model.Patient.basicDetails;
import com.MHC.Project.Model.Patient.basicDetails.Name;
import com.MHC.Project.Model.Patient.resource;
import com.MHC.Project.Model.Q15Bed;
import com.MHC.Project.Model.Sensor;
import com.MHC.Project.Model.User;
import com.MHC.Project.Model.down;
import com.MHC.Project.Repository.DropdownRepository;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.Q15BedRepository;
import com.MHC.Project.Repository.SensorRepository;
import com.MHC.Project.Repository.UserRepository;
import com.MHC.Project.Request.PatientRequest;
import com.MHC.Project.Response.DataResponse;
import com.MHC.Project.Response.VerifyResponseFactory;
import com.MHC.Project.Service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.internet.MimeMessage;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE }, maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/patient")
@SecurityRequirement(name = "mettlerHealth")
public class PatientController {

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	private final MongoTemplate mongoTemplate;

	@Autowired
	public PatientController(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Autowired
	PatientService patientService;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	SensorRepository sensorRepository;
	
	@Autowired
	Q15BedRepository q15BedRepository;
	
	@Autowired
	DropdownRepository dropdownRepository;

	@GetMapping
	public String welcome() {
		return "Welcome to Mettler Health Care Home Page...";
	}

	// -------------------------------------SIGN-UP--------------------------------------------------------------------------------------------------------
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody PatientRequest patientRequest) {

		List<basicDetails> basicDetailsList = patientRequest.getBasicDetails();
		if (isDuplicateSSN(basicDetailsList)) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0008"));
		}
		// To validate SSN
		basicDetails firstBasicDetails = basicDetailsList.get(0);
		String ssn = firstBasicDetails.getSsn();
		if (ssn.length() != 9) {
			logger.error("Invalid SSn Length...");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0022"));
		}
		if (patientRepository.existsByUsername(patientRequest.getUsername())) {
			logger.error("User Name already taken...");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0023"));
		}

		if (patientRepository.existsByEmail(patientRequest.getEmail())) {
			logger.error("Eamil Id already taken...");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0024"));
		}
		User userEmail = userRepository.findByEmail(patientRequest.getEmail());
		if (userEmail != null) {
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0024"));
		}

		// Create new user's account
		String uid = PatientService.generateUID(); // Generate UID

		resource resource = new resource();
		resource.setFullUrl("urn:uuid:" + uid);
		resource.setResourceType("PATIENT");
		patientRequest.setActive(patientRequest.getActive());
		
		LocalDateTime createdDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createdDate.format(formatter);

		Patient patient = new Patient(uid, patientRequest.getActive(), List.of(resource),
				patientRequest.getBasicDetails(), patientRequest.getEmail(), patientRequest.getOrganization(),
				patientRequest.getUserType(), patientRequest.getContact(), patientRequest.getEmployer(),
				patientRequest.getGuardian(), patientRequest.getMisc(), patientRequest.getStats(),
				patientRequest.getInsurance(), patientRequest.getFamilyHealth(), patientRequest.getSocialHistory(),
				patientRequest.getPrimaryCarePhysician(), patientRequest.getDevices(), patientRequest.getBeaconDevice(), 
				patientRequest.getUuid(), patientRequest.getBeaconDevice1(), patientRequest.getUuid1());

		Optional<Organization> organizationOptional = organizationRepository.findById(patientRequest.getOrganization());
		if (organizationOptional.isEmpty()) {
			logger.error("Error: Invalid organization ID");
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0025"));
		}
		List<FamilyHealth> family = patientRequest.getFamilyHealth();
		for (FamilyHealth familyMember : family) {
		    familyMember.setId(PatientService.generateUID());
		}
		List<PrimaryCarePhysician> primary = patientRequest.getPrimaryCarePhysician();
		for (PrimaryCarePhysician primaryC : primary) {
			primaryC.setId(PatientService.generateUID());
		}
//		List<Devices> device = patientRequest.getDevices();
//		for (Devices d : device) {
//			d.setId(PatientService.generateUID());
//		}
//		String organizationName = organizationOptional.get().getOrganizationdetails().get(0).getName();
		patient.setUsername(patientRequest.getUsername());
		patient.setPassword(encoder.encode(patientRequest.getPassword()));
		patient.setOrganization(organizationOptional.get().getId());
		patient.setCreatedAt(createdAt);
		patient.setUserType("Patient");
		patient.setBeaconDevice(patientRequest.getDeviceId());
		patient.setBeaconDevice1(patientRequest.getDeviceId1());

		Map<String, String> uuid = new HashMap<>();
		for (String deviceId : patientRequest.getDeviceId1()) {
			if (deviceId != null && !deviceId.isEmpty()) {
				Optional<Sensor> sensorOptional = sensorRepository.findByDeviceId(deviceId);
				sensorOptional.ifPresent(sensor -> uuid.put(deviceId, sensor.getUuid()));
			}
		}
		patient.setUuid1(uuid);

		for (String deviceId : patientRequest.getDeviceId1()) {
			if (deviceId != null && !deviceId.isEmpty()) {
				Optional<Patient> patientOptional = patientRepository.findByBeaconDevice(deviceId);
				if (patientOptional.isPresent()) {
					return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0195"));
				}
			}	
		}

		patientRepository.save(patient);
		User user = new User();
		user.setUsername(patientRequest.getUsername());
		user.setEmail(patientRequest.getEmail());
		user.setPassword(encoder.encode(patientRequest.getPassword()));
		user.setOrganization(organizationOptional.get().getId());
		user.setUserType("Patient");

		userRepository.save(user);

		// Generate OTP
		String secretKey = Login.generateSecretKey();
		user.setSecretKey(secretKey);
		user.setSecretKeyExpiration(LocalDateTime.now().plusDays(60)); // Set OTP expiration to 60 days from now
		userRepository.save(user);

		// ****** Email- Send ******
		sendVerificationEmail(patientRequest.getEmail(), patientRequest.getUsername(), patientRequest.getPassword(),
				secretKey, patient);
		Object patients = PatientService.save(patient);
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0271");
	    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}

	// ****** To Validate the SSN *****\\
	private boolean isDuplicateSSN(List<basicDetails> basicDetailsList) {
		for (basicDetails basicDetails : basicDetailsList) {
			String ssn = basicDetails.getSsn();

			if (patientRepository.existsByBasicDetailsSsn(ssn)) {
				return true; // Duplicate SSN found
			}
		}
		return false; // No duplicate SSN
	}

	private String sendVerificationEmail(String email, String username, String password, String secretKey,
			Patient patient) {
		try {
			// Initialize the Apache Velocity engine
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init();
			String given = "";
			String family = "";
			String firstname = "";

			if (patient != null && patient.getBasicDetails() != null && !patient.getBasicDetails().isEmpty()) {
				Patient.basicDetails basicDetails = patient.getBasicDetails().get(0); // Assuming only one basicDetails
				if (basicDetails.getName() != null && !basicDetails.getName().isEmpty()) {
					Name name = basicDetails.getName().get(0); // Assuming only one name
					given = name.getGiven();
					family = name.getFamily();
					firstname = given + " " + family;
				}
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

	// ****** To get all the Patient List *****\\
	@GetMapping("/get_all")
	public ResponseEntity<?> getAllPatient() throws PageNotFound {
		List<Patient> patients = patientService.getAllPatient();

		for (Patient patient : patients) {

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}
		}

		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}
	
	@GetMapping("/sortBy")
    public ResponseEntity<?> sortPatientByGivenFamilySsnRoomBedNos(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        // Construct a query based on the provided search parameter
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (search != null) {
            Pattern pattern = Pattern.compile("^" + search, Pattern.CASE_INSENSITIVE);
            criteria.orOperator(
                Criteria.where("basicDetails.name.given").regex(pattern),
                Criteria.where("basicDetails.name.family").regex(pattern),
                Criteria.where("basicDetails.ssn").regex(pattern),
                Criteria.where("roomNo").regex(pattern),
                Criteria.where("bedNo").regex(pattern)
            );
        }

        query.addCriteria(criteria);

        // Execute the query to get the total count of elements
        long totalElements = mongoTemplate.count(query, Patient.class);

        // Apply pagination
        Pageable pageable = PageRequest.of(page, pageSize);
        query.with(pageable);

        // Execute the query with pagination
        List<Patient> patientList = mongoTemplate.find(query, Patient.class);

        // Create a Page object manually
        Page<Patient> patientPage = new PageImpl<>(patientList, pageable, totalElements);

        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientPage);
        return ResponseEntity.ok(dataResponse); 
    }
	
	
	@GetMapping("/get/activePatientMob/{org}")
	public ResponseEntity<?> getAllActivePatients(@PathVariable String org) {

		List<Patient> patients = patientRepository.findAllByActiveAndOrganization("1", org);
			if (!patients.isEmpty()) {

				DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
				DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
				return ResponseEntity.ok(dataResponse);
			} else {
				logger.error("No Active Patients Found...");
				return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0055"));
			}
		}
	
	@GetMapping("/get/activePatient/{org}")
	public ResponseEntity<?> getAllActivePatientsPage(@PathVariable String org,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {

		if (page == -1) {
			List<Patient> patientList = patientRepository.findAll();

			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientList);
			return ResponseEntity.ok(dataResponse);
		} else {

			Pageable pageable = PageRequest.of(page, pageSize);
			Page<Patient> patients = patientRepository.findAllByActiveAndOrganization("1", org, pageable);
			
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
			return ResponseEntity.ok(dataResponse);
		}

	}
//	@GetMapping("/get/activePatient/{org}")
//	public ResponseEntity<DataResponse<?>> getAllActivePatientsPage(
//	        @PathVariable String org,
//	        @RequestParam(defaultValue = "0") int page,
//	        @RequestParam(defaultValue = "10") int pageSize) {
//
//	    if (page == -1) {
//	        // Fetch all patients without pagination
//	        List<Patient> patientList = patientRepository.findAll();
//
//	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientList);
//	        return ResponseEntity.ok(dataResponse);
//	    } else {
//	        // Fetch patients with pagination
//	        Pageable pageable = PageRequest.of(page, pageSize);
//	        Page<Patient> patients = patientRepository.findAllByActiveAndOrganization("1", org, pageable);
//
//	        DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//	        DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
//	        return ResponseEntity.ok(dataResponse);
//	    }
//	}


	
	@GetMapping("/get-name/{pid}")
	public ResponseEntity<?> getPatientName(@PathVariable String pid) {
		
		Optional<Patient> patient = patientRepository.findById(pid);
		String given = patient.get().getBasicDetails().get(0).getName().get(0).getGiven();
		String family = patient.get().getBasicDetails().get(0).getName().get(0).getFamily();
		String patientName = given+" " + family;
		
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientName);
		return ResponseEntity.ok(dataResponse);
	}

//	// ****** To get all the Active Patient List *****\\
//	@GetMapping("/get/activePatient/{org}")
//	public ResponseEntity<?> getAllActivePatientsPage(@PathVariable String org,
//												  @RequestParam(defaultValue = "0") int page,
//												  @RequestParam(defaultValue = "10") int pageSize) {
//		Optional<Organization> organizationOptional = organizationRepository.findById(org);
//		String organization = organizationOptional.get().getOrganizationdetails().get(0).getName();
//		Pageable pageable = PageRequest.of(page, pageSize);
//		Page<Patient> patients = patientRepository.findAllByActiveAndOrganization("1", org, pageable);
//		if (!patients.isEmpty()) {
//
//			for (Patient patient : patients) {
//
//				if (patient != null) {
//
//					basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0)
//							: null;
//					Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
//					Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
//					Emergency emergency = patient.getContact() != null
//							&& patient.getContact().get(0).getEmergency() != null
//									? patient.getContact().get(0).getEmergency().get(0)
//									: null;
//					Primary primary = patient.getInsurance() != null
//							&& patient.getInsurance().get(0).getPrimary() != null
//									? patient.getInsurance().get(0).getPrimary().get(0)
//									: null;
//
//					List<down> downList = dropdownRepository.findAll();
//					for (down d : downList) {
//						com.MHC.Project.Model.down.List[] list = d.getList();
//						for (com.MHC.Project.Model.down.List item : list) {
//
//							if (basicDetails != null) {
//								String genderId = basicDetails.getGender();
//								String maritalStatusId = basicDetails.getMaritalStatus();
//								String sexualOrientationId = basicDetails.getSexualOrientation();
//
//								if (d.getDropdown().equalsIgnoreCase("gender")) {
//									if (item.getId().equals(genderId)) {
//										basicDetails.setGender(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("marital status")) {
//									if (item.getId().equals(maritalStatusId)) {
//										basicDetails.setMaritalStatus(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
//									if (item.getId().equals(sexualOrientationId)) {
//										basicDetails.setSexualOrientation(item.getValue());
//									}
//								}
//							}
//
//							if (stats != null) {
//								String ethnicityId = stats.getEthnicity();
//								String religionId = stats.getReligion();
//								String referralSourceId = stats.getReferralSource();
//								String raceId = stats.getRace();
//								String languageId = stats.getLanguage();
//
//								if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
//									if (item.getId().equals(ethnicityId)) {
//										stats.setEthnicity(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("religion")) {
//									if (item.getId().equals(religionId)) {
//										stats.setReligion(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("referralSource")) {
//									if (item.getId().equals(referralSourceId)) {
//										stats.setReferralSource(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("race")) {
//									if (item.getId().equals(raceId)) {
//										stats.setRace(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("language")) {
//									if (item.getId().equals(languageId)) {
//										stats.setLanguage(item.getValue());
//									}
//								}
//							}
//
//							if (employer != null) {
//								String industryId = employer.getIndustry();
//								String occupationId = employer.getOccupation();
//								String countryId = employer.getCountry();
//								String stateId = employer.getState();
//
//								if (d.getDropdown().equalsIgnoreCase("industry")) {
//									if (item.getId().equals(industryId)) {
//										employer.setIndustry(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("occupation")) {
//									if (item.getId().equals(occupationId)) {
//										employer.setOccupation(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("country")) {
//									if (item.getId().equals(countryId)) {
//										employer.setCountry(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("state")) {
//									if (item.getId().equals(stateId)) {
//										employer.setState(item.getValue());
//									}
//								}
//							}
//
//							if (emergency != null) {
//								String relationshipId = emergency.getRelationship();
//
//								if (d.getDropdown().equalsIgnoreCase("relationship")) {
//									if (item.getId().equals(relationshipId)) {
//										emergency.setRelationship(item.getValue());
//									}
//								}
//							}
//
//							if (primary != null) {
//								String titleId = primary.getTitle();
//								String acceptAssignmentId = primary.getAcceptAssignment();
//
//								if (d.getDropdown().equalsIgnoreCase("title")) {
//									if (item.getId().equals(titleId)) {
//										primary.setTitle(item.getValue());
//									}
//								}
//
//								if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
//									if (item.getId().equals(acceptAssignmentId)) {
//										primary.setAcceptAssignment(item.getValue());
//									}
//								}
//							}
//						}
//					}
//
//				}
//
//			}
//
//			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
//			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
//			return ResponseEntity.ok(dataResponse);
//		} else {
//			logger.error("No Active Patients Found...");
//			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0055"));
//		}
//	}

	/******** To get the patient based on unassigned bed, active and org ********/
	@GetMapping("/getUnassisgned/{org}")
	public ResponseEntity<?> getAllAssignedActivePatients1(@PathVariable String org) {
		Optional<Organization> organizationOptional = organizationRepository.findById(org);
		if (organizationOptional.isPresent()) {
//			String organization = organizationOptional.get().getOrganizationdetails().get(0).getName();
			List<Patient> patients = patientRepository.findAllByActiveAndOrganization("1", org);

			for (Patient patient : patients) {

				if (patient != null) {

					basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0)
							: null;
					Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
					Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
					Emergency emergency = patient.getContact() != null
							&& patient.getContact().get(0).getEmergency() != null
									? patient.getContact().get(0).getEmergency().get(0)
									: null;
					Primary primary = patient.getInsurance() != null
							&& patient.getInsurance().get(0).getPrimary() != null
									? patient.getInsurance().get(0).getPrimary().get(0)
									: null;

					List<down> downList = dropdownRepository.findAll();
					for (down d : downList) {
						com.MHC.Project.Model.down.List[] list = d.getList();
						for (com.MHC.Project.Model.down.List item : list) {

							if (basicDetails != null) {
								String genderId = basicDetails.getGender();
								String maritalStatusId = basicDetails.getMaritalStatus();
								String sexualOrientationId = basicDetails.getSexualOrientation();

								if (d.getDropdown().equalsIgnoreCase("gender")) {
									if (item.getId().equals(genderId)) {
										basicDetails.setGender(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("marital status")) {
									if (item.getId().equals(maritalStatusId)) {
										basicDetails.setMaritalStatus(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
									if (item.getId().equals(sexualOrientationId)) {
										basicDetails.setSexualOrientation(item.getValue());
									}
								}
							}

							if (stats != null) {
								String ethnicityId = stats.getEthnicity();
								String religionId = stats.getReligion();
								String referralSourceId = stats.getReferralSource();
								String raceId = stats.getRace();
								String languageId = stats.getLanguage();

								if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
									if (item.getId().equals(ethnicityId)) {
										stats.setEthnicity(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("religion")) {
									if (item.getId().equals(religionId)) {
										stats.setReligion(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("referralSource")) {
									if (item.getId().equals(referralSourceId)) {
										stats.setReferralSource(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("race")) {
									if (item.getId().equals(raceId)) {
										stats.setRace(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("language")) {
									if (item.getId().equals(languageId)) {
										stats.setLanguage(item.getValue());
									}
								}
							}

							if (employer != null) {
								String industryId = employer.getIndustry();
								String occupationId = employer.getOccupation();
								String countryId = employer.getCountry();
								String stateId = employer.getState();

								if (d.getDropdown().equalsIgnoreCase("industry")) {
									if (item.getId().equals(industryId)) {
										employer.setIndustry(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("occupation")) {
									if (item.getId().equals(occupationId)) {
										employer.setOccupation(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("country")) {
									if (item.getId().equals(countryId)) {
										employer.setCountry(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("state")) {
									if (item.getId().equals(stateId)) {
										employer.setState(item.getValue());
									}
								}
							}

							if (emergency != null) {
								String relationshipId = emergency.getRelationship();

								if (d.getDropdown().equalsIgnoreCase("relationship")) {
									if (item.getId().equals(relationshipId)) {
										emergency.setRelationship(item.getValue());
									}
								}
							}

							if (primary != null) {
								String titleId = primary.getTitle();
								String acceptAssignmentId = primary.getAcceptAssignment();

								if (d.getDropdown().equalsIgnoreCase("title")) {
									if (item.getId().equals(titleId)) {
										primary.setTitle(item.getValue());
									}
								}

								if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
									if (item.getId().equals(acceptAssignmentId)) {
										primary.setAcceptAssignment(item.getValue());
									}
								}
							}
						}
					}

				}

			}

			if (patients != null && !patients.isEmpty()) {
				for (Patient patient : patients) {
					String assigned = patient.getAssignedBed();
					if (assigned != null) {
						return ResponseEntity.badRequest()
								.body(VerifyResponseFactory.createVerifyResponse("MHC - 0068"));
					}
					DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
					DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patient);
					return ResponseEntity.ok().body(dataResponse);
				}
			} else {
				return ResponseEntity.badRequest().body(VerifyResponseFactory.createVerifyResponse("MHC - 0069"));
			}
		}
		return ResponseEntity.badRequest().body(VerifyResponseFactory.createVerifyResponse("MHC - 0070"));
	}

	// ****** To find the Specific Patient Based on id *****\\
	@GetMapping("/getPatient/{id}")
	public ResponseEntity<?> getPatient(@PathVariable String id) {
		Optional<Patient> patientOptional = patientRepository.findById(id);
		if (patientOptional.isPresent()) {
			Patient patient = patientOptional.get();

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}

			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patient);
			return ResponseEntity.ok(dataResponse);
		} else {
			logger.error("Patient Not Found With the Given Id: {}", id);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}
	}

	// ****** To get the Specific Patient Based on SSN *****\\
	@GetMapping("/getPatient/ssn/{ssn}")
	public ResponseEntity<?> getPatientBySsn(@PathVariable String ssn) {
		Optional<Patient> patientExists = patientRepository.findByBasicDetailsSsn(ssn);
		if (!patientExists.isEmpty()) {

			Patient patient = patientExists.get();

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}

			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
			DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patientExists);
			return ResponseEntity.ok(dataResponse);
		}
		logger.error("Patient Not Found With the Given SSN: {}", ssn);
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0028"));
	}

	// ****** To get the Specific Patient Based on Given Name *****\\
	@GetMapping("/getPatient/givenName/{givenName}")
	public ResponseEntity<?> getPatientsByGivenName(@PathVariable String givenName) {
		List<Patient> patients = patientRepository.findByBasicDetailsNameGiven(givenName);

		for (Patient patient : patients) {

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}
		}

		if (patients.isEmpty()) {
			logger.error("Patient Not Found With the Given Name: {}", givenName);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0029"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}

	// ****** To get the Patient List Based on Family Name *****\\
	@GetMapping("/getPatient/familyName/{familyName}")
	public ResponseEntity<?> getPatientsByFamilyName(@PathVariable String familyName) {
		List<Patient> patients = patientRepository.findByBasicDetailsNameFamily(familyName);

		for (Patient patient : patients) {

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}

		}

		if (patients.isEmpty()) {
			logger.error("Patient Not Found With the Family Name: {}", familyName);
			return ResponseEntity.ok().body(new MessageResponse("No patients found with family name"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}

	// ****** To get the Patient List Based on Given Name And Family Name *****\\
	@GetMapping("/getPatient/givenName/{givenName}/familyName/{familyName}")
	public ResponseEntity<?> getPatientsByGivenNameAndFamilyName(@PathVariable String givenName,
			@PathVariable String familyName) {
		List<Patient> patients = patientRepository.findByBasicDetailsNameGivenAndBasicDetailsNameFamily(givenName,
				familyName);

		for (Patient patient : patients) {

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}

		}

		if (patients.isEmpty()) {
			logger.error("No patients found with given name: {} and family name: {}", givenName, familyName);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0030"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}

	// ****** To get the Patient List Based on Given Name And Family Name And DOB//
	// *****\\
	@GetMapping("/getPatient/givenName/{givenName}/familyName/{familyName}/birthDate/{birthDate}")
	public ResponseEntity<?> getPatientsByGivenNameAndFamilyNameAndBirthDate(@PathVariable String givenName,
			@PathVariable String familyName, @PathVariable String birthDate) {
		List<Patient> patients = patientRepository
				.findByBasicDetailsNameGivenAndBasicDetailsNameFamilyAndBasicDetailsBirthDate(givenName, familyName,
						birthDate);

		for (Patient patient : patients) {

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}
		}

		if (patients.isEmpty()) {
			logger.error("No patients found with given name: {} and family name: {} and birthDate: {}", givenName,
					familyName, birthDate);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0031"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}

	// ****** To get the Patient List Based on DOB *****\\
	@GetMapping("/getPatient/dob/{birthDate}")
	public ResponseEntity<?> getPatientsByBirthDate(String birthDate) {
		List<Patient> patients = patientRepository.findByBasicDetailsBirthDate(birthDate);

		for (Patient patient : patients) {

			if (patient != null) {

				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
						? patient.getContact().get(0).getEmergency().get(0)
						: null;
				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
						? patient.getInsurance().get(0).getPrimary().get(0)
						: null;

				List<down> downList = dropdownRepository.findAll();
				for (down d : downList) {
					com.MHC.Project.Model.down.List[] list = d.getList();
					for (com.MHC.Project.Model.down.List item : list) {

						if (basicDetails != null) {
							String genderId = basicDetails.getGender();
							String maritalStatusId = basicDetails.getMaritalStatus();
							String sexualOrientationId = basicDetails.getSexualOrientation();

							if (d.getDropdown().equalsIgnoreCase("gender")) {
								if (item.getId().equals(genderId)) {
									basicDetails.setGender(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("marital status")) {
								if (item.getId().equals(maritalStatusId)) {
									basicDetails.setMaritalStatus(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
								if (item.getId().equals(sexualOrientationId)) {
									basicDetails.setSexualOrientation(item.getValue());
								}
							}
						}

						if (stats != null) {
							String ethnicityId = stats.getEthnicity();
							String religionId = stats.getReligion();
							String referralSourceId = stats.getReferralSource();
							String raceId = stats.getRace();
							String languageId = stats.getLanguage();

							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
								if (item.getId().equals(ethnicityId)) {
									stats.setEthnicity(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("religion")) {
								if (item.getId().equals(religionId)) {
									stats.setReligion(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
								if (item.getId().equals(referralSourceId)) {
									stats.setReferralSource(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("race")) {
								if (item.getId().equals(raceId)) {
									stats.setRace(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("language")) {
								if (item.getId().equals(languageId)) {
									stats.setLanguage(item.getValue());
								}
							}
						}

						if (employer != null) {
							String industryId = employer.getIndustry();
							String occupationId = employer.getOccupation();
							String countryId = employer.getCountry();
							String stateId = employer.getState();

							if (d.getDropdown().equalsIgnoreCase("industry")) {
								if (item.getId().equals(industryId)) {
									employer.setIndustry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("occupation")) {
								if (item.getId().equals(occupationId)) {
									employer.setOccupation(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("country")) {
								if (item.getId().equals(countryId)) {
									employer.setCountry(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("state")) {
								if (item.getId().equals(stateId)) {
									employer.setState(item.getValue());
								}
							}
						}

						if (emergency != null) {
							String relationshipId = emergency.getRelationship();

							if (d.getDropdown().equalsIgnoreCase("relationship")) {
								if (item.getId().equals(relationshipId)) {
									emergency.setRelationship(item.getValue());
								}
							}
						}

						if (primary != null) {
							String titleId = primary.getTitle();
							String acceptAssignmentId = primary.getAcceptAssignment();

							if (d.getDropdown().equalsIgnoreCase("title")) {
								if (item.getId().equals(titleId)) {
									primary.setTitle(item.getValue());
								}
							}

							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
								if (item.getId().equals(acceptAssignmentId)) {
									primary.setAcceptAssignment(item.getValue());
								}
							}
						}
					}
				}

			}

		}

		if (patients.isEmpty()) {
			logger.error("No patients found with birthDate: {}", birthDate);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0032"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}

	// ****** To Update Specific Patient details Based on Id *****\\
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable String id,
	        @RequestBody @Validated PatientRequest patientRequest) {
	    Optional<Patient> patientOptional = patientRepository.findById(id);
	    
	    LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
	    
	    if (patientOptional.isPresent()) {
	        Patient patient = patientOptional.get();
	        
	        String username = patient.getUsername();
	        patient.setUserType(username);
	        patient.setUpdatedAt(updatedAt);
	        patient.update(patientRequest);

	        // Assuming you have a field in PatientRequest for the email
//	        String userEmail = patientRequest.getEmail();
	        User user = userRepository.findByEmail(patient.getEmail());

	        if (user != null) {
	            user.setOrganization(patientRequest.getOrganization());
	            user.setEmail(patientRequest.getEmail());
	            userRepository.save(user);

	            Patient updatedPatient = patientRepository.save(patient);
	            DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0270");
	            DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, updatedPatient);
	            return ResponseEntity.ok(dataResponse); // Return the updated patient
	        } else {
	            logger.error("User not found with email: " + patient.getEmail());
	            return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
	        }
	    } else {
	        logger.error("Patient not found with id: " + id);
	        return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
	    }
	}


	// ****** To Delete the Patient details Based on Username *****\\
	@DeleteMapping("/delete/{username}")
	public ResponseEntity<?> deleteStaff(@PathVariable String username) {

		Optional<Patient> patientOptional = patientRepository.findByUsername(username);
		if (patientOptional.isEmpty()) {
			logger.error("Patient not found with username: " + username);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0033"));
		}
		patientRepository.deleteByUsername(username);
		userRepository.deleteByUsername(username);
		return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0209"));
	}

	@GetMapping("/getPatientByorg/{organization}")
	public ResponseEntity<?> getPatientByOrganization(@PathVariable String organization) {
		List<Patient> patients = patientRepository.findAllByActiveAndOrganization("0",organization);

		if (patients.isEmpty()) {
			logger.error("No patients found for organization: {}", organization);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0006")); 
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}
	
	// ****** To get the Patient List Based on Organization *****\\
	@GetMapping("/getPatient/org/{organization}")
	public ResponseEntity<?> getPatientsByOrganization(@PathVariable String organization,
													   @RequestParam(defaultValue = "0") int page,
													   @RequestParam(defaultValue = "10") int pageSize) {
		
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Patient> patients = patientRepository.findAllByActiveAndOrganization("0",organization, pageable);

//		for (Patient patient : patients) {
//
//			if (patient != null) {
//
//				basicDetails basicDetails = patient.getBasicDetails() != null ? patient.getBasicDetails().get(0) : null;
//				Stats stats = patient.getStats() != null ? patient.getStats().get(0) : null;
//				Employer employer = patient.getEmployer() != null ? patient.getEmployer().get(0) : null;
//				Emergency emergency = patient.getContact() != null && patient.getContact().get(0).getEmergency() != null
//						? patient.getContact().get(0).getEmergency().get(0)
//						: null;
//				Primary primary = patient.getInsurance() != null && patient.getInsurance().get(0).getPrimary() != null
//						? patient.getInsurance().get(0).getPrimary().get(0)
//						: null;
//
//				List<down> downList = dropdownRepository.findAll();
//				for (down d : downList) {
//					com.MHC.Project.Model.down.List[] list = d.getList();
//					for (com.MHC.Project.Model.down.List item : list) {
//
//						if (basicDetails != null) {
//							String genderId = basicDetails.getGender();
//							String maritalStatusId = basicDetails.getMaritalStatus();
//							String sexualOrientationId = basicDetails.getSexualOrientation();
//
//							if (d.getDropdown().equalsIgnoreCase("gender")) {
//								if (item.getId().equals(genderId)) {
//									basicDetails.setGender(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("marital status")) {
//								if (item.getId().equals(maritalStatusId)) {
//									basicDetails.setMaritalStatus(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("sexual Orientation")) {
//								if (item.getId().equals(sexualOrientationId)) {
//									basicDetails.setSexualOrientation(item.getValue());
//								}
//							}
//						}
//
//						if (stats != null) {
//							String ethnicityId = stats.getEthnicity();
//							String religionId = stats.getReligion();
//							String referralSourceId = stats.getReferralSource();
//							String raceId = stats.getRace();
//							String languageId = stats.getLanguage();
//
//							if (d.getDropdown().equalsIgnoreCase("ethnicity")) {
//								if (item.getId().equals(ethnicityId)) {
//									stats.setEthnicity(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("religion")) {
//								if (item.getId().equals(religionId)) {
//									stats.setReligion(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("referralSource")) {
//								if (item.getId().equals(referralSourceId)) {
//									stats.setReferralSource(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("race")) {
//								if (item.getId().equals(raceId)) {
//									stats.setRace(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("language")) {
//								if (item.getId().equals(languageId)) {
//									stats.setLanguage(item.getValue());
//								}
//							}
//						}
//
//						if (employer != null) {
//							String industryId = employer.getIndustry();
//							String occupationId = employer.getOccupation();
//							String countryId = employer.getCountry();
//							String stateId = employer.getState();
//
//							if (d.getDropdown().equalsIgnoreCase("industry")) {
//								if (item.getId().equals(industryId)) {
//									employer.setIndustry(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("occupation")) {
//								if (item.getId().equals(occupationId)) {
//									employer.setOccupation(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("country")) {
//								if (item.getId().equals(countryId)) {
//									employer.setCountry(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("state")) {
//								if (item.getId().equals(stateId)) {
//									employer.setState(item.getValue());
//								}
//							}
//						}
//
//						if (emergency != null) {
//							String relationshipId = emergency.getRelationship();
//
//							if (d.getDropdown().equalsIgnoreCase("relationship")) {
//								if (item.getId().equals(relationshipId)) {
//									emergency.setRelationship(item.getValue());
//								}
//							}
//						}
//
//						if (primary != null) {
//							String titleId = primary.getTitle();
//							String acceptAssignmentId = primary.getAcceptAssignment();
//
//							if (d.getDropdown().equalsIgnoreCase("title")) {
//								if (item.getId().equals(titleId)) {
//									primary.setTitle(item.getValue());
//								}
//							}
//
//							if (d.getDropdown().equalsIgnoreCase("accept assignment")) {
//								if (item.getId().equals(acceptAssignmentId)) {
//									primary.setAcceptAssignment(item.getValue());
//								}
//							}
//						}
//					}
//				}
//
//			}
//
//		}

		if (patients.isEmpty()) {
			logger.error("No patients found for organization: {}", organization);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0006"));
		}
		DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0272");
		DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, patients);
		return ResponseEntity.ok(dataResponse);
	}

	// ****** To set the Specific Patient Active details to inactive *****\\
	@PostMapping("/discharge/{id}")
	public ResponseEntity<?> dischargePatient(@PathVariable String id) {
		Optional<Patient> patientOptional = patientRepository.findById(id);
		Optional<Q15Bed> bedOptional = q15BedRepository.findByPid(id);
		if (patientOptional.isPresent()) {
			Patient patient = patientOptional.get();
			if (bedOptional.isPresent()) {
				Q15Bed bed = bedOptional.get();
				bed.setPid(null);
				bed.setBedAssignDateTime(null);
				bed.setOccupied(false);
				q15BedRepository.save(bed);
			}
			patient.setActive("0"); // Set active status to false
			Patient discharge = patientRepository.save(patient);
			DataResponse.Response verifyResponse = DataResponse.createVerifyResponse("MHC - 0184");
		    DataResponse<?> dataResponse = new DataResponse<>(verifyResponse, discharge);
			return ResponseEntity.ok(dataResponse); 
		} else {
			logger.error("Patient not found with id: " + id);
			return ResponseEntity.ok().body(VerifyResponseFactory.createVerifyResponse("MHC - 0027"));
		}

	}
}