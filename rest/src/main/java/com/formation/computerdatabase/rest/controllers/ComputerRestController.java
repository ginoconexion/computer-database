package com.formation.computerdatabase.rest.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.mapper.PagerMapper;
import com.formation.computerdatabase.service.util.Pager;

@RestController
public class ComputerRestController {

	@Autowired
	ComputerDaoService computerService;
	
	
	@RequestMapping(value = "/computer/liste", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> liste(@RequestParam Map<String,String> requestParams) {
		Pager<ComputerDTO> pager = PagerMapper.map(requestParams);
		computerService.updatePager(pager);
		pager.updateListe();
		return pager.getListe();
	}
	
	@RequestMapping(value = "/computer/edit/{id}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public Computer editComputer(@PathVariable Integer id) {
		Computer computer = computerService.getById(id);
		return computer;
	}
	
	@RequestMapping(value = "/computer/add", method = RequestMethod.POST)
	public Response addComputer(@RequestBody ComputerDTO computerDTO) {
		System.out.println(computerDTO);
		return null;
	}
}