package com.formation.computerdatabase.binding.mapper;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.core.model.Company;
import com.formation.computerdatabase.core.model.Computer;

/**
 * The Class ComputerMapper.
 */
public class ComputerMapper {

	/**
	 * Map.
	 *
	 * @param rs the rs
	 * @return the computer
	 * @throws SQLException the SQL exception
	 */
	
	public static Computer map(ComputerDTO cDTO) {
		return new Computer.Builder(cDTO.getName())
				.id( "".equals(cDTO.getId()) ? 0 : Long.parseLong(cDTO.getId()))
				.introduced(cDTO.getIntroduced())
				.discontinued(cDTO.getDiscontinued())
				.company(new Company.Builder("")
						.id(Long.parseLong(cDTO.getCompanyId()))
						.build())
				.build();
	}
	
}
