<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_new.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style_response.css"  media="screen" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min-214.js"></script>
<title>Products Pages</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h5 class="widget-title"><a href="products" > List All Products</a></h5>
</sec:authorize>
<p><a href="restsc/products" > List All Products flickr</a></p>
<p><a href="carts" > Show products and Cart</a></p>
<p><a href="cartsAjax">Show products and Cart(Ajax version)</a></p>
<p><a href="cartsJson">Show products and Cart(Restful Json version)</a></p>
<p>tag name to search</p>
<input type="text"  class="input-small" id="skeyword" name="skeyword" >
<input type="button" class="btn btn-info btn-sm" name="flickrsearch" id="flickrsearch" value="Search">
<p><a href="${pageContext.request.contextPath}/logout">Log Out</a></p>
</body>
<script type="text/javascript">
   $(document).ready(function(){
	$("#flickrsearch").click(function(e){
		e.preventDefault();
		var kw=$("#skeyword").val();console.log(kw);
		window.location.href = "restsc/products/search?keyword="+kw;
	});
   })
</script>
</html>
