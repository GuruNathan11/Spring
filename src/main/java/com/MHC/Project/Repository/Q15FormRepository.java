package com.MHC.Project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Q15Form;

public interface Q15FormRepository extends MongoRepository<Q15Form, String> {

	Optional<Q15Form> findById(String id);

}
