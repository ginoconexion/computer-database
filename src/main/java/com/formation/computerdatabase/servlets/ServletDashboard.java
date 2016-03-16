package com.formation.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

/**
 * Servlet implementation class ServletDashboard
 */
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LogManager.getLogger("com.formation.computerdatabase.console");
    private ComputerDaoServiceImpl computerService;
    private Pager<Computer> pager;
	
	
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.computerService = service.getComputerDaoServiceImpl();
		this.pager = new Pager<>(10, 1, computerService);
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
		
		int page = 1;
		int nb = 10;
		if (request.getParameter("page") != null) {
			try {
				pager.setPageActuelle(Integer.parseInt(request.getParameter("page")));
				//page = Integer.parseInt(request.getParameter("page"));
				
			} catch (NumberFormatException e) {
				// traitement
			}
		}
		if (request.getParameter("nb") != null) {
			try {
				pager.setNbParPage(Integer.parseInt(request.getParameter("nb")));
				//nb = Integer.parseInt(request.getParameter("nb"));
				
			} catch (NumberFormatException e) {
				// traitement
			}
		}
		pager.updateListe();
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/views/dashboard.jsp").forward( request, response );
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
					computerService.deleteComputer(id);
					logger.info("Suppression du computer d'id " + id);
				}
				
				doGet(request, response);
			} catch (NumberFormatException e) {
				request.getRequestDispatcher("/views/500.jsp").forward( request, response );
			}
		}
		else {
			doGet(request, response);
		}
	}

}
