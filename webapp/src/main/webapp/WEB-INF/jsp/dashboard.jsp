<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
	<head>
	<title><spring:message code="title"></spring:message></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">

	<c:import url="templates/head.jsp"></c:import>
	</head>
	
	<body>
    <c:import url="templates/header.jsp"></c:import>
    
    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${ pager.count } <spring:message code="computer.found"></spring:message>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" value="${ pager.filter['search'] }" />
                        <input type="submit" id="searchsubmit" value="<spring:message code="button.filter"></spring:message>" class="btn btn-primary" />
                        <%-- on écrit le reste des paramètres dans l'url --%>
						<c:forEach items="${ pager.filter }" var="entry">
							<c:if test="${ entry.key ne 'search' }">
								<input type="hidden" value="${ entry.key }" name="${ entry.value }" />
							</c:if>
						</c:forEach>
                    </form>
                </div>
                
                <security:authorize access="isAuthenticated()"> 
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="computer/add/"><spring:message code="computer.add"></spring:message></a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="button.delete"></spring:message></a>
                </div>
                </security:authorize>
            </div>
        </div>

        <form id="deleteForm" action="computer/delete" method="POST">
            <input type="hidden" id="selection" name="selection" value="">
            <input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}" />
        </form>
        
        <div class="container" style="margin-top: 10px;">
        	 <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->
                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                            	<a id="deleteSelected" style="cursor: pointer" onclick="$.fn.deleteSelected();" >-<span aria-hidden="true" class="glyphicon glyphicon-trash"></span></a>
                            </span>
                        </th>
                        <th aria-sort="ascending">
                            <a id="${ computer.name }_name" href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByName"></mylib:link>' ><spring:message code="computer.name" ></spring:message> <mylib:chevron filter="${ pager.filter }" parameter="orderByName"></mylib:chevron></a>
                        </th>
                        <th>
                            <a id="introduced" href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByIntroduced"></mylib:link>' > <spring:message code="computer.introduced"></spring:message> <mylib:chevron filter="${ pager.filter }" parameter="orderByIntroduced"></mylib:chevron></a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <a id="discontinued"  href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByDiscontinued"></mylib:link>' ><spring:message code="computer.discontinued"></spring:message> <mylib:chevron filter="${ pager.filter }" parameter="orderByDiscontinued"></mylib:chevron></a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <a id="computerId" href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByCompany"></mylib:link>' > <spring:message code="computer.company"></spring:message> <mylib:chevron filter="${ pager.filter }" parameter="orderByCompany"></mylib:chevron></a>
                        </th>
                    </tr>
                </thead>
                
                <!-- Browse attribute computers -->
                <tbody id="results">
                    <c:forEach var="computer" items="${ pager.liste }">
                    <tr>
                        <td class="editMode">
                            <input id="${ computer.name}_id" type="checkbox" name="cb" class="cb" value="${ computer.id }">
                        </td>
                        <td>
                            <a id="${ computer.name }_name" href="${ pageContext.request.contextPath }/computer/edit/${ computer.id }" onclick=""><c:out value="${ computer.name }" ></c:out></a>
                        </td>
                        <td>
                        	<mylib:date date="${ computer.introduced }"></mylib:date>
                        </td>
                        <td>
                        	<mylib:date date="${ computer.discontinued }"></mylib:date>
                        </td>
                        <td><c:out value="${ computer.companyName }"></c:out></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
		</div>
    </section>
	<c:set var="url" value="dashboard?nb=${ pager.offset }"></c:set>
    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
        	<c:set var="pagePrecedente" value="${ pager.current -1 }"></c:set>
        	<c:set var="pageSuivante" value="${ pager.current + 1 }"></c:set>
        	
            <ul class="pagination">
            	<mylib:pagination pager="${ pager }" url="dashboard"></mylib:pagination>
        	</ul>
	        <div class="btn-group btn-group-sm pull-right" role="group" >
	            <a href="<mylib:link pager="${ pager }" parameterValue="10" parameter="offset"></mylib:link>" type="button" class="btn btn-default <c:if test="${ pager.offset == 10 }">active</c:if>"  >10</a>
	            <a href="<mylib:link pager="${ pager }" parameterValue="50" parameter="offset"></mylib:link>" type="button" class="btn btn-default <c:if test="${ pager.offset == 50 }">active</c:if>">50</a>
	            <a href="<mylib:link pager="${ pager }" parameterValue="100" parameter="offset"></mylib:link>" type="button" class="btn btn-default <c:if test="${ pager.offset == 100 }">active</c:if>">100</a>
	        </div>
		</div>
    </footer>
    	
       <input type="checkbox" id="selectall" /> 
       <c:import url="templates/script.jsp"></c:import>
       <c:import url="templates/validation-messages.jsp"></c:import>
       <script src="<spring:url value="/resources/js/dashboard.js" />"></script>
</body>
</html>