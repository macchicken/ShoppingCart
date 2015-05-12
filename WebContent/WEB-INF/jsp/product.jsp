<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Individual Product</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/spcing/products/save" method = "post" commandName="product">
<label>Title
<form:input  path="title" /> <form:errors path="title"/></label>
<br/>
<label> Description 
<form:input type = "text" path= "description"/></label>
<br/>
<label>Price
<form:input  path= "price" /><form:errors path="price"/></label>
<form:input type="hidden" path="productId"/>
<br/>
<input type = "submit" value = "Submit"/>
<br/>
<input type = "reset" />
</form:form>
</body>
</html>