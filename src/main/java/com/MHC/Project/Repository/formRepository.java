package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.form;

public interface formRepository extends MongoRepository<form, String> {

	boolean existsByName(String name);

	form findByName(String name);
    
}