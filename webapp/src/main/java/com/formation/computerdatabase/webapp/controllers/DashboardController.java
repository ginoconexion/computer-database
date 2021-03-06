package com.formation.computerdatabase.webapp.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.mapper.PagerMapper;
import com.formation.computerdatabase.service.util.Pager;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private ComputerDaoService computerService;

	@RequestMapping(method = RequestMethod.GET)
	public String dashboard(@RequestParam Map<String,String> requestParams, Model model) {
		Pager<ComputerDTO> pager = PagerMapper.map(requestParams);
		
		// on set la liste et le nombre d'entrées
		computerService.updatePager(pager);
		
		pager.updateListe();
		if (pager.isOutofBounds()) {
			return "403";
		}
		else {
			model.addAttribute("pager", pager);
			return "dashboard";
		}
	}
}
