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

<jsp:include page="service/header.jsp"></jsp:include>

<main>
    <div class="form">
        <c:choose>
            <c:when test="${empty client}">
                <h1>Welcome, Guest</h1>
                <p> Чтобы просматривать свой профиль, вам необходимо <a
                        href="${pageContext.request.contextPath}/ApplicationServlet?command=link&url=pages/login.jsp">войти</a>
                    или зарегистрироваться.
                </p>
            </c:when>
            <c:otherwise>
                <h1>Enter auction info</h1>
                <form action="${pageContext.request.contextPath}/ApplicationServlet?command=createAuction"
                      method="post">
                    <div class="field-wrap">
                        <label>
                            Name<span class="req">*</span>
                        </label>
                        <input type="text" name="name" required autocomplete="off"/>
                    </div>
                    <div class="field-wrap">
                        <label>
                            Description<span class="req">*</span>
                        </label>
                        <input type="text" name="description" required autocomplete="off"/>
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
                    Create!</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
</main>

<jsp:include page="service/footer.jsp"></jsp:include>

</body>
</html>