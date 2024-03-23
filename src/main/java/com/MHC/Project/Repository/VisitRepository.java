package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Visit;

public interface VisitRepository extends MongoRepository<Visit, String> {

	List<Visit> findByPid(String pid);

}