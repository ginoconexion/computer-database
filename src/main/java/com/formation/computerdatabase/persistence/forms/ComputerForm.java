package com.formation.computerdatabase.persistence.forms;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.exception.FormValidationException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;
import com.formation.computerdatabase.util.Formatter;
import com.formation.computerdatabase.util.Regexp;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerForm.
 */
public class ComputerForm {
	
	/** The Constant CHAMP_NAME. */
	private final static String CHAMP_NAME = "computerName";
	
	/** The Constant CHAMP_INTRODUCED. */
	private final static String CHAMP_INTRODUCED = "introduced";
	
	/** The Constant CHAMP_DISCONTINUED. */
	private final static String CHAMP_DISCONTINUED = "discontinued";
	
	/** The Constant CHAMP_COMPANY_ID. */
	private final static String CHAMP_COMPANY_ID = "companyId";
	
	/** The resultat. */
	private String resultat;
	
	/** The erreurs. */
	private Map<String, String> erreurs = new HashMap<>();
	
	/** The computer service. */
	private ComputerDaoServiceImpl computerService;
	
	/** The company service. */
	private CompanyDaoServiceImpl companyService;
	
	/** The pattern date. */
	private Pattern patternDate = Pattern.compile(Regexp.REGEXP_DATE);
	
	/** The pattern long. */
	private Pattern patternLong = Pattern.compile(Regexp.REGEXP_LONG);
	
	/**
	 *  constructeur.
	 *
	 * @param computerService the computer service
	 * @param companyService the company service
	 */
	public ComputerForm(ComputerDaoServiceImpl computerService, CompanyDaoServiceImpl companyService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}

	/**
	 *  getters.
	 *
	 * @return the resultat
	 */
	public String getResultat() {
		return resultat;
	}

	/**
	 * Gets the erreurs.
	 *
	 * @return the erreurs
	 */
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	/**
	 * Sets the erreur.
	 *
	 * @param champ the champ
	 * @param erreur the erreur
	 */
	public void setErreur(String champ, String erreur) {
		this.erreurs.put(champ, erreur);
	}

	/**
	 * Hydrate.
	 *
	 * @param request the request
	 * @param computer the computer
	 */
	public void hydrate(HttpServletRequest request, Computer computer) {
		String name = getValeurChamp(request, CHAMP_NAME);
		String introduced = getValeurChamp(request, CHAMP_INTRODUCED);
		String discontinued = getValeurChamp(request, CHAMP_DISCONTINUED);
		String companyId = getValeurChamp(request, CHAMP_COMPANY_ID);
		
		processName(name, computer);
		processIntroduced(introduced, computer);
		processDiscontinued(discontinued, computer);
		processCompanyId(companyId, computer);
	}
	
	
	/**
	 * Update computer.
	 *
	 * @param request the request
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer updateComputer(HttpServletRequest request, Computer computer) {
		hydrate(request, computer);
		try {
			if (erreurs.isEmpty()) {
				computerService.updateComputer(computer);
				resultat = "Succès de la modification du computer";
			}
			else {
				resultat = "Echec de la modification du computer";
			}
		} catch (DAOException e) {
			// a remplacer par un log
			e.printStackTrace();
		}
		return computer;
	}
	
	
	
	/**
	 * Adds the computer.
	 *
	 * @param request the request
	 * @return the computer
	 */
	public Computer addComputer(HttpServletRequest request) {
		Computer computer = new Computer();
		hydrate(request, computer);
		try {
			if (erreurs.isEmpty()) {
				computerService.createComputer(computer);
				resultat = "Succès de la création du computer";
			}
			else {
				resultat = "Echec de la création du computer";
			}
		} catch (DAOException e) {
			// a remplacer par un log
			e.printStackTrace();
		}
		
		return computer;
	}
	
	/**
	 * Process name.
	 *
	 * @param name the name
	 * @param computer the computer
	 */
	private void processName(String name, Computer computer) {
		try {
			validateName(name);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NAME, e.getMessage());
		}
		computer.setName(name);
	}
	
	/**
	 * Process introduced.
	 *
	 * @param introduced the introduced
	 * @param computer the computer
	 */
	private void processIntroduced(String introduced, Computer computer) {
		try {
			computer.setIntroduced(validateDate(introduced));
		} catch (FormValidationException e) {
			setErreur(CHAMP_INTRODUCED, e.getMessage());
		}
	}
	
	/**
	 * Process discontinued.
	 *
	 * @param discontinued the discontinued
	 * @param computer the computer
	 */
	private void processDiscontinued(String discontinued, Computer computer) {
		try {
			computer.setDiscontinued(validateDate(discontinued));
		} catch (FormValidationException e) {
			setErreur(CHAMP_DISCONTINUED, e.getMessage());
		}
	}
	
	/**
	 * Process company id.
	 *
	 * @param companyId the company id
	 * @param computer the computer
	 */
	private void processCompanyId(String companyId, Computer computer) {
		try {
			computer.setCompany(validateCompanyId(companyId));
			
		} catch (FormValidationException e) {
			setErreur(CHAMP_COMPANY_ID, e.getMessage());
		}
	}
	
	/**
	 * Validate name.
	 *
	 * @param name the name
	 * @throws FormValidationException the form validation exception
	 */
	private void validateName(String name) throws FormValidationException {
		if (name != null) {
			if (name.length() < 2 ){
				throw new FormValidationException("Le nom du computer doit contenir au moins 2 caractères.");
			}
			else if (name.length() > 25 ){
				throw new FormValidationException("Le nom du computer doit contenir au maximum 25 caractères");
			}
		}
		else {
			throw new FormValidationException("Veuillez entrer un name");
		}
	}
	
	/**
	 * Validate date.
	 *
	 * @param date the date
	 * @return the local date
	 * @throws FormValidationException the form validation exception
	 */
	private LocalDate validateDate(String date) throws FormValidationException{
		LocalDate ld = null;
		System.out.println("date : " + date);
		
		if (date != null && !Pattern.matches(Regexp.REGEXP_DATE, date)) {
			System.out.println("date incorrecte");
			throw new FormValidationException("Le format de la date n'est pas valide");
		}
		else if (date != null) {
			try {
				ld = LocalDate.parse(date);
			} catch (DateTimeException e) {
				throw new FormValidationException("La date n'est pas valide");
			}
		}
		return ld;
	}
	
	/**
	 * Validate company id.
	 *
	 * @param id the id
	 * @return the company
	 * @throws FormValidationException the form validation exception
	 */
	private Company validateCompanyId(String id) throws FormValidationException {
		Company company =  null;
		if (id == null) {
			throw new FormValidationException("Veuillez spécifier une company");
		}
		else if (!patternLong.matcher(id).find()){
			throw new FormValidationException("La company n'est pas valide");
		}
		else {
			company = companyService.getById(Long.parseLong(id));
			if (company == null){
				throw new FormValidationException("La company n'est pas valide");
			}
		}
		return company;
	}
	
	
	/**
	 * Gets the valeur champ.
	 *
	 * @param request the request
	 * @param nomChamp the nom champ
	 * @return the valeur champ
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter( nomChamp );
		if (valeur == null || valeur.trim().length() == 0){
			return null;
		}
		else {
			return valeur;
		}
	}
	
	
	
	
	
	

}
