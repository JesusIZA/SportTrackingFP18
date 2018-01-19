<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Food form</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="shortcut icon" type="image/x-icon" href="images/icon1.png">

<div class="admin">
<h1>Admin Page</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/do?command=adminshownewfoodform">Add New Food</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/do?command=adminshowlistfood">List All Foods</a>

    </h2>

<div align="center">
    <c:if test="${entity != null}">
        <form action="${pageContext.request.contextPath}/do?command=admineditfood" method="post">
    </c:if>
    <c:if test="${entity == null}">
        <form action="${pageContext.request.contextPath}/do?command=admininsertfood" method="post">
    </c:if>
        <table border="1" cellpadding="3">
            <caption>
                <h2>
                    <c:if test="${entity != null}">
                        Edit Food
                    </c:if>
                    <c:if test="${entity == null}">
                        Add New Food
                    </c:if>
                    </h2>
            </caption>
                <c:if test="${entity != null}">
                    <input type="hidden" name="id"  value="<c:out value='${entity.getIdF()}'/>" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="nameF" maxlength="250" size="50"
                               value="<c:out value='${entity.getName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Calories: </th>
                    <td>
                        <input type="text" name="caloriesF" maxlength="12" size="50"
                               value="<c:out value='${entity.getCalories()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Proteins: </th>
                    <td>
                        <input type="text" name="proteinsF" maxlength="12" size="50"
                               value="<c:out value='${entity.getProteins()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Fats: </th>
                    <td>
                        <input type="text" name="fatsF" maxlength="12" size="50"
                               value="<c:out value='${entity.getFats()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Carbohydrates: </th>
                    <td>
                        <input type="text" name="carbohydratesF" maxlength="12" size="50"
                               value="<c:out value='${entity.getCarbohydrates()}' />"
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