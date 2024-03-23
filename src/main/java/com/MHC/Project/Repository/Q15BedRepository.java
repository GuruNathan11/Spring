package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Q15Bed;

public interface Q15BedRepository extends MongoRepository<Q15Bed, String>{

	List<Q15Bed> findByRoomNo(String roomNoStart);

	boolean existsByRoomNo(String valueOf);

	Optional<Q15Bed> findByPid(String pid);

	List<Q15Bed> findByOrganizationAndOccupied(String orgId, boolean b);

	List<Q15Bed> findByOrganization(String orgId);

	List<Q15Bed> findByOrganizationAndRoomNo(String organization, String roomNo);

	boolean existsByOrganizationAndRoomNoAndBedNo(String organization, String string, String format);

	Optional<Q15Bed> findByRoomNoAndBedNoAndOrganization(String roomNo, String bedNo, String orgId);

}
