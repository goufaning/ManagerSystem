<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css"rel="stylesheet" href="css/main.css">
<script type="text/javascript">
    function save() {
        var msg = $("#submitForm").serialize();
        msg = decodeURIComponent(msg,true);
        var json = "[{";
        var msg2 = msg.split("&");     //先以“&”符号进行分割，得到一个key=value形式的数组
        var t = false;
        for(var i = 0; i<msg2.length; i++){
            var msg3 = msg2[i].split("=");    //再以“=”进行分割，得到key，value形式的数组
            for(var j = 0; j<msg3.length; j++){
                json+="\""+msg3[j]+"\"";
                if(j+1 != msg3.length){
                    json+=":";
                }
                if(t){
                    json+="}";
                    if(i+1 != msg2.length){    //表示是否到了当前行的最后一列
                        json+=",{";
                    }
                    t=false;
                }
                if(msg3[j] == "product_number"){    //这里的“canshu5”是你的表格的最后一列的input标签的name值，表示是否到了当前行的最后一个input
                    t = true;
                }
            }
            if(!msg2[i].match("product_number")){    //同上
                json+=";";
            }
        }
        json+="]";
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            url: "${pageContext.request.contextPath}/InWarehoustServlet",//传入后台的地址/方法
            data: json,//参数，这里是一个json语句
            success: function (data) {
                var result = data.d;
                alert(result);
            },
        });
    }
    $("form[name='form1']").submit();
    $(document).ready(function(){
        //<tr/>居中
        $("#tab tr").attr("align","center");
        $(".input_text").attr("disabled",true);
        $(".input_text").attr("readonly",true);
        //增加<tr/>
        $("#but").click(function(){
            var _len = $("#tab tr").length+1;
            var price = $('option:selected', '#product_list').attr("label");
            var provider = $("input[name=provider]").val();
            var warehouse = $("input[name=warehouse]").val();
            $("#tab").append("<tr id="+_len+" width=20% align='center'>"
                +"<td>"+_len+"</td>"
                +"<td><input type='text'  readonly='true' class='input_text'  name='prodect_name' id='desc"+_len+"' value='"+$("input[name=product]").val()+"' /></td>"
                +"<td>台</td>"
                +"<input type='hidden' name='provider' value='"+provider+"'>"
                +"<input type='hidden' name='warehouse' value='"+warehouse+"'>"
               +"<td><input type='text' name='price' id='price"+_len+"' value='"+price+"' /></td>"
                +"<td><input type='number' name='desc"+_len+"' id='desc"+_len+"' disabled='true' min='1' max='100' value='100'/></td>"
                +"<td><input type='number' min='0' name='product_number' id='number"+_len+"' value='"+$("input[name=number]").val()+"' /></td>"
                +"<td>"+($("input[name=number]").val() * price).toFixed(2) +"</td>"
                +"</tr>");
        })
    })
</script>
<div id="a" class="div_rigth">
    <label class="title"><a href="manager.jsp"><p class="back">&lt;&nbsp;返回</p></a><span>入库管理</span></label>
    <hr />
    <div class="work_area">
        <div id="work">
                <div class="work">
                    <label>供应商</label>
                    <input type="text" list="provider_list" id="provider" name="provider" />
                    <datalist id="provider_list">
                        <c:forEach var="provider" items="${providerList}" >
                            <option value="${provider.name}">${provider.name}</option>
                        </c:forEach>
                    </datalist>

                    <label>仓库</label>
                    <input type="text" list="warehouse_list" id="warehouse" name="warehouse" />
                    <datalist id="warehouse_list">
                        <c:forEach var="warehouse" items="${wareHouseList}">
                            <option value="${warehouse.name}">${warehouse.name}</option>
                        </c:forEach>
                    </datalist>
                </div>
                <hr/>
            <div class="work">
                货品名称
                <input type="text" list="product__ist" id="product" name="product" />
                <datalist id="product__ist">
                    <select id="product_list">
                    <c:forEach var="product" items="${productList}">
                        <option label="${product.price}" value="${product.name}">${product.name}</option>
                    </c:forEach>
                    </select>
                </datalist>
                数量
                <input name="number" type="number" min="0" value="1"/>
                    <input type="button" id="but" value="增加"/>
            </div>
        <div class="main_form autoScroll">
            <form id="submitForm" name = "form2" method="post" >
            <table  frame=hsides>
                <thead>
                <tr>
                    <td width="35px">序号</td>
                    <td width="200px">品名型号</td>
                    <td width="150px">规格</td>
                    <td width="110px">单价</td>
                    <td width="110px">折扣</td>
                    <td width="110px">数量</td>
                    <td width="150px">金额</td>
                    <td width="auto"></td>
                </tr>
                </thead>
                <tbody id="tab">
                </tbody>
            </table>
            </form>
        </div>
        <button id="in_button" class="mybtn" onclick="save();">入库</button>
    </div>
</div>