<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
	<title><fmt:message  key="label.addbartenderform" /></title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/adduserstyle.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
	<div id="wrapper">
		<div id="header">
			<h2><fmt:message  key="label.bartenderslist" /></h2>
		</div>
	</div>
	<div id="container">
	<c:out value="${sessionScope.whileAddingBartenderMessage}"/>
		<c:remove var="whileAddingBartenderMessage" scope="session" />
		<h3><fmt:message  key="label.addbartender" /></h3>
		<form action="controller" method="POST">
			<input type="hidden" name="command" value="addbartender" />
			<table>
				<tbody>
					<tr>
						<td><label><fmt:message key="label.nameEN" />:</label></td>
						<td><input type="text" name="userNameEN" /></td>
					</tr>
					<tr>
						<td><label><fmt:message key="label.nameRU" />:</label></td>
						<td><input type="text" name="userNameRU" /></td>
					</tr>
					<tr>
						<td><label><fmt:message  key="label.email" />:</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
                    	<td><label><fmt:message key="label.password" />:</label></td>
                    	<td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                      <td><label><fmt:message key="label.description" />:</label></td>
                      <td><input type="text" name="description" /></td>
                     </tr>
					<tr>
						<td><label></label></td>
						<td>
							<button class="menu-text" type="submit">
								<fmt:message  key="label.save" />
							</button>
							</td>
					</tr>
				</tbody>
			</table>
		</form>
	<br/>
		<button class="button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
   <jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>