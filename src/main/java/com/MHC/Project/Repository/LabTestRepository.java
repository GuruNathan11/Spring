package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.OrderLabTest;

public interface LabTestRepository extends MongoRepository<OrderLabTest, String>{

	List<OrderLabTest> findByPid(String pid);

}
