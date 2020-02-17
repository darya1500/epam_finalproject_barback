<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="label.welcome" />!</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <title><fmt:message key="label.welcome"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/><br/>
${message}
${defaultMessage}
<c:set scope="request" var="temp" value="${USER}"/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.greeting"/> ${temp.name}</h2>
    </div>
</div><br/>
<fmt:message key="label.welcome"/><br/><br/>
<!--see list of cocktails for user -->
<c:if test="${sessionScope.role == 'USER'}">
    <form name="listofcocktails" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktails"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button type="submit">
            <fmt:message  key="label.listofcocktails" />
        </button>
    </form>
</c:if>
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <form name="listofcocktails" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktails"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button type="submit">
            <fmt:message  key="label.listofcocktails" />
        </button>
    </form>
</c:if>
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofcocktails" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktails"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button type="submit">
            <fmt:message  key="label.listofcocktails" />
        </button>
    </form>
</c:if>
<!-- list of suggested cocktails for user-->
<c:if test="${sessionScope.role == 'USER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="listofsuggestedcocktails"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.listofsuggestedcocktails"/>
        </button>
    </form>
</c:if>
<!-- list of created cocktails for bartender-->
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="listofcreatedcocktails"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.listofcreatedcocktails"/>
        </button>
    </form>
</c:if>
<!-- suggest cocktail for user-->
<c:if test="${sessionScope.role == 'USER'}">
    <button onclick="location.href='/barback/suggestcocktail'"><fmt:message key="label.suggestcocktail"/></button><br><br>
</c:if>
<!--create cocktail for bartender-->
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <button onclick="location.href='/barback/createcocktail'"><fmt:message key="label.createcocktail"/></button><br><br>
</c:if>
<!-- logout-->
<c:if test="${sessionScope.role == 'USER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="logout"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.logout"/>
        </button>
    </form>
</c:if>
<!-- logout-->
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="logout"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.logout"/>
        </button>
    </form>
</c:if>
<!--delete account user-->
<c:if test="${sessionScope.role == 'USER'}">
    <button type="button" class="btn" onclick="location.href='/barback/deleteaccount'"><fmt:message key="label.deleteaccount"/></button><br><br>
</c:if>
<!--delete account bartender-->
<c:if test="${sessionScope.role == 'BARTENDER'}">
    <button type="button" class="btn" onclick="location.href='/barback/deleteaccount'"><fmt:message key="label.deleteaccount"/></button><br><br>
</c:if>
<!--see list of bartenders -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofbartenders" action="controller" method="POST">
        <input type="hidden" name="command" value="listofbartenders"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.listofbartenders"/>
        </button>
    </form>
</c:if>
<!--see list of users -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofusers" action="controller" method="POST">
        <input type="hidden" name="command" value="listofusers"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.listofusers"/>
        </button>
    </form>
</c:if>
<!--see list of ingredients -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofingredients" action="controller" method="POST">
        <input type="hidden" name="command" value="listofingredients"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.listofingredients"/>
        </button>
    </form>
</c:if>
<!--who needs help -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="whoneedshelp" action="controller" method="POST">
        <input type="hidden" name="command" value="whoneedshelp"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.whoneedshelp"/>
        </button>
    </form>
</c:if>
<!--users to change status -->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="userstochangestatus" action="controller" method="POST">
        <input type="hidden" name="command" value="userstochangestatus"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.userstochangestatus"/>
        </button>
    </form>
</c:if>
<!--list of cocktails to approve-->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form name="listofcocktailstoapprove" action="controller" method="POST">
        <input type="hidden" name="command" value="listofcocktailstoapprove"/>
        <input type="hidden" name="operation" value="LIST"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.listofcocktailstoapprove"/>
        </button>
    </form>
</c:if>
<!-- logout-->
<c:if test="${sessionScope.role == 'ADMIN'}">
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="logout"/>
        <button class="menu-text" type="submit">
            <fmt:message key="label.logout"/>
        </button>
    </form>
</c:if><br/><br/>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>