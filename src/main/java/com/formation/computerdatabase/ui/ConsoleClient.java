package com.formation.computerdatabase.ui;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.formation.computerdatabase.exception.DAOException;
import com.formation.computerdatabase.exception.DAONotFoundException;
import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.pagination.Pager;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;
import com.formation.computerdatabase.util.Regexp;

public class ConsoleClient {
	
	//private static Logger logger = LogManager.getLogger("com.formation.computerdatabase.console");
	private final static int nbParPage = 10;
	private final static Pattern patternChoix = Pattern.compile(Regexp.REGEXP_CHOIX);
	
	private ServiceFactory serviceFactory;
	private ComputerDaoServiceImpl computerDaoServiceImpl;
	private CompanyDaoServiceImpl companyDaoServiceImpl;
	
	
	public void printDeleteCompanyById() {
		System.out.println("Supprimer une company : ");
		System.out.println("Entrer l'id de la company :");
		Scanner scanner = new Scanner(System.in);
		String choix = null;
		Pattern patternId = Pattern.compile(Regexp.REGEXP_LONG);

		do {
			choix = scanner.next();
			long id = Long.parseLong(choix);
			companyDaoServiceImpl.delete(id);
		} while (patternId.matcher(choix).find());
	}
	
	
	
	
	public ConsoleClient(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
		this.computerDaoServiceImpl = serviceFactory.getComputerDaoServiceImpl();
		this.companyDaoServiceImpl = serviceFactory.getCompanyDaoServiceImpl();
	}
	
	public void printMenu() {
		
		System.out.println("------ Menu");
		System.out.println("Sélectioner une option :");
		System.out.println("a : afficher tous les computers");
		System.out.println("b : afficher toutes les companies");
		System.out.println("c : afficher un computer via son id");
		System.out.println("d : créer un computer");
		System.out.println("e : mettre à jour un computer via son id");
		System.out.println("f : supprimer un computer via son id");
		System.out.println("---- Fin Menu");

		// creation d'un scanner pour lire les entrée en ligne de commande
		Scanner scanner = new Scanner(System.in);
		System.out.println("Entrez votre choix : ");

		String choix = null;
		do {
			choix = scanner.next();
		}
		while (!patternChoix.matcher(choix).find());
		
		switch (choix) {
		case "a":
			printAllComputers(null);
			break;
		case "b":
			printAllCompanies(null);
			break;
		case "c":
			printComputerById();
			break;
		case "d":
			createComputer();
			break;
		case "e":
			updateComputer();
			break;
		case "f":
			deleteComputer(1);
			break;
		}
	}

	public void printAllComputers(Pager<Computer> pager) {
		
		HashMap<String, Object> filter = new HashMap<>();
		
		if (pager == null) {
			int nbEntries = computerDaoServiceImpl.getNbEntries(filter);
			
			pager = new Pager(nbEntries, 1, computerDaoServiceImpl, filter);
		}
		//pager.printListe();
		
		System.out.println("Page suivante : n | Page précédente : p Quitter : q ");
		Scanner scanner = new Scanner(System.in);
		String choix = null;

		choix = scanner.next().trim();
		if ("n".equals(choix)) {
			pager.next();
			printAllComputers(pager);
		} else if ("p".equals(choix)) {
			pager.prev();
			printAllComputers(pager);
		} else if ("q".equals(choix)) {
			printMenu();
		}
		else {
			printAllComputers(pager);
		}
	}

	public void printComputerById() {
		System.out.println("Affichage d'un computer : ");
		System.out.println("Entrer l'id du computer :");
		Scanner scanner = new Scanner(System.in);
		String choix = null;
		Pattern patternId = Pattern.compile(Regexp.REGEXP_LONG);

		do {
			choix = scanner.next();
			long id = Long.parseLong(choix);
			Computer computer = null;
			try {
				computer = computerDaoServiceImpl.getById(id);
			} catch (DAONotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (computer == null) {
				String message = "Le computer choisi n'existe pas";
				System.err.println(message);
				throw new DAOException(message);
			}
			System.out.println("----------- " + computer.toString());
		} while (patternId.matcher(choix).find());
	}
	
	private void hydrateComputer(Computer computer) {
		System.out.println("Entrer le nom du computer :");
		Pattern pattern = null;
		Scanner scanner = new Scanner(System.in);
		computer.setName(scanner.nextLine());
		
		pattern = Pattern.compile(Regexp.REGEXP_DATE);
		String introduced = null;

		System.out.println("Entrez la date introduced au format dd-mm-YYYY");
		do {
			introduced = scanner.nextLine();
		} while (!pattern.matcher(introduced).find());
		//computer.setIntroduced(LocalDate.);

		System.out.println("Entrez la date discontinued au format dd-mm-YYYY");
		String continued = null;

		do {
			continued = scanner.next().trim();
		} while (!pattern.matcher(continued).find());
		//computer.setDiscontinued(continued);

		System.out.println("Entrez l'id de la company");
		pattern = Pattern.compile(Regexp.REGEXP_LONG);
		String companyIdString = null;
		long companyId;

		do {
			companyIdString = scanner.next();
			companyId = Long.parseLong(companyIdString);
		} while (!pattern.matcher(companyIdString).find());
		computer.setCompany(CompanyDaoImpl.INSTANCE.getById(companyId));
	}
	
	public void createComputer() {
		System.out.println("Creation d'un nouvel ordinateur");
		Computer computer = new Computer();
		hydrateComputer(computer);
		computerDaoServiceImpl.create(computer);
		//logger.info("Création ordinateur : " + computer);
	}

	public void updateComputer() {
		System.out.println("Mise à jour d'un ordinateur, entrez l'id de l'ordinateur que vous souhaitez modifier : ");
		Pattern pattern = Pattern.compile(Regexp.REGEXP_LONG);
		Scanner scanner = new Scanner(System.in);
		String computerIdString = null;
		do {
			computerIdString = scanner.next().trim();
		} while (!pattern.matcher(computerIdString).find());
		long computerId = Long.parseLong(computerIdString);
		Computer computer = null;
		try {
			computer = computerDaoServiceImpl.getById(computerId);
		} catch (DAONotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hydrateComputer(computer);
		computerDaoServiceImpl.update(computer);
		//logger.info("Mise à jour computer : " + computer);
	}

	public void deleteComputer(long id) {
		System.out.println("Supression de l'ordinateur d'id " + id);
		computerDaoServiceImpl.delete(id);
		//logger.info("Suppression du computer d'id : " + id);
	}

	public void printAllCompanies(Pager pager) {
		HashMap<String, Object> filter = new HashMap<>();
		
		if (pager == null) {
			int nbEntries = computerDaoServiceImpl.getNbEntries(filter);
			pager = new Pager<>(nbEntries, 1, computerDaoServiceImpl, filter);
		}
		//pager.printListe();

		System.out.println("Page suivante : n | Page précédente : p Quitter : q ");
		Scanner scanner = new Scanner(System.in);
		String choix = null;

		choix = scanner.next().trim();
		if ("n".equals(choix)) {
			pager.next();
			printAllCompanies(pager);
		} else if ("p".equals(choix)) {
			pager.prev();
			printAllCompanies(pager);
		} else if ("q".equals(choix)) {
			printMenu();
		}
		else {
			printAllCompanies(pager);
		}
	}
	
	public static void main(String[] args) {
		ConsoleClient consoleClient = new ConsoleClient(ServiceFactory.INSTANCE);
		consoleClient.printMenu();
	}
}
