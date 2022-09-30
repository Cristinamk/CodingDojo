<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<form:form action="/ninjas/new" method ="POST" modelAttribute="ninja">
	<form:label path="firstName">First Name:</form:label>
	<form:input type="text" path="firstName"/>
	<form:label path="lastName">Last Name:</form:label>
	<form:input type="text" path="lastName"/>
	<form:label path="age"></form:label>
	<form:input type="number" path="age"/>
	<form:label path="dojo">Dojo:</form:label>
	<form:select path="dojo">
		<c:forEach var="dojos" items="${dojo}">
			<option value="${dojos.id}">${dojos.name}</option>
		</c:forEach>
	</form:select>
	<input type="submit" value="SUBMIT">
	</form:form>

</div>

</body>
</html>