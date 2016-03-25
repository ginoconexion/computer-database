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
	public Company getById(long id) {
		return companyDaoImpl.getById(id);
	}
	
	public List<Company> getAll() {
		return companyDaoImpl.getAll();
	}

	@Override
	public void delete(long id, ComputerDaoService computerService) {
		try {
			List<Computer> liste = computerService.getListByCompany(id);
			ConnexionFactory.initTransaction();
			computerService.deleteList(liste);
			companyDaoImpl.delete(id);
			ConnexionFactory.commit();
			
		} catch (SQLException e) {
			try {
				ConnexionFactory.rollback();
			} catch (SQLException e1) {
				String message = "La transaction n'a pas pu être annulée";
				System.err.println(message);
				throw new DAOException(message);
			}
			String message = "La company n'a pas pu être supprimée";
			System.err.println(message);
			throw new DAOException(message);
		} finally {
			try {
				ConnexionFactory.closeTransaction();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
