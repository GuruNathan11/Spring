package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Organization;


public interface OrganizationRepository extends MongoRepository<Organization, String> {

	List<Organization> getById(String id);

	boolean existsByOrganizationdetailsName(String name);

	boolean existsByOrganizationdetailsNameAndIdNot(String name, String organizationId);

	Optional<Organization> findById(Organization organization);

	boolean existsByEmail(String email);

	Optional<Organization> findByEmail(String email);

//	Optional<Organization> findByUsername(String username);
}
