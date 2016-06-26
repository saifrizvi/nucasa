package com.nookcasa.vo;

import static com.nookcasa.utils.ApplicationConstants.DEFAULT_USER_ID;

import com.nookcasa.interfaces.KeyedItem;


public class PropertyBid implements KeyedItem{

	private long propertyId ;
	private int noOfTenants;
	private double bidPrice;
	private long userId;
	private String proposal;

	public PropertyBid(long propertyId, int noOfTenants, double bidPrice) {
		this.propertyId=propertyId;
		this.bidPrice=bidPrice;
		this.noOfTenants=noOfTenants;
		this.userId = DEFAULT_USER_ID;

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
	
	public long getUserId() {
		return userId;
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
