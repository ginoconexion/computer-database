package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.model.Computer;

public interface ComputerDao {
	public List<Computer> getAll();
	public Computer getComputerById(int id);
	public void createComputer(Computer computer);
	public void updateComputer(Computer computer);
	public void deleteComputer(Computer computer);
}
