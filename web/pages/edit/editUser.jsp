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
<jsp:include page="${pageContext.request.contextPath}/pages/service/header.jsp"></jsp:include>
<main>
    <c:choose>
        <c:when test="${not empty user && (user == userProfile || user.admin)}">
            <div class="form">
                <h1>Edit a profile!</h1>
                <form action="${pageContext.request.contextPath}/ApplicationController?command=edit-user&userId=${userProfile.id}"
                      method="post">
                    <div class="field-wrap">
                        <label>
                            Current email: ${userProfile.email}
                        </label>
                        <input type="text" disabled="disabled" required autocomplete="off"/>
                    </div>
                    <div class="field-wrap">
                        <label>
                            Current name: ${userProfile.name}
                        </label>
                        <input type="text" name="name" autocomplete="off"/>
                    </div>
                    <div class="field-wrap">
                        <label>
                            Current balance: ${userProfile.balance}
                        </label>
                        <input type="text" name="balance" autocomplete="off"/>
                    </div>
                    <button type="submit" class="button button-block"/>
                    Apply Changes!</button>
                </form>
            </div>
            <script src="${pageContext.request.contextPath}/js/login.js"></script>
        </c:when>
        <c:otherwise>
            <h1>We are sorry, but you do not have permission to view this page.</h1>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="${pageContext.request.contextPath}/pages/service/footer.jsp"></jsp:include>
</body>

</html>