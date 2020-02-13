package com.example.gitcat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener{

            //화면 이동
            //var intent = Intent(this,Info1Activity::class.java)
            //startActivity(intent)
            webview.visibility = View.VISIBLE
            startButton.visibility = View.GONE

            val myWebView: WebView = findViewById(R.id.webview)
            val myURL = "https://a.gitcat.app/api/auth/github"
            myWebView.settings.javaScriptEnabled = true
            myWebView.settings.setSupportZoom(true)
            myWebView.addJavascriptInterface(WebPasser(),"Android")
            myWebView.loadUrl(myURL)

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

    var mContext: Context? = null

    fun WebPasser(c:Context) {
        mContext = c
    }

    @JavascriptInterface
    fun showToast(toast:String){
        Toast.makeText(mContext,"결과는 $toast 입니다.",Toast.LENGTH_SHORT).show()
    }
}
