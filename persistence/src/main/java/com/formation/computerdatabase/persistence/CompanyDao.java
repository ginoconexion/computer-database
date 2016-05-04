package com.formation.computerdatabase.persistence;
import java.util.List;

import com.formation.computerdatabase.core.model.Company;


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
	void delete(Company company);
}
