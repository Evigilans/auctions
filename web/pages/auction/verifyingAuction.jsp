<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controls.css" type="text/css" media="screen">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/pages/service/header.jsp"></jsp:include>

<main>
    <h3>Информация об аукционе #${auction.id} ${auction.lot.name}</h3>
    <p>Описание лота: ${auction.lot.description}</p>

    <p>Текущая максимальная ставка: ${auction.lot.description}</p>

    <div class="form">
        <h1>Current maximal bid is: ${auction.currentMaximalBid.value}</h1>

        <c:choose>
            <c:when test="${empty user}">
                <h1>Welcome, Guest</h1>
                <p> Чтобы просматривать свой профиль, вам необходимо <a
                        href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/user/login.jsp">войти</a>
                    или зарегистрироваться.
                </p>
            </c:when>
            <c:otherwise>
                <h1>Verify auction!</h1>
                <form action="${pageContext.request.contextPath}/ApplicationController?command=verify&auctionId=${auction.id}" method="post">
                    <button type="submit" name="acceptAuction" class="button button-block"/>Accept auction!</button>
                    <button type="submit" name="declineAuction" class="button button-block"/>Decline auction!</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>

</main>

<jsp:include page="${pageContext.request.contextPath}/pages/service/footer.jsp"></jsp:include>

</body>
</html>
