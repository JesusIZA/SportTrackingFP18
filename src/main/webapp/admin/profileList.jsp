<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Profile Admin List</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">


<div class="admin">
<h1>Admin Page</h1>
    <h3>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewprofileform"><strong>Add NEW Profile</strong></a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistuser">List Users</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistfood">List Food</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistnorm">List Norms</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistwaseaten">List Were eaten</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistlink">List Links</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=logout">LOG OUT</a>

    </h3>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Profiles</h2></caption>
        <tr>
            <th>IdP</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Sex</th>
            <th>Birthday</th>
            <th>Height</th>
            <th>Weight</th>
            <th>Active time</th>
        </tr>
        <c:forEach var="entity" items="${listEntity}">
            <tr>
                <td><c:out value="${entity.getIdP()}" /></td>
                <td><c:out value="${entity.getName()}" /></td>
                <td><c:out value="${entity.getSurname()}" /></td>
                <td><c:out value="${entity.getSex()}" /></td>
                <td><c:out value="${entity.getBirthday()}" /></td>
                <td><c:out value="${entity.getHeight()}" /></td>
                <td><c:out value="${entity.getWeight()}" /></td>
                <td><c:out value="${entity.getActiveTime()}" /></td>
                <td>
                    <a href="${pageContext.request.contextPath}/do?command=adminshoweditprofileform&id=<c:out value='${entity.getIdP()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/do?command=admindeleteprofile&id=<c:out value='${entity.getIdP()}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
    <div class="clear"></div>
</div>
