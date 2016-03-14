package com.formation.computerdatabase.persistence;

import java.util.List;

import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Company;

public interface Dao<T> {
	List<T> getFromTo(int from, int nb);
	T getById(long id) throws DAONotFoundException;
	int getNbEntries();
}