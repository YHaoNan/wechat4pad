(function(){
    var win_height = $(window).height();
    $(window).resize(function(){
        var win_height_diff = win_height - $(window).height();
        if(win_height_diff>0){
            console.log("HEIGHT CHANGED!!!");
            var chat_panel = $('.chat_bd .scroll-content')[0];
            chat_panel.scrollTop += win_height_diff;
        }
    })
}())