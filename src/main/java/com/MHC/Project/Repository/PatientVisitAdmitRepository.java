package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.PatientVisitAdmit;

public interface PatientVisitAdmitRepository extends MongoRepository<PatientVisitAdmit, String> {
	Optional<PatientVisitAdmit> findById(String id);

	boolean existsByPatientid(String patientid);
	
	List<PatientVisitAdmit> findByPatientid(String patientid);
	
	void save(List<PatientVisitAdmit> patientVisit);
	
	boolean existsByid(String id);
	
	void deleteByid(String id);

	List<PatientVisitAdmit> findAllByActiveFlag(String activeFlag);
}