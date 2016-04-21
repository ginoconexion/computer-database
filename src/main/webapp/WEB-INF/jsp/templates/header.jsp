<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"  %>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand"
			href="${ pageContext.request.contextPath }/dashboard"><spring:message code="title"></spring:message></a>
		<div class="navbar-right">
			<a href="<mylib:link pager="${ pager }" parameterValue="fr" parameter="lang"></mylib:link>"><img alt="fr" src="<spring:url value="/resources/flags/fr.png" />"></a>
			<a href="<mylib:link pager="${ pager }" parameterValue="en" parameter="lang"></mylib:link>"><img alt="eng" src="<spring:url value="/resources/flags/gb.png" />"></a>
			
			<!--<mylib:pagination pager="${ pager }" url="dashboard"></mylib:pagination> -->
		</div>
	</div>
</header>