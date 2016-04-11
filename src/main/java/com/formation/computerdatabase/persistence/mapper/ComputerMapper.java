package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;

/**
 * The Class ComputerMapper.
 */
public class ComputerMapper {

	private CompanyDaoImpl companyDaoImpl;
	private CompanyMapper companyMapper;
	
	public ComputerMapper(CompanyDaoImpl companyDaoImpl, CompanyMapper companyMapper) {
		this.companyDaoImpl = companyDaoImpl;
		this.companyMapper = companyMapper;
	}
	
	/**
	 * Map.
	 *
	 * @param rs the rs
	 * @return the computer
	 * @throws SQLException the SQL exception
	 */
	public Computer map(ResultSet rs) throws SQLException{
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced((rs.getTimestamp("introduced") == null) ? null : rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
		computer.setDiscontinued((rs.getTimestamp("discontinued") == null) ? null : rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
		computer.setCompany(companyDaoImpl.getById(rs.getLong("company_id")));
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
	
	public Computer map(ComputerDTO cDTO) {
		Computer c = new Computer();
 		c.setId(Long.parseLong(cDTO.getId()));
		c.setIntroduced((cDTO.getIntroduced() == null) ? null : LocalDate.parse(cDTO.getIntroduced()));
		c.setDiscontinued((cDTO.getDiscontinued() == null) ? null : LocalDate.parse(cDTO.getDiscontinued()));
		c.setName(cDTO.getName());
		c.setCompany(companyMapper.map(cDTO.getCompany()));
		return c;
	}
	
}
