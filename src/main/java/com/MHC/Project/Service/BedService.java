package com.MHC.Project.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MHC.Project.Error.MessageResponse;
import com.MHC.Project.Model.Bed;
import com.MHC.Project.Model.BedMasterConfig;
import com.MHC.Project.Model.Patient;
import com.MHC.Project.Repository.BedMasterRepository;
import com.MHC.Project.Repository.BedRepository;
import com.MHC.Project.Repository.PatientRepository;
import com.MHC.Project.Repository.StaffRepository;
import com.MHC.Project.Request.AdmitRequest;

@Service
public class BedService {

    private final BedRepository bedRepository;
    private final BedMasterRepository wardRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;

    public BedService(BedRepository bedRepository, BedMasterRepository wardRepository,
                      PatientRepository patientRepository, StaffRepository staffRepository) {
        this.bedRepository = bedRepository;
        this.wardRepository = wardRepository;
        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
    }

 // Create a new bed configuration
    public BedMasterConfig createBedMasterConfig(BedMasterConfig bedMasterConfig) {
    	
    	bedMasterConfig.setId(PatientService.generateUID());
        bedMasterConfig.setOccupied(false);
        return wardRepository.save(bedMasterConfig);
    }

    // Retrieve all bed configurations
    public List<BedMasterConfig> getAllBedMasterConfigs() {
        return wardRepository.findAll();
    }
}