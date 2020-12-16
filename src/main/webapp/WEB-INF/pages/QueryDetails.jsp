<jsp:useBean id="qById" scope="request" type="domain.Queries.Queries"/>

<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 14-12-2020
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script>
        function myFunction() {
            var checkBox = document.getElementById("gridCheck");
            var text = document.getElementById("text");
            if (checkBox.checked == true) {
                text.style.display = "block";
                $("#text").removeClass('d-none');
            } else {
                text.style.display = "none";
                $("#text").addClass('d-none');
            }
        }

    </script>
    <style>
        <jsp:include page="../../css/details.css"/>
    </style>
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
    </ul>
</nav>
<div class="Container">
    <div CLASS="holder">
        <h1 style="text-align: center"> Kunde-forespørgelse detaljer:</h1>
        <table class="table table-striped table-dark table-md table-bordered table-hover table col-md-11">
            <thead>
            <tr>
                <th scope="col">Kunde</th>
                <th scope="col">Forespørgsel</th>
                <th scope="col">Carport</th>
                <th scope="col">Skur</th>
            </tr>

              <c:forEach items="${qById}" var="items">
                    <tr>
                        <td>${qById.email}</td>
                        <td>${qById.carport.roof} TAG</td>
                        <td>${qById.carport.length} x ${qById.carport.width}</td>
                        <td>${qById.shed.length} x ${qById.shed.width}</td>
                    </tr>
            </c:forEach>
            </thead>
            <tbody>

        </table>
    </div>
    </div>
</div>
<hr class="solid">
<div class="dimenstioner">
    <h1>Dimensioner:</h1>

    <div class="">
        <p><Strong>Carport bredde:</strong></p>

        <input required type="range" class="form-control-range" value=${qById.carport.width} min="240" max="750" step="30"
               id="customRange3" name="CarportWidth" oninput="amount.value=customRange3.value">
        <input disabled="disabled" class="outputshowcase" id="amount" type="number" value="${qById.carport.width}" min="240" max="750"
               oninput="customRange3.value=amount.value"/>
        <p><Strong>Carport længde:</strong></p>
        <input required type="range" class="form-control-range" value="${qById.carport.length}" min="240" max="780" step="30"
               id="customRange4" name="CarportLength" oninput="amount2.value=customRange4.value">
        <input disabled="disabled" class="outputshowcase" id="amount2" type="number" value="${qById.carport.length}" min="240" max="780"
               oninput="customRange4.value=amount2.value"/>
        <div class="form-check">
            <input name="includeShed" class="form-check-input" type="checkbox"  id="gridCheck" onclick="myFunction()">
            <label class="form-check-label" for="gridCheck">
              Redskabsrum
            </label>
        </div>
        <div class="d-none" style="desplay:none" id="text">
        <p><Strong>Redskabsrum bredde:</Strong></p>
        <div class="Redskabsrum-bredde">
            <input required type="range" class="form-control-range" value="${qById.shed.width}" min="240" max="780" step="30"
                   id="customRange5" name="ShedWidth" oninput="amount3.value=customRange5.value">
            <input disabled="disabled" class="outputshowcase" id="amount3" type="number" value="${qById.shed.width}" min="240"
                   max="780" oninput="customRange5.value=amount3.value"/>

        </div>
        <p><Strong>Redskabsrum-længde:</Strong></p>
        <div class="Redskabsrum-længde">
            <input required type="range" class="form-control-range" value="${qById.shed.length}" min="240" max="780" step="30"
                   id="customRange6" name="ShedLength" oninput="amount4.value=customRange6.value">
            <input disabled="disabled" class="outputshowcase" id="amount4" type="number" value="${qById.shed.length}" min="240"
                   max="780" oninput="customRange6.value=amount4.value"/>
        </div>
        </div>
</div>
</div>
<hr class="solid">
<div class="tag">
    <h1>TAG:</h1>
    <div class="tbd">
        <p>TAG type:</p>
        <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
            <option selected>${qById.carport.roof}</option>
            <option value="1">Fladt</option>
            <option value="2">Rejsning</option>
        </select>
        <p><Strong>Tag rejsning grader </Strong></p>
        <div class="Redskabsrum-længde">
            <input required type="range" class="form-control-range" value="${qById.carport.roofAngle}" min="10" max="90" step="5"
                   id="rejsning" name="ShedLength" oninput="procent.value=rejsning.value">
            <input disabled="disabled" class="outputshowcase" id="procent" type="number" value="${qById.carport.roofAngle}" min="10"
                   max="90" oninput="rejsning.value=procent.value"/> grader
    </div>
</div>
</div>
<hr class="solid">
<div class="stykliste">
    <h1>STYKLISTE:</h1>
    <div class="styk">
        <div class="stykebutton">
        <button class="beregnstyk" >Beregn stykliste</button>
        </div>
        <table class="table table-striped table-dark table-md table-bordered table-hover table col-md-11">
       <thead>
       <tr>
       <th>Antal</th>
       <th>ID</th>
       <th>Beskrivelse</th>
       <th>Længde</th>
       <th>Brede</th>
       </tr>
       </thead>
        </table>
        <div class="stykpris">
            <h5>Pris forslag med dæknings grad:</h5>
            <p>Ialt uden moms:</p>
            <p>Kostpris:</p>
            <hr class="solid">
            <p>Dæknignsgrad:</p>
        </div>
        <div class="custemstykpris"></div>
        <p>Ialt med moms:</p>
        <p>Custom pris:</p>
        <input type="number">
    </div>
</div>
<hr class="solid">

<div class="svgdrawer">
    <h1>Tegning:</h1>
    <div class="SVGContainer"></div>
    <form method="post">
    <button class="GenererSVG" name="GenererSVG" >Generer SVG</button>
    </form>
    ${svgDraw}

</div>
<hr class="solid">

<div class="final">
    <h1>FINAL STUFF:</h1>
    <div class="flairtext">
        <input class="customtext" type="text" >
    </div>
    <div class="finalbutton">
    <button class="SendTilbud" >Send Tilbud</button>
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
