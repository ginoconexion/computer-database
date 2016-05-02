package com.formation.computerdatabase.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.formation.computerdatabase.service.CompanyDaoService;
import com.formation.computerdatabase.service.ComputerDaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context.xml" })
public class TestDeleteCompany {


	private CompanyDaoService companyService;
	private ComputerDaoService computerService;

	@Test
	public void test() {
		//companyService.delete(43, computerService);
	}

}
