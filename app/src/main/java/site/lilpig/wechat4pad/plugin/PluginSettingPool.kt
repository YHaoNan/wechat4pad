package site.lilpig.wechat4pad.plugin

import site.lilpig.wechat4pad.plugin.exception.SettingIdAlreadyUsedException

class PluginSettingPool{
    private val pool: MutableMap<String,PluginSetting> = HashMap()

    fun addPluginSetting(pluginSetting: PluginSetting){
        if (pool.containsKey(pluginSetting.id))
            throw SettingIdAlreadyUsedException(pluginSetting.id)
        pool.put(pluginSetting.id,pluginSetting)
    }

    fun getPluginSetting(id: String): PluginSetting? {
        return pool.get(id)
    }
}