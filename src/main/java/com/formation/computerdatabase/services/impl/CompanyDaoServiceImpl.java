package com.formation.computerdatabase.services.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.services.CompanyDaoService;
import com.formation.computerdatabase.services.ComputerDaoService;


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
	private SessionFactory sessionFactory;
	/*
	@Autowired
	private TransactionTemplate transactionTemplate;
	*/

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
		/*
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
		*/

	}
	/*
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	*/
}
