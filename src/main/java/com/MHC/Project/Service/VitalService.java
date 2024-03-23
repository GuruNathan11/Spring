package com.MHC.Project.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Vital;
import com.MHC.Project.Repository.VitalRepository;


@Service
public class VitalService {
	
	@Autowired
	VitalRepository vitalRepository;

	public Vital saveDetails(Vital vital) {
		String uid=generateUID();
		
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);
		
		Vital vitalDetails= vital.build(uid,vital.getPatientId(), vital.getBodyTemperature(),vital.getPulseRate(),vital.getHeartRate(),vital.getRespirationRate(),
				vital.getBloodPressure(),vital.getBloodOxygen(), vital.getHeight(),vital.getWeight(),vital.getBloodGlucoseLevel(), vital.getCircumferenceOrGirth(), 
				vital.getPain(), vital.getPulseOximetry(),vital.getLastVisit(),vital.getComments(), vital.getCreatedAt(), vital.getUpdatedAt());
		
		vital.setId(uid);
		vitalDetails.setCreatedAt(createdAt);
		vitalDetails.setEnteredDate(vital.getEnteredDate());
		return vitalRepository.save(vitalDetails);
	}

	public Vital updateDetail(String id,Vital vital)throws PageNotFound {
		
		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		Vital vitalDetails= vital.build(vital.getId(),vital.getPatientId(), vital.getBodyTemperature(),vital.getPulseRate(),vital.getHeartRate(),vital.getRespirationRate(),
				 vital.getBloodPressure(),vital.getBloodOxygen(), vital.getHeight(),vital.getWeight(),vital.getBloodGlucoseLevel(),vital.getCircumferenceOrGirth(), 
				 vital.getPain(), vital.getPulseOximetry(),vital.getLastVisit(),vital.getComments(), vital.getCreatedAt(), vital.getUpdatedAt());
		
		Optional<Vital> vitalOptional= vitalRepository.findById(id);
		if(vitalOptional.isPresent())
		{
			Vital vitalUpdate=vitalOptional.get();
			vitalUpdate.setUpdatedAt(updatedAt);
			vitalUpdate.update(vitalDetails);
			return vitalRepository.save(vitalUpdate);
		}else {
			throw new PageNotFound("Vital not Found");
		}
	}

	public List<Vital> getAllVital() {
		return vitalRepository.findAll();
	}

	public Vital getById(String id) {
		return vitalRepository.findById(id).get();
	}

	public boolean deleteById(String id) {
		boolean delete=false;
		if(vitalRepository.existsById(id))
		{
			vitalRepository.deleteById(id);
			delete=true;
		}
		return delete;
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
//	private String getDateTime() {
//		LocalDateTime now= LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//		String dateTime = now.format(formatter);
//		return dateTime;
//	}
}