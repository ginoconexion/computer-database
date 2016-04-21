package com.formation.computerdatabase.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		ComputerDTO computerDTO = ComputerDTOMapper.map(computerService.getById(id));
		model.addAttribute("companiesDTO", companiesDTO);
		model.addAttribute("computerDTO", computerDTO);
		return "formComputer";
	}
	
	@RequestMapping(value = "/computer/edit/{id}", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult bindingResult, Model model) {
		
		LocaleContextHolder.getLocale().toString();
		List<CompanyDTO> companiesDTO = CompanyDTOMapper.mapList(companyService.getAll());
		model.addAttribute("companiesDTO", companiesDTO);
		if (bindingResult.hasErrors()) {
			return "formComputer";
		}
		else {
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
			return "formComputer";
		}
		else {
			computerService.create(ComputerMapper.map(computerDTO));
			return "redirect:/dashboard";
		}
    }
	
	@RequestMapping(value = "/computer/delete", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> param) {
		
		if (param.get("selection") != null) {
			try {
				for (String idParameter : param.get("selection").split(",")) {
		            long id = Long.parseLong(idParameter);
		            if (id > 0) {
		                computerService.delete(id);
		            }
		        }
			} catch (NumberFormatException e) {
				LOGGER.info("attempt to delete computer(s), params : " +param.get("selection"));
				return "500";
			}
		}
		 
		return "redirect:/dashboard";
    }
	
}
