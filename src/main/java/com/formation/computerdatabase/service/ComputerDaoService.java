package com.formation.computerdatabase.service;

import java.sql.Connection;
import java.util.List;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.Dao;

public interface ComputerDaoService extends Dao<Computer> {
	
	void create(Computer computer);
	void update(Computer computer);
	void delete(long id);
	List<Computer> getListByCompany(long id);
	void deleteList(List<Computer> list, Connection connexion);
	Computer getByName(String name);
}
