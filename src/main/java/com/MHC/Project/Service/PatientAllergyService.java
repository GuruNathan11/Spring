package com.MHC.Project.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.PatientAllergy;
import com.MHC.Project.Repository.PatientAllergyRepository;

@Service
public class PatientAllergyService {

	@Autowired
	PatientAllergyRepository allergyrepo;
	
	public PatientAllergy saveDetails(PatientAllergy patientAllergy)
	{
		String uid=generateUID();
		PatientAllergy allergyDetails=patientAllergy.build(uid,patientAllergy.getPatientId(), patientAllergy.getCausativeAgentName(),patientAllergy.getDateOfOnset(), patientAllergy.getPhysicianName(),patientAllergy.getLastVisit(), patientAllergy.getAllergyType(),
				patientAllergy.getSpecificAllergen(),patientAllergy.getTreatmentPlan(),patientAllergy.getOtherReleventMedicalCondition(),patientAllergy.getMedicationHistory(),patientAllergy.getSymptoms(),patientAllergy.getAllergySeverity(),
				patientAllergy.getFamilyHistoryOfAllergies(),patientAllergy.getNatureOfReaction(),patientAllergy.getComments(), patientAllergy.getObserved(),patientAllergy.isInactive(),patientAllergy.getObservedDetails());
		
		allergyDetails.setEnteredDate(getDateTime());
		allergyDetails.setId(uid);
		System.out.println("1");
		return allergyrepo.save(allergyDetails);
	}
	public PatientAllergy updateDetails(String id,PatientAllergy patientAllergy) throws PageNotFound
	{
		PatientAllergy allergyDetails=patientAllergy.build(patientAllergy.getId(),patientAllergy.getPatientId(), patientAllergy.getCausativeAgentName(),patientAllergy.getDateOfOnset(), patientAllergy.getPhysicianName(),patientAllergy.getLastVisit(), patientAllergy.getAllergyType(),
				patientAllergy.getSpecificAllergen(),patientAllergy.getTreatmentPlan(),patientAllergy.getOtherReleventMedicalCondition(),patientAllergy.getMedicationHistory(),patientAllergy.getSymptoms(),patientAllergy.getAllergySeverity(),
				patientAllergy.getFamilyHistoryOfAllergies(),patientAllergy.getNatureOfReaction(),patientAllergy.getComments(), patientAllergy.getObserved(),patientAllergy.isInactive(),patientAllergy.getObservedDetails());
		
		Optional<PatientAllergy> allergyOptional =allergyrepo.findById(id);
		if(allergyOptional.isPresent())
		{
			PatientAllergy allergyUpdate=allergyOptional.get();
			allergyUpdate.update(allergyDetails);
			allergyUpdate.setUpdatedDate(getDateTime());
			return allergyrepo.save(allergyUpdate);
		}
		else {
			throw new PageNotFound("PatientProblem not Found");
        }
	}
	public List<PatientAllergy> getAllAllergyDetails()
	{
		return allergyrepo.findAll();
	}
	public PatientAllergy getById(String id)
	{
		return allergyrepo.findById(id).get();
	}
	public String deleteById(String id)
	{
		boolean delete=false;
		if(allergyrepo.existsById(id))
		{
			allergyrepo.deleteById(id);
			delete=true;
		}
		return "allergyDetails of id: "+id+" Delete Status: "+delete;
	}
	public static String getDateTime(){
		 LocalDateTime now= LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		 String dateTime = now.format(formatter);
		 return dateTime;
	}
	public static String generateUID() {
		// Generate a random alphanumeric ID with 10 digits
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
}
