package com.nookcasa.vo;

import com.nookcasa.interfaces.KeyedItem;

import java.util.Date;


public class Property implements KeyedItem{

	private long propertyId ;
	private int maxTenants;
	private double startBidPrice;
	private Date endDateTime;

	public Property(long propertyId, int maxTenants, double bidPrice,Date endDateTime) {
		this.propertyId=propertyId;
		this.startBidPrice =bidPrice;
		this.maxTenants = maxTenants;
		this.endDateTime=endDateTime;
	} 


	@Override
	public String getKey() {
		return String.valueOf(propertyId);
	}

	public long getPropertyId() {
		return propertyId;
	}

	public int getMaxTenants() {
		return maxTenants;
	}

	public double getStartBidPrice() {
		return startBidPrice;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}
}
