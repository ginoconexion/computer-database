package com.formation.computerdatabase.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.pagination.mapper.PagerMapper;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private ComputerDaoServiceImpl computerService;

	@RequestMapping(method = RequestMethod.GET)
	public String dashboard(@RequestParam Map<String,String> requestParams, Model model) {
		
		System.out.println();
		Pager<Computer> pager = PagerMapper.map(requestParams);
		
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