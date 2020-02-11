<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="label.deleteaccount" /></title>
</head>
<body>
<jsp:include page="/views/myheader.jsp"/>
<fmt:message  key="label.areyousure" />
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="deleteaccount" />
        <button class="menu-text" type="submit">
            <fmt:message  key="label.deleteaccount" />
        </button>
    </form>
<a onclick="history.back(); return false;"><fmt:message  key="label.back" /></a>
<jsp:include page="/views/myfooter.html"/>
</body>
</html>