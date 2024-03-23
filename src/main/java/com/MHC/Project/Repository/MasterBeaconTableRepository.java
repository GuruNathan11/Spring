package com.MHC.Project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.MasterBeaconTable;

public interface MasterBeaconTableRepository extends MongoRepository<MasterBeaconTable, String> {

	Optional<MasterBeaconTable> findByDeviceId(String deviceId);

	Optional<MasterBeaconTable> findByDeviceName(String deviceName);

	Optional<MasterBeaconTable> findByUuid(String uuid);

}
