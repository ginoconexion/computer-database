<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pager" required="true" type="com.formation.computerdatabase.service.util.Pager"%>
<%@ attribute name="parameter" required="true" type="java.lang.String"%>
<%@ attribute name="parameterValue" required="true" type="java.lang.String"%>
<%@ attribute name="type" type="java.lang.String"%>

<c:set var="filter" value="${ pager.filter }"></c:set>

<c:choose>
	<%-- s'il s'agit d'un lien permettant d'ordonner --%>
	<c:when test="${ type == 'orderBy' }">
		<c:choose>
			<c:when test="${ not empty filter[parameter] }">
				<c:choose>
					<c:when test="${ filter[parameter] == 'asc' }">
						<c:set value="desc" var="orderBy"></c:set>
					</c:when>
					<c:otherwise>
						<c:set value="asc" var="orderBy"></c:set>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:set value="asc" var="orderBy"></c:set>
			</c:otherwise>
		</c:choose>
		<c:out value="?${ parameter }=${ orderBy }" ></c:out>
	</c:when>
	
	<%-- s'il s'agit d'un lien de pagination --%>
	<c:otherwise>
		<c:out value="?${ parameter }=${ parameterValue }"></c:out>
	</c:otherwise>
</c:choose>

<%-- on écrit le reste des paramètres dans l'url --%>
<c:forEach items="${filter}" var="entry">
	<c:if test="${ entry.key ne parameter }">
		<c:out value="&${ entry.key }=${ entry.value }"></c:out>
	</c:if>
</c:forEach>

<%-- on écrit le paramètre d'offset --%>
<c:out value="&offset=${ pager.offset }"></c:out>



