package com.formation.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.dto.CompanyDTO;


/**
 * The Class CompanyMapper.
 */
@Component
public class CompanyMapper implements RowMapper<Company> {

	/**
	 * Map.
	 *
	 * @param rs the rs
	 * @return the company
	 * @throws SQLException the SQL exception
	 */
	public Company map(ResultSet rs) throws SQLException{
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		return company;
	}
	
	/**
	 * Map.
	 *
	 * @param companyDto the company dto
	 * @return the company
	 */
	public Company map(CompanyDTO companyDto) {
		Company company = new Company();
		company.setId(Long.parseLong(companyDto.getId()));
		company.setName(companyDto.getName());
		return company;
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Company.Builder(rs.getString("name")).id(rs.getLong("id")).build();
	}
	
}
