<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,shop.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" /> 

<script type="text/javascript">

var addToCart = function(itemId){
	count = document.getElementById('count_'+itemId).value;
	window.location.href="<%=request.getContextPath()%>/item/addToCart?itemId="+itemId+"&count="+count;
	
};

var changeCount = function(itemId,price){
	count = document.getElementById('buy_count_'+itemId).value;
	old_item_price_total = parseFloat(document.getElementById('itemPriceTotal_'+itemId).innerHTML);
	new_item_price_total = count*price;
	document.getElementById('itemPriceTotal_'+itemId).innerHTML = new_item_price_total;
	old_price_total = parseFloat(document.getElementById('total_price').innerHTML);
	new_price_total = old_price_total - old_item_price_total + new_item_price_total;
	document.getElementById('total_price').innerHTML = new_price_total;
	
}

</script>
</head>
<body>
<% User user=(User)session.getAttribute("user");
if(user!=null){
%>
欢迎<%=user.getName() %>
<a href="<%=request.getContextPath()%>/logout">退出</a>
<%}else{ %>
<a href="<%=request.getContextPath()%>/login" >登录</a>
<%} %>
<a href="ShoppingCart.jsp">购物车</a>
<table class="paleBlueRows">
<thead>
<tr>
<td>Item Name</td>
<td>Image</td>
<td>Price</td>
<td>Count</td>
<td>Add to Cart</td>
</tr>
</thead>
<tbody>
<% List<Item> itemList = (List<Item>)request.getAttribute("itemList"); 
for(Item i:itemList){
%>
<tr>
<td><%=i.getName()%></td>
<td><img height="64" src="<%=request.getContextPath()%>/download?path=<%=i.getImageurl()%>"/></td>
<td><%=i.getPrice()%></td>
<td><input id="count_<%=i.getId()%>" type="number" value="1" min="1"></td>
<td>
<button onclick="addToCart('<%=i.getId()%>')">Add to Cart</button>
</tr>
<%} %>

</tbody>
</table>
<hr>

</body>
</html>