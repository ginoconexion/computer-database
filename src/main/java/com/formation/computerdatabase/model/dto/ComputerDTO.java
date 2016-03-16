package com.formation.computerdatabase.model.dto;

import java.time.LocalDate;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.mapper.dto.CompanyDTOMapper;

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
	private CompanyDTO company;
	
	public ComputerDTO(Computer computer) {
		this.id = Long.toString(computer.getId());
		this.name = computer.getName();
		this.introduced = computer.getIntroduced().toString();
		this.discontinued = computer.getDiscontinued().toString();
		this.company = new CompanyDTO(computer.getCompany());
	}

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

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO companyDTO) {
		this.company = companyDTO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
