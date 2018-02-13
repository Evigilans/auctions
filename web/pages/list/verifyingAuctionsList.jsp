<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
                    <c:forEach var="auction" items="${auctions}">
                        <article class="download-card">
                            <div class="download-card__icon-box"><img src="images/question-512.png"/></div>
                            <div class="download-card__content-box">
                                <div class="content">
                                    <h3 class="download-card__content-box__title">
                                        <a href="${pageContext.request.contextPath}/ApplicationController?command=show-verifying-auction&auctionId=${auction.id}">${auction.lot.name}</a>
                                    </h3>
                                    <p class="download-card__content-box__description">
                                        High & low-res photos for print and web media.
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
