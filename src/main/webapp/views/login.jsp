<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels"/>
<html>
<head>
    <title> <fmt:message key="label.login"/></title>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="/views/myheader.jsp"/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message key="label.loginform"/></h2>
    </div>
</div></br>
<!-- Login form    -->
<h2> <fmt:message key="label.loginusingyour"/></h2>
<c:set var="message" value="${errorMessage}"/>
${message}</br>
<form name="loginform" action="controller" method="POST">
    <input type="hidden" name="command" value="login"/>
      <fmt:message key="label.email"/> :<span style="padding: 0px 30px;">&nbsp;</span><input type="text" name="email"><br/><br/>
      <fmt:message key="label.password"/> :<span style="padding: 0px 20px;">&nbsp;</span><input type="password" name="password"><br><br>
    ${errorLoginPassMessage}
    ${wrongAction}
    ${nullPage}
    <br/>
    <button class="menu-text" type="submit">
        <fmt:message key="label.submit"/>
    </button>
</form>
<a onclick="javascript:history.back(); return false;"><fmt:message key="label.back"/></a>
<jsp:include page="/views/myfooter.html"/>
</body>
</html>