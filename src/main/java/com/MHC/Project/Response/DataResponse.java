package com.MHC.Project.Response;

public class DataResponse<T> {
    private Response message;
    private T data;

    public DataResponse(Response message, T data) {
        this.message = message;
        this.data = data;
    }

    public Response getMessage() {
        return message; 
    }

    public void setMessage(Response message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class Response {
        private String code;
        private String description;

        public Response(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
    
    
    public static Response createVerifyResponse(String inputCode) {
        Response response = null;

        if ("MHC - 0270".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: Data updated successfully.");
        } else if ("MHC - 0271".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: Data saved successfully.");
        } else if ("MHC - 0272".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: Data fetched successfully.");
        } else if ("MHC - 0173".equals(inputCode)) {
            response = new Response("MHC - 0173", "Error: Entered Invalid Staff ID.");
        } else if ("MHC - 0174".equals(inputCode)) {
            response = new Response("MHC - 0174", "Error: Entered Invalid Patient ID.");
        } else if ("MHC - 0175".equals(inputCode)) {
            response = new Response("MHC - 0175", "Error: No Active Patients Found with the specified criteria.");
        } else if ("MHC - 0176".equals(inputCode)) {
            response = new Response("MHC - 0176", "Error: No Active Patients Found.");
        } else if ("MHC - 0177".equals(inputCode)) {
            response = new Response("MHC - 0177", "Error: No Active Patients Found with no config data for the specified criteria.");
        }
        
        else if ("MHC - 0178".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: Staff-Patient Assigned Successfully.");
        } 
        else if ("MHC - 0179".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: All Patient-Staff Details Fetched Successfully.");
        } else if ("MHC - 0181".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: Patient Details Fetched Successfully");
        } 
         else if ("MHC - 0183".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: Staff Details Fetched Successfully");
        }
        
         else if ("MHC - 0184".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Patient discharged Successfully with the given id");
         }
         else if ("MHC - 0185".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Beacon details saved successfully..");
         }
        
        
        if ("MHC - 0223".equals(inputCode)) {
            response = new Response("MHC - 0200", "Organization Details Registered Successfully");
        }
        if ("MHC - 0224".equals(inputCode)) {
            response = new Response("MHC - 0200", "Get the List of Organization Details.");
        }
        if ("MHC - 0225".equals(inputCode)) {
            response = new Response("MHC - 0200", "Get the Organization Details based on id");
        }
        if ("MHC - 0226".equals(inputCode)) {
            response = new Response("MHC - 0200", "Organization Updated Successfully");
        }
        if ("MHC - 0227".equals(inputCode)) {
            response = new Response("MHC - 0200", "Get the Organization Name");
        }
        
         else if ("MHC - 0250".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Q15 Location registered successfully.");
         } 
         else if ("MHC - 0251".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get Q15 Form by Given Id .");
         }
         else if ("MHC - 0252".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: GetAll Q15 Form .");
         }
         else if ("MHC - 0253".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Q15 Location updated Successfully.");
         }
         else if ("MHC - 0254".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Q15 Form Registered Successfully.");
         }
         else if ("MHC - 0255".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get Q15 Form updated Successfully.");
         }
         else if ("MHC - 0256".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Activity registered Successfully .");
         }
         else if ("MHC - 0257".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Activity Updated Successfully .");
         }
         
         else if ("MHC - 0258".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Staff Registered Successfully .");
         }
         else if ("MHC - 0259".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get All Staff Details .");
         }
         else if ("MHC - 0260".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get ActiveStaff .");
         }
         else if ("MHC - 0261".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get particular Staff .");
         }
         else if ("MHC - 0262".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Staff Updated Successfully .");
         }
         else if ("MHC - 0263".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Staff Checkedin Successfully .");
         }
         else if ("MHC - 0264".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Staff Checked out Successfully .");
         }
         else if ("MHC - 0265".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get all staff by role .");
         }
         else if ("MHC - 0266".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Record Deleted successfully.");
         }
         else if ("MHC - 0268".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Staff Configuration registered Successfully.");
         }
         else if ("MHC - 0269".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Staff Configuration Updated Successfully.");
         } 
         else if ("MHC - 0275".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Bed assigned successfully.");
         } 
         else if ("MHC - 0273".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get Patient Staff Configuration by Date .");
         }
         else if ("MHC - 0274".equals(inputCode)) {
             response = new Response("MHC - 0200", "Success: Get All Patient Staff Configuration.");
         }
         else  if ("MHC - 280".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success: Patient Visit Registered Successfully");
        }else if ("MHC - 281".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success: Get the All list of PatientVisit Details");
		}else if ("MHC - 282".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success: Get the Particular Id PatientVisit Details");
		}else if ("MHC - 283".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success: Get the Particular Patientid PatientVisit Details");
		}else if ("MHC - 284".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:PatientVisit Details updated successfully");
		}else if ("MHC - 285".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:PatientVisit Details VisitEndDate updated successfully");
		}else if ("MHC - 286".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:PatientVisit Details ActivityEndDate updated successfully");
		}else if ("MHC - 287".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:PatientVisit Details ExpectedAdmitDateTime updated successfully");
		}else if ("MHC - 288".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:PatientVisit Details ExpectedDischDateTime updated successfully");
		}else if ("MHC - 289".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:PatientVisit Details AdmitDateTime updated successfully");
		}else if ("MHC - 290".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:PatientVisit Details DischargeDateTime updated successfully");
		}else if ("MHC - 291".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Patientvisit All get Activevisit details ");
		}else if ("MHC - 292".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Requested Data deleted succeessfully. ");
		}else if ("MHC - 293".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success: Get the Staff Name based on given Id. ");
		}				
		
		else  if ("MHC - 300".equals(inputCode)) {
            response = new Response("MHC - 0200", "Success:Laborder Registered Successfully");
        }else if ("MHC - 301".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Get all list Laborder Details");
		}else if ("MHC - 302".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Get ParticularId Laborder Details");
		}else if ("MHC - 303".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:LabOrder Update successfully");
		}else if ("MHC - 304".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Laborder stop date update succeessfully ");
		}else if ("MHC - 305".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Laborder Collectiondate Update succeessfully ");
		}else if ("MHC - 306".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Get the list of Access Control details..");
		}else if ("MHC - 307".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Get the Access Control details based on id..");
		}else if ("MHC - 308".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Get the Access Control details based on OrgName..");
		}else if ("MHC - 309".equals(inputCode)) {
			response = new Response("MHC - 0200", "Success:Access Control registered successfully..");
		}
        
        return response;
    }
}