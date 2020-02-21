<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
 <meta charset="UTF-8">
    <title><fmt:message  key="label.listofusers" /></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
	<style>
	.stars-container {
	position: relative;
	display: inline-block;
	color: transparent;
	font-size: 20px;
	}
	.stars-container:before{
	position: absolute;
	top: 0;
	left: 0;
	content: '★★★★★';
	color:lightgray;
	}
	.stars-container:after {
	position: absolute;
	top: 0;
	left: 0;
	content: '★★★★★';
	color: gold;
	overflow: hidden;
	}
	.stars-0:after { width: 0%; }
	.stars-5:after { width: 5%; }
	.stars-10:after { width: 10%; }
	.stars-15:after { width: 15%; }
	.stars-20:after { width: 20%; }
	.stars-25:after { width: 25%; }
	.stars-30:after { width: 30%; }
	.stars-35:after { width: 35%; }
	.stars-40:after { width: 40%; }
	.stars-45:after { width: 45%; }
	.stars-50:after { width: 50%; }
	.stars-55:after { width: 55%; }
	.stars-60:after { width: 60%; }
	.stars-65:after { width: 65%; }
	.stars-70:after { width: 70%; }
	.stars-75:after { width: 75%; }
	.stars-80:after { width: 80%; }
	.stars-85:after { width: 85%; }
	.stars-90:after { width: 90%; }
	.stars-95:after { width: 95%; }
	.stars-100:after { width: 100%;}
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
		<div id="header">
			<h2><fmt:message  key="label.listofusers" /></h2>
		</div>
</div>
<c:out value="${message}"/>
<div id="container">
   <div id="content">
	   <div class="help" style="width: 85%">
	   <!--  Add User button-->
	   <input type="button" class="back-button"style="margin-left: 10px"value="Add User" onclick="window.location.href='/barback/adduserform'"/>
		   <br/><br/>
		   <table style="width: 85%">
			<tr>
				<th><fmt:message key="label.nameEN" /></th>
				<th><fmt:message key="label.nameRU" /></th>
				<th><fmt:message key="label.email" /></th>
				<th><fmt:message key="label.password" /></th>
				<th><fmt:message key="label.description" /></th>
				<th><fmt:message key="label.rating" /></th>
				<th><fmt:message key="label.action" /></th>
				</tr>
                   <c:forEach var="tempUser" items="${USERS}" varStatus="status">
					<tr>
						<td> ${tempUser.name} </td>
						<td> ${tempUser.nameRU}</td>
						<td> ${tempUser.email} </td>
						<td> ${tempUser.password}</td>
						<td> ${tempUser.description}</td>
						<td>
							<span class="stars-container stars-${USER_RATINGS[status.index]}">★★★★★</span>
						</td>
						<td> <form action="controller" method="POST">
							<input type="hidden" name="command" value="updateuserform"/>
							<input type="hidden" name="userID" value="${tempUser.id}" />
							<button class="white-button" type="submit">
								<fmt:message  key="label.update" />
							</button>
						</form>
						</form>
							<form action="controller" method="POST">
								<input type="hidden" name="command" value="listofusers"/>
								<input type="hidden" name="operation" value="DELETE"/>
								<input type="hidden" name="userID" value="${tempUser.id}" />
								<button class="white-button" type="submit">
									<fmt:message  key="label.delete" />
								</button>
							</form>
						</td>
					</tr>
                   </c:forEach>
		</table>
	   </div></div>
</div>
<br/>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>