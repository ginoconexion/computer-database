package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;

public class ComputerMapper {

	public static Computer map(ResultSet rs) throws SQLException{
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced((rs.getTimestamp("introduced") == null) ? null : rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
		computer.setDiscontinued((rs.getTimestamp("discontinued") == null) ? null : rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
		computer.setCompany(CompanyDaoImpl.INSTANCE.getById(rs.getLong("company_id")));
		return computer;
	}
	
	public static List<Computer> mapList(ResultSet rs) {
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
	
}
