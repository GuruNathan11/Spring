package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Suicide;

public interface SuicideRepository extends MongoRepository<Suicide, String> {

	List<Suicide> findByPid(String pid);

	List<Suicide> findByScoringSuicideRisk(String suicideRisk);

	List<Suicide> findByPidAndLastVisit(String pid, String visitId);

}
