package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Bed;

public interface BedRepository extends MongoRepository<Bed, String> {

	List<Bed> findByOccupied(boolean b);
//	boolean existsByPid(String pid);
//
//	Bed findByPid(String pid);
//
//	List<Bed> findByOccupied(boolean b);

}