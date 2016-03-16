package com.formation.computerdatabase.persistence.mapper.dto;

import java.time.LocalDate;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;

public class ComputerDTOMapper {
	
	/**
	 * Map.
	 *
	 * @param cDTO the c dto
	 * @return the computer
	 */
	public static Computer map(ComputerDTO cDTO) {
		Computer c = new Computer();
 		c.setId(Long.parseLong(cDTO.getId()));
		c.setIntroduced(LocalDate.parse(cDTO.getIntroduced()));
		c.setDiscontinued(LocalDate.parse(cDTO.getDiscontinued()));
		c.setName(cDTO.getName());
		c.setCompany(CompanyDTOMapper.map(cDTO.getCompany()));
		return c;
	}
}
