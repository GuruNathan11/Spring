package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.OrderProcedure;

public interface OrderProcedureRepository extends MongoRepository<OrderProcedure, String>{

	List<OrderProcedure> findByPid(String pid);

}
