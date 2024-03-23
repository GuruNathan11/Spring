package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "MasterTreatment")
@SuppressWarnings("unused")
public class MasterTreatment {

    @Id
    private String id;
    private String pid;
    private String lastVisit;
    private RecreationalTherapist recreationalTherapist;
    private SocialWorkRecorder socialWorkRecorder;
    private Nursing nursing;
    private MedicalPractitioner medicalPractitioner;
    private List<TreatmentTeamMemberSignature> participatingTreatmentTeamMemberSignatures;

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public RecreationalTherapist getRecreationalTherapist() {
        return recreationalTherapist;
    }

    public void setRecreationalTherapist(RecreationalTherapist recreationalTherapist) {
        this.recreationalTherapist = recreationalTherapist;
    }

    public SocialWorkRecorder getSocialWorkRecorder() {
        return socialWorkRecorder;
    }

    public void setSocialWorkRecorder(SocialWorkRecorder socialWorkRecorder) {
        this.socialWorkRecorder = socialWorkRecorder;
    }

    public Nursing getNursing() {
        return nursing;
    }

    public void setNursing(Nursing nursing) {
        this.nursing = nursing;
    }

    public MedicalPractitioner getMedicalPractitioner() {
        return medicalPractitioner;
    }

    public void setMedicalPractitioner(MedicalPractitioner medicalPractitioner) {
        this.medicalPractitioner = medicalPractitioner;
    }

    public List<TreatmentTeamMemberSignature> getParticipatingTreatmentTeamMemberSignatures() {
        return participatingTreatmentTeamMemberSignatures;
    }

    public void setParticipatingTreatmentTeamMemberSignatures(List<TreatmentTeamMemberSignature> participatingTreatmentTeamMemberSignatures) {
        this.participatingTreatmentTeamMemberSignatures = participatingTreatmentTeamMemberSignatures;
    }
   
    public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public static class RecreationalTherapist {
		private String id;
        private String admissionDate;
        private String treatmentTeamDate;
        private String nextReviewDate;
        private String createdAt;
        private String updatedAt;
        
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		public String getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getAdmissionDate() {
			return admissionDate;
		}
		public void setAdmissionDate(String admissionDate) {
			this.admissionDate = admissionDate;
		}
		public String getTreatmentTeamDate() {
			return treatmentTeamDate;
		}
		public void setTreatmentTeamDate(String treatmentTeamDate) {
			this.treatmentTeamDate = treatmentTeamDate;
		}
		public String getNextReviewDate() {
			return nextReviewDate;
		}
		public void setNextReviewDate(String nextReviewDate) {
			this.nextReviewDate = nextReviewDate;
		}
        
        
    }

    public static class SocialWorkRecorder {
    	private String id;
        private LegalStatus legalStatus;
        private String[] dsm5Diagnoses;
        private List<PatientStrengthAssets> patientStrengthAssets;
        private DischargeContinuingCarePlan dischargeContinuingCarePlan;
        private FamilySignificantOtherInvolvement familySignificantOtherInvolvement;
        private GuardianInvolvementInIndividualPlanOfService guardianInvolvementInIndividualPlanOfService;
        private String createdAt;
        private String updatedAt;
        
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		public String getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
        
		public LegalStatus getLegalStatus() {
			return legalStatus;
		}
		public void setLegalStatus(LegalStatus legalStatus) {
			this.legalStatus = legalStatus;
		}
		public String[] getDsm5Diagnoses() {
			return dsm5Diagnoses;
		}
		public void setDsm5Diagnoses(String[] dsm5Diagnoses) {
			this.dsm5Diagnoses = dsm5Diagnoses;
		}
		public List<PatientStrengthAssets> getPatientStrengthAssets() {
			return patientStrengthAssets;
		}
		public void setPatientStrengthAssets(List<PatientStrengthAssets> patientStrengthAssets) {
			this.patientStrengthAssets = patientStrengthAssets;
		}
		public DischargeContinuingCarePlan getDischargeContinuingCarePlan() {
			return dischargeContinuingCarePlan;
		}
		public void setDischargeContinuingCarePlan(DischargeContinuingCarePlan dischargeContinuingCarePlan) {
			this.dischargeContinuingCarePlan = dischargeContinuingCarePlan;
		}
		public FamilySignificantOtherInvolvement getFamilySignificantOtherInvolvement() {
			return familySignificantOtherInvolvement;
		}
		public void setFamilySignificantOtherInvolvement(FamilySignificantOtherInvolvement familySignificantOtherInvolvement) {
			this.familySignificantOtherInvolvement = familySignificantOtherInvolvement;
		}
		public GuardianInvolvementInIndividualPlanOfService getGuardianInvolvementInIndividualPlanOfService() {
			return guardianInvolvementInIndividualPlanOfService;
		}
		public void setGuardianInvolvementInIndividualPlanOfService(
				GuardianInvolvementInIndividualPlanOfService guardianInvolvementInIndividualPlanOfService) {
			this.guardianInvolvementInIndividualPlanOfService = guardianInvolvementInIndividualPlanOfService;
		}
        
        
    }

