package com.formation.computerdatabase.persistence;

import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.model.Computer;

/**
 * The Interface ComputerDao.
 */
public interface ComputerDao extends Dao<Computer> {
	void create(Computer computer);
	void update(Computer computer);
	Computer getByName(String name);
	List<Computer> getListByCompany(long id);
	void delete(long id);
	void deleteList(List<Computer> list);
	List<Computer> getFromTo(int from, int nb, HashMap<String, Object> filter);
}
