package com.formation.computerdatabase.ui;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.formation.computerdatabase.service.CompanyDaoService;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.util.Formatter;
import com.formation.computerdatabase.util.Regexp;
import com.google.common.util.concurrent.Service;

public class Test {

	public static void main(String[] args) {
		//String REGEXP_DATE = "^[0-9]{2}[-][0-9]{2}[-][0-9]{4}$";
		//String REGEXP_DATE = "^[0-9]+$";
		//DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(REGEXP_DATE);
		
		/*
		SimpleDateFormat sp = new SimpleDateFormat("dd-MM-yy");
		sp.
		*/
		
		//LocalDate ld = LocalDate.parse("2016-03-29");
		ServiceFactory service = ServiceFactory.INSTANCE;
		CompanyDaoService companyService = service.getCompanyDaoServiceImpl();
		companyService.delete(44);
		
	}

}
