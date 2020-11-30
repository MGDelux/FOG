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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://kit.fontawesome.com/e79609ac4a.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
    <div align="center" class="container1">
        <h1>Bestil din skrædersyet carport her!</h1>
        <h3>Carporte med fladt tag:</h3>
        <p><Strong>Carport bredde:</strong></p>
        <table id="Carport-bredde" border="1" title="">
        </table>
        <form method="post" style="text-align: center">
            <div class="form-group col-md-12 col-xs-8 d-flex justify-content-center">
                <select class="Carport-bredde" name="Carport-bredde">
                    <option value="240 cm">240 cm</option>
                    <option value="270 cm">270 cm</option>
                    <option value="300 cm">300 cm</option>
                    <option value="330 cm">330 cm</option>
                    <option value="360 cm">360 cm</option>
                    <option value="390 cm">390 cm</option>
                    <option value="420 cm">420 cm</option>
                    <option value="450 cm">450 cm</option>
                    <option value="480 cm">480 cm</option>
                    <option value="480 cm">480 cm</option>
                    <option value="510 cm">510 cm</option>
                    <option value="540 cm">540 cm</option>
                    <option value="570 cm">570 cm</option>
                    <option value="600 cm">600 cm</option>
                    <option value="630 cm">630 cm</option>
                    <option value="660 cm">660 cm</option>
                    <option value="690 cm">690 cm</option>
                    <option value="720 cm">720 cm</option>
                    <option value="750 cm">750 cm</option>
                </select>
            </div>
        </form>

        <p><Strong>Carport længde:</Strong></p>
        <table id="Carport-længde" border="1" title="">
        </table>
        <form method="post">
            <div class="form-group col-md-12 d-flex justify-content-center">
                <select class="Carport-længde col-xs-4" name="Carport-længde">
                    <option value="240 cm">240 cm</option>
                    <option value="270 cm">270 cm</option>
                    <option value="300 cm">300 cm</option>
                    <option value="330 cm">330 cm</option>
                    <option value="360 cm">360 cm</option>
                    <option value="390 cm">390 cm</option>
                    <option value="420 cm">420 cm</option>
                    <option value="450 cm">450 cm</option>
                    <option value="480 cm">480 cm</option>
                    <option value="510 cm">510 cm</option>
                    <option value="540 cm">540 cm</option>
                    <option value="570 cm">570 cm</option>
                    <option value="600 cm">600 cm</option>
                    <option value="630 cm">630 cm</option>
                    <option value="660 cm">660 cm</option>
                    <option value="690 cm">690 cm</option>
                    <option value="720 cm">720 cm</option>
                    <option value="750 cm">750 cm</option>
                    <option value="780 cm">780 cm</option>
                </select>
            </div>
        </form>

        <p><Strong>Carport tag:</Strong></p>
        <table id="Carport-tag" border="1" title="">
        </table>
        <form method="post">
            <div class="form-group col-md-12 d-flex justify-content-center">
                <select class="Carport-længde col-xs-4" name="Carport-tag">
                    <option value="240 cm">240 cm</option>
                    <option value="270 cm">270 cm</option>
                    <option value="300 cm">300 cm</option>
                    <option value="330 cm">330 cm</option>
                    <option value="360 cm">360 cm</option>
                    <option value="390 cm">390 cm</option>
                    <option value="420 cm">420 cm</option>
                    <option value="450 cm">450 cm</option>
                    <option value="480 cm">480 cm</option>
                    <option value="510 cm">510 cm</option>
                    <option value="540 cm">540 cm</option>
                    <option value="570 cm">570 cm</option>
                    <option value="600 cm">600 cm</option>
                    <option value="630 cm">630 cm</option>
                    <option value="660 cm">660 cm</option>
                    <option value="690 cm">690 cm</option>
                    <option value="720 cm">720 cm</option>
                    <option value="750 cm">750 cm</option>
                    <option value="780 cm">780 cm</option>
                </select>
            </div>
        </form>

        <h2>Redskabsrum:</h2>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck">
                <label class="form-check-label" for="gridCheck">
                    Ja tak
                </label>
            </div>
        </div>
        <p>NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*</p>
        <p><Strong>Redskabsrum bredde:</Strong></p>
        <table id="Redskabsrum-bredde" border="1" title="">
        </table>
        <form method="post">
            <div class="Redskabsrum-bredde col-xs-4">
                <select class="Redskabsrum-bredde" name="Redskabsrum-bredde">
                    <option value="240 cm">240 cm</option>
                    <option value="270 cm">270 cm</option>
                    <option value="300 cm">300 cm</option>
                    <option value="330 cm">330 cm</option>
                    <option value="360 cm">360 cm</option>
                    <option value="390 cm">390 cm</option>
                    <option value="420 cm">420 cm</option>
                    <option value="450 cm">450 cm</option>
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
            <div class="Redskabsrum-længde col-xs-4">
                <select class="Redskabsrum-længde" name="Redskabsrum-længde">
                    <option value="240 cm">240 cm</option>
                    <option value="270 cm">270 cm</option>
                    <option value="300 cm">300 cm</option>
                    <option value="330 cm">330 cm</option>
                    <option value="360 cm">360 cm</option>
                    <option value="390 cm">390 cm</option>
                    <option value="420 cm">420 cm</option>
                    <option value="450 cm">450 cm</option>
                </select>
            </div>
        </form>
        <button name="placeOrder" type="submit" class="btn btn-primary">Bestil carport</button>
    </div>

</div>
</body>
<footer>
    <div class="footer">
        <a> SolidCODE: Emil, Janus og Mathias </a>
        <a href="https://github.com/MGDelux/FOG">| Github |</a>
        <a href="${pageContext.request.contextPath}/adminpage/">Adminpage |</a>
    </div>
</footer>
</html>
