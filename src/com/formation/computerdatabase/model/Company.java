package com.formation.computerdatabase.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Company {
	
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private static Company map(ResultSet rs ) throws SQLException{
		Company company = new Company();
		company.setId(rs.getLong("id"));
		company.setName(rs.getString("name"));
		return company;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	
}
