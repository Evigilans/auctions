$(document).ready(function () {
    $("#burger").click(function () {
        $(".menu-ul").slideToggle("fast");
        $(this).css("font-size", "4rem");
    });
});