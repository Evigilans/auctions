<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>

<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controls.css" type="text/css" media="screen">
</head>

<body>
<jsp:include page="../service/header.jsp"></jsp:include>
<main>
    <c:choose>
        <c:when test="${empty user}">
            <p>
                Чтобы просматривать профили, Вам необходимо <a
                    href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/user/login.jsp"><strong>войти
                или зарегистрироваться.</strong></a>
            </p>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${empty errorMessage}">
                    <c:choose>
                        <c:when test="${user == userProfile}">
                            <h3>Welcome to your profile, ${userProfile.name}!</h3>
                            <div id="profile">
                                <div class="img"></div>
                                <a href="" class="btn btn_left">${userProfile.email}</a>
                                <div class="info">
                                    Your login: <strong>${userProfile.login}</strong> <br>
                                    Your balance: <strong>${userProfile.balance}</strong> <br>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h3>Welcome to profile of user ${userProfile.name}!</h3>
                            <div id="profile">
                                <div class="img"></div>
                                <a href="" class="btn btn_left">${userProfile.email}</a>
                                <div class="info">
                                    User login: <strong>${userProfile.login}</strong> <br>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${user == userProfile}">
                        <a href="${pageContext.request.contextPath}/ApplicationController?command=editUserPage&userId=${userProfile.id}">Click
                            here to edit your profile</a>
                    </c:if>
                    <c:if test="${user.admin}">
                        <a href="${pageContext.request.contextPath}/ApplicationController?command=editUserPage&userId=${userProfile.id}">Click
                            here to edit this user profile profile</a>
                        <a href="${pageContext.request.contextPath}/ApplicationController?command=editUserPage&userId=${userProfile.id}">Click
                            here to promote this user to 'Advanced'</a>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <h3>${errorMessage}</h3>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="../service/footer.jsp"></jsp:include>
</body>

</html>
