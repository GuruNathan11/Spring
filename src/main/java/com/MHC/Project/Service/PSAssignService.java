//package com.MHC.Project.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.MHC.Project.Model.PatientStaffAssignment;
//import com.MHC.Project.Repository.PSAssignRepository;
//
//@Service
//public class PSAssignService {
//
//	@Autowired
//	PSAssignRepository repository;
//
//	public PatientStaffAssignment savePsAssign(PatientStaffAssignment psAssign) {
//		// Check if a configuration with the given PID already exists
//		Optional<PatientStaffAssignment> existingConfig = repository.findByPID(psAssign.getPID());
//
//		if (existingConfig.isPresent()) {
//			// Configuration with the same PID already exists, update the other fields
//			PatientStaffAssignment existingConfigData = existingConfig.get();
//
//			// Update the non-null fields from the request
//			if (psAssign.getPsychiatrists1() != null) {
//				existingConfigData.setPsychiatrists1(psAssign.getPsychiatrists1());
//			}
//			if (psAssign.getPsychiatrists2() != null) {
//				existingConfigData.setPsychiatrists2(psAssign.getPsychiatrists2());
//			}
//			if (psAssign.getPsychiatrists3() != null) {
//				existingConfigData.setPsychiatrists3(psAssign.getPsychiatrists3());
//			}
//			if (psAssign.getPsychiatrists4() != null) {
//				existingConfigData.setPsychiatrists4(psAssign.getPsychiatrists4());
//			}
//			if (psAssign.getPsychiatrists5() != null) {
//				existingConfigData.setPsychiatrists5(psAssign.getPsychiatrists5());
//			}
//			
//			if (psAssign.getMedicalDoctor1() != null) {
//				existingConfigData.setMedicalDoctor1(psAssign.getMedicalDoctor1());
//			}
//			if (psAssign.getMedicalDoctor2() != null) {
//				existingConfigData.setMedicalDoctor2(psAssign.getMedicalDoctor2());
//			}
//			if (psAssign.getMedicalDoctor3() != null) {
//				existingConfigData.setMedicalDoctor3(psAssign.getMedicalDoctor3());
//			}
//			if (psAssign.getMedicalDoctor4() != null) {
//				existingConfigData.setMedicalDoctor4(psAssign.getMedicalDoctor4());
//			}
//			if (psAssign.getMedicalDoctor5() != null) {
//				existingConfigData.setMedicalDoctor5(psAssign.getMedicalDoctor5());
//			}
//			
//			if (psAssign.getNursePractitioner1() != null) {
//				existingConfigData.setNursePractitioner1(psAssign.getNursePractitioner1());
//			}
//			if (psAssign.getNursePractitioner2() != null) {
//				existingConfigData.setNursePractitioner2(psAssign.getNursePractitioner2());
//			}
//			if (psAssign.getNursePractitioner3() != null) {
//				existingConfigData.setNursePractitioner3(psAssign.getNursePractitioner3());
//			}
//			if (psAssign.getNursePractitioner4() != null) {
//				existingConfigData.setNursePractitioner4(psAssign.getNursePractitioner4());
//			}
//			if (psAssign.getNursePractitioner5() != null) {
//				existingConfigData.setNursePractitioner5(psAssign.getNursePractitioner5());
//			}
//			
//			if (psAssign.getPhysicianAssistant1() != null) {
//				existingConfigData.setPhysicianAssistant1(psAssign.getPhysicianAssistant1());
//			}
//			if (psAssign.getPhysicianAssistant2() != null) {
//				existingConfigData.setPhysicianAssistant2(psAssign.getPhysicianAssistant2());
//			}
//			if (psAssign.getPhysicianAssistant3() != null) {
//				existingConfigData.setPhysicianAssistant3(psAssign.getPhysicianAssistant3());
//			}
//			if (psAssign.getPhysicianAssistant4() != null) {
//				existingConfigData.setPhysicianAssistant4(psAssign.getPhysicianAssistant4());
//			}
//			if (psAssign.getPhysicianAssistant5() != null) {
//				existingConfigData.setPhysicianAssistant5(psAssign.getPhysicianAssistant5());
//			}
//			
//			if (psAssign.getPsychologist1() != null) {
//				existingConfigData.setPsychologist1(psAssign.getPsychologist1());
//			}
//			if (psAssign.getPsychologist2() != null) {
//				existingConfigData.setPsychologist2(psAssign.getPsychologist2());
//			}
//			if (psAssign.getPsychologist3() != null) {
//				existingConfigData.setPsychologist3(psAssign.getPsychologist3());
//			}
//			if (psAssign.getPsychologist4() != null) {
//				existingConfigData.setPsychologist4(psAssign.getPsychologist4());
//			}
//			if (psAssign.getPsychologist5() != null) {
//				existingConfigData.setPsychologist5(psAssign.getPsychologist5());
//			}
//			
//			if (psAssign.getRegisteredNurses1() != null) {
//				existingConfigData.setRegisteredNurses1(psAssign.getRegisteredNurses1());
//			}
//			if (psAssign.getRegisteredNurses2() != null) {
//				existingConfigData.setRegisteredNurses2(psAssign.getRegisteredNurses2());
//			}
//			if (psAssign.getRegisteredNurses3() != null) {
//				existingConfigData.setRegisteredNurses3(psAssign.getRegisteredNurses3());
//			}
//			if (psAssign.getRegisteredNurses4() != null) {
//				existingConfigData.setRegisteredNurses4(psAssign.getRegisteredNurses4());
//			}
//			if (psAssign.getRegisteredNurses5() != null) {
//				existingConfigData.setRegisteredNurses5(psAssign.getRegisteredNurses5());
//			}
//			
//			if (psAssign.getSocialWorkers1() != null) {
//				existingConfigData.setSocialWorkers1(psAssign.getSocialWorkers1());
//			}
//			if (psAssign.getSocialWorkers2() != null) {
//				existingConfigData.setSocialWorkers2(psAssign.getSocialWorkers2());
//			}
//			if (psAssign.getSocialWorkers3() != null) {
//				existingConfigData.setSocialWorkers3(psAssign.getSocialWorkers3());
//			}
//			if (psAssign.getSocialWorkers4() != null) {
//				existingConfigData.setSocialWorkers4(psAssign.getSocialWorkers4());
//			}
//			if (psAssign.getSocialWorkers5() != null) {
//				existingConfigData.setSocialWorkers5(psAssign.getSocialWorkers5());
//			}
//			
//			if (psAssign.getActivityTherapist1() != null) {
//				existingConfigData.setActivityTherapist1(psAssign.getActivityTherapist1());
//			}
//			if (psAssign.getActivityTherapist2() != null) {
//				existingConfigData.setActivityTherapist2(psAssign.getActivityTherapist2());
//			}
//			if (psAssign.getActivityTherapist3() != null) {
//				existingConfigData.setActivityTherapist3(psAssign.getActivityTherapist3());
//			}
//			if (psAssign.getActivityTherapist4() != null) {
//				existingConfigData.setActivityTherapist4(psAssign.getActivityTherapist4());
//			}
//			if (psAssign.getActivityTherapist5() != null) {
//				existingConfigData.setActivityTherapist5(psAssign.getActivityTherapist5());
//			}
//			
//			if (psAssign.getYogaTherapist1() != null) {
//				existingConfigData.setYogaTherapist1(psAssign.getYogaTherapist1());
//			}
//			if (psAssign.getYogaTherapist2() != null) {
//				existingConfigData.setYogaTherapist2(psAssign.getYogaTherapist2());
//			}
//			if (psAssign.getYogaTherapist3() != null) {
//				existingConfigData.setYogaTherapist3(psAssign.getYogaTherapist3());
//			}
//			if (psAssign.getYogaTherapist4() != null) {
//				existingConfigData.setYogaTherapist4(psAssign.getYogaTherapist4());
//			}
//			if (psAssign.getYogaTherapist5() != null) {
//				existingConfigData.setYogaTherapist5(psAssign.getYogaTherapist5());
//			}
//			
//			if (psAssign.getMentalHealthWorkers1() != null) {
//				existingConfigData.setMentalHealthWorkers1(psAssign.getMentalHealthWorkers1());
//			}
//			if (psAssign.getMentalHealthWorkers2() != null) {
//				existingConfigData.setMentalHealthWorkers2(psAssign.getMentalHealthWorkers2());
//			}
//			if (psAssign.getMentalHealthWorkers3() != null) {
//				existingConfigData.setMentalHealthWorkers3(psAssign.getMentalHealthWorkers3());
//			}
//			if (psAssign.getMentalHealthWorkers4() != null) {
//				existingConfigData.setMentalHealthWorkers4(psAssign.getMentalHealthWorkers4());
//			}
//			if (psAssign.getMentalHealthWorkers5() != null) {
//				existingConfigData.setMentalHealthWorkers5(psAssign.getMentalHealthWorkers5());
//			}
//
//			if (psAssign.getrROfficer1() != null) {
//				existingConfigData.setrROfficer1(psAssign.getrROfficer1());
//			}
//			if (psAssign.getrROfficer2() != null) {
//				existingConfigData.setrROfficer2(psAssign.getrROfficer2());
//			}
//			if (psAssign.getrROfficer3() != null) {
//				existingConfigData.setrROfficer3(psAssign.getrROfficer3());
//			}
//			if (psAssign.getrROfficer4() != null) {
//				existingConfigData.setrROfficer4(psAssign.getrROfficer4());
//			}
//			if (psAssign.getrROfficer5() != null) {
//				existingConfigData.setrROfficer5(psAssign.getrROfficer5());
//			}
//			
//			if (psAssign.getNurseManagers1() != null) {
//				existingConfigData.setNurseManagers1(psAssign.getNurseManagers1());
//			}
//			if (psAssign.getNurseManagers2() != null) {
//				existingConfigData.setNurseManagers2(psAssign.getNurseManagers2());
//			}
//			if (psAssign.getNurseManagers3() != null) {
//				existingConfigData.setNurseManagers3(psAssign.getNurseManagers3());
//			}
//			if (psAssign.getNurseManagers4() != null) {
//				existingConfigData.setNurseManagers4(psAssign.getNurseManagers4());
//			}
//			if (psAssign.getNurseManagers5() != null) {
//				existingConfigData.setNurseManagers5(psAssign.getNurseManagers5());
//			}
//			
//			if (psAssign.getDirOfNursing1() != null) {
//				existingConfigData.setDirOfNursing1(psAssign.getDirOfNursing1());
//			}
//			if (psAssign.getDirOfNursing2() != null) {
//				existingConfigData.setDirOfNursing2(psAssign.getDirOfNursing2());
//			}
//			if (psAssign.getDirOfNursing3() != null) {
//				existingConfigData.setDirOfNursing3(psAssign.getDirOfNursing3());
//			}
//			if (psAssign.getDirOfNursing4() != null) {
//				existingConfigData.setDirOfNursing4(psAssign.getDirOfNursing4());
//			}
//			if (psAssign.getDirOfNursing5() != null) {
//				existingConfigData.setDirOfNursing5(psAssign.getDirOfNursing5());
//			}
//			
//			if (psAssign.getExecutives1() != null) {
//				existingConfigData.setExecutives1(psAssign.getExecutives1());
//			}
//			if (psAssign.getExecutives2() != null) {
//				existingConfigData.setExecutives2(psAssign.getExecutives2());
//			}
//			if (psAssign.getExecutives3() != null) {
//				existingConfigData.setExecutives3(psAssign.getExecutives3());
//			}
//			if (psAssign.getExecutives4() != null) {
//				existingConfigData.setExecutives4(psAssign.getExecutives4());
//			}
//			if (psAssign.getExecutives5() != null) {
//				existingConfigData.setExecutives5(psAssign.getExecutives5());
//			}
//			
//			if (psAssign.getHR1() != null) {
//				existingConfigData.setHR1(psAssign.getHR1());
//			}
//			if (psAssign.getHR2() != null) {
//				existingConfigData.setHR2(psAssign.getHR2());
//			}
//			if (psAssign.getHR3() != null) {
//				existingConfigData.setHR3(psAssign.getHR3());
//			}
//			if (psAssign.getHR4() != null) {
//				existingConfigData.setHR4(psAssign.getHR4());
//			}
//			if (psAssign.getHR5() != null) {
//				existingConfigData.setHR5(psAssign.getHR5());
//			}
//			
//			if (psAssign.getQRDirector1() != null) {
//				existingConfigData.setQRDirector1(psAssign.getQRDirector1());
//			}
//			if (psAssign.getQRDirector2() != null) {
//				existingConfigData.setQRDirector2(psAssign.getQRDirector2());
//			}
//			if (psAssign.getQRDirector3() != null) {
//				existingConfigData.setQRDirector3(psAssign.getQRDirector3());
//			}
//			if (psAssign.getQRDirector4() != null) {
//				existingConfigData.setQRDirector4(psAssign.getQRDirector4());
//			}
//			if (psAssign.getQRDirector5() != null) {
//				existingConfigData.setQRDirector5(psAssign.getQRDirector5());
//			}
//			
//			if (psAssign.getDirOfHIM1() != null) {
//				existingConfigData.setDirOfHIM1(psAssign.getDirOfHIM1());
//			}
//			if (psAssign.getDirOfHIM2() != null) {
//				existingConfigData.setDirOfHIM2(psAssign.getDirOfHIM2());
//			}
//			if (psAssign.getDirOfHIM3() != null) {
//				existingConfigData.setDirOfHIM3(psAssign.getDirOfHIM3());
//			}
//			if (psAssign.getDirOfHIM4() != null) {
//				existingConfigData.setDirOfHIM4(psAssign.getDirOfHIM4());
//			}
//			if (psAssign.getDirOfHIM5() != null) {
//				existingConfigData.setDirOfHIM5(psAssign.getDirOfHIM5());
//			}
//			
//			if (psAssign.getRegDietitian1() != null) {
//				existingConfigData.setRegDietitian1(psAssign.getRegDietitian1());
//			}
//			if (psAssign.getRegDietitian2() != null) {
//				existingConfigData.setRegDietitian2(psAssign.getRegDietitian2());
//			}
//			if (psAssign.getRegDietitian3() != null) {
//				existingConfigData.setRegDietitian3(psAssign.getRegDietitian3());
//			}
//			if (psAssign.getRegDietitian4() != null) {
//				existingConfigData.setRegDietitian4(psAssign.getRegDietitian4());
//			}
//			if (psAssign.getRegDietitian5() != null) {
//				existingConfigData.setRegDietitian5(psAssign.getRegDietitian5());
//			}
//			
//			// Save the updated configuration
//			existingConfigData.setUpdatedAt(getTimeStamp());
//			return repository.save(existingConfigData);
//		} else {
//			// Configuration with the same PID does not exist, create a new record
//			String uid = PatientService.generateUID();
//			psAssign.setId(uid);
//			psAssign.setCreatedAt(getTimeStamp());
//			return repository.save(psAssign);
//		}
//	}
//
//	// ***************** Get All PatientStaffAssignment ****************\\
//	public List<PatientStaffAssignment> getAllPatientStaffAssignment() {
//		return repository.findAll();
//	}	
//	
//	private String getTimeStamp() {
//		LocalDateTime now  = LocalDateTime.now();
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
//		String time = now.format(format);
//		return time;
//	}
//}