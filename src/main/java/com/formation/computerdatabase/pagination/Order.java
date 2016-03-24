package com.formation.computerdatabase.pagination;

import java.util.HashMap;

public class Order {
	public final static String BY_NAME = "orderByName";
	public final static String BY_COMPANY = "orderByCompany";
	public final static String BY_INTRODUCED = "orderByIntroduced";
	public final static String BY_DISCONTINUED = "orderByDiscontinued";
	public final static String SEARCH = "search";
	public final static String ASC = "asc";
	public final static String DESC = "desc";
	public static final String OFFSET = "offset";
	public static final String PAGE = "page";
	
	public final static void orderBy(HashMap<String, Object> filter, StringBuilder sb) {
		
		if (filter.containsKey(BY_INTRODUCED) || filter.containsKey(BY_DISCONTINUED) || filter.containsKey(BY_COMPANY) || filter.containsKey(BY_NAME)) {
			sb.append("ORDER BY ");
			boolean first = true;
			if (filter.containsKey(BY_INTRODUCED)) {
				sb.append("introduced ").append(filter.get(BY_INTRODUCED));
				first = false;
			}
			if (filter.containsKey(BY_DISCONTINUED)) {
				if (!first)
					sb.append(",");
				sb.append("discontinued ").append(filter.get(BY_DISCONTINUED));
				first = false;
			}
			if (filter.containsKey(BY_COMPANY)) {
				if (!first)
					sb.append(",");
				sb.append("company.name ").append(filter.get(BY_COMPANY));
				first = false;
			}
			if (filter.containsKey(BY_NAME)) {
				if (!first)
					sb.append(",");
				sb.append("computer.name ").append(filter.get(BY_NAME));
			}
		}
	}
}
