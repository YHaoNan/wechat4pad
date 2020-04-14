(function(){
    var plugin_list = $("<ul id='wx4pad_plugin_list'></ul>");
    var plugins = __env__.getPluginsShowUpInList();
    for(i in plugins){
        var plugin = plugins[i];
        var plugin_item = $("<li class='wx4pad_plugin_item'></li>");
        var plugin_icon = $("<img>",{
            class: "wx4pad_plugin_item_icon",
            src: plugin.icon
        });
        var plugin_name = $("<div>",{
            class: "wx4pad_plugin_item_name"
        });
        plugin_name.text(plugin.name);
        plugin_item.append(plugin_icon);
        plugin_item.append(plugin_name);
        (function(i){
            plugin_item.click(function(){
                // 我也不知道为啥i是string 可能是JSON就这样吧，parse一下就好了
                __env__.openPluginUI(parseInt(i));
            })
        })(i)
        plugin_list.append(plugin_item);
    }
    __env__.addToDrawer("已安装插件",plugin_list);
}())