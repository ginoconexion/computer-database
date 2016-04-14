package com.formation.computerdatabase.pagination.mapper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.formation.computerdatabase.pagination.Order;
import com.formation.computerdatabase.pagination.Pager;

public class PagerMapper<T> {
	
	private final static int NB_PAR_PAGE = 10;
	private final static int PAGE = 1;
	
	public static <T> Pager<T> map(Map<String, String> requestParams) {
		
		HashMap<String, Object> filter = new HashMap<>();
		Pager<T> pager = new Pager<>(NB_PAR_PAGE, PAGE, filter);
		
		if (requestParams.get(Order.PAGE) != null) {
			try {
				pager.setCurrent(Integer.parseInt(requestParams.get(Order.PAGE)));
				
			} catch (NumberFormatException e) {
				// traitement
				
			}
		}
		if (requestParams.get(Order.OFFSET) != null) {
			try {
				pager.setOffset(Integer.parseInt(requestParams.get(Order.OFFSET)));
				
			} catch (NumberFormatException e) {
				// traitement
			}
		}
		if (requestParams.get(Order.SEARCH) !=  null ) {
				// si l'utilisateur a entré une nouvelle recherche
				if (requestParams.get(Order.SEARCH).equals(filter.get(Order.SEARCH)))
					pager.setCurrent(1);
				/*
				filter.remove(Order.BY_NAME);
				filter.remove(Order.BY_COMPANY);
				filter.remove(Order.BY_DISCONTINUED);
				filter.remove(Order.BY_INTRODUCED);*/
				filter.put("search", requestParams.get(Order.SEARCH).toLowerCase());
		}
		
		if (requestParams.get(Order.BY_NAME) != null) {
			if (requestParams.get(Order.BY_NAME).equals(Order.ASC) || requestParams.get(Order.BY_NAME).equals(Order.DESC))
				filter.put(Order.BY_NAME, requestParams.get(Order.BY_NAME));
		}
		if (requestParams.get(Order.BY_INTRODUCED) != null) {
			if (requestParams.get(Order.BY_INTRODUCED).equals(Order.ASC) || requestParams.get(Order.BY_INTRODUCED).equals(Order.DESC))
				filter.put(Order.BY_INTRODUCED, requestParams.get(Order.BY_INTRODUCED));
		}
		if (requestParams.get(Order.BY_DISCONTINUED) != null) {
			if (requestParams.get(Order.BY_DISCONTINUED).equals(Order.ASC) || requestParams.get(Order.BY_DISCONTINUED).equals(Order.DESC))
				filter.put(Order.BY_DISCONTINUED, requestParams.get(Order.BY_DISCONTINUED));
		}
		if (requestParams.get(Order.BY_COMPANY) != null) {
			if (requestParams.get(Order.BY_COMPANY).equals(Order.ASC) || requestParams.get(Order.BY_COMPANY).equals(Order.DESC))
				filter.put(Order.BY_COMPANY, requestParams.get(Order.BY_COMPANY));
		}
		
		return pager;
	}
}
