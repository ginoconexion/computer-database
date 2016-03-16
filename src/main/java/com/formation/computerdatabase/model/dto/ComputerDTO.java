package com.formation.computerdatabase.model.dto;

import java.time.LocalDate;

import com.formation.computerdatabase.model.Company;

public class ComputerDTO {
	
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The introduced. */
	private String introduced;
	
	/** The discontinued. */
	private String discontinued;
	
	/** The company. */
	private CompanyDTO companyDTO;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
