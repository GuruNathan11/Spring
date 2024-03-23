package com.MHC.Project.Repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.EmerDataModel;



public interface EmerDataRepo extends MongoRepository<EmerDataModel, String>{
	EmerDataModel findTopByDeviceIdOrderByTimestampDesc(String deviceId);

	Optional<EmerDataModel> findByUuid(String uuid);

	EmerDataModel findTopByUuidOrderByTimestampDesc(String uuid);
}


