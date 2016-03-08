package com.formation.computerdatabase.ui;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.formation.computerdatabase.model.Company;
import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.persistence.DAOFactory;
import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.persistence.impl.ComputerDaoImpl;

public class ConsoleClient {

	
	private ComputerDaoImpl computerDaoImpl;
	private CompanyDaoImpl companyDaoImpl;
	
	public ConsoleClient(ComputerDaoImpl computerDaoImpl, CompanyDaoImpl companyDaoImpl) {
		super();
		this.computerDaoImpl = computerDaoImpl;
		this.companyDaoImpl = companyDaoImpl;
	}
	
	
	public void printMenu(){
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
		
		// utilisation d'un do .. while car instructions effectuées au moins une fois
		do {
			// lecture de l'entrée
			choix = scanner.next();
		}
		// tant que 
		while (!patternChoix.matcher(choix).find());
		
		// si on arrive ici, le choix est correct
		
		switch (choix) {
		case "a":
			printAllComputers();
			break;
		case "b":
			printAllCompanies();
			break;
		case "c":
			printComputerById();
			break;
		case "d":
			createComputer(null);
			break;
		case "e":
			updateComputer(null);
			break;
		case "f":
			deleteComputer(1);
			break;
		}
	}
	
	public void printAllComputers() {
		System.out.println("Affichage de la liste de tous les ordinateurs : ");
		
		List<Computer> liste = computerDaoImpl.getAll();
		for (Computer computer : liste) {
			System.out.println("------------ " + computer.toString());
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
			Computer computer = computerDaoImpl.getComputerById(id);
			System.out.println("----------- " + computer.toString());
			
		} while (patternId.matcher(choix).find());
	}
	
	public void createComputer(Computer computer) {
		System.out.println("Creation d'un nouvel ordinateur");
		computerDaoImpl.createComputer(computer);
		System.out.println("Création réussie");
	}
	
	public void updateComputer(Computer computer) {
		System.out.println("Mis à jour d'un ordinateur");
		computerDaoImpl.updateComputer(computer);
		System.out.println("Ordinateur mis à jour");
	}
	
	public void deleteComputer(long id){
		System.out.println("Supression de l'ordinateur d'id " + id);
		computerDaoImpl.deleteComputer(id);
		System.out.println("Ordinateur supprimé");
	}
	
	public void printAllCompanies() {
		System.out.println("Affichage de la liste des company");
		List<Company> liste = companyDaoImpl.getAll();
		for (Company company : liste) {
			System.out.println("--------- " + company.toString());
		}
	}

	public static void main(String[] args) {
		DAOFactory daoFactory = new DAOFactory();
		ComputerDaoImpl computerDaoImpl = new ComputerDaoImpl(daoFactory);
		CompanyDaoImpl companyDaoImpl = new CompanyDaoImpl(daoFactory);
		ConsoleClient consoleClient = new ConsoleClient(computerDaoImpl, companyDaoImpl);
		
		consoleClient.printAllComputers();
		consoleClient.printComputerById(1);
		
		Computer computer = new Computer();
		computer.setId(1);
		computer.setCompanyId(1);
		Timestamp introduced = new Timestamp(10000000);
		computer.setDiscontinued(introduced);
		Timestamp discontinued = new Timestamp(20000000);
		computer.setDiscontinued(discontinued);
		computer.setName("MacBook Pro 15.4 inch");
		consoleClient.createComputer(computer);
		//consoleClient.updateComputer(computer);
		//consoleClient.deleteComputer(575);
	}

}
