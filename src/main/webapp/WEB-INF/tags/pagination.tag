<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="pageActuelle" required="true" type="java.lang.Integer"%>
<%@ attribute name="nbPages" required="true" type="java.lang.Integer"%>
<%@ attribute name="url" required="true" type="java.lang.String"%>


<c:if test="${ pager.pageActuelle > 2 }">
	<li><a href="${ url }&page=1">1</a></li>
</c:if>

<c:if test="${ pager.pageActuelle > 3 }">
<li><a>...</a></li>
</c:if>

<c:forEach begin="1" end="${ nbPages }" var="p">
	<c:if test="${ p > pageActuelle - 2 && p < pageActuelle + 2 }">
		<li <c:if test="${ p == pageActuelle }">class="active"</c:if> ><a href="${url}&page=${ p }">${ p }</a></li>
	</c:if>
</c:forEach>


<c:if test="${ pageActuelle < nbPages - 2 }">
	<li><a>...</a></li>
</c:if>

<c:if test="${ pageActuelle < nbPages - 1 }">
	<li><a href="${ url }&page=${ nbPages }">${ nbPages }</a></li>
</c:if>