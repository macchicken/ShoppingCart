<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All products</title>
</head>
<body>
<table border = "1">
<thead>
<tr>
<th>Title</th><th>Description</th><th>Price</th><th>icon</th>
</tr>
</thead>
<tbody>

<c:forEach var="product" items= "${products}"
	varStatus="productCount">
            <tr>
            <td>${product.title}</td>
               <td>${product.description}</td>
               <td>${product.price}</td>
                <td>
                <img src="${product.imageUrl}" alt="image not available">
                </td>
           </tr>

</c:forEach>

 </tbody>
</table>

<a href="${pageContext.request.contextPath}/spcing/mainPage"> Back to home</a>
</body>
</html>