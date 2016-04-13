package com.formation.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.service.CompanyDaoService;
import com.formation.computerdatabase.service.ComputerDaoService;


/**
 * The Enum CompanyDaoServiceImpl.
 */
@Service
public class CompanyDaoServiceImpl implements CompanyDaoService {

	/** The company dao impl. */
	@Autowired
	private ComputerDaoServiceImpl computerService;
	@Autowired
	private CompanyDaoImpl companyDaoImpl;
	@Autowired
	private TransactionTemplate transactionTemplate;


	@Override
	public Company getById(long id) {
		return companyDaoImpl.getById(id);
	}

	public List<Company> getAll() {
		return companyDaoImpl.getAll();
	}

	@Override
	@Transactional
	public void delete(long id, ComputerDaoService computerService) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus paramTransactionStatus) {
				try {
					List<Computer> liste = computerService.getListByCompany(id);
					computerService.deleteList(liste);
					companyDaoImpl.delete(id);
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Rollback transaction");
					//use this to rollback exception in case of exception
					paramTransactionStatus.setRollbackOnly();
				}

			}
		});

		/*
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
		 */
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
}
