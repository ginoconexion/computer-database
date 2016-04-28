<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"  %>

<%@ attribute name="pager" required="true" type="com.formation.computerdatabase.service.util.Pager"%>
<%@ attribute name="url" required="true" type="java.lang.String"%>

<c:set var="current" value="${ pager.current }"></c:set>
<c:set var="previous" value="${ current - 1 }"></c:set>
<c:set var="nbPages" value="${ pager.nbPages }"></c:set>

<c:if test="${ previous > 0 }">
	<li>
     	<a href="<mylib:link pager="${ pager }" parameterValue="${ previous }" parameter="page"></mylib:link>" aria-label="Previous">
         	<span aria-hidden="true">&laquo;</span>
        </a>
    </li>
</c:if>


<c:if test="${ current > 2 }">
	<li><a href="<mylib:link pager="${ pager }" parameterValue="1" parameter="page"></mylib:link>">1</a></li>
</c:if>

<c:if test="${ current > 3 }">
<li><a>...</a></li>
</c:if>

<c:forEach begin="1" end="${ nbPages }" var="p">
	<c:if test="${ p > current - 2 && p < current + 2 }">
		<li <c:if test="${ p == current }">class="active"</c:if> ><a href="<mylib:link pager="${ pager }" parameterValue="${ p }" parameter="page"></mylib:link>">${ p }</a></li>
	</c:if>
</c:forEach>


<c:if test="${ current < nbPages - 2 }">
	<li><a>...</a></li>
</c:if>

<c:if test="${ current < nbPages - 1 }">
	<li><a href="<mylib:link pager="${ pager }" parameterValue="${ nbPages }" parameter="page"></mylib:link>">${ nbPages }</a></li>
</c:if>

<c:set var="next" value="${ current + 1 }"></c:set>
<c:if test="${ next < nbPages }">
	<li>
       <a href="<mylib:link pager="${ pager }" parameterValue="${ next }" parameter="page"></mylib:link>" aria-label="Next">
           <span aria-hidden="true">&raquo;</span>
       </a>
   </li>
</c:if>

