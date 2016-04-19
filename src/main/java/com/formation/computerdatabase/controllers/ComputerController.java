package com.formation.computerdatabase.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.formation.computerdatabase.model.dto.CompanyDTO;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.persistence.mapper.ComputerMapper;
import com.formation.computerdatabase.persistence.mapper.dto.CompanyDTOMapper;
import com.formation.computerdatabase.persistence.mapper.dto.ComputerDTOMapper;
import com.formation.computerdatabase.services.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

@Controller
public class ComputerController extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class.getSimpleName());
	@Autowired
	private ComputerDaoServiceImpl computerService;
	@Autowired
	private CompanyDaoServiceImpl companyService;
	
	@RequestMapping(value = "/computer/edit/{id}", method = RequestMethod.GET)
	public String showEditForm(@PathVariable Integer id, Model model) {
		
		List<CompanyDTO> companiesDTO = CompanyDTOMapper.mapList(companyService.getAll());
		
		System.out.println(computerService.getById(id));
		ComputerDTO computerDTO = ComputerDTOMapper.map(computerService.getById(id));
		System.out.println(computerDTO);
		model.addAttribute("companiesDTO", companiesDTO);
		model.addAttribute("computerDTO", computerDTO);
		return "formComputer";
	}
	
	@RequestMapping(value = "/computer/edit/{id}", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult bindingResult, Model model) {
		
		System.out.println("post");
		List<CompanyDTO> companiesDTO = CompanyDTOMapper.mapList(companyService.getAll());
		model.addAttribute("companiesDTO", companiesDTO);
		System.out.println(computerDTO);
		System.out.println(bindingResult);
		if (bindingResult.hasErrors()) {
			System.out.println("form error");
			return "formComputer";
		}
		else {
			System.out.println("form ok");
			
			System.out.println(ComputerMapper.map(computerDTO));
			computerService.update(ComputerMapper.map(computerDTO));
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping(value = "/computer/add", method = RequestMethod.GET)
	public String showComputerForm(ComputerDTO computerDTO, Model model) {
		List<CompanyDTO> companiesDTO = CompanyDTOMapper.mapList(companyService.getAll());
		model.addAttribute("companiesDTO", companiesDTO);
		return "formComputer";
	}
	
	@RequestMapping(value="/computer/add", method=RequestMethod.POST)
    public String add(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult bindingResult, Model model) {
        
		List<CompanyDTO> companiesDTO = CompanyDTOMapper.mapList(companyService.getAll());
		model.addAttribute("companiesDTO", companiesDTO);
		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
			System.out.println("form error");
			return "formComputer";
		}
		
		else {
			System.out.println("form ok");
			System.out.println(ComputerMapper.map(computerDTO));
			computerService.create(ComputerMapper.map(computerDTO));
			//return "formComputer";
			return "redirect:/dashboard";
		}
    }
}
