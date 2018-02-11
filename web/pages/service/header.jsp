<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/temp.css" type="text/css" media="screen">
</head>
<body>

<header>
    <div class="topheader">
        <ul class="social">
            <li><a href=""><i class="ion-social-facebook"></i></a></li>
            <li><a href=""><i class="ion-social-twitter"></i></a></li>
            <li><a href=""><i class="ion-social-instagram-outline"></i></a></li>
        </ul>
        <ul class="shopping">
            <c:choose>
                <c:when test="${empty user}">
                    <li>Welcome, Guest</li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/user/login.jsp">Login/Register</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>Welcome, ${user.name}</li>
                    <li>
                        <a href="${pageContext.request.contextPath}/ApplicationController?command=logout">Logout</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>

    <div id="home">
        <a href="" id="logo"><img src="${pageContext.request.contextPath}/images/logo.png"
                                  alt="Logo"/></a>

        <div href="" id="burger"><i class="ion-navicon"></i></div>
    </div>

    <div class="menu">
        <ul class="menu-ul">
            <li><a href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/home.jsp">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/auction/create.jsp">Create
                    auction</a></li>
            <li><a href="${pageContext.request.contextPath}/ApplicationController?command=activeAuctionsList">Auctions
                List</a></li>
            <li><a href="${pageContext.request.contextPath}/ApplicationController?command=profile&userId=${user.id}">My
                Profile</a></li>
            <c:if test="${user.admin}">
                <li>
                    <a href="${pageContext.request.contextPath}/ApplicationController?command=link&url=pages/admin/manage.jsp">Auction
                        Manage</a></li>
            </c:if>
        </ul>
    </div>
    <script src="${pageContext.request.contextPath}/js/temp.js"></script>

    <div id="lang-menu">
        <div><img src="${pageContext.request.contextPath}/images/RU.png"> RU</div>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=language&language_id=en_US"><img
                        src="${pageContext.request.contextPath}/images/UK.png"> EN</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=language&language_id=ru_RU"><img
                        src="${pageContext.request.contextPath}/images/RU.png"> RU</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/ApplicationController?command=language&language_id=ru_RU"><img
                        src="${pageContext.request.contextPath}/images/BY.png"> BY</a>
            </li>
        </ul>
    </div>
    <script src="${pageContext.request.contextPath}/js/language.js"></script>
</header>

</body>
</html>