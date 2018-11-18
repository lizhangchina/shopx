
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
body {
	color: #333333;
	font: 16px "Lucida Grande", Verdana, Arial, "Bitstream Vera Sans",
		sans-serif;
	padding-top: 0px;
	background-color: #FFFFFF;
}

* {
	margin: 0;
	padding: 0;
}
#login {
	margin: 3em auto;
	width: 600px;
}

input.button-primary,button.button-primary,a.button-primary {
	background: url("img/button-grad.png") repeat-x scroll left top
		#21759B;
	border-color: #298CBA;
	color: #FFFFFF;
	font-weight: bold;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.3);
}

form {
	background: none repeat scroll 0 0 #FFFFFF;
	border: 1px solid #E5E5E5;
	border-radius: 11px 11px 11px 11px;
	box-shadow: 0 4px 18px #C8C8C8;
	font-weight: normal;
	margin-left: 8px;
	padding: 16px 16px 40px;
}

body form .input {
	background: none repeat scroll 0 0 #FBFBFB;
	border: 1px solid #E5E5E5;
	font-size: 24px;
	margin-bottom: 16px;
	margin-right: 6px;
	margin-top: 2px;
	padding: 3px;
	width: 97%;
}

textarea,input[type="text"],input[type="password"],input[type="file"],input[type="button"],input[type="submit"],input[type="reset"],select
	{
	background-color: #FFFFFF;
	border-color: #DFDFDF;
}

input {
	color: #555555;
}

form .submit,.alignright {
	float: right;
}

.submit {
	border-color: #DFDFDF;
}

.button-primary {
	border: 1px solid;
	border-radius: 11px 11px 11px 11px;
	cursor: pointer;
	font-family: "Lucida Grande", Verdana, Arial, "Bitstream Vera Sans",
		sans-serif;
	font-size: 16px;
	margin-top: -3px;
	padding: 3px 10px;
	text-decoration: none;
}
</style>
</head>
<body>
<div style="text-align: center;margin-top: 40px"><img alt="Logo" src="<%=request.getContextPath()%>/img/cart.jpg" height="100px"/>
	 <img alt="People" src="<%=request.getContextPath()%>/img/top.jpg" height="100px"/></div>
<div id="login">

<div style="padding: 0px 50px">
		<form action="login" method="post">
			<p><label>User Name:<br />
				<input id="username" type="text" name="username" class="input"
					value="" size="20" tabindex="10" /> </label>
			</p>
			<p>
				<label>Password:<br /> <input type="password" name="password"
					id="password" class="input" value="" size="20" tabindex="20" /> </label>
			</p>
			<p class="submit">
				<input type="submit" name="wp-submit"
					id="wp-submit"
					class="button-primary" value="Login" tabindex="100" />
			</p>
		</form>
		</div>
		<div style="text-align: center;margin-top: 20px"><p style="font-size: 8px">&copy; ZhangLi</p></div>
</div>

</body>
</html>