    public static class Nursing {
    	private String id;
        private List<BehavioralProblem> behavioralProblems;
        private List<Medication> allMedications;
        private String createdAt;
        private String updatedAt;
        
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		public String getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
		public List<BehavioralProblem> getBehavioralProblems() {
			return behavioralProblems;
		}
		public void setBehavioralProblems(List<BehavioralProblem> behavioralProblems) {
			this.behavioralProblems = behavioralProblems;
		}
		public List<Medication> getAllMedications() {
			return allMedications;
		}
		public void setAllMedications(List<Medication> allMedications) {
			this.allMedications = allMedications;
		}
        
        
    }

    public static class MedicalPractitioner {
    	private String id;
        private List<MedicalProblem> medicalProblems;
        private List<ChronicStaticProblem> chronicStaticProblems;
        private String createdAt;
        private String updatedAt;
        
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		public String getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
		public List<MedicalProblem> getMedicalProblems() {
			return medicalProblems;
		}
		public void setMedicalProblems(List<MedicalProblem> medicalProblems) {
			this.medicalProblems = medicalProblems;
		}
		public List<ChronicStaticProblem> getChronicStaticProblems() {
			return chronicStaticProblems;
		}
		public void setChronicStaticProblems(List<ChronicStaticProblem> chronicStaticProblems) {
			this.chronicStaticProblems = chronicStaticProblems;
		}
        
    }

    public static class TreatmentTeamMemberSignature {
    	private String id;
        private String name;
        private String signature;
        private String date;
        private String time;
        private String createdAt;
        private String updatedAt;
        
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		public String getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
        
    }

    // LegalStatus.java
    public static class LegalStatus {
    	
    	private String currentStatus;
    	private String voluntary;
    	private String legalGuardian;
    	private String courtDate;
    	private String courtOrder;
    	private String collectionType;
    	private String address;
    	private String telephoneNumber;
    	
		public String getCourtDate() {
			return courtDate;
		}
		public void setCourtDate(String courtDate) {
			this.courtDate = courtDate;
		}
		public String getCourtOrder() {
			return courtOrder;
		}
		public void setCourtOrder(String courtOrder) {
			this.courtOrder = courtOrder;
		}
		public String getCurrentStatus() {
			return currentStatus;
		}
		public void setCurrentStatus(String currentStatus) {
			this.currentStatus = currentStatus;
		}
		public String getVoluntary() {
			return voluntary;
		}
		public void setVoluntary(String voluntary) {
			this.voluntary = voluntary;
		}
		public String getLegalGuardian() {
			return legalGuardian;
		}
		public void setLegalGuardian(String legalGuardian) {
			this.legalGuardian = legalGuardian;
		}
		public String getCollectionType() {
			return collectionType;
		}
		public void setCollectionType(String collectionType) {
			this.collectionType = collectionType;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTelephoneNumber() {
			return telephoneNumber;
		}
		public void setTelephoneNumber(String telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}
    	
    }

    // TherapistRecorder.java
    public static class PatientStrengthAssets {
        
    	private String id;
    	private String patientStrengthsAssets;
    	private String liabilities;
    	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPatientStrengthsAssets() {
			return patientStrengthsAssets;
		}
		public void setPatientStrengthsAssets(String patientStrengthsAssets) {
			this.patientStrengthsAssets = patientStrengthsAssets;
		}
		public String getLiabilities() {
			return liabilities;
		}
		public void setLiabilities(String liabilities) {
			this.liabilities = liabilities;
		}
    	
    }

    // DischargeContinuingCarePlan.java
    public static class DischargeContinuingCarePlan {
    	
    	private LivingArrangement livingArrangement;
    	private WorkSchool workSchool;
    	private MentalHealthTreatmentAppoinment mentalHealthTreatmentAppoinment;
    	private SupportGroup supportGroup;
    	private MedicalAftercareRecommendationsReferral medicalAftercareRecommendationsReferral;

