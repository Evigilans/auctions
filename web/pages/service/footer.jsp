<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen">
</head>
<body>

<footer>
    2018 @ SOLD.BY <strong>all rights reserved</strong>.
</footer>

</body>
</html>