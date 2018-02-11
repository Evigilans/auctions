var options = {
    valueNames: [ 'project-name', 'project-title', 'project-label' ]
};

var userList = new List('sandbox', options);

$('#viewSwitch').on('click',function(e) {
    if ($('ul').hasClass('grid-au')) {
        $('ul').removeClass('grid-au').addClass('list-au');
    }
    else if($('ul').hasClass('list-au')) {
        $('ul').removeClass('list-au').addClass('grid-au');
    }
});