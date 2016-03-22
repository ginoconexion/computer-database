package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.pagination.Order;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.ConnexionFactory;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;

/**
 * The Enum ComputerDaoImpl.
 */
public enum ComputerDaoImpl implements ComputerDao {
	
	/** The instance. */
	INSTANCE;
	
	/** The Constant SELECT_LIMIT. */
	private final static String SELECT = "SELECT * FROM computer ";
	private final static String LIMIT = "LIMIT ?, ? ";
	private final static String JOIN_ON_COMPANY = "LEFT JOIN company on computer.id = company.id ";
	private final static String WHERE_NAME = " WHERE computer.name LIKE ?  OR company.name LIKE ? ";

	@Override
	public List<Computer> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Computer> liste = new ArrayList<>();
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			StringBuilder sb = new StringBuilder(SELECT);
			
			int i = 1;
			
			if (filter.containsKey(Order.SEARCH) || filter.containsKey(Order.BY_COMPANY)) {
				sb.append(JOIN_ON_COMPANY).append(WHERE_NAME);
			}
			Order.orderBy(filter, sb);
			
			sb.append(" ").append(LIMIT);
			pstmt = connexion.prepareStatement(sb.toString());
			
			if (filter.containsKey(Order.SEARCH) || filter.containsKey(Order.BY_COMPANY)) {
				pstmt.setString(i++, "%" + (String) filter.get("search") + "%");
				pstmt.setString(i++, "%" + (String) filter.get("search") + "%");
			}
			pstmt.setInt(i++, from);
			pstmt.setInt(i++, nb);
			rs = pstmt.executeQuery();
			liste = ComputerMapper.mapList(rs);
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return liste;
	}

	private final static String SELECT_COUNT = "SELECT COUNT(*) as nb_computers FROM computer ";
	
	@Override
	public int getNbEntries(HashMap<String, Object> filter) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nbEntries = 0;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			String sql = SELECT_COUNT;
			
			if (filter.containsKey("search")){
				sql = sql + JOIN_ON_COMPANY + WHERE_NAME;
			}
			
			pstmt = connexion.prepareStatement(sql);
			
			if (filter.containsKey("search")) {
				pstmt.setString(1, "%" + (String) filter.get("search") + "%");
				pstmt.setString(2, "%" + (String) filter.get("search") + "%");
			}
			
			rs = pstmt.executeQuery();
			if (rs.next()){
				nbEntries = rs.getInt("nb_computers");
			}
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, pstmt, null);
		}
		return nbEntries;
	}

	/** The Constant SELECT_BY_ID. */
	private final static String SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
	
	@Override
	public Computer getById(long id) throws DAONotFoundException {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Computer computer = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt = connexion.prepareStatement(SELECT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				computer = ComputerMapper.map(rs);
			}
			else {
				throw new DAONotFoundException("Le computer demandé n'existe pas");
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			ConnexionFactory.close(connexion, rs, null, pstmt);
		}
		return computer;
	}

	/** The Constant INSERT. */
	private final static String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	
	@Override
	public void create(Computer computer) {
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt = connexion.prepareStatement(INSERT);
			pstmt.setString(1, computer.getName());
			pstmt.setString(2, (computer.getIntroduced() == null) ? null : computer.getIntroduced().toString());
			pstmt.setString(3, (computer.getDiscontinued() == null) ? null : computer.getDiscontinued().toString());
			pstmt.setLong(4, (computer.getCompany() == null) ? 0 : computer.getCompany().getId() );
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		} finally {
			ConnexionFactory.close(connexion, null, null, pstmt);
		}
	}

	/** The Constant UPDATE. */
	private final static String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	
	@Override
	public void update(Computer computer) {
		
		Connection connexion = null;
		PreparedStatement pstmt = null;
		
		try {
			connexion = ConnexionFactory.INSTANCE.getConnection();
			pstmt =  connexion.prepareStatement(UPDATE);
			pstmt.setString(1, computer.getName());
			pstmt.setString(2, (computer.getIntroduced() == null) ? null : computer.getIntroduced().toString());
			pstmt.setString(3, (computer.getDiscontinued() == null) ? null : computer.getDiscontinued().toString());
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

	/** The Constant DELETE. */
	private final static String DELETE = "DELETE FROM computer WHERE id = ?";
	
	@Override
	public void delete(long id) {
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

	/** The Constant SELECT_BY_NAME. */
	private final static String SELECT_BY_NAME = "SELECT * FROM computer WHERE name = ?";
	
	@Override
	public Computer getByName(String name) {
		
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
