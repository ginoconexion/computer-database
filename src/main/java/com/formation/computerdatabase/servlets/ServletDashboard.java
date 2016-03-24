package com.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.pagination.Order;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.persistence.mapper.dto.ComputerDTOMapper;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.ServiceFactory;

/**
 * Servlet implementation class ServletDashboard
 */
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger("com.excilys.formation.computerdatabase");
    private ComputerDaoService computerService;
    private Pager<Computer> pager;
    private List<ComputerDTO> liste;
    private HashMap<String, Object> filter;
    
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.computerService = service.getComputerDaoServiceImpl();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		filter = new HashMap<>();
		pager = new Pager<>(10, 1, computerService, filter);
		// a mettre dans un validator de paramètres
		
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
		
		pager.updateListe();
		if (pager.isOutofBounds()) {
			response.sendRedirect("dashboard");
		}
		else {
			liste = ComputerDTOMapper.mapList(computerService.getFromTo(pager.getFrom(), pager.getNbPages(), filter));
			request.setAttribute("liste", liste);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("/views/dashboard.jsp").forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
