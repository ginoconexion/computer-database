package com.formation.computerdatabase.servlets.computer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.forms.ComputerForm;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

/**
 * Servlet implementation class ServletAdd
 */
public class ServletAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private CompanyDaoServiceImpl companyService;
    private ComputerDaoServiceImpl computerService;
    private List<Company> liste;
	
	public void init() {
		ServiceFactory service = (ServiceFactory) getServletContext().getAttribute("service");
		this.companyService = service.getCompanyDaoServiceImpl();
		this.computerService = service.getComputerDaoServiceImpl();
		this.liste = companyService.getAll();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		liste = companyService.getAll();
		request.setAttribute("companies", liste);
		request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		liste = companyService.getAll();
		ComputerForm form = new ComputerForm(computerService, companyService);
		Computer computer = form.addComputer(request);
		request.setAttribute("form", form);
		request.setAttribute("computer", computer);
		request.setAttribute("companies", liste);
		
		if (form.getErreurs().isEmpty()) {
			response.sendRedirect("Dashboard");
		}
		else {
			request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
		}
	}

}
