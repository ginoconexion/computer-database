package com.formation.computerdatabase.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ComputerDTO {
	
	/** The id. */
	@NumberFormat
	private String id;
	
	/** The name. */
	@NotNull
	@Size(min=2, max=30, message="Le nom ne doit contenir au moins 2 charactères, et pas plus de 30")
	private String name;
	
	/** The introduced. */
	@DateTimeFormat
	private String introduced;
	
	/** The discontinued. */
	@DateTimeFormat
	private String discontinued;
	
	/** The company. */
	@NotNull
	@NumberFormat
	private String companyId;
	
	/*
	public ComputerDTO(Computer computer) {
		this.id = Long.toString(computer.getId());
		this.name = computer.getName();
		this.introduced = (computer.getIntroduced() == null) ? null : computer.getIntroduced().toString();
		this.discontinued = (computer.getIntroduced() == null) ? null : computer.getDiscontinued().toString();
		this.company = new CompanyDTO(computer.getCompany());
	}

	public ComputerDTO() {
	}
	*/
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


	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
