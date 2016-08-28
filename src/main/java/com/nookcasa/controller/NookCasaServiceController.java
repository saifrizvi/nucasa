package com.nookcasa.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nookcasa.BidServiceImpl;
import com.nookcasa.exceptions.DataException;
import com.nookcasa.exceptions.InvalidParametersException;
import com.nookcasa.interfaces.BidService;
import com.nookcasa.vo.Property;
import com.nookcasa.vo.PropertyBid;

@Controller
public class NookCasaServiceController {

	private final Logger log = Logger.getLogger(NookCasaServiceController.class.getName());
	private BidService service = new BidServiceImpl();

	@CrossOrigin
	@RequestMapping(value = "/properties", method = RequestMethod.GET)
	public @ResponseBody List<Property> listAllProperties() {
		List<Property> response = service.listAllProperties();
		return response;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addProperty", method = RequestMethod.POST)
	public @ResponseBody Property addProperty(@RequestBody Property request) {
		Property property;
		try {
			property = service.addProperty(request);
		} catch (DataException e) {
			log.info("DataStorageException: Exception while adding Property");
			throw new RuntimeException(e.getMessage());
		}
		return property;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/removeProperty/{propertyId}", method = RequestMethod.GET)
	public @ResponseBody boolean removeProperty(@PathVariable(value = "propertyId") long propertyId) {
		try {
			service.removeProperty(propertyId);
		} catch (DataException e) {
			log.info("DataStorageException: Exception while deleting Property");
			throw new RuntimeException(e.getMessage());
		}
		
		return true;
//		return new BidRequest(1001, 1200, 3, "Test");
	}

	@CrossOrigin
	@RequestMapping(value = "/timeToBid/{propertyId}", method = RequestMethod.GET)
	public @ResponseBody long getTimeToBid(@PathVariable(value = "propertyId") long propertyId) {
		long timeToBid = 0l;
		try {
			timeToBid = service.getTimeToBid(propertyId);
		} catch (Exception e) {
			log.info("Exception while retrieving time to bid.");
			throw new RuntimeException(e.getMessage());
		}

		return timeToBid;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/propertyDetails/{propertyId}", method = RequestMethod.GET)
	public @ResponseBody Property getPropertyDetails(@PathVariable(value = "propertyId") long propertyId) {
		Property property = new Property();
		try {
			property = service.getPropertyDetails(propertyId);
		} catch (Exception e) {
			log.info("Exception while retrieving property details.");
			throw new RuntimeException(e.getMessage());
		}

		return property;
	}

	@CrossOrigin
	@RequestMapping(value = "/startBidPrice/{propertyId}", method = RequestMethod.GET)
	public @ResponseBody double getStartBidPrice(@PathVariable(value = "propertyId") long propertyId) {
		double startBidPrice = 0.0;
		try {
			startBidPrice = service.getStartBidPrice(propertyId);
		} catch (Exception e) {
			log.info("Exception while retrieving start bid price.");
			throw new RuntimeException(e.getMessage());
		}

		return startBidPrice;
	}

	@CrossOrigin
	@RequestMapping(value = "/registerBid", method = RequestMethod.GET)
	public @ResponseBody boolean registerBid(@RequestParam(value = "propertyId") long propertyId, @RequestParam(value = "price") double price,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "proposal") String proposal) {
		try {
			log.info("PropertyId: " +propertyId +" Price: " +price +" UserId: " +userId  +" Proposal: " +proposal );
			service.registerBid(propertyId,price,userId,proposal);
		} catch (InvalidParametersException e) {
			log.severe("Exception while validating bid details - InvalidParametersException");
			throw new RuntimeException(e.getMessage());
		} catch (DataException e) {
			log.severe("Exception while saving bid details- DataException");
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/bids/{propertyId}", method = RequestMethod.GET)
	public @ResponseBody List<PropertyBid> getAllBids(@PathVariable long propertyId) {
		List<PropertyBid> bids;
		try {
			bids = service.getAllBids(propertyId);
		} catch (Exception e) {
			log.info("Exception while retrieving max bid price.");
			throw new RuntimeException(e.getMessage());
		}

		return bids;
	}

	@CrossOrigin
	@RequestMapping(value = "/maxBidPrice/{propertyId}", method = RequestMethod.GET)
	public @ResponseBody double getMaxBidPrice(@PathVariable long propertyId) {
		double maxBidPrice = 0.0;
		try {
			maxBidPrice = service.getMaxBidPrice(propertyId);
		} catch (Exception e) {
			log.info("Exception while retrieving max bid price.");
			throw new RuntimeException(e.getMessage());
		}

		return maxBidPrice;
	}
}
