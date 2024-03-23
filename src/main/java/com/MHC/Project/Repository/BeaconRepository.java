package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Beacon;


public interface BeaconRepository extends MongoRepository<Beacon, String>{



	boolean existsByDeviceId(String deviceId);

	Optional<Beacon> findByDeviceId(String deviceId);

	void getById(String deviceId);

	List<Beacon> findByOrganization(String orgId); 
 
}

