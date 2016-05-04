package com.formation.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.binding.mapper.ComputerDTOMapper;
import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.persistence.ComputerDao;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.util.Pager;


@Service
public class ComputerDaoServiceImpl implements ComputerDaoService {
	/** The computer dao impl. */
	@Autowired
	private ComputerDao computerDaoImpl;
	
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
	public void delete(Computer computer) {
		computerDaoImpl.delete(computer);
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
	public void updatePager(Pager<ComputerDTO> pager) {
		pager.setCount(computerDaoImpl.getCount(pager.getFilter()));
		List<ComputerDTO> liste = ComputerDTOMapper.mapList(computerDaoImpl.getFromTo(pager.getFrom(), pager.getOffset(), pager.getFilter()));
		pager.setListe(liste);
		pager.updateListe();
	}
	public ComputerDao getComputerDaoImpl() {
		return computerDaoImpl;
	}
}
