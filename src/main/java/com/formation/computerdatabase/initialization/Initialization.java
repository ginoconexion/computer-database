package com.formation.computerdatabase.initialization;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.formation.computerdatabase.service.ServiceFactory;

public class Initialization implements ServletContextListener {

	private ServiceFactory service;
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		this.service = ServiceFactory.INSTANCE;
		servletContext.setAttribute("service", service);
		System.out.println("ok");
	}
}
