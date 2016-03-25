package com.formation.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.pagination.mapper.PagerMapper;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.ServiceFactory;

/**
 * Servlet implementation class ServletDashboard
 */
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private static Logger logger = LoggerFactory.getLogger("com.excilys.formation.computerdatabase");
    private ComputerDaoService computerService;
    
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.computerService = service.getComputerDaoServiceImpl();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// on map le pager au niveau des différent paramètre
		Pager<ComputerDTO> pager = PagerMapper.map(request);
		// on set la liste et le nombre d'entrées
		computerService.updatePager(pager);
		
		pager.updateListe();
		if (pager.isOutofBounds()) {
			response.sendRedirect("dashboard");
		}
		else {
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("/views/dashboard.jsp").forward( request, response );
		}
	}

}
