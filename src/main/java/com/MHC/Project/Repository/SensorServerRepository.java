package com.MHC.Project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.SensorServer;

public interface SensorServerRepository extends MongoRepository<SensorServer, String>{

	Optional<SensorServer> findByUuid(String uuid);

	Optional<SensorServer> findByDeviceId(String deviceId);

}
