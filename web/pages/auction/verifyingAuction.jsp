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
    <c:choose>
        <c:when test="${not empty user && (user.admin || user == auction.lot.owner)}">
            <h3>Информация об аукционе #${auction.id} ${auction.lot.name}</h3>
            <p>Описание лота: ${auction.lot.description}</p>
            <div class="form">
                <h1>Verify auction?</h1>
                <form action="${pageContext.request.contextPath}/ApplicationController?command=verify-auction&auctionId=${auction.id}"
                      method="post">
                    <button type="submit" name="acceptAuction" class="button button-block"/>
                    Accept auction!</button> <br>
                    <button type="submit" name="declineAuction" class="button button-block"/>
                    Decline auction!</button>
                </form>
            </div>
            <c:if test="${user == auction.lot.owner}">
                <p>
                    <a href="${pageContext.request.contextPath}/ApplicationController?command=withdraw-auction&auctionId=${auction.id}"><strong>Click
                        here to withdraw auction.</strong></a>
                </p>
            </c:if>
        </c:when>
        <c:otherwise>
            <h1>We are sorry, but you do not have permission to view this page.</h1>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="${pageContext.request.contextPath}/pages/service/footer.jsp"></jsp:include>
</body>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

</html>