		public LivingArrangement getLivingArrangement() {
			return livingArrangement;
		}

		public void setLivingArrangement(LivingArrangement livingArrangement) {
			this.livingArrangement = livingArrangement;
		}

		public WorkSchool getWorkSchool() {
			return workSchool;
		}

		public void setWorkSchool(WorkSchool workSchool) {
			this.workSchool = workSchool;
		}

		public MentalHealthTreatmentAppoinment getMentalHealthTreatmentAppoinment() {
			return mentalHealthTreatmentAppoinment;
		}

		public void setMentalHealthTreatmentAppoinment(MentalHealthTreatmentAppoinment mentalHealthTreatmentAppoinment) {
			this.mentalHealthTreatmentAppoinment = mentalHealthTreatmentAppoinment;
		}

		public SupportGroup getSupportGroup() {
			return supportGroup;
		}

		public void setSupportGroup(SupportGroup supportGroup) {
			this.supportGroup = supportGroup;
		}

		public MedicalAftercareRecommendationsReferral getMedicalAftercareRecommendationsReferral() {
			return medicalAftercareRecommendationsReferral;
		}

		public void setMedicalAftercareRecommendationsReferral(
				MedicalAftercareRecommendationsReferral medicalAftercareRecommendationsReferral) {
			this.medicalAftercareRecommendationsReferral = medicalAftercareRecommendationsReferral;
		}
    	
    }
    
    public static class WorkSchool{
    	
    	private String returnToJobAt;
    	private String returnJobOn;
    	private String returnToSchoolAt;
    	private String returnSchoolOn;
		public String getReturnToJobAt() {
			return returnToJobAt;
		}
		public void setReturnToJobAt(String returnToJobAt) {
			this.returnToJobAt = returnToJobAt;
		}
		public String getReturnJobOn() {
			return returnJobOn;
		}
		public void setReturnJobOn(String returnJobOn) {
			this.returnJobOn = returnJobOn;
		}
		public String getReturnToSchoolAt() {
			return returnToSchoolAt;
		}
		public void setReturnToSchoolAt(String returnToSchoolAt) {
			this.returnToSchoolAt = returnToSchoolAt;
		}
		public String getReturnSchoolOn() {
			return returnSchoolOn;
		}
		public void setReturnSchoolOn(String returnSchoolOn) {
			this.returnSchoolOn = returnSchoolOn;
		}
    	
    }

