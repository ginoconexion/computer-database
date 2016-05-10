package com.formation.computerdatabase.console;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.binding.mapper.ComputerDTOMapper;
import com.formation.computerdatabase.core.model.Company;
import com.formation.computerdatabase.core.model.Computer;
import com.formation.computerdatabase.service.CompanyDaoService;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.util.Pager;

public class ConsoleClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleClient.class);
	private final static int OFFSET = 10;
	private final static Pattern PATTERN_CHOIX = Pattern.compile("^[a-f]{1}$");
	private final static Pattern PATTERN_DATE = Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
	private final static Pattern PATTERN_ID = Pattern.compile("[0-9]+");
	private static Scanner scanner = new Scanner(System.in);
	
	private ComputerDaoService computerDaoService;
	private CompanyDaoService companyDaoService;
	
	
	public void printDeleteCompanyById() {
		
		System.out.println("Supprimer une company : ");
		System.out.println("Entrer l'id de la company :");
		String choix = null;

		do {
			choix = scanner.next();
			long id = Long.parseLong(choix);
			companyDaoService.delete(id, computerDaoService);
		} while (PATTERN_ID.matcher(choix).find());
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
		System.out.println("Entrez votre choix : ");

		String choix = null;
		do {
			choix = scanner.next();
		}
		while (!PATTERN_CHOIX.matcher(choix).find());
		
		switch (choix) {
		case "a":
			printAllComputers(null);
			break;
		case "b":
			printAllCompanies();
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

	public void printAllComputers(Pager<ComputerDTO> pager) {
		
		LOGGER.info("Affichage de tous les ordinateurs");
		
		if (pager == null) {
			pager = new Pager<>(OFFSET, 1, new HashMap<>());
		}
		computerDaoService.updatePager(pager);
		List<ComputerDTO> liste = pager.getListe();
		
		for (ComputerDTO computerDTO : liste) {
			System.out.println(computerDTO.toString());
		}
		
		System.out.println("Page suivante : n | Page précédente : p Quitter : q ");
		String choix = null;

		choix = scanner.next().trim();
		if ("n".equals(choix)) {
			pager.setCurrent(pager.getCurrent() + 1);
			printAllComputers(pager);
		} else if ("p".equals(choix)) {
			pager.setCurrent(pager.getCurrent() - 1);
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
		String choix = null;

		do {
			choix = scanner.next();
			long id = Long.parseLong(choix);
			Computer computer = null;
			try {
				computer = computerDaoService.getById(id);
			} catch (Exception e) {
				// recréer DAOException
				e.printStackTrace();
				String message = "can't find computer with id " + id;
				LOGGER.error(message);
			}
			
			if (computer == null) {
				String message = "Le computer choisi n'existe pas";
				System.err.println(message);
				//throw new DAOException(message);
			}
			System.out.println("----------- " + computer.toString());
		} while (PATTERN_ID.matcher(choix).find());
	}
	
	private void hydrateComputer(Computer computer) {
		
		System.out.println("Entrer le nom du computer :");
		computer.setName(scanner.nextLine());
		
		String introduced = null;

		System.out.println("Entrez la date introduced au format dd-mm-YYYY");
		do {
			introduced = scanner.nextLine();
		} while (!PATTERN_DATE.matcher(introduced).find());
		//computer.setIntroduced(LocalDate.);

		System.out.println("Entrez la date discontinued au format dd-mm-YYYY");
		String continued = null;

		do {
			continued = scanner.next().trim();
		} while (!PATTERN_DATE.matcher(continued).find());
		//computer.setDiscontinued(continued);

		System.out.println("Entrez l'id de la company");
		String companyIdString = null;
		long companyId;

		do {
			companyIdString = scanner.next();
		} while (!PATTERN_ID.matcher(companyIdString).find());
		companyId = Long.parseLong(companyIdString);
		computer.setCompany(companyDaoService.getById(companyId));
	}
	
	public void createComputer() {
		System.out.println("Creation d'un nouvel ordinateur");
		
		Client client = ClientBuilder.newClient();
		
		
		
		Computer computer = new Computer();
		hydrateComputer(computer);
		ComputerDTO computerDTO = ComputerDTOMapper.map(computer);
		
		ComputerDTO persisted = client.target("http://localhost:8080/webapp/rest/computer/add")
				.request()
				.post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON),
						ComputerDTO.class);
		
		
		computerDaoService.create(computer);
		LOGGER.info("Création ordinateur : " + computer);
	}

	public void updateComputer() {
		System.out.println("Mise à jour d'un ordinateur, entrez l'id de l'ordinateur que vous souhaitez modifier : ");
		String computerIdString = null;
		do {
			computerIdString = scanner.next().trim();
		} while (!PATTERN_ID.matcher(computerIdString).find());
		long computerId = Long.parseLong(computerIdString);
		Computer computer = null;
		try {
			computer = computerDaoService.getById(computerId);
		} catch (Exception e) {
			// Exception DAONotFoundException
			e.printStackTrace();
		}
		hydrateComputer(computer);
		computerDaoService.update(computer);
		LOGGER.info("Mise à jour computer : " + computer);
	}

	public void deleteComputer(long id) {
		System.out.println("Supression de l'ordinateur d'id " + id);
		computerDaoService.delete(computerDaoService.getById(id));
		LOGGER.info("Suppression du computer d'id : " + id);
	}

	public void printAllCompanies() {
		
		List<Company> liste = companyDaoService.getAll();
		
		System.out.println("----------- Liste des company -----------");
		
		for (Company company : liste) {
			company.toString();
		}
		
		System.out.println("---------------------------------------------");
		
		printMenu();
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("console-context.xml");
		ConsoleClient consoleClient = (ConsoleClient) context.getBean("console");
		consoleClient.printMenu();
	}
}
