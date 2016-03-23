package com.formation.computerdatabase.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

public class TestDeleteCompany {
	
	private static ComputerDaoServiceImpl computerService;
	private static CompanyDaoServiceImpl companyService;
	
	
	@BeforeClass
	public static void init() {
		computerService = ComputerDaoServiceImpl.INSTANCE;
		companyService = CompanyDaoServiceImpl.INSTANCE;
	}
	
	@Test
	public void test() {
		companyService.delete(44, computerService);
	}

}
