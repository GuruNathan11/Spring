package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.TreatmentPlanProblem;

public interface TreatmentPlanProblemRepository extends MongoRepository<TreatmentPlanProblem, String>{

	List<TreatmentPlanProblem> findByProblemName(String problemName);

}
