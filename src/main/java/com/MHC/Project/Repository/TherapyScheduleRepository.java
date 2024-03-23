package com.MHC.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.TherapySchedule;

public interface TherapyScheduleRepository extends MongoRepository<TherapySchedule, String>{

	List<TherapySchedule> findByScheduleDate(String scheduleDate);

	List<TherapySchedule> findByScheduleTime(String scheduleTime);

	List<TherapySchedule> findByScheduleDateAndScheduleTime(String scheduleDate, String scheduleTime);

}
