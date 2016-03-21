package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.mapper.CompanyMapper;

/**
 * The Enum CompanyDaoImpl.
 */
public enum CompanyDaoImpl implements CompanyDao {
	
	/** The instance. */
	INSTANCE;
	
	@Override
	public int getNbEntries(HashMap<String, Object> filter) {
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
	
	@Override
	public List<Company> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		
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
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return company;
	}
	
	/** The Constant DELETE. */
	private final static String DELETE_COMPUTERS = "DELETE FROM computer WHERE company_id = ?";
	private final static String DELETE = "DELETE FROM company WHERE id = ?";
	
	@Override
	public void delete(long id) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			connexion.setAutoCommit(false);
			pstmt = connexion.prepareStatement(DELETE_COMPUTERS);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = connexion.prepareStatement(DELETE);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			connexion.commit();
			
		} catch (SQLException e) {
			try {
				connexion.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e.getMessage());
			}
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, null, null, pstmt);
		}
	}
}
