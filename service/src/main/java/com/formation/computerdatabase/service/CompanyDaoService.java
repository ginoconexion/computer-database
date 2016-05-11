package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.core.model.Company;

public interface CompanyDaoService {
		List<Company> getAll();
		void delete(long id);
		Company getById(long id);
}
