<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title><fmt:message key="label.errorpage"/></title>
</head>
<body>
<h1 class="title"><fmt:message key="label.thatpagedoesntexist"/>
</h1><fmt:message key="label.requestfrom"/> ${pageContext.errorData.requestURI} <fmt:message key="label.hasfailed"/>
<br/>
<fmt:message key="label.servletnameortype"/>: ${pageContext.errorData.servletName}
<br/>
<fmt:message key="label.statuscode"/>: ${pageContext.errorData.statusCode}
<br/>
<fmt:message key="label.exception"/>: ${pageContext.errorData.throwable}
<a onclick="javascript:history.back(); return false;"><fmt:message key="label.back"/></a>
</body>
</html>