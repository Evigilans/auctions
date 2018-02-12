<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="resource.content" var="rb"/>

<html>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controls.css" type="text/css" media="screen">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>

<body>
<jsp:include page="../service/header.jsp"></jsp:include>
<main>
    <c:choose>
        <c:when test="${empty user}">
            <div class="form">
                <ul class="tab-group">
                    <li class="tab active"><a href="#login">Log In</a></li>
                    <li class="tab"><a href="#signup">Sign Up</a></li>
                </ul>
                <div class="tab-content">
                    <div id="login">
                        <c:choose>
                            <c:when test="${empty loginErrorMessage}">
                                <h1>Welcome Back!</h1>
                            </c:when>
                            <c:otherwise>
                                <h1>${loginErrorMessage}</h1>
                            </c:otherwise>
                        </c:choose>
                        <form action="${pageContext.request.contextPath}/ApplicationController?command=login"
                              method="post">
                            <div class="field-wrap">
                                <label>
                                    Login<span class="req">*</span>
                                </label>
                                <input type="text" name="login" required autocomplete="off"/>
                            </div>
                            <div class="field-wrap">
                                <label>
                                    Password<span class="req">*</span>
                                </label>
                                <input type="password" name="password" required autocomplete="off"/>
                            </div>
                            <p class="forgot"><a href="#">Forgot Password?</a></p>
                            <button class="button button-block"/>
                            Log In</button>
                        </form>
                    </div>
                    <div id="signup">
                        <c:choose>
                            <c:when test="${empty registerErrorMessage}">
                                <h1>Sign up for free</h1>
                            </c:when>
                            <c:otherwise>
                                <h1>${registerErrorMessage}</h1>
                            </c:otherwise>
                        </c:choose>
                        <form action="${pageContext.request.contextPath}/ApplicationController?command=register"
                              method="post">
                            <div class="top-row">
                                <div class="field-wrap">
                                    <label>
                                        Login<span class="req">*</span>
                                    </label>
                                    <input type="text" name="login" required autocomplete="off"/>
                                </div>
                                <div class="field-wrap">
                                    <label>
                                        Full name<span class="req">*</span>
                                    </label>
                                    <input type="text" name="name" required autocomplete="off"/>
                                </div>
                            </div>
                            <div class="field-wrap">
                                <label>
                                    Email address<span class="req">*</span>
                                </label>
                                <input type="email" name="email" required autocomplete="off"/>
                            </div>
                            <div class="field-wrap">
                                <label>
                                    Set a password<span class="req">*</span>
                                </label>
                                <input type="password" name="password" required autocomplete="off"/>
                            </div>
                            <button type="submit" class="button button-block"/>
                            Get Started</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <p>
                You already logged in a system. You can click <a
                    href="${pageContext.request.contextPath}/ApplicationController?command=logout"><strong>here to
                logout.</strong></a>
            </p>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="../service/footer.jsp"></jsp:include>
</body>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

</html>