<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>User Admin List</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="../siteparts/header.jsp" />

<div class="admin">
<h1>Admin Page</h1>
    <h3>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewuserform"><strong>Add New User</strong></a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistfood">List Foods</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistprofile">List Profiles</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistnorm">List Norms</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistwaseaten">List Were eaten</a>
        &nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistlink">List Links</a>

    </h3>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>IdU</th>
            <th>Login</th>
            <th>Password</th>
        </tr>
        <c:forEach var="entity" items="${listEntity}">
            <tr>
                <td><c:out value="${entity.getIdU()}" /></td>
                <td><c:out value="${entity.getLogin()}" /></td>
                <td><c:out value="${entity.getPassword()}" /></td>
                <td>
                    <a href="${pageContext.request.contextPath}/do?command=adminshowedituserform&id=<c:out value='${entity.getIdU()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/do?command=admindeleteuser&id=<c:out value='${entity.getIdU()}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
    <div class="clear"></div>
</div>

<jsp:include page="../siteparts/footer.jsp" />