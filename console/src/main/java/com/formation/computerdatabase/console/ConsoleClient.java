package com.formation.computerdatabase.console;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.formation.computerdatabase.binding.dto.CompanyDTO;
import com.formation.computerdatabase.binding.dto.ComputerDTO;
import com.formation.computerdatabase.service.util.Pager;

public class ConsoleClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleClient.class);
	private final static int OFFSET = 10;
	private final static Pattern PATTERN_CHOIX = Pattern.compile("^[a-g]{1}$");
	private final static Pattern PATTERN_DATE = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	private final static Pattern PATTERN_ID = Pattern.compile("[0-9]+");
	private static Scanner scanner = new Scanner(System.in);
	
	private static final Client CLIENT = ClientBuilder.newBuilder().build();
	private static final String URL = "http://localhost:8080/rest/";
	
	public void deleteCompany() {
		System.out.println("Supprimer une company : ");
		System.out.println("Entrer l'id de la company :");
		
		String idString = null;
		do {
			idString = scanner.next();
		} while (!PATTERN_ID.matcher(idString).find());
		long id = Long.parseLong(idString);
		
		WebTarget target = CLIENT.target(URL + "company/delete/" + id);
		Response response = target.request().get();
		System.out.println(response);
		response.close();
	}
	
	public void deleteComputer() {
		System.out.println("Suppression d'un ordinateur, entrez l'id de l'ordinateur que vous souhaitez modifier : ");
		String computerId = null;
		do {
			computerId = scanner.next().trim();
		} while (!PATTERN_ID.matcher(computerId).find());
		long id = Long.parseLong(computerId);
		
		WebTarget target = CLIENT.target(URL + "computer/delete/" + id);
		Response response = target.request().get();
		response.close();
		System.out.println(response);
		LOGGER.info("Suppression du computer d'id : " + id);
		printMenu();
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
		System.out.println("g : supprimer une company via son id");
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
			deleteComputer();
			break;
		case "g":
			deleteCompany();
			break;
		}
	}

	public void printAllComputers(Pager<ComputerDTO> pager) {
		
		System.out.println("Affichage de tous les ordinateurs");
		
		if (pager == null) {
			pager = new Pager<>(OFFSET, 1, new HashMap<>());
		}
		
		WebTarget target = CLIENT.target(URL + "computer/list?page=" + pager.getCurrent());
		Response response = target.request().get();
		List<ComputerDTO> computersDTO = response.readEntity(new GenericType<List<ComputerDTO>>() {});
		System.out.println(response);
		response.close();
		    
		for (ComputerDTO computerDTO : computersDTO) {
			System.out.println(computerDTO);
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
			ComputerDTO computerDTO = null;
			try {
				WebTarget target = CLIENT.target(URL + "computer/" + id);
				Response response = target.request().get();
				computerDTO = response.readEntity(ComputerDTO.class);
				System.out.println(response);
				response.close();
				
			} catch (Exception e) {
				// recréer DAOException
				e.printStackTrace();
				String message = "can't find computer with id " + id;
				LOGGER.error(message);
			}
			
			if (computerDTO == null) {
				String message = "Le computer choisi n'existe pas";
				System.err.println(message);
			}
			System.out.println("----------- " + computerDTO);
		} while (PATTERN_ID.matcher(choix).find());
	}
	
	private void hydrateComputer(ComputerDTO computerDTO) {
		
		System.out.println("Entrer le nom du computer :");
		String name = "";
		do {
			name = scanner.nextLine();
		} while (!"".equals(name));
		computerDTO.setName(name);
		
		computerDTO.setName(scanner.nextLine());
		
		String introduced = null;
		System.out.println("Entrez la date introduced au format YYYY-mm-dd");
		do {
			introduced = scanner.nextLine();
		} while (!PATTERN_DATE.matcher(introduced).find());
		computerDTO.setIntroduced(introduced);

		System.out.println("Entrez la date discontinued au format YYYY-mm-dd");
		String discontinued = null;
		do {
			discontinued = scanner.next().trim();
		} while (!PATTERN_DATE.matcher(discontinued).find());
		computerDTO.setDiscontinued(discontinued);

		System.out.println("Entrez l'id de la company");
		String companyId = null;
		do {
			companyId = scanner.next();
		} while (!PATTERN_ID.matcher(companyId).find());
		computerDTO.setCompanyId(companyId);
	}
	
	public void createComputer() {
		System.out.println("Creation d'un nouvel ordinateur");
		
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId("");
		hydrateComputer(computerDTO);
		
		System.out.println(computerDTO);
		
		WebTarget target = CLIENT.target(URL + "computer/add");
		Response response = target.request().post(Entity.entity(computerDTO, "application/json"));
		ComputerDTO comp = response.readEntity(ComputerDTO.class);
		response.close();
		
		printMenu();
	}

	public void updateComputer() {
		
		System.out.println("Mise à jour d'un ordinateur, entrez l'id de l'ordinateur que vous souhaitez modifier : ");
		String computerId = null;
		do {
			computerId = scanner.next().trim();
		} while (!PATTERN_ID.matcher(computerId).find());
		long id = Long.parseLong(computerId);
		
		WebTarget target = CLIENT.target(URL + "computer/" + id);
		Response response = target.request().get();
		ComputerDTO computerDTO = response.readEntity(ComputerDTO.class);
		System.out.println(computerDTO);
		System.out.println(response);
			
		hydrateComputer(computerDTO);
		
		target = CLIENT.target(URL + "computer/edit");
		response = target.request().post(Entity.entity(computerDTO, "application/json"));
		System.out.println(response);
		
		ComputerDTO comp = response.readEntity(ComputerDTO.class);
		response.close();
		
		LOGGER.info("Mise à jour computer : " + computerDTO);
	}

	

	public void printAllCompanies() {
		
		WebTarget target = CLIENT.target(URL + "company/list");
		Response response = target.request().get();
		List<CompanyDTO> companiesDTO = response.readEntity(new GenericType<List<CompanyDTO>>() {});
		response.close();
		
		System.out.println("----------- Liste des company -----------");
		for (CompanyDTO company : companiesDTO) {
			System.out.println(company);
		}
		System.out.println("---------------------------------------------");
		printMenu();
	}
	
	public static void main(String[] args) {
		ConsoleClient console = new ConsoleClient();
		console.printMenu();
	}
	
	@ExceptionHandler(MessageBodyProviderNotFoundException.class)
	public void messageBodyNotFoundException() {
		
	}
}
