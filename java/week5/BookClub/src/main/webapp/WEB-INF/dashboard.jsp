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
    <title>Books</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Welcome <c:out value="${user.name}"></c:out> </h1>

	<p>Books from everyone's shelves:</p>
	<a href="/newbook">Add a Book to my shelf!</a>
	
	<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Title</th>
      <th scope="col">Author Name</th>
      <th scope="col">Posted By</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="book" items="${books}">
    <tr>
      <td><c:out value="${book.id}"></c:out></td>
      <td><a href="/books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
      <td><c:out value="${book.author}"></c:out></td>
      <td><c:out value="${book.user.name}"></c:out></td>
      <td>
      <c:if test="${loggedIn.equals(book.user.id)}">
             <form:form action="/delete/${book.id}" method="POST">
      	<input type="hidden" name="_method" value="delete">
		<input type="submit" value="Delete">
      </form:form>  
      <a href ="/edit/${book.id}">Edit Book</a>        
       </c:if>

</td>
      
     
      
      <%-- <form:form action="/expenses/delete/${travel.id}" method="POST">
      	//<input type="hidden" name="_method" value="delete">
		<input type="submit" value="Delete">
      </form:form><a href="/expenses/display/${book.id}">
      </td>--%>
      
      
      
    </tr>
 </c:forEach>
  </tbody>
</table> 
	
<a href="/logout"><button>Log Out</button></a>


   
</body>
</html>