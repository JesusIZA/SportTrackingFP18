<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Delete profile</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/author.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="siteparts/header.jsp" />
	
<div class="content">
	<span class="data">
		<a href="${pageContext.request.contextPath}/do?command=showprofile">Go to edit profile</a>
		<form name="deleteProfileF" action="${pageContext.request.contextPath}/do">
			<input id="command" name="command" value="deleteprofile" hidden="true">
			<input type="text" size="30" name="login" id="login" maxlength="56" placeholder="Confirm login">
			<label for="login">*</label>
			<br>
			<input type="password" size="30" name="password" id="password" maxlength="56" placeholder="Confirm password">
			<label for="password">*</label>
			<br>
			<input class="button" type="submit" name="submit" value="Delete">
		</form>
	</span>
	<span class="tips">
			<p>*Only latin letters or numbers</p>
	</span>
	<div class="clear"></div>
</div>

<jsp:include page="siteparts/footer.jsp" />
