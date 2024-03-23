package com.MHC.Project.Response;

import com.MHC.Project.Error.VerifyResponse;

public class VerifyResponseFactory {

	public static VerifyResponse createVerifyResponse(String inputCode) {
		VerifyResponse response = null;

		if ("MHC - 0001".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0001", "Error: Organization field is required.");
		} else if ("MHC - 0002".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0002", "Error: User already signed in. Please check.");
		} else if ("MHC - 0003".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0003", "Error: Account temporarily blocked. Please try again later.");
		} else if ("MHC - 0004".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0004", "Error: Invalid username and organization.");
		} else if ("MHC - 0005".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0005", "Error: Invalid username.");
		} else if ("MHC - 0006".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0006", "Error: Invalid organization.");
		} else if ("MHC - 0007".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0007", "Error: Invalid password.");
		} else if ("MHC - 0008".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0008", "Error: Given SSN already registered");
		} else if ("MHC - 0009".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0009", "Error: Invalid username or password.");
		} else if ("MHC - 0010".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0010", "Error: Invalid JWT token.");
		} else if ("MHC - 0011".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0011", "Error: Invalid secret key.");
		} else if ("MHC - 0012".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0012", "Error: Failed to retrieve user details from JWT token.");
		} else if ("MHC - 0013".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0013", "Error: Invalid or expired token.");
		} else if ("MHC - 0014".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0014", "Error: Email not found.");
		} else if ("MHC - 0015".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0015", "Error: Invalid OTP or OTP has expired.");
		} else if ("MHC - 0016".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0016", "Error: Passwords do not match.");
		} else if ("MHC - 0017".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0017", "Error: User not found.");
		} else if ("MHC - 0018".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0018", "Error: Email and new password are required fields!");
		} else if ("MHC - 0019".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0019", "Error: Invalid Secret Key.");
		} else if ("MHC - 0020".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0020", "Error: User is not signed in or session expired.");
		} else if ("MHC - 0021".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0021", "Error: JWT token is invalid or not provided.");
		} else if ("MHC - 0022".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0022", "Error: Invalid ssn Number.");
		} else if ("MHC - 0023".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0023", "Error: Username already taken.");
		} else if ("MHC - 0024".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0024", "Error: Email already in use.");
		} else if ("MHC - 0025".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0025", "Error: Invalid organization ID.");
		} else if ("MHC - 0026".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0026", "Error: PatientID already exists.");
		} else if ("MHC - 0027".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0027", "Error: Patient with the given patientId not found.");
		} else if ("MHC - 0028".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0028", "Error: Patient Not Found With the Given SSN.");
		} else if ("MHC - 0029".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0029", "Error: No patients found with givenName.");
		} else if ("MHC - 0030".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0030",
					"Error: No patients found with the combination of given name  and family name .");
		} else if ("MHC - 0031".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0031",
					"Error: No patients found with the combination of given name, family name and birthDate .");
		} else if ("MHC - 0032".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0032", "Error: No patients found with given birth date .");
		} else if ("MHC - 0033".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0033", "Error: Patient not found with username .");
		} else if ("MHC - 0034".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0034", "Error: Patient with the given patientId not found.");
		} else if ("MHC - 0035".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0035", "Error: Patient with the given patientId not found.");
		} else if ("MHC - 0036".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0036", "Error: Patient with the given patientId not found.");
		} else if ("MHC - 0037".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0037", "Error: Patient with the given patientId not found.");
		} else if ("MHC - 0038".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0038", "Error: given staffId does not exists.");
		} else if ("MHC - 0039".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0039", "Error: Staff With given staffId not found.");
		} else if ("MHC - 0040".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0040", "Error: username of staff not found.");
		} else if ("MHC - 0041".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0041", "Error: given Beacon device id not found.");
		} else if ("MHC - 0044".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0044", "Error: Staff not found with given role.");
		} else if ("MHC - 0045".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0045", "Error: Patientvisit with patientvisitId not found.");
		} else if ("MHC - 0046".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0046", "Error: Patientvisit with patientvisitId not found.");
		} else if ("MHC - 0047".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0047", "Error: Beacon device Name already exist.");
		} else if ("MHC - 0048".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0048", "Error: No Data found for the given Id.");
		} else if ("MHC - 0049".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0049", "Error: You have entered wrong password.");
		} else if ("MHC - 0050".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0050", "Error: New password and confirmation didn't match.");
		}else if ("MHC - 0051".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0051", "Error: No  bed found for the given bedId");
		}else if ("MHC - 0052".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0052", "Error: check your organization");
		}else if ("MHC - 0053".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0052", "Error: organization already registered");
		}
		else if ("MHC - 0054".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0054", "Error: The Given patientId does not Exists");
		} else if ("MHC - 0055".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0055", "Error: Patientvisit Given patientid does not exists");
		} else if ("MHC - 0056".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0056", "Error: No Patients Assign for this Staff Id.");
		} else if ("MHC - 0057".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0057", "Error: The Given patientId does not Exists.");
		} else if ("MHC - 0058".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0058", "Error: The Given patientId does not Exists.");
		} else if ("MHC - 0059".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0059", "Error: Organization name already exists .");
		} else if ("MHC - 0060".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0060", "Error: Organization Name Already Exists.");
		} else if ("MHC - 0061".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0061", "Error: Organization Id Could Not be found .");
		} else if ("MHC - 0064".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0064", "Error: No Activevisit Could Not be found .");
	    }else if ("MHC - 0065".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0065", "Error: PatientVisit with the same visitStartDate already exists.");
	    }
	    else if ("MHC - 0066".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0066", "Error: Laborder Id could not found.");
	    }
	    else if ("MHC - 0067".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0067", "Error: No active staff found.");
	    }else if ("MHC - 0068".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0068", "Error: unassigned patients could not be found.");
	    }
	    else if ("MHC - 0069".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0069", "Error: No active patients found.");
	    }
	    else if ("MHC - 0070".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0070", "Error: no patients are active based on given organization.");
	    }else if ("MHC - 0071".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0071", "Error: bed is occupied.");
	    }
	    else if ("MHC - 0072".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0072", "Error: Bed is already assigned for the patient."); 
	    }
	    else if ("MHC - 0073".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0073", "Error: Patient not found.");
	    }
	    else if ("MHC - 0074".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0074", "Error: No data found based on the given date.");
	    } else if ("MHC - 0075".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0075", "Error: No bed data found.");
	    }
	    else if ("MHC - 0076".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0076", "Error: bed is not occupied.");
	    }
	    else if ("MHC - 0077".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0077", "Error: no bed found for given bed Id.");
	    } else if ("MHC - 0078".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0078", "Error: no data found.");
	    } else if ("MHC - 0079".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0079", "Error: Entered field Name Already Exists.");
	    }

		else if ("MHC - 0080".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0080", "Error: No records found for the given PID.");
		} else if ("MHC - 0081".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0081", "Error: The given Q15Slot can not be find");
		} else if ("MHC - 0082".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0082", "Error: The given PID can not be find");
		}	else if ("MHC - 0083".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0083", "Error: No records found in this Q15 Config");
		}
		else if ("MHC - 0084".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0084", "Error: Given PID does not Exists"); 
		}
		else if ("MHC - 0085".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0085", "Error: Given Staff Name does not Exixts");
		} else if ("MHC - 0086".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0086", "Error: Entered field Name Already Exists.");
	    }
		else if ("MHC - 0087".equals(inputCode)) {
			  response = new VerifyResponse("MHC - 0087", "Error: Entered Form Name Already Exist.");
	    }
		
		
		else if ("MHC - 0089".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0089", "Error: No records found for the given id: ");
		}
		else if ("MHC - 0090".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0090", "Error: The Given Id Does not Exists.");
		} else if ("MHC - 0091".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0091", "Error: The Given Q15Slot And Q15Date Does not Exists.");
		} else if ("MHC - 0092".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0092", "Error: The Given PSConfiguration Id Does not Exists.");
		}
		else if ("MHC - 0093".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0093", "Error: No record found for given date.");
		}
		else if ("MHC - 0094".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0094", "Error: Vital Id Not found.");
		}
		else if ("MHC - 0095".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0095", "Error: Allergy Id Not found.");
		}
		else if ("MHC - 0096".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0096", "Error: Problem ID not found.");
		}else if ("MHC - 0097".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0097", "Error:Laborder given PatientId Could Not found ");
		}else if ("MHC - 0098".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0098", "Error:Laborder given StaffId Could Not found ");
		} else if ("MHC - 0099".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0099", "Error: Requested patient was already assigned with the bed.");
		}else if ("MHC - 0100".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0100", "Error: Requested Bed was already occupied.");
		}
		else if ("MHC - 0102".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0102", "Error: IndexForm has no existing contents. ");
		}else if ("MHC - 0103".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0103", "Error: SubHeading not found. ");
		} else if ("MHC - 0104".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0104", "Error: Content not found.");
		}else if ("MHC - 0105".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0105", "Error: IndexForm not found.");
		} else if ("MHC - 0106".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0106", "Error: Enter any value.");
		} else if ("MHC - 0107".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0107", "Error: Given room was already there.");
		} else if ("MHC - 0108".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0108", "Error: Given Patient already assign with another bed.");
		}
		
		else if ("MHC - 0180".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0180", "Error: Staff Id does not Exists.");      
        }
		else if ("MHC - 0181".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0181", "Error: Given Id Doesn't Exists in Bedconfig.");      
        } else if ("MHC - 0182".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0182", "Error: Patient Id does not Exists.");
		}else if ("MHC - 0183".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0183", "Error: Field not found in the form.");
		}
        else if ("MHC - 0184".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0184", "Error: Form not found with the given form name.");
		}
        else if ("MHC - 0185".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0185", "Error: Given id does not found any field.");
		}
        else if ("MHC - 0186".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0186", "Error: Mac Address already registered.");
		}
        else if ("MHC - 0187".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0187", "Error: No device found for given deviceId.");
		}
        else if ("MHC - 0188".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0188", "Error: patient could not be found for given organizationId");
		}
        else if ("MHC - 0189".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0189", "Error: given uuid already registered");
		}
        else if ("MHC - 0190".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0190", "Error: uuid could not be found. chcek the table");
		}
        else if ("MHC - 0191".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0191", "Error: deviceID could not be found. chcek the sensor table");
		}
        else if ("MHC - 0192".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0192", "Error: given deviceID already registered.");
		}
        else if ("MHC - 0193".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0193", "Error: given device Name already registered.");
		}
        else if ("MHC - 0194".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0194", "Error: given scheduleId could not found.");
		}
        else if ("MHC - 0195".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0195", "Error: given deviceId already registered with another patient.");
		}
        else if ("MHC - 0196".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0196", "Error: given roomNo and bedNo in the organization already created.");
		}
        else if ("MHC - 0197".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0197", "Error: bed is assigned for the given pid.");
		}
        else if ("MHC - 0198".equals(inputCode)) {
            response = new VerifyResponse("MHC - 0198", "Error: No beds found for the given roomNo and organization.");
		} 
		
		
		
		else if ("MHC - 0201".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Login successful.");
		} else if ("MHC - 0202".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: You've been signed out.");
		} else if ("MHC - 0203".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: OTP sent to your regostered mail. please check your inbox.");
		} else if ("MHC - 0204".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: OTP Verified Successfully.");
		} else if ("MHC - 0205".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Password Reset Successfully.");
		} else if ("MHC - 0206".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200",
					"Success: Secret key reset successfully. New Secret Key sent to your Registered Email.");
		} else if ("MHC - 0207".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: User is signed in.");
		} else if ("MHC - 0208".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: User Credentials Verified Successfully.");
		} else if ("MHC - 0209".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Data Deleted Successfully.");
		} else if ("MHC - 0210".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Staff active.");
		} else if ("MHC - 0211".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Staff Inactive.");
		} else if ("MHC - 0212".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Staff Details Deleted Successfully.");
		} else if ("MHC - 0213".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Organization Details Deleted Successfully");
		} else if ("MHC - 214".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: The given Q15-Slot As follows");
		} else if ("MHC - 0215".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: The given Q15Form Config as follows");
		} else if ("MHC - 216".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: The given PID Deleted.");
		} else if ("MHC - 217".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: The given Id with the data Deleted.");
		} else if ("MHC - 0218".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Account unblocked successfully.");
		}
		
		
		
		
		else if ("MHC - 241".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: The given Q15 Config as follows");
	    } else if ("MHC - 242".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Data fetched Successfully.");
	    } else if ("MHC - 243".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Beacon Device added successfully.");
	    } else if ("MHC - 245".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Get the list of PatientVisit Details");
		} else if ("MHC - 246".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Get the Particular Id PatientVisit Details");
		} else if ("MHC - 247".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Get the Particular Patientid PatientVisit Details");
		} else if ("MHC - 248".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: PatientVisit Details updated successfully");
		} else if ("MHC - 249".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: PatientVisit Details VisitEndDate updated successfully");
		} else if ("MHC - 250".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: PatientVisit Details ActivityEndDate updated successfully");
		} else if ("MHC - 251".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: PatientVisit Details ExpectedAdmitDateTime updated successfully");
		} else if ("MHC - 252".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: PatientVisit Details ExpectedDischDateTime updated successfully");
		} else if ("MHC - 253".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: PatientVisit Details AdmitDateTime updated successfully");
		} else if ("MHC - 254".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: PatientVisit Details DischargeDateTime updated successfully");
		} else if ("MHC - 292".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Patientvisit details delete succeessfully ");
		} else if ("MHC - 271".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: List of Patient based on Q15-Slot and Q15Date As follows");
		} else if ("MHC - 272".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: LabOrder Details deleted successfully");
		} else if ("MHC - 0273".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Bed Config Details deleted successfully");
		}else if ("MHC - 0274".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Bed Details deleted successfully");
		}
		else if ("MHC - 0275".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Error: Given Id Doesn't Exists in Bed.");
		} else if ("MHC - 0276".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Bed has been unavailable for the given bedId");
		} else if ("MHC - 0277".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0200", "Success: Bed has been available for the given bedId");
		} else if ("MHC - 0280".equals(inputCode)) {
			response = new VerifyResponse("MHC - 0280", "Error: Invalid username or secretkey.");
		}

		

		return response;
	}

}