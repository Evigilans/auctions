<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>

<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" type="text/css" media="screen">
</head>

<body>
<jsp:include page="../service/header.jsp"></jsp:include>
<main>
    <c:choose>
        <c:when test="${empty errorMessage}">
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
                        <c:when test="${user == userProfile}">
                            <h1>Welcome to your profile, ${userProfile.name}!</h1>
                        </c:when>
                        <c:otherwise>
                            <h1>Welcome to profile of user ${userProfile.name}!</h1>
                        </c:otherwise>
                    </c:choose>
                    <section>
                        <c:choose>
                            <c:when test="${user == userProfile || user.admin}">
                                <div id="profile-pic">
                                    <img class="profile" src="images/avatar.png"/>
                                </div>
                                <div id="details">
                                    <h1>Name: ${userProfile.name}</h1>
                                    <h1>Email: ${userProfile.email}</h1>
                                    <h1>Balance: ${userProfile.balance}$</h1>
                                    <h1>Category: ${userProfile.category}</h1>
                                </div>
                                <ul>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/ApplicationController?command=edit-user-page&userId=${userProfile.id}">Click
                                            here to edit your profile</a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/ApplicationController?command=user-auctions-list&userId=${userProfile.id}">Click
                                            here to view lots</a>
                                    </li>
                                    <c:if test="${user.admin && userProfile.client}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/ApplicationController?command=promote-user&userId=${userProfile.id}">Click
                                                here to promote this user to 'Advanced'</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <div id="profile-pic">
                                    <img class="profile" src="images/avatar/avatar.png"/>
                                </div>
                                <div id="details">
                                    <h1>Name: ${userProfile.name}</h1>
                                    <h1>Email: ${userProfile.email}</h1>
                                    <h1>Category: ${userProfile.category}</h1>
                                </div>
                                <ul>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/ApplicationController?command=view-user-auctions&userId=${userProfile.id}">Click
                                            here to view lots</a>
                                    </li>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                    </section>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <h3>${errorMessage}</h3>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="../service/footer.jsp"></jsp:include>
</body>

</html>
