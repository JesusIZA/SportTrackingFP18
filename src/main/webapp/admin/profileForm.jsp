<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Profile form</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="../siteparts/header.jsp" />
<div class="admin">
<h1>Admin Page</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewprofileform">Add New Profile</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistprofile">List All Profiles</a>

    </h2>

<div align="center">
    <c:if test="${entity != null}">
        <form action="${pageContext.request.contextPath}/do?command=admineditprofile" method="post">
    </c:if>
    <c:if test="${entity == null}">
        <form action="${pageContext.request.contextPath}/do?command=admininsertprofile" method="post">
    </c:if>
        <table border="1" cellpadding="3">
            <caption>
                <h2>
                    <c:if test="${entity != null}">
                        Edit Profile
                    </c:if>
                    <c:if test="${entity == null}">
                        Add New Profile
                    </c:if>
                    </h2>
            </caption>
                <c:if test="${entity != null}">
                    <input type="hidden" name="id"  value="<c:out value='${entity.getIdP()}'/>" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="nameP" maxlength="56" size="50"
                               value="<c:out value='${entity.getName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Surname: </th>
                    <td>
                        <input type="text" name="surnameP" maxlength="56" size="50"
                               value="<c:out value='${entity.getSurname()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Sex: </th>
                    <td>
                        <input type="text" name="sexP" maxlength="12" size="50"
                               value="<c:out value='${entity.getSex()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Birthday: </th>
                    <td>
                        <input type="date" name="birthdayP" maxlength="56" size="50"
                               value="<c:out value='${entity.getBirthday()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Height: </th>
                    <td>
                        <input type="number" name="heightP" maxlength="56" size="50" min="1" max="999"
                               value="<c:out value='${entity.getHeight()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Weight: </th>
                    <td>
                        <input type="number" name="weightP" maxlength="56" size="50" min="1" max="999"
                               value="<c:out value='${entity.getWeight()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Active time: </th>
                    <td>
                        <input type="number" name="activeTimeP" maxlength="56" size="50" min="1" max="99"
                               value="<c:out value='${entity.getActiveTime()}' />"
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

<jsp:include page="../siteparts/footer.jsp" />