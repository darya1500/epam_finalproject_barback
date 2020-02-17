<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
 <meta charset="UTF-8">
	<title> <fmt:message  key="label.addingredient" /></title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/adduserstyle.css">
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/><br/>
	<div id="wrapper">
		<div id="header">
			<h2><fmt:message  key="label.ingredientslist" /></h2>
		</div>
	</div>
	<div id="container">
		<h3><fmt:message  key="label.addingredient" /></h3>
		<form action="controller" method="POST">
			<input type="hidden" name="command" value="listofingredients" />
			<input type="hidden" name="operation" value="ADD" />
			<table>
				<tbody>
				<tr>
                	<td><label><fmt:message key="label.ingredientNameEN" />:</label></td>
                	<td><input type="text" name="ingredientNameEN" /></td>
                </tr>
                <tr>
                	<td><label><fmt:message key="label.ingredientNameRU" />:</label></td>
                	<td><input type="text" name="ingredientNameRU" /></td>
                </tr>
                <tr>
                	<td><label><fmt:message key="label.isAlcohol" />:</label></td>
                	<td>
						<label>
							<input type="checkbox" name="isAlcohol" value="true"/>
						</label>
					</td>
                </tr>
					<tr>
						<td><label></label></td>
						<td>
							<button class="menu-text" type="submit">
								<fmt:message  key="label.save" />
							</button>
							<</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<button class="button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
	</div>
	<br/>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>











