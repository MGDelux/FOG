<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 26-10-2020
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404'ED</title>
    <style> <jsp:include page="../css/Loginstyle.css"/></style>
</head>
<body>
<main>
    <div class="center">
        <div class="container">
            <div class="data">
               <h1>OH GOD OH LAWD SOMETHING BAD HAS HAPPENED:</h1>
                <h2 class="ERROR: ">${pageContext.response.status}</h2>
                <p><%=request.getAttribute("javax.servlet.error.message")%></p>
                <div class="signup-link">
                    WANNA GO BACK? <a href="${pageContext.request.contextPath}">klik her.</a></div>
            </div>
        </div>
    </div>
</main>

</body>
</html>
