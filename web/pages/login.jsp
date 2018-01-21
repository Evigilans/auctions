<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<jsp:include page="service/header.jsp"></jsp:include>
<jsp:include page="service/menu.jsp"></jsp:include>

<h3>Login Page</h3>
<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/AuctionServlet?command=login">
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName" value="${user.userName}"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${user.password}"/></td>
        </tr>
        <tr>
            <td>Remember me</td>
            <td><input type="checkbox" name="rememberMe" value="Y"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/AuctionServlet?command=home">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<p style="color:blue;">User Name: test, password: test</p>

<jsp:include page="service/footer.jsp"></jsp:include>
</body>
</html>