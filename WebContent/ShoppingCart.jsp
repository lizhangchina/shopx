<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,shop.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
table.paleBlueRows {
  font-family: "Times New Roman", Times, serif;
  border: 1px solid #FFFFFF;
  width: 350px;
  height: 200px;
  text-align: center;
  border-collapse: collapse;
}
table.paleBlueRows td, table.paleBlueRows th {
  border: 1px solid #FFFFFF;
  padding: 3px 2px;
}
table.paleBlueRows tbody td {
  font-size: 13px;
}
table.paleBlueRows tr:nth-child(even) {
  background: #D0E4F5;
}
table.paleBlueRows thead {
  background: #0B6FA4;
  border-bottom: 5px solid #FFFFFF;
}
table.paleBlueRows thead th {
  font-size: 17px;
  font-weight: bold;
  color: #FFFFFF;
  text-align: center;
  border-left: 2px solid #FFFFFF;
}
table.paleBlueRows thead th:first-child {
  border-left: none;
}

table.paleBlueRows tfoot {
  font-size: 14px;
  font-weight: bold;
  color: #333333;
  background: #D0E4F5;
  border-top: 3px solid #444444;
}
table.paleBlueRows tfoot td {
  font-size: 14px;
}
</style>
<script type="text/javascript">



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
<title>Insert title here</title>
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
<a href="<%=request.getContextPath()%>/item">Item List</a>
<hr>
<p>Your Cart:</p>
<table class="paleBlueRows">
<thead>
<tr>
<td>Item Name</td>
<td>Count</td>
<td>Price Total</td>
<td>Add to Cart</td>
</tr>
</thead>
<form action="<%=request.getContextPath()%>/item/saveCart" method="post">
<tbody>
<% Map<String,Map<String,Object>> itemCart = (Map<String,Map<String,Object>>)session.getAttribute("itemCart");
float total_price = 0.0f;

if(itemCart!=null){
for(Map<String,Object> i:itemCart.values()){
	Item item = (Item)i.get("item");
	int itemCount = Integer.parseInt(i.get("itemCount").toString());
	float itemPriceTotal = Float.parseFloat(i.get("itemPriceTotal").toString());
	total_price += itemPriceTotal;
%>
<tr>
<td><input type="hidden" name="itemId" value="<%=item.getId()%>"><%=item.getName()%></td>
<td><input id="buy_count_<%=item.getId()%>" name="buy_count" type="number" value="<%=itemCount %>" min="0" onchange="changeCount('<%=item.getId()%>',<%=item.getPrice()%>)"></td>
<td><span id="itemPriceTotal_<%=item.getId()%>"><%=itemPriceTotal%></span></td>
<td><a href="<%=request.getContextPath()%>/item/removeItem?itemId=<%=item.getId()%>">Remove</a></td>
</tr>
<%}} %>
</tbody>
<tfoot><tr><td>Total Price:<span id="total_price"><%=total_price%></span></td><td><button type="submit" >Save Cart</button></td></tr></tfoot>
</form>
</table>
</body>
</html>