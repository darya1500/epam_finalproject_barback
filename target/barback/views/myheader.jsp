<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<style>
    input[type=submit] {
        background-color: #ffffff;
        border: none;
        color: #0b013b;
        padding: 16px 32px;
        text-decoration: none;
        margin: 4px 2px;
        cursor: pointer;
    }
body,h1,h2,h3,h4,h5,h6 {font-family: "Karma", sans-serif}
.w3-bar-block .w3-bar-item {padding:20px}
</style>
<head>
 <meta charset="UTF-8">
</head>
<body>
<!-- Sidebar-left-menu (hidden by default) -->
<nav class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:40%;min-width:300px" id="mySidebar">
<a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button"><fmt:message key="label.close" /></a>
<c:if test="${sessionScope.role != 'BARTENDER'&& sessionScope.role != 'USER'&& sessionScope.role != 'ADMIN'}">
<a href="#login" onclick="location.href='/barback/login'" class="w3-bar-item w3-button"><fmt:message key="label.login" /></a></c:if>
<a href="#mainpage" onclick="location.href='/barback/index'" class="w3-bar-item w3-button"><fmt:message key="label.mainpage"  /></a>
<c:if test="${sessionScope.role == 'BARTENDER'}">
<a href="#login" onclick="location.href='/barback/main'" class="w3-bar-item w3-button"><fmt:message key="label.mypage" /></a></c:if>
<c:if test="${sessionScope.role == 'USER'}">
<a href="#login" onclick="location.href='/barback/main'" class="w3-bar-item w3-button"><fmt:message key="label.mypage" /></a></c:if>
<c:if test="${sessionScope.role == 'ADMIN'}">
<a href="#login" onclick="location.href='/barback/main'" class="w3-bar-item w3-button"><fmt:message key="label.mypage" /></a></c:if>
<c:if test="${sessionScope.role != 'BARTENDER'&& sessionScope.role != 'USER'&& sessionScope.role != 'ADMIN'}">
<a href="#login" onclick="location.href='/barback/register'" class="w3-bar-item w3-button"><fmt:message key="label.register" /></a></c:if>
<c:if test="${sessionScope.role != 'ADMIN'}">
    <a href="#help" onclick="location.href='/barback/help'" class="w3-bar-item w3-button"><fmt:message key="label.help"/></a>
</c:if>
</nav>
<!-- Top menu -->
<div class="w3-top">
    <div class="w3-white w3-xlarge" style="margin:auto">
        <div class="w3-button w3-padding-16 w3-left" onclick="w3_open()"><fmt:message  key="label.menu" /></div>
        <div class="w3-center w3-padding-16"><fmt:message  key="label.barback" /></div>
    </div>
</div>
<!--Language selection menu-->
<br/><br/><br/><br/>
<div class="btn-group">
<form action="controller" method="POST">
    <input type="hidden" name="command" value="LOCALE" />
    <input type="hidden" name="locale" value="ru_RU" />
    <button type="submit">
        <fmt:message  key="label.russian" />
    </button>
</form>
<form action="controller" method="POST">
    <input type="hidden" name="command" value="LOCALE" />
    <input type="hidden" name="locale" value="en_GB" />
    <button type="submit">
        <fmt:message  key="label.english" />
    </button>
</form>
</div>
<script>
// Script to open and close sidebar
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
}
function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
}
</script>
</body>
</html>
