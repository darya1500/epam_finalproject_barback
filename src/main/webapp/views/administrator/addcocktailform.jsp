<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
	<title><fmt:message  key="label.addcocktail" /></title>
</head>
<body>
<jsp:include page="/views/myheader.jsp"/>
<h2><fmt:message  key="label.createcocktail" /></h2>
<form name="createcocktailform" action="controller" method="POST">
	<input type="hidden" name="command" value="createcocktail"/>
	<fmt:message  key="label.nameEN" />:<input type="text" name="nameEN"><br/><br/>
	<fmt:message key="label.nameRU" />:<input type="text" name="nameRU"><br /><br/>
	<c:forEach var = "i" begin = "1" end = "10">
		<div id="ingredient">
			<fmt:message  key="label.ingredientinenglish" />:<input type="text" name="ingredientNameEN${i}">
			<fmt:message  key="label.ingredientinrussian" />:<input type="text" name="ingredientNameRU${i}">
			<fmt:message  key="label.amount" />:<input type="text" name="amount${i}">
			<fmt:message  key="label.description" />:<input type="text" name="description${i}">
			<label>
				<input type="checkbox" name="isAlcohol${i}" value="true" > <fmt:message  key="label.alcohol" />
			</label>
		</div>
		<br>
	</c:forEach>
	<button class="menu-text" type="submit">
		<fmt:message  key="label.submit" />
	</button>
</form>
<a onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></a>
    <jsp:include page="/views/myfooter.html"/>
</body>
</html>










