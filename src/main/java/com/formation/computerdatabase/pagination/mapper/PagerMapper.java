package com.formation.computerdatabase.pagination.mapper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.formation.computerdatabase.pagination.Order;
import com.formation.computerdatabase.pagination.Pager;

public class PagerMapper<T> {
	
	private final static int NB_PAR_PAGE = 10;
	private final static int PAGE = 1;
	
	public static <T> Pager<T> map(HttpServletRequest request) {
		
		HashMap<String, Object> filter = new HashMap<>();
		Pager<T> pager = new Pager<>(NB_PAR_PAGE, PAGE, filter);
		
		if (request.getParameter(Order.PAGE) != null) {
			try {
				pager.setCurrent(Integer.parseInt(request.getParameter(Order.PAGE)));
				
			} catch (NumberFormatException e) {
				// traitement
			}
		}
		if (request.getParameter(Order.OFFSET) != null) {
			try {
				pager.setOffset(Integer.parseInt(request.getParameter(Order.OFFSET)));
				
			} catch (NumberFormatException e) {
				// traitement
			}
		}
		if (request.getParameter(Order.SEARCH) !=  null ) {
				// si l'utilisateur a entré une nouvelle recherche
				if (!request.getParameter(Order.SEARCH).equals(filter.get(Order.SEARCH)))
					pager.setCurrent(1);
				filter.remove(Order.BY_NAME);
				filter.remove(Order.BY_COMPANY);
				filter.remove(Order.BY_DISCONTINUED);
				filter.remove(Order.BY_INTRODUCED);
				filter.put("search", request.getParameter("search").toLowerCase());
		}
		
		if (request.getParameter(Order.BY_NAME) != null) {
			if (request.getParameter(Order.BY_NAME).equals(Order.ASC) || request.getParameter(Order.BY_NAME).equals(Order.DESC))
				filter.put(Order.BY_NAME, request.getParameter(Order.BY_NAME));
		}
		if (request.getParameter(Order.BY_INTRODUCED) != null) {
			if (request.getParameter(Order.BY_INTRODUCED).equals(Order.ASC) || request.getParameter(Order.BY_INTRODUCED).equals(Order.DESC))
				filter.put(Order.BY_INTRODUCED, request.getParameter(Order.BY_INTRODUCED));
		}
		if (request.getParameter(Order.BY_DISCONTINUED) != null) {
			if (request.getParameter(Order.BY_DISCONTINUED).equals(Order.ASC) || request.getParameter(Order.BY_DISCONTINUED).equals(Order.DESC))
				filter.put(Order.BY_DISCONTINUED, request.getParameter(Order.BY_DISCONTINUED));
		}
		if (request.getParameter(Order.BY_COMPANY) != null) {
			if (request.getParameter(Order.BY_COMPANY).equals(Order.ASC) || request.getParameter(Order.BY_COMPANY).equals(Order.DESC))
				filter.put(Order.BY_COMPANY, request.getParameter(Order.BY_COMPANY));
		}
		
		return pager;
	}
}
