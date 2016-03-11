package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.model.Computer;

public interface ComputerDao extends Dao<Computer> {
	List<Computer> getFromTo(int from, int to);
	int getNbEntries();
	void createComputer(Computer computer);
	void updateComputer(Computer computer);
	Computer getComputerById(long id);
	Computer getComputerByName(String name);
	void deleteComputer(long id);
}
