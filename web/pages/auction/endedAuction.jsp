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
<jsp:include page="../service/header.jsp"></jsp:include>

<main>
    <div class="form">
        <c:choose>
            <c:when test="${empty user}">
                <p>
                    Чтобы просматривать аукционы, вам необходимо <a
                        href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/user/login.jsp"><strong>зарегистрироваться
                    или войти.</strong></a>
                </p>
            </c:when>
            <c:otherwise>
                <h3>Информация об аукционе #${auction.id} ${auction.lot.name}</h3>
                <p>Описание лота: ${auction.lot.description}</p>
                <c:choose>
                    <c:when test="${empty auction.currentMaximalBid}">
                        <h1>Auction ended unsuccessfully</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Winner bid is ${auction.currentMaximalBid.value}$</h1>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>

</main>

<jsp:include page="../service/footer.jsp"></jsp:include>
</body>
</html>
