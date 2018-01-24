<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Add food type</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/author.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="siteparts/header.jsp" />
	
<div class="content">
	<span class="data">
		<a href="${pageContext.request.contextPath}/do?command=tracking">Tracking today</a>
		<form name="addFoodType" action="${pageContext.request.contextPath}/do">
			<input id="command" name="command" value="addfood" hidden="true">
			<input type="text" size="30" name="nameFood" id="nameFood" maxlength="250" placeholder="Name of food">
			<label for="nameFood">*</label>
			<br>
			<input type="text" size="30" name="calories" id="calories" maxlength="12" placeholder="Calories">
			<label for="calories">**</label>
			<br>
			<input type="text" size="30" name="proteins" id="proteins" maxlength="12" placeholder="Proteins">
			<label for="proteins">**</label>
			<br>
			<input type="text" size="30" name="fats" id="fats" maxlength="12" placeholder="Fats">
			<label for="fats">**</label>
			<br>
			<input type="text" size="30" name="carbohydrates" id="carbohydrates" maxlength="12" placeholder="Carbohydrates">
			<label for="carbohydrates">**</label>
			<br>
			<input class="button" type="submit" name="submit" value="Add">
		</form>
	</span>
	<span class="tips">
		<p>*Only letters or numbers</p>
		<p>**Real number as 0.0</p>
	</span>
	<div class="clear"></div>
</div>

<jsp:include page="siteparts/footer.jsp" />
