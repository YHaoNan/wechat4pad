package site.lilpig.wechat4pad.wxwebview

import android.webkit.WebSettings

interface WXWebViewConfig{
    fun set(webView: WXWebView)
}


/**
 * This class provide a basic config for webview. Include js support, zoom, cache, etc.
 */
class DefaultWXWebViewConfig: WXWebViewConfig{


    override fun set(webView: WXWebView) {
        val settings = webView.settings
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
        webView.requestFocusFromTouch()
    }
}