//package com.MHC.Project.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.MHC.Project.Model.PtStRecords;
//import com.MHC.Project.Model.PtStRequest;
//import com.MHC.Project.Repository.PtStRecordsRepo;
//import com.MHC.Project.Repository.PtStRepo;
//import com.MHC.Project.Response.DataResponse;
//
//@Service
//public class PtStService {
//
//	@Autowired
//	PtStRepo repository;
//	
//	@Autowired
//	PtStRecordsRepo psrepo;
//
//	public DataResponse<PtStRequest> savePsAssign(PtStRequest psAssign) {
//		// Check if a configuration with the given PID already exists
//		Optional<PtStRequest> existingConfig = repository.findByPID(psAssign.getPID());
//
//		if (existingConfig.isPresent()) {
//			// Configuration with the same PID already exists, update the other fields
//			PtStRequest existingConfigData = existingConfig.get();
//
//			// Update the non-null fields from the request
//			  if (psAssign.getPsychiatrists() != null) {
//	                // Merge the new psychiatrists with the existing ones
//	                Set<String> mergedPsychiatrists = new HashSet<>(Arrays.asList(existingConfigData.getPsychiatrists()));
//	                mergedPsychiatrists.addAll(Arrays.asList(psAssign.getPsychiatrists()));
//	                existingConfigData.setPsychiatrists(mergedPsychiatrists.toArray(new String[0]));
//	            }
//	            if (psAssign.getMedicalDoctor() != null) {
//	                // Merge the new medical doctors with the existing ones
//	                Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getMedicalDoctor()));
//	                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getMedicalDoctor()));
//	                existingConfigData.setMedicalDoctor(mergedMedicalDoctors.toArray(new String[0]));
//	            }
//	            if (psAssign.getNursePractitioner() != null) {
//	                Set<String> mergedNursePractitioners = new HashSet<>(Arrays.asList(existingConfigData.getNursePractitioner()));
//	                mergedNursePractitioners.addAll(Arrays.asList(psAssign.getNursePractitioner()));
//	                existingConfigData.setNursePractitioner(mergedNursePractitioners.toArray(new String[0]));
//	            }
//	            if (psAssign.getPhysicianAssistant() != null) {
//	                Set<String> mergedPhysicianAssistants = new HashSet<>(Arrays.asList(existingConfigData.getPhysicianAssistant()));
//	                mergedPhysicianAssistants.addAll(Arrays.asList(psAssign.getPhysicianAssistant()));
//	                existingConfigData.setPhysicianAssistant(mergedPhysicianAssistants.toArray(new String[0]));
//	            }
//	            if (psAssign.getPsychologist() != null) {
//	                Set<String> mergedPsychologists = new HashSet<>(Arrays.asList(existingConfigData.getPsychologist()));
//	                mergedPsychologists.addAll(Arrays.asList(psAssign.getPsychologist()));
//	                existingConfigData.setPsychologist(mergedPsychologists.toArray(new String[0]));
//	            }
//	            if (psAssign.getRegisteredNurses() != null) {
//	                Set<String> mergedRegisteredNurses = new HashSet<>(Arrays.asList(existingConfigData.getRegisteredNurses()));
//	                mergedRegisteredNurses.addAll(Arrays.asList(psAssign.getRegisteredNurses()));
//	                existingConfigData.setRegisteredNurses(mergedRegisteredNurses.toArray(new String[0]));
//	            }
//	            if (psAssign.getSocialWorkers() != null) {
//	                Set<String> mergedSocialWorkers = new HashSet<>(Arrays.asList(existingConfigData.getSocialWorkers()));
//	                mergedSocialWorkers.addAll(Arrays.asList(psAssign.getSocialWorkers()));
//	                existingConfigData.setSocialWorkers(mergedSocialWorkers.toArray(new String[0]));
//	            }
//	            if (psAssign.getActivityTherapist() != null) {
//	                Set<String> mergedActivityTherapists = new HashSet<>(Arrays.asList(existingConfigData.getActivityTherapist()));
//	                mergedActivityTherapists.addAll(Arrays.asList(psAssign.getActivityTherapist()));
//	                existingConfigData.setActivityTherapist(mergedActivityTherapists.toArray(new String[0]));
//	            }
//	            if (psAssign.getYogaTherapist() != null) {
//	                Set<String> mergedYogaTherapists = new HashSet<>(Arrays.asList(existingConfigData.getYogaTherapist()));
//	                mergedYogaTherapists.addAll(Arrays.asList(psAssign.getYogaTherapist()));
//	                existingConfigData.setYogaTherapist(mergedYogaTherapists.toArray(new String[0]));
//	            }
//	            if (psAssign.getMentalHealthWorkers() != null) {
//	                Set<String> mergedMentalHealthWorkers = new HashSet<>(Arrays.asList(existingConfigData.getMentalHealthWorkers()));
//	                mergedMentalHealthWorkers.addAll(Arrays.asList(psAssign.getMentalHealthWorkers()));
//	                existingConfigData.setMentalHealthWorkers(mergedMentalHealthWorkers.toArray(new String[0]));
//	            }
//	            if (psAssign.getrROfficer() != null) {
//	                Set<String> mergedRROfficers = new HashSet<>(Arrays.asList(existingConfigData.getrROfficer()));
//	                mergedRROfficers.addAll(Arrays.asList(psAssign.getrROfficer()));
//	                existingConfigData.setrROfficer(mergedRROfficers.toArray(new String[0]));
//	            }
//	            if (psAssign.getNurseManagers() != null) {
//	                Set<String> mergedNurseManagers = new HashSet<>(Arrays.asList(existingConfigData.getNurseManagers()));
//	                mergedNurseManagers.addAll(Arrays.asList(psAssign.getNurseManagers()));
//	                existingConfigData.setNurseManagers(mergedNurseManagers.toArray(new String[0]));
//	            }
//	            if (psAssign.getDirOfNursing() != null) {
//	                Set<String> mergedDirOfNursing = new HashSet<>(Arrays.asList(existingConfigData.getDirOfNursing()));
//	                mergedDirOfNursing.addAll(Arrays.asList(psAssign.getDirOfNursing()));
//	                existingConfigData.setDirOfNursing(mergedDirOfNursing.toArray(new String[0]));
//	            }
//	            if (psAssign.getExecutives() != null) {
//	                Set<String> mergedExecutives = new HashSet<>(Arrays.asList(existingConfigData.getExecutives()));
//	                mergedExecutives.addAll(Arrays.asList(psAssign.getExecutives()));
//	                existingConfigData.setExecutives(mergedExecutives.toArray(new String[0]));
//	            }
//	            if (psAssign.getHR() != null) {
//	                Set<String> mergedHR = new HashSet<>(Arrays.asList(existingConfigData.getHR()));
//	                mergedHR.addAll(Arrays.asList(psAssign.getHR()));
//	                existingConfigData.setHR(mergedHR.toArray(new String[0]));
//	            }
//	            if (psAssign.getQRDirector() != null) {
//	                Set<String> mergedQRDirector = new HashSet<>(Arrays.asList(existingConfigData.getQRDirector()));
//	                mergedQRDirector.addAll(Arrays.asList(psAssign.getQRDirector()));
//	                existingConfigData.setQRDirector(mergedQRDirector.toArray(new String[0]));
//	            }
//	            if (psAssign.getDirOfHIM() != null) {
//	                Set<String> mergedDirOfHIM = new HashSet<>(Arrays.asList(existingConfigData.getDirOfHIM()));
//	                mergedDirOfHIM.addAll(Arrays.asList(psAssign.getDirOfHIM()));
//	                existingConfigData.setDirOfHIM(mergedDirOfHIM.toArray(new String[0]));
//	            }
//	            if (psAssign.getRegDietitian() != null) {
//	                Set<String> mergedRegDietitian = new HashSet<>(Arrays.asList(existingConfigData.getRegDietitian()));
//	                mergedRegDietitian.addAll(Arrays.asList(psAssign.getRegDietitian()));
//	                existingConfigData.setRegDietitian(mergedRegDietitian.toArray(new String[0]));
//	            }
//  			    if (psAssign.getPID() != null) {
//	                Set<String> mergedPid = new HashSet<>(Arrays.asList(existingConfigData.getPID()));
//	                mergedPid.addAll(Arrays.asList(psAssign.getPID()));
//	                existingConfigData.setPID(mergedPid.toArray(new String[0]));
//	            }
//			
//			// Save the updated configuration
//			existingConfigData.setUpdatedAt(getTimeStamp());
//
//			   PtStRecords existingRecords = psrepo.findByPID(psAssign.getPID());
//	            if (existingRecords != null) {
//	                existingRecords.setPsychiatrists(existingConfigData.getPsychiatrists());
//	                existingRecords.setMedicalDoctor(existingConfigData.getMedicalDoctor());
//	                existingRecords.setNursePractitioner(existingConfigData.getNursePractitioner());
//	                existingRecords.setPsychiatrists(existingConfigData.getPsychiatrists());
//	                existingRecords.setPhysicianAssistant(existingConfigData.getPhysicianAssistant());
//	                existingRecords.setPsychologist(existingConfigData.getPsychologist());
//	                existingRecords.setRegisteredNurses(existingConfigData.getRegisteredNurses());
//	                existingRecords.setSocialWorkers(existingConfigData.getSocialWorkers());
//	                existingRecords.setActivityTherapist(existingConfigData.getActivityTherapist());
//	                existingRecords.setYogaTherapist(existingConfigData.getYogaTherapist());
//	                existingRecords.setMentalHealthWorkers(existingConfigData.getMentalHealthWorkers());
//	                existingRecords.setrROfficer(existingConfigData.getrROfficer());
//	                existingRecords.setDirOfNursing(existingConfigData.getDirOfNursing());
//	                existingRecords.setExecutives(existingConfigData.getExecutives());
//	                existingRecords.setQRDirector(existingConfigData.getQRDirector());
//	                existingRecords.setDirOfHIM(existingConfigData.getDirOfHIM());
//	                existingRecords.setRegDietitian(existingConfigData.getRegDietitian());
//	                
//	                // Update other fields as needed
//	                psrepo.save(existingRecords); // Update the PtStRecords record
//	            }
//	             repository.save(existingConfigData);
//	            DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0270");
//	            return new DataResponse<>(response, existingConfigData);
//	        } else {
//			// Configuration with the same PID does not exist, create a new record
//			String uid = PatientService.generateUID();
//			psAssign.setId(uid);
//			psAssign.setCreatedAt(getTimeStamp());
//			PtStRecords psa1 = new PtStRecords();
//			psa1.setId(uid);
//			psa1.setPID(psAssign.getPID());
//			psa1.setPsychiatrists(psAssign.getPsychiatrists());
//			psa1.setMedicalDoctor(psAssign.getMedicalDoctor());
//			psa1.setNursePractitioner(psAssign.getNursePractitioner());
//			psa1.setPhysicianAssistant(psAssign.getPhysicianAssistant());
//			psa1.setPsychologist(psAssign.getPsychologist());
//			psa1.setRegisteredNurses(psAssign.getRegisteredNurses());
//			psa1.setSocialWorkers(psAssign.getSocialWorkers());
//			psa1.setActivityTherapist(psAssign.getActivityTherapist());
//			psa1.setYogaTherapist(psAssign.getYogaTherapist());
//			psa1.setMentalHealthWorkers(psAssign.getMentalHealthWorkers());
//			psa1.setrROfficer(psAssign.getrROfficer());
//			psa1.setNurseManagers(psAssign.getNurseManagers());
//			psa1.setDirOfNursing(psAssign.getDirOfNursing());
//			psa1.setExecutives(psAssign.getExecutives());
//			psa1.setHR(psAssign.getHR());
//			psa1.setQRDirector(psAssign.getQRDirector());
//			psa1.setDirOfHIM(psAssign.getDirOfHIM());
//			psa1.setRegDietitian(psAssign.getRegDietitian());
//				psrepo.save(psa1);
//				repository.save(psAssign);
//				DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0271");
//		        return new DataResponse<>(response, psAssign);
//		    }
//		
//	}
//
//	// ***************** Get All PatientStaffAssignment ****************\\
//	public DataResponse<List<PtStRecords>> getAllPatientStaffAssignment() {
//        List<PtStRecords> records = psrepo.findAll();
//        
//        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
//        return new DataResponse<>(response, records);
//    }
//
//	public DataResponse<List<String>> getUniquePatientIdsByStaffId(String staffId) {
//        List<String> uniquePatientIds = new ArrayList<>();
//
//        // Fetch records based on the staff ID and add unique patient IDs to the list
//        List<PtStRecords> allRecords = psrepo.findAll();
//        boolean foundStaff = false; // Flag to check if staffId was found
//        for (PtStRecords record : allRecords) {
//            if (containsIdInArray(record.getPsychiatrists(), staffId) ||
//                containsIdInArray(record.getMedicalDoctor(), staffId) ||
//                containsIdInArray(record.getNursePractitioner(), staffId) ||
//                containsIdInArray(record.getPhysicianAssistant(), staffId) ||
//                containsIdInArray(record.getPsychologist(), staffId) ||
//            	containsIdInArray(record.getRegisteredNurses(), staffId) ||
//				containsIdInArray(record.getSocialWorkers(), staffId) ||
//				containsIdInArray(record.getActivityTherapist(), staffId) ||
//				containsIdInArray(record.getYogaTherapist(), staffId) ||
//				containsIdInArray(record.getMentalHealthWorkers(), staffId) ||
//				containsIdInArray(record.getrROfficer(), staffId) ||
//				containsIdInArray(record.getNurseManagers(), staffId) ||
//				containsIdInArray(record.getDirOfNursing(), staffId) ||
//				containsIdInArray(record.getExecutives(), staffId) ||
//				containsIdInArray(record.getHR(), staffId) ||
//				containsIdInArray(record.getQRDirector(), staffId) ||
//				containsIdInArray(record.getDirOfHIM(), staffId) ||
//				containsIdInArray(record.getRegDietitian(), staffId))
//				   
//            {
//                foundStaff = true; // StaffId found
//                uniquePatientIds.addAll(Arrays.asList(record.getPID()));
//            }
//        }
//
//        if (!foundStaff) {
//        	DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0173");
//            return new DataResponse<>(response, uniquePatientIds);
//        }
//
//        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
//        return new DataResponse<>(response, uniquePatientIds);
//    }
//
//    public DataResponse<List<PtStRecords>> getPtStRecordsByStaffId(String staffId) {
//        List<PtStRecords> records = new ArrayList<>();
//
//        // Fetch records based on the staff ID and add them to the list
//        List<PtStRecords> allRecords = psrepo.findAll();
//        boolean foundStaff = false; // Flag to check if staffId was found
//        for (PtStRecords record : allRecords) {
//            if (containsStaffId(record, staffId)) {
//                foundStaff = true; // StaffId found
//                records.add(record);
//            }
//        }
//
//        if (!foundStaff) {
//        	DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0173");
//            return new DataResponse<>(response, records);
//        }
//
//        DataResponse.Response response = DataResponse.createVerifyResponse("MHC - 0272");
//        return new DataResponse<>(response, records);
//    }
//	
//	private boolean containsStaffId(PtStRecords record, String staffId) {
//	    // Check if the staffId exists in any of the staff arrays of the record
//	    return containsIdInArray(record.getPsychiatrists(), staffId) ||
//	           containsIdInArray(record.getMedicalDoctor(), staffId) ||
//	           containsIdInArray(record.getNursePractitioner(), staffId) ||
//	           containsIdInArray(record.getPhysicianAssistant(), staffId) ||
//	           containsIdInArray(record.getPsychologist(), staffId) ||
//           	   containsIdInArray(record.getRegisteredNurses(), staffId) ||
//		   	   containsIdInArray(record.getSocialWorkers(), staffId) ||
//			   containsIdInArray(record.getActivityTherapist(), staffId) ||
//			   containsIdInArray(record.getYogaTherapist(), staffId) ||
//			   containsIdInArray(record.getMentalHealthWorkers(), staffId) ||
//			   containsIdInArray(record.getrROfficer(), staffId) ||
//			   containsIdInArray(record.getNurseManagers(), staffId) ||
//			   containsIdInArray(record.getDirOfNursing(), staffId) ||
//		 	   containsIdInArray(record.getExecutives(), staffId) ||
//			   containsIdInArray(record.getHR(), staffId) ||
//			   containsIdInArray(record.getQRDirector(), staffId) ||
//			   containsIdInArray(record.getDirOfHIM(), staffId) ||
//			   containsIdInArray(record.getRegDietitian(), staffId) ||
//	           false; // Return false if staffId is not found in any array
//	}
//
//	private boolean containsIdInArray(String[] array, String staffId) {
//	    if (array == null) {
//	        return false;
//	    }
//	    for (String id : array) {
//	        if (id.equals(staffId)) {
//	            return true;
//	        }
//	    }
//	    return false;
//	}
//
//	public DataResponse<Map<String, List<String>>> getUniqueStaffDetailsByPid(String id) {
//        Map<String, List<String>> staffDetails = new HashMap<>();
//
//        // Fetch records based on the PID
//        List<PtStRecords> allRecords = psrepo.findAll();
//        boolean foundPid = false; // Flag to check if the given PID was found
//        for (PtStRecords record : allRecords) {
//            if (containsIdInArray(record.getPID(), id)) {
//                foundPid = true; // PID found
//                addStaffDetails(record.getPsychiatrists(), "Psychiatrist", staffDetails);
//                addStaffDetails(record.getMedicalDoctor(), "Medical Doctor", staffDetails);
//                addStaffDetails(record.getNursePractitioner(), "Nurse Practitioner", staffDetails);
//                addStaffDetails(record.getPhysicianAssistant(), "Physician Assistant", staffDetails);
//                addStaffDetails(record.getPsychologist(), "Psychologist", staffDetails);
//                addStaffDetails(record.getRegisteredNurses(), "Registered Nurses", staffDetails);
//                addStaffDetails(record.getSocialWorkers(), "Social Workers", staffDetails);
//                addStaffDetails(record.getActivityTherapist(), "Activity Therapist", staffDetails);
//                addStaffDetails(record.getYogaTherapist(), "Yoga Therapist", staffDetails);
//                addStaffDetails(record.getMentalHealthWorkers(), "Mental Health Workers", staffDetails);
//                addStaffDetails(record.getrROfficer(), "Recipient Rights Officer", staffDetails);
//                addStaffDetails(record.getNurseManagers(), "Nurse Managers", staffDetails);
//                addStaffDetails(record.getDirOfNursing(), "Director of Nursing", staffDetails);
//                addStaffDetails(record.getExecutives(), "Executives", staffDetails);
//                addStaffDetails(record.getHR(), "Human Resources", staffDetails);
//                addStaffDetails(record.getQRDirector(), "Quality and Risk Director", staffDetails);
//                addStaffDetails(record.getDirOfHIM(), "Director of HIM", staffDetails);
//                addStaffDetails(record.getRegDietitian(), "Registered Dietitian", staffDetails);
// 
//                // Add similar lines for other staff roles
//            }
//        }
//
//        if (!foundPid) {
//        	DataResponse.Response errorResponse = DataResponse.createVerifyResponse("MHC - 0174");
//            return new DataResponse<>(errorResponse, staffDetails);
//        }
//
//        DataResponse.Response successResponse = DataResponse.createVerifyResponse("MHC - 0272");
//        return new DataResponse<>(successResponse, staffDetails);
//    }
//
//	private void addStaffDetails(String[] staffIds, String role, Map<String, List<String>> staffDetails) {
//	    if (staffIds != null) {
//	        staffDetails.putIfAbsent(role, new ArrayList<>()); // Create the list if not already present
//	        staffDetails.get(role).addAll(Arrays.asList(staffIds)); // Add staff IDs to the list
//	    }
//	}
//	
//	private String getTimeStamp() {
//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
//		String time = now.format(format);
//		return time;
//	}
//	
//}


