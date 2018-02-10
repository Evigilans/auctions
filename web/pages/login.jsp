<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<jsp:include page="service/header.jsp"></jsp:include>

<main>
    <div class="form">
        <ul class="tab-group">
            <li class="tab active"><a href="#signup">Sign Up</a></li>
            <li class="tab"><a href="#login">Log In</a></li>
        </ul>
        <div class="tab-content">
            <div id="signup">
                <h1>Sign up for free</h1>
                <form action="${pageContext.request.contextPath}/ApplicationServlet?command=registration" method="post">
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
            <div id="login">
                <h1>Welcome Back!</h1>
                <form action="${pageContext.request.contextPath}/ApplicationServlet?command=login" method="post">
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
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>

</main>

<jsp:include page="service/footer.jsp"></jsp:include>
</body>
</html>