package site.lilpig.wechat4pad.wxwebview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.webkit.*
import site.lilpig.wechat4pad.plugin.AssetsPluginFactory
import site.lilpig.wechat4pad.plugin.Plugin
import site.lilpig.wechat4pad.plugin.PluginSettingPool

class WXWebView(context: Context?, attrs: AttributeSet?) : WebView(context, attrs) {

    private val pluginList: MutableList<Plugin> = ArrayList()
    private val pluginSettingPool = PluginSettingPool()

    init {
        Log.i("WXWebView","Start init...")

        initBuiltInPlugin()

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                callEachPlugin()
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                loadUrl(request?.url.toString())
                return true
            }
        }

        webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                if (consoleMessage!=null)
                    Log.i("WXWebView (consolelog)","[${consoleMessage.messageLevel()}] ${consoleMessage.message()} ||| ")
                return super.onConsoleMessage(consoleMessage)
            }

        }

        addJavascriptInterface(WXWebViewJavaScriptInterface(this,pluginSettingPool),"__env__")

        DefaultWXWebViewConfig().set(this)

        Log.i("WXWebView","End init...")
    }

    private fun initBuiltInPlugin() {
        pluginList.add(AssetsPluginFactory(context,pluginSettingPool).createPlugin("jquery_support"))
        pluginList.add(AssetsPluginFactory(context,pluginSettingPool).createPlugin("baseui"))
    }

    private fun callEachPlugin() {
        for (plugin in pluginList){
            plugin.injectFiles(this)
        }
    }


    fun destoryWebView(){
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        clearHistory()
        (parent as ViewGroup).removeView(this)
        destroy()
    }
}