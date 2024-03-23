package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.MHC.Project.Model.BedMasterConfig;

@EnableMongoRepositories
public interface BedMasterRepository extends MongoRepository<BedMasterConfig, String> {

	List<BedMasterConfig> findByOrganization(String org);

}