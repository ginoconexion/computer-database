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
                ${ pager.nbEntries } Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" value="${ pager.filter['byName'] }" />
                        <input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
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
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
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

	<c:set var="url" value="dashboard?nb=${ pager.nbParPage }&search=${ pager.filter['byName'] }"></c:set>
	
    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
        	<c:set var="pagePrecedente" value="${ pager.pageActuelle -1 }"></c:set>
        	<c:set var="pageSuivante" value="${ pager.pageActuelle + 1 }"></c:set>
        	
            <ul class="pagination">
            	<mylib:link nbPages="${ pager.nbPages }" url="${ url }" type="prev" pageActuelle="${ pager.pageActuelle }"></mylib:link>
            	<mylib:pagination nbPages="${ pager.nbPages }" url="${ url }" pageActuelle="${ pager.pageActuelle }"></mylib:pagination>
            	<mylib:link nbPages="${ pager.nbPages }" url="${ url }" type="next" pageActuelle="${ pager.pageActuelle }"></mylib:link>
        	</ul>

	        <div class="btn-group btn-group-sm pull-right" role="group" >
	            <a href="Dashboard?nb=10" type="button" class="btn btn-default <c:if test="${ pager.nbParPage == 10 }">active</c:if>"  >10</a>
	            <a href="Dashboard?nb=50" type="button" class="btn btn-default <c:if test="${ pager.nbParPage == 50 }">active</c:if>"">50</a>
	            <a href="Dashboard?nb=100" type="button" class="btn btn-default <c:if test="${ pager.nbParPage == 100 }">active</c:if>"">100</a>
	        </div>
	        
		</div>
    </footer>
       <input type="checkbox" id="selectall" /> 
		<c:import url="/views/templates/script.jsp"></c:import>
		<script src="js/dashboard.js"></script>

</body>
</html>