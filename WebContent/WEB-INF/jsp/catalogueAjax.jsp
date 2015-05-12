<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style_new.css"/>"  media="screen" />
<script src="<c:url value="/resources/js/jquery.min-214.js"/>"></script>
<script type="text/javascript">
   $(document).ready(function(){
	$(".add").click(function(e){
		e.preventDefault(); 
		$("#cart").load($(this).attr("href"));
	});
	$(".remove").click(function(e){
		if($(".proId").length==0){return false;}
		e.preventDefault();
		var pid=$(this).attr("productId");var found=false;
		$(".proId").each(function(i,e){
			if(pid==e.value){found=true;}
		});
		if(!found){return false;}
		$("#cart").load($(this).attr("href"));
	});
   })
</script>
<title>Catalogue page</title>
</head>
<body>
<div id="wrap">

<div id="header">
<h1>COMP5347 Week 10 Lab</h1>
<h2>shopping cart page</h2>
</div>


<div id="catalogue">

<h1>Catalogue</h1>

<table border = "1">
	<thead>
		<tr>
			<th> Item Title</th>
			<th> Item Description</th>
			<th> Picture </th>
			<th> Item Price</th>
			<th> Quantity</th>
		</tr>
	</thead>
	<tbody>
<c:forEach var="product" items= "${products}"
	varStatus="productCount">
            <tr>
            <td>${product.title}</td>
               <td>${product.description}</td>
                 
                <td><img src="<c:url value="/resources/imgs/${product.imageUrl}"/>" width="80px" alt="image not available"></td>
               
               <td>${product.price}</td>
                <td>
                <a href="${pageContext.request.contextPath}/cartsAjax/add/${product.productId}" class="add">add</a>
                <a href="${pageContext.request.contextPath}/cartsAjax/remove/${product.productId}" class="remove" productId="${product.productId}">remove</a>
                </td>
           </tr>

</c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/spcing/mainPage"> Back to home</a>
</div>

<div id="cart"> 

 <jsp:include page="cart_partial.jsp"></jsp:include>

</div>
</div>

</body>
</html>