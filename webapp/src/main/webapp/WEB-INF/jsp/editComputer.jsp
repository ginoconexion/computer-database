<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<c:import url="templates/head.jsp"></c:import>
</head>
<body>
    <c:import url="templates/header.jsp"></c:import>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <c:import url="forms/computerForm.jsp"></c:import>
                </div>
            </div>
        </div>
    </section>
</body>
<c:import url="templates/script.jsp"></c:import>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.2.8/jquery.form-validator.min.js"></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js"></c:url>"></script>
<script src='<c:url value="/resources/js/validator.js"></c:url>'></script>
</html>