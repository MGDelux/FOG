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
    <title>Titlechamp</title>
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
    <!-- table  Buttom laves/-->
    <h1>Admin side</h1>
    <table id="buttom" border="1" title="">
        <thead>
        </thead>
        <tbody>

        </tbody>
    </table>
    <form method="post">
        <div class="buttons-buttom">
            <input name="materialName" type="text" value="navn" required>
            <input name="materialDescription" type="text" value="Beskrivelse" required>
            <input name="materialPrice" type="number" value="pris" required>
            <button type="submit" name="add-button" class="add-button">Tilføj</button>
        </div>
    </form>
    <form method="post">
        <div class="buttons-buttom" align="center">
            <label>ID: </label>
            <input name="removeButId" type="number" value="0" required>
            <button type="submit" name="removeBut" class="remove-button">Slet</button>
        </div>
    </form>


    <h3>Træ</h3>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Antal</th>
            <th scope="col">Navn</th>
            <th scope="col">Beskrivelse</th>
            <th scope="col">Pris</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>4</td>
            <td>bøgetræ</td>
            <td>@Vindskeder	på	rejsning</td>
            <td>420</td>
        </tr>

        </tbody>
    </table>
    <h3>Tagpakken</h3>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Antal</th>
            <th scope="col">Navn</th>
            <th scope="col">Beskrivelse</th>
            <th scope="col">Pris</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>4</td>
            <td>bøgetræ</td>
            <td>@Vindskeder	på	rejsning</td>
            <td>420</td>
        </tr>

        </tbody>
    </table>

    <h3>Beslag & Skruer</h3>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Antal</th>
            <th scope="col">Navn</th>
            <th scope="col">Beskrivelse</th>
            <th scope="col">Pris</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>4</td>
            <td>bøgetræ</td>
            <td>@Vindskeder	på	rejsning</td>
            <td>420</td>
        </tr>

        </tbody>
    </table>


</body>
<footer>
    <div class="footer">
        <a> SolidCODE: Emil, Janus og Mathias </a>
        <a href="https://github.com/MGDelux/FOG">| Github |</a>
    </div>
</footer>
</html>
