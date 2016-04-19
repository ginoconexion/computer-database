package com.formation.computerdatabase.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.formation.computerdatabase.model.dto.ComputerDTO;

public class DateAnteriorityValidator implements ConstraintValidator<DateAnteriority, ComputerDTO> {

	@Override
	public void initialize(final DateAnteriority constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(ComputerDTO cDTO, ConstraintValidatorContext ctx) {
		
		if (cDTO.getIntroduced() != null && GenericValidator.isDate(cDTO.getIntroduced(), "yyyy-MM-dd", true) && cDTO.getIntroduced() != null && GenericValidator.isDate(cDTO.getDiscontinued(), "yyyy-MM-dd", true)) {
			LocalDate ldIntroduced = LocalDate.parse(cDTO.getIntroduced());
			LocalDate ldDiscontinued = LocalDate.parse(cDTO.getDiscontinued());
			return (ldIntroduced.isBefore(ldDiscontinued));
		}
		
		return true;
	}

}
