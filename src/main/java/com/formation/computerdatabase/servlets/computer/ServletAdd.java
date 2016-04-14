package com.formation.computerdatabase.servlets.computer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.forms.ComputerForm;
import com.formation.computerdatabase.services.CompanyDaoService;
import com.formation.computerdatabase.services.ComputerDaoService;
import com.formation.computerdatabase.services.ServiceFactory;
import com.formation.computerdatabase.servlets.ServletDashboard;

/**
 * Servlet implementation class ServletAdd
 */
public class ServletAdd extends HttpServlet {
	/*
	private static final long serialVersionUID = 1L;
       
    private CompanyDaoService companyService;
    private ComputerDaoService computerService;
    private List<Company> liste;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletDashboard.class.getSimpleName());
	
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.companyService = service.getCompanyDaoServiceImpl();
		this.computerService = service.getComputerDaoServiceImpl();
		this.liste = companyService.getAll();
	}
	*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		liste = companyService.getAll();
		request.setAttribute("companies", liste);
		request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		liste = companyService.getAll();
		ComputerForm form = new ComputerForm(computerService, companyService);
		Computer computer = form.addComputer(request);
		request.setAttribute("form", form);
		request.setAttribute("computer", computer);
		request.setAttribute("companies", liste);
		
		if (form.getErreurs().isEmpty()) {
			response.sendRedirect("dashboard");
			LOGGER.info("Computer created : " + computer);
		}
		else {
			request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
		}
		*/
	}

}
