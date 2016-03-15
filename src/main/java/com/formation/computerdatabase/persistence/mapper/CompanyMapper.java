package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.formation.computerdatabase.model.Company;


// TODO: Auto-generated Javadoc
/**
 * The Class CompanyMapper.
 */
public class CompanyMapper {

	/**
	 * Map.
	 *
	 * @param rs the rs
	 * @return the company
	 * @throws SQLException the SQL exception
	 */
	public static Company map(ResultSet rs) throws SQLException{
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		return company;
	}
}
