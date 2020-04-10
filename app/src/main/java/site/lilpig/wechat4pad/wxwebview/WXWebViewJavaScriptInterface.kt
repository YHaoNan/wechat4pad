package site.lilpig.wechat4pad.wxwebview

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import org.json.JSONObject
import site.lilpig.wechat4pad.plugin.PluginSettingPool

class WXWebViewJavaScriptInterface(val wxWebView: WXWebView,val pluginSettingPool: PluginSettingPool){
    @JavascriptInterface
    fun getSetting(settingId: String): String{
        val pluginSetting = pluginSettingPool.getPluginSetting(settingId)
        if (pluginSetting==null)return ""
        val sharedpreferences = wxWebView.context.getSharedPreferences(WXWebViewContacts.SETTINGS_PREF_NAME, Context.MODE_PRIVATE)
        var resultBool: Boolean? = null
        var resultInt: Int? = null
        var resultFloat: Float? = null
        var resultString: String? = null
        when(pluginSetting.type){
            WXWebViewPluginContacts.TYPE_BOOL ->
                resultBool = sharedpreferences.getBoolean(settingId,pluginSetting.defaultValue as Boolean)
            WXWebViewPluginContacts.TYPE_INT ->
                resultInt = sharedpreferences.getInt(settingId,pluginSetting.defaultValue as Int)
            WXWebViewPluginContacts.TYPE_FLOAT ->
                resultFloat = sharedpreferences.getFloat(settingId,pluginSetting.defaultValue as Float)
            WXWebViewPluginContacts.TYPE_STRING ->
                resultString = sharedpreferences.getString(settingId,pluginSetting.defaultValue as String)
            else ->
                null
        }

        val result = JSONObject()
        result.put("bool",resultBool)
        result.put("int",resultInt)
        result.put("float",resultFloat)
        result.put("string",resultString)

        return result.toString()
    }

    @JavascriptInterface fun setSetting(settingId: String, value: Any){
        val pluginSetting = pluginSettingPool.getPluginSetting(settingId)
        if(pluginSetting==null) return
        val sharedpreferencesEditor = wxWebView.context.getSharedPreferences(WXWebViewContacts.SETTINGS_PREF_NAME,Context.MODE_PRIVATE).edit()
        when(pluginSetting.type){
            WXWebViewPluginContacts.TYPE_BOOL ->
                sharedpreferencesEditor.putBoolean(settingId,value as Boolean)
            WXWebViewPluginContacts.TYPE_INT ->
                sharedpreferencesEditor.putInt(settingId,value as Int)
            WXWebViewPluginContacts.TYPE_FLOAT ->
                sharedpreferencesEditor.putFloat(settingId,value as Float)
            WXWebViewPluginContacts.TYPE_STRING ->
                sharedpreferencesEditor.putString(settingId,value as String)
        }
    }
}