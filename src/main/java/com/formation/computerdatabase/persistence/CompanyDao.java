package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDao.
 */
public interface CompanyDao extends Dao<Company> {
	
	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.Dao#getFromTo(int, int)
	 */
	List<Company> getFromTo(int from, int nb);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Company> getAll();
	
	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.Dao#getById(long)
	 */
	Company getById(long id);
	
	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.Dao#getNbEntries()
	 */
	int getNbEntries();
}
