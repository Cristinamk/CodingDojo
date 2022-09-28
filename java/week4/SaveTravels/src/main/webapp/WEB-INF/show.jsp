<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
  <thead>
    <tr>
      <th scope="col">Expense name:</th>
      <th scope="col">Vendor name:</th>
      <th scope="col">Amount</th>
      <th scope="col">Description</th>
    </tr>
  </thead>
  <tbody>

    <tr>
      <td><c:out value="${travel.name}"></c:out></td>
      <td><c:out value="${travel.vendor}"></c:out></td>
      <td><c:out value="${travel.price}"></c:out></td>
      <td><c:out value="${travel.description}"></c:out></td>
     </tr>
  </tbody>
</table>

</body>
</html>