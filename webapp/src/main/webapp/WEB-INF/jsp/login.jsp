<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="templates/head.jsp"></c:import>
<title><spring:message code="title" /></title>

</head>
<body>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					
					<form class="form-horizontal"  name="loginForm" action="<c:url value='/j_spring_security_check' />" method="post">
						<fieldset>
							<h1><spring:message code="login.title"></spring:message></h1>
							
							<c:if test="${ error }">
							<div class="alert alert-danger">
								<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
								<spring:message code="error.login"></spring:message>
							</div>
							</c:if>
							
							<div class="form-group">
								<label for="username"><spring:message code="user.username"></spring:message></label>
		      					<input type='text' name='username' class="form-control">
		      				</div>
		      				
		      				<div class="form-group">
		      					<label for="password"><spring:message code="user.password"></spring:message></label>
		      					<input type="password" name="password" class="form-control">
		      				</div>
		      				
		      				<div class="actions pull-right">
						        <button type="reset" class="btn btn-default"><spring:message code="button.cancel"></spring:message></button>
						        <input name="submit" type="submit" value='<spring:message code="login.title"></spring:message>' class="btn btn-primary" />
						        <input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}" />
						    </div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>