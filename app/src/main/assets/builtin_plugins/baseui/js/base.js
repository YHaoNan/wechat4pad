/**
  * @Author: LILPIG
  * @Date: Unknown
  * 创建项目初期写的代码，很乱，有时间会改改，主要对界面做了一些基本的操作让它更适合平板
  *
  */
(function(){
    $(document.head).append('<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />');
    $(".login .lang,.login .copyright").wrapAll("<div class='lang-and-copyright' style='position: absolute;bottom: 20px;left: 50%;transform: translateX(-50%);text-align: center;text-shadow: 0 0 black;'></div>");
    $(".login_box .qrcode .sub_desc").text("WeChat4PAD基于微信网页版");
    $(".web_wechat_screencut").remove();
    $("#editArea").css('height','1.5em');
    $(".chat .box_ft").css('height','80px');
    $(".chat .box_bd").css('bottom','80px');
    var sendBtn = $(".box_ft .btn_send");
    $(".box_ft .content").append(sendBtn).css('display','flex').css('align-items','center');
    $(".box_ft .action").remove();

    $(".js_fileupload").click(function(){
        __env__.toast("暂不支持此功能")
    });
}())
