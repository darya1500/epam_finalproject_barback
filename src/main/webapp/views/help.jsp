<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
 <title><fmt:message key="label.help" /></title>
    <meta charset="UTF-8">
</head>
<body
<jsp:include page="/views/myheader.jsp"/>
<!--help form-->
<form name="HelpForm" action="controller" method="POST">
        <input type="hidden" name="command" value="help"/>
<h2><fmt:message key="label.contactus" /></h2>
<fmt:message key="label.firstname" /> : <input type="text" name="name" ><br /><br />
<fmt:message key="label.email" /> : <input type="text" name="email"><br /><br />
<fmt:message key="label.message" /> : <input type="text" name="message"><br /><br/>
    <button class="menu-text" type="submit">
        <fmt:message key="label.submit"/>
    </button>
</form>
<a onclick="javascript:history.back(); return false;"><fmt:message key="label.back"/></a>
<jsp:include page="/views/myfooter.html"/>
</body>
</html>
