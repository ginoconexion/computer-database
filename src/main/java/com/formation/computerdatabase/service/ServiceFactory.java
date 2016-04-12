package com.formation.computerdatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formation.computerdatabase.service.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.service.impl.ComputerDaoServiceImpl;

/**
 * A factory for creating Service objects.
 */
@Component
public class ServiceFactory {
	
	/** The computer dao service impl. */
	@Autowired
	ComputerDaoServiceImpl computerDaoServiceImpl;
	
	/** The company dao service impl. */
	@Autowired
	CompanyDaoServiceImpl companyDaoServiceImpl;

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
