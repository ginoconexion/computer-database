package com.formation.computerdatabase.persistence.mapper;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.dto.CompanyDTO;

public class CompanyDTOMapper {

	/**
	 * Map.
	 *
	 * @param companyDto the company dto
	 * @return the company
	 */
	public static Company map(CompanyDTO companyDto) {
		Company company = new Company();
		company.setId(Long.parseLong(companyDto.getId()));
		company.setName(companyDto.getName());
		return company;
	}
}
