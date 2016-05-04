package com.formation.computerdatabase.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.formation.computerdatabase.core.model.User;
import com.formation.computerdatabase.persistence.UserDao;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findByUserName(String username) {
		return (User) em.find(User.class, username);
	}
	
}
