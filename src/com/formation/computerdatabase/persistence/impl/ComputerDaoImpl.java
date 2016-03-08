package com.formation.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.DAOFactory;

public class ComputerDaoImpl implements ComputerDao {

	private DAOFactory daoFactory;
	
	public ComputerDaoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Computer> getAll() {
		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Computer> list = new ArrayList<Computer>();
		
		try {
			connexion = daoFactory.getConnection();
			String sql = "SELECT * FROM computers";
			stmt = connexion.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				Computer computer = ComputerMapper.map(rs);
				list.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Computer getComputerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createComputer(Computer computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComputer(Computer computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComputer(Computer computer) {
		// TODO Auto-generated method stub
		
	}

}
