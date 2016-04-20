package com.formation.computerdatabase.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.persistence.mapper.dto.ComputerDTOMapper;
import com.formation.computerdatabase.services.ComputerDaoService;

@Service
public class ComputerDaoServiceImpl implements ComputerDaoService {
	
	/** The computer dao impl. */
	@Autowired
	private ComputerDaoImpl computerDaoImpl;
	
	@Override
	public Computer getById(long id)  {
		return computerDaoImpl.getById(id);
	}
	@Override
	public void create(Computer computer) {
		computerDaoImpl.create(computer);
	}
	@Override
	public void update(Computer computer) {
		computerDaoImpl.update(computer);
	}
	@Override
	public void delete(long id) {
		computerDaoImpl.delete(id);
	}
	@Override
	public Computer getByName(String name) {
		return computerDaoImpl.getByName(name);
	}
	@Override
	public List<Computer> getListByCompany(long id) {
		return computerDaoImpl.getListByCompany(id);
	}
	@Override
	public void deleteList(List<Computer> list) {
		computerDaoImpl.deleteList(list);
	}

	@Override
	public void updatePager(Pager<Computer> pager) {
		pager.setCount(computerDaoImpl.getCount(pager.getFilter()));
		List<Computer> liste = computerDaoImpl.getFromTo(pager.getFrom(), pager.getOffset(), pager.getFilter());
		pager.setListe(computerDaoImpl.getFromTo(pager.getFrom(), pager.getOffset(), pager.getFilter()));
		pager.updateListe();
	}
}
