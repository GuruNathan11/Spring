package com.MHC.Project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.field;


public interface fieldRepository extends MongoRepository<field, String> {

//	field findByFormNameAndFieldLabel(String formName, String fieldLabel);

	boolean existsByFieldLabelAndFormName(String fieldLabel, String formName);
    
}
