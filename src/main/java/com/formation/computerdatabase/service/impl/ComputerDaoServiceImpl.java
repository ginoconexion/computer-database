package com.formation.computerdatabase.service.impl;

import java.util.List;

import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;

public enum ComputerDaoServiceImpl implements ComputerDao {
	INSTANCE;
	private ComputerDaoImpl computerDaoImpl;
	
	private ComputerDaoServiceImpl() {
		computerDaoImpl = ConnexionFactory.getComputerDaoImpl();
	}

	@Override
	public List<Computer> getFromTo(int from, int nb) {
		return computerDaoImpl.getFromTo(from, nb);
	}

	@Override
	public int getNbEntries() {
		return computerDaoImpl.getNbEntries();
	}

	@Override
	public void createComputer(Computer computer) {
		computerDaoImpl.createComputer(computer);
	}

	@Override
	public void updateComputer(Computer computer) {
		computerDaoImpl.updateComputer(computer);
	}

	@Override
	public void deleteComputer(long id) {
		computerDaoImpl.deleteComputer(id);
		
	}

	@Override
	public Computer getComputerByName(String name) {
		return computerDaoImpl.getComputerByName(name);
	}

	@Override
	public Computer getById(long id) throws DAONotFoundException {
		return computerDaoImpl.getById(id);
	}

}
