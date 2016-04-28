package com.formation.computerdatabase.binding.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

import com.formation.computerdatabase.binding.formatter.DateFormatter;


public class DateValidator implements ConstraintValidator<Date, String> {

	@Override
	public void initialize(Date arg0) {
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext ctx) {
		// si la date n'est pas vide on test si elle correspond au pattern
		
		System.out.println(DateFormatter.getDatePattern());
		System.out.println(GenericValidator.isDate(date, "yyyy-MM-dd", false));
		if (!date.equals("") && !GenericValidator.isDate(date, DateFormatter.getDatePattern(), true)) {
			return false;
		}
		
		return true;
	}
}
