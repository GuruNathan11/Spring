package com.MHC.Project.Request;

import java.util.List;

public class UpdateQ15BedRequest {

	private String organization;
	private List<RoomBedQuantity> bedNoList;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public List<RoomBedQuantity> getBedNoList() {
		return bedNoList;
	}

	public void setBedNoList(List<RoomBedQuantity> bedNoList) {
		this.bedNoList = bedNoList;
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
