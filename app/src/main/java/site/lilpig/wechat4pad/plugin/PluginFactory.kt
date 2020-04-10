package site.lilpig.wechat4pad.plugin

import android.content.Context
import android.os.Environment
import org.json.JSONObject
import site.lilpig.wechat4pad.utils.FileUtils
import java.io.File


abstract class PluginFactory(val settingPool: PluginSettingPool){
    fun createPlugin(path: String): Plugin{
        val configJson = JSONObject(readFile("${path + File.separator}config.json"))
        val plugin = Plugin(configJson.getString("name"), configJson.getString("author"))
        plugin.desc = configJson.getString("desc")
        plugin.icon = configJson.getString("icon")
        val settings = configJson.getJSONArray("settings")
        for (i in 0..settings.length()-1){
            val setting = settings.getJSONObject(i)
            val id = setting.getString("id")
            val name = setting.getString("name")
            val desc = setting.getString("desc")
            val type = setting.getString("type")
            val defaultValue = setting.get("default")
            val pluginSetting = PluginSetting(id,name,desc,type,defaultValue)
            plugin.settings.add(pluginSetting)
            settingPool.addPluginSetting(pluginSetting)
        }
        val inject = configJson.getJSONArray("inject")
        for (i in 0..inject.length()-1){
            val ijPath = inject.getString(i)
            val ext = ijPath.substring(ijPath.lastIndexOf(".")+1,ijPath.length)
            if (ext in arrayOf("css","js")){
                val map:MutableMap<String,String> = HashMap()
                map.put("type",ext)
                val content = readFile("${path}/${ijPath}")
                map.put("content",content)
                plugin.injectList.add(map)
            }
        }
        return plugin
    }
    abstract fun readFile(path: String): String
}

class AssetsPluginFactory(val context: Context,val pool: PluginSettingPool): PluginFactory(pool){
    override fun readFile(path: String): String {
        return FileUtils.getFromAssets(context,"builtin_plugins${File.separator + path}")
    }
}