<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<c:import url="/views/templates/header.jsp"></c:import>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><c:choose><c:when test="${ empty computer.id or computer.id == 0  }">Add</c:when><c:otherwise>Edit</c:otherwise></c:choose> Computer</h1>
                    <form action="<c:choose><c:when test="${ empty computer.id or computer.id == 0 }">addComputer</c:when><c:otherwise>editComputer</c:otherwise></c:choose>" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" name="computerName" id="computerName" placeholder="Computer name" value="${ computer.name }">
                                <input type="hidden" name="id" value="${ computer.id }">
                            </div>
                            <c:if test="${ form.erreurs.containsKey('computerName') }">
                            <div class="alert alert-danger">
							  <strong>Error :</strong> ${ form.erreurs['computerName']}
							</div>
							</c:if>
                            
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" name="introduced" id="introduced" placeholder="Introduced date" value="${ computer.introduced }">
                            </div>
                            <c:if test="${ form.erreurs.containsKey('introduced') }">
                            <div class="alert alert-danger">
							  <strong>Error :</strong> ${ form.erreurs['introduced']}
							</div>
							</c:if>
                            
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" name="discontinued" id="discontinued" placeholder="Discontinued date" value="${ computer.discontinued }">
                            </div>
                            <c:if test="${ form.erreurs.containsKey('discontinued') }">
                            <div class="alert alert-danger">
							  <strong>Error :</strong> ${ form.erreurs['discontinued']}
							</div>
							</c:if>
                            
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" name="companyId" id="companyId" >
                                	<option value="">Sélectionner une company</option>
                                    <c:forEach var="company" items="${ companies }">
                                    <option  <c:if test="${ company.id == computer.company.id }">selected="selected"</c:if> value="${ company.id }">${ company.name }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <c:if test="${ form.erreurs.containsKey('companyId') }">
                            <div class="alert alert-danger">
							  <strong>Error :</strong> ${ form.erreurs['companyId']}
							</div>
							</c:if>
                                              
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>