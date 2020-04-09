package site.lilpig.wechat4pad.plugin

import android.util.Log
import site.lilpig.wechat4pad.WXWebView

class Plugin(val name: String,val author: String) {
    var desc: String? = null
    var icon: String? = null
    var settings: MutableList<PluginSetting> = ArrayList()
    var injectList: MutableList<Map<String,String>> = ArrayList()

    fun injectFiles(webView: WXWebView){
        for (inject in injectList){
            if(inject.get("type") == "css"){
                webView.loadUrl("javascript:$(document.head).append('<style>${inject.get("content")}</style>')")
            }else if (inject.get("type") == "js"){
                webView.loadUrl("javascript:${inject.get("content")}")
            }
            Log.i("Plugin",inject.get("content"))

        }
    }
}