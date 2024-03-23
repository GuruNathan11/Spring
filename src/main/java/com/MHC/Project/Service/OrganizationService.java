package com.MHC.Project.Service;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MHC.Project.Controller.Login;
import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Organization;
import com.MHC.Project.Model.Organization.HIPAAPrivacyOfficer;
import com.MHC.Project.Model.Organization.HIPAASecurityOfficer;
import com.MHC.Project.Model.Organization.OrganizationDetails;
import com.MHC.Project.Model.Organization.PointOfContact;
import com.MHC.Project.Model.Organization.SHIFT;
import com.MHC.Project.Model.User;
import com.MHC.Project.Repository.OrganizationRepository;
import com.MHC.Project.Repository.UserRepository;
import com.MHC.Project.Response.OrganizationResponse;

import jakarta.mail.internet.MimeMessage;

@Service
public class OrganizationService {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
    private OrganizationRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	 
	public Organization saveOrganization(Organization organizationRequest) {
	    String uid = generateUID();

	    List<OrganizationDetails> organizationDetailsList = organizationRequest.getOrganizationdetails();
	    
	    LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);
	    
	    if (isDuplicateOrganization(organizationDetailsList)) {
	        throw new IllegalArgumentException("Organization Name Already Exists"); 
	    }

	    Organization org = Organization.build(uid, organizationDetailsList, 
	            organizationRequest.getContact(), organizationRequest.getEmail(), organizationRequest.getMobileNumber(), 
	            organizationRequest.getWebsiteUrl(),organizationRequest.getShift(), organizationRequest.getProximityVerification(), organizationRequest.getGeofencing(), organizationRequest.getQ15Access(),
	            organizationRequest.getPointofcontact(), organizationRequest.getHippaprivacyofficer(), organizationRequest.getHippassecurityofficer(),organizationRequest.getUserType(), organizationRequest.getProfile(),
	            createdAt, organizationRequest.getUpdatedAt()
	            );
	    
	    org.setUserType("System Admin");
	    org.setCreatedAt(createdAt);
	    String username = "Admin"+organizationRequest.getOrganizationdetails().get(0).getName();
	    String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4)+"@"+
	    				  UUID.randomUUID().toString().replaceAll("-", "").substring(0, 2);
		
	   
	    User user = new User();
	    user.setEmail(organizationRequest.getEmail());
	    user.setUserType(organizationRequest.getUserType());
	    
	    String orgId = org.getId();
	    	// Generate OTP
	 		String secretKey = Login.generateSecretKey();
	 		user.setUsername(username);
	 		user.setPassword(encoder.encode(password));
	 		user.setUserType("System Admin");
	 		user.setSecretKey(secretKey);
	 		user.setOrganization(orgId);
	 		user.setSecretKeyExpiration(LocalDateTime.now().plusDays(60)); // Set OTP expiration to 60 days from now
	 		userRepository.save(user);
	 		
	 	// Send OTP to the user's email
			sendVerificationEmail(organizationRequest.getEmail(), secretKey, username,
					password, org);
	    
	    return repository.save(org);
	}

	private String sendVerificationEmail(String email, String secretKey, String username, String password,
			Organization org) {
		try {
			// Initialize the Apache velocity engine
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init();

			String firstname = org.getOrganizationdetails().get(0).getName();
			
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

	//*************** Organization Name Checking*************\\
	    private boolean isDuplicateOrganization(List<OrganizationDetails> organizationDetailsList) {
	        for (OrganizationDetails organizationDetails : organizationDetailsList) {
	            String name = organizationDetails.getName();
	            if (repository.existsByOrganizationdetailsName(name)) {
	                return true; // Duplicate organization name found
	            }
	        }
	        return false; // No duplicate organization name
	    }  
	
	    //*************** Get All Organization *************\\
	public List<Organization> getALlOrganization() {
        return repository.findAll();
    }
	
	 //*************** Find the Specific Organization By Id*************\\
	public Organization getOrganization(String id) throws PageNotFound {
	    Optional<Organization> organization = repository.findById(id);
	    if (organization.isPresent()) {
	        return organization.get();
	    } else {
	        throw new PageNotFound("The Organization Id: "  + id +" could not be found...");
	    }
	}

    
	public Organization updateOrganization(String id, Organization organizationRequest) throws PageNotFound {
	    Optional<Organization> organizationOptional = repository.findById(id);
	    if (organizationOptional.isPresent()) {
	        Organization organization = organizationOptional.get();
	        
	        List<Organization.OrganizationDetails> organizationDetailsList = organizationRequest.getOrganizationdetails();
	        List<Organization.contact> contact =organizationRequest.getContact();
	        String email = organizationRequest.getEmail();
	        String webUrl = organizationRequest.getWebsiteUrl();
	        SHIFT shift1 = organizationRequest.getShift();
	        List<PointOfContact> pContact = organizationRequest.getPointofcontact();
	    	List<HIPAAPrivacyOfficer> HPO = organizationRequest.getHippaprivacyofficer();
	    	List<HIPAASecurityOfficer> HSO = organizationRequest.getHippassecurityofficer();

	    	
	        organization.setOrganizationdetails(organizationDetailsList);
	        organization.setContact(contact);
	        organization.setEmail(email);
	        organization.setWebsiteUrl(webUrl);
	        organization.setShift(shift1);
	        organization.setPointofcontact(pContact);
	        organization.setHippaprivacyofficer(HPO);
	        organization.setHippassecurityofficer(HSO);
	        organization.setProfile(organizationRequest.getProfile()); 
	        return repository.save(organization);
	    } else {
	        throw new PageNotFound("The Organization Id:"  + id +"could not be found...");
	    }
	}
	
//	private boolean isDuplicateOrganization(List<Organization.OrganizationDetails> organizationDetailsList, String organizationId) {
//	    for (Organization.OrganizationDetails organizationDetails : organizationDetailsList) {
//	        String name = organizationDetails.getName();
//	        // Exclude the current organization from duplicate name check
//	        if (repository.existsByOrganizationdetailsNameAndIdNot(name, organizationId)) {
//	            return true; // Duplicate organization name found
//	        }
//	    }
//	    return false; // No duplicate organization name
//	}

  //*************** Delete the Specific Organization By Id*************\\
    public void deleteOrganization(String id) throws PageNotFound{
        if (!repository.existsById((String) id)) {
            throw new PageNotFound("The Organization Id:"  + id +"could not be found...");
        }
        repository.deleteById((String) id);
    }

    private String generateUID() {
		// Generate a random alphanumeric ID with 10 digits
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
    
    public List<OrganizationResponse> getAllOrganizations() {
        List<Organization> organizations = repository.findAll();
        return organizations.stream()
            .map(organization -> new OrganizationResponse(organization.getId(), getOrganizationName(organization)))
            .collect(Collectors.toList());
    }

    private String getOrganizationName(Organization organization) {
        if (!organization.getOrganizationdetails().isEmpty()) {
            return organization.getOrganizationdetails().get(0).getName();
        }
        return "N/A";
    }
}
        
	
