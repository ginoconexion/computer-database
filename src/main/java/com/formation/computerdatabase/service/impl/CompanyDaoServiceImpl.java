package com.formation.computerdatabase.service.impl;

import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.dto.CompanyDTO;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.mapper.dto.CompanyDTOMapper;
import com.formation.computerdatabase.service.CompanyDaoService;

// TODO: Auto-generated Javadoc
/**
 * The Enum CompanyDaoServiceImpl.
 */
public enum CompanyDaoServiceImpl implements CompanyDaoService {
	
	/** The instance. */
	INSTANCE;
	
	/** The company dao impl. */
	private CompanyDaoImpl companyDaoImpl;
	
	/**
	 * Instantiates a new company dao service impl.
	 */
	private CompanyDaoServiceImpl() {
		companyDaoImpl = ConnexionFactory.getCompanyDaoImpl();
	}

	@Override
	public List<CompanyDTO> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		return CompanyDTOMapper.mapList(companyDaoImpl.getFromTo(from, nb, filter));
	}

	@Override
	public CompanyDTO getById(long id) {
		return CompanyDTOMapper.map(companyDaoImpl.getById(id));
	}

	@Override
	public int getNbEntries(HashMap<String, Object> filter) {
		return companyDaoImpl.getNbEntries(filter);
	}
	
	public List<CompanyDTO> getAll() {
		return CompanyDTOMapper.mapList(companyDaoImpl.getAll());
	}
	

}
