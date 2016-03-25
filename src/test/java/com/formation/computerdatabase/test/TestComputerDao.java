package com.formation.computerdatabase.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

public class TestComputerDao {
	
	private static ComputerDaoServiceImpl computerService;
	
	
	@BeforeClass
	public static void init() {
		computerService = ComputerDaoServiceImpl.INSTANCE;
	}

	@Test
	public void testGetById() {
		Computer computer = null;
		computer = computerService.getById(1);
		assertEquals(1, computer.getId());
	}
	
	@Test
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
	
	@Test(expected = DAOException.class)
	public void testUpdateUnknownCompany() {
		Computer computer = null;
		computer = computerService.getById(1);
		computer.getCompany().setId(600);
		computerService.update(computer);
	}
	
	@Test(expected = DAOException.class)
	public void testCreateComputerWithNullCompanyAndNullTimestamps() {
		Computer computer = new Computer();
		computer.setName("Ordinateur test");
		computerService.create(computer);
	}
	
	@Test
	public void testCreateAndDelete() {
		
		Computer computer = new Computer();
		computer.setCompany(CompanyDaoImpl.INSTANCE.getById(1));
		String name = "Test";
		computer.setName(name);
		computerService.create(computer);
		
		/** on vérifie qu'on récupère bien le bon ordinateur */
		computer = computerService.getByName(name);
		assertEquals(name, computer.getName());
		
		computerService.delete(computer.getId());
		
		/** on vérifie que l'ordinateur a bien été supprimé */
		/*computer = computerService.getByName(name);
		
		assertEquals(null, computer);*/
	}
	@Ignore
	@Test(expected = DAOException.class)
	public void testFromToWithNegative() {
		/*
		computerService.getFromTo(-5, 5, new HashMap<>()); */
	}
	
	@Test
	public void testFromTo() {
		//assertTrue(computerService.getFromTo(0, 10, new HashMap<>()).size() == 10);
	}
}
