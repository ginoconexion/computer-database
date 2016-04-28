package com.formation.computerdatabase.persistence.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.formation.computerdatabase.core.model.Company;
import com.formation.computerdatabase.core.model.Computer;

public class OrderBy {

	public final static String BY_NAME = "orderByName";
	public final static String BY_COMPANY = "orderByCompany";
	public final static String BY_INTRODUCED = "orderByIntroduced";
	public final static String BY_DISCONTINUED = "orderByDiscontinued";
	public final static String SEARCH = "search";
	public final static String ASC = "asc";
	public final static String DESC = "desc";
	public static final String OFFSET = "offset";
	public static final String PAGE = "page";
	
	public final static void orderBy(HashMap<String, Object> filter, Root<Computer> computer, Join<Computer, Company> company, CriteriaBuilder builder, CriteriaQuery query) {
		
		if (filter.containsKey(BY_INTRODUCED) || filter.containsKey(BY_DISCONTINUED) || filter.containsKey(BY_COMPANY) || filter.containsKey(BY_NAME)) {
			List<Order> orders = new ArrayList<>();
			
			if (filter.containsKey(BY_INTRODUCED)) {
				if (filter.get(BY_INTRODUCED).equals(ASC))
					orders.add(builder.asc(computer.get("introduced")));
				else 
					orders.add(builder.desc(computer.get("introduced")));
			}
			if (filter.containsKey(BY_DISCONTINUED)) {
				if (filter.get(BY_DISCONTINUED).equals(ASC))
					orders.add(builder.asc(computer.get("discontinued")));
				else
					orders.add(builder.desc(computer.get("discontinued")));
			}
			if (filter.containsKey(BY_COMPANY)) {
				if (filter.get(BY_COMPANY).equals(ASC))
					orders.add(builder.asc(company.get("name")));
				else 
					orders.add(builder.desc(company.get("name")));
			}
			if (filter.containsKey(BY_NAME)) {
				if (filter.get(BY_NAME).equals(ASC))
					orders.add(builder.asc(computer.get("name")));
				else
					orders.add(builder.desc(company.get("name")));
			}
			query.orderBy(orders);
		}
	}
}
