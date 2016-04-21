package com.formation.computerdatabase.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.formation.computerdatabase.model.dto.ComputerDTO;
import com.formation.computerdatabase.util.DateFormatter;
import com.formation.computerdatabase.util.Lang;

public class DateAnteriorityValidator implements ConstraintValidator<DateAnteriority, ComputerDTO> {

	@Override
	public void initialize(final DateAnteriority constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(ComputerDTO cDTO, ConstraintValidatorContext ctx) {
		
		if (cDTO.getIntroduced() != null && GenericValidator.isDate(cDTO.getIntroduced(), DateFormatter.getDatePattern(), true) && cDTO.getDiscontinued() != null && GenericValidator.isDate(cDTO.getDiscontinued(), DateFormatter.getDatePattern(), true)) {
			LocalDate ldIntroduced = LocalDate.parse(cDTO.getIntroduced());
			LocalDate ldDiscontinued = LocalDate.parse(cDTO.getDiscontinued());
			return (ldIntroduced.isBefore(ldDiscontinued));
		}
		
		return true;
	}

}
