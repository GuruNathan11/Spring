package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.PSConfiguration;

public interface PSConfigurationRepository extends MongoRepository<PSConfiguration, String>{

	PSConfiguration findByDate(String date);

	List<PSConfiguration> findAllByShiftShiftName(String shiftName);

	List<PSConfiguration> findByDateAndShiftShiftName(String date, String shiftName);

}