    public static class MentalHealthTreatmentAppoinment {
    	private String agency;
    	private String telephone;
    	private String therapist;
    	private String psychiatrist;
    	private String address;
		public String getAgency() {
			return agency;
		}
		public void setAgency(String agency) {
			this.agency = agency;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getTherapist() {
			return therapist;
		}
		public void setTherapist(String therapist) {
			this.therapist = therapist;
		}
		public String getPsychiatrist() {
			return psychiatrist;
		}
		public void setPsychiatrist(String psychiatrist) {
			this.psychiatrist = psychiatrist;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
    	
    	
    }
    
    private static class SupportGroup {
    	
    	private String recommendationsReferral;
    	private String telephone;
    	private String address;
    	private String location;
		public String getRecommendationsReferral() {
			return recommendationsReferral;
		}
		public void setRecommendationsReferral(String recommendationsReferral) {
			this.recommendationsReferral = recommendationsReferral;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
    	
    }
    
    private static class MedicalAftercareRecommendationsReferral {
    	
    	private String problem;
    	private String primaryCarePhysician;
    	private String telephone;
    	private String address;
    	private String otherCare;
    	private String dietANdAftercareRecommentations;
    	private String recreationalTherapyRecommentations;
		public String getProblem() {
			return problem;
		}
		public void setProblem(String problem) {
			this.problem = problem;
		}
		public String getPrimaryCarePhysician() {
			return primaryCarePhysician;
		}
		public void setPrimaryCarePhysician(String primaryCarePhysician) {
			this.primaryCarePhysician = primaryCarePhysician;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getOtherCare() {
			return otherCare;
		}
		public void setOtherCare(String otherCare) {
			this.otherCare = otherCare;
		}
		public String getDietANdAftercareRecommentations() {
			return dietANdAftercareRecommentations;
		}
		public void setDietANdAftercareRecommentations(String dietANdAftercareRecommentations) {
			this.dietANdAftercareRecommentations = dietANdAftercareRecommentations;
		}
		public String getRecreationalTherapyRecommentations() {
			return recreationalTherapyRecommentations;
		}
		public void setRecreationalTherapyRecommentations(String recreationalTherapyRecommentations) {
			this.recreationalTherapyRecommentations = recreationalTherapyRecommentations;
		}
    	
    	
    }
    
    public static class LivingArrangement {
        
    	private String livingArrangements;
    	private String phoneNumber;
    	private String address;
    	private String independentLiving;
    	private String modeOfTransportation;
		public String getLivingArrangements() {
			return livingArrangements;
		}
		public void setLivingArrangements(String livingArrangements) {
			this.livingArrangements = livingArrangements;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getIndependentLiving() {
			return independentLiving;
		}
		public void setIndependentLiving(String independentLiving) {
			this.independentLiving = independentLiving;
		}
		public String getModeOfTransportation() {
			return modeOfTransportation;
		}
		public void setModeOfTransportation(String modeOfTransportation) {
			this.modeOfTransportation = modeOfTransportation;
		}
    	
    	
    }
    
    // FamilySignificantOtherInvolvement.java
    public static class FamilySignificantOtherInvolvement {
        
    	private FamilyContact familyContact;
    	private Interventions interventions;
		public FamilyContact getFamilyContact() {
			return familyContact;
		}
		public void setFamilyContact(FamilyContact familyContact) {
			this.familyContact = familyContact;
		}
		public Interventions getInterventions() {
			return interventions;
		}
		public void setInterventions(Interventions interventions) {
			this.interventions = interventions;
		}
    	
    }
    
    public static class GuardianInvolvementInIndividualPlanOfService {
    	
    	private String patientChooseNotToAttend;
    	private String attendedTheTreatmentTeamConference;
    	private String receivedACopyOfMyTreatmentPlan;
    	private String agreeWithMyTreatmentPlanUpdate;
    	private String patientComments;
    	private String patientDate;
    	private String patientSignature;
    	private String guardianReceivedCopyOfTreatmentPlan;
    	private String guardianComments;
    	private String guardianDate;
    	private String guardianSignature;
    	
		public String getPatientChooseNotToAttend() {
			return patientChooseNotToAttend;
		}
		public void setPatientChooseNotToAttend(String patientChooseNotToAttend) {
			this.patientChooseNotToAttend = patientChooseNotToAttend;
		}
		
		public String getPatientComments() {
			return patientComments;
		}
		public void setPatientComments(String patientComments) {
			this.patientComments = patientComments;
		}
		public String getPatientSignature() {
			return patientSignature;
		}
		public void setPatientSignature(String patientSignature) {
			this.patientSignature = patientSignature;
		}
		public String getGuardianReceivedCopyOfTreatmentPlan() {
			return guardianReceivedCopyOfTreatmentPlan;
		}
		public void setGuardianReceivedCopyOfTreatmentPlan(String guardianReceivedCopyOfTreatmentPlan) {
			this.guardianReceivedCopyOfTreatmentPlan = guardianReceivedCopyOfTreatmentPlan;
		}
		public String getGuardianComments() {
			return guardianComments;
		}
		public void setGuardianComments(String guardianComments) {
			this.guardianComments = guardianComments;
		}
		public String getGuardianSignature() {
			return guardianSignature;
		}
		public void setGuardianSignature(String guardianSignature) {
			this.guardianSignature = guardianSignature;
		}
		public String getAttendedTheTreatmentTeamConference() {
			return attendedTheTreatmentTeamConference;
		}
		public void setAttendedTheTreatmentTeamConference(String attendedTheTreatmentTeamConference) {
			this.attendedTheTreatmentTeamConference = attendedTheTreatmentTeamConference;
		}
		public String getReceivedACopyOfMyTreatmentPlan() {
			return receivedACopyOfMyTreatmentPlan;
		}
		public void setReceivedACopyOfMyTreatmentPlan(String receivedACopyOfMyTreatmentPlan) {
			this.receivedACopyOfMyTreatmentPlan = receivedACopyOfMyTreatmentPlan;
		}
		public String getAgreeWithMyTreatmentPlanUpdate() {
			return agreeWithMyTreatmentPlanUpdate;
		}
		public void setAgreeWithMyTreatmentPlanUpdate(String agreeWithMyTreatmentPlanUpdate) {
			this.agreeWithMyTreatmentPlanUpdate = agreeWithMyTreatmentPlanUpdate;
		}
		public String getPatientDate() {
			return patientDate;
		}
		public void setPatientDate(String patientDate) {
			this.patientDate = patientDate;
		}
		public String getGuardianDate() {
			return guardianDate;
		}
		public void setGuardianDate(String guardianDate) {
			this.guardianDate = guardianDate;
		}
    	
    	
    }
    
    public static class FamilyContact {
        
    	private String familyContacted;
    	private String familyparticipatedInTreatmentPlanning;
    	private String familyDeclinesToParticipate;
    	private String patientDeclinesFamilyParticipationAtThis;
		public String getFamilyContacted() {
			return familyContacted;
		}
		public void setFamilyContacted(String familyContacted) {
			this.familyContacted = familyContacted;
		}
		public String getFamilyparticipatedInTreatmentPlanning() {
			return familyparticipatedInTreatmentPlanning;
		}
		public void setFamilyparticipatedInTreatmentPlanning(String familyparticipatedInTreatmentPlanning) {
			this.familyparticipatedInTreatmentPlanning = familyparticipatedInTreatmentPlanning;
		}
		public String getFamilyDeclinesToParticipate() {
			return familyDeclinesToParticipate;
		}
		public void setFamilyDeclinesToParticipate(String familyDeclinesToParticipate) {
			this.familyDeclinesToParticipate = familyDeclinesToParticipate;
		}
		public String getPatientDeclinesFamilyParticipationAtThis() {
			return patientDeclinesFamilyParticipationAtThis;
		}
		public void setPatientDeclinesFamilyParticipationAtThis(String patientDeclinesFamilyParticipationAtThis) {
			this.patientDeclinesFamilyParticipationAtThis = patientDeclinesFamilyParticipationAtThis;
		}
    	
    }
    
    public static class Interventions {
    	
    	private String phoneContact;
    	private String familyTherapy;
    	private String resourcesGiven;
    	private String familyMeeting;
    	private String education;
    	private String date;
    	private String other;
		public String getPhoneContact() {
			return phoneContact;
		}
		public void setPhoneContact(String phoneContact) {
			this.phoneContact = phoneContact;
		}
		public String getFamilyTherapy() {
			return familyTherapy;
		}
		public void setFamilyTherapy(String familyTherapy) {
			this.familyTherapy = familyTherapy;
		}
		public String getResourcesGiven() {
			return resourcesGiven;
		}
		public void setResourcesGiven(String resourcesGiven) {
			this.resourcesGiven = resourcesGiven;
		}
		public String getFamilyMeeting() {
			return familyMeeting;
		}
		public void setFamilyMeeting(String familyMeeting) {
			this.familyMeeting = familyMeeting;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getOther() {
			return other;
		}
		public void setOther(String other) {
			this.other = other;
		}
    	
    	
    }
    // BehavioralProblem.java
    public static class BehavioralProblem {
        private String problem;
        private String behavioralProblem;
		public String getProblem() {
			return problem;
		}
		public void setProblem(String problem) {
			this.problem = problem;
		}
		public String getBehavioralProblem() {
			return behavioralProblem;
		}
		public void setBehavioralProblem(String behavioralProblem) {
			this.behavioralProblem = behavioralProblem;
		}
        
    }

    // Medication.java
    public static class Medication {
        
    	private String name;
    	private String dose;
    	private String frequency;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDose() {
			return dose;
		}
		public void setDose(String dose) {
			this.dose = dose;
		}
		public String getFrequency() {
			return frequency;
		}
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
    	
    }

    // MedicalProblem.java
    public static class MedicalProblem {
        
    	private String id;
    	private String problem;
        private String activeMedicalProblem;
        
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getProblem() {
			return problem;
		}
		public void setProblem(String problem) {
			this.problem = problem;
		}
		public String getActiveMedicalProblem() {
			return activeMedicalProblem;
		}
		public void setActiveMedicalProblem(String activeMedicalProblem) {
			this.activeMedicalProblem = activeMedicalProblem;
		}
    }

    // ChronicStaticProblem.java
    public static class ChronicStaticProblem {
        
    	private String problem;
        private String chronicProblem;
		public String getProblem() {
			return problem;
		}
		public void setProblem(String problem) {
			this.problem = problem;
		}
		public String getChronicProblem() {
			return chronicProblem;
		}
		public void setChronicProblem(String chronicProblem) {
			this.chronicProblem = chronicProblem;
		}
    }

}
