package com.formation.computerdatabase.persistence;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;

/**
 * The Interface Dao.
 *
 * @param <T> the generic type
 */
public interface Dao<T> {
	
	/**
	 * Gets the from to.
	 *
	 * @param from the from
	 * @param nb the nb
	 * @return the from to
	 */
	List<T> getFromTo(int from, int nb, HashMap<String, Object> filter);
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws DAONotFoundException the DAO not found exception
	 */
	T getById(long id);
	
	/**
	 * Gets the nb entries.
	 *
	 * @return the nb entries
	 */
	int getCount(HashMap<String, Object> filter);
}