package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.NurseToNurse;

public interface NurseToNurseRepository extends MongoRepository<NurseToNurse, String>{

	List<NurseToNurse> findByAdmittingNurse(String admittingNurse);

}
