<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <title><fmt:message key="label.register"/></title>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.register"/></h2>
    </div>
</div>
<!-- !PAGE CONTENT! -->
<h2><fmt:message key="label.createnewaccount"/></h2>
<c:out value="${errorMessage}"/>
<form name="registerform" action="controller" method="POST">
    <input type="hidden" name="command" value="register"/>
    <fmt:message key="label.email"/>:<input type="text" name="email" placeholder="Your email.."><br/><br/>
    <fmt:message key="label.password"/>:<input type="password" name="password"
                                               placeholder="at least 6 symbols"><br/><br/>
    <fmt:message key="label.firstname"/>:<input type="text" name="userNameEN" placeholder="Your name..."><br/><br/>
    <fmt:message key="label.nameinrussian"/>:<input type="text" name="userNameRU" placeholder="Your name..."><br/><br/>
    <fmt:message key="label.description"/>:<input type="text" name="description"
                                                  placeholder="Some words about yourself..."><br/><br/>
    <button type="submit"><fmt:message key="label.submit"/></button>
</form>
<button class="button2" onclick="javascript:history.back(); return false;"><fmt:message key="label.back"/></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>