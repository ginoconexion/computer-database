package com.formation.computerdatabase.binding.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.formation.computerdatabase.core.model.util.DateFormatter;

public class DateBefore1970Validator implements ConstraintValidator<DateBefore1970, String> {

	@Override
	public void initialize(final DateBefore1970 constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext ctx) {
		
		if (date != null && GenericValidator.isDate(date, DateFormatter.getDatePattern(), true)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatter.getDatePattern());
			return LocalDate.parse(date, formatter).isBefore(DateFormatter.date1970);
		}
		return true;
	}

}
