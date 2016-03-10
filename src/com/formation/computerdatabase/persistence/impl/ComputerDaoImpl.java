package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.DAOFactory;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;

public class ComputerDaoImpl implements ComputerDao {

	private DAOFactory daoFactory;
	
	public ComputerDaoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Computer> getFromTo(int from, int to) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Computer> liste = new ArrayList<Computer>();
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM computer LIMIT ?, ?";
			pstmt = connexion.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				Computer computer = ComputerMapper.map(rs);
				liste.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, rs, null, pstmt);
		}
		return liste;
	}



	@Override
	public int getNbEntries() {
		// TODO Auto-generated method stub
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		int nbEntries = 0;
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT COUNT(*) as nb_computers FROM computer";
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				nbEntries = rs.getInt("nb_computers");
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
	public List<Computer> getAll() {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Computer> list = new ArrayList<Computer>();
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM computer";
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				Computer computer = ComputerMapper.map(rs);
				list.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, rs, stmt, null);
		}
		return list;
	}

	@Override
	public Computer getComputerById(long id) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Computer computer = null;
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM computer WHERE id = ?";
			pstmt = connexion.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			// si l'entrée existe
			if (rs.next()){
				computer = ComputerMapper.map(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, rs, null, pstmt);
		}
		return computer;
	}

	@Override
	public int createComputer(Computer computer) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		int nbRow = 0;
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
			pstmt = connexion.prepareStatement(sql);
			pstmt.setString(1, computer.getName());
			pstmt.setTimestamp(2, computer.getIntroduced());
			pstmt.setTimestamp(3, computer.getDiscontinued());
			pstmt.setLong(4, computer.getCompanyId());
			nbRow = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, null, null, pstmt);
		}
		return nbRow;
	}

	@Override
	public void updateComputer(Computer computer) {
		
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
			pstmt =  connexion.prepareStatement(sql);
			pstmt.setString(1, computer.getName());
			pstmt.setTimestamp(2, computer.getIntroduced());
			pstmt.setTimestamp(3, computer.getDiscontinued());
			pstmt.setLong(4, computer.getCompanyId());
			pstmt.setLong(5, computer.getId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		finally {
			DAOFactory.close(connexion, null, null, pstmt);
		}
	}

	@Override
	public void deleteComputer(long id) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "DELETE FROM computer WHERE id = ?";
			pstmt = connexion.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		} finally {
			DAOFactory.close(connexion, null, null, pstmt);
		}
	}

}
