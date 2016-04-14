<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"  %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<c:import url="/views/templates/head.jsp"></c:import>
</head>
<body>
    <c:import url="/views/templates/header.jsp"></c:import>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${ pager.count } Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" value="${ pager.filter['search'] }" />
                        <input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
                        <%-- on écrit le reste des paramètres dans l'url --%>
						<c:forEach items="${ pager.filter }" var="entry">
							<c:if test="${ entry.key ne 'search' }">
								<input type="hidden" value="${ entry.key }" name="${ entry.value }" />
							</c:if>
						</c:forEach>
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="deleteComputer" method="POST">
            <input type="hidden" name="selection" value="">
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
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th aria-sort="ascending">
                        	
                            <a href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByName"></mylib:link>' >Computer name <mylib:chevron filter="${ pager.filter }" parameter="orderByName"></mylib:chevron></a>
                        </th>
                        <th>
                            <a href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByIntroduced"></mylib:link>' >Introduced date <mylib:chevron filter="${ pager.filter }" parameter="orderByIntroduced"></mylib:chevron></a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <a href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByDiscontinued"></mylib:link>' >Discontinued date <mylib:chevron filter="${ pager.filter }" parameter="orderByDiscontinued"></mylib:chevron></a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <a href='<mylib:link pager="${ pager }" type="orderBy" parameterValue="" parameter="orderByCompany"></mylib:link>' >Company <mylib:chevron filter="${ pager.filter }" parameter="orderByCompany"></mylib:chevron></a>
                        </th>
                    </tr>
                </thead>
                
                <!-- Browse attribute computers -->
                <tbody id="results">
                    <c:forEach var="computer" items="${ pager.liste }">
                    <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${ computer.id }">
                        </td>
                        <td>
                            <a href="editComputer?id=${ computer.id }" onclick=""><c:out value="${ computer.name }" ></c:out></a>
                        </td>
                        <td>${ computer.introduced }</td>
                        <td>${ computer.discontinued }</td>
                        <td><c:out value="${ computer.company.name }"></c:out></td>
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
		<c:import url="/views/templates/script.jsp"></c:import>
		<script src="js/dashboard.js"></script>

</body>
</html>