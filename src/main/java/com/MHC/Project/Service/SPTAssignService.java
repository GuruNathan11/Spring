package com.MHC.Project.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MHC.Project.Model.PtStRecords;
import com.MHC.Project.Model.Staff;
import com.MHC.Project.Model.StaffPatientAssign;
import com.MHC.Project.Repository.PtStRecordsRepo;
import com.MHC.Project.Repository.SPAssignRepo;
import com.MHC.Project.Repository.StaffRepository;


@Service
public class SPTAssignService {

    @Autowired
    SPAssignRepo srepo;

    @Autowired
    PtStRecordsRepo psrepo;

    @Autowired
    StaffRepository staffRepository;

    public String getStaffRole(String sid) {
        Optional<Staff> staff = staffRepository.findById(sid);

        if (staff.isPresent()) {
            return staff.get().getRole();
        }

        return null; // Return null or a default role if staff is not found
    }

    public StaffPatientAssign saveSpAssign(StaffPatientAssign sptAssign) {
        Optional<StaffPatientAssign> existingStaff = srepo.findBySid(sptAssign.getSid());

        if (existingStaff.isPresent()) {
            StaffPatientAssign existingStaffData = existingStaff.get();

            if (sptAssign.getPid() != null) {
                // Merge the new pid with the existing pid
                Set<String> mergedPid = new HashSet<>(Arrays.asList(existingStaffData.getPid()));
                mergedPid.addAll(Arrays.asList(sptAssign.getPid()));
                existingStaffData.setPid(mergedPid.toArray(new String[0]));
            }

            // Find and update the corresponding PtStRecords record based on PID
            PtStRecords existingRecords = psrepo.findByPID(existingStaffData.getPid());
            if (existingRecords != null) {
                updatePtStRecordsWithStaffRoles(existingRecords, existingStaffData.getSid());
                psrepo.save(existingRecords); // Update the PtStRecords record
            } else {
                // Create a new PtStRecords record
                PtStRecords newRecords = new PtStRecords();
                String uid = PatientService.generateUID();
                newRecords.setId(uid);
                newRecords.setPID(existingStaffData.getPid());
                updatePtStRecordsWithStaffRoles(newRecords, existingStaffData.getSid());
                psrepo.save(newRecords); // Save the new PtStRecords record
            }

            return srepo.save(existingStaffData);
        } else {
            String uid = PatientService.generateUID();
            sptAssign.setId(uid);

            PtStRecords psa = new PtStRecords();
            psa.setId(uid);
            psa.setPID(sptAssign.getPid());
            updatePtStRecordsWithStaffRoles(psa, sptAssign.getSid());

            // Fetch the staff roles and associate them with the sids in PtStRecords
            for (String sid : sptAssign.getSid()) {
                String staffRole = getStaffRole(sid);
                if (staffRole != null) {
                    associateRoleWithSid(psa, sid, staffRole);
                }
            }

            psrepo.save(psa);
            return srepo.save(sptAssign);
        }
    }

    private void updatePtStRecordsWithStaffRoles(PtStRecords psa, String[] sids) {
        for (String sid : sids) {
            String staffRole = getStaffRole(sid);
            if (staffRole != null) {
                associateRoleWithSid(psa, sid, staffRole);
            }
        }
    }

    private void associateRoleWithSid(PtStRecords psa, String sid, String role) {
        if ("Psychiatrists".equals(role)) {
            psa.setPsychiatrists(new String[]{sid});
        } else if ("Medical Doctor".equals(role)) {
            psa.setMedicalDoctor(new String[]{sid});
        } else if ("Nurse Practitioner".equals(role)) {
            psa.setNursePractitioner(new String[]{sid});
        } else if ("Physician Assistant".equals(role)) {
            psa.setPhysicianAssistant(new String[]{sid});
        } else if ("Psychologist".equals(role)) {
            psa.setPsychologist(new String[]{sid});
        } else if ("Registered Nurses".equals(role)) {
            psa.setRegisteredNurses(new String[]{sid});
        } else if ("Social Workers".equals(role)) {
            psa.setSocialWorkers(new String[]{sid});
        } else if ("Activity Therapist".equals(role)) {
            psa.setActivityTherapist(new String[]{sid});
        } else if ("Yoga Therapist".equals(role)) {
            psa.setYogaTherapist(new String[]{sid});
        } else if ("Mental Health Workers".equals(role)) {
            psa.setMentalHealthWorkers(new String[]{sid});
        } else if ("Recipient Rights Officer".equals(role)) {
            psa.setrROfficer(new String[]{sid});
        } else if ("Nurse Managers".equals(role)) {
            psa.setNurseManagers(new String[]{sid});
        } else if ("Director of Nursing".equals(role)) {
            psa.setDirOfNursing(new String[]{sid});
        } else if ("Executives".equals(role)) {
            psa.setExecutives(new String[]{sid});
        } else if ("Human Resources".equals(role)) {
            psa.setHR(new String[]{sid});
        } else if ("Quality and Risk Director".equals(role)) {
            psa.setQRDirector(new String[]{sid});
        } else if ("Director of Health Information Management".equals(role)) {
            psa.setDirOfHIM(new String[]{sid});
        } else if ("Registered Dietitian".equals(role)) {
            psa.setRegDietitian(new String[]{sid});
        }
        // Add other role associations as needed
    }

    // *************** Get All Drop Down List *************\\
    public List<StaffPatientAssign> getAllList() {
        return srepo.findAll();
    }
}
