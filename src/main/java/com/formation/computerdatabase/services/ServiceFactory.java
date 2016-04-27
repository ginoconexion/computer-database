package com.formation.computerdatabase.services;

import org.springframework.stereotype.Service;

import com.formation.computerdatabase.services.impl.CompanyDaoServiceImpl;
import com.formation.computerdatabase.services.impl.ComputerDaoServiceImpl;

/**
 * A factory for creating Service objects.
 */
@Service
public class ServiceFactory {
	
	/** The computer dao service impl. */
	//@Autowired
	ComputerDaoServiceImpl computerDaoServiceImpl;
	
	/** The company dao service impl. */
	//@Autowired
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
