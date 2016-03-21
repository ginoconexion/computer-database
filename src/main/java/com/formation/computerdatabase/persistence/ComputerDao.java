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
	void create(Computer computer);
	
	/**
	 * Update computer.
	 *
	 * @param computer the computer
	 */
	void update(Computer computer);
	
	/**
	 * Gets the computer by name.
	 *
	 * @param name the name
	 * @return the computer by name
	 */
	Computer getByName(String name);
}
