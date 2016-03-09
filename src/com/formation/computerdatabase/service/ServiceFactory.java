package com.formation.computerdatabase.service;

import com.formation.computerdatabase.service.impl.ComputerDatabaseServiceImpl;

public enum ServiceFactory {
	INSTANCE;
	
	ComputerDatabaseServiceImpl computerDatabaseServiceImpl;
	
	private ServiceFactory() {
		computerDatabaseServiceImpl = ComputerDatabaseServiceImpl.INSTANCE;
	}
	
	public ComputerDatabaseService getService() {
		return computerDatabaseServiceImpl;
	}

}
