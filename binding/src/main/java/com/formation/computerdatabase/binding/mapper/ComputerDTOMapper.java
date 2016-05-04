package com.formation.computerdatabase.binding.mapper;

import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.core.model.Computer;

public class ComputerDTOMapper {
	
	/**
	 * Map
	 *
	 * @param cDTO the c dto
	 * @return the computer
	 */
	
	public static ComputerDTO map(Computer computer) {
		ComputerDTO cDTO = new ComputerDTO();
		cDTO.setId(Long.toString(computer.getId()));
		cDTO.setName(computer.getName());
		cDTO.setDiscontinued((computer.getDiscontinued() == null) ? null : computer.getDiscontinued().toString());
		cDTO.setIntroduced((computer.getIntroduced() == null) ? null : computer.getIntroduced().toString());
		cDTO.setCompanyId(computer.getCompany() == null ? null : Long.toString(computer.getCompany().getId()));
		cDTO.setCompanyName(computer.getCompany() == null ? null : computer.getCompany().getName());
		return cDTO;
	}
	
	
	public static List<ComputerDTO> mapList(List<Computer> listeComputer) {
		List<ComputerDTO> liste = new ArrayList<>();
		for (Computer computer : listeComputer) {
			liste.add(ComputerDTOMapper.map(computer));
		}
		return liste;
	}
}
