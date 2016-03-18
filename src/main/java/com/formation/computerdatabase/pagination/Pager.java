package com.formation.computerdatabase.pagination;

import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.persistence.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Pager.
 *
 * @param <T> the generic type
 */
public class Pager<T> {

	/** The liste. */
	private List<T> liste;
	
	/** The nb entries. */
	private int nbEntries;
	
	/** The nb par page. */
	private int nbParPage;
	
	/** The nb pages. */
	private int nbPages;
	
	/** The page actuelle. */
	private int pageActuelle;
	
	/** The service. */
	private Dao<T> dao;
	
	/** The filter. */
	private HashMap<String, Object> filter;

	public HashMap<String, Object> getFilter() {
		return filter;
	}

	/**
	 * Instantiates a new pager.
	 *
	 * @param nbParPage the nb par page
	 * @param page the page
	 * @param dao the dao
	 * @param filter the filter
	 */
	public Pager(int nbParPage, int page, Dao<T> dao, HashMap<String, Object> filter) {
		this.nbParPage = nbParPage;
		this.pageActuelle = page;
		this.dao = dao;
		this.filter = filter;
		updateListe();
	}
	
	/**
	 * Prints the liste.
	 */
	public void printListe(){
		for (Object object : liste) {
			System.out.println("------------ " + object);
		}
	}
	
	/**
	 * Next.
	 */
	public void next() {
		if (pageActuelle < nbPages){
			pageActuelle  += 1;
			updateListe();
		}
	}
	
	/**
	 * Prev.
	 */
	public void prev() {
		if (pageActuelle > 1){
			pageActuelle  -= 1;
			updateListe();
		}
	}
	
	/**
	 * Checks if is outof bounds.
	 *
	 * @return true, if is outof bounds
	 */
	public boolean isOutofBounds(){
		boolean bool = false;
		System.out.println(pageActuelle);
		System.out.println(nbPages);
		
		if (pageActuelle < 1 || pageActuelle > nbPages) {
			correctPage();
			bool = true;
		}
		return bool;
	}
	
	/**
	 * Update liste.
	 */
	public void updateListe() {
		this.nbEntries = dao.getNbEntries(filter);
		this.nbPages = (int) Math.ceil((double) nbEntries/nbParPage);
		liste = this.dao.getFromTo((pageActuelle - 1) * nbParPage, nbParPage, filter);
		
		
	}
	
	/**
	 * Correct page.
	 */
	public void correctPage(){
		if (this.pageActuelle < 1){
			this.pageActuelle = 1;
		}
		else if (this.pageActuelle > nbPages) {
			this.pageActuelle = nbPages;
		}
	}
	
	/**
	 * Gets the nb entries.
	 *
	 * @return the nb entries
	 */
	public int getNbEntries() {
		return nbEntries;
	}
	
	/**
	 * Sets the nb entries.
	 *
	 * @param nbEntries the new nb entries
	 */
	public void setNbEntries(int nbEntries) {
		this.nbEntries = nbEntries;
	}
	
	/**
	 * Gets the nb par page.
	 *
	 * @return the nb par page
	 */
	public int getNbParPage() {
		return nbParPage;
	}
	
	/**
	 * Sets the nb par page.
	 *
	 * @param nbParPage the new nb par page
	 */
	public void setNbParPage(int nbParPage) {
		this.nbParPage = nbParPage;
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
	
	/**
	 * Gets the page actuelle.
	 *
	 * @return the page actuelle
	 */
	public int getPageActuelle() {
		return pageActuelle;
	}
	
	/**
	 * Sets the page actuelle.
	 *
	 * @param pageActuelle the new page actuelle
	 */
	public void setPageActuelle(int pageActuelle) {
		this.pageActuelle = pageActuelle;
	}

	/**
	 * Gets the liste.
	 *
	 * @return the liste
	 */
	public List<T> getListe() {
		return liste;
	}
	
}
