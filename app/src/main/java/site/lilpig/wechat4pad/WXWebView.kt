package site.lilpig.wechat4pad

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.webkit.*
import site.lilpig.wechat4pad.plugin.AssetsPluginFactory
import site.lilpig.wechat4pad.plugin.Plugin
import site.lilpig.wechat4pad.plugin.PluginFactory
import site.lilpig.wechat4pad.utils.FileUtils

class WXWebView(context: Context?, attrs: AttributeSet?) : WebView(context, attrs) {

    private val pluginList: MutableList<Plugin> = ArrayList()

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

        setWebSettings()
        requestFocusFromTouch()

        Log.i("WXWebView","End init...")
    }

    private fun initBuiltInPlugin() {
        pluginList.add(AssetsPluginFactory(context).createPlugin("jquery_support"))
        pluginList.add(AssetsPluginFactory(context).createPlugin("baseui"))
    }

    private fun callEachPlugin() {
        for (plugin in pluginList){
            plugin.injectFiles(this)
        }
    }


    private fun setWebSettings() {
        settings.userAgentString = WXWebViewContacts.UA
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.defaultTextEncodingName= "utf-8"
        settings.loadsImagesAutomatically = true
        settings.setSupportZoom(true)
        settings.useWideViewPort = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        settings.loadWithOverviewMode = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.setAppCacheEnabled(true)
    }

    fun destoryWebView(){
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        clearHistory()
        (parent as ViewGroup).removeView(this)
        destroy()
    }
}