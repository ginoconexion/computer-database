package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.formation.computerdatabase.model.Company;


public class CompanyMapper {

	public static Company map(ResultSet rs) throws SQLException{
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		return company;
	}
}
