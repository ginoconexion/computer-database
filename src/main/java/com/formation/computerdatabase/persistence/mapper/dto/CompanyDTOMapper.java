package com.formation.computerdatabase.persistence.mapper.dto;

import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.dto.CompanyDTO;

public class CompanyDTOMapper {

	public static CompanyDTO map(Company company) {
		CompanyDTO cDTO = new CompanyDTO();
		cDTO.setId(Long.toString(company.getId()));
		cDTO.setName(company.getName());
		return cDTO;
	}
	
	public static List<CompanyDTO> mapList(List<Company> listeCompany) {
		List<CompanyDTO> liste = new ArrayList<>();
		for (Company company : listeCompany) {
			liste.add(CompanyDTOMapper.map(company));
		}
		return liste;
	}
}
