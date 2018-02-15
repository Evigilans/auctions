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
<jsp:include page="service/header.jsp"></jsp:include>
<main>
    <h2 class="heading"><fmt:message key="home.label.welcome" bundle="${rb}"/></h2>

    <p><fmt:message key="home.paragraph.first" bundle="${rb}"/></p>

    <p><fmt:message key="home.paragraph.second" bundle="${rb}"/></p>

    <p><fmt:message key="home.paragraph.third" bundle="${rb}"/></p>

    <c:choose>
        <c:when test="">

        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

</main>
<jsp:include page="service/footer.jsp"></jsp:include>
</body>

</html>