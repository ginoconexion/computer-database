package com.formation.computerdatabase.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.formation.computerdatabase.util.DateFormatter;

public class DateBefore1970Validator implements ConstraintValidator<DateBefore1970, String> {

	@Override
	public void initialize(final DateBefore1970 constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext ctx) {
		if (date != null && GenericValidator.isDate(date, DateFormatter.getDatePattern().toString(), true) && LocalDate.parse(date).isBefore(DateFormatter.date1970)) {
			return false;
		}
		return true;
	}

}
