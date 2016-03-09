package com.formation.computedatabase.pagination;

public class Paginator {
	
	private int nbEntries;
	private int nbParPage;
	private int nbPages;
	private int pageActuelle;
	
	public Paginator(int nbEntries, int nbParPage) {
		super();
		this.nbEntries = nbEntries;
		this.nbParPage = nbParPage;
		this.nbPages = (int) Math.ceil(nbEntries/nbParPage);
		this.pageActuelle = 1;
	}

	public int getNbEntries() {
		return nbEntries;
	}


	public int getNbParPage() {
		return nbParPage;
	}


	public int getNbPages() {
		return nbPages;
	}


	public int getPageActuelle() {
		return pageActuelle;
	}

	public void setPageActuelle(int pageActuelle) {
		this.pageActuelle = pageActuelle;
	}
	
	public void next(){
		if (pageActuelle < nbParPage){
			pageActuelle  += 1;
		}
	}
	public void prev(){
		if (pageActuelle > 1){
			pageActuelle  -= 1;
		}
	}
}
