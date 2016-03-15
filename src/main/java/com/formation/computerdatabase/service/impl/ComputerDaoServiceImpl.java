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

// TODO: Auto-generated Javadoc
/**
 * The Enum ComputerDaoServiceImpl.
 */
public enum ComputerDaoServiceImpl implements ComputerDao {
	
	/** The instance. */
	INSTANCE;
	
	/** The computer dao impl. */
	private ComputerDaoImpl computerDaoImpl;
	
	/**
	 * Instantiates a new computer dao service impl.
	 */
	private ComputerDaoServiceImpl() {
		computerDaoImpl = ConnexionFactory.getComputerDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.Dao#getFromTo(int, int)
	 */
	@Override
	public List<Computer> getFromTo(int from, int nb) {
		return computerDaoImpl.getFromTo(from, nb);
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.Dao#getNbEntries()
	 */
	@Override
	public int getNbEntries() {
		return computerDaoImpl.getNbEntries();
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.ComputerDao#createComputer(com.formation.computerdatabase.model.Computer)
	 */
	@Override
	public void createComputer(Computer computer) {
		computerDaoImpl.createComputer(computer);
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.ComputerDao#updateComputer(com.formation.computerdatabase.model.Computer)
	 */
	@Override
	public void updateComputer(Computer computer) {
		computerDaoImpl.updateComputer(computer);
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.ComputerDao#deleteComputer(long)
	 */
	@Override
	public void deleteComputer(long id) {
		computerDaoImpl.deleteComputer(id);
		
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.ComputerDao#getComputerByName(java.lang.String)
	 */
	@Override
	public Computer getComputerByName(String name) {
		return computerDaoImpl.getComputerByName(name);
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.Dao#getById(long)
	 */
	@Override
	public Computer getById(long id) throws DAONotFoundException {
		return computerDaoImpl.getById(id);
	}

}
