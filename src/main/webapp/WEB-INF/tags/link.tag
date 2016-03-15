<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="pageActuelle" required="true" type="java.lang.Integer" %>
<%@ attribute name="nbPages" required="true" type="java.lang.Integer" %>
<%@ attribute name="type" required="true" type="java.lang.String" %>
<%@ attribute name="url" required="true" type="java.lang.String" %>

<c:choose>
	<c:when test="${ type == 'prev' }">
		<c:set var="pagePrecedente" value="${ pageActuelle - 1 }"></c:set>
		<c:if test="${ pagePrecedente > 0 }">
			<li>
            	<a href="${ url }&page=${ pagePrecedente }" aria-label="Previous">
                	<span aria-hidden="true">&laquo;</span>
               </a>
           </li>
		</c:if>
	</c:when>
	<c:when test="${ type == 'next' }">
		<c:set var="pageSuivante" value="${ pageActuelle + 1 }"></c:set>
		<c:if test="${ pageSuivante < nbPages }">
			<li>
	            <a href="${ url }&page=${ pageSuivante }" aria-label="Next">
	                <span aria-hidden="true">&raquo;</span>
	            </a>
	        </li>
	     </c:if>
	</c:when>
</c:choose>
