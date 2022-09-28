<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>Here's Your Omikuji!</h1>
 <p>In <c:out value="${number}"/> years,</p>
 <p>you will live in <c:out value="${city}"/></p>
 <p>with <c:out value="${person}"/> as your roommate,</p>
 <p><c:out value="${hobby}"/>for living.</p>
 <p>Next time you see a,<c:out value="${thing}"/> <c:out value="${nice}"/></p>
 
 	<a href="/omikuji" >Go Back</a>
</body>
</html>