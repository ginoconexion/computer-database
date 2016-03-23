package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.Dao;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

public interface CompanyDaoService extends Dao<Company> {
		List<Company> getAll();
		void delete(long id, ComputerDaoService computerService);
}
