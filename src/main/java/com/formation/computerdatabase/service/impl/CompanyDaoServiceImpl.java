package com.formation.computerdatabase.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class CompanyDaoServiceImpl implements CompanyDaoService {
	
	/** The company dao impl. */
	@Autowired
	private CompanyDaoImpl companyDaoImpl;
	@Autowired
	private ConnexionFactory connexionFactory;
	

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
			connexionFactory.initTransaction();
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
