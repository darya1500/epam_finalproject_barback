<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <title><fmt:message key="label.help"/></title>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body
<jsp:include page="/WEB-INF/myheader.jsp"/>
<!--help form-->
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.contactus"/></h2>
    </div>
</div>
<br/>
<div class="help">
<form name="HelpForm" action="controller" method="POST">
    <input type="hidden" name="command" value="help"/>
    <fmt:message key="label.firstname"/> : <input class="help-input" type="text" name="name"><br/><br/>
    <fmt:message key="label.email"/> : <input class="help-input" type="text" name="email"><br/><br/>
    <div>
    <fmt:message key="label.message"/> :<input class="help-input" type="text" name="message" placeholder="Write message...">
    </div>
    <br/><br/>
    <button class="menu-text" type="submit">
        <fmt:message key="label.submit"/>
    </button>
</form>
</div>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message key="label.back"/></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>