package com.MHC.Project.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {

    Optional<Patient> findById(String id);

    boolean existsByBasicDetailsNameGivenOrBasicDetailsNameFamily(String givenName, String familyName);
    
    boolean existsByBasicDetailsSsn(String ssn); // Custom query method to check if a patient with the given SSN exists

    boolean existsByBasicDetailsNameGiven(String givenName);
    
    boolean existsByBasicDetailsNameFamily(String familyname);
    
    boolean existsByBasicDetailsBirthDate(LocalDate birthdate);
    
    void deleteById(String id);

    Optional<Patient> findByBasicDetailsSsn(String ssn);
    
    List<Patient> findByBasicDetailsNameGiven(String givenName);
    
    List<Patient> findByBasicDetailsNameFamily(String familyName);

    Optional<Patient> findByUsername(String username);
    
    List<Patient> findByBasicDetailsBirthDate(String birthDate);
     
    List<Patient> findByBasicDetailsNameGivenAndBasicDetailsNameFamilyAndBasicDetailsBirthDate(String givenName, String familyName,String birthdate);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	Patient findByEmail(String email);

	Patient findByUsernameAndOrganization(String username, String org);

	List<Patient> findByOrganization(String organization);

	List<Patient> findByBasicDetailsNameGivenAndBasicDetailsNameFamily(String givenName, String familyName);

	void deleteByUsername(String username);

	List<Patient> findAllByActiveAndOrganization(String active, String organization);

	List<Patient> findByIdIn(List<String> ids);

	Optional<Patient> findByBeaconDeviceAndOrganization(String deviceId,String orgId);

	Optional<Patient> findByBeaconDevice1AndOrganization(String deviceId, String orgId);

	Optional<Patient> findByBeaconDevice(String deviceId);

	Page<Patient> findAllByActiveAndOrganization(String active, String org, Pageable pageable);

//	List<Patient> findByOrganizationAndActive(String organization, String string);

}