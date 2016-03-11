package com.formation.computerdatabase.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.model.Company;

public class TestPager {

	private static Pager pager;
	private final static int nbEntries = 578;
	private final static int parPage = 10;
	private static CompanyDaoServiceImpl companyDaoImpl;
	
	
	@BeforeClass
	public static void init() {
		ServiceFactory service = ServiceFactory.INSTANCE;
		companyDaoImpl = service.getCompanyDaoServiceImpl();
		pager = new Pager(nbEntries, parPage, companyDaoImpl);
	}
	@AfterClass
	public static void close() {
		pager = null;
	}
	
	@Test
	public void testNbPages() {
		assertEquals((int) Math.ceil(nbEntries/parPage), pager.getNbPages());
	}
	
	@Test
	public void testNext(){
		pager.setPageActuelle(1);
		List<Company> previousListe = pager.getListe();
		pager.next();
		assertTrue(!previousListe.get(0).equals(pager.getListe().get(0)));
		assertEquals(2, pager.getPageActuelle());
	}
	@Test
	public void testPrev(){
		pager.setPageActuelle(2);
		List<Company> previousListe = pager.getListe();
		pager.prev();
		assertTrue(!previousListe.get(0).equals(pager.getListe().get(0)));
		assertEquals(1, pager.getPageActuelle());
	}
}
