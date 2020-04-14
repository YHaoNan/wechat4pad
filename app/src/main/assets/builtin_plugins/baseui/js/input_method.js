/**
 * @Author: LILPIG
 * @Date: 2020-04-14
 * 修复输入法弹出时，或者说界面高度改变时，聊天列表不跟着整体上移的难受问题
 */
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