package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.filledForm;

public interface FilledFormRepository extends MongoRepository<filledForm, String> {

	List<filledForm> findByPid(String pid);

	Optional<filledForm> findByPidAndForm_Name(String pid, String form);

}
