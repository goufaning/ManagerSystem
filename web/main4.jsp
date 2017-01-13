<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css"rel="stylesheet" href="css/main.css">
<script>
    function warehouseShow() {
        $("#warehouseshow").show();
        $(".back").click(function () {
            $("#warehouseshow").hide();
        })
    }
</script>
<div class="div_rigth">
    <label class="title"><a href="manager.jsp"><p class="back">&lt;&nbsp;返回</p></a> <span>仓库管理</span></label>
    <hr />
    <div class="menu" style="display: none">
        <a href="#" onclick="warehouseShow()"><img src="images/menu1.png"/><span>仓库管理</span></a>
        <a href="#" ><img src="images/menu2.png"/><span>仓库调拨</span></a>
        <a href="#"><img src="images/menu3.png"/><span>仓库盘点</span></a>
    </div>
    <div id="warehouseshow" class="work_area">
        <div class="title">
            <button>新建仓库</button>
            <button>删除仓库</button>
            <button>添加产品</button>
        </div>
        <hr/>
        <div class="main_form autoScroll">
            <form id="submitForm" name = "form2" method="post" >
                <table  frame=hsides>
                    <thead>
                    <tr>
                        <td width="20%">仓库名称</td>
                        <td width="20%">管理人员</td>
                        <td width="20%">面积</td>
                        <td width="20%">仓库地址</td>
                    </tr>
                    </thead>
                    <tbody id="tab">
                    <c:forEach var="warehouse" items="${wareHouseList}">
                        <tr>
                            <td>${warehouse.name}</td>
                            <td></td>
                            <td>${warehouse.area}</td>
                            <td>${warehouse.address}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>