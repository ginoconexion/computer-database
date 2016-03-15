package com.formation.computerdatabase.persistence;

import com.formation.computerdatabase.model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDao.
 */
public interface ComputerDao extends Dao<Computer> {
	
	/**
	 * Creates the computer.
	 *
	 * @param computer the computer
	 */
	void createComputer(Computer computer);
	
	/**
	 * Update computer.
	 *
	 * @param computer the computer
	 */
	void updateComputer(Computer computer);
	
	/**
	 * Gets the computer by name.
	 *
	 * @param name the name
	 * @return the computer by name
	 */
	Computer getComputerByName(String name);
	
	/**
	 * Delete computer.
	 *
	 * @param id the id
	 */
	void deleteComputer(long id);
}
