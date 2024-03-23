package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Therapy;

public interface TherapyRepository extends MongoRepository<Therapy, String>{

	List<Therapy> findByDate(String date);

	List<Therapy> findByScheduleId(String scheduleId);

}
