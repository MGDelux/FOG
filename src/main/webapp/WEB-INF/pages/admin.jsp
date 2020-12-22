<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Janus
  Date: 2020-12-15
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
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
        <jsp:include page="../../css/admin.css"/>
    </style>
    <title>Admin</title>
    <nav class="navbar navbar-expand-md bg-primary navbar-dark sticky-top">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">FOG</a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/carport/">Bestil carporte</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/salesman/">Sælgerside</a>
            </li>
        </ul>
    </nav>
</head>
<body>

<div align="center">

<h1>Admin side</h1>

<table id="buttom" border="1" title="">
    <thead>
    </thead>
    <tbody>

    </tbody>
</table>

<h3>Træ</h3>
<table class="table-md table col-md-11">
<thead class="thead-dark">
<tr>
    <th scope="col">Id</th>
    <th scope="col">Antal</th>
    <th scope="col">Navn</th>
    <th scope="col">Beskrivelse</th>
    <th scope="col">længde</th>
    <th scope="col">Pris</th>
</tr>
</thead>
    <form method="post">
        <div class="buttons-buttom">
            <input name="materialId" type="number" value="Id" placeholder="Id" required>
            <input name="materialAntal" type="number" value="antal" placeholder="antal" required>
            <input name="materialName" type="text" value="navn" required>
            <input name="materialDescription" type="text" value="Beskrivelse" required>
            <input name="materialLength" type="number" value="length" placeholder="angiv i mm" required>
            <input name="materialPrice" type="number" value="pris" placeholder="pris" required>
            <button type="submit" name="add-material" class="add-button">Tilføj</button>
        </div>
    </form>
    <form method="post">
        <div class="buttons-buttom">
            <input name="materialId" type="number" value="antal" placeholder="Id" required>
            <input name="materialAntal" type="number" value="antal" placeholder="antal" required>
            <input name="materialName" type="text" value="navn" required>
            <input name="materialDescription" type="text" value="Beskrivelse" required>
            <input name="materialLength" type="number" value="length" placeholder="angiv i mm" required>
            <input name="materialPrice" type="number" value="pris" placeholder="pris" required>
            <button type="submit" name="replace-material" class="add-button">Erstat</button>
        </div>
    </form>
    <form method="post">
        <div class="buttons-buttom" align="center">
            <label>ID: </label>
            <input name="removeMaterialById" type="number" value="0" required>
            <button type="submit" name="removeMaterial" class="remove-button">Slet</button>
        </div>
    </form>
<tbody>
<c:forEach items="${MaTsFoRu}" var="items">
<tr>
    <td>${items.id}</td>
    <td>${items.getAmount()}</td>
    <td>${items.getName()}</td>
    <td>${items.getDescription()}</td>
    <td>${items.length}</td>
    <td>${items.getPrice()}</td>
    </tr>
</c:forEach>
    </tbody>
    </table>

    <h3>Beslag & Skruer</h3>

    <form method="post">
        <div class="buttons-buttom">
            <input name="screwId" type="number" value="Id" placeholder="Id" required>
            <input name="screwAntal" type="number" value="antal" placeholder="antal" required>
            <input name="screwName" type="text" value="navn" required>
            <input name="screwDescription" type="text" value="Beskrivelse" required>
            <input name="screwLength" type="number" value="længde" placeholder="Angiv i mm" required>
            <input name="screwPrice" type="number" value="pris" placeholder="pris" required>
            <button type="submit" name="add-screw" class="add-button">Tilføj</button>
        </div>
    </form>
    <form method="post">
        <div class="buttons-buttom">
            <input name="screwId" type="number" value="antal" placeholder="Id" required>
            <input name="screwAntal" type="number" value="antal" placeholder="antal" required>
            <input name="screwName" type="text" value="navn" required>
            <input name="screwDescription" type="text" value="Beskrivelse" required>
            <input name="screwLength" type="number" value="længde" placeholder="Angiv i mm" required>
            <input name="screwPrice" type="number" value="pris" placeholder="pris" required>
            <button type="submit" name="replace-screw" class="add-button">Erstat</button>
        </div>
    </form>
    <form method="post">
        <div class="buttons-buttom" align="center">
            <label>ID: </label>
            <input name="removeScrewById" type="number" value="0" required>
            <button type="submit" name="removeScrew" class="remove-button">Slet</button>
        </div>
    </form>

    <table class="table-md table col-md-11">

    <thead class="thead-dark">
    <tr>
    <th scope="col">Id</th>
    <th scope="col">Antal</th>
    <th scope="col">Navn</th>
    <th scope="col">Beskrivelse</th>
    <th scope="col">længde</th>
    <th scope="col">Pris</th>
    </tr>
    </thead>
    <tbody>
    <tr>
<c:forEach items="${matsScrew}" var="screws">
    <tr>
        <td>${screws.id}</td>
        <td>${screws.getAmount()}</td>
        <td>${screws.getName()}</td>
        <td>${screws.getDescription()}</td>
        <td>${screws.length}</td>
        <td>${screws.getPrice()}</td>
    </tr>
    </c:forEach>

    </tbody>
    </table>

    <div class="breaks">
    <br>
        <br>
        <br>
    </div>
</div>
</div>
    </body>
    <footer>
    <div class="footer">
    <a> SolidCODE: Emil, Janus og Mathias </a>
    <a href="https://github.com/MGDelux/FOG">| Github |</a>
    </div>
    </footer>
    </html>
