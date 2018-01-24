<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Edit profile</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/author.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="siteparts/header.jsp" />
	
<div class="content">
	<span class="data">
		<a href="editUser.jsp">Change login and password</a>
		<form name="editProfileF" action="${pageContext.request.contextPath}/do">
			<input id="command" name="command" value="editprofile" hidden="true">
			<input type="text" size="30" name="name" id="name" value="${name}" maxlength="56" placeholder="Name">
			<label for="name">*</label>
			<br>
			<input type="text" size="30" name="surname" id="surname" value="${surname}" maxlength="56" placeholder="Surname">
			<label for="surname">*</label>
			<br>
			<br>
			<input type="radio" value="male" name="sex" id="Ra1">
			<label style="padding-left: 10px;" for="Ra1">Male</label>
			<input type="radio" value="female" name="sex" id="Ra2">
			<label style="padding-left: 10px;" for="Ra2">Famale</label>
			<br>
			<label class="wordl" for="birthday">Birthday:</label>
			<input type="date" name="birthday" value="${birthday}" id="birthday">
			<br>
			<input type="number" name="height" value="${height}" id="height" min="1" max="999" placeholder="Height">
			<label for="height">**</label>
			<br>
			<input type="number" name="weight" value="${weight}" id="weight" min="1" max="999" placeholder="Weight">
			<label for="weight">**</label>
			<br>
			<input type="number" name="activeTime" value="${activeTime}" id="activeTime" min="1" max="99" placeholder="Active time">
			<label for="activeTime">***</label>
			<br>
			<input class="button" type="submit" name="submit" value="Save">
		</form>
	</span>
	<span class="tips">
			<p>*Only letters</p>
			<p>**Only nembers (1 - 999)</p>
			<p>***Percent by day you move (1 - 99)</p>
	</span>
	<div class="clear"></div>
</div>

<jsp:include page="siteparts/footer.jsp" />
