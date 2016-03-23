package com.formation.computerdatabase.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.service.ComputerDaoService;

// TODO: Auto-generated Javadoc
/**
 * The Enum ComputerDaoServiceImpl.
 */
public enum ComputerDaoServiceImpl implements ComputerDaoService {
	
	/** The instance. */
	INSTANCE;
	
	/** The computer dao impl. */
	private ComputerDaoImpl computerDaoImpl;
	
	/**
	 * Instantiates a new computer dao service impl.
	 */
	private ComputerDaoServiceImpl() {
		computerDaoImpl = ConnexionFactory.getComputerDaoImpl();
	}

	@Override
	public List<Computer> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		return computerDaoImpl.getFromTo(from, nb, filter);
	}
	@Override
	public Computer getById(long id)  {
		return computerDaoImpl.getById(id);
	}
	@Override
	public int getNbEntries(HashMap<String, Object> filter) {
		return computerDaoImpl.getNbEntries(filter);
	}
	@Override
	public void create(Computer computer) {
		computerDaoImpl.create(computer);
	}
	@Override
	public void update(Computer computer) {
		computerDaoImpl.update(computer);
	}
	@Override
	public void delete(long id) {
		computerDaoImpl.delete(id);
	}
	@Override
	public Computer getByName(String name) {
		return computerDaoImpl.getByName(name);
	}
	@Override
	public List<Computer> getListByCompany(long id) {
		return computerDaoImpl.getListByCompany(id);
	}
	@Override
	public void deleteList(List<Computer> list, Connection connexion) {
		computerDaoImpl.deleteList(list, connexion);
	}
}
