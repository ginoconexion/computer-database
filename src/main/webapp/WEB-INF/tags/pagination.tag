<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"  %>

<%@ attribute name="pager" required="true" type="com.formation.computerdatabase.pagination.Pager"%>
<%@ attribute name="url" required="true" type="java.lang.String"%>

<c:set var="pageActuelle" value="${ pager.pageActuelle }"></c:set>
<c:set var="pagePrecedente" value="${ pageActuelle - 1 }"></c:set>
<c:set var="nbPages" value="${ pager.nbPages }"></c:set>

<c:if test="${ pagePrecedente > 0 }">
	<li>
     	<a href="<mylib:link pager="${ pager }" parameterValue="${ pagePrecedente }" parameter="page"></mylib:link>" aria-label="Previous">
         	<span aria-hidden="true">&laquo;</span>
        </a>
    </li>
</c:if>


<c:if test="${ pageActuelle > 2 }">
	<li><a href="<mylib:link pager="${ pager }" parameterValue="1" parameter="page"></mylib:link>">1</a></li>
</c:if>

<c:if test="${ pageActuelle > 3 }">
<li><a>...</a></li>
</c:if>

<c:forEach begin="1" end="${ nbPages }" var="p">
	<c:if test="${ p > pageActuelle - 2 && p < pageActuelle + 2 }">
		<li <c:if test="${ p == pageActuelle }">class="active"</c:if> ><a href="<mylib:link pager="${ pager }" parameterValue="${ p }" parameter="page"></mylib:link>">${ p }</a></li>
	</c:if>
</c:forEach>


<c:if test="${ pageActuelle < nbPages - 2 }">
	<li><a>...</a></li>
</c:if>

<c:if test="${ pageActuelle < nbPages - 1 }">
	<li><a href="<mylib:link pager="${ pager }" parameterValue="${ nbPages }" parameter="page"></mylib:link>">${ nbPages }</a></li>
</c:if>

<c:set var="pageSuivante" value="${ pageActuelle + 1 }"></c:set>
<c:if test="${ pageSuivante < nbPages }">
	<li>
       <a href="<mylib:link pager="${ pager }" parameterValue="${ pageSuivante }" parameter="page"></mylib:link>" aria-label="Next">
           <span aria-hidden="true">&raquo;</span>
       </a>
   </li>
</c:if>

<%--
--%>