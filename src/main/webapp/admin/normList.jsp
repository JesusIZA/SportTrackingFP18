<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Norm Admin List</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">


<div class="admin">
<h1>Admin Page</h1>
    <h3>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewnormform"><strong>Add NEW Norm</strong></a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistuser">List Users</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistprofile">List Profiles</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistfood">List Foods</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistwaseaten">List Were eaten</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistlink">List Links</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=logout">LOG OUT</a>

    </h3>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Norms</h2></caption>
        <tr>
            <th>IdN</th>
            <th>Calories</th>
            <th>Proteins</th>
        </tr>
        <c:forEach var="entity" items="${listEntity}">
            <tr>
                <td><c:out value="${entity.getIdN()}" /></td>
                <td><c:out value="${entity.getCalories()}" /></td>
                <td><c:out value="${entity.getProteins()}" /></td>
                <td>
                    <a href="${pageContext.request.contextPath}/do?command=adminshoweditnormform&id=<c:out value='${entity.getIdN()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/do?command=admindeletenorm&id=<c:out value='${entity.getIdN()}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
    <div class="clear"></div>
</div>
