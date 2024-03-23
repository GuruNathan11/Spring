package com.MHC.Project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Emergency1;

public interface EmergencyRepo extends MongoRepository<Emergency1, String> {

	boolean existsByDeviceId(String deviceId);

	Optional<Emergency1> findByUuid(String uuid);

	Optional<Emergency1> findByDeviceId(String deviceId);

	Optional<Emergency1> findByDeviceName(String deviceName);

}
