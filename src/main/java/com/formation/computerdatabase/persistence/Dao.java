package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Company;

// TODO: Auto-generated Javadoc
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
	List<T> getFromTo(int from, int nb);
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws DAONotFoundException the DAO not found exception
	 */
	T getById(long id) throws DAONotFoundException;
	
	/**
	 * Gets the nb entries.
	 *
	 * @return the nb entries
	 */
	int getNbEntries();
}