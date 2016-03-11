package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;

public enum ComputerDaoImpl implements ComputerDao {
	INSTANCE;
	
	private final static String SELECT_LIMIT = "SELECT * FROM computer LIMIT ?, ?";
	
	
	@Override
	public List<Computer> getFromTo(int from, int nb) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Computer> liste = new ArrayList<>();
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			String sql = SELECT_LIMIT;
			pstmt = connexion.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, nb);
			rs = pstmt.executeQuery();
			
			liste = ComputerMapper.mapList(rs);
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
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
			connexion = ConnexionFactory.INSTANCE.getConnection();
			String sql = "SELECT COUNT(*) as nb_computers FROM computer";
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				nbEntries = rs.getInt("nb_computers");
			}
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, stmt, null);
		}
		return nbEntries;
	}

	private final static String SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
	
	@Override
	public Computer getComputerById(long id) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Computer computer = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt = connexion.prepareStatement(SELECT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()){
				computer = ComputerMapper.map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return computer;
	}

	private final static String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	
	@Override
	public void createComputer(Computer computer) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt = connexion.prepareStatement(INSERT);
			pstmt.setString(1, computer.getName());
			pstmt.setTimestamp(2, (computer.getIntroduced() == null) ? null : Timestamp.valueOf(computer.getIntroduced().toString()));
			pstmt.setTimestamp(3, (computer.getIntroduced() == null) ? null : Timestamp.valueOf(computer.getIntroduced().toString()));
			pstmt.setLong(4, (computer.getCompany() == null) ? 0 : computer.getCompany().getId() );
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			ConnexionFactory.close(connexion, null, null, pstmt);
		}
	}

	private final static String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	@Override
	public void updateComputer(Computer computer) {
		
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt =  connexion.prepareStatement(UPDATE);
			pstmt.setString(1, computer.getName());
			pstmt.setTimestamp(2, (computer.getIntroduced() == null) ? null : Timestamp.valueOf(computer.getIntroduced().toString()));
			pstmt.setTimestamp(3, (computer.getIntroduced() == null) ? null : Timestamp.valueOf(computer.getIntroduced().toString()));
			pstmt.setLong(4, computer.getCompany().getId());
			pstmt.setLong(5, computer.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		finally {
			ConnexionFactory.close(connexion, null, null, pstmt);
		}
	}

	private final static String DELETE = "DELETE FROM computer WHERE id = ?";
	
	@Override
	public void deleteComputer(long id) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt = connexion.prepareStatement(DELETE);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, null, null, pstmt);
		}
	}

	private final static String SELECT_BY_NAME = "SELECT * FROM computer WHERE name = ?";
	
	@Override
	public Computer getComputerByName(String name) {
		
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Computer computer = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt = connexion.prepareStatement(SELECT_BY_NAME);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()){
				computer = ComputerMapper.map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return computer;
	}
}
