package com.formation.computerdatabase.pagination;

import java.util.HashMap;

import com.formation.computerdatabase.persistence.Dao;

/**
 * The Class Pager.
 *
 * @param <T> the generic type
 */
public class Pager<T> {

	private int count;
	private int offset;
	private int nbPages;
	private int current;
	
	/** The service. */
	private Dao<T> dao;
	
	/** The filter. */
	private HashMap<String, Object> filter;
	

	/**
	 * Instantiates a new pager.
	 *
	 * @param nbParPage the nb par page
	 * @param page the page
	 * @param dao the dao
	 * @param filter the filter
	 */
	public Pager(int offset, int current, Dao<T> dao, HashMap<String, Object> filter) {
		this.offset = offset;
		this.current = current;
		this.dao = dao;
		this.filter = filter;
		updateListe();
	}
	
	/**
	 * Checks if is outof bounds.
	 *
	 * @return true, if is outof bounds
	 */
	public boolean isOutofBounds(){
		boolean bool = false;
		
		if (current < 1 || current > nbPages) {
			correctPage();
			bool = true;
		}
		return bool;
	}
	
	
	/**
	 * Update liste.
	 */
	public void updateListe() {
		this.count = dao.getNbEntries(filter);
		this.nbPages = (int) Math.ceil((double) count/offset);
	}
	
	/**
	 * Correct page.
	 */
	public void correctPage(){
		if (this.current < 1){
			this.current = 1;
		}
		else if (this.current > nbPages) {
			this.current = nbPages;
		}
	}
	
	public int getFrom() {
		return (current - 1)*offset;
	}
	
	
	
	
	/**
	 * Gets the nb pages.
	 *
	 * @return the nb pages
	 */
	public int getNbPages() {
		return nbPages;
	}
	
	/**
	 * Sets the nb pages.
	 *
	 * @param nbPages the new nb pages
	 */
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	
	public HashMap<String, Object> getFilter() {
		return filter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public Dao<T> getDao() {
		return dao;
	}

	public void setDao(Dao<T> dao) {
		this.dao = dao;
	}

	public void setFilter(HashMap<String, Object> filter) {
		this.filter = filter;
	}
	
	
}
