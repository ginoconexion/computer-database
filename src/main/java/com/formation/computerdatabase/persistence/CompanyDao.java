package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.model.Company;

public interface CompanyDao extends Dao<Company> {
	List<Company> getFromTo(int from, int nb);
	Company getById(long id);
	int getNbEntries();
}
