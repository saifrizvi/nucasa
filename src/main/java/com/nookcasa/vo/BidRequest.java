package com.nookcasa.vo;

public class BidRequest {

	private long propertyId;
	private double price;
	private int noOfTenants;
	private String proposal;
	
	public BidRequest() {
	}
	
	public BidRequest(long propertyId, double price, int noOfTenants, String proposal) {
		this.propertyId = propertyId;
		this.price = price;
		this.noOfTenants = noOfTenants;
		this.proposal = proposal;
	}

	public long getPropertyId() {
		return propertyId;
	}

	public double getPrice() {
		return price;
	}

	public int getNoOfTenants() {
		return noOfTenants;
	}

	public String getProposal() {
		return proposal;
	}

}
