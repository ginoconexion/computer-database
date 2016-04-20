/**
 * 
 */

$(document).ready(function() {
    jQuery.validator.addMethod("greaterThan", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if ($(param).val() == "") return true;
        return new Date(value) >= new Date($(param).val());
    });

    jQuery.validator.addMethod("notBefore70", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        return new Date(value) > new Date("1970-01-01");
    });

    jQuery.validator.addMethod("isDate", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if (value == "0000-00-00") return false;

        var regEx = /^\d{4}-\d{2}-\d{2}$/;
        return value.match(regEx) != null;
    });
    
    jQuery.validator.addMethod("allSpace", function(value, element, param) {
    	return value.trim().length != 0;
    });

    $("#form").validate({
        rules: {
            name: {
                required : true,
                minlength: 2,
                maxlength: 30,
                allSpace: true
            },
            introduced: {
                required: false,
                isDate: true,
                notBefore70: true
            },
            discontinued: {
                required: false,
                isDate: true,
                greaterThan: "#introduced",
                notBefore70: true
            },
            companyId: {
            	required: true,
            }
        },
        messages: {
            name: {
            	required: "Can't be empty",
            	allSpace: "Can't be all space"
            },
            introduced: {
                isDate: translation['isDate'],
                notBefore70: translation['notBefore70'],
            },
            discontinued: {
                isDate: translation['isDate'],
                greaterThan: translation['greaterThanDiscontinued'],
                notBefore70: translation['notBefore70'],
            }
        },
        errorPlacement: function(label, element) {
            label.addClass('alert alert-danger');
            label.insertAfter(element);
        },
        wrapper: 'div'
    });
});


/*
$("#introduced").datepicker({
	dateFormat: "yy-mm-dd",
	maxDate: 0,
});
$("#discontinued").datepicker({
	dateFormat: "yy-mm-dd",
	maxDate: 0,
});
*/