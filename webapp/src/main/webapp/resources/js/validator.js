/**
 * 
 */

$(document).ready(function() {
	
	var regExEn = /^\d{4}-\d{2}-\d{2}$/;
    var regExFr = /^\d{2}-\d{2}-\d{4}$/;
	
    jQuery.validator.addMethod("greaterThan", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if ($(param).val() == "") return true;
        var value2 = $(param).val();
        if (value.match(regExFr) != null) {
        	var values = value.split("-");
        	value = values[2] + "-" + values[1] + "-" + values[0];
        	var values2 = value2.split("-");
        	value2 = values2[2] + "-" + values2[1] + "-" + values2[0];
        }
        return new Date(value) >= new Date(value2);
    });

    jQuery.validator.addMethod("notBefore70", function(value, element, param) {
        if (value == null) return true;
        if (value == "") return true;
        if (value.match(regExFr) != null) {
        	var values = value.split("-");
            value = values[2] + "-" + values[1] + "-" + values[0];
        }
        return new Date(value) > new Date("1970-01-01");
    });

    jQuery.validator.addMethod("isDate", function(value, element, param) {
        console.log(value);
    	if (value == null) return true;
        if (value == "") return true;
        
        console.log(value.match(regExFr));
        return value.match(regExEn) != null || value.match(regExFr) != null;
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