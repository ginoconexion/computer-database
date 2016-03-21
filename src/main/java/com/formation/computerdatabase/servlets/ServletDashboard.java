package com.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

/**
 * Servlet implementation class ServletDashboard
 */
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private static Logger logger = LogManager.getLogger("com.formation.computerdatabase.console");
	private static Logger logger = LoggerFactory.getLogger("com.excilys.formation.computerdatabase");
    private ComputerDaoServiceImpl computerService;
    private Pager<ComputerDTO> pager;
    private HashMap<String, Object> filter;
	
	
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.computerService = service.getComputerDaoServiceImpl();
		filter = new HashMap<>();
		this.pager = new Pager<>(10, 1, computerService, filter);
	}
	
    /**
     * Instantiates a new servlet dashboard.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ServletDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		filter.remove("byName");
		if (request.getParameter("page") != null) {
			try {
				pager.setPageActuelle(Integer.parseInt(request.getParameter("page")));
				
			} catch (NumberFormatException e) {
				// traitement
			}
		}
		if (request.getParameter("nb") != null) {
			try {
				pager.setNbParPage(Integer.parseInt(request.getParameter("nb")));
				
			} catch (NumberFormatException e) {
				// traitement
			}
		}
		
		if (request.getParameter("search") !=  null ) {
			try {
				filter.put("byName", request.getParameter("search").toLowerCase());
			} catch (Exception e) {
				
			}
		}
		pager.updateListe();
		if (pager.isOutofBounds()) {
			response.sendRedirect("dashboard");
		}
		else {
			//request.setAttribute("url", "Dashboard?nb="+pager.getNbParPage()+"&se");
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("/views/dashboard.jsp").forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("selection") !=  null) {
			try {
				String[] idArray = request.getParameter("selection").split(",");
				for (int i = 0; i < idArray.length; i++) {
					long id = Long.parseLong(idArray[i]);
					computerService.delete(id);
					logger.info("Suppression du computer d'id " + id);
				}
				
				doGet(request, response);
			} catch (NumberFormatException e) {
				request.getRequestDispatcher("/views/500.jsp").forward( request, response );
			}
		}
		else {
			//doGet(request, response);
			response.sendRedirect("dashboard");
		}
	}

}
