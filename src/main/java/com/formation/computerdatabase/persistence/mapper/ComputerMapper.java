package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;

/**
 * The Class ComputerMapper.
 */
@Component
public class ComputerMapper implements RowMapper<Computer> {

	/**
	 * Map.
	 *
	 * @param rs the rs
	 * @return the computer
	 * @throws SQLException the SQL exception
	 */
	public Computer map(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced((rs.getTimestamp("introduced") == null) ? null : rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
		computer.setDiscontinued((rs.getTimestamp("discontinued") == null) ? null : rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
		computer.setCompany(new Company.Builder(rs.getString("cp.name"))
								.id(rs.getLong("cp.id"))
								.build()
		);
		return computer;
	}
	
	
	/**
	 * Map list.
	 *
	 * @param rs the rs
	 * @return the list
	 */
	public List<Computer> mapList(ResultSet rs) {
		List<Computer> liste = new ArrayList<>();
		try {
			while (rs.next()){
				liste.add(map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	public static Computer map(ComputerDTO cDTO) {
		return new Computer.Builder(cDTO.getName())
				.id(cDTO.getId().equals("") ? 0 : Long.parseLong(cDTO.getId()))
				.introduced(cDTO.getIntroduced())
				.discontinued(cDTO.getDiscontinued())
				.company(new Company.Builder("")
						.id(Long.parseLong(cDTO.getCompanyId()))
						.build())
				.build();
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		LocalDate introduced = null;
	    LocalDate discontinued = null;
	    if (rs.getTimestamp("introduced") != null) {
	    	introduced = rs.getTimestamp("introduced").toLocalDateTime().toLocalDate();
	    }
	    if (rs.getTimestamp("discontinued") != null) {
	    	discontinued = rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate();
	    }
	    
	    System.out.println(rs.getString("company.name"));
		Computer c = new Computer.Builder(rs.getString("name"))
			.id(rs.getLong("id"))
			.introduced(introduced)
			.discontinued(discontinued)
			.company(new Company.Builder(rs.getString("company.name")).id(rs.getLong("company_id")).build())
			.build();
		return c;
	}
	
}
