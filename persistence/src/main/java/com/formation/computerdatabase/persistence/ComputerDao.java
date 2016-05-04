package com.formation.computerdatabase.persistence;

import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.core.model.Computer;

/**
 * The Interface ComputerDao.
 */
public interface ComputerDao extends Dao<Computer> {
	void create(Computer computer);
	void update(Computer computer);
	List<Computer> getListByCompany(long id);
	void delete(Computer computer);
	void deleteList(List<Computer> list);
	List<Computer> getFromTo(int from, int nb, HashMap<String, Object> filter);
}
