<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <c:choose>
        <c:when test="${not empty user && user.admin}">
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
                    <c:forEach var="user" items="${users}">
                        <li>
                            <img src="http://placehold.it/120x120" alt="#"/>
                            <h3 class="project-name"><a
                                    href="${pageContext.request.contextPath}/ApplicationController?command=profile&userId=${user.id}">${user.name}</a>
                            </h3>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <script src="${pageContext.request.contextPath}/js/list.js"></script>
        </c:when>
        <c:otherwise>
            <h1>We are sorry, but you do not have permission to view this page.</h1>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="${pageContext.request.contextPath}/pages/service/footer.jsp"></jsp:include>
</body>

</html>
