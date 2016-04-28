<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="date" required="true" type="java.lang.String"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pattern" value="yyyy-MM-dd"></c:set>

<fmt:parseDate value="${ date }" pattern="${ pattern }" var="parsedDate"/>

<c:if test="${ pageContext.response.locale == 'fr' }">
	<c:set var="pattern" value="dd-MM-yyyy"></c:set>
</c:if>


<fmt:formatDate value="${ parsedDate }" var="formattedDate" pattern="${ pattern }"/>

<c:out value="${ formattedDate }"></c:out>
