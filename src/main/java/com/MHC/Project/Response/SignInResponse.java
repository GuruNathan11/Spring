package com.MHC.Project.Response;

import java.util.Map;

public class SignInResponse {
    private Response message;
    private Map<String, Object> data;

    public SignInResponse(Response message, Map<String, Object> data) {
        this.message = message;
        this.data = data;
    }
    public Response getMessage() {
        return message;
    }
    public void setMessage(Response message) {
        this.message = message;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
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

        if ("MHC - 0208".equals(inputCode)) {
            response = new Response("MHC - 0200", "User Credentials verified successfully.");
        }
        
        if ("MHC - 0210".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff Active");
        }
        if ("MHC - 0211".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff InActive");
        }
        if ("MHC - 0212".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff Username deleted successfully");
        }
        if ("MHC - 0213".equals(inputCode)) {
            response = new Response("MHC - 0200", "Organization details deleted successfully");
        }
        
        if ("MHC - 0217".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff Registered successfully.");
        }
        if ("MHC - 0218".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff getall details");
        }
        if ("MHC - 0219".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff getActiveStaff details");
        }
        if ("MHC - 0220".equals(inputCode)) {
            response = new Response("MHC - 0200", "GetStaff particular details");
        }
        if ("MHC - 0221".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff Details Update successfully");
        }
        if ("MHC - 0222".equals(inputCode)) {
            response = new Response("MHC - 0200", "Staff Particular Role");
        }
        if ("MHC - 0227".equals(inputCode)) {
            response = new Response("MHC - 0200", "StaffPatient Assign Registered Successfully");
        }
        if ("MHC - 0228".equals(inputCode)) {
            response = new Response("MHC - 0200", "StaffPatient Assign getAll Details");
        }
        if ("MHC - 0229".equals(inputCode)) {
            response = new Response("MHC - 0200", "PatientStaff Assign Registered Successfully");
        }
        if ("MHC - 0230".equals(inputCode)) {
            response = new Response("MHC - 0200", "PatientStaff Assign getAll Details");
        }
        if ("MHC - 0231".equals(inputCode)) {
            response = new Response("MHC - 0200", "PatientStaff Assign getStaffId particular details");
        }
        if ("MHC - 0232".equals(inputCode)) {
            response = new Response("MHC - 0200", "PatientStaff Assign getPID particular details");
        }
        
        if("MHC - 0233".equals(inputCode)) {
        	response=new Response("MHC - 0200", "Get the Q15 Form Details Based on given Id");
        }
        if("MHC - 0234".equals(inputCode)) {
        	response=new Response("MHC - 0200", "List of Q15 Form");
        }
        if("MHC - 0235".equals(inputCode)) {
        	response=new Response("MHC - 0200", "Patient Location Updated Successfully");
        }
        if("MHC - 0236".equals(inputCode)) {
        	response=new Response("MHC - 0200", "Q15Form Updated Successfully");
        }
        if("MHC - 0237".equals(inputCode)) {
        	response=new Response("MHC - 0200", "Patient Activity Updated Successfully");
        }
        if("MHC - 0238".equals(inputCode)) {
        	response=new Response("MHC - 0200", "Q15 Location Registered Successfully");
        }
        if("MHC - 0239".equals(inputCode)) {
        	response=new Response("MHC - 0200", "Q15 Registered Successfully");
        }
        if("MHC - 0240".equals(inputCode)) {
        	response=new Response("MHC - 0200", "Q15 Acitivity Registered Successfully");
        }
        
        if ("MHC - 0242".equals(inputCode)) {
            response = new Response("MHC - 0200", "Q15 config Details Registered Successfully");
        }
        if ("MHC - 0244".equals(inputCode)) {
            response = new Response("MHC - 0200", "Patient Visit Registered Successfully");
        }
        return response;
    }
}
