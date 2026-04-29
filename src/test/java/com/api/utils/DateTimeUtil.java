package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
	
	private DateTimeUtil() {
		
	}
	//Instant - Static class in Java
	public static String getDatetime(int days) {
		
		return Instant.now().minus(days, ChronoUnit.DAYS).toString();
	}

}
