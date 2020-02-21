<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <title><fmt:message key="label.listofcocktails"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">

</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.cocktailslist"/></h2>
    </div>
</div>
<c:out value="${message}"/>
<div id="container">
    <div id="content">
        <div class="help" style="width: 85%">
        <!--  Add Cocktail button-->
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <input type="button" value="Add Cocktail"style="margin-left: 10px"
                   onclick="window.location.href='/barback/addcocktailform'; return false;" class="back-button"/>
            <br/><br/>
        </c:if>
        <table style="width: 85%">
            <tr>
                <th><fmt:message key="label.cocktailNameEN"/></th>
                <th><fmt:message key="label.cocktailNameRU"/></th>
                <th><fmt:message key="label.author"/></th>
                <th><fmt:message key="label.rating"/></th>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <th><fmt:message key="label.action"/></th>
                </c:if>
            </tr>
            <c:forEach var="tempCocktail" items="${COCKTAILS}" varStatus="status">
                <tr>
                    <td>
                        <form name="cocktailpageform" action="controller" method="POST">
                            <input type="hidden" name="command" value="cocktailpage"/>
                            <input type="hidden" name="cocktailID" value="${tempCocktail.id}"/>
                            <input type="submit" value="${tempCocktail.nameEN}"/>
                        </form>
                    </td>
                    <td> ${tempCocktail.nameRU} </td>
                    <td> ${tempCocktail.author.name}</td>
                    <td><span class="stars-container stars-${COCKTAILS_RATINGS[status.index]}">★★★★★</span></td>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <td>
                            <form action="controller" method="POST">
                                <input type="hidden" name="command" value="updatecocktailform"/>
                                <input type="hidden" name="cocktailID" value="${tempCocktail.id}"/>
                                <button class="white-button" type="submit">
                                    <fmt:message key="label.update"/>
                                </button>
                            </form>
                            <form action="controller" method="POST">
                                <input type="hidden" name="command" value="listofcocktails"/>
                                <input type="hidden" name="operation" value="DELETE"/>
                                <input type="hidden" name="cocktailID" value="${tempCocktail.id}"/>
                                <button class="white-button" type="submit">
                                    <fmt:message key="label.delete"/>
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        </div></div>
</div>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<br/>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>