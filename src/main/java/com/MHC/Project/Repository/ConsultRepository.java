package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.OrderConsult;

public interface ConsultRepository extends MongoRepository<OrderConsult, String>{

	List<OrderConsult> findByPid(String pid);

}
