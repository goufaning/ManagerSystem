$(document).ready( function() {
    var $submenu = $('.submenu');
    var $mainmenu = $('.mainmenu');
    $submenu.hide();
    // $submenu.first().delay(400).slideDown(700);
    $submenu.on('click','li', function() {
        $submenu.siblings().find('li').removeClass('chosen');
        $(this).addClass('chosen');
    });
    $mainmenu.on('click', 'li', function() {
        $(this).next('.submenu').slideToggle().siblings('.submenu').slideUp();
    });
    $mainmenu.children('li:last-child').on('click', function() {
        $mainmenu.fadeOut().delay(500).fadeIn();
    });
});

var search_type_storage = "none";
var search_keyWord = "";
var search_repository = "";
var select_goodsID;
var select_repositoryID;
$(function() {
    optionAction();
    searchAction();
    storageListInit();
    bootstrapValidatorInit();
    repositoryOptionInit();
    addStorageAction();
    editStorageAction();
    deleteStorageAction();
    importStorageAction();
    exportStorageAction()
})
// 查询方式下拉框，为search_type_storage赋值，若为所有，搜索框不能编辑
function optionAction() {
    $(".dropOption").click(function() {
        var type = $(this).text();
        $("#search_input").val("");
        if (type == "所有") {
            $("#search_input_type").attr("readOnly", "true");
            search_type_storage = "searchAll";
        } else if (type == "货物ID") {
            $("#search_input_type").removeAttr("readOnly");
            search_type_storage = "searchByGoodsID";
        } else if (type == "货物名称") {
            $("#search_input_type").removeAttr("readOnly");
            search_type_storage = "searchByGoodsName";
        } else if(type = "货物类型"){
            $("#search_input_type").removeAttr("readOnly");
            search_type_storage = "searchByGoodsType";
        }else {
            $("#search_input_type").removeAttr("readOnly");
        }
        $("#search_type").text(type);
        $("#search_input_type").attr("placeholder", type);
    })
}
// 仓库下拉框数据初始化，页面加载时完成
function repositoryOptionInit(){
    $.ajax({
        type : 'POST',
        url : '/warehouse/list',
        dataType : 'json',
        contentType : 'application/json',
        // data:{
        //     searchType : "searchAll",
        //     keyWord : "",
        //     offset : -1,
        //     limit : -1
        // },
        success : function(response){
            //组装option
            $.each(response,function(index,elem){
                $('#search_input_repository').append("<option value='" + elem.id + "'>" + elem.id +"号仓库</option>");
            })
        },
        error : function(response){
        }
    });
    $('#search_input_repository').append("<option value='all'>请选择仓库</option>");
}
// 搜索动作
function searchAction() {
    $('#search_button').click(function() {
        search_keyWord = $('#search_input_type').val();
        search_repository = $('#search_input_repository').val();
        tableRefresh();
    })
}
// 分页查询参数
function queryParams(params) {
    var temp = {
        limit : params.limit,
        offset : params.offset,
        searchType : search_type_storage,
        repositoryBelong : search_repository,
        keyword : search_keyWord
    }
    return temp;
}
// 表格初始化
function storageListInit() {
    $('#storageList')
        .bootstrapTable(
            {
                columns : [
                    {
                        field : 'id',
                        title : '货物ID'
                        //sortable: true
                    },
                    {
                        field : 'id',
                        title : '货物名称'
                    },
                    {
                        field : 'id',
                        title : '货物类型'
                    },
                    {
                        field : 'id',
                        title : '货物尺寸',
                        visible : false
                    },
                    {
                        field : 'id',
                        title : '货物价值',
                        visible : false
                    },
                    {
                        field : 'id',
                        title : '仓库ID'
                    },
                    {
                        field : 'id',
                        title : '库存数量'
                    },
                    {
                        field : 'operation',
                        title : '操作',
                        formatter : function(value, row, index) {
                            var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
                            var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                            var fun = '';
                            return s + ' ' + d;
                        },
                        events : {
                            // 操作列中编辑按钮的动作，rowEditOperation(row)，传入row
                            'click .edit' : function(e, value,
                                                     row, index) {
                                //selectID = row.id;
                                rowEditOperation(row);
                            },
                            'click .delete' : function(e,
                                                       value, row, index) {
                                select_goodsID = row.id;
                                select_repositoryID = row.id
                                $('#deleteWarning_modal').modal(
                                    'show');
                            }
                        }
                    } ],
                url : '/inventory/list',
                method : 'post',
                queryParams : queryParams,
                // sidePagination : "server",
                dataType : 'json',
                pagination : true,
                pageNumber : 1,
                pageSize : 5,
                pageList : [ 5, 10, 25, 50, 100 ],
                clickToSelect : true
            });
}
// 表格刷新
function tableRefresh() {
    $('#storageList').bootstrapTable('refresh', {
        query : {}
    });
}
// 行编辑操作模态框展示与数据填充
function rowEditOperation(row) {
    $('#edit_modal').modal("show");
    // load info
    $('#storage_form_edit').bootstrapValidator("resetForm", true);
    $('#storage_goodsID_edit').text(row.goodsID);
    $('#storage_repositoryID_edit').text(row.repositoryID);
    $('#storage_number_edit').val(row.number);
}
// 添加库存信息模态框数据校验
function bootstrapValidatorInit() {
    $("#storage_form").bootstrapValidator({
        message : 'This is not valid',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded : [ ':disabled' ],
        fields : {
            storage_goodsID : {
                validators : {
                    notEmpty : {
                        message : '货物ID不能为空'
                    }
                }
            },
            storage_repositoryID : {
                validators : {
                    notEmpty : {
                        message : '仓库ID不能为空'
                    }
                }
            },
            storage_number : {
                validators : {
                    notEmpty : {
                        message : '库存数量不能为空'
                    }
                }
            }
        }
    })
}
// 编辑库存信息，表单数据提交
function editStorageAction() {
    $('#edit_modal_submit').click(
        function() {
            $('#storage_form_edit').data('bootstrapValidator')
                .validate();
            if (!$('#storage_form_edit').data('bootstrapValidator')
                    .isValid()) {
                return;
            }
            var data = {
                goodsID : $('#storage_goodsID_edit').text(),
                repositoryID : $('#storage_repositoryID_edit').text(),
                number : $('#storage_number_edit').val(),
            }
            // ajax
            $.ajax({
                type : "POST",
                url : 'storageManage/updateStorageRecord',
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify(data),
                success : function(response) {
                    $('#edit_modal').modal("hide");
                    var type;
                    var msg;
                    if (response.result == "success") {
                        type = "success";
                        msg = "库存信息更新成功";
                    } else if (resposne == "error") {
                        type = "error";
                        msg = "库存信息更新失败"
                    }
                    infoModal(type, msg);
                    tableRefresh();
                },
                error : function(response) {
                }
            });
        });
}
// 刪除库存信息
function deleteStorageAction(){
    $('#delete_confirm').click(function(){
        var data = {
            "goodsID" : select_goodsID,
            "repositoryID" : select_repositoryID
        }

        // ajax
        $.ajax({
            type : "GET",
            url : "storageManage/deleteStorageRecord",
            dataType : "json",
            contentType : "application/json",
            data : data,
            success : function(response){
                $('#deleteWarning_modal').modal("hide");
                var type;
                var msg;
                if(response.result == "success"){
                    type = "success";
                    msg = "库存信息删除成功";
                }else{
                    type = "error";
                    msg = "库存信息删除失败";
                }
                infoModal(type, msg);
                tableRefresh();
            },error : function(response){
            }
        })

        $('#deleteWarning_modal').modal('hide');
    })
}
// 添加库存信息
function addStorageAction() {
    $('#add_storage').click(function() {
        $('#add_modal').modal("show");
    });
    $('#add_modal_submit').click(function() {
        var data = {
            goodsID : $('#storage_goodsID').val(),
            repositoryID : $('#storage_repositoryID').val(),
            number : $('#storage_number').val()
        }
        // ajax
        $.ajax({
            type : "POST",
            url : "/inventory/add",
            dataType : "json",
            contentType:"application/json",
            data : JSON.stringify(data),
            success : function(response) {
                $('#add_modal').modal("hide");
                var msg;
                var type;
                if (response.result == "success") {
                    type = "success";
                    msg = "库存信息添加成功";
                } else if (response.result == "error") {
                    type = "error";
                    msg = "库存信息添加失败";
                }
                infoModal(type, msg);
                tableRefresh();
                // reset
                $('#storage_goodsID').val("");
                $('#storage_repositoryID').val("");
                $('#storage_number').val("");
                $('#storage_form').bootstrapValidator("resetForm", true);
            },
            error : function(response) {
            }
        })
    })
}
var import_step = 1;
var import_start = 1;
var import_end = 3;
// 导入库存信息
function importStorageAction() {
    $('#import_storage').click(function() {
        $('#import_modal').modal("show");
    });
    $('#previous').click(function() {
        if (import_step > import_start) {
            var preID = "step" + (import_step - 1)
            var nowID = "step" + import_step;
            $('#' + nowID).addClass("hide");
            $('#' + preID).removeClass("hide");
            import_step--;
        }
    })
    $('#next').click(function() {
        if (import_step < import_end) {
            var nowID = "step" + import_step;
            var nextID = "step" + (import_step + 1);
            $('#' + nowID).addClass("hide");
            $('#' + nextID).removeClass("hide");
            import_step++;
        }
    })
    $('#file').on("change", function() {
        $('#previous').addClass("hide");
        $('#next').addClass("hide");
        $('#submit').removeClass("hide");
    })
    $('#submit').click(function() {
        var nowID = "step" + import_end;
        $('#' + nowID).addClass("hide");
        $('#uploading').removeClass("hide");
        // next
        $('#confirm').removeClass("hide");
        $('#submit').addClass("hide");
        // ajax
        $.ajaxFileUpload({
            url : "storageManage/importStorageRecord",
            secureuri: false,
            dataType: 'json',
            fileElementId:"file",
            success : function(data, status){
                var total = 0;
                var available = 0;
                var msg1 = "库存信息导入成功";
                var msg2 = "库存信息导入失败";
                var info;
                $('#import_progress_bar').addClass("hide");
                if(data.result == "success"){
                    total = data.total;
                    available = data.available;
                    info = msg1;
                    $('#import_success').removeClass('hide');
                }else{
                    info = msg2
                    $('#import_error').removeClass('hide');
                }
                info = info + ",总条数：" + total + ",有效条数:" + available;
                $('#import_result').removeClass('hide');
                $('#import_info').text(info);
                $('#confirm').removeClass('disabled');
            },error : function(data, status){
            }
        })
    })
    $('#confirm').click(function() {
        // modal dissmiss
        importModalReset();
    })
}
// 导出库存信息
function exportStorageAction() {
    $('#export_storage').click(function() {
        $('#export_modal').modal("show");
    })
    $('#export_storage_download').click(function(){
        var data = {
            searchType : search_type_storage,
            repositoryBelong : search_repository,
            keyword : search_keyWord
        }
        var url = "storageManage/exportStorageRecord?" + $.param(data)
        window.open(url, '_blank');
        $('#export_modal').modal("hide");
    })
}
// 导入库存信息模态框重置
function importModalReset(){
    var i;
    for(i = import_start; i <= import_end; i++){
        var step = "step" + i;
        $('#' + step).removeClass("hide")
    }
    for(i = import_start; i <= import_end; i++){
        var step = "step" + i;
        $('#' + step).addClass("hide")
    }
    $('#step' + import_start).removeClass("hide");
    $('#import_progress_bar').removeClass("hide");
    $('#import_result').removeClass("hide");
    $('#import_success').removeClass('hide');
    $('#import_error').removeClass('hide');
    $('#import_progress_bar').addClass("hide");
    $('#import_result').addClass("hide");
    $('#import_success').addClass('hide');
    $('#import_error').addClass('hide');
    $('#import_info').text("");
    $('#file').val("");
    $('#previous').removeClass("hide");
    $('#next').removeClass("hide");
    $('#submit').removeClass("hide");
    $('#confirm').removeClass("hide");
    $('#submit').addClass("hide");
    $('#confirm').addClass("hide");
    //$('#file').wrap('<form>').closest('form').get(0).reset();
    //$('#file').unwrap();
    //var control = $('#file');
    //control.replaceWith( control = control.clone( true ) );
    $('#file').on("change", function() {
        $('#previous').addClass("hide");
        $('#next').addClass("hide");
        $('#submit').removeClass("hide");
    })

    import_step = 1;
}

// 操作结果提示模态框
function infoModal(type, msg) {
    $('#info_success').removeClass("hide");
    $('#info_error').removeClass("hide");
    if (type == "success") {
        $('#info_error').addClass("hide");
    } else if (type == "error") {
        $('#info_success').addClass("hide");
    }
    $('#info_content').text(msg);
    $('#info_modal').modal("show");
}