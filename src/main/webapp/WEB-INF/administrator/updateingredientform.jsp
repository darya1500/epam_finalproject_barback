<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <title><fmt:message key="label.updateingredient"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.updateingredient"/></h2>
    </div>
</div>
<div id="container">
    <div class="help" style="width: 40%">
        <br/><br/>
        <form action="controller" method="POST">
            <input type="hidden" name="command" value="listofingredients"/>
            <input type="hidden" name="operation" value="UPDATE"/>
            <input type="hidden" name="ingredientID" value="${THE_INGREDIENT.id}"/>
            <fmt:message key="label.ingredientNameEN"/>:<input class="help-input" type="text" name="ingredientNameEN"
                                                               value="${THE_INGREDIENT.nameEN}"/><br/><br/>
            <fmt:message key="label.ingredientNameRU"/>:<input class="help-input" type="text" name="ingredientNameRU"
                                                               value="${THE_INGREDIENT.nameRU}"/><br/><br/>
            <fmt:message key="label.isAlcohol"/>:
            <label>
                <input type="checkbox" name="isAlcohol" value="true"/>
            </label>
            <button class="menu-text" type="submit">
                <fmt:message key="label.save"/>
            </button>
        </form>
    </div>
    <button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message
            key="label.back"/></button>
    <br/>
    <jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>











