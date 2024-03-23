package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.PatientProblem;

public interface PatientProblemRepository extends MongoRepository<PatientProblem, String>{

	List<PatientProblem> findByPatientId(String patientId);
	boolean existsByPatientId(String patientId);
	Optional<PatientProblem> findByPatientIdAndId(String patientId, String id);
}
