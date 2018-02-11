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
    <h2 class="heading"><fmt:message key="home.label.welcome" bundle="${rb}"/></h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Possimus voluptate obcaecati nesciunt porro laudantium,
        autem expedita cum fugit cupiditate itaque debitis sint, tenetur accusamus, omnis! WHAT</p>

    <p>Est, dolorum, inventore? Soluta illo necessitatibus facilis omnis nam ipsam, laudantium voluptas veniam ipsa. Ea
        debitis, explicabo! Fugit repudiandae ex unde assumenda, numquam aliquam architecto?</p>

    <p>Beatae minima, ipsam nisi rerum commodi. Culpa quod quibusdam, odit ut! Reprehenderit officiis sint suscipit,
        neque, mollitia minus? Ab earum cum nam at, quos id!</p>
</main>

<jsp:include page="${pageContext.request.contextPath}/service/footer.jsp"></jsp:include>

</body>
</html>