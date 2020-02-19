<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.barback"/></title>
    <link type="text/css" rel="stylesheet" href="css/w3.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<!-- !PAGE CONTENT! -->
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:20px">
    <!-- First Photo Grid-->
    <div class="w3-row-padding w3-padding-16 w3-center" id="drinks">
        <div class="w3-quarter">
            <img src="images/mojito.jpg" alt="Mohito" style="width:100%">
            <h3><fmt:message key="label.therealmojitorecipe"/></h3>
            <p><fmt:message key="label.text1"/></p>
        </div>
        <div class="w3-quarter">
            <img src="images/aperol.jpg" alt="Aperol" style="width:100%">
            <h3><fmt:message key="label.aperolspritz"/></h3>
            <p><fmt:message key="label.text2"/></p>
        </div>
        <div class="w3-quarter">
            <img src="images/margarita.jpg" alt="Margarita" style="width:100%">
            <h3><fmt:message key="label.31margaritarecipestoenjoy"/></h3>
            <p><fmt:message key="label.text3"/></p>
        </div>
        <div class="w3-quarter">
            <img src="images/nonalco.jpg" alt="Non alcoholic" style="width:100%">
            <h3><fmt:message key="label.nonalcoholiccocktailrecipes"/></h3>
            <p><fmt:message key="label.text4"/></p>
        </div>
    </div>
    <!-- Second Photo Grid-->
    <div class="w3-row-padding w3-padding-16 w3-center">
        <div class="w3-quarter">
            <img src="images/mulledwine.jpg" alt="Mulled wine" style="width:100%">
            <h3><fmt:message key="label.gluhwein"/></h3>
            <p><fmt:message key="label.text5"/></p>
        </div>
        <div class="w3-quarter">
            <img src="images/pineapple.jpg" style="width:100%">
            <h3><fmt:message key="label.pinneapple"/></h3>
            <p><fmt:message key="label.text6"/></p>
        </div>
        <div class="w3-quarter">
            <img src="images/oldfashioned.jpg" alt="Old fashioned" style="width:100%">
            <h3><fmt:message key="label.mostpopularcocktail"/></h3>
            <p><fmt:message key="label.text7"/></p>
        </div>
        <div class="w3-quarter">
            <img src="images/negroni.jpg" alt="Negroni" style="width:100%">
            <h3><fmt:message key="label.bestnegronirecipe"/></h3>
            <p><fmt:message key="label.text8"/></p>
        </div>
    </div>
    <hr id="about">
    <!-- About Section -->
    <div class="w3-container w3-padding-32 w3-center">
        <h3><fmt:message key="label.aboutbarbackteam"/></h3><br>
        <div class="w3-padding-32">
            <h4><b><fmt:message key="label.happytoshare"/></b></h4>
            <p><fmt:message key="label.text9"/></p>
        </div>
    </div>
    <hr>
    <br/><br/>
    <jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>
