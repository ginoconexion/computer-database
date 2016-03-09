package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.DAOFactory;
import com.formation.computerdatabase.persistence.mapper.CompanyMapper;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;

public class CompanyDaoImpl implements CompanyDao {

	private DAOFactory daoFactory;
	private static Logger logger = LogManager.getLogger("com.formation.computerdatabase.console");
	
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

	@Override
	public int getNbEntries() {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		int nbEntries = 0;
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT COUNT(*) as nb_companies FROM company";
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				nbEntries = rs.getInt("nb_companies");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, rs, stmt, null);
		}
		return nbEntries;
	}

	@Override
	public List<Company> getFromTo(int from, int nb) {
		// TODO Auto-generated method stub
		logger.info("Affichage de la liste des computers");
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Company> liste = new ArrayList<Company>();
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM company LIMIT ?, ?";
			pstmt = connexion.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, nb);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				Company company = CompanyMapper.map(rs);
				liste.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, rs, null, pstmt);
		}
		return liste;
	}

}
