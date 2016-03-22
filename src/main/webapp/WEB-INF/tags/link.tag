<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ attribute name="pager" required="true" type="com.formation.computerdatabase.pagination.Pager"%>
<%@ attribute name="parameter" required="true" type="java.lang.String"%>
<%@ attribute name="parameterValue" required="true" type="java.lang.String"%>
<%@ attribute name="type" type="java.lang.String"%>

<c:choose>
	<c:when test="${ type == 'orderBy' }">
		<c:choose>
			<c:when test="${ not empty pager.filter[parameter] }">
				<c:choose>
					<c:when test="${ pager.filter[parameter] == 'asc' }">
		<%-- 				<c:out value="desc"></c:out> --%>
						<c:set value="desc" var="orderBy"></c:set>
					</c:when>
					<c:otherwise>
						<%-- <c:out value="asc"></c:out> --%>
						<c:set value="asc" var="orderBy"></c:set>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<%-- <c:out value="asc"></c:out> --%>
				<c:set value="asc" var="orderBy"></c:set>
			</c:otherwise>
		</c:choose>
		<c:out value="?${ parameter }=${ orderBy }" ></c:out>
	</c:when>
	<c:otherwise>
		<c:out value="?${ parameter }=${ parameterValue }"></c:out>
	</c:otherwise>
</c:choose>

<c:forEach items="${pager.filter}" var="entry">
	<c:if test="${ entry.key ne parameter }">
		<c:out value="&${ entry.key }=${ entry.value }"></c:out>
	</c:if>
</c:forEach>
