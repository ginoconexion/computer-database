package com.formation.computerdatabase.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.pagination.Order;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;

/**
 * The Enum ComputerDaoImpl.
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {
	
	/** The Constant SELECT_LIMIT. */
	private final static String SELECT = "SELECT * FROM computer ";
	private final static String LIMIT = "LIMIT ?, ? ";
	private final static String JOIN_ON_COMPANY = "LEFT JOIN company as company on computer.id = company.id ";
	private final static String WHERE_NAME = " WHERE computer.name LIKE ?  OR company.name LIKE ? ";
	private final static String WHERE_ID = " WHERE computer.id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ComputerMapper computerMapper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Computer> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Computer> query = builder.createQuery(Computer.class);
	    Root<Computer> fromComputers = query.from(Computer.class);
	    Join<Computer, Company> company = fromComputers.join("company", JoinType.LEFT);
	    
	    //List<Predicate> conditions = new ArrayList<>();
	    query.select(fromComputers);
	    
	    if (filter.containsKey(Order.SEARCH) || filter.containsKey(Order.BY_COMPANY)) {
	    	
	    	query.where(
					builder.or(
							builder.like(fromComputers.get("name"), (String) "%" + filter.get("search") + "%"),
							builder.like(company.get("name"), (String) "%" + filter.get("search") + "%")
							)
			);
	    	
	    	//conditions.add(builder.like(fromComputers.get("name"), (String) "%" + filter.get("search") + "%"));
		    //conditions.add(builder.like(company.get("name"), (String) "%" + filter.get("search") + "%"));
		}
	    
	    /*
	    TypedQuery<Computer> typedQuery = em.createQuery(query
	    		.select(fromComputers)
	    		.where(conditions.toArray(new Predicate[] {}))
	    		)
	    		.setFirstResult(from)
	    		.setMaxResults(nb);
	    
	    return typedQuery.getResultList();
	    */
	    return em.createQuery(query).setFirstResult(from).setMaxResults(nb).getResultList();
	}

	private final static String SELECT_COUNT = "SELECT COUNT(*) as nb_computers FROM computer ";
	
	@Override
	public int getCount(HashMap<String, Object> filter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Long> query = builder.createQuery(Long.class);
	    Root<Computer> fromComputers = query.from(Computer.class);
	    Join<Computer, Company> company = fromComputers.join("company", JoinType.LEFT);
	    
	    query.select(builder.count(fromComputers));
	    
		if (filter.containsKey("search")) {
			query.where(
					builder.or(
							builder.like(fromComputers.get("name"), (String) "%" + filter.get("search") + "%"),
							builder.like(company.get("name"), (String) "%" + filter.get("search") + "%")
							)
					);
			/*
			conditions.add(builder.like(fromComputers.get("name"), (String) "%" + filter.get("search") + "%"));
		    conditions.add(builder.like(company.get("name"), (String) "%" + filter.get("search") + "%"));
		    */
		}
		
		//query.where(conditions.toArray(new Predicate[] {}));
		return (int) (long) em.createQuery(query).getSingleResult();

	}

	/** The Constant SELECT_BY_ID. */
	private final static String SELECT_BY_ID = "SELECT * FROM computer ";
	
	@Override
	public Computer getById(long id) {
		return (Computer) em.find(Computer.class, id);
	}

	/** The Constant INSERT. */
	private final static String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?) ";
	
	@Override
	@Transactional
	public void create(Computer computer) {
		em.persist(computer);
		em.flush();
		em.refresh(computer);
	}

	/** The Constant UPDATE. */
	private final static String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ? ";
	
	@Override
	@Transactional
	public void update(Computer computer) {
		em.merge(computer);
		em.flush();
	}

	/** The Constant DELETE. */
	private final static String DELETE = "DELETE FROM computer WHERE id = ?";
	
	public void delete(Computer computer) {
		em.remove(computer);
		em.flush();
	}

	private final static String SELECT_BY_COMPANY_ID = "SELECT * FROM computer WHERE company_id = ? ";
	@Override
	public List<Computer> getListByCompany(long id) {
		return jdbcTemplate.query(SELECT_BY_COMPANY_ID, computerMapper, new Object[]{id});
	}

	@Override
	public void deleteList(List<Computer> list) {
		for (Computer computer : list) {
			jdbcTemplate.update(DELETE, computer.getId());
		}
	}
}
