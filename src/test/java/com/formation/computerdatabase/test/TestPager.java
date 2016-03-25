package com.formation.computerdatabase.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;

public class TestPager {
	
	private static Pager pager;
	private final static int nbEntries = 578;
	private final static int parPage = 10;
	private static CompanyDaoServiceImpl companyDaoImpl;
	
	
	@BeforeClass
	public static void init() {
		ServiceFactory service = ServiceFactory.INSTANCE;
		companyDaoImpl = service.getCompanyDaoServiceImpl();
		//pager = new Pager<>(parPage, 1, companyDaoImpl, new HashMap<>());
	}
	@AfterClass
	public static void close() {
		pager = null;
	}
	
	@Test
	public void testNbPages() {
		//assertEquals((int) Math.ceil(nbEntries/parPage), pager.getNbPages());
	}
}
