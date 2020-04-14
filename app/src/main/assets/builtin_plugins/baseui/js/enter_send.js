/**
 * @Author: LILPIG
 * @Date: 2020-04-14
 * 回车发送相关
 */

 (function(){
    var is_enabled = __env__.getBoolSetting("wx4pad.baseui.sendmsgbyenter");
    var send_button = $(".btn_send");
    var next_line_button = $("<a class='btn btn_next_line'>换行</a>")
    next_line_button.click(function(){
        $("#editArea").text($("#editArea").text()+"\n");
    });
    $(".box_ft .content").append(next_line_button);
    if(is_enabled){
        send_button.css("display","none");
        next_line_button.css("display","inline-block")
    }else{
        send_button.css("display","inline-block");
        next_line_button.css("display","none")
    }
 }())