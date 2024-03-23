package com.MHC.Project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Model;

public interface Repo extends MongoRepository<Model, String> {

	Model findTopByDeviceIdOrderByTimestampDesc(String deviceId);
	// Define custom query methods if needed

	Optional<Model> findByDeviceId(String deviceId);

	void deleteByDeviceId(String beaconDevice);

//	List<Model> getById();

//	List<Model> getByDeviceId(String deviceId);
}
