<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<meta charset="utf-8">
<title>Statistics</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/tracking.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon2.png">

<jsp:include page="siteparts/header.jsp" />
<c:set var="language" value="${empty param.language ? (empty sessionScope.language ? 'en_EN' : sessionScope.language) : param.language }" scope="session"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="bundles/bundle"/>
	
<div class="content">
	<div class="userPanel">
		<span class="name">
			<form name="statisticsF" method="post" action="${pageContext.request.contextPath}/do">
				<input id="command" name="command" value="statistics" hidden="true">
				<label for="from"><fmt:message key="statistics.from"/>: </label>
				<input class="dateF" id="from" type="date" name="from" value="${sessionScope.from}">
				<label for="to"><fmt:message key="statistics.to"/>: </label>
				<input class="dateF" id="to" type="date" name="to" value="${sessionScope.to}">
				<input id="show" class="button" type="submit" name="look" value=<fmt:message key="statistics.show"/>>
			</form>
		</span>
		<span class="sOut">
			<form name="lo" action="${pageContext.request.contextPath}/do" method="post">
				<input name="command" value="logout" hidden="true">
				<input class="button" type="submit" name="submit" value="<fmt:message key="main.signout"/>">
			</form>
		</span>
		<span class="hrefs">
			<a class="do" href="${pageContext.request.contextPath}/do?command=showprofile"><img src="images/editProfile.png" alt="Edit profile" title="Edit profile"></a>
			<a class="do" href="${pageContext.request.contextPath}/do?command=tracking"><img src="images/trackingToday.png" alt="Tracking today" title="Tracking today"></a>
			<a class="lang" href="${pageContext.request.contextPath}/do?command=statistics&language=en_EN"><img src="images/en.png" alt="EN" title="EN"></a>
			<a class="lang" href="${pageContext.request.contextPath}/do?command=statistics&language=ru_RU"><img src="images/ru.png" alt="RU" title="RU"></a>
			<a class="lang" href="${pageContext.request.contextPath}/do?command=statistics&language=ua_UA"><img src="images/ua.png" alt="UA" title="UA"></a>
		</span>
		<div class="clear"></div>
	</div>
	
	<div class="eatenItems">
		<c:forEach var="i" begin="0" end="${foods.size()-1}">
			<div class="day">
				<span class="date">${foods.get(i).getDate()}</span
				<div class="food">
					<p style="font-size: 30pt;"><strong>${foods.get(i).getName()}</strong></p>
					<br>
					<span style="color : mediumvioletred;"><fmt:message key="food.calories"/>:</span>
					<span>${foods.get(i).getCalories()}</span>
					<span style="color : red;"><fmt:message key="food.fats"/>:</span>
					<span>${foods.get(i).getFats()}</span>
					<span style="color : green;"><fmt:message key="food.proteins"/>:</span>
					<span>${foods.get(i).getProteins()}</span>
					<span style="color : orange;"><fmt:message key="food.carbohydrates"/>:</span>
					<span>${foods.get(i).getCarbohydrates()}</span>
				</div>
			</div>
		</c:forEach>
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
