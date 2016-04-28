package com.formation.computerdatabase.persistence;

import java.util.HashMap;

/**
 * The Interface Dao.
 *
 * @param <T> the generic type
 */
public interface Dao<T> {
	
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