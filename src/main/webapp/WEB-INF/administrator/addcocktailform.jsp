<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
	<title><fmt:message  key="label.addcocktail" /></title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div id="wrapper">
	<div id="header">
<h2><fmt:message  key="label.createcocktail" /></h2>
		</div></div>
		<div class="help" style="width:40%">
	<c:out value="${sessionScope.whileAddingCocktailMessage}"/>
<c:remove var="whileAddingCocktailMessage" scope="session" />
			<br/><br/>
			<fmt:message  key="label.youcancreatecocktail" />
			<br/><br/>
<form name="createcocktailform" action="controller" method="POST">
	<input type="hidden" name="command" value="createcocktail"/>
	<fmt:message  key="label.nameEN" />:<input class="help-input" type="text" name="nameEN"><br/><br/>
	<fmt:message key="label.nameRU" />:<input class="help-input" type="text" name="nameRU"><br /><br/><br/>
	<c:forEach var = "i" begin = "1" end = "10">
		<div id="ingredient">
			<fmt:message  key="label.ingredientinenglish" />:<input class="help-input" type="text" name="ingredientNameEN${i}"><br/><br/>
			<fmt:message  key="label.ingredientinrussian" />:<input class="help-input"type="text" name="ingredientNameRU${i}"><br/><br/>
			<fmt:message  key="label.amount" />:<input class="help-input"type="text" name="amount${i}"><br/><br/>
			<fmt:message  key="label.description" />:<input class="help-input"type="text" name="description${i}"><br/><br/>
			<label>
				<input type="checkbox" name="isAlcohol${i}" value="true" > <fmt:message  key="label.alcohol" /><br/><br/>
			</label>
		</div>
		<br>
	</c:forEach>
	<button class="menu-text" type="submit">
		<fmt:message  key="label.submit" />
	</button>
</form>
</div>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>











