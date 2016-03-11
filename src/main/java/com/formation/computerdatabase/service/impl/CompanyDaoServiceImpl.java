package com.formation.computerdatabase.service.impl;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;

public enum CompanyDaoServiceImpl implements CompanyDao {
	INSTANCE;
	private CompanyDaoImpl companyDaoImpl;
	
	private CompanyDaoServiceImpl() {
		companyDaoImpl = ConnexionFactory.getCompanyDaoImpl();
	}

	@Override
	public List<Company> getFromTo(int from, int nb) {
		return companyDaoImpl.getFromTo(from, nb);
	}

	@Override
	public Company getById(long id) {
		return companyDaoImpl.getById(id);
	}

	@Override
	public int getNbEntries() {
		return companyDaoImpl.getNbEntries();
	}
}
