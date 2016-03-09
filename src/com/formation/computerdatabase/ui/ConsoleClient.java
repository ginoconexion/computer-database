package com.formation.computerdatabase.ui;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.pagination.Paginator;
import com.formation.computerdatabase.persistence.CompanyDao;
import com.formation.computerdatabase.persistence.DAOFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;
import com.formation.computerdatabase.service.ServiceFactory;
import com.formation.computerdatabase.service.impl.ComputerDatabaseServiceImpl;

public class ConsoleClient {

	private ComputerDatabaseServiceImpl computerDatabaseServiceImpl;

	public ConsoleClient(ComputerDatabaseServiceImpl computerDatabaseServiceImpl) {
		super();
		this.computerDatabaseServiceImpl = computerDatabaseServiceImpl;
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

		// instanciation d'un pattern avec la regexp du choix
		Pattern patternChoix = Pattern.compile("^[a|b|c|d|e|f]$");
		String choix = null;

		// utilisation d'un do .. while car instructions effectuées au moins une
		// fois
		do {
			// lecture de l'entrée
			choix = scanner.next();
		}
		// tant que
		while (!patternChoix.matcher(choix).find());

		// si on arrive ici, le choix est correct

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
			updateComputer(null);
			break;
		case "f":
			deleteComputer(1);
			break;
		}
	}

	public void printAllComputers(Paginator paginator) {

		if (paginator == null) {
			// List<Computer> liste = computerDaoImpl.getAll();
			int nbEntries = computerDatabaseServiceImpl.getNbComputers();
			int nbParPage = 10;
			paginator = new Paginator(nbEntries, nbParPage);

		}

		List<Computer> liste = computerDatabaseServiceImpl.getComputersFromTo(
				(paginator.getPageActuelle() - 1) * paginator.getNbParPage(), paginator.getNbParPage());

		System.out.println(paginator.getPageActuelle() * paginator.getNbParPage());
		System.out.println("Affichage de " + paginator.getNbParPage() + " computers sur " + paginator.getNbEntries()
				+ " (page : " + paginator.getPageActuelle() + ")");
		for (Computer computer : liste) {
			System.out.println("------------ " + computer.toString());
		}
		System.out.println("Page suivante : n | Page précédente : p Quitter : q ");
		Scanner scanner = new Scanner(System.in);
		String choix = null;

		choix = scanner.next().trim();
		if (choix.equals("n")) {
			paginator.next();
			printAllComputers(paginator);
		} else if (choix.equals("p")) {
			paginator.prev();
			printAllComputers(paginator);
		} else if (choix.equals("q")) {
			printMenu();
		}
		else {
			printAllComputers(paginator);
		}
	}

	public void printComputerById() {
		System.out.println("Affichage d'un computer : ");
		System.out.println("Entrer l'id du computer :");
		Scanner scanner = new Scanner(System.in);
		String choix = null;
		Pattern patternId = Pattern.compile("^[0-9]+$");

		do {
			choix = scanner.next();
			long id = Long.parseLong(choix);
			Computer computer = computerDatabaseServiceImpl.getComputerById(id);
			try {
				System.out.println("----------- " + computer.toString());
			} catch (NullPointerException e) {
				System.out.println("Le computer choisi n'existe pas");
			}
		} while (patternId.matcher(choix).find());
	}

	public void createComputer() {
		System.out.println("Creation d'un nouvel ordinateur");
		Pattern pattern = null;
		Computer computer = new Computer();

		System.out.println("Entrer le nom du computer :");
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		computer.setName(scanner.nextLine());

		pattern = Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{4}$");
		String introduced = null;

		System.out.println("Entrez la date introduced au format dd-mm-YYYY");
		do {
			introduced = scanner.nextLine();
		} while (!pattern.matcher(introduced).find());
		// TODO : utiliser la méthode utilisant un String
		computer.setIntroduced(introduced);

		System.out.println("Entrez la date discontinued au format dd-mm-YYYY");
		String continued = null;

		do {
			continued = scanner.next().trim();
		} while (!pattern.matcher(continued).find());
		computer.setDiscontinued(continued);

		System.out.println("Entrez l'id de la company");
		pattern = Pattern.compile("^[0-9]+$");
		String companyIdString = null;
		long companyId;

		do {
			companyIdString = scanner.next();
			companyId = Long.parseLong(companyIdString);
		} while (!pattern.matcher(companyIdString).find());
		computer.setCompanyId(companyId);

		System.out.println(computer);
		computerDatabaseServiceImpl.createComputer(computer);
		System.out.println("Création réussie");
	}

	public void updateComputer(Computer computer) {
		System.out.println("Mis à jour d'un ordinateur");
		computerDatabaseServiceImpl.updateComputer(computer);
		System.out.println("Ordinateur mis à jour");
	}

	public void deleteComputer(long id) {
		System.out.println("Supression de l'ordinateur d'id " + id);
		computerDatabaseServiceImpl.deleteComputer(id);
		System.out.println("Ordinateur supprimé");
	}

	public void printAllCompanies(Paginator paginator) {

		if (paginator == null) {
			int nbEntries = computerDatabaseServiceImpl.getNbCompanies();
			int nbParPage = 10;
			paginator = new Paginator(nbEntries, nbParPage);

		}

		List<Company> liste = computerDatabaseServiceImpl.getCompaniesFromTo(
				(paginator.getPageActuelle() - 1) * paginator.getNbParPage(), paginator.getNbParPage());

		System.out.println(paginator.getPageActuelle() * paginator.getNbParPage());
		System.out.println("Affichage de " + paginator.getNbParPage() + " computers sur " + paginator.getNbEntries()
				+ " (page : " + paginator.getPageActuelle() + ")");
		for (Company company : liste) {
			System.out.println("------------ " + company.toString());
		}
		System.out.println("Page suivante : n | Page précédente : p Quitter : q ");
		Scanner scanner = new Scanner(System.in);
		String choix = null;

		choix = scanner.next().trim();
		if (choix.equals("n")) {
			paginator.next();
			printAllCompanies(paginator);
		} else if (choix.equals("p")) {
			paginator.prev();
			printAllCompanies(paginator);
		} else if (choix.equals("q")) {
			printMenu();
		}
		else {
			printAllCompanies(paginator);
		}
	}

	public static void main(String[] args) {
		ConsoleClient consoleClient = new ConsoleClient((ComputerDatabaseServiceImpl) ServiceFactory.INSTANCE.getService());
		consoleClient.printMenu();
	}

}
