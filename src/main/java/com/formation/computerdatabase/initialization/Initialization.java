package com.formation.computerdatabase.initialization;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.formation.computerdatabase.model.Computer;
import com.formation.computerdatabase.service.ComputerDaoService;
import com.formation.computerdatabase.service.ServiceFactory;

public class Initialization implements ServletContextListener {

	private ServiceFactory service;
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		
		ServletContext servletContext = event.getServletContext();
		/*
		this.service = new ServiceFactory();
		this.service = ServiceFactory.INSTANCE;
		*/
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	    ServiceFactory service = (ServiceFactory) context.getBean("serviceFactory");
		servletContext.setAttribute("service", service);
	}
}
