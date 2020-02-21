<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <title><fmt:message key="label.updatebartender"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.updatebartender"/></h2>
    </div>
</div>
<div id="container">
    <div class="help" style="width: 40%">
		<br/><br/>
        <form action="controller" method="POST">
            <input type="hidden" name="command" value="listofbartenders"/>
            <input type="hidden" name="operation" value="UPDATE"/>
            <input type="hidden" name="userID" value="${THE_BARTENDER.id}"/>
            <fmt:message key="label.nameEN"/>:<input class="help-input" type="text" name="userNameEN" value="${THE_BARTENDER.name}"/><br/><br/>
            <fmt:message key="label.nameRU"/>:<input class="help-input"type="text" name="userNameRU" value="${THE_BARTENDER.nameRU}"/><br/><br/>
            <fmt:message key="label.email"/>:<input class="help-input"type="text" name="email" value="${THE_BARTENDER.email}"/><br/><br/>
            <fmt:message key="label.password"/>:<input class="help-input"type="password" name="password"
                                                       value="${THE_BARTENDER.password}"/><br/><br/>
            <fmt:message key="label.description"/>:<input class="help-input"type="text" name="description"
                                                          value="${THE_BARTENDER.description}"/><br/><br/>
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











