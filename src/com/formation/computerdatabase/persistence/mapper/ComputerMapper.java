package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.formation.computerdatabase.model.Computer;

public class ComputerMapper {

	public static Computer map(ResultSet rs) throws SQLException{
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced(rs.getTimestamp("introduced"));
		computer.setDiscontinued(rs.getTimestamp("discontinued"));
		computer.setCompanyId(rs.getLong("company_id"));
		return computer;
	}
}
