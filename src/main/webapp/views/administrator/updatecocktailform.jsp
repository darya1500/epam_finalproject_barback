<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="label.updatecocktail" /></title>
</head>
<body>
<jsp:include page="/views/myheader.jsp"/>
<h3><fmt:message  key="label.updatecocktail" /></h3>
<form action="controller" method="POST">
    <input type="hidden" name="command" value="listofcocktails" />
    <input type="hidden" name="operation" value="UPDATE" />
    <input type="hidden" name="cocktailID" value="${THE_COCKTAIL.id}" />
    <fmt:message key="label.cocktailNameEN" />:<input type="text" name="cocktailNameEN" value="${cocktailNameEN}"/><br/><br/>
    <fmt:message key="label.cocktailNameRU" />:<input type="text" name="cocktailNameRU" value="${cocktailNameRU}"/><br/><br/>
    <fmt:message key="label.authorName" />:   ${authorName}<input type="hidden" name="authorID" value="${authorID}"/><br/><br/>
        <c:forEach var = "i" begin = "1" end ="${components.size()}" varStatus="status">
        <div id="ingredient">
            <label><fmt:message key="label.ingredientNameEN" />:</label>
            <input type="text" name="ingredientNameEN${i}" value="${ingredientNamesEN[status.index-1]}"/>
            <label><fmt:message key="label.ingredientNameRU" />:</label>
            <input type="text" name="ingredientNameRU${i}" value="${ingredientNamesRU[status.index-1]}"/>
                <input type="hidden" name="ingredientID${i}" value="${components[status.index-1].ingredientID}"/>
            <label><fmt:message key="label.ingredientAmount" />:</label>
                <input type="text" name="ingredientAmount${i}" value="${components[status.index-1].ingredientAmount}"/>
            <label><fmt:message key="label.description" />:</label>
               <input type="text" name="description${i}" value="${components[status.index-1].description}"/>
        </div>
            <br>
        </c:forEach>
            <label></label>
    <button class="menu-text" type="submit">
        <fmt:message  key="label.save" />
    </button>
</form>
<a onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></a>
<jsp:include page="/views/myfooter.html"/>
</body>
</html>