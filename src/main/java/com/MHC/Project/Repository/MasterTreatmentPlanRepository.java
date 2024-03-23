package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.MasterTreatmentPlan;
import com.MHC.Project.Model.TreatmentPlanProblem;

public interface MasterTreatmentPlanRepository extends MongoRepository<MasterTreatmentPlan, String>{

	List<MasterTreatmentPlan> findByProblemName(String problemName);

	List<MasterTreatmentPlan> findByPid(String pid);
}
