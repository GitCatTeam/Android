package com.example.gitcat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.net.URL
import org.json.JSONObject
import com.google.gson.Gson
import android.R.attr.data
import android.app.Activity
import android.webkit.*
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.webkit.JavascriptInterface
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log.d
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener{

            //화면 이동
//            var intent = Intent(this,Info1Activity::class.java)
//            startActivity(intent)

            webview.visibility = View.VISIBLE
            startButton.visibility = View.GONE

            val myWebView: WebView = findViewById(R.id.webview)
            val myURL = "https://a.gitcat.app/api/auth/github"

            myWebView.settings.javaScriptEnabled = true
            myWebView.settings.setSupportZoom(true)
            //myWebView.addJavascriptInterface(WebPasser(),"Android")
            d("*+*+*+","가즈아아아")
//            myWebView.addJavascriptInterface(object : Any() {
//                @JavascriptInterface
//                fun showToast(datas: String?, msg:String) {
//                    d("*+*+","시작한다")
//                    Toast.makeText(this@MainActivity, "Keyword is $datas, and message is $msg", Toast.LENGTH_LONG)
//                        .show()
//                    var dataaaa:String = ChCrypto.aesDecrypt("dddd",datas!!)
//                    d("*+*+",dataaaa)
//                }
//            }, "Android")
            myWebView.addJavascriptInterface(WebPasser(),"Android")

            myWebView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            myWebView.loadUrl(myURL)
            myWebView.webChromeClient = object : WebChromeClient(){}
            myWebView.webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }
            }
        }

    }

}

class WebPasser{

    private var mContext: Activity? = null
    private var mWebView: WebView? = null

    fun WebPasser(c: Activity, w:WebView) {
        mContext = c
        mWebView = w
    }

    @JavascriptInterface
    fun showToast(datas: String?, msg:String){
        d("*+*+*+","시작한다")
        //var data = JSONObject(datas)
        //val obj = data.get(0) as JSONObject
        d("*+*+*+","====== ${datas}")

    }

}
