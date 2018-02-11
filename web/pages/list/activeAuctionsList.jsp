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
    <div id="sandbox">
        <button class="sort" data-sort="project-name">
            Sort by name
        </button>
        <button class="sort" data-sort="project-title">
            Sort by Project
        </button>
        <button class="sort" id="viewSwitch">
            Change View
        </button>

        <ul class="list-au" id="list-au">
            <c:forEach var="auction" items="${auctions}">
                <li>
                    <img src="http://placehold.it/120x120" alt="#"/>
                    <h3 class="project-name"><a
                            href="${pageContext.request.contextPath}/ApplicationController?command=auction&auctionId=${auction.id}">${auction.lot.name}</a>
                    </h3>
                    <p>${auction.lot.description}</p>
                    <p class="project-label">Maximal bid: </p>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/list.js"></script>

<jsp:include page="${pageContext.request.contextPath}/pages/service/footer.jsp"></jsp:include>

</body>
</html>
