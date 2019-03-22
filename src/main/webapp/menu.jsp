<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Current date--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>

<%@ page isELIgnored="false" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Restaurant menu</title>
</head>
<body>
<h1>Restaurant menu</h1>
<table border="1">
    <caption align="left">Menu for today: <fmt:formatDate type="date" value="${date}" pattern="dd.MM.yyyy"/></caption>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Discount, %</th>
        <th>Weight, gram</th>
    </tr>
    <c:forEach var="dish" items="${dishes}">
        <tr>
            <td>${dish.name}</td>
            <td>${dish.price}</td>
            <td>${dish.discount} </td>
            <td>${dish.weight}</td>
        </tr>
    </c:forEach>
</table>
<br>
<strong>Add new dish:</strong>  <br>
<form action="add" method="post">
    Name: <input type="text" min="0" step="any" name="name" placeholder="name" required>
    Price: <input type="number" min="0" step="1" name="price" placeholder="price" required>
    Discount:<input type="number" min="0" max="50" step="1" name="discount" placeholder="0-50%" required>
    Weight:<input type="number" min="0" step="50" name="weight" placeholder="gram" required>
    <input type="submit" value="Add">
    <input type="reset" value="Reset">
</form>
<br>
<strong>Select menu:</strong>
<br>
<form action="/" method="post">
    Price from: <input type="number" min="0" name="priceFrom">
    to: <input type="number" min="0" name="priceTo"><br>
    Discount only:<input type="checkbox" name="disc_only" value="true"><br>
    Weight up to 1 kg:<input type="checkbox" name="weight_up" value="true"><br>
    <input type="submit" value="Get">
    <input type="reset" value="Reset">
</form>
<br>

<table border="1">
    <caption align="left">Your menu </caption>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Discount, %</th>
        <th>Weight, gram</th>
    </tr>

    <c:forEach var="dish" items="${dishes_select}">
        <tr>
            <td>${dish.name}</td>
            <td>${dish.price}</td>
            <td>${dish.discount} </td>
            <td>${dish.weight}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${dishes_select == null}"><strong>You chose nothing!</strong> </c:if>
<%--Наши блюда: <br>--%>
<%--<c:choose>--%>
<%--<c:when test="${(dishes ne null) && (fn:length(dishes) gt 0)}">--%>
<%--<table border="1">--%>
<%--<tr>--%>
<%--<th>Название</th><th>Цена (грн)</th><th>Вес (гр)</th><th>Скидка</th>--%>
<%--</tr>--%>
<%--<c:forEach items="${dishes}" var="a">--%>
<%--<tr>--%>
<%--<td>${a.name}</td>--%>
<%--<td>${a.price / 100.0}</td>--%>
<%--<td>${a.weight / 1000.0}</td>--%>
<%--<td>--%>
<%--<c:choose>--%>
<%--<c:when test="${a.discount eq true}">--%>
<%--Есть--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--Нету--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--Нет.--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
</body>
</html>
