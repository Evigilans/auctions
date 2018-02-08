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
        <c:when test="${empty user}">
            <p> Чтобы просматривать свой профиль, вам необходимо <a
                    href="${pageContext.request.contextPath}/ApplicationServlet?command=link&url=pages/login.jsp">войти</a>
                или зарегистрироваться.
            </p>
        </c:when>
        <c:otherwise>
            <h3>Your profile</h3>

            <p>
                Your Name: ${user.name}
            </p>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="service/footer.jsp"></jsp:include>

</body>
</html>
