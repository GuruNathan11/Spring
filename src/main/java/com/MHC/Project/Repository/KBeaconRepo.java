package com.MHC.Project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.KBeacon;

public interface KBeaconRepo extends MongoRepository<KBeacon, String> {
}

