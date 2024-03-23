package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.PtStRequest;

public interface PtStRepo extends MongoRepository<PtStRequest, String>{


	Optional<PtStRequest> findByPID(String pid);

	Optional<PtStRequest> findByPID(String[] pid);

}
