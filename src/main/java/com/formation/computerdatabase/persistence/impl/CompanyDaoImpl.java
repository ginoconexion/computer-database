package com.formation.computerdatabase.persistence.impl;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.mapper.CompanyMapper;

/**
 * The Enum CompanyDaoImpl.
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final static String SELECT_COUNT = "SELECT COUNT(*) as nb_companies FROM company";
	@Override
	public int getCount(HashMap<String, Object> filter) {
		return jdbcTemplateObject.queryForObject(SELECT_COUNT, Integer.class);
	}
	
	/** The Constant SELECT_ALL. */
	private final static String SELECT_ALL = "SELECT * FROM company";
	
	@Override
	public List<Company> getAll() {
		List <Company> liste = jdbcTemplateObject.query(SELECT_ALL, new CompanyMapper());
		return liste;
	}

	/** The Constant SELECT_BY_ID. */
	private final static String SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
	
	@Override
	public Company getById(long id) {
		if (id != 0) {
			return jdbcTemplateObject.queryForObject(SELECT_BY_ID, new Object[]{id}, new CompanyMapper());
		}
		return null;
	}
	
	/** The Constant DELETE. */
	private final static String DELETE = "DELETE FROM company WHERE id = ?";
	
	@Override
	public void delete(long id) {
		jdbcTemplateObject.update(DELETE, id);
	    // logger msg
	    return;
	}
}
