package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.PtStRecords;
import com.MHC.Project.Model.PtStRequest;


	public interface PtStRecordsRepo extends MongoRepository<PtStRecords , String> {

//		Optional<StaffPatientAssign> findBySid(String[] sid);
		 Optional<PtStRecords> findById(String id);

		Optional<PtStRecords> findById(String[] id);

		List<PtStRecords> findByPID(String pID);

		PtStRecords findByPID(String[] pid);

	

	}
