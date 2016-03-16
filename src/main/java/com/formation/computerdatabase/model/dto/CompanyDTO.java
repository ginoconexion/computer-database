package com.formation.computerdatabase.model.dto;

import com.formation.computerdatabase.model.Company;

public class CompanyDTO {
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;

	public CompanyDTO(Company company) {
		this.id = Long.toString(company.getId());
		this.name = company.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
