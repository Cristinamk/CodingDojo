<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Send an Omikuji</h1>
	<form action="/info" method="post">
		<label>Pick any Number from 5 to 25</label>
		<input type="number"name="number">
		<label>Enter the name of any city</label>
		<input type="text"name="city">
		<label>Enter a name of a real person</label>
		<input type="text"name="person">
		<label>Enter professional endeavor or hobby:</label>
		<input type="text"name="hobby">
		<label>Enter any type of living thing</label>
		<input type="text"name="thing">
		<label>Say something nice to someone:</label>
		<textarea name="nice"></textarea>
		 
		 <p>Send and show a friend</p>
		<input type="submit"name="Send">
	</form>
</body>
</html>