<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Was eaten form</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<jsp:include page="../siteparts/header.jsp" />
<div class="admin">
<h1>Admin Page</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewnormform">Add New Was eaten</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistnorm">List All Were eaten</a>

    </h2>

<div align="center">
    <c:if test="${entity != null}">
        <form action="${pageContext.request.contextPath}/do?command=admineditwaseaten" method="post">
    </c:if>
    <c:if test="${entity == null}">
        <form action="${pageContext.request.contextPath}/do?command=admininsertwaseaten" method="post">
    </c:if>
        <table border="1" cellpadding="3">
            <caption>
                <h2>
                    <c:if test="${entity != null}">
                        Edit Was eaten
                    </c:if>
                    <c:if test="${entity == null}">
                        Add New Was eaten
                    </c:if>
                    </h2>
            </caption>
                <c:if test="${entity != null}">
                    <input type="hidden" name="id"  value="<c:out value='${entity.getIdWE()}'/>" />
                </c:if>
                <tr>
                    <th>Id Profile: </th>
                    <td>
                        <input type="number" name="idPWE" maxlength="12" size="50"
                               value="<c:out value='${entity.getIdP()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Id Food: </th>
                    <td>
                        <input type="number" name="idFWE" maxlength="12" size="50"
                               value="<c:out value='${entity.getIdF()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date: </th>
                    <td>
                        <input type="date" name="dateWE"
                               value="<c:out value='${entity.getDateWE()}' />"
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