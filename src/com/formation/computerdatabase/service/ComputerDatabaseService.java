package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;

public interface ComputerDatabaseService {
	List<Computer> getComputersFromTo(int from, int nb);
	int getNbComputers();
	int getNbCompanies();
	List<Computer> getAllComputers();
	Computer getComputerById(Long id);
	List<Company> getAllCompanies();
	List<Company> getCompaniesFromTo(int from, int nb);
	void createComputer(Computer c);
	void updateComputer(Computer c);
	void deleteComputer(Long id);
}