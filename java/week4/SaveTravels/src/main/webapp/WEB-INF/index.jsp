<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Save Travels</title>
</head>
<body> 
	<h1>Save Travels</h1>
	<table class="table">
  <thead>
    <tr>
      <th scope="col">Expense</th>
      <th scope="col">Vendor</th>
      <th scope="col">Amount</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="travel" items="${travels}">
    <tr>
      <td><a href="/expenses/display/${travel.id}"><c:out value="${travel.name}"></c:out></a></td>
      <td><c:out value="${travel.vendor}"></c:out></td>
      <td><c:out value="${travel.price}"></c:out></td>
      <td><a href="/expenses/edit/${travel.id}">edit</a></td>
      <td>
      <form:form action="/expenses/delete/${travel.id}" method="POST">
      	<input type="hidden" name="_method" value="delete">
		<input type="submit" value="Delete">
      </form:form>
      </td>
      
      
      
    </tr>
 </c:forEach>
  </tbody>
</table> 





	<h2>Add an Expense</h2>
<form:form action="/expenses" method="post" modelAttribute="travel">
    <p>
        <form:label path="name">Expense Name:</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="vendor">Vendor:</form:label>
        <form:errors path="vendor"/>
        <form:textarea path="vendor"/>
    </p>
    <p>
        <form:label path="price">Amount</form:label>
        <form:errors path="price"/>
        <form:input type="number" path="price"/>
    </p>
    <p>
        <form:label path="description">Description</form:label>
        <form:errors path="description"/>  
        <form:textarea path="description"/>   
       
    </p>    
    <input type="submit" value="Submit"/>
</form:form> 

</body>
</html>