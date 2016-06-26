package com.nookcasa.dao;

import static com.nookcasa.utils.ApplicationConstants.DEFAULT_BID_END_DATE;
import static com.nookcasa.utils.ApplicationConstants.DEFAULT_MAX_NO_OF_TENANTS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.nookcasa.vo.Property;
import com.nookcasa.exceptions.DataException;

public class PropertyDAO extends BaseDAO {

	private final AtomicLong propertyId;

	public PropertyDAO() {
		propertyId = new AtomicLong(1000);
	}

	public List<Property> getAllProperties() {
		List<Property> properties = new ArrayList<Property>();
		for (Property property : propertyDataSource) {
			properties.add(property);
		}

		return properties;
	}

	public Property addProperty(double startBidPrice) throws DataException {
		Property property;
		try {
			property = new Property(generateNextPropertyId(),DEFAULT_MAX_NO_OF_TENANTS, startBidPrice,DEFAULT_BID_END_DATE);
			propertyDataSource.add(property);
		} catch (Exception e) {
			throw new DataException("Exception while adding property to data store");
		}
		return property;
	}

	public boolean removeProperty(long propertyId) throws DataException {
		try {
			for (Property property : propertyDataSource) {
				if (property.getPropertyId() == propertyId) {
					propertyDataSource.remove(property);
				}
			}
		} catch (Exception e) {
			throw new DataException("Exception while removing property to data store");
		}
		return true;
	}

	public Property getPropertyById(long propertyId) throws DataException {
		Property returnPro = new Property(-1, DEFAULT_MAX_NO_OF_TENANTS, 0.0,
				DEFAULT_BID_END_DATE);
		try {
			for (Property property : propertyDataSource) {
				if (property.getPropertyId() == propertyId) {
					returnPro = property;
					break;
				}
			}
		} catch (Exception e) {
			throw new DataException("Exception while retrieving property.");
		}

		if (returnPro.getPropertyId() == -1) {
			throw new DataException("Exception while retrieving property.");
		}

		return returnPro;
	}

	// TODO Implement when DB is added by using DB sequence
	public long generateNextPropertyId() {
		return propertyId.incrementAndGet();
	}
}
