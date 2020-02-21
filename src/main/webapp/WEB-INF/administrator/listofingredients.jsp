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
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
		<div id="header">
			<h2><fmt:message  key="label.listofingredients" /></h2>
		</div>
</div>
<c:out value="${message}"/>
<div id="container">
   <div id="content">
       <div class="help" style="width: 85%">
   <!--  Add Ingredient button-->
       <input type="button" value="Add Ingredient" onclick="window.location.href='/barback/addingredientform'"
              style="margin-left: 10px" class="back-button"/>
           <br/><br/>
		<table style="width: 85%">
			<tr>
					<th><fmt:message key="label.ingredientNameEN" /></th>
					<th><fmt:message key="label.ingredientNameRU" /></th>
					<th><fmt:message key="label.isAlcohol" /></th>
                    <th><fmt:message key="label.action" /></th>
				</tr>
                   <c:forEach var="tempIngredient" items="${INGREDIENTS}">
					<tr>
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
                            <button class="white-button" type="submit">
                                <fmt:message  key="label.update" />
                            </button>
                        </form>
                            <form action="controller" method="POST">
                                <input type="hidden" name="command" value="listofingredients"/>
                                <input type="hidden" name="operation" value="DELETE"/>
                                <input type="hidden" name="ingredientID" value="${tempIngredient.id}" />
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