<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <title> <fmt:message key="label.login"/></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.loginform"/></h2>
    </div>
</div></br>
<!-- Login form    -->
<h2 class="login" > <fmt:message key="label.loginusingyour"/></h2>
<div class="help">
<b style="color: #c40000"> <c:out value="${sessionScope.errorLoginPassMessage}"/></b>
<c:remove var="errorLoginPassMessage" scope="session" /><br/><br/>
</div>
<div class="help">
<form name="loginform" action="controller" method="POST">
    <input type="hidden" name="command" value="login"/>
      <fmt:message key="label.email"/> :<input class="help-input"type="text" name="email"><br/><br/>
      <fmt:message key="label.password"/> :<input class="help-input"type="password" name="password"><br><br><br/>
    <button class="menu-text" type="submit">
        <fmt:message key="label.submit"/>
    </button>
</form>
</div>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>