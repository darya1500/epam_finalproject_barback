<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
    <title><fmt:message  key="label.whoneedshelp" /></title>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message  key="label.whoneedshelp" /></h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th><fmt:message key="label.name" /></th>
                <th><fmt:message key="label.email" /></th>
                <th><fmt:message key="label.message" /></th>
                <th><fmt:message key="label.status" /></th>
            </tr>
            <c:forEach var="tempHelp" items="${THE_LIST}">
                <tr>
                    <td> ${tempHelp.name} </td>
                    <td> ${tempHelp.email}</td>
                    <td> ${tempHelp.message} </td>
                    <td> <form action="controller" method="POST">
                        <input type="hidden" name="command" value="helped"/>
                        <input type="hidden" name="helpID" value="${tempHelp.id}" />
                        <button class="menu-text" type="submit">
                            <fmt:message  key="label.helped" />
                        </button>
                    </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<br/>
<button class="button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>