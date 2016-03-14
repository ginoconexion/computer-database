package com.formation.computerdatabase.persistence;

import com.formation.computerdatabase.model.Computer;

public interface ComputerDao extends Dao<Computer> {
	void createComputer(Computer computer);
	void updateComputer(Computer computer);
	Computer getComputerByName(String name);
	void deleteComputer(long id);
}
