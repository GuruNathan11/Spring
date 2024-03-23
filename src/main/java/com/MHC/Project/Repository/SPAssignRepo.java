package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.StaffPatientAssign;

public interface SPAssignRepo extends MongoRepository<StaffPatientAssign, String>{

	 Optional<StaffPatientAssign> findBySid(String[] strings);

		List<StaffPatientAssign> findByPid(String Pid); 
}