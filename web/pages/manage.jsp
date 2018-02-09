<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<jsp:include page="service/header.jsp"></jsp:include>

<main>
    <c:choose>
        <c:when test="${user.admin}">
            Управление аукционами: <br>
        </c:when>
        <c:otherwise>
            Данная страница доступна только для администратора! <br>
        </c:otherwise>
    </c:choose>

</main>

<jsp:include page="service/footer.jsp"></jsp:include>

</body>
</html>
