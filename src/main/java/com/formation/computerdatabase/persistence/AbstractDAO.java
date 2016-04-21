package com.formation.computerdatabase.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractDAO {

	protected SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public void persist(Object entity) {
        getSession().persist(entity);
    }
 
    public void delete(Object entity) {
        getSession().delete(entity);
    }
}
