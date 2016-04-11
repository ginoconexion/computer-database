package com.formation.computerdatabase.ui;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.service.ComputerDaoService;

public class Main {

	public static void main(String[] args) {
		
	      AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	      ComputerDaoService obj = (ComputerDaoService) context.getBean("computerService");
	      List<Computer> list = obj.getListByCompany(1);
	      
	      for (Computer computer : list) {
	    	 System.out.println(computer);
	      }
	      
	      context.registerShutdownHook();
	}

}
