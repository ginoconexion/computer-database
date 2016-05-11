package com.formation.computerdatabase.binding.dto;

import com.formation.computerdatabase.core.model.Company;

public class CompanyDTO {
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;

	
	public CompanyDTO() {
	}
	
	public CompanyDTO(Company company) {
		if (company !=  null) {
			this.id = Long.toString(company.getId());
			this.name = company.getName();
		}
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

	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", name=" + name + "]";
	}
	
}
