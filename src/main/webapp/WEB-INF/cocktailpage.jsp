<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.cocktailpage"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/w3.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div class="container">
    <c:out value="${message}"/>
    <h2 class="author"><fmt:message key="label.author"/></h2>
    <table class="table table-borderless" id="authortable">
        <thead>
        <tr>
            <th><fmt:message key="label.name"/>: ${AUTHOR.name}</th>
            <th>${AUTHOR.nameRU}</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><fmt:message key="label.role"/>: ${AUTHOR.status}</td>
            <td>
                <span class="stars-container stars-${AUTHOR_RATING}">★★★★★</span>
                <fmt:message key="label.rating"/>
            </td>
            <td><fmt:message key="label.description"/>: ${AUTHOR.description}</td
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="container">
    <h2 class="author"><fmt:message key="label.cocktail"/></h2>
    <table class="table table-borderless" id="cocktailtable">
        <thead>
        <tr>
            <th><fmt:message key="label.name"/>: ${COCKTAIL.nameEN}</th>
            <th>${COCKTAIL.nameRU}</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempComponent" items="${COCKTAIL.components}" varStatus="status">
            <tr>
                <td><fmt:message key="label.ingredient"/>: ${INGREDIENTS[status.index].nameEN} </td>
                <td>${INGREDIENTS[status.index].nameRU} </td>
                <td>${tempComponent.ingredientAmount}</td>
                <td>${tempComponent.description}</td>
            </tr>
        </c:forEach>
        <tr>
            <span class="stars-container stars-${COCKTAIL_RATING}">★★★★★</span>
            <fmt:message key="label.rating"/>
        </tr>
        </tbody>
    </table>
    <br>
    <div class="txt-center">
        <form action="controller" method="POST">
            <div class="rating">
                <input id="star5" name="star" type="radio" value="5" class="radio-btn hide"/>
                <label for="star5">☆</label>
                <input id="star4" name="star" type="radio" value="4" class="radio-btn hide"/>
                <label for="star4">☆</label>
                <input id="star3" name="star" type="radio" value="3" class="radio-btn hide"/>
                <label for="star3">☆</label>
                <input id="star2" name="star" type="radio" value="2" class="radio-btn hide"/>
                <label for="star2">☆</label>
                <input id="star1" name="star" type="radio" value="1" class="radio-btn hide"/>
                <label for="star1">☆</label>
                <div class="clear"></div>
                <input type="hidden" name="command" value="ratecocktail">
                <input type="hidden" name="cocktailID" value="${COCKTAIL.id}">
                <input type="hidden" name="authorID" value="${COCKTAIL.author.id}">
                <button class="ratebutton" type="submit">
                    <fmt:message key="label.ratecocktail"/>
                </button>
            </div>
        </form>
    </div>
</div>
<br>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>