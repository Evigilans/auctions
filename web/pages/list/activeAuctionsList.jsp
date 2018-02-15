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
    <div class="container">
        <div class="dc-view-switcher">
            <button data-trigger="grid-view"></button>
            <button data-trigger="list-view" class="active"></button>
        </div>
        <div data-view="list-view" class="download-cards">
            <c:forEach var="auction" items="${auctions}">
                <article class="download-card">
                    <div class="download-card__icon-box"><img src="images/question-512.png"/></div>
                    <div class="download-card__content-box">
                        <div class="content">
                            <h3 class="download-card__content-box__title">
                                <a href="${pageContext.request.contextPath}/ApplicationController?command=show-active-auction&auctionId=${auction.id}">${auction.lot.name}</a>
                            </h3>
                            <p class="download-card__content-box__description">
                                Owner: <a
                                    href="${pageContext.request.contextPath}/ApplicationController?command=profile&userId=${auction.lot.owner.id}">${auction.lot.owner.name}</a><br>
                                Type: ${auction.type} <br>
                                <c:choose>
                                    <c:when test="${not empty auction.currentMaximalBid}">
                                        Current maximal bid: ${auction.currentMaximalBid.value}
                                    </c:when>
                                    <c:otherwise>
                                        Not bids has been made yet.
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                </article>
            </c:forEach>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/list.js"></script>

<jsp:include page="${pageContext.request.contextPath}/pages/service/footer.jsp"></jsp:include>

</body>
</html>
