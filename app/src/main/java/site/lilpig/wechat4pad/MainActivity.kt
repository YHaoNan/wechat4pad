package site.lilpig.wechat4pad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        am_wx_view.addCSSToInjectList("theme/default.css")
        am_wx_view.addJSToInjectList("jquery.js")
        am_wx_view.addJSToInjectList("default.js")
        am_wx_view.loadUrl("https://wx.qq.com")
    }
}