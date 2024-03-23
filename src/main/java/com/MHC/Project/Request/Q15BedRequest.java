package com.MHC.Project.Request;

import java.util.List;

public class Q15BedRequest {
	
	private String roomNoStart;
	private String roomNoEnd;
//	private String bedNo;
	private String oddOrEven;
	private String organization;
	private List<RoomBedQuantity> bedNoList; // Updated to use List instead of Map

	public List<RoomBedQuantity> getBedNoList() {
		return bedNoList;
	}

	public void setBedNoList(List<RoomBedQuantity> bedNoList) {
		this.bedNoList = bedNoList;
	}

	public String getOddOrEven() {
		return oddOrEven;
	}
	public void setOddOrEven(String oddOrEven) {
		this.oddOrEven = oddOrEven;
	}
	public String getRoomNoStart() {
		return roomNoStart;
	}
	public void setRoomNoStart(String roomNoStart) {
		this.roomNoStart = roomNoStart;
	}
	public String getRoomNoEnd() {
		return roomNoEnd;
	}
	public void setRoomNoEnd(String roomNoEnd) {
		this.roomNoEnd = roomNoEnd;
	}
//	public String getBedNo() {
//		return bedNo;
//	}
//	public void setBedNo(String bedNo) {
//		this.bedNo = bedNo;
//	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public static class RoomBedQuantity {

		private String roomNo;
		private int bedQuantity;

		public String getRoomNo() {
			return roomNo;
		}

		public void setRoomNo(String roomNo) {
			this.roomNo = roomNo;
		}

		public int getBedQuantity() {
			return bedQuantity;
		}
		
		public void setBedQuantity(int bedQuantity) {
			this.bedQuantity = bedQuantity;
		}
	}
}
