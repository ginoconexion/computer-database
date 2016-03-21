package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.Dao;

public interface CompanyDaoService extends Dao<Company> {
		List<Company> getAll();
}
