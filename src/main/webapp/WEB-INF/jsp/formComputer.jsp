<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="title" /></title>
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
					<h1>
						<c:choose>
							<c:when test="${ not empty computerDTO.id }">
								<spring:message code="computer.edit"></spring:message>
							</c:when>
							<c:otherwise>
								<spring:message code="computer.add"></spring:message>
							</c:otherwise>
						</c:choose>
					</h1>
					<sf:form id="form" method="post" action="" modelAttribute="computerDTO">
						<fieldset>
							<sf:errors cssClass="alert alert-danger" element="div" />
							<div class="form-group">
								<sf:label path="name"><spring:message code="computer.name" /></sf:label>
								<sf:hidden path="id" />
								<sf:input path="name" class="form-control" id="name"
									placeholder="Computer name" />
								<sf:errors path="name" cssClass="alert alert-danger"
									element="div" />
							</div>
							<div class="form-group">
								<sf:label path="introduced"><spring:message code="computer.introduced" /></sf:label>
								<sf:input id="introduced" path="introduced"
									class="form-control datepicker" type="date"
									placeholder="Introduced date" />
								<sf:errors path="introduced" cssClass="alert alert-danger" element="div"></sf:errors>
							</div>

							<div class="form-group">
								<sf:label path="discontinued"><spring:message code="computer.discontinued" /></sf:label>
								<sf:input id="discontinued" path="discontinued"
									class="form-control datepicker" type="date"
									placeholder="Discontinued date" />
								<sf:errors path="discontinued" cssClass="alert alert-danger"
									element="div" />
							</div>

							<div class="form-group">
								<sf:label path="companyId"><spring:message code="computer.company"/></sf:label>
								<sf:select path="companyId" class="form-control">
									<sf:option value="">Select a company</sf:option>
									<sf:options itemValue="id" itemLabel="name"
										items="${ companiesDTO }" />
								</sf:select>
								<sf:errors path="companyId" cssClass="alert alert-danger"
									element="div"></sf:errors>
							</div>

							<div class="actions pull-right">
								<input type="submit" class="btn btn-primary"> or <a
									href="dashboard.html" class="btn btn-default">Cancel</a>
							</div>
						</fieldset>
					</sf:form>
				</div>
			</div>
		</div>
	</section>
</body>
<c:import url="templates/script.jsp"></c:import>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.2.8/jquery.form-validator.min.js"></script>
<script
	src="<c:url value="/resources/js/jquery.validate.min.js"></c:url>"></script>
<script src='<c:url value="/resources/js/validator.js"></c:url>'></script>
</html>