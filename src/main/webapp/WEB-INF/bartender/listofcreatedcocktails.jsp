<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="i18n.resources.mylabels" />
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="label.listofcreatedcocktails" /></title>
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
        .txt-center {
            text-align: center;
        }

        .hide {
            display: none;
        }

        .clear {
            float: none;
            clear: both;
        }

        .rating {
            width: 90px;
            unicode-bidi: bidi-override;
            direction: rtl;
            text-align: center;
            position: relative;
        }

        .rating > label {
            float: right;
            display: inline;
            padding: 0;
            margin: 0;
            position: relative;
            width: 1.1em;
            cursor: pointer;
            color: #000;
        }

        .rating > label:hover,
        .rating > label:hover ~ label,
        .rating > input.radio-btn:checked ~ label {
            color: transparent;
        }

        .rating > label:hover:before,
        .rating > label:hover ~ label:before,
        .rating > input.radio-btn:checked ~ label:before,
        .rating > input.radio-btn:checked ~ label:before {
            content: "\2605";
            position: absolute;
            left: 0;
            color: #FFD700;
        }

        #authortable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #dfb4b4;
            color: white;
        }

        #cocktailtable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #b4dfdf;
            color: white;
        }

        .stars-container {
            position: relative;
            display: inline-block;
            color: transparent;
            font-size: 20px;
        }

        .stars-container:before {
            position: absolute;
            top: 0;
            left: 0;
            content: '★★★★★';
            color: lightgray;
        }

        .stars-container:after {
            position: absolute;
            top: 0;
            left: 0;
            content: '★★★★★';
            color: gold;
            overflow: hidden;
        }

        .stars-0:after {
            width: 0%;
        }

        .stars-5:after {
            width: 5%;
        }

        .stars-10:after {
            width: 10%;
        }

        .stars-15:after {
            width: 15%;
        }

        .stars-20:after {
            width: 20%;
        }

        .stars-25:after {
            width: 25%;
        }

        .stars-30:after {
            width: 30%;
        }

        .stars-35:after {
            width: 35%;
        }

        .stars-40:after {
            width: 40%;
        }

        .stars-45:after {
            width: 45%;
        }

        .stars-50:after {
            width: 50%;
        }

        .stars-55:after {
            width: 55%;
        }

        .stars-60:after {
            width: 60%;
        }

        .stars-65:after {
            width: 65%;
        }

        .stars-70:after {
            width: 70%;
        }

        .stars-75:after {
            width: 75%;
        }

        .stars-80:after {
            width: 80%;
        }

        .stars-85:after {
            width: 85%;
        }

        .stars-90:after {
            width: 90%;
        }

        .stars-95:after {
            width: 95%;
        }

        .stars-100:after {
            width: 100%;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/myheader.jsp"/>
<br/>
<div id="wrapper">
    <div id="header">
        <h2><fmt:message  key="label.listofcreatedcocktails" /></h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th><fmt:message key="label.cocktailNameEN" /></th>
                <th><fmt:message key="label.cocktailNameRU" /></th>
                <th style="width: 30%"><fmt:message key="label.rating" /></th>
            </tr>
            <c:forEach var="tempCocktail" items="${COCKTAILS}" varStatus="status">
            <tr>
                <td>
                    <form name="cocktailpageform" action="controller" method="POST">
                        <input type="hidden" name="command" value="cocktailpage"/>
                        <input type="hidden" name="cocktailID" value="${tempCocktail.id}" />
                        <input type="submit" value="${tempCocktail.nameEN}"/>
                    </form>
                </td>
    <td> ${tempCocktail.nameRU} </td>
                <td><span class="stars-container stars-${COCKTAILS_RATINGS[status.index]}">★★★★★</span></td>
    </tr>
    </c:forEach>
    </table>
</div>
</div><br/>
<button class="back-button" onclick="javascript:history.back(); return false;"><fmt:message  key="label.back" /></button>
<jsp:include page="/WEB-INF/myfooter.html"/>
</body>
</html>