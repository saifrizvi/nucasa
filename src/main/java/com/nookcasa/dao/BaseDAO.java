package com.nookcasa.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.nookcasa.vo.Property;
import com.nookcasa.vo.PropertyBid;

//Implement to get the Datasource
public abstract class BaseDAO {

	protected final List<Property> propertyDataSource;
	protected final ConcurrentHashMap<Long, List<PropertyBid>> bidDataSource;

	public BaseDAO() {
		// TODO Change when implementing the actual database
		propertyDataSource = new CopyOnWriteArrayList<Property>();
		bidDataSource = new ConcurrentHashMap<Long, List<PropertyBid>>();
	}
}
