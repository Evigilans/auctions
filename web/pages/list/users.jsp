<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>SOLD.BY - прродавай и покупай!</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/service/header.jsp"></jsp:include>

<main>
    <div id="container">
        <div class="buttons">
            View:
            <button class="grid square-btn"><i class="fa fa-th-large fa-3x"></i></button>
            <button class="list square-btn"><i class="fa fa-bars fa-3x"></i></button>
        </div>
        <ul class="list">
            <c:forEach var="user" items="${users}">
                <li>
                    <h2>
                        <a href="${pageContext.request.contextPath}/ApplicationServlet?command=profile&userId=${user.id}">${user.name}</a>
                    </h2>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/list.js"></script>

<jsp:include page="${pageContext.request.contextPath}/service/footer.jsp"></jsp:include>

</body>
</html>