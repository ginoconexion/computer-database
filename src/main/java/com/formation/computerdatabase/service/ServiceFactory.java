package com.formation.computerdatabase.service;

import com.formation.computerdatabase.persistence.impl.CompanyDaoImpl;
import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

public enum ServiceFactory {
	
	INSTANCE;
	ComputerDaoServiceImpl computerDaoServiceImpl;
	CompanyDaoServiceImpl companyDaoServiceImpl;
	
	private ServiceFactory() {
		computerDaoServiceImpl = ComputerDaoServiceImpl.INSTANCE;
		companyDaoServiceImpl =  CompanyDaoServiceImpl.INSTANCE;
	}

	public ComputerDaoServiceImpl getComputerDaoServiceImpl() {
		return computerDaoServiceImpl;
	}

	public CompanyDaoServiceImpl getCompanyDaoServiceImpl() {
		return companyDaoServiceImpl;
	}
}
