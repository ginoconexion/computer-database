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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.formation.computerdatabase.model.dto.CompanyDTO;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.persistence.mapper.dto.CompanyDTOMapper;
import com.formation.computerdatabase.services.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

@Controller
public class ComputerController extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class.getSimpleName());
	@Autowired
	private ComputerDaoServiceImpl computerService;
	@Autowired
	private CompanyDaoServiceImpl companyService;
	
	@RequestMapping(value = "/computer/add", method = RequestMethod.GET)
	public String showComputerForm(ComputerDTO computerDTO, Model model) {
		List<CompanyDTO> companiesDTO = CompanyDTOMapper.mapList(companyService.getAll());
		model.addAttribute("companiesDTO", companiesDTO);
		return "addComputer";
	}
	
	@RequestMapping(value="/computer/add", method=RequestMethod.POST)
    public String addComputer(@Valid @ModelAttribute("computerDTO")ComputerDTO computerDTO, BindingResult bindingResult, Model model) {
        
		List<CompanyDTO> companiesDTO = CompanyDTOMapper.mapList(companyService.getAll());
		//System.out.println(computer);
		model.addAttribute("companiesDTO", companiesDTO);
		//model.addAttribute("computer", computer);
		
		if (bindingResult.hasErrors()) {
			System.out.println("form error");
			return "addComputer";
		}
		
		else {
			//return "redirect:/dashboard";
			System.out.println("form ok");
			return "addComputer";
		}
		
		//ComputerForm form = new ComputerForm(computerService, companyService);
		//form.validate(computer);
		
		/*
		computer = form.addComputer(computer);
		model.addAttribute("form", form);
		model.addAttribute("computer", computer);
		
		
		if (form.getErreurs().isEmpty()) {
			return "dashboard"
			response.sendRedirect("dashboard");
			LOGGER.info("Computer created : " + computer);
		}
		else {
			request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
		}
		
        return "result";
        */
    }
}
