$(document).ready( function() {
    menuClickAction();
    pageInit();

});
/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */

(function ($) {
    "use strict";
    var mainApp = {

        initFunction: function () {
            /*MENU
            ------------------------------------*/
            $('#main-menu').metisMenu();

            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });
        },

        initialization: function () {
            mainApp.initFunction();

        }

    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.initFunction();
    });

}(jQuery));

function pageInit() {
    var $submenuli = $('.submenu li');
    $submenuli.first().addClass('chosen');
    $('#main').load("page/warehouseManager");
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