package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.mapper.CompanyMapper;

/**
 * The Enum CompanyDaoImpl.
 */
@Component
public class CompanyDaoImpl implements CompanyDao {
	
	@Autowired
	private ConnexionFactory connexionFactory;
	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public int getCount(HashMap<String, Object> filter) {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		int nbEntries = 0;
		
		try {
			connexion = connexionFactory.getConnection();
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
			connexion = connexionFactory.getConnection();
			String sql = SELECT_LIMIT;
			pstmt = connexion.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, nb);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				Company company = companyMapper.map(rs);
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
			connexion = connexionFactory.getConnection();
			String sql = SELECT_ALL;
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				Company company = companyMapper.map(rs);
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
			connexion = connexionFactory.getConnection();
			pstmt = connexion.prepareStatement(SELECT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				company = companyMapper.map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return company;
	}
	
	/** The Constant DELETE. */
	private final static String DELETE = "DELETE FROM company WHERE id = ?";
	
	@Override
	public void delete(long id) {
		PreparedStatement pstmt = null;
		Connection connexion = null;
		
		try {
			connexion = connexionFactory.getConnection();
			pstmt = connexion.prepareStatement(DELETE);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			
			// si auto commit à true on est pas dans une transaction donc on ferme la connexion
			try {
				if (connexion.getAutoCommit()) {
					ConnexionFactory.close(connexion, null, null, pstmt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ConnexionFactory.close(connexion, null, null, pstmt);
		}
	}
}
