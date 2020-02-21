<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
	<title><fmt:message  key="label.updateuser" /></title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
	<div id="wrapper">
		<div id="header">
			<h2><fmt:message  key="label.userslist" /></h2>
		</div>
	</div>
	<div id="container">
		<div class="help" style="width: 40%">
			<br/><br/>
		<h3><fmt:message  key="label.updateuser" /></h3>
		<form action="controller" method="POST">
			<input type="hidden" name="command" value="listofusers" />
			<input type="hidden" name="operation" value="UPDATE" />
			<input type="hidden" name="userID" value="${THE_USER.id}" />
			<fmt:message key="label.nameEN" />:<input class="help-input"type="text" name="userNameEN" value="${THE_USER.name}"/><br/><br/>
             <fmt:message key="label.nameRU" />:<input class="help-input"type="text" name="userNameRU" value="${THE_USER.nameRU}"/><br/><br/>
                   Email:<input class="help-input"type="text" name="email" value="${THE_USER.email}"/><br/><br/>
                   <fmt:message key="label.password" />:<input class="help-input"type="password" name="password" value="${THE_USER.password}"/><br/><br/>
                    <fmt:message key="label.description" />:<input class="help-input"type="text" name="description" value="${THE_USER.description}"/><br/><br/>
                     <button class="menu-text" type="submit">
							<fmt:message  key="label.save" />
						</button>
		</form>
		</div>
		<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
	<br/>
    <jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>











