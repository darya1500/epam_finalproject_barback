<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="label.updatecocktail" /></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div id="wrapper">
    <div id="header">
<h3><fmt:message  key="label.updatecocktail" /></h3>
    </div></div>
<div class="help" style="width: 40%">
    <br/><br/>
<form action="controller" method="POST">
    <input  type="hidden" name="command" value="listofcocktails" />
    <input type="hidden" name="operation" value="UPDATE" />
    <input type="hidden" name="cocktailID" value="${THE_COCKTAIL.id}" />
    <fmt:message key="label.cocktailNameEN" />:<input class="help-input" type="text" name="cocktailNameEN" value="${cocktailNameEN}"/><br/><br/>
    <fmt:message key="label.cocktailNameRU" />:<input class="help-input" type="text" name="cocktailNameRU" value="${cocktailNameRU}"/><br/><br/>
    <fmt:message key="label.authorName" />:   ${authorName}<input type="hidden" name="authorID" value="${authorID}"/><br/><br/>
        <c:forEach var = "i" begin = "1" end ="${components.size()}" varStatus="status">
        <div id="ingredient">
            <label><fmt:message key="label.ingredientNameEN" />:</label>
            <input class="help-input" type="text" name="ingredientNameEN${i}" value="${ingredientNamesEN[status.index-1]}"/><br/><br/>
            <label><fmt:message key="label.ingredientNameRU" />:</label>
            <input class="help-input" type="text" name="ingredientNameRU${i}" value="${ingredientNamesRU[status.index-1]}"/><br/><br/>
                <input type="hidden" name="ingredientID${i}" value="${components[status.index-1].ingredientID}"/>
            <label><fmt:message key="label.ingredientAmount" />:</label>
                <input class="help-input" type="text" name="ingredientAmount${i}" value="${components[status.index-1].ingredientAmount}"/><br/><br/>
            <label><fmt:message key="label.description" />:</label>
               <input class="help-input" type="text" name="description${i}" value="${components[status.index-1].description}"/><br/><br/>
        </div>
            <br>
        </c:forEach>
            <label></label>
    <button class="menu-text" type="submit">
        <fmt:message  key="label.save" />
    </button>
</form>
</div>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>