package com.MHC.Project.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Staff;
import com.MHC.Project.Model.Staff.Name;


public interface StaffRepository extends MongoRepository <Staff, String> {

	List<Staff> getById(String id);
    boolean existsBySsn(String ssn);
    List<Name> getByName(String Name);
	Optional<Staff> findByName(List<Name> name);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	void deleteByUsername(String username);
	List<Staff> findAllByRole(String role);
	List<Staff> findAllByActive(String active);
	Optional<Staff> findByUsername(String staffUsername);
	List<Staff> findByIdIn(List<String> ids);
	Page<Staff> findAllByActiveAndOrganization(String active, String organizationName, Pageable pageable);
	List<Staff> findAllByRoleAndOrganization(String role, String name);
	
}
