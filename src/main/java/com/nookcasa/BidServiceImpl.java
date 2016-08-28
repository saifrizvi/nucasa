package com.nookcasa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.util.CollectionUtils;

import com.nookcasa.dao.BidRegistryDAO;
import com.nookcasa.dao.PropertyDAO;
import com.nookcasa.exceptions.DataException;
import com.nookcasa.exceptions.InvalidParametersException;
import com.nookcasa.interfaces.BidService;
import com.nookcasa.vo.Property;
import com.nookcasa.vo.PropertyBid;

import static com.nookcasa.utils.ApplicationUtils.*;

public class BidServiceImpl implements BidService {

	private final Logger log = Logger.getLogger(BidServiceImpl.class.getName());
//	private final MappedCacheService<PropertyBid> bidCache = new MappedCacheServiceImpl<PropertyBid>();
//	private final MappedCacheService<Property> propertyCache = new MappedCacheServiceImpl<Property>();
	private final PropertyDAO propertyDAO = new PropertyDAO();
	private final BidRegistryDAO bidDAO = new BidRegistryDAO();

	@Override
	public List<Property> listAllProperties() {
		return propertyDAO.getAllProperties();
	}

	@Override
	public Property addProperty(Property request) throws DataException {

		Property property;
		if (null != request) {
			property = propertyDAO.addProperty(request);
//			propertyCache.add(new Property(propertyDAO.generateNextPropertyId(),DEFAULT_MAX_NO_OF_TENANTS, startBidPrice,DEFAULT_BID_END_DATE));
		} else {
			log.info("Property details - connot be null.");
			throw new DataException("Property details - connot be null.");
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
	public boolean registerBid(long propertyId, double price, String userId, String proposal) throws InvalidParametersException, DataException {
		PropertyBid propertyBid = validateBidDetails(propertyId, price, userId);
//		bidCache.add(propertyBid);
		bidDAO.saveBid(propertyBid);

		return true;
	}

	@Override
	public long getTimeToBid(long propertyId) throws InvalidParametersException, DataException {
		long timeLeftToBid = 0l;
		Date now = new Date();
		// List<Property> property =
		// propertyCache.get(String.valueOf(propertyId));

		Property property = propertyDAO.getPropertyById(propertyId);
		if (property != null) {
			timeLeftToBid = getStringDateAsTimeInMilliseconds(property.getEndDateTime(),"yyyy/MM/dd") - now.getTime();
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
	public Property getPropertyDetails(long propertyId) {

		Property property;
		try {
			property = propertyDAO.getPropertyById(propertyId);
			if (property == null) {
				throw new InvalidParametersException("Invalid property Id:" + propertyId);
			}
		} catch (Exception e) {
			throw new RuntimeException("Invalid property Id:" + propertyId);
		} 
		return property;
	}
	
	@Override
	public List<PropertyBid> getAllBids(long propertyId) throws InvalidParametersException {
		List<PropertyBid> bids = bidDAO.getAllBids(propertyId);
		return (null != bids? bids: new ArrayList<PropertyBid>());
	}

	@Override
	public double getMaxBidPrice(long propertyId) throws InvalidParametersException {
		log.info("Inside getMaxBidPrice()");
		double maxBidPrice = 0;
		// String propId = String.valueOf(propertyId);
		// List<PropertyBid> propertyBidList= bidCache.get(propId);

		List<PropertyBid> propertyBidList = bidDAO.getAllBids(propertyId);
		if (!CollectionUtils.isEmpty(propertyBidList)) {
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
		
		//If there are no bids then keep the max bid as the start bid price
		if(maxBidPrice == 0){
			try {
				log.info("No bid registered so far so returning default.");
				Property property = propertyDAO.getPropertyById(propertyId);
				maxBidPrice = property.getStartBidPrice();
			} catch (DataException e) {
				log.info("Exception in getMaxBidPrice - Cannot getproperty details with "+propertyId);
			}
		}
		
		log.info("Completed getMaxBidPrice()");
		return maxBidPrice;
	}

	private PropertyBid validateBidDetails(long propertyId, double price,String userId) throws InvalidParametersException, DataException {
		log.info("Inside validateBidDetails()");
		log.info("PropertyId: " +propertyId +" Price: " +price +" UserId: " +userId);
		Property property = propertyDAO.getPropertyById(propertyId);
		if (property != null) {
			if (!Double.isNaN(price) && price >= property.getStartBidPrice() && userId != null) {
				return new PropertyBid(propertyId, userId, price);
			} else {
				throw new InvalidParametersException("Invalid property details : Id=" + propertyId + " price: " + price + " userId: "+ userId);
			}
		} else {
			throw new InvalidParametersException("No property found with Id: " + propertyId);
		}
		
	}
}
