package com.formation.computerdatabase.webapp.mapper;

import java.util.HashMap;
import java.util.Map;

import com.formation.computerdatabase.persistence.util.OrderBy;
import com.formation.computerdatabase.service.util.Pager;

public class PagerMapper<T> {
	
	private final static int NB_PAR_PAGE = 10;
	private final static int PAGE = 1;
	
	public static <T> Pager<T> map(Map<String, String> requestParams) {
		
		HashMap<String, Object> filter = new HashMap<>();
		Pager<T> pager = new Pager<>(NB_PAR_PAGE, PAGE, filter);
		
		if (requestParams.get(OrderBy.PAGE) != null) {
			pager.setCurrent(Integer.parseInt(requestParams.get(OrderBy.PAGE)));
		}
		if (requestParams.get(OrderBy.OFFSET) != null) {
			pager.setOffset(Integer.parseInt(requestParams.get(OrderBy.OFFSET)));
		}
		if (requestParams.get(OrderBy.SEARCH) !=  null ) {
			// si l'utilisateur a entré une nouvelle recherche
			if (requestParams.get(OrderBy.SEARCH).equals(filter.get(OrderBy.SEARCH))) {
				pager.setCurrent(1);
			}
			filter.put("search", requestParams.get(OrderBy.SEARCH).toLowerCase());
		}
		
		if (requestParams.get(OrderBy.BY_NAME) != null) {
			if (requestParams.get(OrderBy.BY_NAME).equals(OrderBy.ASC) || requestParams.get(OrderBy.BY_NAME).equals(OrderBy.DESC))
				filter.put(OrderBy.BY_NAME, requestParams.get(OrderBy.BY_NAME));
		}
		if (requestParams.get(OrderBy.BY_INTRODUCED) != null) {
			if (requestParams.get(OrderBy.BY_INTRODUCED).equals(OrderBy.ASC) || requestParams.get(OrderBy.BY_INTRODUCED).equals(OrderBy.DESC))
				filter.put(OrderBy.BY_INTRODUCED, requestParams.get(OrderBy.BY_INTRODUCED));
		}
		if (requestParams.get(OrderBy.BY_DISCONTINUED) != null) {
			if (requestParams.get(OrderBy.BY_DISCONTINUED).equals(OrderBy.ASC) || requestParams.get(OrderBy.BY_DISCONTINUED).equals(OrderBy.DESC))
				filter.put(OrderBy.BY_DISCONTINUED, requestParams.get(OrderBy.BY_DISCONTINUED));
		}
		if (requestParams.get(OrderBy.BY_COMPANY) != null) {
			if (requestParams.get(OrderBy.BY_COMPANY).equals(OrderBy.ASC) || requestParams.get(OrderBy.BY_COMPANY).equals(OrderBy.DESC))
				filter.put(OrderBy.BY_COMPANY, requestParams.get(OrderBy.BY_COMPANY));
		}
		return pager;
	}
}
