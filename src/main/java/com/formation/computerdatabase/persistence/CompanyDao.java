package com.formation.computerdatabase.persistence;
import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.model.Company;

/**
 * The Interface CompanyDao.
 */
public interface CompanyDao extends Dao<Company> {
	
	/**
	 * Gets the all.
	 * @return the all
	 */
	List<Company> getAll();
	Company getById(long id);
	void delete(long id);
}
