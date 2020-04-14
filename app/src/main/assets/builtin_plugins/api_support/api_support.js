(function(){
    /*About settings*/
    __env__.getBoolSetting = function(id){return JSON.parse(__env__.getSettingJI(id)).bool};
    __env__.getIntSetting = function(id){return JSON.parse(__env__.getSettingJI(id)).int};
    __env__.getFloatSetting = function(id){return JSON.parse(__env__.getSettingJI(id)).float};
    __env__.getStringSetting = function(id){return JSON.parse(__env__.getSettingJI(id)).string};
    /*About plugin*/
    __env__.getPluginList = function(id){return JSON.parse(__env__.getPluginListJI())};
    __env__.getPluginsShowUpInList = function(id){return JSON.parse(__env__.getPluginListJI()).filter(function(p){return p.showup_in_plugin_list})};
}())