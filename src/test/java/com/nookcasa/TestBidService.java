//package com.nookcasa;
//
//import static org.testng.AssertJUnit.assertEquals;
//import static org.testng.AssertJUnit.assertNotNull;
//import static org.testng.AssertJUnit.assertTrue;
//import static org.testng.AssertJUnit.fail;
//
//import java.util.logging.Logger;
//
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.nookcasa.exceptions.DataException;
//import com.nookcasa.exceptions.InvalidParametersException;
//
//
//public class TestBidService {
//
//	private static final int PROPERTY_ID_100 = 100;
//	private static final int PROPERTY_ID_200 = 200;
//	private static final int PROPERTY_ID_300 = 300;
//	private static final int PROPERTY_ID_400 = 400;
//	private final Logger log = Logger.getLogger(TestBidService.class.getName());
//	private BidPlaceServiceImpl service;
//
//	@BeforeClass
//	protected void setUp() throws Exception {
//		service = new BidPlaceServiceImpl();
//		// Add 5 Properties to the Market
//		for (int i = 1; i < 6; i++) {
//			service.addProperty(1000 + (i * 50));
//			log.info("PropertyId: " + i * 100 + " Registered Successfully");
//		}
//	}
//
//	@Test(groups = { "happy-path" })
//	public void timeLeftToBidTest() throws DataException {
//		// Get time to bid for each property on the market
//		long timeToBid = 0l;
//		for (int i = 1; i < 6; i++) {
//			try {
//				timeToBid = service.getTimeToBid(i * 100);
//				assertNotNull(timeToBid);
//				log.info("Time Left To Bid for Property: " + i * 100 + " is: "
//						+ timeToBid);
//			} catch (InvalidParametersException e) {
//				fail("InvalidParametersException encountered " + e.getMessage());
//			}
//		}
//	}
//
//	@Test(groups = { "happy-path" })
//	public void startBidPriceTest() throws DataException {
//		// Get Starting price of all properties on the market
//		double startPrice = 0;
//		for (int i = 1; i < 6; i++) {
//			try {
//				startPrice = service.getStartBidPrice(i * 100);
//				assertNotNull(startPrice);
//				log.info("Start price for Property: " + i * 100 + " is: "
//						+ startPrice);
//			} catch (InvalidParametersException e) {
//				fail("InvalidParametersException encountered " + e.getMessage());
//			}
//		}
//	}
//
//	@Test(groups = { "happy-path" })
//	public void registerBidTest1() {
//		// Register bid for two properties
//		try {
//			boolean s11 = service.registerBid(PROPERTY_ID_100, 1500, 2,"");
//			assertTrue(s11);
//			log.info("PropertyId 100 - First Bid Registration Successful");
//		} catch (InvalidParametersException e) {
//			fail("InvalidParametersException encountered " + e.getMessage());
//		} catch (DataException e) {
//			fail("DataException encountered " + e.getMessage());
//		}
//	}
//
//	@Test(groups = { "happy-path" })
//	public void registerBidExceptionTest() {
//		// Register bid for two properties
//		try {
//			boolean s12 = service.registerBid(PROPERTY_ID_200, 900, 2, "");
//			assertTrue(s12);
//		} catch (InvalidParametersException e) {
//			log.info("PropertyId 200 - Bid Registration Below Minimun Price");
//			assertNotNull(e);
//		} catch (DataException e) {
//			fail("DataException encountered " + e.getMessage());
//		}
//	}
//
//	@Test(groups = { "happy-path" })
//	public void maxBidPriceTest1() {
//		// Register bid for two properties
//		try {
//			boolean s11 = service.registerBid(PROPERTY_ID_400, 1500, 2, "");
//			assertTrue(s11);
//			log.info("PropertyId 100 - First Bid Registration Successful");
//
//			boolean s12 = service.registerBid(PROPERTY_ID_400, 1200, 2, "");
//			assertTrue(s12);
//			log.info("PropertyId 100 - Second Bid Registration Successful");
//
//			boolean s13 = service.registerBid(PROPERTY_ID_400, 1300, 2, "");
//			assertTrue(s13);
//			log.info("PropertyId 100 - Third Property Bid Registration Successful");
//
//			// Get Max bid price for property
//			double maxBidPrice = service.getMaxBidPrice(PROPERTY_ID_400);
//			assertEquals(1500.0, maxBidPrice);
//			log.info("Max price for Property: " + PROPERTY_ID_400 + " is: "
//					+ maxBidPrice);
//
//		} catch (InvalidParametersException e) {
//			fail("InvalidParametersException encountered " + e.getMessage());
//		} catch (DataException e) {
//			fail("DataException encountered " + e.getMessage());
//		}
//	}
//
//	@Test(groups = { "happy-path" })
//	public void maxBidPrice2() {
//		// Register bid for two properties
//		try {
//			boolean s11 = service.registerBid(PROPERTY_ID_300, 2550, 3, "");
//			assertTrue(s11);
//			log.info("PropertyId 300 - First Bid Registration Successful");
//
//			boolean s12 = service.registerBid(PROPERTY_ID_300, 1200, 3, "");
//			assertTrue(s12);
//			log.info("PropertyId 300 - Second Bid Registration Successful");
//
//			boolean s13 = service.registerBid(PROPERTY_ID_300, 2500, 3, "");
//			assertTrue(s13);
//			log.info("PropertyId 300 - Third Property Bid Registration Successful");
//
//			double maxBidPrice = service.getMaxBidPrice(PROPERTY_ID_300);
//			assertEquals(2550.0, maxBidPrice);
//			log.info("Max price for Property: " + PROPERTY_ID_300 + " is: "
//					+ maxBidPrice);
//
//		} catch (InvalidParametersException e) {
//			fail("InvalidParametersException encountered " + e.getMessage());
//		} catch (DataException e) {
//			fail("DataException encountered " + e.getMessage());
//		}
//	}
//
//	@AfterClass
//	protected void tearDown() throws Exception {
//	}
//
//}
