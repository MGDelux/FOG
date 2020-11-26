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
    <div align="center" class="container2" >
        <form>
            <div class="form-row  div-wrapper d-flex justify-content-center">
                <div class="form-group col-md-4">
                    <label for="Email"><strong>Email</strong></label>
                    <input type="email" class="form-control" id="Email" placeholder="Email">
                </div>
                <div class="form-group col-md-4">
                    <label for="password"><strong>Password</strong></label>
                    <input type="password" class="form-control" id="password" placeholder="Password">
                </div>
            </div>
            <div class="form-row  div-wrapper d-flex justify-content-center align-items-center">
                <div class="form-group col-md-4">
                    <label for="inputAddress"><strong>Address</strong></label>
                    <input type="text" class="form-control" id="inputAddress" placeholder="Nørrebrogade">
                 </div>
                 <div class="form-group col-md-4">
                     <label for="address"><strong>Address 2</strong></label>
                     <input type="text" class="form-control" id="address" placeholder="Opgang, studie, eller sal">
                </div>
            </div>
            <div class="form-row  div-wrapper d-flex justify-content-center align-items-center">
                <div class="form-group col-md-2">
                    <label for="by"><strong>By</strong></label>
                    <input type="text" class="form-control" id="by" placeholder="By">
                </div>
                <div class="form-group col-md-2">
                    <label for="postnummer"><strong>Postnummer</strong></label>
                    <input type="text" class="form-control" id="postnummer" placeholder="Postnummer">
                </div>
            </div>
            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="gridCheck">
                    <label class="form-check-label" for="gridCheck">
                        Check me out
                    </label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Send forespørgsel</button>
        </form>

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
