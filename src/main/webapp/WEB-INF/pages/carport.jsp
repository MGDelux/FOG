<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Emil
  Date: 23-11-2020
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/e79609ac4a.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        <jsp:include page="../../css/carport.css"/>
    </style>
    <title>Carporte</title>
    <div class="HeaderImage">
        <img class="headerImage" src="https://fut.dk/wp-content/uploads/job-manager-uploads/company_logo/Fog_100_logo-1024x512.jpg">
    </div>
</head>
<body>
<nav class="navbar navbar-expand-md bg-primary navbar-dark sticky-top">
    <a class="navbar-brand" href="https://www.johannesfog.dk/globalassets/100-ar/fog-100-logo-animation-flip-126.gAif">FOG</a>
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/carport/">Bestil carporte</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/EmailSendingServlet ">Mail test</a>
        </li>
    </ul>

</nav>
<div class="bg">
    <div align="center" class="container1">
        <h1>Bestil din skrædersyet carport her!</h1>
        <h3>Carporte med fladt tag:</h3>
        <p><Strong>Carport bredde:</strong></p>
        <table id="Carport-bredde" border="1" title="">
            <thead>
            <!-- table rows id,name og pris laves/-->
            <tr>
                <th></th>
            </tr>

            </thead>
            <tbody>
            <!-- table  Buttom bliver populated/-->
                <tr>
                    <td></td>
                </tr>
            </tbody>
        </table>
        <form method="post">
            <div class="Carport-bredde">
                <select class="Carport-bredde" name="Carport-bredde">
                    <option>1</option>
                    <option>2</option>
                </select>
            </div>
        </form>

        <p><Strong>Carport længde:</Strong></p>
        <table id="Carport-længde" border="1" title="">
            <thead>
            <!-- table rows id,name og pris laves/-->
            <tr>
                <th></th>
            </tr>

            </thead>
            <tbody>
            <!-- table  Buttom bliver populated/-->
            <tr>
                <td></td>
            </tr>
            </tbody>
        </table>
        <form method="post">
            <div class="Carport-længde">
                <select class="Carport-længde" name="Carport-længde">
                    <option>1</option>
                </select>
            </div>
        </form>

        <p><Strong>Carport tag:</Strong></p>
        <table id="Carport-tag" border="1" title="">
            <thead>
            <!-- table rows id,name og pris laves/-->
            <tr>
                <th></th>
            </tr>

            </thead>
            <tbody>
            <!-- table  Buttom bliver populated/-->
            <tr>
                <td></td>
            </tr>
            </tbody>
        </table>
        <form method="post">
            <div class="Carport-tag">
                <select class="Carport-længde" name="Carport-tag">
                    <option>1</option>
                </select>
            </div>
        </form>

        <h2>Redskabsrum:</h2>
        <p>NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*</p>
        <p><Strong>Redskabsrum bredde:</Strong></p>
        <table id="Redskabsrum-bredde" border="1" title="">
            <thead>
            <!-- table rows id,name og pris laves/-->
            <tr>
                <th></th>
            </tr>

            </thead>
            <tbody>
            <!-- table  Buttom bliver populated/-->
            <tr>
                <td></td>
            </tr>
            </tbody>
        </table>
        <form method="post">
            <div class="Redskabsrum-bredde">
                <select class="Redskabsrum-bredde" name="Redskabsrum-bredde">
                    <option>1</option>
                </select>
            </div>
        </form>

        <p><Strong>Redskabsrum-længde:</Strong></p>
        <table id="Redskabsrum-længde" border="1" title="">
            <thead>
            <!-- table rows id,name og pris laves/-->
            <tr>
                <th></th>
            </tr>

            </thead>
            <tbody>
            <!-- table  Buttom bliver populated/-->
            <tr>
                <td></td>
            </tr>
            </tbody>
        </table>
        <form method="post">
            <div class="Redskabsrum-længde">
                <select class="Redskabsrum-længde" name="Redskabsrum-længde">
                    <option>1</option>
                </select>
            </div>
        </form>
    </div>
    <br>
    <div align="center" class="container2">
        <div class="col-xs-4">
            <label for="name"><Strong>Navn:</Strong></label>
            <input type="name" class="form-control" id="name" style="width: 400px">
        </div>

        <div class="form-group">
            <label for="Adresse"><Strong>Adresse:</Strong></label>
            <input type="text" class="form-control" id="Adresse" style="width: 400px">
        </div>

        <div class="form-group">
            <label for="Postnummer"><Strong>Postnummer:</Strong></label>
            <input type="number" class="form-control" id="Postnummer" style="width: 400px">
        </div>

        <div class="form-group">
            <label for="By"><Strong>By:</Strong></label>
            <input type="text" class="form-control" id="By" style="width: 400px">
        </div>

        <div class="form-group">
            <label for="Telefon"><Strong>Telefon:</Strong></label>
            <input type="tel" class="form-control" id="Telefon" style="width: 400px">
        </div>

        <div class="form-group">
            <label for="Email"><Strong>Email:</Strong></label>
            <input type="email" class="form-control" id="Email" style="width: 400px">
        </div>

        <div class="form-group">
            <label for="Bemærkning"><Strong>Evt. Bemærkning:</Strong></label>
            <input type="Bemærkning" class="form-control" id="Bemærkning" style="width: 400px">
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
