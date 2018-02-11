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

    <div id="wrapper">
        <div id="slider-wrap">
            <ul id="slider">
                <li data-color="#1abc9c">
                    <div>
                        <h3>Slide #1</h3>
                        <span>Sub-title #1</span>
                    </div>
                    <i class="fa fa-image"></i>
                </li>

                <li data-color="#3498db">
                    <div>
                        <h3>Slide #2</h3>
                        <span>Sub-title #2</span>
                    </div>
                    <i class="fa fa-gears"></i>
                </li>

                <li data-color="#9b59b6">
                    <div>
                        <h3>Slide #3</h3>
                        <span>Sub-title #3</span>
                    </div>
                    <i class="fa fa-sliders"></i>
                </li>

                <li data-color="#34495e">
                    <div>
                        <h3>Slide #4</h3>
                        <span>Sub-title #4</span>
                    </div>
                    <i class="fa fa-code"></i>
                </li>

                <li data-color="#e74c3c">
                    <div>
                        <h3>Slide #5</h3>
                        <span>Sub-title #5</span>
                    </div>
                    <i class="fa fa-microphone-slash"></i>
                </li>
            </ul>

            <div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
            <div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
            <div id="counter"></div>

            <div id="pagination-wrap">
                <ul>
                </ul>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/slider.js"></script>

    <p>Текущая максимальная ставка: ${auction.lot.description}</p>

    <div class="form">
        <h1>Current maximal bid is: ${auction.currentMaximalBid.value}</h1>

        <c:choose>
            <c:when test="${empty user}">
                <h1>Welcome, Guest</h1>
                <p> Чтобы просматривать свой профиль, вам необходимо <a
                        href="${pageContext.request.contextPath}/ApplicationServlet?command=link&url=pages/login.jsp">войти</a>
                    или зарегистрироваться.
                </p>
            </c:when>
            <c:otherwise>
                <h1>Verify auction!</h1>
                <form action="${pageContext.request.contextPath}/ApplicationServlet?command=verify&auctionId=${auction.id}" method="post">
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
