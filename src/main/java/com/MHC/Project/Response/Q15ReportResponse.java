package com.MHC.Project.Response;

import java.util.List;

import com.MHC.Project.Model.Q15Report;

public class Q15ReportResponse{
	
	private Q15ReportResponse1 message;
	private List<Q15Report> data;
	
	 public Q15ReportResponse(Q15ReportResponse1 message, List<Q15Report> data) {
	    	this.setMessage(message);
	    	this.setData(data);
		}

public Q15ReportResponse1 getMessage() {
		return message;
	}

	public void setMessage(Q15ReportResponse1 q15ReportResponse) {
		this.message = q15ReportResponse;
	}

public List<Q15Report> getData() {
		return data;
	}

	public void setData(List<Q15Report> consolidatedData) {
		this.data = consolidatedData;
	}

	public static class Q15ReportResponse1 {
	    private String code;
	    private String description;

	    public Q15ReportResponse1(String code, String description) {
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
}