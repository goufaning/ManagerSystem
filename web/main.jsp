<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css"rel="stylesheet" href="css/main.css">

<div id="a" class="div_rigth">
    <label class="title"><a href="#" class="back"><&nbsp;返回</a> <span>产品库存</span></label>
    <hr />
    <div class="main_form autoScroll" style="height: 600px">
        <table  frame=hsides>
            <thead>
            <tr>
                <td width="35px">序号</td>
                <td width="110px">品名型号</td>
                <td width="110px">规格</td>
                <td width="110px">最近出入库时间</td>
                <td width="110px">单价</td>
                <td width="110px">数量</td>
                <td width="110px">仓库</td>
            </tr>
            </thead>
            <tbody id="tab">
            <c:forEach var="inventory" items="${inventoryList}" varStatus="status">
                <c:set var="productId" value="${inventory.productId}"/>
                <c:set var="warehouseId" value="${inventory.warehouseId}"/>
            <tr>
                <td>${status.index+1}</td>
                <td>${productmap[productId].name}</td>
                <td>台</td>
                <td>${inventory.updateDate}</td>
                <td>${productmap[productId].price}</td>
                <td>${inventory.num}</td>
                <td>${warehousemap[warehouseId].name}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>