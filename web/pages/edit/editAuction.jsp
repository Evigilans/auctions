<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>SOLD.BY - прродавай и покупай!</title>
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
                    Чтобы изменять аукционы Вам необходимо <a
                        href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/user/login.jsp"><strong>зарегистрироваться
                    или войти.</strong></a>
                </p>
            </c:when>
            <c:otherwise>
                <h1>Edit an auction</h1>
                <form action="${pageContext.request.contextPath}/ApplicationController?command=edit-auction&auctionId=${auction.id}"
                      method="post">
                    <div class="field-wrap">
                        <label>
                            Name<span class="req">*</span>
                        </label>
                        <input type="text" name="lotName" required autocomplete="off"/>
                    </div>
                    <div class="field-wrap">
                        <label>
                            Description<span class="req">*</span>
                        </label>
                        <input type="text" name="lotDescription" required autocomplete="off"/>
                    </div>
                    <div class="field-wrap">
                        <label>
                            Start Price<span class="req">*</span>
                        </label>
                        <input type="text" name="startPrice" required autocomplete="off"/>
                    </div>
                    <div class="field-wrap">
                        <label>
                            Length in days<span class="req">*</span>
                        </label>
                        <input type="text" name="days" required autocomplete="off"/>
                    </div>
                    <button type="submit" class="button button-block"/>
                    Apply changes!</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
</main>

<jsp:include page="../service/footer.jsp"></jsp:include>

</body>
</html>