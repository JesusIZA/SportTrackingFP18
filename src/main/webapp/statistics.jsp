<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Statistics</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/tracking.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon2.png">

<jsp:include page="siteparts/header.jsp" />
	
<div class="content">
	<div class="userPanel">
		<span class="name">
			<form name="statisticsF" method="post" action="${pageContext.request.contextPath}/do">
				<input id="command" name="command" value="statistics" hidden="true">
				<label for="from">From: </label>
				<input class="dateF" id="from" type="date" name="from" value="${sessionScope.from}">
				<label for="to">To: </label>
				<input class="dateF" id="to" type="date" name="to" value="${sessionScope.to}">
				<input id="show" class="button" type="submit" name="look" value="Show">
			</form>
		</span>
		<span class="sOut">
			<form name="lo" action="${pageContext.request.contextPath}/do" method="post">
				<input name="command" value="logout" hidden="true">
				<input class="button" type="submit" name="submit" value="Sign Out">
			</form>
		</span>
		<span class="hrefs">
			<a href="${pageContext.request.contextPath}/do?command=showprofile"><img src="images/editProfile.png" alt="Edit profile" title="Edit profile"></a>
			<a href="${pageContext.request.contextPath}/do?command=tracking"><img src="images/trackingToday.png" alt="Tracking today" title="Tracking today"></a>
		</span>
		<div class="clear"></div>
	</div>
	
	<div class="eatenItems">
			<div class="day">
				<span class="date">${date}</span>
				<c:forEach var="j" begin="0" end="${foods.size()-1}">
					<div class="food">
						<p>${foods.get(j).getName()}</p>
						<br><br>
						<span>${foods.get(j).getCalories()}</span>
						<span>Calories:</span>
						<span>${foods.get(j).getFats()}</span>
						<span>Fats:</span>
						<span>${foods.get(j).getProteins()}</span>
						<span>Proteins:</span>
						<span>${foods.get(j).getCarbohydrates()}</span>
						<span>Carbohydrates:</span>
					</div>
				</c:forEach>
			</div>
		<span>
			<form action="${pageContext.request.contextPath}/do">
			<input  name="command" value="statistics" hidden="true">
				<input id="p1" class="button" type="submit" name="doPage" value="PREV">
			</form>
		</span>
		<span>
			<form action="${pageContext.request.contextPath}/do">
			<input  name="command" value="statistics" hidden="true">
				<input id="p2" class="button" type="submit" name="doPage" value="NEXT">
			</form>
		</span>
		<div class="clear"></div>
	</div>
</div>

<jsp:include page="siteparts/footer.jsp" />
