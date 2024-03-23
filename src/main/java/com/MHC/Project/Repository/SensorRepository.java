package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Sensor;
import com.MHC.Project.Model.SensorServer;

public interface SensorRepository extends MongoRepository<Sensor, String>{

	Optional<Sensor> findByUuid(String uuid);

	Optional<Sensor> findByDeviceName(String deviceName);

	Optional<Sensor> findByDeviceId(String deviceId);

	List<Sensor> findAllByOrgId(String orgId);

	Page<Sensor> findAllByOrgId(String orgId, Pageable pageable); 
	
}
