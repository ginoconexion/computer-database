package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.model.Computer;

public interface ComputerDao {
	List<Computer> getAll();
	int createComputer(Computer computer);
	void updateComputer(Computer computer);
	Computer getComputerById(long id);
	void deleteComputer(long id);
}
