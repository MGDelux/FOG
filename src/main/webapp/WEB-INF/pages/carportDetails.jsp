<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 21-12-2020
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FOG din carport</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
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
        <jsp:include page="../../css/indexRefresh.css"/>
    </style>
    <link rel="stylesheet" type="text/css" href="../../css/indexRefresh.css">

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
<div class="bgimg1">
    <div class="titlebox">
    </div>
</div>
<div class="intro">
    <form method="post">
        <div class="brødtekst">
            <h1 align="center">Forespørgelse(r): </h1>
            <div align="center" class="form-group col-md-4" style="text-align: center">
                <label for="Email"><strong>Din Email:</strong></label>
                <input type="email" class="form-control" name="Email" id="Email" placeholder="Email">
            </div>
            <p></p>
            <div class="form-row div-wrapper d-flex justify-content-center" align="center" style="text-align: center">
                <input required align="center" class="btn btn-primary" type="submit"
                       value="Få Forespørgelse infomation"/>
            </div>
            <div class="tablekeeper">
                <table class="querytable">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Carport</th>
                        <th>Skur</th>
                        <th>Sælger</th>
                    </tr>
                    <c:forEach items="${Query}" var="items">
                        <tr>
                            <td>${items.getId()}</td>
                            <td>${items.getCarport()}</td>
                            <td>${items.getShed()}</td>
                            <td>${items.getSeller()}</td>
                        </tr>
                    </c:forEach>

                    </thead>
                </table>
            </div>
        </div>
    </form>
    <div align="center" style="text-align: center">
        <a  href="${pageContext.request.contextPath}/" class="btn btn-primary btn-lg active" role="button"
            aria-pressed="true">Tilbage</a>
        <div style="text-align: center" class="extraInfo">
            <hr class="solid">
            <p style="text-decoration: none">Hvis informationen ovenfor ikke er korrekt kan de kontakte os via mail: <a href="mailto:solidcodefog@gmail.com
?subject=vedrforsprøgelse&body=Message">solidcodefog@gmail.com
            </a></p>
        </div>
    </div>
</div>
</div>

</div>
</div>
<div class="bgimg2">

</div>
</body>
</html>
