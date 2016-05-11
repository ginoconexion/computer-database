package com.formation.computerdatabase.rest.controllers;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.formation.computerdatabase.binding.dto.CompanyDTO;
import com.formation.computerdatabase.binding.mapper.CompanyDTOMapper;
import com.formation.computerdatabase.core.model.Company;
import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.service.CompanyDaoService;
import com.formation.computerdatabase.service.ComputerDaoService;

@RestController
public class CompanyRestController {
	
	@Autowired
	private ComputerDaoService computerService;
	
	@Autowired
	private CompanyDaoService companyService;
	
	@RequestMapping(value = "/company/list", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public List<CompanyDTO> list() {
		return CompanyDTOMapper.mapList(companyService.getAll());
	}
	
	@RequestMapping(value = "/company/delete/{id}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathVariable Integer id) {
	
		System.out.println(id);
		List<Computer> liste = computerService.getListByCompany(id);
		System.out.println(liste);
		computerService.deleteList(liste);
		Company company = companyService.getById(id);
		companyService.delete(id);
	}
}
