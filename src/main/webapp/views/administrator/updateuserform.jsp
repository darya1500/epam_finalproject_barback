<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
	<title><fmt:message  key="label.updateuser" /></title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/adduserstyle.css">
</head>
<body>
<jsp:include page="/views/myheader.jsp"/>
<br/>
	<div id="wrapper">
		<div id="header">
			<h2><fmt:message  key="label.userslist" /></h2>
		</div>
	</div>
	<div id="container">
		<h3><fmt:message  key="label.updateuser" /></h3>
		<form action="controller" method="POST">
			<input type="hidden" name="command" value="listofusers" />
			<input type="hidden" name="operation" value="UPDATE" />
			<input type="hidden" name="userID" value="${THE_USER.id}" />
			<table>
				<tbody>
					<tr>
                    	<td><label><fmt:message key="label.nameEN" />:</label></td>
                    	<td><input type="text" name="userNameEN" value="${THE_USER.name}"/></td>
                    </tr>
                    <tr>
                    	<td><label><fmt:message key="label.nameRU" />:</label></td>
                    	<td><input type="text" name="userNameRU" value="${THE_USER.nameRU}"/></td>
                    </tr>
                    <tr>
                    	<td><label>Email:</label></td>
                    	<td><input type="text" name="email" value="${THE_USER.email}"/></td>
                    </tr>
                    <tr>
                        <td><label><fmt:message key="label.password" />:</label></td>
                        <td><input type="password" name="password" value="${THE_USER.password}"/></td>
                     </tr>
                      <tr>
                        <td><label><fmt:message key="label.description" />:</label></td>
                         <td><input type="text" name="description" value="${THE_USER.description}"/></td>
                      </tr>
                    <tr>
                    	<td><label></label></td>
                    	<td><button class="menu-text" type="submit">
							<fmt:message  key="label.save" />
						</button>
						</td>
                    </tr>
				</tbody>
			</table>
		</form>
		<a onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></a>
	<br/>
    <jsp:include page="/views/myfooter.html"/>
</body>
</html>











