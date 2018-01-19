<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>User form</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<div class="admin">
<h1>Admin Page</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewuserform">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistuser">List All Users</a>

    </h2>

<div align="center">
    <c:if test="${entity != null}">
        <form action="${pageContext.request.contextPath}/do?command=adminedituser" method="post">
    </c:if>
    <c:if test="${entity == null}">
        <form action="${pageContext.request.contextPath}/do?command=admininsertuser" method="post">
    </c:if>
        <table border="1" cellpadding="3">
            <caption>
                <h2>
                    <c:if test="${entity != null}">
                        Edit User
                    </c:if>
                    <c:if test="${entity == null}">
                        Add New User
                    </c:if>
                    </h2>
            </caption>
                <c:if test="${entity != null}">
                    <input type="hidden" name="id"  value="<c:out value='${entity.getIdU()}'/>" />
                </c:if>
                <tr>
                    <th>Login: </th>
                    <td>
                        <input type="text" name="loginU" maxlength="56" size="50"
                               value="<c:out value='${entity.getLogin()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input type="password" name="passwordU" maxlength="56" size="50"
                               value="<c:out value='${entity.getPassword()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
        <br>
    <div class="clear"></div>
</div>
    <div class="clear"></div>
</div>
