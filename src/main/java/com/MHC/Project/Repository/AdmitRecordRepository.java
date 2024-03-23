package com.MHC.Project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.AdmitRecords;

public interface AdmitRecordRepository extends MongoRepository<AdmitRecords, String>{

}
