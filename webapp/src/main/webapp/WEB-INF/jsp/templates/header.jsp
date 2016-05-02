<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${ pageContext.request.contextPath }/dashboard"><spring:message code="title"></spring:message></a>
			
		<div class="navbar-right">
			<a href="?lang=fr"><img alt="fr" src="<spring:url value="/resources/flags/fr.png" />"></a>
			<a href="?lang=en"><img alt="eng" src="<spring:url value="/resources/flags/gb.png" />"></a>
			<security:authorize access="isAuthenticated()"> 
			<security:authentication property="principal" var="user" />
			<a href="#"><span aria-hidden="true" class="glyphicon glyphicon-user"></span> ${ user.username }</a>
			<a href='<c:url value="/logout"></c:url>'><spring:message code="button.logout" ></spring:message></a>
			</security:authorize>
			
			<security:authorize access="isAnonymous()">
				<a href="login"><span aria-hidden="true" class="glyphicon glyphicon-user"></span><spring:message code="login.title"></spring:message></a>
			</security:authorize>
		</div>
	</div>
</header>