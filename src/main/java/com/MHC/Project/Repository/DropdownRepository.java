package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Dropdown;
import com.MHC.Project.Model.down;

public interface DropdownRepository extends MongoRepository<down, String> {

	down findByIdAndDropdown(String id, String dropdown);

	List<down> findByDropdown(String dropdown);

//	List<String> findDistinctDropdowns();
   
}