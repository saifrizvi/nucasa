package com.nookcasa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.nookcasa.dao.BidRegistryDAO;
import com.nookcasa.dao.PropertyDAO;
import com.nookcasa.exceptions.DataException;
import com.nookcasa.exceptions.InvalidParametersException;
import com.nookcasa.interfaces.BidService;
import com.nookcasa.vo.Property;
import com.nookcasa.vo.PropertyBid;

public class BidPlaceServiceImpl implements BidService {

	private final Logger log = Logger.getLogger(BidPlaceServiceImpl.class.getName());
//	private final MappedCacheService<PropertyBid> bidCache = new MappedCacheServiceImpl<PropertyBid>();
//	private final MappedCacheService<Property> propertyCache = new MappedCacheServiceImpl<Property>();
	private final PropertyDAO propertyDAO = new PropertyDAO();
	private final BidRegistryDAO bidDAO = new BidRegistryDAO();

	@Override
	public List<Property> listAllProperties() {
		return propertyDAO.getAllProperties();
	}

	@Override
	public Property addProperty(double startBidPrice) throws DataException {

		Property property;
		if (!Double.isNaN(startBidPrice)) {
			property = propertyDAO.addProperty(startBidPrice);
//			propertyCache.add(new Property(propertyDAO.generateNextPropertyId(),DEFAULT_MAX_NO_OF_TENANTS, startBidPrice,DEFAULT_BID_END_DATE));
		} else {
			log.info("Invalid property details - price: " + startBidPrice);
			throw new DataException("Invalid property details - price: "+ startBidPrice);
		}

		return property;
	}

	@Override
	public boolean removeProperty(long propertyId) throws DataException {
		if (propertyId > 0) {
			propertyDAO.removeProperty(propertyId);
			removeFromPropertyCache(propertyId);
		} else {
			log.info("Invalid property Id");
			throw new DataException("Invalid property Id");
		}

		return true;
	}

	private void removeFromPropertyCache(long propertyId) {
		// TODO Implement Remove from Chache
	}

	@Override
	public boolean registerBid(long propertyId, double price, int noOfTenants, String proposal) throws InvalidParametersException, DataException {
		PropertyBid propertyBid = validateBidDetails(propertyId, price, noOfTenants);
//		bidCache.add(propertyBid);
		bidDAO.saveBid(propertyBid);

		return true;
	}

	@Override
	public long getTimeToBid(long propertyId)
			throws InvalidParametersException, DataException {
		long timeLeftToBid = 0l;
		Date now = new Date();
		// List<Property> property =
		// propertyCache.get(String.valueOf(propertyId));

		Property property = propertyDAO.getPropertyById(propertyId);
		if (property != null) {
			timeLeftToBid = property.getEndDateTime().getTime() - now.getTime();
		} else {
			throw new InvalidParametersException("Invalid property Id:"
					+ propertyId);
		}
		return timeLeftToBid;
	}

	@Override
	public double getStartBidPrice(long propertyId)
			throws InvalidParametersException, DataException {
		double startBidPrice = Double.NaN;
		// List<Property> property =
		// propertyCache.get(String.valueOf(propertyId));

		Property property = propertyDAO.getPropertyById(propertyId);
		if (property != null) {
			startBidPrice = property.getStartBidPrice();
		} else {
			throw new InvalidParametersException("Invalid property Id:"
					+ propertyId);
		}
		return startBidPrice;
	}
	
	@Override
	public List<PropertyBid> getAllBids(long propertyId) throws InvalidParametersException {
		List<PropertyBid> bids = bidDAO.getAllBids(propertyId);
		return (null != bids? bids: new ArrayList<PropertyBid>());
	}

	@Override
	public double getMaxBidPrice(long propertyId) throws InvalidParametersException {
		double maxBidPrice = Double.NaN;
		// String propId = String.valueOf(propertyId);
		// List<PropertyBid> propertyBidList= bidCache.get(propId);

		List<PropertyBid> propertyBidList = bidDAO.getAllBids(propertyId);
		if (null != propertyBidList) {
			Optional<PropertyBid> propertyBid = propertyBidList.stream().max(
					new Comparator<PropertyBid>() {
						@Override
						public int compare(PropertyBid p1, PropertyBid p2) {
							if (p1.getBidPrice() < p2.getBidPrice()) {
								return -1;
							} else if (p1.getBidPrice() > p2.getBidPrice()) {
								return 1;
							}
							return 0;
						}
					});
			if (propertyBid.isPresent()) {
				maxBidPrice = propertyBid.get().getBidPrice();
			}
		}
		return maxBidPrice;
	}

	private PropertyBid validateBidDetails(long propertyId, double price,int noOfTenants) throws InvalidParametersException, DataException {
//		List<Property> property = propertyCache.get(String.valueOf(propertyId));
		Property property = propertyDAO.getPropertyById(propertyId);
		if (property != null) {
			if (!Double.isNaN(price) && price >= property.getStartBidPrice() && noOfTenants > 0 && noOfTenants <= property.getMaxTenants()) {
				return new PropertyBid(propertyId, noOfTenants, price);
			} else {
				throw new InvalidParametersException("Invalid property details : Id=" + propertyId + " price: " + price + " noOfTenants: "+ noOfTenants);
			}
		} else {
			throw new InvalidParametersException("No property found with Id: " + propertyId);
		}
	}

}
