package com.MHC.Project.Request;

public class PreadmitRequest {
	private String expectedArrivalDateTime;
	private String expectedDischDateTime;
	public String getExpectedArrivalDateTime() {
		return expectedArrivalDateTime;
	}
	public void setExpectedArrivalDateTime(String expectedArrivalDateTime) {
		this.expectedArrivalDateTime = expectedArrivalDateTime;
	}
	public String getExpectedDischDateTime() {
		return expectedDischDateTime;
	}
	public void setExpectedDischDateTime(String expectedDischDateTime) {
		this.expectedDischDateTime = expectedDischDateTime;
	}
}
