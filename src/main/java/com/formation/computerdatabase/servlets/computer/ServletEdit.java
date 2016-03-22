package com.formation.computerdatabase.servlets.computer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.persistence.forms.ComputerForm;
import com.formation.computerdatabase.persistence.mapper.dto.ComputerDTOMapper;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

/**
 * Servlet implementation class ServletEdit
 */
@WebServlet("/ServletEdit")
public class ServletEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static Logger logger = LogManager.getLogger("com.formation.computerdatabase.console");
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
    public ServletEdit() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		ComputerDTO computerDTO = null;
		try {
			computerDTO = ComputerDTOMapper.map(computerService.getById(id));
			request.setAttribute("computer", computerDTO);
			request.setAttribute("companies", liste);
			request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
			
		} catch (DAONotFoundException e) {
			request.getRequestDispatcher("/views/404.jsp").forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ComputerForm form = new ComputerForm(computerService, companyService);
		// pas hyper propre, en cas d'erreurs interne elle est catchée ...
		
		Computer computer = null;
		try {
			computer = computerService.getById(Long.parseLong(request.getParameter("id")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAONotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		form.updateComputer(request, computer);
		request.setAttribute("companies", liste);
		if (form.getErreurs().isEmpty()) {
			//logger.info("Modification reussie du computer : " + computer);
			response.sendRedirect("dashboard");
		}
		else {
			request.setAttribute("form", form);
			request.setAttribute("computer", ComputerDTOMapper.map(computer));
			request.getRequestDispatcher("/views/addComputer.jsp").forward( request, response );
			}
	}

}
