<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="filter" required="true" type="java.util.HashMap" %>
<%@ attribute name="parameter" required="true" type="java.lang.String"%>

<c:choose>
	<c:when test="${ not empty filter[parameter] }">
		<c:choose>
			<c:when test="${ filter[parameter] == 'asc' }">
				<c:out value="desc"></c:out>
			</c:when>
			<c:otherwise>
				<c:out value="asc"></c:out>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:out value="asc"></c:out>
	</c:otherwise>
</c:choose>