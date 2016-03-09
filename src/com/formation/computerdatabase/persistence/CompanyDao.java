package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;

public interface CompanyDao {
	List<Company> getAll();
	List<Company> getFromTo(int from, int nb);
	int getNbEntries();
}
