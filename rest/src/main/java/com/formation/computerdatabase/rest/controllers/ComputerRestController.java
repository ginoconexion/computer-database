package com.formation.computerdatabase.rest.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.binding.mapper.ComputerDTOMapper;
import com.formation.computerdatabase.binding.mapper.ComputerMapper;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.mapper.PagerMapper;
import com.formation.computerdatabase.service.util.Pager;

@RestController
public class ComputerRestController {

	@Autowired
	ComputerDaoService computerService;
	
	
	@RequestMapping(value = "/computer/list", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> liste(@RequestParam Map<String,String> requestParams) {
		Pager<ComputerDTO> pager = PagerMapper.map(requestParams);
		computerService.updatePager(pager);
		pager.updateListe();
		return pager.getListe();
	}
	
	@RequestMapping(value = "/computer/list", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	public Pager<ComputerDTO> liste(@RequestBody Pager<ComputerDTO> pager) {
		System.out.println(pager);
		computerService.updatePager(pager);
		pager.updateListe();
		return pager;
	}
	
	@RequestMapping(value = "/computer/{id}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO editComputer(@PathVariable Integer id) {
		ComputerDTO computerDTO = ComputerDTOMapper.map(computerService.getById(id));
		return computerDTO;
	}
	
	@RequestMapping(value = "/computer/add", method = RequestMethod.POST)
	public ComputerDTO addComputer(@Valid @RequestBody ComputerDTO computerDTO) {
		System.out.println(computerDTO);
		computerService.create(ComputerMapper.map(computerDTO));
		return computerDTO;
	}
	
	@RequestMapping(value = "/computer/edit", method = RequestMethod.POST)
	public ComputerDTO editComputer(@Valid @RequestBody ComputerDTO computerDTO) {
		System.out.println(computerDTO);
		computerService.update(ComputerMapper.map(computerDTO));
		return computerDTO;
	}
	
	@RequestMapping(value = "/computer/delete/{id}", method = RequestMethod.GET)
	public void deleteComputer(@PathVariable Integer id) {
		computerService.delete(computerService.getById(id));
	}
	
	
}