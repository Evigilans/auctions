<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" type="text/css" media="screen">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"></script>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/pages/service/header.jsp"></jsp:include>

<main>
    <c:choose>
        <c:when test="${user.admin}">
            <div class="container">
                <div class="dc-view-switcher">
                    <button data-trigger="grid-view"></button>
                    <button data-trigger="list-view" class="active"></button>
                </div>
                <div data-view="list-view" class="download-cards">
                    <c:forEach var="userProfile" items="${users}">
                        <article class="download-card">
                            <div class="download-card__icon-box"><img
                                    src="images/avatar/avatar_${userProfile.id}.png"/></div>
                            <div class="download-card__content-box">
                                <div class="content">
                                    <h3 class="download-card__content-box__title">
                                        <a href="${pageContext.request.contextPath}/ApplicationController?command=profile&userId=${userProfile.id}">${userProfile.name}</a>
                                    </h3>
                                    <p class="download-card__content-box__description">
                                        Email: ${userProfile.email} <br>
                                        Category: ${userProfile.category}
                                    </p>
                                </div>
                            </div>
                        </article>
                    </c:forEach>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <h1>We are sorry, but you do not have permission to view this page.</h1>
        </c:otherwise>
    </c:choose>


</main>
<script src="${pageContext.request.contextPath}/js/list.js"></script>

<jsp:include page="${pageContext.request.contextPath}/pages/service/footer.jsp"></jsp:include>

</body>
</html>
