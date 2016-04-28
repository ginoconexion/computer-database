package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.service.util.Pager;

public interface ComputerDaoService {
	
	void create(Computer computer);
	void update(Computer computer);
	void delete(Computer computer);
	List<Computer> getListByCompany(long id);
	void deleteList(List<Computer> list);
	void updatePager(Pager<ComputerDTO> pager);
	Computer getById(long id); 
}
