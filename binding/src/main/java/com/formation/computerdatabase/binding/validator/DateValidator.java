package com.formation.computerdatabase.binding.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.formation.computerdatabase.core.model.util.DateFormatter;


public class DateValidator implements ConstraintValidator<Date, String> {

	@Override
	public void initialize(Date arg0) {
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext ctx) {
		// si la date n'est pas vide on test si elle correspond au pattern
		
		if (!date.equals("") && !GenericValidator.isDate(date, DateFormatter.getDatePattern(), true)) {
			return false;
		}
		
		return true;
	}
}
