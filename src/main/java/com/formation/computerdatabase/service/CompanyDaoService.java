package com.formation.computerdatabase.service;

import java.util.List;

import com.formation.computerdatabase.model.dto.CompanyDTO;
import com.formation.computerdatabase.persistence.Dao;

public interface CompanyDaoService extends Dao<CompanyDTO> {
		List<CompanyDTO> getAll();
}
