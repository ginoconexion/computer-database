package com.formation.computerdatabase.service;

import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

/**
 * A factory for creating Service objects.
 */
public class ServiceFactory {
	
	/** The computer dao service impl. */
	ComputerDaoServiceImpl computerDaoServiceImpl;
	
	/** The company dao service impl. */
	CompanyDaoServiceImpl companyDaoServiceImpl;
	
	/**
	 * Instantiates a new service factory.
	 */
	private ServiceFactory(ComputerDaoServiceImpl computerDaoServiceImpl, CompanyDaoServiceImpl companyDaoServiceImpl) {
		this.computerDaoServiceImpl = computerDaoServiceImpl;
		this.companyDaoServiceImpl =  companyDaoServiceImpl;
	}

	/**
	 * Gets the computer dao service impl.
	 *
	 * @return the computer dao service impl
	 */
	public ComputerDaoServiceImpl getComputerDaoServiceImpl() {
		return computerDaoServiceImpl;
	}

	/**
	 * Gets the company dao service impl.
	 *
	 * @return the company dao service impl
	 */
	public CompanyDaoServiceImpl getCompanyDaoServiceImpl() {
		return companyDaoServiceImpl;
	}
}
