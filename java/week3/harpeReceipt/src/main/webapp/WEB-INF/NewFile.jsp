<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	    <h1>Customer name: <c:out value="${name}"></c:out></h1>
			<p>Item name:<c:out value="${item}"></c:out></p>
			<p>Price:<c:out value="${price}"></c:out></p>
			<p>Description:<c:out value="${description}"></c:out></p>
			<p>Vendor:<c:out value="${vendor}"></c:out></p>
</body>
</html>