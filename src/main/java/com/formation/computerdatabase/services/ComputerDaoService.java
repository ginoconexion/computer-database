package com.formation.computerdatabase.services;

import java.util.List;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.pagination.Pager;

public interface ComputerDaoService {
	
	void create(Computer computer);
	void update(Computer computer);
	void delete(Computer computer);
	List<Computer> getListByCompany(long id);
	void deleteList(List<Computer> list);
	void updatePager(Pager<ComputerDTO> pager);
	Computer getById(long id); 
}
