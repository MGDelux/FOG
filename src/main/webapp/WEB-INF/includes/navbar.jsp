<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Janus
  Date: 2020-12-10
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-md bg-primary navbar-dark sticky-top">
    <a class="navbar-brand" href="<c:url value="/"/>"><img src="/images/fog.png" alt="fog"/></a>
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="$<c:url value="/"/>">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/carport"/>">Bestil carporte</a>
    </ul>

</nav>
