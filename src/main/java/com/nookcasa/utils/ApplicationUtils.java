package com.nookcasa.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationUtils {

	private static final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String generatorRandomName() {

		Random rand = new Random();
		Set<String> identifiers = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		
		return builder.toString();
	}
	
	public static String getCurrentDateAsString() {

		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		   return dateFormat.format(cal.getTime());

	  }
	
	public static Date getCurrentDateAsDate() {
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		   return cal.getTime();
	  }

	public static Date getFutureDateAsDate(int futureDays) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, futureDays);
		return c.getTime();
	}
	
	public static int getRandomNumberWithinRange(int min, int max){
		return min + (int)(Math.random() * max);
	}
	
	public static String javaToJSON(Object object) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		//Object to JSON in file
//		mapper.writeValue(new File("Sample.json"), user);

		//Object to JSON in String
		String jsonInString = mapper.writeValueAsString(object);
		return jsonInString;
	}
	
//	public static void main(String args[]) throws JsonProcessingException{
//		System.out.println(javaToJSON(new BidRequest(1001, 1200, 2, "Test")));
//	}
}
