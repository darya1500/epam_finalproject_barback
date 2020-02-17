<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="label.listofcocktailstoapprove" /></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <style>
        input[type=submit] {
            background-color: #ffffff;
            border: none;
            color: blue;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message  key="label.listofcocktailstoapprove" /></h2>
    </div>
</div>
${SUCCESS_MESSAGE}
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th><fmt:message key="label.cocktailNameEN"/></th>
                <th><fmt:message key="label.cocktailNameRU"/></th>
                <th><fmt:message key="label.author"/></th>
            </tr>
            <c:forEach var="tempCocktail" items="${COCKTAILS}" varStatus="status">
                <tr>
                    <td>
                        <form name="listofcocktailstoapprove" action="controller" method="POST">
                            <input type="hidden" name="command" value="listofcocktailstoapprove"/>
                            <input type="hidden" name="operation" value="LOAD"/>
                            <input type="hidden" name="cocktailID" value="${tempCocktail.id}"/>
                            <input type="submit" value="${tempCocktail.nameEN}"/>
                        </form>
                    </td>
                    <td> ${tempCocktail.nameRU} </td>
                    <td> ${tempCocktail.author.name}</td>
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