<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
    <title><fmt:message  key="label.listofingredients" /></title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/views/myheader.jsp"/>
<br/>
<div id="wrapper">
		<div id="header">
			<h2><fmt:message  key="label.listofingredients" /></h2>
		</div>
</div>
${message}
<div id="container">
   <div id="content">
   <!--  Add Ingredient button-->
       <input type="button" value="Add Ingredient" onclick="window.location.href='/barback/addingredientform'"
              class="add-ingredient-button"/>
		<table>
			<tr>
					<th><fmt:message key="label.id" /></th>
					<th><fmt:message key="label.ingredientNameEN" /></th>
					<th><fmt:message key="label.ingredientNameRU" /></th>
					<th><fmt:message key="label.isAlcohol" /></th>
                    <th><fmt:message key="label.action" /></th>
				</tr>
                   <c:forEach var="tempIngredient" items="${INGREDIENTS}">
					<tr>
						<td> ${tempIngredient.id} </td>
						<td> ${tempIngredient.nameEN} </td>
						<td> ${tempIngredient.nameRU}</td>
						<td>
                            <c:if test="${tempIngredient.alcohol}">
                            <p>✓<p>
                            </c:if>
                        </td>
                        <td> <form action="controller" method="POST">
                            <input type="hidden" name="command" value="updateingredientform"/>
                            <input type="hidden" name="ingredientID" value="${tempIngredient.id}" />
                            <button class="menu-text" type="submit">
                                <fmt:message  key="label.update" />
                            </button>
                        </form>
                            <form action="controller" method="POST">
                                <input type="hidden" name="command" value="listofingredients"/>
                                <input type="hidden" name="operation" value="DELETE"/>
                                <input type="hidden" name="ingredientID" value="${tempIngredient.id}" />
                                <button class="menu-text" type="submit">
                                    <fmt:message  key="label.delete" />
                                </button>
                            </form>
                           </td>
					</tr>
                   </c:forEach>
		</table>
	</div>
</div>
<br/>
<a onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></a>
<jsp:include page="/views/myfooter.html"/>
</body>
</html>