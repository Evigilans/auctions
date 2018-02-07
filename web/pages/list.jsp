<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controls.css" type="text/css" media="screen">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>

<jsp:include page="service/header.jsp"></jsp:include>

<main>
    <div id="container">
        <div class="buttons">
            View:
            <button class="grid square-btn"><i class="fa fa-th-large fa-3x"></i></button>
            <button class="list square-btn"><i class="fa fa-bars fa-3x"></i></button>
        </div>
        <ul class="list">
            <c:forEach var="auction" items="${auctions}">
                <li>
                    <h2>
                        <a href="${pageContext.request.contextPath}/ApplicationServlet?command=auction&id=${auction.id}">${auction.lot.name}</a>
                    </h2>
                    <p>Auction description</p>
                    <span class="author">Jean</span><span class="date">11/11/15</span><span class="time">7:22pm</span>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/list.js"></script>

<jsp:include page="service/footer.jsp"></jsp:include>

</body>
</html>
