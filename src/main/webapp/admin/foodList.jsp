<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Food Admin List</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="../siteparts/header.jsp" />

<div class="admin">
<h1>Admin Page</h1>
    <h3>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewfoodform"><strong>Add New Food</strong></a>
        &nbsp;
        <a href="#">List Users</a>
        &nbsp;
        <a href="#">List Profiles</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistnorm">List Norms</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistwaseaten">List Was eaten</a>

    </h3>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Foods</h2></caption>
        <tr>
            <th>IdF</th>
            <th>Name of food</th>
            <th>Calories</th>
            <th>Proteins</th>
            <th>Fats</th>
            <th>Carbohydrates</th>
        </tr>
        <c:forEach var="entity" items="${listEntity}">
            <tr>
                <td><c:out value="${entity.getIdF()}" /></td>
                <td><c:out value="${entity.getName()}" /></td>
                <td><c:out value="${entity.getProteins()}" /></td>
                <td><c:out value="${entity.getFats()}" /></td>
                <td><c:out value="${entity.getCarbohydrates()}" /></td>
                <td>
                    <a href="${pageContext.request.contextPath}/do?command=adminshoweditfoodform&id=<c:out value='${entity.getIdF()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/do?command=admindeletefood&id=<c:out value='${entity.getIdF()}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
    <div class="clear"></div>
</div>

<jsp:include page="../siteparts/footer.jsp" />