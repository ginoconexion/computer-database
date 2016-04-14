package com.formation.computerdatabase.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.formation.computerdatabase.services.ServiceFactory;
import com.formation.computerdatabase.services.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

public class TestDeleteCompany {


	private static CompanyDaoServiceImpl companyService;
	private static ComputerDaoServiceImpl computerService;
	private static AbstractApplicationContext context;

	@BeforeClass
	public static void init() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		ServiceFactory service = (ServiceFactory) context.getBean("serviceFactory");
		companyService = service.getCompanyDaoServiceImpl();
		computerService = service.getComputerDaoServiceImpl();
	}
	@AfterClass
	public static void close() {
		context.close();
	}

	@Test
	public void test() {
		companyService.delete(43, computerService);
	}

}
