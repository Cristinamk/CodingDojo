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
    <meta charset="UTF-8">
    <title>Tacos</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Register</h1>

	<form:form action ="/" method="POST" modelAttribute ="user">
		<div class="row">
			<div class ="col-md-3">
			<form:label path="firstName">First name</form:label>
			<form:input path="firstName"/>
			<form:errors path="firstName"/>
			</div>
		</div>
		<div class="row">
			<div class ="col-md-3">
			<form:label path="lastName">Last name</form:label>
			<form:input path="lastName"/>
			<form:errors path="lastName"/>
			</div>
		</div>
		<div class="row">
			<div class ="col-md-3">
			<form:label path="email">Email</form:label>
			<form:input path="email"/>
			<form:errors path="email"/>
			</div>
		</div>
		<div class="row">
			<div class ="col-md-3">
			<form:label path="password">Password</form:label>
			<form:password path="password"/>
			<form:errors path="password"/>
			</div>
		</div>
		<div class="row">
			<div class ="col-md-3">
			<form:label path="confirm">Confirm Password</form:label>
			<form:password path="confirm"/>
			<form:errors path="confirm"/>
			</div>
		</div>
	
		<input type="submit" value="Register"/>
	</form:form>
	<form:form action="/login" method="POST" modelAttribute="loginUser" class="col-sm">
		<h2>Log in Here!</h2>
				<div class="row">
			<div class ="col-md-3">
			<form:label path="email">Email</form:label>
			<form:input path="email"/>
			<form:errors path="email"/>
			</div>
		</div>
		<div class="row">
			<div class ="col-md-3">
			<form:label path="password">Password</form:label>
			<form:password path="password"/>
			<form:errors path="password"/>
			</div>
		</div>
		<button>Log In</button>
		</form:form>
   
</body>
</html>