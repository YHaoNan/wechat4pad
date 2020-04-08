package site.lilpig.wechat4pad

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.webkit.*
import site.lilpig.wechat4pad.utils.FileUtils

class WXWebView(context: Context?, attrs: AttributeSet?) : WebView(context, attrs) {

    private val cssToInject: MutableList<String>
    private val jsToInject: MutableList<String>
    inner class WebViewJavaScriptInterface{
        @JavascriptInterface
        fun injectCSS(html: String){
            refreshWebViewContent()
        }
    }

    init {
        Log.i("WXWebView","Start init...")

        cssToInject = ArrayList()
        jsToInject = ArrayList()

        webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                injectJS(view)
                injectCSS(view)
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.i("WXWebView","LoadURL ${request?.url.toString()}")
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
        addJavascriptInterface(WebViewJavaScriptInterface(),"_env_")
        requestFocusFromTouch()

        Log.i("WXWebView","End init...")
    }
    private fun refreshWebViewContent() {

    }

    private fun injectJS(view: WebView?) {
        if (view != null)
            for (js in jsToInject){
                view.loadUrl("javascript:${js}")
            }
    }

    private fun injectCSS(view: WebView?) {
        if (view!=null){
            for(css in cssToInject){
                view.loadUrl("javascript:$(document.head).append('<style>${css}</style>'    )")
//                view.loadUrl("javascript:console.log(document.head.innerHTML.substring(document.head.innerHTML.length - 60,document.head.innerHTML.length))")
            }
        }
    }

    /**
     * Add a css path to inject list
     * Every CSS in list will inject when app startup or app reloaded
     */
    fun addCSSToInjectList(cssPath: String){
        val cssContent = FileUtils.getFromAssets(context,"builtin_css/${cssPath}",false)
        cssToInject.add(cssContent)

    }

    fun addJSToInjectList(jsPath: String){
        val jsContent = FileUtils.getFromAssets(context,"builtin_js/${jsPath}", true)
        jsToInject.add(jsContent)
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
        clearHistory();
        (parent as ViewGroup).removeView(this)
        destroy()
    }
}