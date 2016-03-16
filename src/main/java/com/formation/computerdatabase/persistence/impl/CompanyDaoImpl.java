package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.mapper.CompanyMapper;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;

// TODO: Auto-generated Javadoc
/**
 * The Enum CompanyDaoImpl.
 */
public enum CompanyDaoImpl implements CompanyDao {
	
	/** The instance. */
	INSTANCE;
	
	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getNbEntries()
	 */
	@Override
	public int getNbEntries() {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		int nbEntries = 0;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			String sql = "SELECT COUNT(*) as nb_companies FROM company";
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				nbEntries = rs.getInt("nb_companies");
			}
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, stmt, null);
		}
		return nbEntries;
	}

	/** The Constant SELECT_LIMIT. */
	private final static String SELECT_LIMIT = "SELECT * FROM company LIMIT ?, ?";
	
	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getFromTo(int, int)
	 */
	@Override
	public List<Company> getFromTo(int from, int nb) {
		
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Company> liste = new ArrayList<Company>();
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			String sql = SELECT_LIMIT;
			pstmt = connexion.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, nb);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				Company company = CompanyMapper.map(rs);
				liste.add(company);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return liste;
	}
	
/** The Constant SELECT_ALL. */
private final static String SELECT_ALL = "SELECT * FROM company";
	
	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getAll()
	 */
	@Override
	public List<Company> getAll() {
		
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Company> liste = new ArrayList<Company>();
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			String sql = SELECT_ALL;
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				Company company = CompanyMapper.map(rs);
				liste.add(company);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, stmt, null);
		}
		return liste;
	}

	/** The Constant SELECT_BY_ID. */
	private final static String SELECT_BY_ID = "SELECT * FROM company WHERE id = ?";
	
	/* (non-Javadoc)
	 * @see com.formation.computerdatabase.persistence.CompanyDao#getById(long)
	 */
	@Override
	public Company getById(long id) {
		
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Company company = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt = connexion.prepareStatement(SELECT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				company = CompanyMapper.map(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return company;
	}

}