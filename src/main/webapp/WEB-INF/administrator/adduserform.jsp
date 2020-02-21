<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.adduser"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.userslist"/></h2>
    </div>
</div>
<div id="container">
    <div class="help" style="width: 40%">
        <br/><br/>
        <c:out value="${sessionScope.whileAddingUserMessage}"/>
        <c:remove var="whileAddingBartenderMessage" scope="session"/>
        <h3><fmt:message key="label.adduser"/></h3>
        <form action="controller" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="command" value="adduser"/>
            <fmt:message key="label.nameEN"/>:<input class="help-input" type="text" name="userNameEN"/><br/><br/>
            <fmt:message key="label.nameRU"/>:<input class="help-input"type="text" name="userNameRU"/><br/><br/>
            <fmt:message key="label.email"/>:<input class="help-input"type="text" name="email"/><br/><br/>
            <fmt:message key="label.password"/>:<input class="help-input"type="password" name="password"/><br/><br/>
            <fmt:message key="label.description"/>:<input class="help-input"type="text" name="description"/><br/><br/>
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











