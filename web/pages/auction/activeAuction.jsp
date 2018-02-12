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
    <h3>Информация об аукционе #${auction.id} ${auction.lot.name}</h3>
    <p>Описание лота: ${auction.lot.description}</p>

    <c:choose>
        <c:when test="">

        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

    <div class="form">
        <c:choose>
            <c:when test="${empty user}">
                <h1>Welcome, Guest</h1>
                <p>
                    Чтобы просматривать свой профиль, вам необходимо <a
                        href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/user/login.jsp"><strong>войти
                    или зарегистрироваться.</strong></a>
                </p>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty auction.currentMaximalBid}">
                        <h1>Make first bid!</h1>
                        <h1>Start price is ${auction.lot.startPrice}$</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Make your bid!</h1>
                        <h1>Current maximal bid is ${auction.currentMaximalBid.value}$</h1>
                    </c:otherwise>
                </c:choose>
                <form action="${pageContext.request.contextPath}/ApplicationController?command=make-bid&auctionId=${auction.id}"
                      method="post">
                    <div class="field-wrap">
                        <label>
                            Value<span class="req">*</span>
                        </label>
                        <input type="text" name="bidValue" required autocomplete="off"/>
                    </div>
                    <button type="submit" class="button button-block"/>
                    Make a bid!</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>

</main>

<jsp:include page="../service/footer.jsp"></jsp:include>
</body>
</html>
