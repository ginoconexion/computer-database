package com.formation.computerdatabase.service.impl;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;

// TODO: Auto-generated Javadoc
/**
 * The Enum CompanyDaoServiceImpl.
 */
public enum CompanyDaoServiceImpl implements CompanyDao {
	
	/** The instance. */
	INSTANCE;
	
	/** The company dao impl. */
	private CompanyDaoImpl companyDaoImpl;
	
	/**
	 * Instantiates a new company dao service impl.
	 */
	private CompanyDaoServiceImpl() {
		companyDaoImpl = ConnexionFactory.getCompanyDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getFromTo(int, int)
	 */
	@Override
	public List<Company> getFromTo(int from, int nb) {
		return companyDaoImpl.getFromTo(from, nb);
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getById(long)
	 */
	@Override
	public Company getById(long id) {
		return companyDaoImpl.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getNbEntries()
	 */
	@Override
	public int getNbEntries() {
		return companyDaoImpl.getNbEntries();
	}

	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getAll()
	 */
	@Override
	public List<Company> getAll() {
		return companyDaoImpl.getAll();
	}
}
