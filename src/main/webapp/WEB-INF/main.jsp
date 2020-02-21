<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="label.welcome" />!</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/><br/>
<c:set scope="request" var="temp" value="${USER}"/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.greeting"/> ${temp.name}</h2>
    </div>
</div>
<br/>
<div class="help">
    <b style="color: #c40000">
<c:out value="${sessionScope.whileAddingBartenderMessage}"/>
<c:remove var="whileAddingBartenderMessage" scope="session" />
<c:out value="${sessionScope.whileAddingUserMessage}"/>
<c:remove var="whileAddingBartenderMessage" scope="session" />
<c:out value="${sessionScope.whileAddingCocktailMessage}"/>
<c:remove var="whileAddingCocktailMessage" scope="session" />
<c:out value="${sessionScope.whileAddingIngredientMessage}"/>
<c:remove var="whileAddingIngredientMessage" scope="session" />
<c:out value="${message}"/>
<c:out value="${defaultMessage}"/>
    </b>
</div>
<br/><br/>
<!--see list of cocktails for user -->
<table class="table-main">
    <tr>
<div class="help" style="margin-bottom: 10px; margin-left: 40px">
    <!-- suggest cocktail for user-->
    <c:if test="${sessionScope.role == 'USER'}">
        <button class="main-button" onclick="location.href='/barback/suggestcocktail'"><fmt:message key="label.suggestcocktail"/></button>
    </c:if>
    <!--create cocktail for bartender-->
    <c:if test="${sessionScope.role == 'BARTENDER'}">
        <button class="main-button" onclick="location.href='/barback/createcocktail'"><fmt:message key="label.createcocktail"/></button>
    </c:if>
<c:if test="${sessionScope.role == 'USER'}">
    <form name="listofcocktails" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktails"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message  key="label.listofcocktails" />
        </button>
    </form>
</c:if>
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <form name="listofcocktails" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktails"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message  key="label.listofcocktails" />
        </button>
    </form>
</c:if>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofcocktails" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktails"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message  key="label.listofcocktails" />
        </button>
    </form>
</c:if>
<!-- list of suggested cocktails for user-->
<c:if test="${sessionScope.role == 'USER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="listofsuggestedcocktails"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.listofsuggestedcocktails"/>
        </button>
    </form>
</c:if>
<!-- list of created cocktails for bartender-->
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="listofcreatedcocktails"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.listofcreatedcocktails"/>
        </button>
    </form>
</c:if>
<!-- logout-->
<c:if test="${sessionScope.role == 'USER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="logout"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.logout"/>
        </button>
    </form>
</c:if>
<!-- logout-->
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="logout"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.logout"/>
        </button>
    </form>
</c:if>
<!--see list of bartenders -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofbartenders" action="controller" method="POST">
        <input type="hidden" name="command" value="listofbartenders"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.listofbartenders"/>
        </button>
    </form>
</c:if>
<!--see list of users -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofusers" action="controller" method="POST">
        <input type="hidden" name="command" value="listofusers"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.listofusers"/>
        </button>
    </form>
</c:if>
<!--see list of ingredients -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofingredients" action="controller" method="POST">
        <input type="hidden" name="command" value="listofingredients"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.listofingredients"/>
        </button>
    </form>
</c:if>
<!--who needs help -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="whoneedshelp" action="controller" method="POST">
        <input type="hidden" name="command" value="whoneedshelp"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.whoneedshelp"/>
        </button>
    </form>
</c:if>
<!--users to change status -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="userstochangestatus" action="controller" method="POST">
        <input type="hidden" name="command" value="userstochangestatus"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.userstochangestatus"/>
        </button>
    </form>
</c:if>
<!--list of cocktails to approve-->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofcocktailstoapprove" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktailstoapprove"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.listofcocktailstoapprove"/>
        </button>
    </form>
</c:if>
<!-- logout-->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="logout"/>
        <button class="main-button" type="submit">
            <fmt:message key="label.logout"/>
        </button>
    </form>
</c:if>
    <br/> <br/>
    <!--delete account user-->
    <c:if test="${sessionScope.role == 'USER'}">
        <button type="button" class="main-button" onclick="location.href='/barback/deleteaccount'"><fmt:message key="label.deleteaccount"/></button>
    </c:if>
    <!--delete account bartender-->
    <c:if test="${sessionScope.role == 'BARTENDER'}">
        <button type="button" class="main-button" onclick="location.href='/barback/deleteaccount'"><fmt:message key="label.deleteaccount"/></button>
    </c:if>
</div>
    </tr>
    <tr>
<jsp:include page="/WEB-INF/myfooter.html"/>
    </tr>
</table>
</body>
</html>