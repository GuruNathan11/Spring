package com.MHC.Project.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.PageNotFound;
import com.MHC.Project.Model.Immunization;
import com.MHC.Project.Repository.ImmunizationRepository;

@Service
public class ImmunizationService {

	@Autowired
	ImmunizationRepository repository;

	public Immunization saveImmunization(Immunization immunization) {

		String uid = generateUID();
		
		LocalDateTime createDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String createdAt = createDate.format(formatter);

		Immunization immunizationDetails = immunization.build(uid, immunization.getPatientId(),
				immunization.getImmunization(), immunization.getLotNo(), immunization.getDueDate(),
				immunization.getDoneDate(), immunization.getAdministrationDate(), immunization.getAdministeredBy(),
				immunization.getOrderedBy(), immunization.getRoute(), immunization.getAnatomicLocation(),
				immunization.getSeries(), immunization.getDosage(), immunization.isAdministeringByPolicy(),
				immunization.isIncludeNonVAProviders(), immunization.getComments(),immunization.getLastVisit(), createdAt, immunization.getUpdatedAt());

		return repository.save(immunizationDetails);
	}

	public Immunization updateDetails(String id, Immunization immunization) throws PageNotFound {

		LocalDateTime updateDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String updatedAt = updateDate.format(formatter);
		
		Immunization immunizationDetails = immunization.build(immunization.getId(), immunization.getPatientId(),
				immunization.getImmunization(), immunization.getLotNo(), immunization.getDueDate(),
				immunization.getDoneDate(), immunization.getAdministrationDate(), immunization.getAdministeredBy(),
				immunization.getOrderedBy(), immunization.getRoute(), immunization.getAnatomicLocation(),
				immunization.getSeries(), immunization.getDosage(), immunization.isAdministeringByPolicy(),
				immunization.isIncludeNonVAProviders(), immunization.getComments(),immunization.getLastVisit(), immunization.getCreatedAt(), updatedAt);

		Optional<Immunization> immunizationOptional = repository.findById(id);
		
		if (immunizationOptional.isPresent()) {
			Immunization immunizationUpdate = immunizationOptional.get();
			immunizationUpdate.setUpdatedAt(updatedAt);
			immunizationUpdate.update(immunizationDetails);
			return repository.save(immunizationUpdate);
		} else {
			throw new PageNotFound("Immunization Data not Found for given Id");
		}

	}

	public List<Immunization> getAllImmunization() {
		return repository.findAll();
	}

	public boolean deleteById(String id) {

		boolean delete=false;
		if(repository.existsById(id))
		{
			repository.deleteById(id);
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

}
