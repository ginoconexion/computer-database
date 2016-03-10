package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;

public interface ComputerDatabaseService {
	/**
	 * @param from
	 * @param nb
	 * @return
	 */
	List<Computer> getComputersFromTo(int from, int nb);
	int getNbComputers();
	int getNbCompanies();
	List<Computer> getAllComputers();
	/**
	 * @param id
	 * @return
	 */
	Computer getComputerById(Long id);
	List<Company> getAllCompanies();
	/**
	 * @param from
	 * @param nb
	 * @return
	 */
	List<Company> getCompaniesFromTo(int from, int nb);
	void createComputer(Computer c);
	void updateComputer(Computer c);
	void deleteComputer(Long id);
}