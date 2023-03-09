<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 3/9/2023
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new User</title>
</head>
<body>
<form method="post">
  <h1>name</h1>
  <input type="text" name="name"><br>
  <h1>province</h1>
  <input list="province_ids" name="province_id">
  <datalist id="province_ids">
    <c:forEach items='${requestScope["provinces"]}' var="province">
      <option value="${province.getId()}">${province.getName()}</option>
    </c:forEach>
  </datalist>
  <input type="submit" value="Add user">
</form>
</body>
</html>
