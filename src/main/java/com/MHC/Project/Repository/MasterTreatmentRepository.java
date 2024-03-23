package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.MasterTreatment;
import com.MHC.Project.Model.MasterTreatment.Nursing;
import com.MHC.Project.Model.MasterTreatment.SocialWorkRecorder;

public interface MasterTreatmentRepository extends MongoRepository<MasterTreatment, String> {

	Optional<MasterTreatment> findByRecreationalTherapist_Id(String id);

	Optional<SocialWorkRecorder> findBySocialWorkRecorder_Id(String id);

	Optional<Nursing> findByNursing_Id(String id);

	List<MasterTreatment> findByPid(String pid);

	List<MasterTreatment> findByPidAndLastVisit(String pid, String visitId);

}
