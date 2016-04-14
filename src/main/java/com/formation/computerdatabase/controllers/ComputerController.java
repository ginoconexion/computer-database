package com.formation.computerdatabase.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.forms.ComputerForm;
import com.formation.computerdatabase.services.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

@Controller
public class ComputerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class.getSimpleName());
	@Autowired
	private ComputerDaoServiceImpl computerService;
	@Autowired
	private CompanyDaoServiceImpl companyService;
	
	@RequestMapping(value = "/computer/add", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("computer", new Computer());
		return "addComputer";
	}
	
	@RequestMapping(value="/computer/add", method=RequestMethod.POST)
    public String greetingSubmit(@RequestParam Map<String,String> requestParams, @Validated @ModelAttribute Computer computer, Model model) {
        
        
		List<Company> companies = companyService.getAll();
		ComputerForm form = new ComputerForm(computerService, companyService);
		
		form.validate(computer);
		
		computer = form.addComputer(computer);
		model.addAttribute("form", form);
		model.addAttribute("computer", computer);
		model.addAttribute("companies", companies);
		
		if (form.getErreurs().isEmpty()) {
			return "dashboard"
			response.sendRedirect("dashboard");
			LOGGER.info("Computer created : " + computer);
		}
		else {
			request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
		}
		
        return "result";
    }
}
