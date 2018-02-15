<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>

<html>

<head>
    <meta charset="UTF-8">
    <title>Sold.by - <fmt:message key="title" bundle="${rb}"/></title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>

<body>
<jsp:include page="../service/header.jsp"></jsp:include>

<main>
    <c:choose>
        <c:when test="${not empty user && user.admin}">
            <h3>Admin functions: </h3>
            <p>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=user-list"><strong>Manage
                    users</strong></a>
                <br>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=verifying-auctions-list"><strong>Verify
                    auctions</strong></a>
            </p>
        </c:when>
        <c:otherwise>
            <p> Данная страница доступна только для администратора! </p>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="../service/footer.jsp"></jsp:include>
</body>
</html>
