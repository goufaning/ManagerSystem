<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css"rel="stylesheet" href="css/main.css">
<script type="text/javascript">
    $(function(){
        var $div_li =$("div.tab_menu ul li");
        $div_li.click(function(){
            $(this).addClass("selected")            //当前<li>元素高亮
                .siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
            var index =  $div_li.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
            $("div.tab_box > div")       //选取子节点。不选取子节点的话，会引起错误。如果里面还有div
                .eq(index).show()   //显示 <li>元素对应的<div>元素
                .siblings().hide(); //隐藏其它几个同辈的<div>元素
        })
        $(".close img").mouseenter(function () {
            $(this).attr("src","images/Error.png");
        })
        $(".close img").mouseleave(function () {
            $(this).attr("src","images/close.png");
        })
    })
</script>
<div id="overlay"></div>
<div class="div_rigth">
    <div class="menu">
        <a href="javascript:;" ><img src="images/menu1.png" onclick="showOverlay()"/><span>入库统计</span></a>
        <a href="#" ><img src="images/menu1.png" onclick="showOverlay1()"/><span>出库统计</span></a>
    </div>
</div>
<div id="in" class="in_out">
<p class="title">入库报表<a class="close" onclick="hideOverlay() "><img src="images/close.png"></a></p>
    <div class="tab">
        <div class="tab_menu">
            <ul>
                <li class="selected">入库日报</li>
                <li>入库月报</li>
                <li>季度报表</li>
            </ul>
        </div>
        <div class="tab_box">
            <div>
                <div class="main_form autoScroll">
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30px">序号</th>
                            <th width="100px">供应商</th>
                            <th width="100px">时间</th>
                            <th width="100px">入库仓库</th>
                            <th width="100px">负责人</th>
                            <th width="100px">产品名称</th>
                            <th width="100px">数量</th>
                            <th width="100px">金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${baobiaos}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <c:set var="warehouseId" value="${baobiao.warehouseId}"/>
                            <c:set var="providerId" value="${baobiao.providerId}"/>
                            <c:set var="workerId" value="${baobiao.workerId}"/>
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${providermap[providerId].name}</td>
                                <td>${baobiao.date}</td>
                                <td>${warehousemap[warehouseId].name}</td>
                                <td>${usermap[workerId].name}</td>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.number}</td>
                                <td><script>document.write(${baobiao.number}*${baobiao.price});</script></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30%">产品名</th>
                            <th width="30%">总数量</th>
                            <th width="30%">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${inBaobiaoday}">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <tr>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.sumNum}</td>
                                <td>${baobiao.sumPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="hide"><div class="main_form autoScroll">
                <div class="main_form autoScroll">
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30px">序号</th>
                            <th width="100px">供应商</th>
                            <th width="100px">时间</th>
                            <th width="100px">入库仓库</th>
                            <th width="100px">负责人</th>
                            <th width="100px">产品名称</th>
                            <th width="100px">数量</th>
                            <th width="100px">金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${mouthbaobiaos}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <c:set var="warehouseId" value="${baobiao.warehouseId}"/>
                            <c:set var="providerId" value="${baobiao.providerId}"/>
                            <c:set var="workerId" value="${baobiao.workerId}"/>
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${providermap[providerId].name}</td>
                                <td>${baobiao.date}</td>
                                <td>${warehousemap[warehouseId].name}</td>
                                <td>${usermap[workerId].name}</td>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.number}</td>
                                <td><script>document.write(${baobiao.number}*${baobiao.price});</script></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30%">产品名</th>
                            <th width="30%">总数量</th>
                            <th width="30%">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${inBaobiaomonth}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <tr>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.sumNum}</td>
                                <td>${baobiao.sumPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="hide"><div class="main_form autoScroll">
                <div class="main_form autoScroll">
                    <table class="baobiao" id="table1">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30px">序号</th>
                            <th width="100px">供应商</th>
                            <th width="100px">时间</th>
                            <th width="100px">入库仓库</th>
                            <th width="100px">负责人</th>
                            <th width="100px">产品名称</th>
                            <th width="100px">数量</th>
                            <th width="100px">金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${seasonbaobiaos}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <c:set var="warehouseId" value="${baobiao.warehouseId}"/>
                            <c:set var="providerId" value="${baobiao.providerId}"/>
                            <c:set var="workerId" value="${baobiao.workerId}"/>
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${providermap[providerId].name}</td>
                                <td>${baobiao.date}</td>
                                <td>${warehousemap[warehouseId].name}</td>
                                <td>${usermap[workerId].name}</td>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.number}</td>
                                <td><script>document.write(${baobiao.number}*${baobiao.price});</script></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30%">产品名</th>
                            <th width="30%">总数量</th>
                            <th width="30%">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${inBaobiaoseason}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <tr>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.sumNum}</td>
                                <td>${baobiao.sumPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
</div>
<div id="out" class="in_out">
<p class="title">出库报表<a class="close" onclick="hideOverlay()"><img src="images/close.png"></a></p>
    <div class="tab">
        <div class="tab_menu">
            <ul>
                <li class="selected">出库日报</li>
                <li>出库月报</li>
                <li>季度报表</li>
            </ul>
        </div>
        <div class="tab_box">
            <div>
                <div class="main_form autoScroll">
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30px">序号</th>
                            <th width="100px">供应商</th>
                            <th width="100px">时间</th>
                            <th width="100px">入库仓库</th>
                            <th width="100px">负责人</th>
                            <th width="100px">产品名称</th>
                            <th width="100px">数量</th>
                            <th width="100px">金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${dayoutbaobiaos}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <c:set var="warehouseId" value="${baobiao.warehouseId}"/>
                            <c:set var="providerId" value="${baobiao.providerId}"/>
                            <c:set var="workerId" value="${baobiao.workerId}"/>
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${providermap[providerId].name}</td>
                                <td>${baobiao.date}</td>
                                <td>${warehousemap[warehouseId].name}</td>
                                <td>${usermap[workerId].name}</td>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.number}</td>
                                <td><script>document.write(${baobiao.number}*${baobiao.price});</script></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30%">产品名</th>
                            <th width="30%">总数量</th>
                            <th width="30%">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${outBaobiaoday}">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <tr>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.sumNum}</td>
                                <td>${baobiao.sumPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="hide">
                <div class="main_form autoScroll">
                <div class="main_form autoScroll">
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30px">序号</th>
                            <th width="100px">供应商</th>
                            <th width="100px">时间</th>
                            <th width="100px">入库仓库</th>
                            <th width="100px">负责人</th>
                            <th width="100px">产品名称</th>
                            <th width="100px">数量</th>
                            <th width="100px">金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${monthoutbaobiaos}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <c:set var="warehouseId" value="${baobiao.warehouseId}"/>
                            <c:set var="providerId" value="${baobiao.providerId}"/>
                            <c:set var="workerId" value="${baobiao.workerId}"/>
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${providermap[providerId].name}</td>
                                <td>${baobiao.date}</td>
                                <td>${warehousemap[warehouseId].name}</td>
                                <td>${usermap[workerId].name}</td>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.number}</td>
                                <td><script>document.write(${baobiao.number}*${baobiao.price});</script></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30%">产品名</th>
                            <th width="30%">总数量</th>
                            <th width="30%">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${outBaobiaomonth}">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <tr>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.sumNum}</td>
                                <td>${baobiao.sumPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            </div>
            <div class="hide">
                <div class="main_form autoScroll">
                <div class="main_form autoScroll">
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30px">序号</th>
                            <th width="100px">供应商</th>
                            <th width="100px">时间</th>
                            <th width="100px">入库仓库</th>
                            <th width="100px">负责人</th>
                            <th width="100px">产品名称</th>
                            <th width="100px">数量</th>
                            <th width="100px">金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${seasonoutbaobiaos}" varStatus="status">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <c:set var="warehouseId" value="${baobiao.warehouseId}"/>
                            <c:set var="providerId" value="${baobiao.providerId}"/>
                            <c:set var="workerId" value="${baobiao.workerId}"/>
                            <tr>
                                <td>${status.index+1}</td>
                                <td>${providermap[providerId].name}</td>
                                <td>${baobiao.date}</td>
                                <td>${warehousemap[warehouseId].name}</td>
                                <td>${usermap[workerId].name}</td>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.number}</td>
                                <td><script>document.write(${baobiao.number}*${baobiao.price});</script></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="baobiao">
                        <thead style="background-color: #8FC34B">
                        <tr>
                            <th width="30%">产品名</th>
                            <th width="30%">总数量</th>
                            <th width="30%">总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="baobiao" items="${outBaobiaoseason}">
                            <c:set var="productId" value="${baobiao.productId}"/>
                            <tr>
                                <td>${productmap[productId].name}</td>
                                <td>${baobiao.sumNum}</td>
                                <td>${baobiao.sumPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            </div>
            </div>
    </div>
</div>
