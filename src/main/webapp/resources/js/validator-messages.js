/**
 * 
 */

jQuery.extend(jQuery.validator.messages, {
	  required: translation['required'],
	  remote: "votre message",
	  email: "votre message",
	  url: "votre message",
	  date: "votre message",
	  dateISO: "votre message",
	  number: "votre message",
	  digits: "votre message",
	  creditcard: "votre message",
	  equalTo: "votre message",
	  accept: "votre message",
	  maxlength: jQuery.validator.format(translation['max']),
	  minlength: jQuery.validator.format(translation['min']),
	  rangelength: jQuery.validator.format("votre message  entre {0} et {1} caractéres."),
	  range: jQuery.validator.format("votre message  entre {0} et {1}."),
	  max: jQuery.validator.format(translation['max']),
	  min: jQuery.validator.format(translation['min'])
});