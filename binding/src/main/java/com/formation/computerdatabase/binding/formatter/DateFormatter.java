package com.formation.computerdatabase.binding.formatter;

import java.time.LocalDate;

import org.springframework.context.i18n.LocaleContextHolder;

public class DateFormatter {
	public final static String patternEN = "yyyy-MM-dd";
	public final static String patternFR = "dd-MM-yyyy";
	public final static LocalDate date1970 = LocalDate.parse("1970-01-01");
	
	public static String getDatePattern() {
		System.out.println(LocaleContextHolder.getLocale().toString());
		System.out.println(Lang.EN);
		return LocaleContextHolder.getLocale().toString().equals(Lang.EN) ? DateFormatter.patternEN : DateFormatter.patternFR;
	}
}