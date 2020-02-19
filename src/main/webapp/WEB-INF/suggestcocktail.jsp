<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.suggestcocktail"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<h2><fmt:message key="label.suggestcocktail"/></h2>
<form name="suggestcocktailform" action="controller" method="POST">
    <input type="hidden" name="command" value="suggestcocktail"/>
    <fmt:message key="label.nameEN"/>:<input type="text" name="nameEN"><br/><br/>
    <fmt:message key="label.nameRU"/>:<input type="text" name="nameRU"><br/><br/>
    <c:forEach var="i" begin="1" end="10">
        <div id="ingredient">
            <fmt:message key="label.ingredient"/>:<input type="text" name="ingredientName${i}">
            <fmt:message key="label.amount"/>:<input type="text" name="amount${i}">
            <fmt:message key="label.description"/>:<input type="text" name="description${i}">
        </div>
        <br/>
    </c:forEach>
    <button type="submit">
        <fmt:message key="label.submit"/>
    </button>
</form>
<button type="button" class="button2" onclick="history.back(); return false;"><fmt:message key="label.back"/></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>