<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Link Admin List</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="../siteparts/header.jsp" />

<div class="admin">
<h1>Admin Page</h1>
    <h3>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewlinkform"><strong>Add New Link</strong></a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistuser">List Users</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistprofile">List Profiles</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistnorm">List Norms</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistfood">List Foods</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistwaseaten">List Was eaten</a>

    </h3>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Links</h2></caption>
        <tr>
            <th>IdL</th>
            <th>Id User</th>
            <th>Id Profile</th>
            <th>Id Norm</th>
        </tr>
        <c:forEach var="entity" items="${listEntity}">
            <tr>
                <td><c:out value="${entity.getIdL()}" /></td>
                <td><c:out value="${entity.getIdU()}" /></td>
                <td><c:out value="${entity.getIdP()}" /></td>
                <td><c:out value="${entity.getIdN()}" /></td>
                <td>
                    <a href="${pageContext.request.contextPath}/do?command=adminshoweditlinkform&id=<c:out value='${entity.getIdL()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/do?command=admindeletelink&id=<c:out value='${entity.getIdL()}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
    <div class="clear"></div>
</div>

<jsp:include page="../siteparts/footer.jsp" />