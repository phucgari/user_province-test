<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 3/9/2023
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<a href="/user?action=create">create new user</a>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Province</th>
    </tr>
    <c:forEach items='${requestScope["users"]}' var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getProvince().getName()}</td>
            <td><a href="/user?action=update&id=${user.getId()}">update</a></td>
            <td><a href="/user?action=delete&id=${user.getId()}">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
