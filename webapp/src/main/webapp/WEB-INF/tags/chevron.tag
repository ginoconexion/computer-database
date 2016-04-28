<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="filter" required="true" type="java.util.HashMap" %>
<%@ attribute name="parameter" required="true" type="java.lang.String"%>

<c:choose>
	<c:when test="${ not empty filter[parameter] }">
		<c:choose>
			<c:when test="${ filter[parameter] == 'asc' }">
				<span aria-hidden="true" class="glyphicon glyphicon-chevron-up"></span>
			</c:when>
			<c:otherwise>
				<span aria-hidden="true" class="glyphicon glyphicon-chevron-down"></span>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<span aria-hidden="true" class="glyphicon glyphicon-chevron-down"></span>
	</c:otherwise>
</c:choose>