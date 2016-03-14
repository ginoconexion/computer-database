package com.formation.computerdatabase.pagination;

import java.util.List;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.Dao;

public class Pager<T> {

	private List<T> liste;
	private int nbEntries;
	private int nbParPage;
	private int nbPages;
	private int pageActuelle;
	private Dao<T> dao;
	

	public Pager(int nbParPage, int page, Dao<T> dao) {
		this.nbEntries = dao.getNbEntries();
		this.nbParPage = nbParPage;
		this.nbPages = (int) Math.ceil(nbEntries/nbParPage);
		setPageActuelle(page);
		this.dao = dao;
		//this.liste = dao.getFromTo(0, 10);
		//System.out.println("liste " + liste);
		updateListe();
	}
	
	public void printListe(){
		for (Object object : liste) {
			System.out.println("------------ " + object);
		}
	}
	
	public void next() {
		if (pageActuelle < nbPages){
			pageActuelle  += 1;
			updateListe();
		}
	}
	
	public void prev() {
		if (pageActuelle > 1){
			pageActuelle  -= 1;
			updateListe();
		}
	}
	
	public void updateListe() {
		this.liste = this.dao.getFromTo((pageActuelle - 1)*nbParPage, nbParPage);
	}
	
	public int getNbEntries() {
		return nbEntries;
	}
	public void setNbEntries(int nbEntries) {
		this.nbEntries = nbEntries;
	}
	public int getNbParPage() {
		return nbParPage;
	}
	public void setNbParPage(int nbParPage) {
		this.nbParPage = nbParPage;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public int getPageActuelle() {
		return pageActuelle;
	}
	public void setPageActuelle(int pageActuelle) {
		this.pageActuelle = pageActuelle;
	}

	public List<T> getListe() {
		return liste;
	}
	
}
