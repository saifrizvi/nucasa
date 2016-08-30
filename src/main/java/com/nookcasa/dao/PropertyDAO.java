package com.nookcasa.dao;

import static com.nookcasa.utils.ApplicationConstants.*;
import static com.nookcasa.utils.ApplicationUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.StringUtils;

import com.nookcasa.exceptions.DataException;
import com.nookcasa.vo.Property;

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

	public Property addProperty(Property request) throws DataException {
		Property property;
		try {
			int maxTenants = (request.getMaxTenants() > 0) ? request.getMaxTenants() : DEFAULT_MAX_NO_OF_TENANTS;
			double startPrice = (request.getStartBidPrice() > 0) ? request.getStartBidPrice() : DEFAULT_START_BID_PRICE;
			String endDateTime = (!StringUtils.isEmpty(request.getEndDateTime())) ? request.getEndDateTime() : getDefaultBidEndDateAsString();
			String description = (!StringUtils.isEmpty(request.getDescription())) ? request.getDescription() : DEFAULT_DESCRIPTION;
			String title = (!StringUtils.isEmpty(request.getTitle())) ? request.getTitle() : DEFAULT_TITLE;
			String address = (!StringUtils.isEmpty(request.getAddress())) ? request.getAddress() : DEFAULT_ADDRESS;
			property = new Property(generateNextPropertyId(),maxTenants,startPrice,endDateTime,description, address, title);
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
		Property returnPro = new Property();
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
