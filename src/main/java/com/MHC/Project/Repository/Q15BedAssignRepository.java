package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Q15BedAssign;

public interface Q15BedAssignRepository extends MongoRepository<Q15BedAssign, String>{

	List<Q15BedAssign> findAllByOrgId(String orgId);

	Optional<Q15BedAssign> findByPid(String pid);

}
