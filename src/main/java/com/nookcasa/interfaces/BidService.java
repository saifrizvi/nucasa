package com.nookcasa.interfaces;

import java.util.List;

import com.nookcasa.exceptions.DataException;
import com.nookcasa.exceptions.InvalidParametersException;
import com.nookcasa.vo.Property;
import com.nookcasa.vo.PropertyBid;


public interface 	BidService {

	public List<Property> listAllProperties();

	public Property addProperty(double startBidPrice) throws DataException;
	
	public boolean removeProperty(long propertyId) throws DataException;
	
	public boolean registerBid(long propertyId, double price, int noOfTenants, String proposal) throws InvalidParametersException, DataException;
	
	public List<PropertyBid> getAllBids(long propertyId) throws InvalidParametersException;
	
	public double getMaxBidPrice(long propertyId) throws InvalidParametersException, DataException;
	
	public long getTimeToBid(long propertyId) throws InvalidParametersException, DataException;

	public double getStartBidPrice(long propertyId) throws InvalidParametersException, DataException;

}
