package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.PatientAllergy;

public interface PatientAllergyRepository extends MongoRepository<PatientAllergy, String > {

	List<PatientAllergy> findByPatientId(String patientId);
	
	boolean existsByPatientId(String patientId);

	Optional<PatientAllergy> findByPatientIdAndId(String patientId, String id);

	
}
