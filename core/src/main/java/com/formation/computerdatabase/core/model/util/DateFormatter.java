package com.formation.computerdatabase.core.model.util;

import java.time.LocalDate;

import org.springframework.context.i18n.LocaleContextHolder;


public class DateFormatter {
	public final static String patternEN = "yyyy-MM-dd";
	public final static String patternFR = "dd-MM-yyyy";
	public final static LocalDate date1970 = LocalDate.parse("1970-01-01");
	public final static String EN = "en";
	public final static String FR = "fr";
	
	public static String getDatePattern() {
		
		return LocaleContextHolder.getLocale().toString().equals(FR) ? DateFormatter.patternFR : DateFormatter.patternEN;
	}
}
