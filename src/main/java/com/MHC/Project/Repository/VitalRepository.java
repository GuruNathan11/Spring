package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Vital;

public interface VitalRepository extends MongoRepository<Vital, String>{

	Optional<Vital> findById(String Id);
	
	List<Vital> findByPatientId(String patientId);
	
	boolean existsByPatientId(String PatientId);

	Optional<Vital> findByPatientIdAndId(String patientId, String id);

	List<Vital> findByLastVisit(String lastVisit);
}
