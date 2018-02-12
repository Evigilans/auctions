$(document).ready(function () {
    ///hover container lang menu
    $("#lang-menu").hover(
        function () {
            $(this).addClass("cls-border-lang");
            $(this).children().eq(0).addClass("cls-borderbottom-lang");
            $("#lang-menu ul").stop().slideToggle(100);
        },
        function () {
            $(this).removeClass("cls-border-lang");
            $(this).children().eq(0).removeClass("cls-borderbottom-lang");
            $("#lang-menu ul").stop().slideToggle(100);
        }
    );
    /// click languages
    $("#lang-menu ul li").on("click", function () {
        //select lang and apply changes
        $lang = $(this).text();
        console.log($lang);
        $("#lang-menu div").text($lang);
    });

});