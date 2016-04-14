package com.formation.computerdatabase.servlets.computer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.computerdatabase.services.ServiceFactory;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

/**
 * Servlet implementation class ServletDelete
 */
@WebServlet("/ServletDelete")
public class ServletDelete extends HttpServlet {
	/*
	private static final long serialVersionUID = 1L;
	
	//private static Logger logger = LoggerFactory.getLogger("com.excilys.formation.computerdatabase");
    private ComputerDaoServiceImpl computerService;
	
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.computerService = service.getComputerDaoServiceImpl();
	}
	*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		if (request.getParameter("selection") !=  null) {
			try {
				String[] idArray = request.getParameter("selection").split(",");
				for (int i = 0; i < idArray.length; i++) {
					long id = Long.parseLong(idArray[i]);
					computerService.delete(id);
					//logger.info("Suppression du computer d'id " + id);
				}
				
				response.sendRedirect("dashboard");
			} catch (NumberFormatException e) {
				request.getRequestDispatcher("/views/500.jsp").forward( request, response );
			}
		}
		else {
			response.sendRedirect("dashboard");
		}
		*/
	}

}
