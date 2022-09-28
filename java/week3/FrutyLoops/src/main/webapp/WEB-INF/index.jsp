<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Fruit Store </h1>
	<table class="table">
  <thead>
   <tr>
      <th scope="col">Name</th>
      <th scope="col">Price</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var = "fruit" items="${fruits}">
    <tr>
      <th scope="row">1</th>
      <td>${fruit.name}</td>
      <td>${fruit.price}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>