<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.cocktailtoapprovepage"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div class="container">
    <div class="help" style="width: 100%">
        <h2 class="author"><fmt:message key="label.author"/></h2>
        <table class="table table-borderless" id="authortable" style="width: 95%">
            <tr>
                <th>${THE_COCKTAIL.author.name}</th>
            </tr>
        </table>
    </div>
</div>
<div class="container">
    <div class="help" style="width: 100%">
        <h2 class="author"><fmt:message key="label.cocktail"/></h2>
        <table class="table table-borderless" id="cocktailtable" style="width: 95%">
            <tr>
                <th style="width: 20%"><fmt:message key="label.name"/>: ${THE_COCKTAIL.nameEN}</th>
                <th style="width: 20%">${THE_COCKTAIL.nameRU}</th>
                <th style="width: 20%"></th>
                <th></th>
            </tr>
            <tbody>
            <c:forEach var="tempComponent" items="${THE_COCKTAIL.components}" varStatus="status">
                <tr>
                    <td><fmt:message key="label.ingredient"/>: ${INGREDIENTS[status.index].nameEN} </td>
                    <td>${INGREDIENTS[status.index].nameRU} </td>
                    <td>${tempComponent.ingredientAmount}</td>
                    <td>${tempComponent.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="margin-left: 30px">
            <form action="controller" method="POST">
                <input type="hidden" name="command" value="listofcocktailstoapprove"/>
                <input type="hidden" name="operation" value="APPROVE"/>
                <input type="hidden" name="cocktail" value="${THE_COCKTAIL}"/>
                <input type="hidden" name="cocktailID" value="${THE_COCKTAIL.id}"/>
                <button class="back-button" type="submit">
                    <fmt:message key="label.approve"/>
                </button>
            </form>
            <form action="controller" method="POST">
                <input type="hidden" name="command" value="listofcocktailstoapprove"/>
                <input type="hidden" name="operation" value="DELETE"/>
                <input type="hidden" name="cocktailID" value="${THE_COCKTAIL.id}"/>
                <button class="back-button" type="submit" >
                    <fmt:message key="label.delete"/>
                </button>
            </form>
        </div>
        <c:out value="${message}"/>
        <br>
    </div>
    <div/>
    <br>
    <jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>