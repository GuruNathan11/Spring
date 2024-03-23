package com.MHC.Project.Error;

import java.util.List;

import com.MHC.Project.Model.Config;
import com.MHC.Project.Model.PatientVisitAdmit;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyResponse {

	private Response message;
	private List<Config> configs;
	private List<PatientVisitAdmit> patientvisit;
	private List<List<Config>> q15Report;
	
	public Response getMessage() {
		return message;
	}
	public void setMessage(Response message) {
		this.message = message;
	}
	public VerifyResponse(String code, String description) {
		this.message = new Response(code, description);
	}
	public void setData(List<Config> configs) {
		this.configs = configs;
	}
	public List<Config> getData() {
		return configs;
	}
	public List<PatientVisitAdmit> getPatientvisit() {
		return patientvisit;
	}
	public void setData1(List<PatientVisitAdmit> patientvisit) {
		this.patientvisit = patientvisit;
	}
	public List<List<Config>> getQ15Report() {
		return q15Report;
	}
	public void setData2(List<List<Config>> q15Report) {
		this.q15Report = q15Report;
	}
	public static class Response {
		private String code;
		private String description;

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
		
		public Response(String code, String description) {
			this.code = code;
			this.description = description;
		}
	}
}