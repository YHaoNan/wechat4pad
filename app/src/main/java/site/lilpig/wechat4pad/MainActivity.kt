package site.lilpig.wechat4pad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            am_wx_view.loadUrl("https://wx.qq.com")
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        am_wx_view.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        am_wx_view.restoreState(savedInstanceState)
    }

    override fun onDestroy() {
//        am_wx_view.destoryWebView()
        super.onDestroy()
    }
}
