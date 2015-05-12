<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style_new.css"/>"  media="screen" />
<script src="<c:url value="/resources/js/jquery.min-214.js"/>"></script>
<script type="text/javascript">
   $(document).ready(function(){
	  var cartB=$("#cart").find("tbody");var cartF=$("#cart").find("tfoot");
	  $.getJSON("cartsJson/getCartItem.json", function(data) {
		  if (data.sucess){
			   var content;
			   for (var i=0;i<data.cart.items.length;i++){
				   content=content+"<tr></tr><td>"+data.cart.items[i].product.title+"</td><td>"+data.cart.items[i].quantity+"</td></tr>"+'<input type="hidden" value="'+data.cart.items[i].product.productId+'" class="proId"/>';
			   }
			   cartB.append(content);
			   cartF.append("<tr><th>Total:</th><th>"+data.cart.total+"<th></tr>");
		   }
		 });
	   $(".add").click(function(e){
			e.preventDefault();
			var url=$(this).attr("href");
			$.getJSON(url+".json", function(data) {
			   console.log(data);
			   if (data.sucess){
				   var content;cartB.empty();cartF.empty();
				   for (var i=0;i<data.cart.items.length;i++){
					   content=content+"<tr></tr><td>"+data.cart.items[i].product.title+"</td><td>"+data.cart.items[i].quantity+"</td></tr>"+'<input type="hidden" value="'+data.cart.items[i].product.productId+'" class="proId"/>';
				   }
				   cartB.append(content);
				   cartF.append("<tr><th>Total:</th><th>"+data.cart.total+"<th></tr>");
			   }
			});
		});
	   $(".remove").click(function(e){
		   if($(".proId").length==0){return false;}
			e.preventDefault();
			var pid=$(this).attr("classid");var found=false;
			$(".proId").each(function(i,e){
				if(pid==e.value){found=true;}
			});
			if(!found){return false;}
			var url=$(this).attr("href");
			$.getJSON(url+".json", function(data) {
			   console.log(data);
			   if (data.sucess){
				   var content;cartB.empty();cartF.empty();
				   for (var i=0;i<data.cart.items.length;i++){
					   content=content+"<tr></tr><td>"+data.cart.items[i].product.title+"</td><td>"+data.cart.items[i].quantity+"</td></tr>"+'<input type="hidden" value="'+data.cart.items[i].product.productId+'" class="proId"/>';
				   }
				   cartB.append(content);
				   cartF.append("<tr><th>Total:</th><th>"+data.cart.total+"<th></tr>");
			   }
			});
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
                <a href="${pageContext.request.contextPath}/spcing/cartsJson/add/${product.productId}" class="add">add</a>
                <a href="${pageContext.request.contextPath}/spcing/cartsJson/remove/${product.productId}" class="remove" classid="${product.productId}">remove</a>
                </td>
           </tr>

</c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/spcing/mainPage"> Back to home</a>
</div>

<div id="cart"> 
 <h2>Items in cart :</h2>
	<table>
		<tbody>
		</tbody>
		<tfoot>
		</tfoot>
	</table>
</div>
</div>

</body>
</html>