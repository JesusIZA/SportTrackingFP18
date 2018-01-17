<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/author.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="siteparts/header.jsp" />

<div class="content">

	<span class="data">
		<a href="${pageContext.request.contextPath}/registration.jsp">Registration</a>
		<br>
		<form name="loginF" method="post" action="${pageContext.request.contextPath}/do">
			<input id="command" name="command" value="login" hidden="true">
			<input type="text" size="30" name="login" id="login" maxlength="56" placeholder="Login">
			<label for="login">*</label>
			<br>
			<input type="password" size="30" name="password" id="password" maxlength="56" placeholder="Password">
			<label for="password">*</label>
			<br>
			<input class="button" type="submit" name="submit" value="Sign In">
		</form>
	</span>
    <span class="tips">
		<p>*Only latin letters or numbers</p>
	</span>

    <div class="clear"></div>
</div>

<jsp:include page="siteparts/footer.jsp" />
