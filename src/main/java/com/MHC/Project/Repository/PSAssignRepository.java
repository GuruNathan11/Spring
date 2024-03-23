//package com.MHC.Project.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import com.MHC.Project.Model.PatientStaffAssignment;
//
//public interface PSAssignRepository extends MongoRepository<PatientStaffAssignment, String>{
//
//	Optional<PatientStaffAssignment> findByPID(String[] patientId);
//
//	List<PatientStaffAssignment> findByPID(String patientId);
//
//
//}
