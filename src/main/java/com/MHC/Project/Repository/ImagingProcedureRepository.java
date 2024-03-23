package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.OrderImagingProcedure;

public interface ImagingProcedureRepository extends MongoRepository<OrderImagingProcedure, String>{

	List<OrderImagingProcedure> findByPid(String pid);

}
