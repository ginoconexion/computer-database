<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand"
			href="${ pageContext.request.contextPath }/dashboard"><spring:message code="title"></spring:message></a>

		<div class="navbar-right">
			<a href="?lang=fr"><img alt="fr" src="<spring:url value="/resources/flags/fr.png" />"></a>
			<a href="?lang=en"><img alt="eng" src="<spring:url value="/resources/flags/gb.png" />"></a>
		</div>
	</div>
</header>