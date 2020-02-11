<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
    body, h1, h2, h3, h4, h5, h6 {
        font-family: "Karma", sans-serif
    }
    .w3-bar-block .w3-bar-item {
        padding: 20px
    }
</style>
<head>
    <meta charset="UTF-8">
    <title> <fmt:message key="label.barback"/></title>
</head>
<body>
<jsp:include page="/views/myheader.jsp"/>
<!-- !PAGE CONTENT! -->
<div class="w3-main w3-content w3-padding" style="max-width:1200px;margin-top:100px">
    <!-- First Photo Grid-->
    <div class="w3-row-padding w3-padding-16 w3-center" id="drinks">
        <div class="w3-quarter">
            <img src="/barback/views/images/mojito.jpg" alt="Mohito" style="width:100%">
            <h3><fmt:message key="label.therealmojitorecipe"/></h3>
            <p><fmt:message key="label.text1"/></p>
        </div>
        <div class="w3-quarter">
            <img src="/barback/views/images/aperol.jpg" alt="Aperol" style="width:100%">
            <h3><fmt:message key="label.aperolspritz"/></h3>
            <p><fmt:message key="label.text2"/></p>
        </div>
        <div class="w3-quarter">
            <img src="/barback/views/images/margarita.jpg" alt="Margarita" style="width:100%">
            <h3><fmt:message key="label.31margaritarecipestoenjoy"/></h3>
            <p><fmt:message key="label.text3"/></p>
        </div>
        <div class="w3-quarter">
            <img src="/barback/views/images/nonalco.jpg" alt="Non alcoholic" style="width:100%">
            <h3><fmt:message key="label.nonalcoholiccocktailrecipes"/></h3>
            <p><fmt:message key="label.text4"/></p>
        </div>
    </div>
    <!-- Second Photo Grid-->
    <div class="w3-row-padding w3-padding-16 w3-center">
        <div class="w3-quarter">
            <img src="/barback/views/images/mulledwine.jpg" alt="Mulled wine" style="width:100%">
            <h3><fmt:message key="label.gluhwein"/></h3>
            <p><fmt:message key="label.text5"/></p>
        </div>
        <div class="w3-quarter">
            <img src="/barback/views/images/pineapple.jpg" style="width:100%">
            <h3><fmt:message key="label.pinneapple"/></h3>
            <p><fmt:message key="label.text6"/></p>
        </div>
        <div class="w3-quarter">
            <img src="/barback/views/images/oldfashioned.jpg" alt="Old fashioned" style="width:100%">
            <h3><fmt:message key="label.mostpopularcocktail"/></h3>
            <p><fmt:message key="label.text7"/></p>
        </div>
        <div class="w3-quarter">
            <img src="/barback/views/images/negroni.jpg" alt="Negroni" style="width:100%">
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
    <jsp:include page="/views/myfooter.html"/>
</body>
</html>
