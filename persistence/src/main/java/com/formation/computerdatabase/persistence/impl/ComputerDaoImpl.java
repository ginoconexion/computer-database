package com.formation.computerdatabase.persistence.impl;

import static java.lang.Math.toIntExact;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.formation.computerdatabase.core.model.Company;
import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.util.OrderBy;

/**
 * The Enum ComputerDaoImpl.
 */
@Repository
@Transactional
public class ComputerDaoImpl implements ComputerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	//@Cacheable("computers")
	@Override
	public List<Computer> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Computer> query = builder.createQuery(Computer.class);
	    Root<Computer> fromComputers = query.from(Computer.class);
	    Join<Computer, Company> company = fromComputers.join("company", JoinType.LEFT);
	    
	    query.select(fromComputers);
	    
	    if (filter.containsKey(OrderBy.SEARCH)) {
	    	
	    	query.where(
					builder.or(
							builder.like(fromComputers.get("name"), (String) "%" + filter.get("search") + "%"),
							builder.like(company.get("name"), (String) "%" + filter.get("search") + "%")
							)
			);
		}
	    OrderBy.orderBy(filter, fromComputers, company, builder, query);
	    return em.createQuery(query).setFirstResult(from).setMaxResults(nb).getResultList();
	}

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
		}
		return toIntExact(em.createQuery(query).getSingleResult());
	}

	//@Cacheable("computers")
	@Override
	public Computer getById(long id) {
		return (Computer) em.find(Computer.class, id);
	}

	@Override
	public void create(Computer computer) {
		em.persist(computer);
		em.flush();
		em.refresh(computer);
	}

	@Override
	public void update(Computer computer) {
		em.merge(computer);
		em.flush();
	}
	
	public void delete(Computer computer) {
		em.remove(em.merge(computer));
		em.flush();
	}

	//@Cacheable("computers")
	@Override
	public List<Computer> getListByCompany(long id) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Computer> query = builder.createQuery(Computer.class);
	    Root<Computer> fromComputers = query.from(Computer.class);
	    
	    query.select(fromComputers);
	    query.where(builder.equal(fromComputers.get("company_id"), id));
	    return em.createQuery(query).getResultList();
	}

	@Override
	public void deleteList(List<Computer> list) {
		for (Computer computer : list) {
			em.remove(computer);
		}
		em.flush();
	}
}
