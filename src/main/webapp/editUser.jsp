<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Edit user</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/author.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="siteparts/header.jsp" />
	
<div class="content">
	<span class="data">
		<a href="${pageContext.request.contextPath}/do?command=showprofile">Edit profile</a>
		<form name="editUserF" action="${pageContext.request.contextPath}/do">
			<input id="command" name="command" value="edituser" hidden="true">
			<input type="text" size="30" name="newLogin" id="newLogin" maxlength="56" placeholder="New login">
			<label for="newLogin">*</label>
			<br>
			<input type="password" size="30" name="newPassword" id="newPassword" maxlength="56" placeholder="New password">
			<label for="newPassword">*</label>
			<br>
			<input type="password" size="30" name="oldPassword" id="oldPassword" maxlength="56" placeholder="Old password">
			<label for="oldPassword">*</label>
			<br>
			<input class="button" type="submit" name="submit" value="Save">
		</form>
		<a href="${pageContext.request.contextPath}/deleteProfile.jsp">Delete profile</a>
	</span>
	<span class="tips">
		<p>*Only latin letters or numbers</p>
	</span>
	<div class="clear"></div>
</div>

<jsp:include page="siteparts/footer.jsp" />
