package com.formation.computerdatabase.binding.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import com.formation.computerdatabase.binding.validator.Date;
import com.formation.computerdatabase.binding.validator.DateAnteriority;


@DateAnteriority
public class ComputerDTO {
	
	/** The id. */
	@NumberFormat
	private String id;
	
	/** The name. */
	@NotNull
	@Size(min=2, max=150)
	private String name;
	
	/** The introduced. */
	@Date
	private String introduced;
	
	/** The discontinued. */
	@Date
	private String discontinued;
	
	/** The company. */
	@NotEmpty
	@NumberFormat
	private String companyId;
	
	private String companyName;
	
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
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + ", companyName=" + companyName + "]";
	}
	
	
}
