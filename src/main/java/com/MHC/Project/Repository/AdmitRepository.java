package com.MHC.Project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.AdmitPatient;

public interface AdmitRepository  extends MongoRepository<AdmitPatient, String>{

	List<AdmitPatient> findByStatus(String string);

	List<AdmitPatient> findByPid(String pid);

	List<AdmitPatient> findByDischargeDateAndStatus(String dischargeDate, String string);

	List<AdmitPatient> findByAdmissionDateAndStatus(String admissionDate, String string);

}
