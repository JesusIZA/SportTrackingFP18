<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Link form</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<div class="admin">
<h1>Admin Page</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewlinkform">Add New Link</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistlink">List All Link</a>

    </h2>

<div align="center">
    <c:if test="${entity != null}">
        <form action="${pageContext.request.contextPath}/do?command=admineditlink" method="post">
    </c:if>
    <c:if test="${entity == null}">
        <form action="${pageContext.request.contextPath}/do?command=admininsertlink" method="post">
    </c:if>
        <table border="1" cellpadding="3">
            <caption>
                <h2>
                    <c:if test="${entity != null}">
                        Edit Link
                    </c:if>
                    <c:if test="${entity == null}">
                        Add New Link
                    </c:if>
                    </h2>
            </caption>
                <c:if test="${entity != null}">
                    <input type="hidden" name="id"  value="<c:out value='${entity.getIdL()}'/>" />
                </c:if>
                <tr>
                    <th>Id User: </th>
                    <td>
                        <input type="number" name="idUL" maxlength="12" size="50"
                               value="<c:out value='${entity.getIdU()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Id Profile: </th>
                    <td>
                        <input type="number" name="idPL" maxlength="12" size="50"
                               value="<c:out value='${entity.getIdP()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Id Norm: </th>
                    <td>
                        <input type="number" name="idNL" maxlength="12" size="50"
                               value="<c:out value='${entity.getIdN()}' />"
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
