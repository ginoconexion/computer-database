package com.formation.computerdatabase.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.pagination.Order;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;

/**
 * The Enum ComputerDaoImpl.
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {
	
	/** The Constant SELECT_LIMIT. */
	private final static String SELECT = "SELECT * FROM computer ";
	private final static String LIMIT = "LIMIT ?, ? ";
	private final static String JOIN_ON_COMPANY = "LEFT JOIN company as company on computer.id = company.id ";
	private final static String WHERE_NAME = " WHERE computer.name LIKE ?  OR company.name LIKE ? ";
	private final static String WHERE_ID = " WHERE computer.id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ComputerMapper computerMapper;
	@Override
	public List<Computer> getFromTo(int from, int nb, HashMap<String, Object> filter) {
		
		ArrayList<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder(SELECT);
		sb.append(JOIN_ON_COMPANY);
		if (filter.containsKey(Order.SEARCH) || filter.containsKey(Order.BY_COMPANY)) {
			sb.append(WHERE_NAME);
			//on set computer.name ou company.name
			params.add("%" + (String) filter.get("search") + "%");
			params.add("%" + (String) filter.get("search") + "%");
		}
		
		// on ajoute à la requete les éventuels filtre asc ou desc
		Order.orderBy(filter, sb);
		sb.append(" ").append(LIMIT);
		params.add(from);
		params.add(nb);
		
		return jdbcTemplate.query(sb.toString(), params.toArray(), computerMapper);
	}

	private final static String SELECT_COUNT = "SELECT COUNT(*) as nb_computers FROM computer ";
	
	@Override
	public int getCount(HashMap<String, Object> filter) {
		String sql = SELECT_COUNT;
		
		if (filter.containsKey("search")) {
			sql = sql + JOIN_ON_COMPANY + WHERE_NAME;
			ArrayList<Object> params = new ArrayList<>();
			
			params.add("%" + (String) filter.get("search") + "%");
			params.add("%" + (String) filter.get("search") + "%");
			return jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);
		}
		else {
			return jdbcTemplate.queryForObject(sql, Integer.class);
		}
	}

	/** The Constant SELECT_BY_ID. */
	private final static String SELECT_BY_ID = "SELECT * FROM computer ";
	
	@Override
	public Computer getById(long id) {
		return jdbcTemplate.queryForObject(SELECT_BY_ID + JOIN_ON_COMPANY + WHERE_ID, computerMapper, new Object[] {id});
	}

	/** The Constant INSERT. */
	private final static String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?) ";
	
	@Override
	public void create(Computer computer) {
		Object[] params = new Object[4];
		params[0] = computer.getName();
		params[1] = (computer.getIntroduced() == null) ? null : computer.getIntroduced().toString();
		params[2] = (computer.getDiscontinued() == null) ? null : computer.getDiscontinued().toString();
		params[3] = (computer.getCompany() == null) ? 0 : computer.getCompany().getId();
		jdbcTemplate.update(INSERT, params);
	}

	/** The Constant UPDATE. */
	private final static String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ? ";
	
	@Override
	public void update(Computer computer) {
		Object[] params = new Object[5];
		params[0] = computer.getName();
		params[1] = (computer.getIntroduced() == null) ? null : computer.getIntroduced().toString();
		params[2] = (computer.getDiscontinued() == null) ? null : computer.getDiscontinued().toString();
		params[3] = (computer.getCompany() == null) ? 0 : computer.getCompany().getId();
		params[4] = computer.getId();
		
		System.out.println(params[3]);
		System.out.println(jdbcTemplate.update(UPDATE, params));
		
	}

	/** The Constant DELETE. */
	private final static String DELETE = "DELETE FROM computer WHERE id = ?";
	
	public void delete(long id) {
		jdbcTemplate.update(DELETE, id);
	    // logger msg
	    return;
	}

	private final static String SELECT_BY_COMPANY_ID = "SELECT * FROM computer WHERE company_id = ? ";
	@Override
	public List<Computer> getListByCompany(long id) {
		return jdbcTemplate.query(SELECT_BY_COMPANY_ID, computerMapper, new Object[]{id});
	}

	@Override
	public void deleteList(List<Computer> list) {
		for (Computer computer : list) {
			jdbcTemplate.update(DELETE, computer.getId());
		}
	}

	@Override
	public Computer getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
