<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<title>Error</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">

<jsp:include page="siteparts/header.jsp" />

<h2>Error</h2>
<h1 style="color: red;">${error}</h1>

<jsp:include page="siteparts/footer.jsp" />