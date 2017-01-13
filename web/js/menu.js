$(document).ready(function(){
    var $div_li = $('#jcSide li a')
    $('#jcSide li a').click(function () {
        var index = $div_li.index(this);
        $(this).parent().addClass("select").siblings().removeClass("select");
        $("#jcContent div").eq(index).show().siblings().hide();
    })
});
function showOverlay() {
    $("#overlay").height(pageHeight());
    $("#overlay").width(pageWidth());

    // fadeTo第一个参数为速度，第二个为透明度
    // 多重方式控制透明度，保证兼容性，但也带来修改麻烦的问题
    $("#overlay").fadeTo(200, 0.5);
    $("#in").show();
}

function showOverlay1() {
    $("#overlay").height(pageHeight());
    $("#overlay").width(pageWidth());

    // fadeTo第一个参数为速度，第二个为透明度
    // 多重方式控制透明度，保证兼容性，但也带来修改麻烦的问题
    $("#overlay").fadeTo(200, 0.5);
    $("#out").show();
}

/* 隐藏覆盖层 */
function hideOverlay() {
    $("#overlay").fadeOut(200);
    $(".in_out").hide();
}

/* 当前页面高度 */
function pageHeight() {
    return document.body.scrollHeight;
}

/* 当前页面宽度 */
function pageWidth() {
    return document.body.scrollWidth;
}
