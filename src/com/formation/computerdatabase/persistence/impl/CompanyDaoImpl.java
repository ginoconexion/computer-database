package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.DAOFactory;
import com.formation.computerdatabase.persistence.mapper.CompanyMapper;

public class CompanyDaoImpl implements CompanyDao {

	private DAOFactory daoFactory;
	
	public CompanyDaoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Company> liste = new ArrayList<Company>();
		
		try {
			connexion = daoFactory.getConnection();
			stmt = connexion.createStatement();
			String sql = "SELECT * FROM company";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Company company = CompanyMapper.map(rs);
				liste.add(company);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, rs, stmt, null);
		}
		return liste;
	}
}
