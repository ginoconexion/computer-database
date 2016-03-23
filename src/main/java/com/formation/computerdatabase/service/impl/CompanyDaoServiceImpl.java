package com.formation.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.service.CompanyDaoService;
import com.formation.computerdatabase.service.ComputerDaoService;

// TODO: Auto-generated Javadoc
/**
 * The Enum CompanyDaoServiceImpl.
 */
public enum CompanyDaoServiceImpl implements CompanyDaoService {
	
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

	@Override
	public List<Company> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		return companyDaoImpl.getFromTo(from, nb, filter);
	}

	@Override
	public Company getById(long id) {
		return companyDaoImpl.getById(id);
	}

	@Override
	public int getNbEntries(HashMap<String, Object> filter) {
		return companyDaoImpl.getNbEntries(filter);
	}
	
	public List<Company> getAll() {
		return companyDaoImpl.getAll();
	}

	@Override
	public void delete(long id, ComputerDaoService computerService) {
		try {
			List<Computer> liste = computerService.getListByCompany(id);
			Connection connexion = ConnexionFactory.INSTANCE.getConnection();
			connexion.setAutoCommit(false);
			computerService.deleteList(liste, connexion);
			companyDaoImpl.delete(id, connexion);
			connexion.commit();
			
		} catch (SQLException e) {
			String message = "La company n'a pas pu être supprimée";
			System.err.println(message);
			throw new DAOException(message);
		}
	}
}
