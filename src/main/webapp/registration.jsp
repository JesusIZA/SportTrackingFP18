<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/author.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="siteparts/header.jsp" />
	
<div class="content">
	<span class="data">
		<a href="${pageContext.request.contextPath}/login.jsp">Sign In</a>
		<form name="registrationF" method="post" action="${pageContext.request.contextPath}/do">
			<input id="command" name="command" value="registration" hidden="true">
			<input type="text" size="30" name="login" id="login" maxlength="56" placeholder="Login">
			<label for="login">**</label>
			<br>
			<input type="password" size="30" name="password" id="password" maxlength="56" placeholder="Password">
			<label for="password">**</label>
			<br>
			<input type="text" size="30" name="name" id="name" maxlength="56" placeholder="Name">
			<label for="name">*</label>
			<br>
			<input type="text" size="30" name="surname" id="surname" maxlength="56" placeholder="Surname">
			<label for="surname">*</label>
			<br>
			<br>
			<input type="radio" value="male" name="sex" id="Ra1">
			<label style="padding-left: 10px;" for="Ra1">Male</label>
			<input type="radio" value="female" name="sex" id="Ra2">
			<label style="padding-left: 10px;" for="Ra2">Famale</label>
			<br>
			<label class="wordl" for="birthday">Birthday:</label>
			<input type="date" name="birthday" id="birthday">
			<br>
			<input type="number" name="height" id="height" min="1" max="999" placeholder="Height">
			<label for="height">***</label>
			<br>
			<input type="number" name="weight" id="weight" min="1" max="999" placeholder="Weight">
			<label for="weight">***</label>
			<input type="number" name="activeTime" id="activeTime" min="1" max="99" placeholder="Active time">
			<label for="activeTime">****</label>
			<br>
			<input class="button" type="submit" name="submit" value="Registration">
		</form>
	</span>
	<span class="tips">
		<p>*Only letters</p>
		<p>**Only latin letters or numbers</p>
		<p>***Only nembers (1 - 999)</p>
		<p>****Percent by day you move (1 - 99)</p>
	</span>
	<div class="clear"></div>
</div>

<jsp:include page="siteparts/footer.jsp" />
