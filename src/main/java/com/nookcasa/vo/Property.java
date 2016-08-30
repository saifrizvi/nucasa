package com.nookcasa.vo;


import com.nookcasa.interfaces.KeyedItem;


public class Property implements KeyedItem{

	private long propertyId ;
	private int maxTenants;
	private double startBidPrice;
	private String endDateTime;
	private String description;
	private String title;
	private String address;

	public Property(long propertyId, int maxTenants, double bidPrice,String endDateTime, String description, String address, String title) {
		this.propertyId=propertyId;
		this.startBidPrice =bidPrice;
		this.maxTenants = maxTenants;
		this.endDateTime=endDateTime;
		this.description = description;
		this.title = title;
		this.address = address;
	} 
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Property(){
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

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public void setMaxTenants(int maxTenants) {
		this.maxTenants = maxTenants;
	}

	public void setStartBidPrice(double startBidPrice) {
		this.startBidPrice = startBidPrice;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Property [propertyId=" + propertyId + ", maxTenants="
				+ maxTenants + ", startBidPrice=" + startBidPrice
				+ ", endDateTime=" + endDateTime + ", description="
				+ description + ", title=" + title + ", address=" + address
				+ "]";
	}
}
