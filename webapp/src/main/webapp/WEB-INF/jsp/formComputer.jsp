<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"  %>
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
								<sf:hidden id="computerId" path="id" />
								<sf:input path="name" class="form-control" id="name" placeholder="Computer name" />
								<sf:errors path="name" cssClass="alert alert-danger"
									element="div" />
							</div>
							<div class="form-group">
								<sf:label path="introduced"><spring:message code="computer.introduced" /></sf:label>
								<%-- <c:set var="introduced"><mylib:date date="${ computerDTO.introduced }"></mylib:date></c:set> --%>
								<sf:input id="introduced" path="introduced" class="form-control datepicker" placeholder="Introduced date" />
								<sf:errors path="introduced" cssClass="alert alert-danger" element="div"></sf:errors>
							</div>

							<div class="form-group">
								<sf:label path="discontinued"><spring:message code="computer.discontinued" /></sf:label>
								<%-- <c:set var="discontinued"><mylib:date date="${ computerDTO.discontinued }"></mylib:date></c:set> --%>
								<sf:input id="discontinued" path="discontinued" class="form-control datepicker" placeholder="Discontinued date" />
								<sf:errors path="discontinued" cssClass="alert alert-danger"
									element="div" />
							</div>

							<div class="form-group">
								<sf:label path="companyId"><spring:message code="computer.company"/></sf:label>
								<sf:select id="companyId" path="companyId" class="form-control">
									<sf:option value="">Select a company</sf:option>
									<sf:options itemValue="id" itemLabel="name" items="${ companiesDTO }" />
								</sf:select>
								<sf:errors path="companyId" cssClass="alert alert-danger" element="div"></sf:errors>
							</div>

							<div class="actions pull-right">
								<input type="submit" value='<spring:message code="button.validate"></spring:message>' class="btn btn-primary"> or 
								<a href="${ pageContext.request.contextPath }/dashboard" class="btn btn-default"><spring:message code="button.cancel"></spring:message></a>
								<input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}" />
							</div>
						</fieldset>
					</sf:form>
				</div>
			</div>
		</div>
	</section>
</body>

<c:import url="templates/script.jsp"></c:import>
<script src="<c:url value="/resources/js/jquery.validate.min.js"></c:url>"></script>
<c:import url="templates/validation-messages.jsp"></c:import>
<script src='<c:url value="/resources/js/validator.js"></c:url>'></script>
<script src='<c:url value="/resources/js/validator-messages.js"></c:url>'></script>

</html>