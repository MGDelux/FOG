<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="customer" scope="request" type="domain.Customers.Customers"/>
<jsp:useBean id="query" scope="request" type="domain.Queries.Queries"/>
<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 03-12-2020
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <jsp:include page="../../css/forsprøgelse.css"/>
    </style>
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
    <title>Tak for din forspørgelse!</title>
</head>
<body>
<div class="wrapper">
    <div class="content">
        <h1>Tak for din carport forspørgelse!</h1>
        <p>Din forspørgelse er blevet registreret og du modtager en bekræftelse email snarest.</p>
        <p>Du vil blive kontaktet af en af vores sælgere hurtigst muligt.</p>
        <h4><strong>Forespørgelses detaljer:</strong></h4>
        <h5> Kontakt informationer: </h5>
        <table class="table">
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>EMAIL</td>
                <td>${customer.email}</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Tlf.</td>
                <td>${customer.phoneNr}</td>
            </tr>
            </tbody>
        </table>

        <p>EMAIL: ${customer.email}></p>
        <p>TLF NR: ${customer.phoneNr}</p>
        <h5>Carport specifikationer:</h5>
        <p>Længde: ${query.carPortWidth} cm</p>
        <p>Bredde: ${query.cartPortLength} cm</p>
        <p>Tag: ${query.roofType}</p>

        <c:if test="${query.shed != null}">
            <h5>Redskabs rum specifikationer:</h5>
            <p>Længde: ${query.shed.length}</p>
            <p>Bredde: ${query.shed.width}</p>
        </c:if>
        <hr class="solid">

    </div>
    <a href="${pageContext.request.contextPath}/carport/" class="btn btn-primary btn-lg active" role="button"
       aria-pressed="true">Tilbage</a>
    <div class="extraInfo">
        <hr class="solid">
        <p>Hvis informationen ovenfor ikke er korrekt kan de kontakte os via mail: <a href="mailto:solidcodefog@gmail.com
?subject = vedrforsprøgelse &body = Message">solidcodefog@gmail.com
        </a></p></div>
</div>
</body>
</html>
