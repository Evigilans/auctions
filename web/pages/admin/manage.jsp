<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<jsp:include page="../service/header.jsp"></jsp:include>

<main>
    <c:choose>
        <c:when test="${user.admin}">
            <h3>Available functions: </h3>
            <li>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=verifyingAuctionsList">Verify auctions</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/home.jsp">Edit users</a>
            </li>
        </c:when>
        <c:otherwise>
            Данная страница доступна только для администратора! <br>
        </c:otherwise>
    </c:choose>

</main>

<jsp:include page="../service/footer.jsp"></jsp:include>

</body>
</html>