package com.MHC.Project.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Model.PtStRecords;
import com.MHC.Project.Model.PtStRequest;
import com.MHC.Project.Repository.PtStRecordsRepo;
import com.MHC.Project.Repository.PtStRepo;

@Service
public class PtStService {

	@Autowired
	PtStRepo repository;
	
	@Autowired
	PtStRecordsRepo psrepo;

	public PtStRequest savePsAssign(PtStRequest psAssign) {
		// Check if a configuration with the given PID already exists
		Optional<PtStRequest> existingConfig = repository.findByPID(psAssign.getPID());

		if (existingConfig.isPresent()) {
			// Configuration with the same PID already exists, update the other fields
			PtStRequest existingConfigData = existingConfig.get();

//			// Update the non-null fields from the request
			  if (psAssign.getPsychiatrists() != null) {
	                // Merge the new psychiatrists with the existing ones
	                Set<String> mergedPsychiatrists = new HashSet<>(Arrays.asList(existingConfigData.getPsychiatrists()));
	                mergedPsychiatrists.addAll(Arrays.asList(psAssign.getPsychiatrists()));
	                existingConfigData.setPsychiatrists(mergedPsychiatrists.toArray(new String[0]));
	            }
	            if (psAssign.getMedicalDoctor() != null) {
	                // Merge the new medical doctors with the existing ones
	                Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getMedicalDoctor()));
	                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getMedicalDoctor()));
	                existingConfigData.setMedicalDoctor(mergedMedicalDoctors.toArray(new String[0]));
	            }
	            if (psAssign.getNursePractitioner() != null) {
	            	 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getNursePractitioner()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getNursePractitioner()));
		                existingConfigData.setNursePractitioner(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getPhysicianAssistant() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getPhysicianAssistant()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getPhysicianAssistant()));
		                existingConfigData.setPhysicianAssistant(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getPsychologist() != null) {
					  Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getPsychologist()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getPsychologist()));
		                existingConfigData.setPsychologist(mergedMedicalDoctors.toArray(new String[0]));
		            }
				if (psAssign.getRegisteredNurses() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getRegisteredNurses()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getRegisteredNurses()));
		                existingConfigData.setRegisteredNurses(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getSocialWorkers() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getSocialWorkers()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getSocialWorkers()));
		                existingConfigData.setSocialWorkers(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getActivityTherapist() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getActivityTherapist()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getActivityTherapist()));
		                existingConfigData.setActivityTherapist(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getYogaTherapist() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getYogaTherapist()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getYogaTherapist()));
		                existingConfigData.setYogaTherapist(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getMentalHealthWorkers() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getMentalHealthWorkers()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getMentalHealthWorkers()));
		                existingConfigData.setMentalHealthWorkers(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getrROfficer() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getrROfficer()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getrROfficer()));
		                existingConfigData.setrROfficer(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getNurseManagers() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getNurseManagers()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getNurseManagers()));
		                existingConfigData.setNurseManagers(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getDirOfNursing() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getDirOfNursing()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getDirOfNursing()));
		                existingConfigData.setDirOfNursing(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getExecutives() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getExecutives()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getExecutives()));
		                existingConfigData.setExecutives(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getHR() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getHR()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getHR()));
		                existingConfigData.setHR(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getQRDirector() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getQRDirector()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getQRDirector()));
		                existingConfigData.setQRDirector(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getDirOfHIM() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getDirOfHIM()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getDirOfHIM()));
		                existingConfigData.setDirOfHIM(mergedMedicalDoctors.toArray(new String[0]));
				}
				if (psAssign.getRegDietitian() != null) {
					 Set<String> mergedMedicalDoctors = new HashSet<>(Arrays.asList(existingConfigData.getRegDietitian()));
		                mergedMedicalDoctors.addAll(Arrays.asList(psAssign.getRegDietitian()));
		                existingConfigData.setRegDietitian(mergedMedicalDoctors.toArray(new String[0]));
				}
  			    if (psAssign.getPID() != null) {
	                Set<String> mergedPid = new HashSet<>(Arrays.asList(existingConfigData.getPID()));
	                mergedPid.addAll(Arrays.asList(psAssign.getPID()));
	                existingConfigData.setPID(mergedPid.toArray(new String[0]));
	            }
			
			// Save the updated configuration
			existingConfigData.setUpdatedAt(getTimeStamp());

			   PtStRecords existingRecords = psrepo.findByPID(psAssign.getPID());
			   System.out.println("1");
	            if (existingRecords != null) {
	            	System.out.println("2");
	                existingRecords.setPsychiatrists(existingConfigData.getPsychiatrists());
	                existingRecords.setMedicalDoctor(existingConfigData.getMedicalDoctor());
	                existingRecords.setNursePractitioner(existingConfigData.getNursePractitioner());
	                existingRecords.setNurseManagers(existingConfigData.getNurseManagers());
	                existingRecords.setPhysicianAssistant(existingConfigData.getPhysicianAssistant());
	                existingRecords.setPsychologist(existingConfigData.getPsychologist());
	                existingRecords.setRegisteredNurses(existingConfigData.getRegisteredNurses());
	                existingRecords.setSocialWorkers(existingConfigData.getSocialWorkers());
	                existingRecords.setActivityTherapist(existingConfigData.getActivityTherapist());
	                existingRecords.setYogaTherapist(existingConfigData.getYogaTherapist());
	                existingRecords.setMentalHealthWorkers(existingConfigData.getMentalHealthWorkers());
	                existingRecords.setrROfficer(existingConfigData.getrROfficer());
	                existingRecords.setHR(existingConfigData.getHR());
	                existingRecords.setDirOfNursing(existingConfigData.getDirOfNursing());
	                existingRecords.setExecutives(existingConfigData.getExecutives());
	                existingRecords.setQRDirector(existingConfigData.getQRDirector());
	                existingRecords.setDirOfHIM(existingConfigData.getDirOfHIM());
	                existingRecords.setRegDietitian(existingConfigData.getRegDietitian());
	                
	                psrepo.save(existingRecords); // Update the PtStRecords record
	            }
			return repository.save(existingConfigData);
			
			
		} else {
			// Configuration with the same PID does not exist, create a new record
			String uid = PatientService.generateUID();
			psAssign.setId(uid);
			psAssign.setCreatedAt(getTimeStamp());
			PtStRecords psa1 = new PtStRecords();
			psa1.setId(uid);
			psa1.setCreatedAt(getTimeStamp());
			psa1.setPID(psAssign.getPID());
			psa1.setPsychiatrists(psAssign.getPsychiatrists());
			psa1.setMedicalDoctor(psAssign.getMedicalDoctor());
			psa1.setNursePractitioner(psAssign.getNursePractitioner());
			psa1.setPhysicianAssistant(psAssign.getPhysicianAssistant());
			psa1.setPsychologist(psAssign.getPsychologist());
			psa1.setRegisteredNurses(psAssign.getRegisteredNurses());
			psa1.setSocialWorkers(psAssign.getSocialWorkers());
			psa1.setActivityTherapist(psAssign.getActivityTherapist());
			psa1.setYogaTherapist(psAssign.getYogaTherapist());
			psa1.setMentalHealthWorkers(psAssign.getMentalHealthWorkers());
			psa1.setrROfficer(psAssign.getrROfficer());
			psa1.setNurseManagers(psAssign.getNurseManagers());
			psa1.setDirOfNursing(psAssign.getDirOfNursing());
			psa1.setExecutives(psAssign.getExecutives());
			psa1.setHR(psAssign.getHR());
			psa1.setQRDirector(psAssign.getQRDirector());
			psa1.setDirOfHIM(psAssign.getDirOfHIM());
			psa1.setRegDietitian(psAssign.getRegDietitian());
				psrepo.save(psa1);
			
		}
		return repository.save(psAssign);
	}

	// ***************** Get All PatientStaffAssignment ****************\\
	public List<PtStRecords> getAllPatientStaffAssignment() {
		return psrepo.findAll();
	}

	  public List<String> getUniquePatientIdsByStaffId(String staffId) {
	        Set<String> uniquePatientIds = new HashSet<>();
	        
	        // Fetch records based on the staff ID and add unique patient IDs to the set
	        List<PtStRecords> allRecords = psrepo.findAll();
	        for (PtStRecords record : allRecords) {
	            if (containsIdInArray(record.getPsychiatrists(), staffId) ||
	                containsIdInArray(record.getMedicalDoctor(), staffId) ||
	                containsIdInArray(record.getNursePractitioner(), staffId) ||
	                containsIdInArray(record.getPhysicianAssistant(), staffId) ||
	                containsIdInArray(record.getPsychologist(), staffId) ||
	            	containsIdInArray(record.getRegisteredNurses(), staffId) ||
					containsIdInArray(record.getSocialWorkers(), staffId) ||
					containsIdInArray(record.getActivityTherapist(), staffId) ||
					containsIdInArray(record.getYogaTherapist(), staffId) ||
					containsIdInArray(record.getMentalHealthWorkers(), staffId) ||
					containsIdInArray(record.getrROfficer(), staffId) ||
					containsIdInArray(record.getNurseManagers(), staffId) ||
					containsIdInArray(record.getDirOfNursing(), staffId) ||
					containsIdInArray(record.getExecutives(), staffId) ||
					containsIdInArray(record.getHR(), staffId) ||
					containsIdInArray(record.getQRDirector(), staffId) ||
					containsIdInArray(record.getDirOfHIM(), staffId) ||
					containsIdInArray(record.getRegDietitian(), staffId))
					   {
	                uniquePatientIds.addAll(Arrays.asList(record.getPID()));
	            }
	        }
	        
	        return new ArrayList<>(uniquePatientIds);
	    }
	  
	  public List<PtStRecords> getPtStRecordsByStaffId(String staffId) {
		    List<PtStRecords> records = new ArrayList<>();
		    
		    // Fetch records based on the staff ID and add them to the list
		    List<PtStRecords> allRecords = psrepo.findAll();
		    for (PtStRecords record : allRecords) {
		        if (containsStaffId(record, staffId)) {
		            records.add(record);
		        }
		    }
		    
		    return records;
		}
	
	private boolean containsStaffId(PtStRecords record, String staffId) {
	    // Check if the staffId exists in any of the staff arrays of the record
	    return containsIdInArray(record.getPsychiatrists(), staffId) ||
	           containsIdInArray(record.getMedicalDoctor(), staffId) ||
	           containsIdInArray(record.getNursePractitioner(), staffId) ||
	           containsIdInArray(record.getPhysicianAssistant(), staffId) ||
	           containsIdInArray(record.getPsychologist(), staffId) ||
	           containsIdInArray(record.getRegisteredNurses(), staffId) ||
		   	   containsIdInArray(record.getSocialWorkers(), staffId) ||
			   containsIdInArray(record.getActivityTherapist(), staffId) ||
			   containsIdInArray(record.getYogaTherapist(), staffId) ||
			   containsIdInArray(record.getMentalHealthWorkers(), staffId) ||
			   containsIdInArray(record.getrROfficer(), staffId) ||
			   containsIdInArray(record.getNurseManagers(), staffId) ||
			   containsIdInArray(record.getDirOfNursing(), staffId) ||
		 	   containsIdInArray(record.getExecutives(), staffId) ||
			   containsIdInArray(record.getHR(), staffId) ||
			   containsIdInArray(record.getQRDirector(), staffId) ||
			   containsIdInArray(record.getDirOfHIM(), staffId) ||
			   containsIdInArray(record.getRegDietitian(), staffId) ||
	           false; // Return false if staffId is not found in any array
	}

	private boolean containsIdInArray(String[] array, String staffId) {
	    if (array == null) {
	        return false;
	    }
	    for (String id : array) {
	        if (id.equals(staffId)) {
	            return true;
	        }
	    }
	    return false;
	}

	public Map<String, List<String>> getUniqueStaffDetailsByPid(String pid) {
	    Map<String, List<String>> staffDetails = new HashMap<>();

	    // Fetch records based on the PID
	    List<PtStRecords> allRecords = psrepo.findAll();
	    for (PtStRecords record : allRecords) {
	        if (containsIdInArray(record.getPID(), pid)) {
	            addStaffDetails(record.getPsychiatrists(), "psychiatrists", staffDetails);
	            addStaffDetails(record.getMedicalDoctor(), "medicalDoctor", staffDetails);
	            addStaffDetails(record.getNursePractitioner(), "nursePractitioner", staffDetails);
	            addStaffDetails(record.getPhysicianAssistant(), "physicianAssistant", staffDetails);
                addStaffDetails(record.getPsychologist(), "psychologist", staffDetails);
                addStaffDetails(record.getRegisteredNurses(), "registeredNurses", staffDetails);
                addStaffDetails(record.getSocialWorkers(), "socialWorkers", staffDetails);
                addStaffDetails(record.getActivityTherapist(), "activityTherapist", staffDetails);
                addStaffDetails(record.getYogaTherapist(), "yogaTherapist", staffDetails);
                addStaffDetails(record.getMentalHealthWorkers(), "mentalHealthWorkers", staffDetails);
                addStaffDetails(record.getrROfficer(), "rROfficer", staffDetails);
                addStaffDetails(record.getNurseManagers(), "nurseManagers", staffDetails);
                addStaffDetails(record.getDirOfNursing(), "dirOfNursing", staffDetails);
                addStaffDetails(record.getExecutives(), "executives", staffDetails);
                addStaffDetails(record.getHR(), "hr", staffDetails);
                addStaffDetails(record.getQRDirector(), "qrdirector", staffDetails);
                addStaffDetails(record.getDirOfHIM(), "dirOfHIM", staffDetails);
                addStaffDetails(record.getRegDietitian(), "regDietitian", staffDetails);
	        }
	    }
	    return staffDetails;
	}

//	private void addStaffDetails(String[] staffIds, String role, Map<String, List<String>> staffDetails) {
//	    if (staffIds != null) {
//	        staffDetails.putIfAbsent(role, new ArrayList<>()); // Create the list if not already present
//	        staffDetails.get(role).addAll(Arrays.asList(staffIds)); // Add staff IDs to the list
//	    }
//	}
	
	private void addStaffDetails(String[] staffIds, String role, Map<String, List<String>> staffDetails) {
	    if (staffIds != null) {
	        staffDetails.putIfAbsent(role, new ArrayList<>()); // Create the list if not already present

	        Set<String> uniqueStaffIds = new HashSet<>(staffDetails.get(role));
	        uniqueStaffIds.addAll(Arrays.asList(staffIds));

	        staffDetails.put(role, new ArrayList<>(uniqueStaffIds));
	    }
	}
	
	private String getTimeStamp() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String time = now.format(format);
		return time;
	}
}


