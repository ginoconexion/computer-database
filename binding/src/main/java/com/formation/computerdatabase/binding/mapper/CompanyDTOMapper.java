package com.formation.computerdatabase.binding.mapper;

import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.binding.dto.CompanyDTO;
import com.formation.computerdatabase.core.model.Company;

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
