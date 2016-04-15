<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<c:import url="templates/head.jsp"></c:import>
</head>
<body>
    <c:import url="templates/header.jsp"></c:import>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    
                    <sf:form method="post" action="#" modelAttribute="computerDTO">
                    	 <fieldset>
                            <div class="form-group">
                            	<sf:label path="name">Name</sf:label>
                            	<sf:input path="name" class="form-control" id="name" placeholder="Computer name" />
                            </div>
                             <div class="form-group">
                             	<sf:label path="introduced">Introduced</sf:label>
                             	<sf:input id="introduced" path="introduced" class="form-control datepicker" data-validation-format="yyyy-mm-dd" type="date" placeholder="Introduced date" />
                            </div>
                            
                            <div class="form-group">
                            	<sf:label path="discontinued"></sf:label>
                            	<sf:input id="discontinued" path="discontinued" class="form-control datepicker" data-validation-format="yyyy-mm-dd" type="date" placeholder="Discontinued date" />
                            </div>
                            
							<div class="form-group">
								<sf:label path="companyId">Company</sf:label>
								<sf:select path="companyId"  class="form-control">
									<sf:option value="">Select a company</sf:option>
									<sf:options itemValue="id" itemLabel="name" items="${ companiesDTO }" />
								</sf:select>
                            </div>
                            
                             <div class="actions pull-right">
	                            <input type="submit" class="btn btn-primary">
	                            or
	                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
	                        </div>
                            
                        </fieldset>
                    	
                    	<!-- 
                    	<table>
			                <tr>
			                    <td><sf:label path="name">Name</sf:label></td>
			                    <td><sf:input path="name"/></td>
			                </tr>
			                <tr>
			                    <td><input type="submit" value="Submit"/></td>
			                </tr>
			            </table>
			             -->
                    </sf:form>
                </div>
            </div>
        </div>
    </section>
</body>
<c:import url="templates/script.jsp"></c:import>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.2.8/jquery.form-validator.min.js"></script>
<script>
$.validate({ });

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
</script>
</html>