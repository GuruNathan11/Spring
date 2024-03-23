package com.MHC.Project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.MHC.Project.Model.accessControl;

public interface accessRepo extends MongoRepository<accessControl, String>{

	Optional<accessControl> findByOrgName(String orgName);
	

}
