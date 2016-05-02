package com.formation.computerdatabase.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.formation.computerdatabase.core.model.Company;
import com.formation.computerdatabase.service.CompanyDaoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context.xml" })
public class TestCompanyService {
	
	@Autowired
	private CompanyDaoService companyDaoServiceImpl;
	
	@Test
	public void testGetById() {
		Company company = companyDaoServiceImpl.getById(1);
		assertEquals(1,  company.getId());
	}
	
	public void testGetByIdNull() {
		Company company = companyDaoServiceImpl.getById(600);
		assertEquals(null, company);
	}
}
