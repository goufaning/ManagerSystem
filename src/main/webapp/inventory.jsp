<%--
  Created by IntelliJ IDEA.
  User: gfn
  Date: 2016-12-31
  Time: 10:28
  To change this templates use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="css/main.css" type="text/css" />
    <script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/style.js"></script>
    <script>
        $(document).ready(function() {
            $("#menu1").addClass("select").siblings().removeClass("select");
        });
    </script>

</head>
<body>
<div style="height: 700px">
    <jsp:include page="head.jsp"></jsp:include>
    <jsp:include page="menu.jsp"></jsp:include>
    <jsp:include page="main.jsp"></jsp:include>
</div>
</body>
</html>
