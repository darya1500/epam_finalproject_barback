<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.addingredient"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.addingredient"/></h2>
    </div>
</div>
<div id="container">
	<div class="help" style="width: 40%">
		<br/><br/>
    <c:out value="${sessionScope.whileAddingIngredientMessage}"/>
    <c:remove var="whileAddingIngredientMessage" scope="session"/>
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="addingredient"/>
        <fmt:message key="label.ingredientNameEN"/>:<input class="help-input" type="text" name="ingredientNameEN"/></td>	<br/><br/>
        <fmt:message key="label.ingredientNameRU"/>:<input class="help-input"type="text" name="ingredientNameRU"/></td>	<br/><br/>
        <fmt:message key="label.isAlcohol"/>:
        <label>
            <input type="checkbox" name="isAlcohol" value="true"/>
        </label>	<br/><br/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.save"/>
        </button>
    </form>
    <button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message
            key="label.back"/></button>
</div>
<br/>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>











