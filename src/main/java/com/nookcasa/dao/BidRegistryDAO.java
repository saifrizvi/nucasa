package com.nookcasa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import com.nookcasa.vo.PropertyBid;

public class BidRegistryDAO extends BaseDAO {

	private final Logger log = Logger.getLogger(BidRegistryDAO.class.getName());

	public void saveBid(PropertyBid propertyBid) {
		//bidDataSource.put(propertyBid.getPropertyId(), propertyBid);
		
		long propertyId = propertyBid.getPropertyId();
		List<PropertyBid> bids = bidDataSource.get(propertyId);
		double bidPrice = propertyBid.getBidPrice();
		if(null == bids){
			log.info("Adding First Bid for PropertyId: "+ propertyId+" At Bid Price:" +bidPrice );
			bids = new CopyOnWriteArrayList<PropertyBid>();
			bids.add(propertyBid);
			bidDataSource.put(propertyId, bids);
		} else{
			log.info("Adding Subsequent Bid for PropertyId: "+ propertyId+" At Bid Price:" +bidPrice );
			bids.add(propertyBid);
		}
		
		log.info("Bid for property "+propertyId+" registered at "+ bidPrice );
	}

	public List<PropertyBid> getAllBids(long propertyId) {
		List<PropertyBid> bids = bidDataSource.get(propertyId);
		
		log.info("Property Id: "+ propertyId);
		for (PropertyBid propertyBid : bids) {
			log.info("Property Bid Price: "+ propertyBid.getBidPrice());
		}

		return bids;
	}
	
}
