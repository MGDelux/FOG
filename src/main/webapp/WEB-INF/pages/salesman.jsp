<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Emil
  Date: 26-11-2020
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://kit.fontawesome.com/e79609ac4a.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        <jsp:include page="../../css/salesman.css"/>
    </style>
    <title>FOG ADMIN</title>
</head>
<body>
<nav class="navbar navbar-expand-md bg-primary navbar-dark sticky-top">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">FOG</a>
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/carport/">Bestil carporte</a>
        </li>
    </ul>

</nav>
<div class="sellingInfo">
    <div class="Selldiv">
        <p class="smalltext"></p>
        <p class="smalltext">Medarbejder Info:</p>
        <p class="userinfo"><STRONG>${sessionScope.employee.email} - ${sessionScope.employee.role}</STRONG></p>
        <div class="adminref">

            <form method="post">
                <button type="hidden" style="outline: 0;" class="logger" name="logout">
                    >LOGOUT<
                </button>
            </form>
            <div>
                <button type="hidden" style="outline: 0" class="logger" name="adminpage">
                    <c:if test="${sessionScope.employee.role != 'SALESMAN'}"><a
                        href="${pageContext.request.contextPath}/admin">>ADMIN PAGE<</a></button>
                </c:if>


                <p class="smalltext"></p>

                <p class="smalltext"></p>
            </div>
        </div>
        </div>
    </div>
    <div class="overskrift text-center">
    <h1>Sælger side:</h1>
    </div>
    <div class="Salescontainer d-flex align-items-center min-vh-100">

        <div class="tablecontain flex-box justify-content-md-center align-items-center">

            <table class="table-striped table-dark table-md table-bordered table-hover table col-md-11 text-center">
                <thead>
                <tr>
                    <th scope="col">ID:</th>
                    <th scope="col">Kunde</th>
                    <th scope="col">Forespørgsel</th>
                    <th scope="col">Carport dimensioner</th>
                    <th scope="col">Skur dimensioner</th>
                    <th scope="col">Sælger</th>
                    <th scope="col">Tilbudsudregner</th>
                    <th scope="col">Tildel mig</th>
                    <th scope="col">Slet</th>
                </tr>
                <c:forEach items="${Queries}" var="items">
                    <tr>
                        <td>${items.getId()}</td>
                        <td>${items.getEmail()}</td>
                        <td>${items.carport.getRoof()} TAG</td>
                        <td>${items.carport.width} x ${items.carport.length} </td>
                        <td>${items.shed.width} x ${items.shed.length} </td>
                        <td>${items.getSeller()}</td>
                        <td>
                            <form method="post">
                                <input class="Sbutton" type="hidden" name="selectOrder" value="${items.getId()}">
                                <input type="submit" value="Vælg" name="details"/>
                            </form>
                        </td>
                        <td>
                            <form method="post">
                                <input class="Sbutton" type="hidden" name="assignSell" value="${items.getId()}">
                                <input type="submit" value="Tildel" name="assigSellButton"/>
                            </form>
                        </td>
                        <td>
                            <form method="post">
                                <input class="Sbutton" type="hidden" name="deleteOrder" value="${items.getId()}">
                                <input type="submit" value="Slet" name="deleteOrderButton"/>
                            </form>
                        </td>

                    </tr>
                </c:forEach>
                </thead>

            </table>
        </div>
    </div>
    <hr class="solid">

</body>
<footer>
    <div class="footer">
        <a> SolidCODE: Emil, Janus og Mathias </a>
        <a href="https://github.com/MGDelux/FOG">| Github |</a>
    </div>
</footer>
</html>
