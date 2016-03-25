package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.model.Company;

public interface CompanyDaoService {
		List<Company> getAll();
		void delete(long id, ComputerDaoService computerService);
		Company getById(long id);
}
