package com.formation.computerdatabase.binding.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

public class DateAnteriorityValidator implements ConstraintValidator<DateAnteriority, ComputerDTO> {

	@Override
	public void initialize(final DateAnteriority constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(ComputerDTO cDTO, ConstraintValidatorContext ctx) {
		
		if (cDTO.getIntroduced() != null && GenericValidator.isDate(cDTO.getIntroduced(), DateFormatter.getDatePattern(), true) && cDTO.getDiscontinued() != null && GenericValidator.isDate(cDTO.getDiscontinued(), DateFormatter.getDatePattern(), true)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatter.getDatePattern());
			System.out.println(cDTO.getIntroduced());
			System.out.println(DateFormatter.getDatePattern());
			LocalDate ldIntroduced = LocalDate.parse(cDTO.getIntroduced(), formatter);
			LocalDate ldDiscontinued = LocalDate.parse(cDTO.getDiscontinued(), formatter);
			return (ldIntroduced.isBefore(ldDiscontinued));
		}
		
		return true;
	}

}
