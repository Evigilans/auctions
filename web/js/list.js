$('button').on('click', function (e) {
    if ($(this).hasClass('list')) {
        $('#container ul').addClass('list').removeClass('grid');
    } else if ($(this).hasClass('grid')) {
        $('#container ul').addClass('grid').removeClass('list');
    }
});