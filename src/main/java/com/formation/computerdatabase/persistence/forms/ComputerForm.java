package com.formation.computerdatabase.persistence.forms;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.exception.FormValidationException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.services.CompanyDaoService;
import com.formation.computerdatabase.services.ComputerDaoService;
import com.formation.computerdatabase.services.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;
import com.formation.computerdatabase.servlets.ServletDashboard;
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
	private ComputerDaoService computerService;
	
	/** The company service. */
	private CompanyDaoService companyService;
	
	/** The pattern date. */
	private Pattern patternDate = Pattern.compile(Regexp.REGEXP_DATE);
	
	/** The pattern long. */
	private Pattern patternLong = Pattern.compile(Regexp.REGEXP_LONG);
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletDashboard.class.getSimpleName());
	
	/**
	 *  constructeur.
	 *
	 * @param computerService2 the computer service
	 * @param companyService2 the company service
	 */
	public ComputerForm(ComputerDaoService computerService2, CompanyDaoService companyService2) {
		this.computerService = computerService2;
		this.companyService = companyService2;
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
	public Computer hydrate(Map<String,String> requestParams) {
		String name = processName(requestParams, CHAMP_NAME);
		LocalDate introduced = processIntroduced(requestParams, CHAMP_INTRODUCED);
		LocalDate discontinued = processDiscontinued(requestParams, CHAMP_DISCONTINUED);
		Company company = processCompanyId(requestParams, CHAMP_COMPANY_ID);
		Computer computer = new Computer.Builder(name).introduced(introduced).discontinued(discontinued).company(company).build();
		processIntroducedAndDiscontinued(computer);
		return computer;
	}
	
	
	/**
	 * Update computer.
	 *
	 * @param request the request
	 * @param computer the computer
	 * @return the computer
	 */
	public Computer updateComputer(Map<String, String> requestParams, Computer computer) {
		Computer c = hydrate(requestParams);
		c.setId(computer.getId());
		try {
			if (erreurs.isEmpty()) {
				System.out.println(computer.getName());
				computerService.update(c);
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
	
	public void validate(Computer computer) {
		
	}
	
	
	/**
	 * Adds the computer.
	 *
	 * @param request the request
	 * @return the computer
	 */
	public Computer addComputer(Computer computer) {
		/*
		Computer computer = hydrate(requestParams);
		try {
			if (erreurs.isEmpty()) {
				computerService.create(computer);
				resultat = "Succès de la création du computer";
			}
			else {
				resultat = "Echec de la création du computer";
			}
		} catch (DAOException e) {
			// a remplacer par un log
			e.printStackTrace();
		}
		*/
		return null;
	}
	
	/**
	 * Process name.
	 *
	 * @param name the name
	 * @param computer the computer
	 */
	private String processName(Map<String, String> requestParams, String champ) {
		String name = getValeurChamp(requestParams, champ);
		try {
			validateName(name);
		} catch (FormValidationException e) {
			setErreur(champ, e.getMessage());
		}
		return name;
	}
	
	/**
	 * Process introduced.
	 *
	 * @param introduced the introduced
	 * @param computer the computer
	 */
	private LocalDate processIntroduced(Map<String, String> requestParams, String champ) {
		LocalDate date = null;
		try {
			date = validateDate(getValeurChamp(requestParams, champ));
		} catch (FormValidationException e) {
			setErreur(CHAMP_INTRODUCED, e.getMessage());
		}
		return date;
	}
	
	/**
	 * Process discontinued.
	 *
	 * @param discontinued the discontinued
	 * @param computer the computer
	 */
	private LocalDate processDiscontinued(Map<String, String> requestParams, String champ) {
		LocalDate date = null;
		try {
			date = validateDate(getValeurChamp(requestParams, champ));
		} catch (FormValidationException e) {
			setErreur(CHAMP_DISCONTINUED, e.getMessage());
		}
		return date;
	}
	
	private void processIntroducedAndDiscontinued(Computer computer) {
		if (computer.getIntroduced() != null && computer.getDiscontinued() != null && computer.getDiscontinued().isBefore(computer.getIntroduced())) {
			setErreur(CHAMP_DISCONTINUED, "Le champ discontinued ne peut être antérieur à introduced");
		}
	}
	
	/**
	 * Process company id.
	 *
	 * @param request the company id
	 * @param champCompanyId the computer
	 * @return 
	 */
	private Company processCompanyId(Map<String, String> requestParams, String champCompanyId) {
		Company company = null;
		try {
			company = validateCompanyId(getValeurChamp(requestParams, champCompanyId));
			
		} catch (FormValidationException e) {
			setErreur(CHAMP_COMPANY_ID, e.getMessage());
		}
		return company;
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
				String message = "Le nom du computer doit contenir au moins 2 caractères.";
				LOGGER.debug(message);
				throw new FormValidationException(message);
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
				if (ld.isBefore(LocalDate.parse("1970-01-01"))) {
					throw new FormValidationException("La date ne peut être antérieure au 1970-01-01");
				}
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
			try {
				company = companyService.getById(Long.parseLong(id));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
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
	private static String getValeurChamp(Map<String, String> requestParams, String nomChamp) {
		String valeur = requestParams.get( nomChamp );
		if (valeur == null || valeur.trim().length() == 0){
			return null;
		}
		else {
			return valeur;
		}
	}
	
	
	
	
	
	

}
