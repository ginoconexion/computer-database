package com.formation.computerdatabase.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.service.CompanyDaoService;
import com.formation.computerdatabase.service.ComputerDaoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context.xml" })
public class TestComputerService {
	
	@Autowired
	private ComputerDaoService computerService;
	@Autowired
	private CompanyDaoService companyService;
	
	@Test
	public void testGetById() {
		Computer computer = null;
		computer = computerService.getById(1);
		assertEquals(1, computer.getId());
	}
	
	public void testGetByIdNull() {
		Computer computer = null;
		computer = computerService.getById(600);
		assertEquals(null, computer);
	}
	
	@Test
	public void testUpdate() {
		Computer computer = null;
		computer = computerService.getById(1);
		System.out.println(computer);
		computer.setName("MacBook Pro 15.6");
		computerService.update(computer);
		computer = computerService.getById(1);
		System.out.println(computer.getName());
		assertEquals("MacBook Pro 15.6", computer.getName());
	}
	
	public void testUpdateUnknownCompany() {
		Computer computer = null;
		computer = computerService.getById(1);
		computer.getCompany().setId(600);
		computerService.update(computer);
	}
	
	public void testCreateComputerWithNullCompanyAndNullTimestamps() {
		Computer computer = new Computer();
		computer.setName("Ordinateur test");
		computerService.create(computer);
	}
}
