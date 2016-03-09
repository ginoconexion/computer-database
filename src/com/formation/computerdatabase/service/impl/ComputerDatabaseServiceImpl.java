package com.formation.computerdatabase.service.impl;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.DAOFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.service.ComputerDatabaseService;

public enum ComputerDatabaseServiceImpl implements ComputerDatabaseService{
	INSTANCE;
	
	private DAOFactory daoFactory;
	private ComputerDao computerDao;
	private CompanyDao companyDao;
	
	private ComputerDatabaseServiceImpl() {
		daoFactory = new DAOFactory();
		computerDao = new ComputerDaoImpl(daoFactory);
		companyDao 	= new CompanyDaoImpl(daoFactory);
	}
	

	@Override
	public List<Computer> getAllComputers() {
		return computerDao.getAll();
	}


	@Override
	public List<Company> getAllCompanies() {
		return companyDao.getAll();
	}

	@Override
	public void deleteComputer(Long id) {
		computerDao.deleteComputer(id);
	}

	@Override
	public List<Computer> getComputersFromTo(int from, int nb) {
		return computerDao.getFromTo(from, nb);
	}


	@Override
	public int getNbComputers() {
		return computerDao.getNbEntries();
	}


	@Override
	public Computer getComputerById(Long id) {
		return computerDao.getComputerById(id);
	}


	@Override
	public void createComputer(Computer c) {
		computerDao.createComputer(c);
	}


	@Override
	public void updateComputer(Computer c) {
		computerDao.updateComputer(c);
	}


	@Override
	public int getNbCompanies() {
		// TODO Auto-generated method stub
		return companyDao.getNbEntries();
	}

	@Override
	public List<Company> getCompaniesFromTo(int from, int nb) {
		return companyDao.getFromTo(from, nb);
	}

}
