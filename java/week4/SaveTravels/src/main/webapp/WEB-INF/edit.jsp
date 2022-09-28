<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   
<h1>Edit Expense</h1>
<form:form action="/expenses/edit/${travels.id}" method="post" modelAttribute="travels">
    <input type="hidden" name="_method" value="put">
    <p>
        <form:label path="name">Expense name:</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="vendor">Vendor name:</form:label>
        <form:errors path="vendor"/>
        <form:textarea path="vendor"/>
    </p>
    <p>
        <form:label path="price">Price:</form:label>
        <form:errors path="price"/>
       <form:input type="number" path="price"/>
    </p>
    <p>
        <form:label path="description">Pages</form:label>
        <form:errors path="description"/>     
       <form:textarea path="description"/>
    </p>    
    <input type="submit" value="Submit"/>
</form:form>
	<a href="/expenses" >Go Back</a>

</body>
</html>