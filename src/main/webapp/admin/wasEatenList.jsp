<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Were eaten Admin List</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="../siteparts/header.jsp" />

<div class="admin">
<h1>Admin Page</h1>
    <h3>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewwaseatenform"><strong>Add New Was eaten</strong></a>
        &nbsp;
        <a href="#">List Users</a>
        &nbsp;
        <a href="#">List Profiles</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistnorm">List Norms</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistfood">List Foods</a>

    </h3>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Were eaten</h2></caption>
        <tr>
            <th>IdWE</th>
            <th>Id Profile</th>
            <th>Id Food</th>
            <th>Date</th>
        </tr>
        <c:forEach var="entity" items="${listEntity}">
            <tr>
                <td><c:out value="${entity.getIdWE()}" /></td>
                <td><c:out value="${entity.getIdP()}" /></td>
                <td><c:out value="${entity.getIdF()}" /></td>
                <td><c:out value="${entity.getDateWE()}" /></td>
                <td>
                    <a href="${pageContext.request.contextPath}/do?command=adminshoweditwaseatenform&id=<c:out value='${entity.getIdWE()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/do?command=admindeletewaseaten&id=<c:out value='${entity.getIdWE()}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
    <div class="clear"></div>
</div>

<jsp:include page="../siteparts/footer.jsp" />