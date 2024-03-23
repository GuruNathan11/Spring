package com.MHC.Project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Staff;
import com.MHC.Project.Model.Staff.resource;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Request.StaffRequest;

@Service
public class StaffService {

	@Autowired
	private StaffRepository repository;

	@Autowired
	PasswordEncoder encoder;

	public Staff saveStaff(StaffRequest staffRequest) throws PageNotFound {
		String uid = generateUID(); // Generate UID

		resource resource = new resource();
		resource.setFullUrl("urn:uuid:" + uid);
		resource.setResourceType("StaffRegistration");
		
		Staff staff = Staff.build(uid, List.of(resource), staffRequest.getName(), staffRequest.getGender(),
				staffRequest.getEmail(), staffRequest.getRole(), staffRequest.getUsername(), staffRequest.getPassword(),
				staffRequest.getOrganization(),staffRequest.getStartDate(), staffRequest.getUserType(), staffRequest.getSpeciality(),
				staffRequest.getDateofBirth(), staffRequest.get_birthdate(), staffRequest.getSsn(),
				staffRequest.getNpi(), staffRequest.getContact(), staffRequest.getEmployeeDetails(),
				staffRequest.getEmergency(), staffRequest.getBackgroundCheck(), staffRequest.getImmunizationStatus(),
				staffRequest.getHipaaTraining(), staffRequest.getPrivacyAcknowledgement(), staffRequest.getSignature(),
				staffRequest.getActive(), staffRequest.getTerminationDate(), staffRequest.getTerminationReason());
		staff.setId(uid);
		return repository.save(staff);
	}

	// generate fullUrl UID
	public static String generateUID() {
		// Generate a UID
		String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCD";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	// ****** To Get All Staff details *****\\
	public List<Staff> getAllStaff() {
		return repository.findAll();
	}

	// ****** To Get Specific Staff details Based on Id *****\\
	public Staff getStaff(String id) throws PageNotFound {
		Staff staff = repository.findById(id).get();
		if (staff != null) {
			return staff;
		} else {
			throw new PageNotFound("Staff not found with id : " + id);
		}
	}

	// ****** To Update Specific Staff details Based on Id *****\\
	public Staff updateStaff(String id, StaffRequest staffRequest) throws PageNotFound {
		Optional<Staff> staffOptional = Optional.ofNullable(repository.findById(id).get());
		if (staffOptional.isPresent()) {
			Staff staff = staffOptional.get();
			staff.update(staffRequest);
			return repository.save(staff);
		} else {
			throw new PageNotFound("Staff not found with id: " + id);
		}
	}

	// updatestaffIn service
	public Staff staffIn(String id) throws PageNotFound {
		Optional<Staff> staffOptional = repository.findById(id);
		if (staffOptional.isPresent()) {
			Staff staff = staffOptional.get();
			staff.setActive("1"); // Set active
			repository.save(staff);
			return staff;
		} else {
			throw new PageNotFound("Staff not found with id: " + id);
		}
	}

	// update staffOut service
	public Staff staffOut(String id) throws PageNotFound {
		Optional<Staff> staffOptional = repository.findById(id);
		if (staffOptional.isPresent()) {
			Staff staff = staffOptional.get();
			staff.setActive("0"); // set Inactive
			repository.save(staff);
			return staff;
		} else {
			throw new PageNotFound("Staff not found with id: " + id);
		}
	}

	public Staff addStaff(String id) throws PageNotFound {
		Optional<Staff> staffOptional = repository.findById(id);
		if (staffOptional.isPresent()) {
			Staff staff = staffOptional.get();
			repository.save(staff);
			return staff;
		} else {
			throw new PageNotFound("Staff not found with id: " + id);
		}
	}

	// ****** To Delete Specific Staff details Based on User Name *****\\
	public void deleteStaff(String username) throws PageNotFound {
		if (!repository.existsByUsername((String) username)) {
			throw new PageNotFound("Staff not found with id: " + username);
		}
		repository.deleteByUsername((String) username);
	}

//	public class DateConverter {
//	    public static Date convertToDate(String inputDate) throws ParseException {
//	        // Define the possible date formats
//	        String[] possibleFormats = {"yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "yyyy/MM/dd"};
//
//	        // Try parsing the input date with each format
//	        for (String format : possibleFormats) {
//	            SimpleDateFormat sdf = new SimpleDateFormat(format);
//	            try {
//	                Date date = sdf.parse(inputDate);
//	                return date;
//	            } catch (ParseException e) {
//	                // Ignore the exception and try the next format
//	            }
//	        }
//
//	        // If none of the formats match, throw an exception to indicate invalid date format
//	        throw new ParseException("Invalid date format", 0);
//	    }
//	    public static String convertToYYYYMMDD(Date date) {
//	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//	        return sdf.format(date);
//	    }
//	}

	// ****** To Get Specific Staff details Based on Role *****\\
//	public List<Staff> getStaffRole(String role) throws PageNotFound {
//		List<Staff> staff = repository.findByRole(role);
//		if (!staff.isEmpty()) {
//			return staff;
//		} else {
//			throw new PageNotFound("Staff not found with Role: " + role);
//		}
//	}
	}
	
	
