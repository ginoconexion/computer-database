package com.formation.computerdatabase.service.impl;

import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.persistence.mapper.dto.ComputerDTOMapper;
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
	public List<ComputerDTO> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		return ComputerDTOMapper.mapList(computerDaoImpl.getFromTo(from, nb, filter));
	}
	@Override
	public ComputerDTO getById(long id) throws DAONotFoundException {
		return ComputerDTOMapper.map(computerDaoImpl.getById(id));
	}
	public int getNbEntries(HashMap<String, Object> filter) {
		return computerDaoImpl.getNbEntries(filter);
	}

	public void create(Computer computer) {
		computerDaoImpl.create(computer);
	}
	public void update(Computer computer) {
		computerDaoImpl.update(computer);
	}
	public void delete(long id) {
		computerDaoImpl.delete(id);
	}
	public Computer getByName(String name) {
		return computerDaoImpl.getByName(name);
	}
}
