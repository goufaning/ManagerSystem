<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css"rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/menu.js"></script>
<div id="option">
    <div id="jcFrame">
        <ul id="jcSide">
            <p>查询</p>
            <li id="menu1" style="margin-top: 10px"><a href="${pageContext.request.contextPath}/InventoryServlet">产品库存</a></li>
            <p>出入</p>
            <li id="menu2"><a href="in.jsp">入库管理</a></li>
            <li id="menu3"><a href="out.jsp">出库管理</a></li>
            <li id="menu4"><a href="manager.jsp">仓库管理</a></li>
            <p>报表</p>
            <li id="menu5"><a href="${pageContext.request.contextPath}/InBaobiaoServlet">统计报表</a></li>
        </ul>
    </div>
</div>
