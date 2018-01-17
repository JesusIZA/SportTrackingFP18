<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Tracking today</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/tracking.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon2.png">

<jsp:include page="siteparts/header.jsp" />
	
<div class="content">
	<div class="userPanel">
		<span class="name">${sessionScope.name}</span>
		<span class="sOut">
			<form name="lo" action="${pageContext.request.contextPath}/do" method="post">
				<input name="command" value="logout" hidden="true">
				<input class="button" type="submit" name="submit" value="Sign Out">
			</form>
		</span>
		<span class="hrefs">
			<a href="${pageContext.request.contextPath}/do?command=showprofile"><img src="images/editProfile.png" alt="Edit profile" title="Edit profile"></a>
			<a href="${pageContext.request.contextPath}/do?command=statistics"><img src="images/statistics.png" alt="Statistics" title="Statistics"></a>
		</span>
		<div class="clear"></div>
	</div>
	<div class="lookingPanel">
		<span class="leftL">
			<h3>Norma:</h3>
			<p>Calories:</p>
			<p>${norm.getCalories()}</p>
			<p>Proteins:</p>
			<p>${norm.getProteins()}</p>
		</span>
		<span class="rightL">
			<h3>For now:</h3>
			<p>Calories:</p>
			<p>${forNow.getCalories()}</p>
			<p>Proteins:</p>
			<p>${forNow.getProteins()}</p>
		</span>
		<div class="clear"></div>
	</div>
	<div class="messagePanel" style="background-color: ${color}; border: solid 4px ${color};">
		<p>${message}</p>
		<div class="clear"></div>
	</div>
	<div class="eatFood">
		<a href="${pageContext.request.contextPath}/addFoodType.jsp"><img src="images/addMyFood.png" alt="Add my food" title="Add new type of food"></a>
		<form action="${pageContext.request.contextPath}/do">
			<input id="command" name="command" value="addfoodtoday" hidden="true">
			<select name="foodItem" id="foodItem">
				<c:forEach var = "i" begin="0" end="${foods.size()-1}">
					<option value="${foods.get(i).getName()}">${foods.get(i).getName()} (cal: ${foods.get(i).getCalories()}, p: ${foods.get(i).getProteins()}, f: ${foods.get(i).getFats()}, c: ${foods.get(i).getCarbohydrates()})</option>
				</c:forEach>
			</select>
			<input id="add" class="button" type="submit" name="submit" value="Eaten">
		</form>
		<div class="clear"></div>
	</div>
	<div class="eatenItems">
		<c:forEach var = "i" begin="0" end="${eatenToday.size()-1}">
			<div class="food">
				<p>${eatenToday.get(i).getName()}</p>
				<br><br>
				<span>${eatenToday.get(i).getCalories()}</span>
				<span>Calories:</span>
				<span>${eatenToday.get(i).getFats()}</span>
				<span>Fats:</span>
				<span>${eatenToday.get(i).getProteins()}</span>
				<span>Proteins:</span>
				<span>${eatenToday.get(i).getCarbohydrates()}</span>
				<span>Carbohydrates:</span>
			</div>
		</c:forEach>
		<span>
			<form action="${pageContext.request.contextPath}/do">
			<input  name="command" value="tracking" hidden="true">
				<input id="p1" class="button" type="submit" name="doPage" value="PREV">
			</form>
		</span>
			<span>
			<form action="${pageContext.request.contextPath}/do">
			<input  name="command" value="tracking" hidden="true">
				<input id="p2" class="button" type="submit" name="doPage" value="NEXT">
			</form>
		</span>
		<div class="clear"></div>
	</div>
</div>

<jsp:include page="siteparts/footer.jsp" />
