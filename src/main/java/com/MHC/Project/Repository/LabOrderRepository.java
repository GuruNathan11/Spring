package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.MHC.Project.Model.LabOrder;


public interface LabOrderRepository extends MongoRepository<LabOrder, String>{
	
	Optional<LabOrder> findById(String id);

	boolean existsBypatientId(String patientId);

	Optional<LabOrder> findBypatientId(String patientId);

	boolean existsByid(String id);

	Optional<LabOrder> findByIdAndPatientId(String id, String patientID);
}
