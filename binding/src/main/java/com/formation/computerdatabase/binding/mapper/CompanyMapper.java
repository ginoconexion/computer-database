package com.formation.computerdatabase.binding.mapper;

/**
 * The Class CompanyMapper.
 */
//@Component
public class CompanyMapper {

	/**
	 * Map.
	 *
	 * @param rs the rs
	 * @return the company
	 * @throws SQLException the SQL exception
	 */
	/*
	public Company map(ResultSet rs) throws SQLException{
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		return company;
	}
	*/
	/**
	 * Map.
	 *
	 * @param companyDto the company dto
	 * @return the company
	 */
	/*
	public Company map(CompanyDTO companyDto) {
		Company company = new Company();
		company.setId(Long.parseLong(companyDto.getId()));
		company.setName(companyDto.getName());
		return company;
	}
	*/
}
