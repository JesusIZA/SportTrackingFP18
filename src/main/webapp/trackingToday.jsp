<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Tracking today</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/tracking.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon2.png">

<jsp:include page="siteparts/header.jsp" />
<c:set var="language" value="${empty param.language ? (empty sessionScope.language ? 'en_EN' : sessionScope.language) : param.language }" scope="session"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="bundles/bundle"/>
	
<div class="content">
	<div class="userPanel">
		<span class="name">${sessionScope.name}</span>
		<span class="sOut">
			<form name="lo" action="${pageContext.request.contextPath}/do" method="post">
				<input name="command" value="logout" hidden="true">
				<input class="button" type="submit" name="submit" value=<fmt:message key="main.signout"/>>
			</form>
		</span>
		<span class="hrefs">
			<a class="do" href="${pageContext.request.contextPath}/do?command=showprofile"><img src="images/editProfile.png" alt="Edit profile" title="Edit profile"></a>
			<a class="do" href="${pageContext.request.contextPath}/do?command=statistics"><img src="images/statistics.png" alt="Statistics" title="Statistics"></a>
			<a class="lang" href="${pageContext.request.contextPath}/do?command=tracking&language=en_EN"><img src="images/en.png" alt="EN" title="EN"></a>
			<a class="lang" href="${pageContext.request.contextPath}/do?command=tracking&language=ru_RU"><img src="images/ru.png" alt="RU" title="RU"></a>
			<a class="lang" href="${pageContext.request.contextPath}/do?command=tracking&language=ua_UA"><img src="images/ua.png" alt="UA" title="UA"></a>
		</span>
		<div class="clear"></div>
	</div>
	<div class="lookingPanel">
		<span class="leftL">
			<h3><fmt:message key="norm"/>:</h3>
			<p><fmt:message key="food.calories"/>:</p>
			<p>${norm.getCalories()}</p>
			<p><fmt:message key="food.proteins"/>:</p>
			<p>${norm.getProteins()}</p>
		</span>
		<span class="rightL">
			<h3><fmt:message key="fornow"/>:</h3>
			<p><fmt:message key="food.calories"/>:</p>
			<p>${forNow.getCalories()}</p>
			<p><fmt:message key="food.proteins"/>:</p>
			<p>${forNow.getProteins()}</p>
		</span>
		<div class="clear"></div>
	</div>
	<div class="messagePanel" style="background-color: ${color}; border: solid 4px ${color};">
		<p><fmt:message key="main.message.${message}"/></p>
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
			<input id="add" class="button" type="submit" name="submit" value=<fmt:message key="main.eaten"/>>
		</form>
		<div class="clear"></div>
	</div>
	<div class="eatenItems">
		<c:forEach var = "i" begin="0" end="${eatenToday.size()-1}">
			<div class="food">
				<p>${eatenToday.get(i).getName()}</p>
				<br><br>
				<span>${eatenToday.get(i).getCalories()}</span>
				<span><fmt:message key="food.calories"/>:</span>
				<span>${eatenToday.get(i).getFats()}</span>
				<span><fmt:message key="food.fats"/>:</span>
				<span>${eatenToday.get(i).getProteins()}</span>
				<span><fmt:message key="food.proteins"/>:</span>
				<span>${eatenToday.get(i).getCarbohydrates()}</span>
				<span><fmt:message key="food.carbohydrates"/>:</span>
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
