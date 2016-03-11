package com.formation.computerdatabase.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;

public class TestCompany {

	private static CompanyDaoImpl companyDaoImpl;
	
	@BeforeClass
	public static void init(){
		companyDaoImpl = CompanyDaoImpl.INSTANCE;
	}
	
	@Test
	public void testGetById(){
		Company company = companyDaoImpl.getById(1);
		assertEquals(1,  company.getId());
	}
	
	@Test
	public void testGetByIdNull(){
		Company company = companyDaoImpl.getById(600);
		assertEquals(null, company);
	}
	
	@Test
	public void testFromTo(){
		assertTrue(companyDaoImpl.getFromTo(0, 10).size() == 10);
	}
	
	@Test(expected = DAOException.class)
	public void testFromToWithNegative(){
		companyDaoImpl.getFromTo(-5, 5);
	}

}
