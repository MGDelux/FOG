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

<div class="wrapper">
<h1>Tak for din carport forspørgelse!</h1>
<p>Din forspørgelse er blevet registreret og du modtager en bekræftelse email snarest.</p>
<p>Du vil blive kontaktet af en af vores sælgere hurtigst muligt.</p>
<h4><strong>Forespørgelses detaljer:</strong></h4>
<h5> Kontakt informationer: </h5>
    <div class="container-sm" style="width: 400px">
<table class="table">
    <tbody>
    <tr>
        <th scope="row">EMAIL</th>
        <td>${customer.email}</td>
    </tr>
    <tr>
        <th scope="row">Tlf.</th>
        <td>${customer.phoneNr}</td>
    </tr>
    </tbody>
</table>
    </div>

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

<a href="${pageContext.request.contextPath}/carport/" class="btn btn-primary btn-lg active" role="button"
   aria-pressed="true">Tilbage</a>
<div class="extraInfo">
    <hr class="solid">
    <p>Hvis informationen ovenfor ikke er korrekt kan de kontakte os via mail: <a href="mailto:solidcodefog@gmail.com
?subject=vedrforsprøgelse&body=Message">solidcodefog@gmail.com
    </a></p>
</div>
</div>