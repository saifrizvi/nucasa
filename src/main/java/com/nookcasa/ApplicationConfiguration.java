package com.nookcasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nookcasa.exceptions.DataException;
import com.nookcasa.exceptions.InvalidParametersException;

/**
 * Spring Boot Application Configuration
 *
 */
@SpringBootApplication
public class ApplicationConfiguration {
	private static final long PROPERTY_ID_100 = 100;

	public static void main(String[] args) throws InvalidParametersException {

		SpringApplication.run(ApplicationConfiguration.class, args);
		// test2();
		// test1();
	}

	public static void test2() throws DataException, InvalidParametersException {
		test1();
		// Register bid for two properties
		try {
			BidPlaceServiceImpl service = new BidPlaceServiceImpl();
			service.addProperty(1000);

			service.registerBid(PROPERTY_ID_100, 1500, 2, "");
			System.out
					.println("PropertyId 100 - First Bid Registration Successful");

			service.registerBid(PROPERTY_ID_100, 1200, 2, "");
			System.out
					.println("PropertyId 100 - Second Bid Registration Successful");

			service.registerBid(PROPERTY_ID_100, 1300, 2, "");
			System.out
					.println("PropertyId 100 - Third Property Bid Registration Successful");

			// Get Max bid price for property
			double maxBidPrice = service.getMaxBidPrice(PROPERTY_ID_100);
			System.out.println("Max price for Property: " + PROPERTY_ID_100
					+ " is: " + maxBidPrice);

		} catch (InvalidParametersException e) {
			e.printStackTrace();
		}
	}

	private static void test1() throws InvalidParametersException,
			DataException {
		test2();
		System.out.println("Test Register Bid Service: ");
		BidPlaceServiceImpl service = new BidPlaceServiceImpl();

		// Add 5 Properties to the Market
		for (int i = 1; i < 6; i++) {
			service.addProperty(1000 + (i * 50));
		}

		// Register bid for two properties
		boolean s11 = service.registerBid(100, 1500, 2, "");
		System.out.println("First Property Bid Registration Success: " + s11);

		boolean s12 = service.registerBid(100, 900, 2, "");
		System.out.println("First Property Bid Registration Success: " + s12);

		boolean s13 = service.registerBid(100, 1300, 2, "");
		System.out.println("First Property Bid Registration Success: " + s13);

		boolean s21 = service.registerBid(300, 1450, 2, "");
		System.out.println("Second Property Bid Registration Success: " + s21);

		// Get time to bid for each property on the market
		long timeToBid = 0l;
		for (int i = 1; i < 6; i++) {
			timeToBid = service.getTimeToBid(i * 100);
			System.out.println("Time left to bid for Property: " + i * 100
					+ " is: " + timeToBid);
		}

		// Get Starting price of all properties on the market
		double startPrice = 0;
		for (int i = 1; i < 6; i++) {
			startPrice = service.getStartBidPrice(i * 100);
			System.out.println("Start price for Property: " + i * 100 + " is: "
					+ startPrice);
		}

		// Get Max bid price for each property
		double maxBidPrice = 0;
		for (int i = 1; i < 6; i++) {
			maxBidPrice = service.getMaxBidPrice(i * 100);
			System.out.println("Max price for Property: " + i * 100 + " is: "
					+ maxBidPrice);
		}
	}
}
