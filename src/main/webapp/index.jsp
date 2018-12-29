<%--
  Created by IntelliJ IDEA.
  User: gfn
  Date: 2017-01-01
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css"rel="stylesheet" href="css/main.css">
    <link rel="shortcut icon" href="../favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/demo.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
</head>
<body>
<div id="login">
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <div class="stand">
            <div class="form">
                <input type="text" name="username" class="zocial-dribbble" placeholder="用户名" />
                <input type="password" name="password" placeholder="密码" />
                <input type="submit" value="登陆" />
                <a href="">忘记密码</a>
            </div>
        </div>
        <script src='js/jquery-1.11.2.min.js'></script>
    </form>
</div>
</body>
</html>
