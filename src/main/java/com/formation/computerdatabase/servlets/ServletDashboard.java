package com.formation.computerdatabase.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.pagination.Pager;

/**
 * Servlet implementation class ServletDashboard
 */
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private ComputerDaoServiceImpl computerService;
	
	
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.computerService = service.getComputerDaoServiceImpl();
	}
	
    /**
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
		System.out.println(request.getParameter("page"));
		
		int page = 1;
		if (request.getParameter("page") != null){
			try {
				page = Integer.parseInt(request.getParameter("page"));
				
			} catch (NumberFormatException e){
				// traitement
			}
		}
		
		Pager pager = new Pager<>(10, page, computerService);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/views/dashboard.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
