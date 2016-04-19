package com.formation.computerdatabase.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import com.formation.computerdatabase.validator.Date;
import com.formation.computerdatabase.validator.DateAnteriority;


@DateAnteriority
public class ComputerDTO {
	
	/** The id. */
	@NumberFormat
	private String id;
	
	/** The name. */
	@NotNull
	@Size(min=2, max=30)
	private String name;
	
	/** The introduced. */
	//@Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message="Format non valide")
	@Date
	private String introduced;
	
	/** The discontinued. */
	//@Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message="Format non valide")
	@Date
	private String discontinued;
	
	/** The company. */
	@NotEmpty
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

	
	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + "]";
	}
	
}
