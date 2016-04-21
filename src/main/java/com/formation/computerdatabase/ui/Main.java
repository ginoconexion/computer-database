package com.formation.computerdatabase.ui;

import org.hibernate.SessionFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("webapp-context.xml");
	    SessionFactory service = (SessionFactory) context.getBean("sessionFactory");
	}

}
