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
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div style="margin-left: 30px;font-family: sans-serif;font-size: larger;font-weight: bolder"> <fmt:message  key="label.areyousure" />
    <br/><br/>
    <form action="controller" method="POST">
        <input type="hidden" name="command" value="deleteaccount" />
        <button class="menu-text" style="font-weight: normal" type="submit">
            <fmt:message  key="label.deleteaccount" />
        </button>
    </form>
</div>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>