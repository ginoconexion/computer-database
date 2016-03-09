package com.formation.computerdatabase.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.DAOFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;

public class ComputerDaoTest {
	
	private static DAOFactory daoFactory;
	private static ComputerDaoImpl computerDaoImpl;
	//private static Computer computer;
	
	
	@BeforeClass
	public static void init(){
		daoFactory = new DAOFactory();
		computerDaoImpl = new ComputerDaoImpl(daoFactory);
		List<Computer> liste = computerDaoImpl.getAll();
		//computer = liste.get(liste.size()-1);
	}
	
	
	@Test
	public void testCreation() {
		Computer computer = new Computer();
		computer.setId(1);
		computer.setCompanyId(1);
		Timestamp introduced = new Timestamp(10000000);
		computer.setDiscontinued(introduced);
		Timestamp discontinued = new Timestamp(20000000);
		computer.setDiscontinued(discontinued);
		computer.setName("MacBook Pro 15.4 inch");
		int nbRow = computerDaoImpl.createComputer(computer);
		assertEquals(1, nbRow);
	}
	@Test
	public void testGetAll() {
		List<Computer> liste = computerDaoImpl.getAll();
		assertTrue(liste.size() > 0);
	}
	@Test
	public void testGetById(){
		Computer computer = computerDaoImpl.getComputerById(1);
		assertEquals(1, computer.getId());
	}
	
	@Test
	public void testUpdate(){
		Computer computer = computerDaoImpl.getComputerById(1);
		computer.setName("MacBook Pro 15.6");
		computerDaoImpl.updateComputer(computer);
		computer = computerDaoImpl.getComputerById(1);
		System.out.println(computer.getName());
		assertEquals("MacBook Pro 15.6", computer.getName());
	}
	
	
	

}
