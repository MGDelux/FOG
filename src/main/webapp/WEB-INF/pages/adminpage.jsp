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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://kit.fontawesome.com/e79609ac4a.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        <jsp:include page="../../css/admin.css"/>
    </style>
    <title>FOG HOMEPAGE</title>
    <div class="HeaderImage">
        <img class="headerImage" src="https://fut.dk/wp-content/uploads/job-manager-uploads/company_logo/Fog_100_logo-1024x512.jpg">
    </div>
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
<div class="bg">
    <div class="brødtekst">
        <h1>Adminpage</h1>
    </div>
    <table class="table table-striped table-dark table-md table-bordered table-hover table col-md-11">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">kunde</th>
            <th scope="col">Forespørgsel</th>
            <th scope="col">Sælger</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Carport</td>
            <td>Janus</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>Larry</td>
            <td>Carport</td>
            <td>Janus</td>
        </tr>
        <tr>
            <th scope="row">4</th>
            <td>Larry</td>
            <td>Carport</td>
            <td>Janus</td>
        </tr>
        <tr>
            <th scope="row">5</th>
            <td>Larry</td>
            <td>Carport</td>
            <td>Janus</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
<footer>
    <div class="footer">
        <a> SolidCODE: Emil, Janus og Mathias </a>
        <a href="https://github.com/MGDelux/FOG">| Github |</a>
    </div>
</footer>
</html>
