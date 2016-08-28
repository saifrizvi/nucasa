package com.nookcasa.vo;

import static com.nookcasa.utils.ApplicationConstants.*;

import com.nookcasa.interfaces.KeyedItem;


public class PropertyBid implements KeyedItem{

	private long propertyId ;
	private int noOfTenants;
	private double bidPrice;
	private String userId;
	private String proposal;

	public PropertyBid(long propertyId, String userId, double bidPrice) {
		this.propertyId=propertyId;
		this.bidPrice=bidPrice;
		this.noOfTenants=DEFAULT_MAX_NO_OF_TENANTS;
		this.userId = userId;
	} 

	@Override
	public String getKey() {
		return String.valueOf(propertyId);
	}

	public long getPropertyId() {
		return propertyId;
	}

	public int getNoOfTenants() {
		return noOfTenants;
	}

	public double getBidPrice() {
		return bidPrice;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public void setNoOfTenants(int noOfTenants) {
		this.noOfTenants = noOfTenants;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getProposal() {
		return proposal;
	}

	@Override
	public String toString() {
		return "PropertyBid{" +
				"propertyId=" + propertyId +
				", noOfTenants=" + noOfTenants +
				", bidPrice=" + bidPrice +
				", userId=" + userId +
				", proposal=" + proposal +
				'}';
	}
}
