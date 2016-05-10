package com.formation.computerdatabase.persistence.impl;

import static java.lang.Math.toIntExact;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.formation.computerdatabase.core.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;

/**
 * The Enum CompanyDaoImpl.
 */
@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao  {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int getCount(HashMap<String, Object> filter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Long> query = builder.createQuery(Long.class);
	    Root<Company> fromCompanies = query.from(Company.class);
		return toIntExact(em.createQuery(query).getSingleResult());
	}
	
	//@Cacheable("companies")
	@Override
	public List<Company> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Company> query = builder.createQuery(Company.class);
	    Root<Company> fromCompanies = query.from(Company.class);
	    query.select(fromCompanies);
	    return em.createQuery(query).getResultList();
	}

	/** The Constant SELECT_BY_ID. */
	
	//@Cacheable("companies")
	@Override
	public Company getById(long id) {
		return (Company) em.find(Company.class, id);
	}
	
	@Override
	public void delete(Company company) {
		em.remove(company);
		em.flush();
	}
}
