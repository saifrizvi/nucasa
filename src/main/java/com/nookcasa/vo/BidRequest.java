package com.nookcasa.vo;

public class BidRequest {

	private long propertyId;
	private double price;
	private String userId;
	private String proposal;
	
	public BidRequest() {
	}
	
	public BidRequest(long propertyId, double price, String userId, String proposal) {
		this.propertyId = propertyId;
		this.price = price;
		this.userId = userId;
		this.proposal = proposal;
	}

	public long getPropertyId() {
		return propertyId;
	}

	public double getPrice() {
		return price;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getProposal() {
		return proposal;
	}

}
