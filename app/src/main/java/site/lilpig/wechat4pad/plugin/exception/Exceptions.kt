package site.lilpig.wechat4pad.plugin.exception

import site.lilpig.wechat4pad.plugin.Plugin
import java.lang.Exception
import java.lang.RuntimeException

class SettingIdAlreadyUsedException(settingId: String): Exception("Setting id \"${settingId}\" was already used.")