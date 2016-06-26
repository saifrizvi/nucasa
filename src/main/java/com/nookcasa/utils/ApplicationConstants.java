package com.nookcasa.utils;

import java.util.Date;

public class ApplicationConstants {

	private ApplicationConstants() {
	}
	
	public static final long DEFAULT_USER_ID = 777;
	
	public static final int DEFAULT_MAX_NO_OF_TENANTS = 2;
	
	public static final Date DEFAULT_BID_END_DATE = ApplicationUtils.getFutureDateAsDate(3);

}
