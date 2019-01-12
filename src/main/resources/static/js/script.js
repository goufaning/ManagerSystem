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

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        // 获取已激活的标签页的名称
        var activeTab = $(e.target).text();
       $('#goodsManager').load("<div th:include=\"searchWarehouse::#searchWarehouse\">");
    });
});

