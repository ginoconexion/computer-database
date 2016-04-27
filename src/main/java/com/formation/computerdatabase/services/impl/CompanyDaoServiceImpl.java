package com.formation.computerdatabase.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.CompanyDao;
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
	private ComputerDaoService computerService;
	@Autowired
	private CompanyDao companyDaoImpl;

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
		List<Computer> liste = computerService.getListByCompany(id);
		computerService.deleteList(liste);
		Company company = companyDaoImpl.getById(id);
		companyDaoImpl.delete(company);
	}
}
