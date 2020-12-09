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
    <script>
        function myFunction() {
            var checkBox = document.getElementById("gridCheck");
            var text = document.getElementById("text");
            if (checkBox.checked == true){
                text.style.display = "block";
                $("#text").removeClass('d-none');
            } else {
                text.style.display = "none";
                $("#text").addClass('d-none');
            }
        }

    </script>
    <title>Carporte</title>
    <div class="HeaderImage">
        <img class="headerImage" src="https://fut.dk/wp-content/uploads/job-manager-uploads/company_logo/Fog_100_logo-1024x512.jpg">
    </div>
</head>
<body>
<script>
    var slider = document.getElementById("customRange3");
    var output = document.getElementById("demo");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        output.innerHTML = this.value;

    }
</script>
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
<div id="wrapper">
        <h1>Bestil din skrædersyet carport her!</h1>
        <h3>Carporte med fladt tag:</h3>
        <p><Strong>Carport bredde:</strong></p>
        </table>
    <form method="post">
    <input required type="range" class="form-control-range" value="240" min="240" max="750" step="30" id="customRange3" name="CarportWidth" oninput="amount.value=customRange3.value">
            <input disabled="disabled" class="outputshowcase" id="amount" type="number" value="240" min="240" max="750" oninput="customRange3.value=amount.value" />
            <p><Strong>Carport længde:</strong></p>
            <input required type="range" class="form-control-range" value="240" min="240" max="780" step="30" id="customRange4" name="CarportLength" oninput="amount2.value=customRange4.value">
            <input disabled="disabled" class="outputshowcase" id="amount2" type="number" value="240" min="240" max="780" oninput="customRange4.value=amount2.value" />
        <p><Strong>Carport tag:</Strong></p>
            <div class="Carport-tag">
                <div class="row div-wrapper d-flex justify-content-center align-items-center">
                   <div class="column">
                <input type="radio" id="myRadiobox1" name="radiobox" />
                       <label for="myRadiobox1"><img src="https://imgur.com/aQmIU5H.jpg" /></label>
                   </div>
                    <div class="column">
                <input type="radio" id="myRadiobox2" name="radiobox" />
                <label for="myRadiobox2"><img src="https://imgur.com/RLPA3wc.jpg" /></label>
                    </div>
                </div>
        <h2>Redskabsrum:</h2>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="gridCheck" onclick="myFunction()">
                <label class="form-check-label" for="gridCheck">
                    Ja tak
                </label>
            </div>
        </div>
    <div class="d-none" style="desplay:none" id="text">
        <p>NB! Der skal beregnes 15 cm tagudhæng på hver side af redskabsrummet*</p>
        <p><Strong>Redskabsrum bredde:</Strong></p>

            <div class="Redskabsrum-bredde">
                <input required type="range" class="form-control-range" value="240" min="240" max="780" step="30" id="customRange5" name="ShedWidth" oninput="amount3.value=customRange5.value">
                <input disabled="disabled" class="outputshowcase" id="amount3" type="number" value="240" min="240" max="780" oninput="customRange5.value=amount3.value" />

            </div>
        <p><Strong>Redskabsrum-længde:</Strong></p>
            <div class="Redskabsrum-længde">
                    <input required type="range" class="form-control-range" value="240" min="240" max="780" step="30" id="customRange6" name="ShedLength" oninput="amount4.value=customRange6.value">
                    <input disabled="disabled" class="outputshowcase" id="amount4" type="number" value="240" min="240" max="780" oninput="customRange6.value=amount4.value" />
    </div>
            </div>
                <div class="svgshowcase">
                    <p style="text-align: center"><strong>DIN CARPORTS MÅL:</strong></p>
                    <div align="center">
                        <svg width="100" height="100">
                            <rect x="0" y="0" height="90" width="90"
                                  style="stroke:#000000; fill: #ff0000"/>
                            Sorry, your browser does not support inline SVG.
                        </svg>
                        <p>THIS WILL BE A BUTTON THAT WILL SHOW THE CARPORT ON A DIFFRENT PAGE MAYBE?</p>
                    </div>
                </div>
    </div>
    <div align="center" class="container2" >
            <div class="form-row  div-wrapper d-flex justify-content-center">
                <div class="form-group col-md-4">
                    <label for="Email"><strong>Email</strong></label>
                    <input type="email" class="form-control" name="Email" id="Email" placeholder="Email">
                </div>
                <div class="form-group col-md-4">
                    <label for="phoneNR"><strong>TLF NR</strong></label>
                    <input type="number" class="form-control" name="phoneNR" id="phoneNR" placeholder="TLFNR">
                </div>
            </div>
            <div class="form-row  div-wrapper d-flex justify-content-center align-items-center">
                <div class="form-group col-md-4">
                    <label for="inputAddress"><strong>Address</strong></label>
                    <input type="text" class="form-control" name="inputAddress" id="inputAddress" placeholder="Nørrebrogade">
                 </div>
                 <div class="form-group col-md-4">
                     <label for="address"><strong>Address 2</strong></label>
                     <input type="text" class="form-control" name="adress" id="address" placeholder="Opgang, studie, eller sal">
                </div>
            </div>
            <div class="form-row  div-wrapper d-flex justify-content-center align-items-center">
                <div class="form-group col-md-2">
                    <label for="by"><strong>By</strong></label>
                    <input type="text" class="form-control" name="by" id="by" placeholder="By">
                </div>
                <div class="form-group col-md-2">
                    <label for="postnummer"><strong>Postnummer</strong></label>
                    <input type="text" class="form-control" id="postnummer"  name="postnummer" placeholder="Postnummer">
                </div>
            </div>

            <button type="submit" name="submitQ" class="btn btn-primary">Send forespørgsel</button>
        <hr class="solid">
        <hr class="solid">
        <hr class="solid">


    </div>
        </form>



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
