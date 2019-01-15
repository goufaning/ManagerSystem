$(document).ready( function() {
    menuClickAction();
    pageInit();

});

function pageInit() {
    var $submenuli = $('.submenu li');
    $submenuli.first().addClass('chosen');
    $('#main').load("page/goodsManager");
}

function menuClickAction() {
    var $submenu = $('.submenu');
    var $mainmenu = $('.mainmenu');
    $submenu.hide();
    $submenu.first().delay(400).slideDown(700);
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
    $(".submenu li").click(function() {
        var url = "page/" + $(this).attr("name");
        $('#main').load(url);
    })
}