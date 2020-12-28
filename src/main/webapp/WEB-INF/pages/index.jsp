<%--
  Created by IntelliJ IDEA.
  User: Emil
  Date: 19-12-2020
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FOG HOME PAGE</title>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
            <a class="nav-link" href="${pageContext.request.contextPath}/">Forside</a>
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
    <div class="brødtekst">
        <h1 align="center">Velkommen til Fog</h1>
        <p align="center">Byg din egen trægarage efter dine ønsker – så kan bilen opholde sig inde i en robust og samtidigt elegant bygning.<BR>
            Men garager er ikke bare til biler, men også til din drøm af en motorcykel, alle ungernes cykler eller værksted. <BR>
            Køb nu, men få leveret senere når det passer dig.
        </p>
        <p align="center">Klik og find din nye carport idag!</p>

        <div class="form-row div-wrapper d-flex justify-content-center" align="center" >
            <form action="${pageContext.request.contextPath}/carport/">
                <input align="center" class="btn btn-primary" type="submit" value="DESGIN DIN EGEN CARPORT!" />
            </form>
        </div>
    </div>
</div>
<div class="bgimg2">
    <div class="brødtekst2">
    <h1 align="center">har allerede bestilt en carport?</h1>
    <p align="center">Hvis du allerede har bestilt en carport så klik på knappen nedenunder og check din forspørgelses status!
    </p>
        <form action="${pageContext.request.contextPath}/carportDetails/">
            <input align="center" class="btn btn-primary" type="submit" value="CHECK CARPORT STATUS!" />
        </form>
</div>

</div>
</body>
<footer>
    <div class="footer">
        <a> SolidCODE: Emil, Janus og Mathias </a>
        <a href="https://github.com/MGDelux/FOG">| Github |</a>
        <a href="${pageContext.request.contextPath}/salesman">Sælgerside |</a>
    </div>
</footer>
</html>
