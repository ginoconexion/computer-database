package com.formation.computerdatabase.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp-context.xml")
public class TestComputerService {
	
	@Autowired
	private ComputerDaoServiceImpl computerService;
	@Autowired
	private CompanyDaoServiceImpl companyService;
	
	@Test
	public void testGetById() {
		Computer computer = null;
		computer = computerService.getById(1);
		assertEquals(1, computer.getId());
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
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
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testUpdateUnknownCompany() {
		Computer computer = null;
		computer = computerService.getById(1);
		computer.getCompany().setId(600);
		computerService.update(computer);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testCreateComputerWithNullCompanyAndNullTimestamps() {
		Computer computer = new Computer();
		computer.setName("Ordinateur test");
		computerService.create(computer);
	}
}
