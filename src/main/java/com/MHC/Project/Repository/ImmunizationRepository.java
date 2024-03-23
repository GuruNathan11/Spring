package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Immunization;

public interface ImmunizationRepository extends MongoRepository<Immunization, String>{

	List<Immunization> findByPatientId(String patientId);

	Optional<Immunization> findByPatientIdAndId(String patientId, String id);

}
